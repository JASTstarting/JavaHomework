package lesson08_files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayToFile {

    public static void saveArrayToFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== ЗАДАНИЕ 4: Сохранение массива в файл ===\n");

        System.out.print("Введите путь к файлу для сохранения: ");
        String filePath = scanner.nextLine();

        // Предлагаем использовать тестовую папку
        if (!filePath.contains(File.separator)) {
            filePath = TestFilesCreator.getTestFilesPath() + File.separator + filePath;
            System.out.println("Файл будет сохранен в: " + filePath);
        }

        System.out.print("Введите размер массива: ");
        int size;
        try {
            size = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Неверный размер массива");
            return;
        }

        int[] array = new int[size];
        System.out.println("Введите " + size + " целых чисел через пробел:");
        String[] inputNumbers = scanner.nextLine().trim().split("\\s+");

        if (inputNumbers.length < size) {
            System.err.println("Введено недостаточно чисел");
            return;
        }

        for (int i = 0; i < size; i++) {
            try {
                array[i] = Integer.parseInt(inputNumbers[i]);
            } catch (NumberFormatException e) {
                System.err.println("Ошибка в числе: " + inputNumbers[i]);
                return;
            }
        }

        // Создаем различные представления массива
        int[] evenNumbers = Arrays.stream(array)
                .filter(n -> n % 2 == 0)
                .toArray();

        int[] oddNumbers = Arrays.stream(array)
                .filter(n -> n % 2 != 0)
                .toArray();

        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - 1 - i];
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("1. Исходный массив: " + arrayToString(array));
            writer.newLine();
            writer.write("2. Четные значения: " + arrayToString(evenNumbers));
            writer.newLine();
            writer.write("3. Нечетные значения: " + arrayToString(oddNumbers));
            writer.newLine();
            writer.write("4. Перевернутый массив: " + arrayToString(reversedArray));
            writer.newLine();
            writer.write("5. Количество элементов: " + array.length);
            writer.newLine();
            writer.write("6. Сумма элементов: " + Arrays.stream(array).sum());

            System.out.println("\n--- РЕЗУЛЬТАТ ---");
            System.out.println("✓ Данные сохранены в файл: " + filePath);
            System.out.println("\nСодержимое файла:");
            System.out.println("1. " + Arrays.toString(array));
            System.out.println("2. " + Arrays.toString(evenNumbers));
            System.out.println("3. " + Arrays.toString(oddNumbers));
            System.out.println("4. " + Arrays.toString(reversedArray));

        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static String arrayToString(int[] array) {
        if (array.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}