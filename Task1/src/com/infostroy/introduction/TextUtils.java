package com.infostroy.introduction;

/**
 * Проверка орфографии
 * 
 * Некоторые люди не обращают внимание на орфографию. Например, не пишут новое
 * предложение с заглавной буквы. Или не ставят пробел после знаков препинания.
 * 
 * Ваша задача: исправить их ошибки.
 * 
 * Что нужно сделать:
 * 
 * 1. Каждое новое предложение должно начинаться с заглавной буквы. 2. После
 * знаков препинания (точка и запятая) должны быть пробелы.
 */
public class TextUtils {

	private static final char SPACE = ' ';

	public String correctText(String text) {
		StringBuilder correctedText = new StringBuilder();
		char[] inputText = text.toCharArray();
		boolean findDot = false;
		correctedText.append(String.valueOf(text.charAt(0)).toUpperCase());
		for (int i = 1; i < inputText.length; ++i) {
			if (findDot) {
				correctedText.append(String.valueOf(inputText[i]).toUpperCase());
				findDot = false;
			} else {
				correctedText.append(inputText[i]);
			}
			if (inputText[i] == '.' && i + 1 < inputText.length && inputText[i + 1] != SPACE) {
				correctedText.append(SPACE);
				findDot = true;
			}
			if (inputText[i] == ',' && inputText[i + 1] != SPACE) {
				correctedText.append(SPACE);
			}
		}
		return correctedText.toString();
	}
}
