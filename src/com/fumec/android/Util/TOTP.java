package com.fumec.android.Util;

import com.fumec.android.VO.Timer;

public class TOTP {

	private static Integer tokenAtual;
	private static Integer tokenProximo;
	
	public static Integer getTokenAtual() {
		return tokenAtual;
	}
	
	public static Integer getTokenProximo() {
		return tokenProximo;
	}
	
	public static void setTokens(){
		
		Timer.setVariaveis();
		
		String resultado = "";
		Integer soma = 0;
		
		/**primeiro passo
		 *
		 * @operacao 	- somar horario com data
		 * 			 	- transformar em binario
		 * 
		 * @resultado - cadeia de 12 bits 
		 */
		String horario = Timer.getHorario();
		String data = Timer.getData();
		
		soma = Integer.valueOf(horario) + Integer.valueOf(data);
		
		resultado = Converter.integerParaBinario(soma);
		
		/**segundo passo
		 *
		 * @operacao - concatenar cadeia de 12 bits do primeiro passo com os 12 bits do ano 
		 * @resultado - cadeia de 24 bits 
		 */
		
		String ano = Converter.integerParaBinario(Timer.getAno());
		resultado  = resultado + ano;
	}
}
