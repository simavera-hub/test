package com.zurich.policy_client.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public abstract class Utilities {

	private static final Pattern patternEmail = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
	private static final Pattern patternAlphabetic = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚäÄëËïÏöÖüÜ¨ñÑ@´'/().,_ :;=%&#+\\*-]{0,1000}$");
	
	public static boolean isNull(final Object obj) {
		return obj == null;
	}

	public static boolean isNotEmpty(final String string) {
		return string != null && !"".equals(string.trim());
	}

	public static boolean isValidEmail(final String correo) {
		
		Matcher matcher = patternEmail.matcher(correo);
		return matcher.matches();
	}

	/**
	 * Valida si la cadena tiene letras o espacios
	 * @param cadena
	 * @return
	 */
	public static boolean isValidAlphabetic(final String cadena) {
		Matcher matcher = patternAlphabetic.matcher(cadena);
		return matcher.matches();
	}

	/**
	 * Valida si el número tiene el tamaño indicado
	 * @param number
	 * @param size
	 * @return
	 */
	public static boolean isValidSize(final Long number, final int size, boolean isExactSize) {
		int count = 0;
 
		// Si el numero es 0, tiene una cifra
		if (number == 0) {
			count = 1;
		} else {
			// Vamos dividiendo entre 10 hasta que lleguemos a 0
			for (int i = Math.abs(number.intValue()); i > 0; i /= 10) {
				count++;
			}
	 
		}
	 
		if(isExactSize){
			return count == size ? true : false;
		}else{
			return count > size ? false : true;
		}

	}

	public static boolean isAfterDate(LocalDate initialDate, LocalDate endDate){
		return endDate.isAfter(initialDate);	
	}

	/**
	 * Valida si es un monto positivo
	 * @param value
	 * @return
	 */
	public static boolean isPositiveNumber(Double value){
		return value < 0.0 ? false : true;
	 	
	}
	
}
