package com.fumec.android.Util;

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
	
	public static void setTokens(){
		
		Timer.setVariaveis();
		
		Integer soma = 0;
		
		/**primeiro passo
		 *
		 * @operacao 	- somar horario com data
		 * @resultado - cadeia de 12 bits 
		 */
		String horario = Timer.getHorario();
		String data = Timer.getData();
		
		soma = Integer.valueOf(horario) + Integer.valueOf(data);
		
		/**segundo passo
		 *
		 * @operacao - concatenar cadeia de 12 bits do primeiro passo com os 12 bits do ano 
		 * @resultado - cadeia de 24 bits 
		 */
		
		Integer ano = Timer.getAno();
		soma = Integer.valueOf(soma.toString() + ano.toString());
		
		/**terceiro passo
		 *
		 * @operacao - somar o resultado com a chave do usuario 
		 * @resultado - cadeia de 24 bits 
		 */

		soma = soma + chave;
		Log.d("TOKEN", Converter.integerParaBinario(soma));
		Log.d("TOKEN", soma.toString());
		
		tokenAtual = soma;
	}
}
