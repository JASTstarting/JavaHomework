package lesson08_files;

import java.io.*;
import java.nio.file.*;

public class FileUtils {

    // Проверка существования файла
    public static boolean fileExists(String path) {
        if (path == null || path.trim().isEmpty()) {
            return false;
        }
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    // Подсчет количества строк в файле
    public static long countLines(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines().count();
        }
    }

    // Получение расширения файла
    public static String getFileExtension(String path) {
        String fileName = new File(path).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }

    // Создание директории, если её нет
    public static boolean createDirectoryIfNotExists(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return dir.mkdirs();
        }
        return true;
    }

    public static boolean copyFile(String source, String destination) {
        try {
            Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Получение абсолютного пути
    public static String getAbsolutePath(String path) {
        return new File(path).getAbsolutePath();
    }

    // Проверка, является ли путь директорией
    public static boolean isDirectory(String path) {
        File file = new File(path);
        return file.exists() && file.isDirectory();
    }
}