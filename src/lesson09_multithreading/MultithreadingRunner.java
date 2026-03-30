package lesson09_multithreading;

import java.util.Scanner;

public class MultithreadingRunner {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  МНОГОПОТОЧНОСТЬ                           ║");
        System.out.println("║              Демонстрация всех заданий                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        while (true) {
            showMenu();
            int choice = getIntInput();
            System.out.println();

            switch (choice) {
                case 1:
                    task1();
                    break;
                case 2:
                    task2();
                    break;
                case 3:
                    task3();
                    break;
                case 4:
                    task4();
                    break;
                case 0:
                    System.out.println("\n👋 До свидания!");
                    return;
                default:
                    System.out.println("❌ Неверный выбор. Попробуйте снова.");
            }

            System.out.println("\n" + "=".repeat(70) + "\n");
        }
    }

    private static void showMenu() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                    МЕНЮ ЗАДАНИЙ (МНОГОПОТОЧНОСТЬ)              │");
        System.out.println("├─────────────────────────────────────────────────────────────────┤");
        System.out.println("│ 1  - Задание 1: Массив со случайными числами                   │");
        System.out.println("│      (сумма и среднее в разных потоках)                        │");
        System.out.println("│                                                                 │");
        System.out.println("│ 2  - Задание 2: Работа с файлом и числами                      │");
        System.out.println("│      (простые числа и факториалы в разных потоках)             │");
        System.out.println("│                                                                 │");
        System.out.println("│ 3  - Задание 3: Копирование директории                         │");
        System.out.println("│      (однопоточное копирование со статистикой)                 │");
        System.out.println("│                                                                 │");
        System.out.println("│ 4  - Задание 4: Поиск и фильтрация слов в файлах               │");
        System.out.println("│      (два потока: поиск и фильтрация)                          │");
        System.out.println("├─────────────────────────────────────────────────────────────────┤");
        System.out.println("│ 0  - Выход                                                      │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
    }

    private static void task1() {
        System.out.print("Введите размер массива (рекомендуется 10-100): ");
        int size = scanner.nextInt();
        scanner.nextLine();

        ArraySumAverageTask task = new ArraySumAverageTask(size);
        task.start();
    }

    private static void task2() {
        System.out.print("Введите путь к файлу для записи чисел: ");
        String inputFile = scanner.nextLine();

        System.out.print("Введите путь для сохранения простых чисел: ");
        String primesFile = scanner.nextLine();

        System.out.print("Введите путь для сохранения факториалов: ");
        String factorialsFile = scanner.nextLine();

        System.out.print("Введите количество чисел (рекомендуется 10-50): ");
        int count = scanner.nextInt();
        scanner.nextLine();

        FileNumbersTask task = new FileNumbersTask(inputFile, primesFile, factorialsFile, count);
        task.start();
    }

    private static void task3() {
        System.out.print("Введите путь к исходной директории: ");
        String sourceDir = scanner.nextLine();

        System.out.print("Введите путь к целевой директории: ");
        String targetDir = scanner.nextLine();

        DirectoryCopyTask task = new DirectoryCopyTask(sourceDir, targetDir);
        task.start();
    }

    private static void task4() {
        System.out.print("Введите путь к директории для поиска: ");
        String directory = scanner.nextLine();

        System.out.print("Введите слово для поиска: ");
        String searchWord = scanner.nextLine();

        System.out.print("Введите путь к файлу с запрещенными словами: ");
        String forbiddenFile = scanner.nextLine();

        System.out.print("Введите путь для сохранения результатов поиска: ");
        String resultFile = scanner.nextLine();

        System.out.print("Введите путь для сохранения отфильтрованного файла: ");
        String filteredFile = scanner.nextLine();

        FileSearchAndFilterTask task = new FileSearchAndFilterTask(
                directory, searchWord, forbiddenFile, resultFile, filteredFile);
        task.start();
    }

    private static int getIntInput() {
        System.out.print("Выберите задание: ");
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Введите число: ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }
}