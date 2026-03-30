package lesson10_stream_api;

import java.util.*;

public class ProductStreamTasks {

    public static void runTasks() {
        System.out.println("\n=== ЗАДАНИЕ 2: Анализ продуктов через Stream API ===\n");

        List<String> products = createProductsList();

        System.out.println("📦 Всего продуктов: " + products.size());
        System.out.println("Список продуктов: " + products);
        System.out.println("\n" + "=".repeat(60));

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n1️⃣ ВСЕ УНИКАЛЬНЫЕ ПРОДУКТЫ:");
        products.stream()
                .distinct()
                .sorted()
                .forEach(product -> System.out.println("   • " + product));

        System.out.println("\n2️⃣ ПРОДУКТЫ С НАЗВАНИЕМ МЕНЬШЕ 5 СИМВОЛОВ:");
        List<String> shortNames = products.stream()
                .distinct()
                .filter(p -> p.length() < 5)
                .sorted()
                .toList();

        if (shortNames.isEmpty()) {
            System.out.println("   Нет продуктов с названием меньше 5 символов");
        } else {
            shortNames.forEach(p -> System.out.println("   • " + p + " (" + p.length() + " симв.)"));
        }

        System.out.println("\n3️⃣ ПОДСЧЕТ ПОВТОРЕНИЙ ПРОДУКТА:");
        System.out.print("   Введите название продукта: ");
        String userInput = scanner.nextLine().trim();

        long count = products.stream()
                .filter(p -> p.equalsIgnoreCase(userInput))
                .count();

        if (count > 0) {
            System.out.println("   ✅ Продукт \"" + userInput + "\" встречается " + count + " раз(а)");
        } else {
            System.out.println("   ❌ Продукт \"" + userInput + "\" не найден");
        }

        System.out.println("\n4️⃣ ПРОДУКТЫ НА ЗАДАННУЮ БУКВУ:");
        System.out.print("   Введите букву: ");
        String letterInput = scanner.nextLine().trim();

        if (letterInput.isEmpty()) {
            System.out.println("   ❌ Буква не введена");
        } else {
            char letter = Character.toUpperCase(letterInput.charAt(0));
            List<String> startsWithLetter = products.stream()
                    .distinct()
                    .filter(p -> Character.toUpperCase(p.charAt(0)) == letter)
                    .sorted()
                    .toList();

            if (startsWithLetter.isEmpty()) {
                System.out.println("   ❌ Нет продуктов на букву '" + letter + "'");
            } else {
                System.out.println("   ✅ Продукты на букву '" + letter + "':");
                startsWithLetter.forEach(p -> System.out.println("      • " + p));
            }
        }

        System.out.println("\n5️⃣ ПРОДУКТЫ ИЗ КАТЕГОРИИ «МОЛОКО»:");
        List<String> milkProducts = products.stream()
                .distinct()
                .filter(p -> p.toLowerCase().contains("молоко"))
                .sorted()
                .toList();

        if (milkProducts.isEmpty()) {
            System.out.println("   ❌ Нет продуктов с молоком");
        } else {
            System.out.println("   ✅ Найдено " + milkProducts.size() + " продуктов:");
            milkProducts.forEach(p -> System.out.println("      • " + p));
        }
    }

    private static List<String> createProductsList() {
        return new ArrayList<>(Arrays.asList(
                "Молоко", "Хлеб", "Сыр", "Молоко", "Яблоко",
                "Масло", "Молоко", "Кефир", "Сметана", "Хлеб",
                "Творог", "Молоко", "Йогурт", "Сыр", "Ряженка",
                "Кефир", "Молоко", "Сливки", "Хлеб", "Сыр",
                "Молоко", "Простокваша", "Творог", "Масло", "Кефир"
        ));
    }
}