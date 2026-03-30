package lesson09_multithreading;

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class FileSearchAndFilterTask {

    private final String directory;
    private final String searchWord;
    private final String forbiddenWordsFile;
    private final String resultFile;
    private final String filteredFile;

    private final CountDownLatch searchLatch = new CountDownLatch(1);
    private final AtomicInteger filesFound = new AtomicInteger(0);
    private final AtomicInteger totalLines = new AtomicInteger(0);
    private long searchTime;
    private long filterTime;

    public FileSearchAndFilterTask(String directory, String searchWord,
                                   String forbiddenWordsFile, String resultFile, String filteredFile) {
        this.directory = directory;
        this.searchWord = searchWord;
        this.forbiddenWordsFile = forbiddenWordsFile;
        this.resultFile = resultFile;
        this.filteredFile = filteredFile;
    }

    public void start() {
        System.out.println("\n=== ЗАДАНИЕ 4: Поиск и фильтрация слов в файлах ===\n");
        System.out.println("Директория поиска: " + directory);
        System.out.println("Искомое слово: " + searchWord);
        System.out.println("Файл с запрещенными словами: " + forbiddenWordsFile);

        // Проверяем существование директории
        File dir = new File(directory);
        if (!dir.exists()) {
            System.err.println("\n❌ ОШИБКА: Директория не существует!");
            System.err.println("   Проверьте правильность пути: " + directory);
            System.err.println("   Совет: укажите путь к папке, а не к файлу.");
            System.err.println("   Пример правильного пути: test_files");
            System.err.println("   Пример неправильного пути: test_files/file1.txt\n");
            return;
        }

        if (!dir.isDirectory()) {
            System.err.println("\n❌ ОШИБКА: Указанный путь не является директорией!");
            System.err.println("   Путь: " + directory);
            System.err.println("   Совет: укажите путь к папке, а не к файлу.");
            System.err.println("   Пример правильного пути: test_files");
            System.err.println("   Текущий путь указывает на файл.\n");
            return;
        }

        // Показываем содержимое директории
        System.out.println("\n📁 Содержимое директории:");
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            int fileCount = 0;
            int dirCount = 0;
            for (File f : files) {
                if (f.isFile()) {
                    System.out.println("   📄 " + f.getName());
                    fileCount++;
                } else if (f.isDirectory()) {
                    System.out.println("   📁 " + f.getName() + "/");
                    dirCount++;
                }
            }
            System.out.println("   Всего: " + fileCount + " файлов, " + dirCount + " папок");
        } else {
            System.out.println("   (директория пуста)");
        }

        // Создаем файл с запрещенными словами, если его нет
        createForbiddenWordsFileIfNotExists();

        long startTime = System.currentTimeMillis();

        // Первый поток - поиск файлов с искомым словом
        Thread searchThread = new Thread(() -> {
            System.out.println("\n🔵 Поток-поиск: начал работу");
            long start = System.currentTimeMillis();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
                writer.write("=== РЕЗУЛЬТАТЫ ПОИСКА ===");
                writer.newLine();
                writer.write("Искомое слово: " + searchWord);
                writer.newLine();
                writer.write("Директория поиска: " + directory);
                writer.newLine();
                writer.write("Дата поиска: " + new Date());
                writer.newLine();
                writer.write("=".repeat(50));
                writer.newLine();
                writer.newLine();

                searchInDirectory(dir, writer);

                writer.newLine();
                writer.write("=".repeat(50));
                writer.newLine();
                writer.write("СТАТИСТИКА:");
                writer.newLine();
                writer.write("Найдено файлов: " + filesFound.get());
                writer.newLine();
                writer.write("Всего строк с совпадениями: " + totalLines.get());

                searchTime = System.currentTimeMillis() - start;
                System.out.println("✅ Поток-поиск: завершил работу за " + searchTime + " мс");
                System.out.println("   Найдено файлов с совпадениями: " + filesFound.get());
                System.out.println("   Всего строк с совпадениями: " + totalLines.get());
                searchLatch.countDown();
            } catch (IOException e) {
                System.err.println("Ошибка при поиске: " + e.getMessage());
            }
        });

        // Второй поток - фильтрация (ждет завершения поиска)
        Thread filterThread = new Thread(() -> {
            try {
                searchLatch.await();
                System.out.println("\n🔵 Поток-фильтрация: начал работу");
                long start = System.currentTimeMillis();

                List<String> forbiddenWords = loadForbiddenWords();
                System.out.println("   Загружено запрещенных слов: " + forbiddenWords.size());
                if (!forbiddenWords.isEmpty()) {
                    System.out.println("   Запрещенные слова: " + forbiddenWords);
                }

                filterFile(resultFile, filteredFile, forbiddenWords);

                filterTime = System.currentTimeMillis() - start;
                System.out.println("✅ Поток-фильтрация: завершил работу за " + filterTime + " мс");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Запускаем потоки
        searchThread.start();
        filterThread.start();

        try {
            filterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long totalTime = System.currentTimeMillis() - startTime;

        System.out.println("\n--- СТАТИСТИКА ---");
        System.out.println("Время поиска: " + searchTime + " мс");
        System.out.println("Время фильтрации: " + filterTime + " мс");
        System.out.println("Общее время: " + totalTime + " мс");
        System.out.println("\nРезультаты сохранены в файлы:");
        System.out.println("  - Результат поиска: " + new File(resultFile).getAbsolutePath());
        System.out.println("  - Отфильтрованный результат: " + new File(filteredFile).getAbsolutePath());

        // Показываем размеры файлов
        File result = new File(resultFile);
        File filtered = new File(filteredFile);
        if (result.exists()) {
            System.out.println("\nРазмер файла результатов: " + formatSize(result.length()));
        }
        if (filtered.exists()) {
            System.out.println("Размер отфильтрованного файла: " + formatSize(filtered.length()));
        }
    }

    private void searchInDirectory(File dir, BufferedWriter writer) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                searchInDirectory(file, writer);
            } else if (file.isFile() && file.canRead()) {
                searchInFile(file, writer);
            }
        }
    }

    private void searchInFile(File file, BufferedWriter writer) throws IOException {
        boolean fileHasMatch = false;
        List<String> matchingLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.toLowerCase().contains(searchWord.toLowerCase())) {
                    fileHasMatch = true;
                    matchingLines.add(String.format("[%s:%d] %s", file.getName(), lineNumber, line));
                    totalLines.incrementAndGet();
                }
            }
        }

        if (fileHasMatch) {
            filesFound.incrementAndGet();
            writer.write("\n📄 Файл: " + file.getAbsolutePath());
            writer.newLine();
            writer.write("   Найдено совпадений: " + matchingLines.size());
            writer.newLine();
            writer.write("   " + "-".repeat(40));
            writer.newLine();
            for (String match : matchingLines) {
                writer.write("   " + match);
                writer.newLine();
            }
        }
    }

    private void createForbiddenWordsFileIfNotExists() {
        File file = new File(forbiddenWordsFile);
        if (!file.exists()) {
            System.out.println("\n📝 Создаю тестовый файл с запрещенными словами...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(forbiddenWordsFile))) {
                String[] words = {"мат", "ругательство", "плохое", "запрет", "цензура", "дурак", "глупый"};
                for (String word : words) {
                    writer.write(word);
                    writer.newLine();
                }
                System.out.println("   ✅ Создан файл: " + forbiddenWordsFile);
                System.out.println("   Запрещенные слова: " + Arrays.toString(words));
            } catch (IOException e) {
                System.err.println("   ⚠️ Не удалось создать файл с запрещенными словами: " + e.getMessage());
            }
        } else {
            System.out.println("\n✅ Файл с запрещенными словами найден: " + forbiddenWordsFile);
        }
    }

    private List<String> loadForbiddenWords() {
        List<String> forbidden = new ArrayList<>();
        File file = new File(forbiddenWordsFile);

        if (!file.exists()) {
            return forbidden;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(forbiddenWordsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().toLowerCase();
                if (!line.isEmpty()) {
                    forbidden.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла запрещенных слов: " + e.getMessage());
        }

        return forbidden;
    }

    private void filterFile(String inputFile, String outputFile, List<String> forbiddenWords) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            int filteredLines = 0;
            int totalLines = 0;

            while ((line = reader.readLine()) != null) {
                totalLines++;
                String filteredLine = line;
                for (String forbidden : forbiddenWords) {
                    // Заменяем запрещенные слова на *** (игнорируем регистр)
                    filteredLine = filteredLine.replaceAll("(?i)\\b" + Pattern.quote(forbidden) + "\\b", "***");
                }

                writer.write(filteredLine);
                writer.newLine();

                if (!filteredLine.equals(line)) {
                    filteredLines++;
                }
            }

            System.out.println("   Обработано строк: " + totalLines);
            System.out.println("   Изменено строк: " + filteredLines);

        } catch (IOException e) {
            System.err.println("Ошибка при фильтрации: " + e.getMessage());
        }
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " Б";
        if (bytes < 1024 * 1024) return String.format("%.2f КБ", bytes / 1024.0);
        return String.format("%.2f МБ", bytes / (1024.0 * 1024.0));
    }
}