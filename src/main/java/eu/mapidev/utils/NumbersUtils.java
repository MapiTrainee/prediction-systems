package eu.mapidev.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NumbersUtils {

    public static final String DEFAULT_DELIMITER = ",";

    public static List<Integer> convertTextToNumbers(String textNumbers) {
	return convertTextToNumbers(textNumbers, DEFAULT_DELIMITER);
    }

    public static List<Integer> convertTextToNumbers(String textNumbers, String delimiter) {
	final List<Integer> convertedNumbers = new ArrayList<>();
	if (textNumbers != null) {
	    String[] splitedText = textNumbers.split(delimiter);
	    for (String textNumber : splitedText) {
		convertedNumbers.add(Integer.parseInt(textNumber));
	    }
	}
	return convertedNumbers;
    }

    public static String convertNumbersToText(Collection<Integer> numbers) {
	return NumbersUtils.convertNumbersToText(numbers, DEFAULT_DELIMITER);
    }

    public static String convertNumbersToText(Collection<Integer> numbers, String delimiter) {
	if (numbers != null) {
	    StringBuilder stringBuilder = new StringBuilder();
	    int i = 1;
	    for (int value : numbers) {
		stringBuilder.append(value);
		stringBuilder.append((i != numbers.size()) ? delimiter : "");
		i++;
	    }
	    return stringBuilder.toString();
	}
	return null;
    }
}
