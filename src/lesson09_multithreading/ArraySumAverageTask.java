package lesson09_multithreading;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ArraySumAverageTask {

    private final int[] array;
    private int sum;
    private double average;
    private final int arraySize;
    private final CountDownLatch fillLatch = new CountDownLatch(1);
    private final CountDownLatch resultLatch = new CountDownLatch(2);

    public ArraySumAverageTask(int arraySize) {
        this.arraySize = arraySize;
        this.array = new int[arraySize];
    }

    public void start() {
        System.out.println("\n=== ЗАДАНИЕ 1: Сумма и среднее массива ===\n");
        System.out.println("Размер массива: " + arraySize);

        // Поток для заполнения массива
        Thread filler = new Thread(() -> {
            System.out.println("🔵 Поток-заполнитель: начал работу");
            Random random = new Random();
            for (int i = 0; i < arraySize; i++) {
                array[i] = random.nextInt(100) + 1; // числа от 1 до 100
                if ((i + 1) % (arraySize / 10) == 0) {
                    System.out.printf("   Заполнено %d%%\n", (i + 1) * 100 / arraySize);
                }
            }
            System.out.println("✅ Поток-заполнитель: массив заполнен");
            fillLatch.countDown(); // Сигнализируем, что массив готов
        });

        // Поток для вычисления суммы
        Thread sumCalculator = new Thread(() -> {
            try {
                fillLatch.await(); // Ждем заполнения массива
                System.out.println("🔵 Поток-вычислитель суммы: начал работу");
                int localSum = 0;
                for (int num : array) {
                    localSum += num;
                }
                sum = localSum;
                System.out.println("✅ Поток-вычислитель суммы: сумма = " + sum);
                resultLatch.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Поток для вычисления среднего
        Thread averageCalculator = new Thread(() -> {
            try {
                fillLatch.await(); // Ждем заполнения массива
                System.out.println("🔵 Поток-вычислитель среднего: начал работу");
                int localSum = 0;
                for (int num : array) {
                    localSum += num;
                }
                average = (double) localSum / arraySize;
                System.out.printf("✅ Поток-вычислитель среднего: среднее = %.2f\n", average);
                resultLatch.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Запускаем все потоки
        filler.start();
        sumCalculator.start();
        averageCalculator.start();

        try {
            // Ждем завершения вычислений
            resultLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n--- РЕЗУЛЬТАТЫ ---");
        System.out.println("Массив: " + Arrays.toString(array));
        System.out.println("Сумма элементов: " + sum);
        System.out.printf("Среднее арифметическое: %.2f\n", average);
    }
}