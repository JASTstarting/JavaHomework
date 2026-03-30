package lesson08_files;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FileBackup {

    private static final String BACKUP_DIR = "backups";

    public static void backupFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== РЕЗЕРВНОЕ КОПИРОВАНИЕ ===\n");

        System.out.print("Введите путь к файлу для резервного копирования: ");
        String sourceFile = scanner.nextLine();

        if (FileUtils.fileExists(sourceFile)) {
            System.err.println("Файл не найден: " + sourceFile);
            return;
        }

        // Создаем директорию для бэкапов
        FileUtils.createDirectoryIfNotExists(BACKUP_DIR);

        // Создаем имя для бэкапа с датой и временем
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String backupName = new File(sourceFile).getName();
        String backupPath = BACKUP_DIR + File.separator + backupName + "_" + timestamp + ".bak";

        try {
            // Копируем файл
            Files.copy(Paths.get(sourceFile), Paths.get(backupPath), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("\n✓ Резервная копия создана: " + backupPath);
            System.out.println("  📦 Размер: " + new File(sourceFile).length() + " байт");
            System.out.println("  📅 Дата: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));

        } catch (IOException e) {
            System.err.println("Ошибка при создании резервной копии: " + e.getMessage());
        }
    }

    public static void listBackups() {
        System.out.println("\n=== СПИСОК БЭКАПОВ ===\n");

        File dir = new File(BACKUP_DIR);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("📁 Папка с бэкапами не найдена");
            return;
        }

        File[] backups = dir.listFiles((_, name) -> name.endsWith(".bak"));

        if (backups == null || backups.length == 0) {
            System.out.println("📁 Нет сохраненных бэкапов");
            return;
        }

        System.out.println("Найдено " + backups.length + " бэкапов:\n");
        System.out.println("=".repeat(70));
        for (File backup : backups) {
            System.out.printf("📄 %s - %d байт - %s\n",
                    backup.getName(),
                    backup.length(),
                    new java.util.Date(backup.lastModified()));
        }
        System.out.println("=".repeat(70));

        // Предлагаем восстановить бэкап
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВосстановить бэкап? (введите имя файла или Enter для отмены): ");
        String backupName = scanner.nextLine();

        if (!backupName.isEmpty()) {
            String backupPath = BACKUP_DIR + File.separator + backupName;
            if (new File(backupPath).exists()) {
                System.out.print("Введите путь для восстановления: ");
                String restorePath = scanner.nextLine();
                if (FileUtils.copyFile(backupPath, restorePath)) {
                    System.out.println("✓ Бэкап восстановлен: " + restorePath);
                } else {
                    System.err.println("Ошибка при восстановлении бэкапа");
                }
            } else {
                System.err.println("Бэкап не найден: " + backupName);
            }
        }
    }
}