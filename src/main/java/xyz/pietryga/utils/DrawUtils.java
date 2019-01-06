package xyz.pietryga.utils;

import java.util.Set;
import java.util.TreeSet;

public class DrawUtils {

    public static Set<Integer> convertStringToSet(String string) {
        if (string != null) {
            String[] strings = string.split(",");
            Set<Integer> intSet = new TreeSet<>();

            for (String s : strings) {
                intSet.add(Integer.parseInt(s));
            }
            return intSet;
        }
        return null;
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
