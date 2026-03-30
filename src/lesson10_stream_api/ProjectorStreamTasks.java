package lesson10_stream_api;

import java.util.*;
import java.util.stream.Collectors;

public class ProjectorStreamTasks {

    public static void runTasks() {
        System.out.println("\n=== ЗАДАНИЕ 4: Анализ проекторов через Stream API ===\n");

        List<Projector> projectors = createProjectorsList();

        System.out.println("📽️ Всего проекторов: " + projectors.size());
        System.out.println("\n" + "=".repeat(75));
        System.out.printf("%-25s | %4s | %12s | %s%n",
                "Название", "Год", "Цена", "Производитель");
        System.out.println("=".repeat(75));
        projectors.forEach(System.out::println);
        System.out.println("=".repeat(75));

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n1️⃣ ВСЕ ПРОЕКТОРЫ: Показаны выше (" + projectors.size() + " шт.)");

        System.out.println("\n2️⃣ ПРОЕКТОРЫ ОДНОГО ПРОИЗВОДИТЕЛЯ:");
        System.out.print("   Введите производителя (Epson, Sony, LG, BenQ): ");
        String manufacturer = scanner.nextLine().trim();

        List<Projector> byManufacturer = projectors.stream()
                .filter(p -> p.manufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());

        printProjectors(byManufacturer, "производителю '" + manufacturer + "'");

        System.out.println("\n3️⃣ ПРОЕКТОРЫ ТЕКУЩЕГО ГОДА:");
        int currentYear = java.time.Year.now().getValue();

        List<Projector> currentYearProjectors = projectors.stream()
                .filter(p -> p.year() == currentYear)
                .collect(Collectors.toList());

        printProjectors(currentYearProjectors, "текущему году (" + currentYear + ")");

        System.out.println("\n4️⃣ ПРОЕКТОРЫ ДОРОЖЕ ЗАДАННОЙ ЦЕНЫ:");
        System.out.print("   Введите минимальную цену: ");
        double minPrice = getDoubleInput(scanner);

        List<Projector> byPrice = projectors.stream()
                .filter(p -> p.price() > minPrice)
                .collect(Collectors.toList());

        printProjectors(byPrice, "цене > " + minPrice + " руб.");

        System.out.println("\n5️⃣ ПРОЕКТОРЫ, СОРТИРОВАННЫЕ ПО ЦЕНЕ (ВОЗРАСТАНИЕ):");
        List<Projector> sortedByPriceAsc = projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::price))
                .collect(Collectors.toList());
        printProjectors(sortedByPriceAsc, "цене (возрастание)");

        System.out.println("\n6️⃣ ПРОЕКТОРЫ, СОРТИРОВАННЫЕ ПО ЦЕНЕ (УБЫВАНИЕ):");
        List<Projector> sortedByPriceDesc = projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::price).reversed())
                .collect(Collectors.toList());
        printProjectors(sortedByPriceDesc, "цене (убывание)");

        System.out.println("\n7️⃣ ПРОЕКТОРЫ, СОРТИРОВАННЫЕ ПО ГОДУ (ВОЗРАСТАНИЕ):");
        List<Projector> sortedByYearAsc = projectors.stream()
                .sorted(Comparator.comparingInt(Projector::year))
                .collect(Collectors.toList());
        printProjectors(sortedByYearAsc, "году (возрастание)");

        System.out.println("\n8️⃣ ПРОЕКТОРЫ, СОРТИРОВАННЫЕ ПО ГОДУ (УБЫВАНИЕ):");
        List<Projector> sortedByYearDesc = projectors.stream()
                .sorted(Comparator.comparingInt(Projector::year).reversed())
                .collect(Collectors.toList());
        printProjectors(sortedByYearDesc, "году (убывание)");
    }

    private static List<Projector> createProjectorsList() {
        int currentYear = java.time.Year.now().getValue();

        return new ArrayList<>(Arrays.asList(
                new Projector("Epson EH-TW7100", currentYear, 149990, "Epson"),
                new Projector("Epson EH-TW7000", currentYear - 1, 119990, "Epson"),
                new Projector("Sony VPL-VW295ES", currentYear, 349990, "Sony"),
                new Projector("Sony VPL-HW45ES", currentYear - 1, 179990, "Sony"),
                new Projector("LG CineBeam HU810P", currentYear, 199990, "LG"),
                new Projector("LG CineBeam HU715Q", currentYear - 1, 129990, "LG"),
                new Projector("BenQ W2700", currentYear, 109990, "BenQ"),
                new Projector("BenQ W1700M", currentYear - 2, 89990, "BenQ"),
                new Projector("Xiaomi Mi Smart", currentYear, 59990, "Xiaomi"),
                new Projector("XGIMI Horizon Pro", currentYear, 89990, "XGIMI"),
                new Projector("Epson CO-FH02", currentYear - 1, 49990, "Epson"),
                new Projector("BenQ TH685", currentYear, 69990, "BenQ")
        ));
    }

    private static void printProjectors(List<Projector> projectors, String criteria) {
        if (projectors.isEmpty()) {
            System.out.println("   ❌ Нет проекторов по " + criteria);
        } else {
            System.out.println("   ✅ Найдено " + projectors.size() + " проекторов по " + criteria + ":");
            System.out.println("   " + "=".repeat(70));
            System.out.printf("   %-25s | %4s | %12s | %s%n",
                    "Название", "Год", "Цена", "Производитель");
            System.out.println("   " + "=".repeat(70));
            projectors.forEach(p -> System.out.println("   " + p));
            System.out.println("   " + "=".repeat(70));
        }
    }

    private static double getDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("   ❌ Введите число: ");
            scanner.next();
        }
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }
}