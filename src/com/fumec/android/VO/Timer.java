package com.fumec.android.VO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class Timer {
	private static Integer hora;
	private static Integer minuto;
	private static Integer dia;
	private static Integer mes;
	private static Integer ano;
	private static String data;
	private static String horario;
	
	public static Integer getHora() {
		return hora;
	}
	public static Integer getMinuto() {
		return minuto;
	}
	public static Integer getDia() {
		return dia;
	}
	public static Integer getMes() {
		return mes;
	}
	public static String getData() {
		return data;
	}
	public static String getHorario() {
		return horario;
	}
	public static Integer getAno() {
		return ano;
	}

	public static void setVariaveis(){
		
		DateFormat dateFormatHora = new SimpleDateFormat("HH");
		DateFormat dateFormatMin = new SimpleDateFormat("mm");
		DateFormat dateFormatDia = new SimpleDateFormat("dd");
		DateFormat dateFormatMes = new SimpleDateFormat("MM");
		DateFormat dateFormatAno = new SimpleDateFormat("yyyy");
		Date date = new Date();
		
		hora = Integer.valueOf(dateFormatHora.format(date));
		minuto = Integer.valueOf(dateFormatMin.format(date));
		dia = Integer.valueOf(dateFormatDia.format(date));
		mes = Integer.valueOf(dateFormatMes.format(date));
		ano = Integer.valueOf(dateFormatAno.format(date));
		
		Log.d("DATA: ", hora + ":" + minuto + " " + dia + "/" + mes + "/" + ano );
		
		horario = ( hora.toString().length() == 1 ? "0" + hora.toString() : hora.toString() ) 
				+ ( minuto.toString().length() == 1 ? "0" + minuto.toString() : minuto.toString() ); 
				 
		data = ( mes.toString().length() == 1 ? "0" + mes.toString() : mes.toString() ) 
				+ ( dia.toString().length() == 1 ? "0" + dia.toString() : dia.toString() );
	}
	
}
