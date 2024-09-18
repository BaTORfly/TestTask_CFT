package ru.batorfly.testTask;

public class StatisticsCollector {
    private int intCount = 0;
    private int floatCount = 0;
    private int stringCount = 0;

    private long minInt = Long.MAX_VALUE;
    private long maxInt = Long.MIN_VALUE;
    private long sumInt = 0;

    private double minFloat = Double.MAX_VALUE;
    private double maxFloat = Double.MIN_VALUE;
    private double sumFloat = 0;

    private int minLengthString = Integer.MAX_VALUE;
    private int maxLengthString = Integer.MIN_VALUE;

    public void updateIntStats(long value) {
        intCount++;
        minInt = Math.min(minInt, value);
        maxInt = Math.max(maxInt, value);
        sumInt += value;
    }

    // Обновление статистики для вещественных чисел
    public void updateFloatStats(double value) {
        floatCount++;
        minFloat = Math.min(minFloat, value);
        maxFloat = Math.max(maxFloat, value);
        sumFloat += value;
    }

    // Обновление статистики для строк
    public void updateStringStats(String value) {
        stringCount++;
        int length = value.length();
        minLengthString = Math.min(minLengthString, length);
        maxLengthString = Math.max(maxLengthString, length);
    }

    // Вывод статистики
    public void printStatistics(boolean shortStats, boolean fullStats) {
        if (shortStats) {
            System.out.println("Short statistics:");
            System.out.println("Integers: " + intCount);
            System.out.println("Floats: " + floatCount);
            System.out.println("Strings: " + stringCount);
        } else if (fullStats) {
            System.out.println("Full statistics:");
            System.out.println("Integers: " + intCount);
            if (intCount > 0) {
                System.out.println("Min: " + minInt + ", Max: " + maxInt + ", Sum: " + sumInt
                        + ", Average: " + (double) sumInt / intCount);
            }
            System.out.println("Floats: " + floatCount);
            if (floatCount > 0) {
                System.out.println("Min: " + minFloat + ", Max: " + maxFloat + ", Sum: " + sumFloat
                        + ", Average: " + sumFloat / floatCount);
            }
            System.out.println("Strings: " + stringCount);
            if (stringCount > 0) {
                System.out.println("Shortest: " + minLengthString + ", Longest: " + maxLengthString);
            }
        }
    }
}

