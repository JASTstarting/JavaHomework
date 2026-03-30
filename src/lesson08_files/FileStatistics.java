package lesson08_files;

import java.io.*;
import java.util.*;

public class FileStatistics {

    public static void showFileStatistics() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== СТАТИСТИКА ФАЙЛА ===\n");

        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        // Проверяем существование файла с подробной информацией
        File file = new File(filePath);
        System.out.println("\n--- ДИАГНОСТИКА ---");
        System.out.println("Введенный путь: " + filePath);
        System.out.println("Абсолютный путь: " + file.getAbsolutePath());
        System.out.println("Файл существует: " + file.exists());

        if (!file.exists()) {
            System.err.println("\n❌ Файл не найден!");
            System.err.println("Проверьте правильность пути.");
            System.err.println("Совет: используйте относительный путь 'test_files/file1.txt' или");
            System.err.println("абсолютный путь, например: " + new File("test_files/file1.txt").getAbsolutePath());
            return;
        }

        if (file.isDirectory()) {
            System.err.println("\n❌ Указанный путь ведет к папке, а не к файлу!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            int totalChars = 0;
            int totalWords = 0;
            int maxLength = 0;
            String longestLine = "";
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                totalChars += line.length();

                // Подсчет слов (разделитель - пробелы)
                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    totalWords += words.length;
                }

                if (line.length() > maxLength) {
                    maxLength = line.length();
                    longestLine = line;
                }
            }

            System.out.println("\n--- СТАТИСТИКА ФАЙЛА ---");
            System.out.println("📄 Имя файла: " + file.getName());
            System.out.println("📁 Полный путь: " + file.getAbsolutePath());
            System.out.println("💾 Размер файла: " + file.length() + " байт");
            System.out.println("📊 Количество строк: " + lineCount);
            System.out.println("🔤 Общее количество символов: " + totalChars);
            System.out.println("📝 Общее количество слов: " + totalWords);
            System.out.printf("📏 Средняя длина строки: %.2f\n", lineCount > 0 ? (double) totalChars / lineCount : 0);
            System.out.println("🏆 Самая длинная строка (" + maxLength + " символов):");
            System.out.println("   " + longestLine);

        } catch (IOException e) {
            System.err.println("❌ Ошибка при чтении файла: " + e.getMessage());
        }
    }
}