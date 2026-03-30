package lesson07_lambda;

import java.util.function.BiFunction;

public class FractionLambdaTasks {

    // Вспомогательный класс для представления дроби
    public static class Fraction {
        private int numerator;
        private int denominator;

        public Fraction(int numerator, int denominator) {
            if (denominator == 0) {
                throw new IllegalArgumentException("Знаменатель не может быть 0");
            }
            this.numerator = numerator;
            this.denominator = denominator;
            simplify();
        }

        private void simplify() {
            int gcd = findGCD(Math.abs(numerator), Math.abs(denominator));
            numerator /= gcd;
            denominator /= gcd;
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
        }

        private int findGCD(int a, int b) {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }

        public int getNumerator() { return numerator; }
        public int getDenominator() { return denominator; }

        @Override
        public String toString() {
            if (denominator == 1) {
                return String.valueOf(numerator);
            }
            return numerator + "/" + denominator;
        }
    }

    // Сумма двух дробей
    public static BiFunction<Fraction, Fraction, Fraction> addFractions = (f1, f2) -> {
        int newNumerator = f1.getNumerator() * f2.getDenominator() +
                f2.getNumerator() * f1.getDenominator();
        int newDenominator = f1.getDenominator() * f2.getDenominator();
        return new Fraction(newNumerator, newDenominator);
    };

    // Разность двух дробей
    public static BiFunction<Fraction, Fraction, Fraction> subtractFractions = (f1, f2) -> {
        int newNumerator = f1.getNumerator() * f2.getDenominator() -
                f2.getNumerator() * f1.getDenominator();
        int newDenominator = f1.getDenominator() * f2.getDenominator();
        return new Fraction(newNumerator, newDenominator);
    };

    // Произведение двух дробей
    public static BiFunction<Fraction, Fraction, Fraction> multiplyFractions = (f1, f2) -> {
        int newNumerator = f1.getNumerator() * f2.getNumerator();
        int newDenominator = f1.getDenominator() * f2.getDenominator();
        return new Fraction(newNumerator, newDenominator);
    };

    // Деление двух дробей
    public static BiFunction<Fraction, Fraction, Fraction> divideFractions = (f1, f2) -> {
        if (f2.getNumerator() == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        int newNumerator = f1.getNumerator() * f2.getDenominator();
        int newDenominator = f1.getDenominator() * f2.getNumerator();
        return new Fraction(newNumerator, newDenominator);
    };

    public static void runTasks() {
        System.out.println("\n\n=== ЗАДАНИЕ 2: Лямбда-выражения для работы с дробями ===\n");

        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = new Fraction(2, 5);
        Fraction f4 = new Fraction(3, 4);

        System.out.println("Исходные дроби:");
        System.out.println("f1 = " + f1);
        System.out.println("f2 = " + f2);
        System.out.println("f3 = " + f3);
        System.out.println("f4 = " + f4);

        // Сложение
        System.out.println("\n--- Сложение дробей ---");
        System.out.println(f1 + " + " + f2 + " = " + addFractions.apply(f1, f2));
        System.out.println(f3 + " + " + f4 + " = " + addFractions.apply(f3, f4));
        System.out.println(f1 + " + " + f3 + " = " + addFractions.apply(f1, f3));

        // Вычитание
        System.out.println("\n--- Вычитание дробей ---");
        System.out.println(f1 + " - " + f2 + " = " + subtractFractions.apply(f1, f2));
        System.out.println(f4 + " - " + f1 + " = " + subtractFractions.apply(f4, f1));
        System.out.println(f3 + " - " + f4 + " = " + subtractFractions.apply(f3, f4));

        // Умножение
        System.out.println("\n--- Умножение дробей ---");
        System.out.println(f1 + " * " + f2 + " = " + multiplyFractions.apply(f1, f2));
        System.out.println(f3 + " * " + f4 + " = " + multiplyFractions.apply(f3, f4));
        System.out.println(f1 + " * " + f3 + " = " + multiplyFractions.apply(f1, f3));

        // Деление
        System.out.println("\n--- Деление дробей ---");
        System.out.println(f1 + " / " + f2 + " = " + divideFractions.apply(f1, f2));
        System.out.println(f3 + " / " + f4 + " = " + divideFractions.apply(f3, f4));
        System.out.println(f1 + " / " + f3 + " = " + divideFractions.apply(f1, f3));

        System.out.println("\n--- Дополнительные примеры ---");
        Fraction f5 = new Fraction(2, 3);
        Fraction f6 = new Fraction(4, 5);
        System.out.println(f5 + " + " + f6 + " = " + addFractions.apply(f5, f6));
        System.out.println(f5 + " * " + f6 + " = " + multiplyFractions.apply(f5, f6));

        // Цепочка операций
        Fraction sum1 = addFractions.apply(f1, f2);
        Fraction result = multiplyFractions.apply(sum1, f3);
        System.out.println("\n(" + f1 + " + " + f2 + ") * " + f3 + " = " + result);
    }
}