package lesson08_files;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileTasksRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  РАБОТА С ФАЙЛАМИ                          ║");
        System.out.println("║              Демонстрация всех заданий                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        // Автоматическое создание тестовых файлов
        System.out.println("\n📁 Проверка наличия тестовых файлов...");
        if (!TestFilesCreator.areTestFilesExist()) {
            System.out.println("Тестовые файлы не найдены. Создаем автоматически...");
            TestFilesCreator.createAllTestFiles();
        } else {
            System.out.println("✓ Тестовые файлы уже существуют в папке: " + TestFilesCreator.getTestFilesPath());
            System.out.print("Хотите пересоздать тестовые файлы? (да/нет): ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("да")) {
                TestFilesCreator.createAllTestFiles();
            }
        }

        while (true) {
            showMenu();
            int choice = getIntInput(scanner);
            System.out.println();

            switch (choice) {
                case 1:
                    FileComparator.compareFiles();
                    break;
                case 2:
                    LongestLineFinder.findLongestLine();
                    break;
                case 3:
                    ArraysFromFile.loadArraysFromFile();
                    break;
                case 4:
                    ArrayToFile.saveArrayToFile();
                    break;
                case 5:
                    CorporationSystem system = new CorporationSystem();
                    system.start();
                    break;
                case 6:
                    FileStatistics.showFileStatistics();
                    break;
                case 7:
                    FileMerger.mergeFiles();
                    break;
                case 8:
                    FileSearch.searchInFile();
                    break;
                case 9:
                    FileBackup.backupFile();
                    break;
                case 10:
                    FileBackup.listBackups();
                    break;
                case 11:
                    TestFilesCreator.createAllTestFiles();
                    break;
                case 12:
                    showFileInfo();
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
        System.out.println("│                        МЕНЮ ЗАДАНИЙ                             │");
        System.out.println("├─────────────────────────────────────────────────────────────────┤");
        System.out.println("│ 1  - Сравнение двух файлов                                      │");
        System.out.println("│ 2  - Поиск самой длинной строки                                 │");
        System.out.println("│ 3  - Загрузка массивов из файла                                 │");
        System.out.println("│ 4  - Сохранение массива в файл                                  │");
        System.out.println("│ 5  - Информационная система «Корпорация»                        │");
        System.out.println("├─────────────────────────────────────────────────────────────────┤");
        System.out.println("│ 6  - Статистика файла                                           │");
        System.out.println("│ 7  - Объединение двух файлов                                    │");
        System.out.println("│ 8  - Поиск текста в файле                                       │");
        System.out.println("│ 9  - Создать резервную копию файла                              │");
        System.out.println("│ 10 - Показать список бэкапов                                    │");
        System.out.println("│ 11 - Создать/обновить тестовые файлы                            │");
        System.out.println("│ 12 - Информация о файле                                         │");
        System.out.println("├─────────────────────────────────────────────────────────────────┤");
        System.out.println("│ 0  - Выход                                                      │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
    }

    private static void showFileInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== ИНФОРМАЦИЯ О ФАЙЛЕ ===\n");
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        if (!FileUtils.fileExists(filePath)) {
            System.err.println("❌ Файл не найден: " + filePath);
            return;
        }

        File file = new File(filePath);
        System.out.println("\n--- ИНФОРМАЦИЯ О ФАЙЛЕ ---");
        System.out.println("📄 Имя файла: " + file.getName());
        System.out.println("📁 Полный путь: " + file.getAbsolutePath());
        System.out.println("📁 Родительская папка: " + file.getParent());
        System.out.println("💾 Размер: " + file.length() + " байт");
        System.out.println("🔧 Расширение: " + FileUtils.getFileExtension(filePath));
        System.out.println("📖 Читаемый: " + (file.canRead() ? "Да" : "Нет"));
        System.out.println("✏️ Записываемый: " + (file.canWrite() ? "Да" : "Нет"));
        System.out.println("🔒 Исполняемый: " + (file.canExecute() ? "Да" : "Нет"));
        System.out.println("📅 Последнее изменение: " + new java.util.Date(file.lastModified()));

        try {
            long lines = FileUtils.countLines(filePath);
            System.out.println("📊 Количество строк: " + lines);
        } catch (IOException e) {
            System.out.println("📊 Количество строк: не удалось определить (" + e.getMessage() + ")");
        }

        // Дополнительная информация о содержимом
        if (file.length() > 0 && file.length() < 10000) {
            System.out.println("\n--- ПЕРВЫЕ 5 СТРОК ФАЙЛА ---");
            try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
                String line;
                int lineCount = 0;
                while ((line = reader.readLine()) != null && lineCount < 5) {
                    System.out.printf("%2d: %s\n", lineCount + 1,
                            line.length() > 80 ? line.substring(0, 80) + "..." : line);
                    lineCount++;
                }
                if (lineCount == 0) {
                    System.out.println("(файл пуст)");
                }
            } catch (IOException e) {
                System.out.println("Не удалось прочитать содержимое: " + e.getMessage());
            }
        }
    }

    private static int getIntInput(Scanner scanner) {
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