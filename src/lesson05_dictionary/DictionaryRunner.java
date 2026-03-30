package lesson05_dictionary;

import java.util.*;

public class DictionaryRunner {
    private static Dictionary dictionary;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        dictionary = new Dictionary("Английский", "Русский");

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    СЛОВАРЬ (Англо-Русский)                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        dictionary.addInitialData();

        while (true) {
            showMenu();
            int choice = getIntInput();
            System.out.println();

            switch (choice) {
                case 1:
                    showAllWords();
                    break;
                case 2:
                    translateWord();
                    break;
                case 3:
                    showWordInfo();
                    break;
                case 4:
                    addWord();
                    break;
                case 5:
                    removeWord();
                    break;
                case 6:
                    replaceWord();
                    break;
                case 7:
                    addTranslation();
                    break;
                case 8:
                    replaceTranslation();
                    break;
                case 9:
                    removeTranslation();
                    break;
                case 10:
                    dictionary.showTopPopular();
                    break;
                case 11:
                    dictionary.showTopUnpopular();
                    break;
                case 0:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
            System.out.println("\n" + "=".repeat(60) + "\n");
        }
    }

    private static void showMenu() {
        System.out.println("\n--- МЕНЮ СЛОВАРЯ ---");
        System.out.println("1. Показать все слова");
        System.out.println("2. Перевести слово (увеличивает популярность)");
        System.out.println("3. Показать информацию о слове");
        System.out.println("4. Добавить слово");
        System.out.println("5. Удалить слово");
        System.out.println("6. Заменить слово");
        System.out.println("7. Добавить перевод");
        System.out.println("8. Заменить перевод");
        System.out.println("9. Удалить перевод");
        System.out.println("10. Топ-10 популярных слов");
        System.out.println("11. Топ-10 непопулярных слов");
        System.out.println("0. Выход");
    }

    private static void showAllWords() {
        dictionary.showAll();
    }

    private static void translateWord() {
        String word = getStringInput("Введите слово для перевода: ");
        List<String> translations = dictionary.translate(word);
        if (!translations.isEmpty()) {
            System.out.println("Перевод(ы): " + translations);
        }
    }

    private static void showWordInfo() {
        String word = getStringInput("Введите слово: ");
        dictionary.showWord(word);
    }

    private static void addWord() {
        String word = getStringInput("Введите слово на английском: ");
        System.out.print("Введите перевод(ы) через запятую: ");
        String transInput = scanner.nextLine();
        List<String> translations = Arrays.asList(transInput.split(",\\s*"));
        dictionary.addWord(word, translations);
    }

    private static void removeWord() {
        String word = getStringInput("Введите слово для удаления: ");
        dictionary.removeWord(word);
    }

    private static void replaceWord() {
        String oldWord = getStringInput("Введите слово для замены: ");
        String newWord = getStringInput("Введите новое слово: ");
        dictionary.replaceWord(oldWord, newWord);
    }

    private static void addTranslation() {
        String word = getStringInput("Введите слово: ");
        String translation = getStringInput("Введите новый перевод: ");
        dictionary.addTranslation(word, translation);
    }

    private static void replaceTranslation() {
        String word = getStringInput("Введите слово: ");
        String oldTranslation = getStringInput("Введите старый перевод: ");
        String newTranslation = getStringInput("Введите новый перевод: ");
        dictionary.replaceTranslation(word, oldTranslation, newTranslation);
    }

    private static void removeTranslation() {
        String word = getStringInput("Введите слово: ");
        String translation = getStringInput("Введите перевод для удаления: ");
        dictionary.removeTranslation(word, translation);
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput() {
        System.out.print("Выберите действие: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Введите число: ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        return result;
    }
}