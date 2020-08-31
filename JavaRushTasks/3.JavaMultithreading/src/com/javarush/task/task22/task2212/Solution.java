package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.isEmpty()) {
            return false;
        }
        int numsQuantity = numsQuantity(telNumber);

        if (telNumber.startsWith("+") && numsQuantity != 12) {
            return false;
        } else if ((startsWithDigit(telNumber) || telNumber.startsWith("(")) && numsQuantity != 10) {
            return false;
        } else if (telNumber.contains("[a-z]")) {
            return false;
        } else if (!endsWithDigit(telNumber)) {
            return false;
        } else if (!isMinusQuantityAndOrderOk(telNumber)) {
            return false;
        } else if (telNumber.contains("(") || telNumber.contains(")")) {
            if (!hasTwoBrackets(telNumber)) {
                return false;
            } else if (!rightBracketsOrder(telNumber)) {
                return false;
            } else if (!bracketsLeftThanMinus(telNumber)) {
                return false;
            }
        }

        return true;
    }


    public static int numsQuantity(String number) {
        int quantity = 0;
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                quantity++;
            }
        }
        return quantity;
    }

    public static boolean hasTwoBrackets(String number) {
        char[] chars = number.toCharArray();
        int rightBracketQuantity = 0;
        int leftBracketQuantity = 0;
        for (char ch : chars) {
            if (ch == '(') {
                leftBracketQuantity++;
            } else if (ch == ')') {
                rightBracketQuantity++;
            }
        }
        if (rightBracketQuantity == 1 && leftBracketQuantity == 1) {
            return true;
        } else return false;
    }

    public static boolean rightBracketsOrder(String number) {
        int numberOfFirst = number.indexOf('(');
        int numberOfSecond = number.indexOf(')');
        int first = numberOfFirst + 1;
        int second = numberOfFirst + 2;
        int third = numberOfFirst + 3;
        char f = number.charAt(first);
        char s = number.charAt(second);
        char t = number.charAt(third);
        if ((numberOfSecond - numberOfFirst) != 4) {
            return false;
        } else if (!Character.isDigit(f) || !Character.isDigit(s) ||
                !Character.isDigit(t)) {
            return false;
        } else return true;
    }

    public static boolean bracketsLeftThanMinus(String number) {
        if (number.contains("-")) {
            if (number.indexOf(')') < number.indexOf('-')) {
                return true;
            } else return false;
        } else return true;
    }

    public static boolean isMinusQuantityAndOrderOk(String number) {
        Pattern p = Pattern.compile("-");
        Matcher m = p.matcher(number);
        int matches = 0;
        while (m.find()) {
            matches++;
        }
        if (matches == 0) {
            return true;
        }
        if (number.startsWith("-")) {
            return false;
        }
        int firstMinusNumber = number.indexOf("-");
        int numberAfterFirstMinus = firstMinusNumber + 1;
        char first = number.charAt(firstMinusNumber);
        char second = number.charAt(numberAfterFirstMinus);
        if (matches > 2) {
            return false;
        } else if ((matches == 2) && (first == second)) {
            return false;
        } else return true;
    }

    public static boolean endsWithDigit(String tel) {
        char endLetter = tel.charAt(tel.length() - 1);
        if (Character.isDigit(endLetter)) {
            return true;
        } else return false;
    }

    public static boolean startsWithDigit(String tel) {
        char firstLetter = tel.charAt(0);
        if (Character.isDigit(firstLetter)) {
            return true;
        } else return false;

    }


    public static void main(String[] args) {
        String[] s = {
                "",
                "+380501234567",
                "+38(050)1234567",
                "+38050123-45-67",
                "050123-4567",
                "+38)050(1234567",
                "+38(050)1-23-45-6-7",
                "050ххх4567",
                "0501236",
                "(0)501234567",
                "+38(050)1-23-45--6-7",
                "+3-8(050)1-23-45--6-7",
                "+38050123-4567-"
        };

        for (String t : s) System.out.printf("%25s :   %5s %n", t, checkTelNumber(t));

    }
}
