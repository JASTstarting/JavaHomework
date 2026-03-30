package lesson08_files;

import java.io.*;
import java.util.*;

public class FileMerger {

    public static void mergeFiles() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== ОБЪЕДИНЕНИЕ ФАЙЛОВ ===\n");

        System.out.print("Введите путь к первому файлу: ");
        String file1 = scanner.nextLine();

        System.out.print("Введите путь ко второму файлу: ");
        String file2 = scanner.nextLine();

        System.out.print("Введите путь к файлу результата: ");
        String resultFile = scanner.nextLine();

        if (FileUtils.fileExists(file1)) {
            System.err.println("Файл не найден: " + file1);
            return;
        }
        if (FileUtils.fileExists(file2)) {
            System.err.println("Файл не найден: " + file2);
            return;
        }

        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {

            String line1, line2;
            int lineNumber = 1;

            writer.write("=== ОБЪЕДИНЕНИЕ ФАЙЛОВ ===");
            writer.newLine();
            writer.write("Файл 1: " + file1);
            writer.newLine();
            writer.write("Файл 2: " + file2);
            writer.newLine();
            writer.write("Дата: " + new Date());
            writer.newLine();
            writer.write("=".repeat(50));
            writer.newLine();
            writer.newLine();

            while (true) {
                line1 = reader1.readLine();
                line2 = reader2.readLine();

                if (line1 == null && line2 == null) {
                    break;
                }

                writer.write("Строка " + lineNumber + ":");
                writer.newLine();

                if (line1 != null) {
                    writer.write("  📄 Файл 1: " + line1);
                    writer.newLine();
                }

                if (line2 != null) {
                    writer.write("  📄 Файл 2: " + line2);
                    writer.newLine();
                }

                writer.newLine();
                lineNumber++;
            }

            System.out.println("\n✓ Файлы успешно объединены в: " + resultFile);
            System.out.println("  Всего строк обработано: " + (lineNumber - 1));

        } catch (IOException e) {
            System.err.println("Ошибка при объединении файлов: " + e.getMessage());
        }
    }
}