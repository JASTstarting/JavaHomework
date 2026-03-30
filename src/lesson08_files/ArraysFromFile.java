package lesson08_files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArraysFromFile {

    public static void loadArraysFromFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== ЗАДАНИЕ 3: Загрузка массивов из файла ===\n");

        String filePath;

        // Предлагаем использовать тестовый файл
        System.out.println("Тестовый файл: " + TestFilesCreator.getTestFilePath("arrays.txt"));
        System.out.print("Использовать тестовый файл? (да/нет/свои): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("да")) {
            filePath = TestFilesCreator.getTestFilePath("arrays.txt");
            System.out.println("Используется файл: " + filePath);
        } else if (choice.equalsIgnoreCase("свои")) {
            System.out.print("Введите путь к файлу: ");
            filePath = scanner.nextLine();
        } else {
            System.out.println("Загрузка отменена");
            return;
        }

        if (FileUtils.fileExists(filePath)) {
            System.err.println("Файл не найден: " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            List<int[]> arrays = new ArrayList<>();
            String line;
            int lineNumber = 0;

            // Читаем строки и создаем массивы
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] numbersStr = line.trim().split("\\s+");
                int[] array = new int[numbersStr.length];

                try {
                    for (int i = 0; i < numbersStr.length; i++) {
                        array[i] = Integer.parseInt(numbersStr[i]);
                    }
                    arrays.add(array);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка в строке " + lineNumber + ": нечисловые данные");
                }
            }

            if (arrays.isEmpty()) {
                System.out.println("Файл не содержит корректных числовых данных");
                return;
            }

            // Переменные для глобальной статистики
            int globalMax = Integer.MIN_VALUE;
            int globalMin = Integer.MAX_VALUE;
            long globalSum = 0;

            System.out.println("\n--- СТАТИСТИКА ПО МАССИВАМ ---\n");

            // Обрабатываем каждый массив
            for (int i = 0; i < arrays.size(); i++) {
                int[] array = arrays.get(i);

                int localMax = array[0];
                int localMin = array[0];
                long localSum = 0;

                for (int num : array) {
                    localMax = Math.max(localMax, num);
                    localMin = Math.min(localMin, num);
                    localSum += num;
                }

                // Обновляем глобальную статистику
                globalMax = Math.max(globalMax, localMax);
                globalMin = Math.min(globalMin, localMin);
                globalSum += localSum;

                System.out.println("Массив " + (i + 1) + " (размер: " + array.length + "):");
                System.out.println("  Элементы: " + Arrays.toString(array));
                System.out.println("  Максимум: " + localMax);
                System.out.println("  Минимум: " + localMin);
                System.out.println("  Сумма: " + localSum);
                System.out.println();
            }

            System.out.println("--- ОБЩАЯ СТАТИСТИКА ПО ВСЕМ МАССИВАМ ---");
            System.out.println("Количество массивов: " + arrays.size());
            System.out.println("Глобальный максимум: " + globalMax);
            System.out.println("Глобальный минимум: " + globalMin);
            System.out.println("Общая сумма всех элементов: " + globalSum);

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}