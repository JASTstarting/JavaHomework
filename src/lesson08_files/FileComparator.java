package lesson08_files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileComparator {

    public static void compareFiles() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== ЗАДАНИЕ 1: Сравнение двух файлов ===\n");

        String filePath1;
        String filePath2;

        // Предлагаем использовать тестовые файлы
        System.out.println("Тестовые файлы: ");
        System.out.println("  1. " + TestFilesCreator.getTestFilePath("file1.txt"));
        System.out.println("  2. " + TestFilesCreator.getTestFilePath("file2.txt"));
        System.out.print("\nИспользовать тестовые файлы? (да/нет/свои): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("да")) {
            filePath1 = TestFilesCreator.getTestFilePath("file1.txt");
            filePath2 = TestFilesCreator.getTestFilePath("file2.txt");
            System.out.println("Используются файлы: " + filePath1 + " и " + filePath2);
        } else if (choice.equalsIgnoreCase("свои")) {
            System.out.print("Введите путь к первому файлу: ");
            filePath1 = scanner.nextLine();
            System.out.print("Введите путь ко второму файлу: ");
            filePath2 = scanner.nextLine();
        } else {
            System.out.println("Сравнение отменено");
            return;
        }

        // Проверяем существование файлов
        if (FileUtils.fileExists(filePath1)) {
            System.err.println("Файл не найден: " + filePath1);
            return;
        }
        if (FileUtils.fileExists(filePath2)) {
            System.err.println("Файл не найден: " + filePath2);
            return;
        }

        try (BufferedReader reader1 = new BufferedReader(new FileReader(filePath1));
             BufferedReader reader2 = new BufferedReader(new FileReader(filePath2))) {

            String line1;
            String line2;
            int lineNumber = 1;
            boolean filesAreEqual = true;
            int differencesCount = 0;

            System.out.println("\n--- Сравнение файлов ---\n");

            while (true) {
                line1 = reader1.readLine();
                line2 = reader2.readLine();

                if (line1 == null && line2 == null) {
                    break;
                }

                if (line1 == null) {
                    System.out.println("! Файл 1 закончился на строке " + lineNumber);
                    System.out.println("  Файл 2 строка " + lineNumber + ": " + line2);
                    filesAreEqual = false;
                    differencesCount++;
                    break;
                }

                if (line2 == null) {
                    System.out.println("! Файл 2 закончился на строке " + lineNumber);
                    System.out.println("  Файл 1 строка " + lineNumber + ": " + line1);
                    filesAreEqual = false;
                    differencesCount++;
                    break;
                }

                if (!line1.equals(line2)) {
                    System.out.println("! Несовпадение на строке " + lineNumber + ":");
                    System.out.println("  Файл 1: " + line1);
                    System.out.println("  Файл 2: " + line2);
                    filesAreEqual = false;
                    differencesCount++;
                }

                lineNumber++;
            }

            System.out.println("\n--- РЕЗУЛЬТАТ ---");
            if (filesAreEqual) {
                System.out.println("✓ Файлы полностью идентичны!");
                System.out.println("  Всего строк: " + (lineNumber - 1));
            } else {
                System.out.println("✗ Файлы различаются");
                System.out.println("  Количество различий: " + differencesCount);
                System.out.println("  Всего строк в первом файле: " + (lineNumber - 1));
                System.out.println("  Всего строк во втором файле: " + (lineNumber - 1));
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файлов: " + e.getMessage());
        }
    }
}