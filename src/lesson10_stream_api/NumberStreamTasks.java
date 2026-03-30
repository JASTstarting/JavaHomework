package lesson10_stream_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberStreamTasks {

    public static void runTasks() {
        System.out.println("\n=== ЗАДАНИЕ 1: Анализ чисел через Stream API ===\n");

        List<Integer> numbers = generateRandomNumbers();

        System.out.println("📊 Сгенерировано чисел: " + numbers.size());
        System.out.println("Числа: " + numbers);
        System.out.println("\n" + "=".repeat(60));

        long positiveCount = numbers.stream()
                .filter(n -> n > 0)
                .count();
        System.out.println("\n✅ Количество положительных чисел: " + positiveCount);

        long negativeCount = numbers.stream()
                .filter(n -> n < 0)
                .count();
        System.out.println("✅ Количество отрицательных чисел: " + negativeCount);

        long twoDigitCount = numbers.stream()
                .filter(n -> (n >= 10 && n <= 99) || (n <= -10 && n >= -99))
                .count();
        System.out.println("✅ Количество двузначных чисел: " + twoDigitCount);

        List<Integer> palindromeNumbers = numbers.stream()
                .filter(NumberStreamTasks::isPalindrome)
                .toList();

        System.out.println("✅ Количество зеркальных чисел: " + palindromeNumbers.size());
        if (!palindromeNumbers.isEmpty()) {
            System.out.println("   Зеркальные числа: " + palindromeNumbers);
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("📈 ДОПОЛНИТЕЛЬНАЯ СТАТИСТИКА:");

        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        int max = numbers.stream().mapToInt(Integer::intValue).max().orElse(0);
        int min = numbers.stream().mapToInt(Integer::intValue).min().orElse(0);

        System.out.println("   Сумма всех чисел: " + sum);
        System.out.printf("   Среднее значение: %.2f\n", average);
        System.out.println("   Максимальное число: " + max);
        System.out.println("   Минимальное число: " + min);
    }

    private static List<Integer> generateRandomNumbers() {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            numbers.add(random.nextInt(19999) - 9999);
        }
        return numbers;
    }

    private static boolean isPalindrome(int number) {
        String str = String.valueOf(Math.abs(number));
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
}