package com.fumec.android.Util;

public class Converter {
	
	
	/**
	 * @author filipefaria
	 * @param valor
	 * @return valor em binario do numero passado no formato integer
	 */
	public static String integerParaBinario(Integer valor){
		
		return Integer.toBinaryString(valor);
	}
	
	/**
	 * @author filipefaria
	 * @param valor
	 * @return valor em binario do numero passado no formato string
	 */
	public static String stringParaBinario(String valor){
		
		return Integer.toBinaryString(Integer.valueOf(valor));
	}
	
	public static Integer stringBinarioParaInteger(String valor){
		return Integer.valueOf(valor, 2);
	}

}
