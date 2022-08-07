package com.armannaltakyan;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final Map<Integer, Integer> DIGITS_COUNT_MAP = new HashMap<>();

    public static final List<Integer> BASE_DIGITS = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    static {
        for (var digit :
                BASE_DIGITS) {
            DIGITS_COUNT_MAP.put(digit, 0);
        }
    }

    public static void main(String[] args) {
        // input number
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your Number");
        int number = scanner.nextInt();
        number++;
        // array from number
        var array = arrayFromNumber(number);
        // cleaning redundant zeros
        removeRedundantZeros(array.length);
        // call main calculation method
        calculation(number, array);
        // print result
        printMap();
    }

    private static void removeRedundantZeros(final int numberLength) {
        var zerosCount = DIGITS_COUNT_MAP.get(0);
        for (int i = 1; i < numberLength; i++) {
            zerosCount -= (int) Math.pow(10, i);
        }
        DIGITS_COUNT_MAP.put(0, zerosCount);
    }

    private static void calculation(final int number, final int[] array) {
        for (int i = 0; i < array.length; i++) {
            int tensDegree = (int) Math.pow(10, i + 1);
            dividerCountCalculation(number, tensDegree);
            remainderCountCalculation(number, tensDegree, array[i]);
        }
    }

    private static void remainderCountCalculation(final int number, final int tensDegree, final int ordinalDigit) {
        int remainder = number % tensDegree;
        for (int j = 0; j < ordinalDigit; j++) {
            var count = DIGITS_COUNT_MAP.get(j);
            var repeated = tensDegree / 10;
            count += repeated;
            remainder -= repeated;
            DIGITS_COUNT_MAP.put(j, count);
        }
        var count = DIGITS_COUNT_MAP.get(ordinalDigit);
        count += remainder;
        DIGITS_COUNT_MAP.put(ordinalDigit, count);
    }

    private static void dividerCountCalculation(final int number, final int tensDegree) {
        int repeated = number / tensDegree;
        for (var digit : BASE_DIGITS) {
            var count = DIGITS_COUNT_MAP.get(digit);
            count += repeated * tensDegree / 10;
            DIGITS_COUNT_MAP.put(digit, count);
        }
    }

    private static void printMap() {
        System.out.println(Collections.singletonList(DIGITS_COUNT_MAP));
    }

    private static int[] arrayFromNumber(int number) {
        int length = String.valueOf(number).length();
        int[] array = new int[length];
        int i = 0;
        while (number != 0) {
            array[i++] = number % 10;
            number /= 10;
        }
        return array;
    }
}
