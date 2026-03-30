package lesson05_dictionary;

import java.util.ArrayList;
import java.util.List;

public class WordEntry {
    private String word;                    // Слово на иностранном языке
    private final List<String> translations;      // Список переводов
    private int popularity;                 // Счетчик обращений (популярность)

    public WordEntry(String word) {
        this.word = word;
        this.translations = new ArrayList<>();
        this.popularity = 0;
    }

    public WordEntry(String word, List<String> translations) {
        this.word = word;
        this.translations = new ArrayList<>(translations);
        this.popularity = 0;
    }

    public void addTranslation(String translation) {
        if (!translations.contains(translation)) {
            translations.add(translation);
            System.out.println("Перевод \"" + translation + "\" добавлен для слова \"" + word + "\"");
        } else {
            System.out.println("Такой перевод уже существует");
        }
    }

    public void replaceTranslation(String oldTranslation, String newTranslation) {
        int index = translations.indexOf(oldTranslation);
        if (index != -1) {
            translations.set(index, newTranslation);
            System.out.println("Перевод \"" + oldTranslation + "\" заменен на \"" + newTranslation + "\"");
        } else {
            System.out.println("Перевод \"" + oldTranslation + "\" не найден");
        }
    }

    public void removeTranslation(String translation) {
        if (translations.remove(translation)) {
            System.out.println("Перевод \"" + translation + "\" удален");
        } else {
            System.out.println("Перевод \"" + translation + "\" не найден");
        }
    }

    public List<String> getTranslations() {
        popularity++;
        return new ArrayList<>(translations);
    }

    public void display() {
        System.out.println(word + ": " + translations);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getPopularity() {
        return popularity;
    }

    public void resetPopularity() {
        this.popularity = 0;
    }

    public boolean hasTranslation(String translation) {
        return translations.contains(translation);
    }

    public int getTranslationsCount() {
        return translations.size();
    }

    @Override
    public String toString() {
        return word + " -> " + translations + " (просмотров: " + popularity + ")";
    }
}