package lesson08_files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LongestLineFinder {

    public static void findLongestLine() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== ЗАДАНИЕ 2: Поиск самой длинной строки ===\n");

        String filePath;

        // Предлагаем использовать тестовый файл
        System.out.println("Тестовый файл: " + TestFilesCreator.getTestFilePath("test.txt"));
        System.out.print("Использовать тестовый файл? (да/нет/свои): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("да")) {
            filePath = TestFilesCreator.getTestFilePath("test.txt");
            System.out.println("Используется файл: " + filePath);
        } else if (choice.equalsIgnoreCase("свои")) {
            System.out.print("Введите путь к файлу: ");
            filePath = scanner.nextLine();
        } else {
            System.out.println("Поиск отменен");
            return;
        }

        // Проверяем существование файла
        if (FileUtils.fileExists(filePath)) {
            System.err.println("Файл не найден: " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            String longestLine = "";
            int lineNumber = 1;
            int longestLineNumber = 0;
            int totalLines = 0;

            while ((line = reader.readLine()) != null) {
                totalLines++;
                if (line.length() > longestLine.length()) {
                    longestLine = line;
                    longestLineNumber = lineNumber;
                }
                lineNumber++;
            }

            System.out.println("\n--- РЕЗУЛЬТАТ ---");
            if (totalLines == 0) {
                System.out.println("Файл пуст");
            } else {
                System.out.println("Всего строк в файле: " + totalLines);
                System.out.println("Длина самой длинной строки: " + longestLine.length() + " символов");
                System.out.println("Номер строки: " + longestLineNumber);
                System.out.println("\nСодержимое самой длинной строки:");
                System.out.println(longestLine);
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}