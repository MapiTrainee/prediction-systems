package eu.mapidev.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public static String convertSetToString(Set<Integer> set) {
	if (set != null) {
	    StringBuilder stringBuilder = new StringBuilder();
	    int i = 0;
	    for (int value : set) {
		stringBuilder.append(value);
		stringBuilder.append((i++ != (set.size() - 1)) ? "," : "");
	    }
	    return stringBuilder.toString();
	}
	return null;
    }

    public static int[] convertStringToArray(String string) {
	if (string != null) {
	    String[] strings = string.split(",");
	    int[] intArray = new int[strings.length];
	    int i = 0;
	    for (String s : strings) {
		intArray[i] = (Integer.parseInt(s));
		i++;
	    }
	    return intArray;
	}
	return null;
    }

    public static String convertArrayToString(int[] array) {
	if (array != null) {
	    StringBuilder stringBuilder = new StringBuilder();
	    int i = 0;
	    for (int value : array) {
		stringBuilder.append(value);
		stringBuilder.append((i++ != (array.length - 1)) ? "," : "");
	    }
	    return stringBuilder.toString();
	}
	return null;
    }
}
