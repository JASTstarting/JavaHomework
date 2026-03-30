package lesson09_multithreading;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DirectoryCopyTask {

    private final String sourceDir;
    private final String targetDir;
    private final AtomicInteger filesCopied = new AtomicInteger(0);
    private final AtomicInteger dirsCreated = new AtomicInteger(0);
    private long totalBytes = 0;
    private long copyTime;

    public DirectoryCopyTask(String sourceDir, String targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
    }

    public void start() {
        System.out.println("\n=== ЗАДАНИЕ 3: Копирование директории ===\n");
        System.out.println("Исходная директория: " + sourceDir);
        System.out.println("Целевая директория: " + targetDir);

        // Проверяем существование исходной директории
        File source = new File(sourceDir);
        if (!source.exists() || !source.isDirectory()) {
            System.err.println("❌ Исходная директория не существует или не является папкой!");
            return;
        }

        long startTime = System.currentTimeMillis();

        // Запускаем поток для копирования
        Thread copier = new Thread(() -> {
            try {
                System.out.println("🔵 Поток-копировщик: начал работу");
                copyDirectory(new File(sourceDir), new File(targetDir));
                copyTime = System.currentTimeMillis() - startTime;
                System.out.println("✅ Поток-копировщик: завершил работу");
            } catch (IOException e) {
                System.err.println("Ошибка при копировании: " + e.getMessage());
            }
        });

        copier.start();

        try {
            copier.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n--- СТАТИСТИКА ---");
        System.out.println("Создано папок: " + dirsCreated.get());
        System.out.println("Скопировано файлов: " + filesCopied.get());
        System.out.println("Всего скопировано байт: " + totalBytes + " (" + formatSize(totalBytes) + ")");
        System.out.println("Время копирования: " + copyTime + " мс");
        if (copyTime > 0) {
            double speed = totalBytes / (double) copyTime / 1024;
            System.out.printf("Скорость копирования: %.2f КБ/сек\n", speed * 1000);
        }
    }

    private void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            if (target.mkdirs()) {
                dirsCreated.incrementAndGet();
                System.out.println("   Создана папка: " + target.getPath());
            }
        }

        File[] files = source.listFiles();
        if (files == null) return;

        for (File file : files) {
            File targetFile = new File(target, file.getName());
            if (file.isDirectory()) {
                copyDirectory(file, targetFile);
            } else {
                copyFile(file, targetFile);
            }
        }
    }

    private void copyFile(File source, File target) throws IOException {
        Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        filesCopied.incrementAndGet();
        totalBytes += source.length();

        if (filesCopied.get() % 100 == 0) {
            System.out.printf("   Скопировано файлов: %d (%.2f МБ)\n",
                    filesCopied.get(), totalBytes / (1024.0 * 1024.0));
        }
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " Б";
        if (bytes < 1024 * 1024) return String.format("%.2f КБ", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.2f МБ", bytes / (1024.0 * 1024.0));
        return String.format("%.2f ГБ", bytes / (1024.0 * 1024.0 * 1024.0));
    }
}