package com.kyoto.task1;

public class ArcsinExpander {
    public static void main(String[] args) {
        double x = 0.5; // Пример значения x
        double arccosine = arccosTaylorSeries(x, 20); // Вычисление arccos(x) с точностью до 20 членов
        System.out.println("arccos(" + x + ") = " + arccosine);
    }

    // Метод для вычисления arccos(x) с использованием степенного ряда Тейлора
    public static double arccosTaylorSeries(double x, int terms) {
        if (x < -1 || x > 1) {
            throw new IllegalArgumentException("The value of x must be in the range [-1, 1]");
        }

        double result = Math.PI / 2; // Первый член ряда

        for (int n = 0; n < terms; n++) {
            double numerator = factorial(2 * n);
            double denominator = Math.pow(2, 2 * n) * Math.pow(factorial(n), 2);
            result -= (numerator / denominator) * Math.pow(x, 2 * n + 1) / (2 * n + 1);
        }

        return result;
    }

    // Метод для вычисления факториала числа
    public static double factorial(int n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

