package lesson08_files;

import java.io.*;
import java.util.*;

public class FileSearch {

    public static void searchInFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== ПОИСК В ФАЙЛЕ ===\n");

        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        if (FileUtils.fileExists(filePath)) {
            System.err.println("Файл не найден: " + filePath);
            return;
        }

        System.out.print("Введите текст для поиска: ");
        String searchText = scanner.nextLine();

        boolean caseSensitive = false;
        System.out.print("Учитывать регистр? (да/нет): ");
        String caseChoice = scanner.nextLine();
        if (caseChoice.equalsIgnoreCase("да")) {
            caseSensitive = true;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            int lineNumber = 1;
            List<Integer> foundLines = new ArrayList<>();
            List<String> foundContent = new ArrayList<>();

            String searchFor = caseSensitive ? searchText : searchText.toLowerCase();

            while ((line = reader.readLine()) != null) {
                String compareLine = caseSensitive ? line : line.toLowerCase();
                if (compareLine.contains(searchFor)) {
                    foundLines.add(lineNumber);
                    foundContent.add(line);
                }
                lineNumber++;
            }

            System.out.println("\n--- РЕЗУЛЬТАТЫ ПОИСКА ---");
            if (foundLines.isEmpty()) {
                System.out.println("🔍 Текст \"" + searchText + "\" не найден в файле");
            } else {
                System.out.println("🔍 Найдено " + foundLines.size() + " совпадений:");
                System.out.println("=".repeat(60));
                for (int i = 0; i < foundLines.size(); i++) {
                    System.out.printf("Строка %d: %s\n", foundLines.get(i), foundContent.get(i));
                }
                System.out.println("=".repeat(60));
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}