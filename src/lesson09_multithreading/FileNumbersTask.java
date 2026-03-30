package lesson09_multithreading;

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class FileNumbersTask {

    private final String inputFile;
    private final String primesFile;
    private final String factorialsFile;
    private final int numbersCount;
    private final CountDownLatch fillLatch = new CountDownLatch(1);
    private final CountDownLatch processLatch = new CountDownLatch(2);

    private List<Integer> primes = new ArrayList<>();
    private List<String> factorials = new ArrayList<>();
    private long fillTime;
    private long primesTime;
    private long factorialsTime;

    public FileNumbersTask(String inputFile, String primesFile, String factorialsFile, int numbersCount) {
        this.inputFile = inputFile;
        this.primesFile = primesFile;
        this.factorialsFile = factorialsFile;
        this.numbersCount = numbersCount;
    }

    public void start() {
        System.out.println("\n=== ЗАДАНИЕ 2: Работа с файлом и числами ===\n");
        System.out.println("Входной файл: " + inputFile);
        System.out.println("Количество чисел: " + numbersCount);

        // Поток для заполнения файла
        Thread filler = new Thread(() -> {
            System.out.println("🔵 Поток-заполнитель: начал работу");
            long start = System.currentTimeMillis();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
                Random random = new Random();
                for (int i = 0; i < numbersCount; i++) {
                    int num = random.nextInt(200) + 1; // числа от 1 до 200
                    writer.write(String.valueOf(num));
                    if (i < numbersCount - 1) {
                        writer.newLine();
                    }
                    if ((i + 1) % (numbersCount / 10) == 0) {
                        System.out.printf("   Записано %d%% чисел\n", (i + 1) * 100 / numbersCount);
                    }
                }
                fillTime = System.currentTimeMillis() - start;
                System.out.println("✅ Поток-заполнитель: файл заполнен за " + fillTime + " мс");
                fillLatch.countDown();
            } catch (IOException e) {
                System.err.println("Ошибка при записи файла: " + e.getMessage());
            }
        });

        // Поток для поиска простых чисел
        Thread primeFinder = new Thread(() -> {
            try {
                fillLatch.await();
                System.out.println("🔵 Поток-поиск простых чисел: начал работу");
                long start = System.currentTimeMillis();

                List<Integer> numbers = readNumbersFromFile(inputFile);
                primes = new ArrayList<>();

                for (int num : numbers) {
                    if (isPrime(num)) {
                        primes.add(num);
                    }
                }

                // Записываем результаты
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(primesFile))) {
                    writer.write("Простые числа (всего: " + primes.size() + "):\n");
                    for (int prime : primes) {
                        writer.write(prime + "\n");
                    }
                }

                primesTime = System.currentTimeMillis() - start;
                System.out.println("✅ Поток-поиск простых чисел: найдено " + primes.size() + " чисел за " + primesTime + " мс");
                processLatch.countDown();
            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Поток для вычисления факториалов
        Thread factorialCalculator = new Thread(() -> {
            try {
                fillLatch.await();
                System.out.println("🔵 Поток-вычисление факториалов: начал работу");
                long start = System.currentTimeMillis();

                List<Integer> numbers = readNumbersFromFile(inputFile);
                factorials = new ArrayList<>();

                for (int num : numbers) {
                    factorials.add(num + "! = " + factorial(num));
                }

                // Записываем результаты
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(factorialsFile))) {
                    writer.write("Факториалы чисел:\n");
                    for (String fact : factorials) {
                        writer.write(fact + "\n");
                    }
                }

                factorialsTime = System.currentTimeMillis() - start;
                System.out.println("✅ Поток-вычисление факториалов: вычислено " + factorials.size() + " значений за " + factorialsTime + " мс");
                processLatch.countDown();
            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Запускаем все потоки
        filler.start();
        primeFinder.start();
        factorialCalculator.start();

        try {
            processLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n--- СТАТИСТИКА ---");
        System.out.println("Время заполнения файла: " + fillTime + " мс");
        System.out.println("Время поиска простых чисел: " + primesTime + " мс");
        System.out.println("Время вычисления факториалов: " + factorialsTime + " мс");
        System.out.println("Общее время: " + (fillTime + primesTime + factorialsTime) + " мс");
        System.out.println("\nРезультаты сохранены в файлы:");
        System.out.println("  - Простые числа: " + primesFile);
        System.out.println("  - Факториалы: " + factorialsFile);
    }

    private List<Integer> readNumbersFromFile(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        }
        return numbers;
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private String factorial(int n) {
        if (n < 0) return "не определено";
        if (n == 0 || n == 1) return "1";

        java.math.BigInteger result = java.math.BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(java.math.BigInteger.valueOf(i));
        }
        return result.toString();
    }
}