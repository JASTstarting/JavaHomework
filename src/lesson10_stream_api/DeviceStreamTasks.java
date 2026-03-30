package lesson10_stream_api;

import java.util.*;
import java.util.stream.Collectors;

public class DeviceStreamTasks {

    public static void runTasks() {
        System.out.println("\n=== ЗАДАНИЕ 3: Анализ устройств через Stream API ===\n");

        List<Device> devices = createDevicesList();

        System.out.println("📱 Всего устройств: " + devices.size());
        System.out.println("\n" + "=".repeat(85));
        System.out.printf("%-20s | %4s | %12s | %-10s | %s%n",
                "Название", "Год", "Цена", "Цвет", "Тип");
        System.out.println("=".repeat(85));
        devices.forEach(System.out::println);
        System.out.println("=".repeat(85));

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n1️⃣ ВСЕ УСТРОЙСТВА: Показаны выше (" + devices.size() + " шт.)");

        System.out.println("\n2️⃣ УСТРОЙСТВА ЗАДАННОГО ЦВЕТА:");
        System.out.print("   Введите цвет (Черный, Белый, Серебристый, Красный): ");
        String color = scanner.nextLine().trim();

        List<Device> byColor = devices.stream()
                .filter(d -> d.color().equalsIgnoreCase(color))
                .collect(Collectors.toList());

        printDevices(byColor, "цвету '" + color + "'");

        System.out.println("\n3️⃣ УСТРОЙСТВА ЗАДАННОГО ГОДА:");
        System.out.print("   Введите год: ");
        int year = getIntInput(scanner);

        List<Device> byYear = devices.stream()
                .filter(d -> d.year() == year)
                .collect(Collectors.toList());

        printDevices(byYear, "году " + year);

        System.out.println("\n4️⃣ УСТРОЙСТВА ДОРОЖЕ ЗАДАННОЙ ЦЕНЫ:");
        System.out.print("   Введите минимальную цену: ");
        double minPrice = getDoubleInput(scanner);

        List<Device> byPrice = devices.stream()
                .filter(d -> d.price() > minPrice)
                .sorted(Comparator.comparingDouble(Device::price).reversed())
                .collect(Collectors.toList());

        printDevices(byPrice, "цене > " + minPrice + " руб.");

        System.out.println("\n5️⃣ УСТРОЙСТВА ЗАДАННОГО ТИПА:");
        System.out.print("   Введите тип (Телефон, Ноутбук, Планшет, Часы): ");
        String type = scanner.nextLine().trim();

        List<Device> byType = devices.stream()
                .filter(d -> d.type().equalsIgnoreCase(type))
                .collect(Collectors.toList());

        printDevices(byType, "типу '" + type + "'");

        System.out.println("\n6️⃣ УСТРОЙСТВА В ДИАПАЗОНЕ ГОДОВ:");
        System.out.print("   Введите начальный год: ");
        int startYear = getIntInput(scanner);

        System.out.print("   Введите конечный год: ");
        int endYear = getIntInput(scanner);

        List<Device> byYearRange = devices.stream()
                .filter(d -> d.year() >= startYear && d.year() <= endYear)
                .sorted(Comparator.comparingInt(Device::year))
                .collect(Collectors.toList());

        printDevices(byYearRange, "диапазону годов " + startYear + "-" + endYear);
    }

    private static List<Device> createDevicesList() {
        return new ArrayList<>(Arrays.asList(
                new Device("iPhone 14", 2023, 89990, "Черный", "Телефон"),
                new Device("iPhone 13", 2022, 69990, "Белый", "Телефон"),
                new Device("Samsung S23", 2023, 79990, "Серебристый", "Телефон"),
                new Device("MacBook Pro", 2023, 189990, "Серебристый", "Ноутбук"),
                new Device("MacBook Air", 2022, 119990, "Серый", "Ноутбук"),
                new Device("iPad Pro", 2023, 99990, "Черный", "Планшет"),
                new Device("iPad Air", 2022, 59990, "Розовый", "Планшет"),
                new Device("Apple Watch", 2023, 45990, "Черный", "Часы"),
                new Device("Xiaomi 13", 2023, 54990, "Белый", "Телефон"),
                new Device("Huawei P60", 2023, 64990, "Черный", "Телефон"),
                new Device("Asus ROG", 2022, 149990, "Черный", "Ноутбук"),
                new Device("Lenovo ThinkPad", 2021, 89990, "Черный", "Ноутбук"),
                new Device("Samsung Tab", 2022, 44990, "Серый", "Планшет"),
                new Device("Garmin Fenix", 2023, 69990, "Черный", "Часы"),
                new Device("OnePlus 11", 2023, 59990, "Зеленый", "Телефон")
        ));
    }

    private static void printDevices(List<Device> devices, String criteria) {
        if (devices.isEmpty()) {
            System.out.println("   ❌ Нет устройств по " + criteria);
        } else {
            System.out.println("   ✅ Найдено " + devices.size() + " устройств по " + criteria + ":");
            System.out.println("   " + "=".repeat(80));
            System.out.printf("   %-20s | %4s | %12s | %-10s | %s%n",
                    "Название", "Год", "Цена", "Цвет", "Тип");
            System.out.println("   " + "=".repeat(80));
            devices.forEach(d -> System.out.println("   " + d));
            System.out.println("   " + "=".repeat(80));
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("   ❌ Введите число: ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
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