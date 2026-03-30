package lesson07_lambda;

import java.util.function.BinaryOperator;

public class MinMaxLambdaTasks {

    // Максимум из двух чисел
    public static BinaryOperator<Integer> maxOfTwo = (a, b) -> a > b ? a : b;

    // Создаем метод для четырех параметров
    public static int maxOfFourNumbers(int a, int b, int c, int d) {
        return maxOfTwo.apply(maxOfTwo.apply(a, b), maxOfTwo.apply(c, d));
    }

    // Второй вариант с использованием дополнительных параметров
    public interface QuadFunction<T> {
        T apply(T a, T b, T c, T d);
    }

    public static QuadFunction<Integer> maxOfFourLambda = (a, b, c, d) -> {
        int max1 = Math.max(a, b);
        int max2 = Math.max(c, d);
        return Math.max(max1, max2);
    };

    public static QuadFunction<Integer> minOfFourLambda = (a, b, c, d) -> {
        int min1 = Math.min(a, b);
        int min2 = Math.min(c, d);
        return Math.min(min1, min2);
    };

    // Альтернативный вариант с использованием BinaryOperator
    public static QuadFunction<Integer> maxOfFourLambdaV2 = (a, b, c, d) -> {
        BinaryOperator<Integer> max = (x, y) -> x > y ? x : y;
        return max.apply(max.apply(a, b), max.apply(c, d));
    };

    public static QuadFunction<Integer> minOfFourLambdaV2 = (a, b, c, d) -> {
        BinaryOperator<Integer> min = (x, y) -> x < y ? x : y;
        return min.apply(min.apply(a, b), min.apply(c, d));
    };

    public static void runTasks() {
        System.out.println("\n\n=== ЗАДАНИЕ 3: Поиск максимума и минимума из четырех чисел ===\n");


        int[][] testCases = {
                {10, 5, 8, 3},
                {100, 200, 150, 175},
                {-5, -2, -10, -1},
                {0, 0, 0, 0},
                {7, 7, 7, 7},
                {1000, 500, 2000, 750}
        };

        System.out.println("--- Поиск максимума из четырех чисел (QuadFunction) ---");
        for (int[] nums : testCases) {
            int max = maxOfFourLambda.apply(nums[0], nums[1], nums[2], nums[3]);
            System.out.printf("Максимум из %d, %d, %d, %d = %d\n",
                    nums[0], nums[1], nums[2], nums[3], max);
        }

        System.out.println("\n--- Поиск максимума из четырех чисел (BinaryOperator) ---");
        for (int[] nums : testCases) {
            int max = maxOfFourLambdaV2.apply(nums[0], nums[1], nums[2], nums[3]);
            System.out.printf("Максимум из %d, %d, %d, %d = %d\n",
                    nums[0], nums[1], nums[2], nums[3], max);
        }

        System.out.println("\n--- Поиск минимума из четырех чисел (QuadFunction) ---");
        for (int[] nums : testCases) {
            int min = minOfFourLambda.apply(nums[0], nums[1], nums[2], nums[3]);
            System.out.printf("Минимум из %d, %d, %d, %d = %d\n",
                    nums[0], nums[1], nums[2], nums[3], min);
        }

        System.out.println("\n--- Поиск минимума из четырех чисел (BinaryOperator) ---");
        for (int[] nums : testCases) {
            int min = minOfFourLambdaV2.apply(nums[0], nums[1], nums[2], nums[3]);
            System.out.printf("Минимум из %d, %d, %d, %d = %d\n",
                    nums[0], nums[1], nums[2], nums[3], min);
        }

        // Дополнительные примеры с другими типами
        System.out.println("\n--- Поиск максимума и минимума для double ---");
        QuadFunction<Double> maxDouble = (a, b, c, d) -> {
            double max1 = Math.max(a, b);
            double max2 = Math.max(c, d);
            return Math.max(max1, max2);
        };

        QuadFunction<Double> minDouble = (a, b, c, d) -> {
            double min1 = Math.min(a, b);
            double min2 = Math.min(c, d);
            return Math.min(min1, min2);
        };

        double d1 = 3.14, d2 = 2.71, d3 = 1.41, d4 = 1.73;
        System.out.printf("Максимум из %.2f, %.2f, %.2f, %.2f = %.2f\n",
                d1, d2, d3, d4, maxDouble.apply(d1, d2, d3, d4));
        System.out.printf("Минимум из %.2f, %.2f, %.2f, %.2f = %.2f\n",
                d1, d2, d3, d4, minDouble.apply(d1, d2, d3, d4));

        // Пример с использованием встроенных функций
        System.out.println("\n--- Использование встроенных функций ---");
        int min = 23;
        int max = 78;
        System.out.printf("Максимум: %d, Минимум: %d\n", max, min);

        // С разными типами
        System.out.println("\n--- Поиск максимума для разных типов ---");
        QuadFunction<String> maxString = (max1, _, _, _) -> max1;

        String result = maxString.apply("яблоко", "апельсин", "банан", "вишня");
        System.out.println("Максимальная строка (по алфавиту): " + result);
    }
}