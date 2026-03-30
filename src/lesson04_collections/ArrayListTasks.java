package lesson04_collections;

import java.util.ArrayList;

public class ArrayListTasks {

    // Задание 1: Вычисление суммы и среднего арифметического
    public static void task1() {
        System.out.println("=== Задание 1: Сумма и среднее арифметическое ===\n");

        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(15);
        numbers.add(23);
        numbers.add(42);
        numbers.add(7);
        numbers.add(31);
        numbers.add(54);
        numbers.add(19);
        numbers.add(8);
        numbers.add(37);
        numbers.add(45);

        System.out.println("Список чисел: " + numbers);

        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }

        double average = (double) sum / numbers.size();

        System.out.println("Сумма всех чисел: " + sum);
        System.out.printf("Среднее арифметическое: %.2f\n", average);

        // Альтернативный способ с использованием for-each
        System.out.println("\n--- Альтернативный способ (for-each) ---");
        int sum2 = 0;
        for (int num : numbers) {
            sum2 += num;
        }
        double average2 = (double) sum2 / numbers.size();
        System.out.println("Сумма (for-each): " + sum2);
        System.out.printf("Среднее (for-each): %.2f\n", average2);
    }

    // Задание 2: Поиск самого длинного слова
    public static void task2() {
        System.out.println("\n\n=== Задание 2: Поиск самого длинного слова ===\n");

        ArrayList<String> words = new ArrayList<>();

        words.add("яблоко");
        words.add("апельсин");
        words.add("груша");
        words.add("мандарин");
        words.add("арбуз");
        words.add("виноград");
        words.add("ананас");
        words.add("киви");
        words.add("клубника");
        words.add("черника");

        System.out.println("Список слов: " + words);

        String longestWord = words.getFirst(); // Начинаем с первого слова

        for (int i = 1; i < words.size(); i++) {
            if (words.get(i).length() > longestWord.length()) {
                longestWord = words.get(i);
            }
        }

        System.out.println("Самое длинное слово: \"" + longestWord + "\"");
        System.out.println("Длина слова: " + longestWord.length() + " символов");

        // Дополнительно: если нужно найти все самые длинные слова
        System.out.println("\n--- Поиск всех самых длинных слов ---");
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }

        System.out.println("Максимальная длина: " + maxLength);
        System.out.print("Слова максимальной длины: ");
        for (String word : words) {
            if (word.length() == maxLength) {
                System.out.print("\"" + word + "\" ");
            }
        }
        System.out.println();
    }

    // Задание 3: Проверка на палиндром
    public static void task3() {
        System.out.println("\n\n=== Задание 3: Проверка на палиндром ===\n");

        // Пример 1: Палиндром "казак"
        ArrayList<Character> palindrome1 = new ArrayList<>();
        palindrome1.add('к');
        palindrome1.add('а');
        palindrome1.add('з');
        palindrome1.add('а');
        palindrome1.add('к');

        System.out.println("Список 1: " + palindrome1);
        System.out.println("Является палиндромом? " + isPalindrome(palindrome1));

        // Пример 2: Палиндром "А роза упала на лапу Азора" (игнорируем пробелы и регистр)
        ArrayList<Character> palindrome2 = new ArrayList<>();
        String text = "арозаупаланалапуазора";
        for (char c : text.toCharArray()) {
            palindrome2.add(c);
        }

        System.out.println("\nСписок 2: " + palindrome2);
        System.out.println("Является палиндромом? " + isPalindrome(palindrome2));

        // Пример 3: Не палиндром
        ArrayList<Character> notPalindrome = new ArrayList<>();
        notPalindrome.add('п');
        notPalindrome.add('р');
        notPalindrome.add('и');
        notPalindrome.add('в');
        notPalindrome.add('е');
        notPalindrome.add('т');

        System.out.println("\nСписок 3: " + notPalindrome);
        System.out.println("Является палиндромом? " + isPalindrome(notPalindrome));

        // Пример 4: Палиндром с четным количеством символов
        ArrayList<Character> palindrome3 = new ArrayList<>();
        palindrome3.add('а');
        palindrome3.add('б');
        palindrome3.add('б');
        palindrome3.add('а');

        System.out.println("\nСписок 4: " + palindrome3);
        System.out.println("Является палиндромом? " + isPalindrome(palindrome3));

        // Пример 5: Один символ - всегда палиндром
        ArrayList<Character> singleChar = new ArrayList<>();
        singleChar.add('х');

        System.out.println("\nСписок 5: " + singleChar);
        System.out.println("Является палиндромом? " + isPalindrome(singleChar));

        // Пример 6: Пустой список
        ArrayList<Character> emptyList = new ArrayList<>();

        System.out.println("\nСписок 6 (пустой): " + emptyList);
        System.out.println("Является палиндромом? " + isPalindrome(emptyList));
    }

    // Метод для проверки списка на палиндром
    public static boolean isPalindrome(ArrayList<Character> list) {
        // Пустой список считаем палиндромом
        if (list == null || list.isEmpty()) {
            return true;
        }

        // Сравниваем элементы с начала и конца
        for (int i = 0; i < list.size() / 2; i++) {
            if (!list.get(i).equals(list.get(list.size() - 1 - i))) {
                return false; // Если нашли несовпадение
            }
        }
        return true;
    }

    // Дополнительный метод: проверка строки на палиндром
    public static boolean isPalindromeString(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        // Приводим к нижнему регистру и удаляем пробелы
        String cleanStr = str.toLowerCase().replaceAll("\\s+", "");

        for (int i = 0; i < cleanStr.length() / 2; i++) {
            if (cleanStr.charAt(i) != cleanStr.charAt(cleanStr.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // Дополнительный метод: создание списка символов из строки
    public static ArrayList<Character> stringToCharList(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list;
    }
}