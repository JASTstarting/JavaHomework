package lesson08_files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFilesCreator {

    private static final String TEST_FILES_DIR = "test_files";

    public static void createAllTestFiles() {
        System.out.println("\n=== СОЗДАНИЕ ТЕСТОВЫХ ФАЙЛОВ ===\n");

        // Получаем абсолютный путь к папке test_files
        File dir = new File(TEST_FILES_DIR);
        String absolutePath = dir.getAbsolutePath();
        System.out.println("Создаем файлы в: " + absolutePath);

        // Создаем директорию, если её нет
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("✓ Создана папка: " + absolutePath);
            } else {
                System.err.println("✗ Не удалось создать папку: " + absolutePath);
                return;
            }
        } else {
            System.out.println("✓ Папка уже существует: " + absolutePath);
        }

        // Создаем все тестовые файлы
        createFile1();
        createFile2();
        createTestFile();
        createArraysFile();
        createEmployeesFile();

        System.out.println("\n✓ Все тестовые файлы успешно созданы в папке: " + absolutePath);
        System.out.println("  - file1.txt");
        System.out.println("  - file2.txt");
        System.out.println("  - test.txt");
        System.out.println("  - arrays.txt");
        System.out.println("  - employees.txt");

        // Проверяем, что файлы действительно созданы
        System.out.println("\n--- ПРОВЕРКА СОЗДАНИЯ ФАЙЛОВ ---");
        String[] files = {"file1.txt", "file2.txt", "test.txt", "arrays.txt", "employees.txt"};
        for (String fileName : files) {
            File file = new File(TEST_FILES_DIR, fileName);
            System.out.printf("  %s: %s\n", fileName, file.exists() ? "✓ создан" : "✗ не создан");
        }
    }

    private static void createFile1() {
        String content = """
                Привет, мир!
                Это первая строка
                Java программирование
                Файлы в Java
                Сравнение строк
                Последняя строка""";
        writeFile("file1.txt", content);
    }

    private static void createFile2() {
        String content = """
                Привет, мир!
                Это первая строка
                Java программирование
                Файлы в Java
                Строки отличаются здесь
                Последняя строка""";
        writeFile("file2.txt", content);
    }

    private static void createTestFile() {
        String content = """
                Короткая строка
                Эта строка значительно длиннее, чем все остальные строки в файле
                Средняя строка
                Еще одна строка
                Самая""";
        writeFile("test.txt", content);
    }

    private static void createArraysFile() {
        String content = """
                1 2 3 4 5 6 7 8 9 10
                15 25 35 45 55
                100 200 300 400 500 600
                7 14 21 28 35 42 49
                2 4 8 16 32 64 128 256""";
        writeFile("arrays.txt", content);
    }

    private static void createEmployeesFile() {
        String content = """
                1;Иванов;Иван;Иванович;15.05.1985;Директор;150000;Руководство
                2;Петрова;Мария;Сергеевна;20.03.1990;Главный бухгалтер;80000;Бухгалтерия
                3;Сидоров;Алексей;Петрович;10.07.1988;Программист;120000;IT
                4;Козлова;Елена;Владимировна;25.11.1995;Менеджер;60000;Продажи
                5;Смирнов;Дмитрий;Андреевич;03.09.1992;Системный администратор;90000;IT
                6;Новикова;Анна;Игоревна;18.12.1998;Секретарь;45000;Администрация
                7;Морозов;Константин;Валерьевич;30.06.1980;Технический директор;130000;Руководство
                8;Волкова;Татьяна;Алексеевна;12.04.1993;Дизайнер;70000;Маркетинг
                9;Соколов;Павел;Николаевич;08.08.1987;Аналитик;110000;Аналитика
                10;Кузнецова;Ольга;Викторовна;22.02.1996;Тестировщик;65000;QA""";
        writeFile("employees.txt", content);
    }

    private static void writeFile(String fileName, String content) {
        String filePath = TEST_FILES_DIR + File.separator + fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(content);
            System.out.println("✓ Создан файл: " + filePath);
        } catch (IOException e) {
            System.err.println("✗ Ошибка при создании файла " + fileName + ": " + e.getMessage());
        }
    }

    public static boolean areTestFilesExist() {
        String[] files = {"file1.txt", "file2.txt", "test.txt", "arrays.txt", "employees.txt"};
        for (String file : files) {
            Path path = Paths.get(TEST_FILES_DIR, file);
            if (!Files.exists(path)) {
                System.out.println("Файл не найден: " + path.toAbsolutePath());
                return false;
            }
        }
        return true;
    }

    public static String getTestFilesPath() {
        return TEST_FILES_DIR;
    }

    public static String getTestFilePath(String fileName) {
        return TEST_FILES_DIR + File.separator + fileName;
    }
}