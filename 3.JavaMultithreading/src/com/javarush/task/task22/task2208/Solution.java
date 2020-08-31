package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        if (params == null || params.isEmpty()) return result.toString();
        for (Map.Entry<String, String> pair : params.entrySet()) {
            if (pair.getValue() != null && pair.getKey() != null)
                result.append(String.format("%s = '%s' and ", pair.getKey(), pair.getValue()));
        }

        if (result.length() > 0) {
            result.replace(result.length() - 5, result.length(), "");
        }
        return result.toString();
    }
}
