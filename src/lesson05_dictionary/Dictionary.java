package lesson05_dictionary;

import java.util.*;

public class Dictionary {
    private final Map<String, WordEntry> dictionary;  // Слово -> Запись словаря
    private final String sourceLanguage;              // Исходный язык (например, "Английский")
    private final String targetLanguage;              // Целевой язык (например, "Русский")

    public Dictionary(String sourceLanguage, String targetLanguage) {
        this.dictionary = new HashMap<>();
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

    public void addInitialData() {
        System.out.println("\n--- Начальное заполнение словаря ---");

        addWord("hello", Arrays.asList("привет", "здравствуйте"));
        addWord("world", Arrays.asList("мир", "вселенная"));
        addWord("computer", Arrays.asList("компьютер", "ЭВМ"));
        addWord("programming", List.of("программирование"));
        addWord("language", Arrays.asList("язык", "речь"));
        addWord("java", Arrays.asList("Ява", "остров Ява"));
        addWord("dictionary", List.of("словарь"));
        addWord("word", List.of("слово"));
        addWord("translation", List.of("перевод"));
        addWord("popular", Arrays.asList("популярный", "известный"));

        System.out.println("Словарь инициализирован " + dictionary.size() + " словами");
    }

    public void addWord(String word, List<String> translations) {
        if (dictionary.containsKey(word)) {
            System.out.println("Слово \"" + word + "\" уже существует. Используйте добавление переводов.");
            return;
        }
        dictionary.put(word, new WordEntry(word, translations));
        System.out.println("Слово \"" + word + "\" добавлено со значениями: " + translations);
    }

    public void removeWord(String word) {
        if (dictionary.remove(word) != null) {
            System.out.println("Слово \"" + word + "\" удалено из словаря");
        } else {
            System.out.println("Слово \"" + word + "\" не найдено");
        }
    }

    public void replaceWord(String oldWord, String newWord) {
        WordEntry entry = dictionary.remove(oldWord);
        if (entry != null) {
            entry.setWord(newWord);
            dictionary.put(newWord, entry);
            System.out.println("Слово \"" + oldWord + "\" заменено на \"" + newWord + "\"");
        } else {
            System.out.println("Слово \"" + oldWord + "\" не найдено");
        }
    }

    public List<String> translate(String word) {
        WordEntry entry = dictionary.get(word.toLowerCase());
        if (entry != null) {
            return entry.getTranslations();
        } else {
            System.out.println("Слово \"" + word + "\" не найдено в словаре");
            return new ArrayList<>();
        }
    }

    public void showWord(String word) {
        WordEntry entry = dictionary.get(word.toLowerCase());
        if (entry != null) {
            entry.display();
        } else {
            System.out.println("Слово \"" + word + "\" не найдено");
        }
    }

    public void addTranslation(String word, String translation) {
        WordEntry entry = dictionary.get(word.toLowerCase());
        if (entry != null) {
            entry.addTranslation(translation);
        } else {
            System.out.println("Слово \"" + word + "\" не найдено");
        }
    }

    public void replaceTranslation(String word, String oldTranslation, String newTranslation) {
        WordEntry entry = dictionary.get(word.toLowerCase());
        if (entry != null) {
            entry.replaceTranslation(oldTranslation, newTranslation);
        } else {
            System.out.println("Слово \"" + word + "\" не найдено");
        }
    }

    public void removeTranslation(String word, String translation) {
        WordEntry entry = dictionary.get(word.toLowerCase());
        if (entry != null) {
            entry.removeTranslation(translation);
        } else {
            System.out.println("Слово \"" + word + "\" не найдено");
        }
    }

    public void showTopPopular() {
        List<WordEntry> entries = new ArrayList<>(dictionary.values());
        entries.sort((e1, e2) -> Integer.compare(e2.getPopularity(), e1.getPopularity()));

        System.out.println("\n=== ТОП-10 САМЫХ ПОПУЛЯРНЫХ СЛОВ ===");
        int count = Math.min(10, entries.size());
        if (count == 0) {
            System.out.println("Словарь пуст");
            return;
        }

        for (int i = 0; i < count; i++) {
            WordEntry entry = entries.get(i);
            System.out.printf("%d. %s (просмотров: %d)\n", i + 1, entry.getWord(), entry.getPopularity());
        }
    }

    public void showTopUnpopular() {
        List<WordEntry> entries = new ArrayList<>(dictionary.values());
        entries.sort(Comparator.comparingInt(WordEntry::getPopularity));

        System.out.println("\n=== ТОП-10 САМЫХ НЕПОПУЛЯРНЫХ СЛОВ ===");
        int count = Math.min(10, entries.size());
        if (count == 0) {
            System.out.println("Словарь пуст");
            return;
        }

        for (int i = 0; i < count; i++) {
            WordEntry entry = entries.get(i);
            System.out.printf("%d. %s (просмотров: %d)\n", i + 1, entry.getWord(), entry.getPopularity());
        }
    }

    public void showAll() {
        System.out.println("\n=== ВЕСЬ СЛОВАРЬ (" + sourceLanguage + " -> " + targetLanguage + ") ===");
        System.out.println("Всего слов: " + dictionary.size());
        for (WordEntry entry : dictionary.values()) {
            System.out.println(entry);
        }
    }

    public int size() {
        return dictionary.size();
    }
}