package lesson07_lambda;

import java.util.function.IntPredicate;

public class ArrayFilterLambdaTasks {

    // Метод, который принимает массив и лямбда-условие, возвращает сумму элементов, удовлетворяющих условию
    public static int sumIf(int[] array, IntPredicate condition) {
        int sum = 0;
        for (int num : array) {
            if (condition.test(num)) {
                sum += num;
            }
        }
        return sum;
    }

    public static void runTasks() {
        System.out.println("\n\n=== ЗАДАНИЕ 4: Лямбда-выражения как параметры метода ===\n");

        int[] numbers = { -10, -5, 0, 3, 7, 12, -8, 15, 20, -3, 25, 30, -2, 4, 6 };

        System.out.println("Исходный массив: ");
        printArray(numbers);

        System.out.println("\n--- Проверка на равенство конкретному числу ---");
        int targetNumber = 7;
        int sumEquals = sumIf(numbers, n -> n == targetNumber);
        System.out.println("Сумма элементов, равных " + targetNumber + ": " + sumEquals);

        // Проверка на равенство 0
        int sumZero = sumIf(numbers, n -> n == 0);
        System.out.println("Сумма элементов, равных 0: " + sumZero);

        // Проверка на равенство -5
        int sumNegativeFive = sumIf(numbers, n -> n == -5);
        System.out.println("Сумма элементов, равных -5: " + sumNegativeFive);

        // Число не находится в диапазоне от А до В
        System.out.println("\n--- Число не находится в диапазоне от А до В ---");

        final int A1 = 0;
        final int B1 = 10;
        int sumOutsideRange1 = sumIf(numbers, n -> n < A1 || n > B1);
        System.out.println("Сумма элементов вне диапазона [" + A1 + ", " + B1 + "]: " + sumOutsideRange1);

        final int A2 = -5;
        final int B2 = 5;
        int sumOutsideRange2 = sumIf(numbers, n -> n < A2 || n > B2);
        System.out.println("Сумма элементов вне диапазона [" + A2 + ", " + B2 + "]: " + sumOutsideRange2);

        final int A3 = -10;
        final int B3 = 10;
        int sumOutsideRange3 = sumIf(numbers, n -> n < A3 || n > B3);
        System.out.println("Сумма элементов вне диапазона [" + A3 + ", " + B3 + "]: " + sumOutsideRange3);

        // Проверка на положительность числа
        System.out.println("\n--- Проверка на положительность числа ---");
        int sumPositive = sumIf(numbers, n -> n > 0);
        System.out.println("Сумма положительных чисел: " + sumPositive);

        // Проверка на отрицательность числа
        System.out.println("\n--- Проверка на отрицательность числа ---");
        int sumNegative = sumIf(numbers, n -> n < 0);
        System.out.println("Сумма отрицательных чисел: " + sumNegative);

        // Дополнительные примеры
        System.out.println("\n--- Дополнительные примеры с разными условиями ---");

        // Четные числа
        int sumEven = sumIf(numbers, n -> n % 2 == 0);
        System.out.println("Сумма четных чисел: " + sumEven);

        // Нечетные числа
        int sumOdd = sumIf(numbers, n -> n % 2 != 0);
        System.out.println("Сумма нечетных чисел: " + sumOdd);

        // Числа, кратные 3
        int sumMultipleOf3 = sumIf(numbers, n -> n % 3 == 0);
        System.out.println("Сумма чисел, кратных 3: " + sumMultipleOf3);

        // Числа больше 10
        int sumGreaterThan10 = sumIf(numbers, n -> n > 10);
        System.out.println("Сумма чисел больше 10: " + sumGreaterThan10);

        // Числа меньше 0
        int sumLessThan0 = sumIf(numbers, n -> n < 0);
        System.out.println("Сумма чисел меньше 0: " + sumLessThan0);

        // Комбинированные условия
        System.out.println("\n--- Комбинированные условия ---");

        // Положительные четные числа
        int sumPositiveEven = sumIf(numbers, n -> n > 0 && n % 2 == 0);
        System.out.println("Сумма положительных четных чисел: " + sumPositiveEven);

        // Отрицательные нечетные числа
        int sumNegativeOdd = sumIf(numbers, n -> n < 0 && n % 2 != 0);
        System.out.println("Сумма отрицательных нечетных чисел: " + sumNegativeOdd);

        // Числа в диапазоне [5, 20]
        final int LOW = 5;
        final int HIGH = 20;
        int sumInRange = sumIf(numbers, n -> n >= LOW && n <= HIGH);
        System.out.println("Сумма чисел в диапазоне [" + LOW + ", " + HIGH + "]: " + sumInRange);

        // Числа в диапазоне [-5, 5]
        final int LOW2 = -5;
        final int HIGH2 = 5;
        int sumInRange2 = sumIf(numbers, n -> n >= LOW2 && n <= HIGH2);
        System.out.println("Сумма чисел в диапазоне [" + LOW2 + ", " + HIGH2 + "]: " + sumInRange2);

        // С разными условиями в цикле
        System.out.println("\n--- Демонстрация работы нескольких условий ---");
        IntPredicate[] conditions = {
                n -> n > 0,                    // положительные
                n -> n < 0,                    // отрицательные
                n -> n == 0,                   // нули
                n -> n % 2 == 0,               // четные
                n -> n % 2 != 0,               // нечетные
                n -> n > 10 && n < 20,         // от 11 до 19
                n -> Math.abs(n) > 10          // по модулю больше 10
        };

        String[] conditionNames = {
                "Положительные",
                "Отрицательные",
                "Нули",
                "Четные",
                "Нечетные",
                "От 11 до 19",
                "|n| > 10"
        };

        for (int i = 0; i < conditions.length; i++) {
            int sum = sumIf(numbers, conditions[i]);
            System.out.printf("%-15s: сумма = %d\n", conditionNames[i], sum);
        }

        // Пример с использованием методов класса Math
        System.out.println("\n--- Использование Math.abs в лямбда-выражениях ---");

        // Сумма чисел, абсолютное значение которых больше 5
        final int THRESHOLD = 5;
        int sumAbsGreaterThan5 = sumIf(numbers, n -> Math.abs(n) > THRESHOLD);
        System.out.println("Сумма чисел с |n| > " + THRESHOLD + ": " + sumAbsGreaterThan5);

        // Сумма квадратов чисел
        int sumSquares = sumIf(numbers, _ -> true); // все числа
        System.out.println("Сумма всех чисел: " + sumSquares);

        // Сумма чисел, которые являются степенью двойки
        int sumPowersOfTwo = sumIf(numbers, n -> (n > 0) && ((n & (n - 1)) == 0));
        System.out.println("Сумма чисел, являющихся степенью двойки: " + sumPowersOfTwo);
    }

    private static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}