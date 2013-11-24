package com.fumec.android.Util;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import android.util.Log;

import com.fumec.android.VO.Timer;

public class TOTP {

	private static Integer tokenAtual;
	private static Integer tokenProximo;
	private static Integer chave = 364987;
	public static Integer getTokenAtual() {
		return tokenAtual;
	}
	
	public static Integer getTokenProximo() {
		return tokenProximo;
	}
	
    static public String generateTOTP(String key, String time, String returnDigits){
        int codeDigits = Integer.decode(returnDigits);
        String crypto = "HmacSHA1";
        String result = null;
        byte[] bArray;

        byte[] msg = new byte[8];
        // Put the bytes of "time" to the message
        // Input is the HEX value of "time"
        if(time.length() > 0){
            bArray = new BigInteger(time,16).toByteArray();
            if(bArray.length == 9){
                // First byte is the "sign" byte
                for (int i = 0; i < 8 && i < bArray.length ; i++) {
                    msg[i + 8 - bArray.length] = bArray[i+1];
                }
            }
            else {
                for (int i = 0; i < 8 && i < bArray.length ; i++) {




                    msg[i + 8 - bArray.length] = bArray[i];
                }
            }
        }

        byte[] hash;
        bArray = new BigInteger(key,16).toByteArray();
        if(bArray[0] == 0){
            byte[] b = new byte[bArray.length - 1];
            for(int i = 0 ; i < b.length; i++)
                b[i]=bArray[i+1];
            hash = hmac_sha1(crypto, b, msg);
        }
        else{
            // compute hmac hash
            hash = hmac_sha1(crypto, bArray, msg);
        }

        // put selected bytes into result int
        int offset = hash[hash.length - 1] & 0xf;

        int binary =
            ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) |
            ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);

        int otp = binary % DIGITS_POWER[codeDigits];

        result = Integer.toString(otp);
        while (result.length() < codeDigits) {
            result = "0" + result;
        }
        return result;
    }
    
    private static final int[] DIGITS_POWER = {1,10,100,1000,10000,100000,1000000,10000000,100000000 };
    
    public static byte[] hmac_sha1(String crypto, byte[] keyBytes, byte[] text){
        try {
            Mac hmac;

            hmac = Mac.getInstance(crypto);
            SecretKeySpec macKey =
                new SecretKeySpec(keyBytes, "RAW");
            hmac.init(macKey);
            return hmac.doFinal(text);
        } catch (GeneralSecurityException gse) {
            throw new UndeclaredThrowableException(gse);
        }
    }
	
	public static void setTokens(){
		
		Timer.setVariaveis();
		
		String seed = "3132333435363738393031323334353637383930";
        String time = "0";
        BigInteger b = new BigInteger("0");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        b = new BigInteger("0" + (Timer.getDate()).getTime());
        b = b.divide(new BigInteger("30000"));
        time = b.toString(16).toUpperCase();
        while(time.length() < 16) time = "0" + time;
        System.out.print(" " + time + " | ");
        tokenAtual = Integer.valueOf(generateTOTP(seed, time, "6"));
        
	}
	
//	public static void setTokens(){
//		
//		Timer.setVariaveis();
//		
//		Integer soma = 0;
//		
//		/**primeiro passo
//		 *
//		 * @operacao 	- somar horario com data
//		 * @resultado - cadeia de 12 bits 
//		 */
//		String horario = Timer.getHorario();
//		String data = Timer.getData();
//		
//		soma = Integer.valueOf(horario) + Integer.valueOf(data);
//		
//		/**segundo passo
//		 *
//		 * @operacao - concatenar cadeia de 12 bits do primeiro passo com os 12 bits do ano 
//		 * @resultado - cadeia de 24 bits 
//		 */
//		
//		Integer ano = Timer.getAno();
//		soma = Integer.valueOf(soma.toString() + ano.toString());
//		
//		/**terceiro passo
//		 *
//		 * @operacao - somar o resultado com a chave do usuario 
//		 * @resultado - cadeia de 24 bits 
//		 */
//
//		soma = soma + chave;
//		Log.d("TOKEN", Converter.integerParaBinario(soma));
//		Log.d("TOKEN", soma.toString());
//		
//		tokenAtual = soma;
//	}
}
