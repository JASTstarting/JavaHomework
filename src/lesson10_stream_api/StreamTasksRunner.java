package lesson10_stream_api;

import java.util.Scanner;

public class StreamTasksRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    STREAM API                              ║");
        System.out.println("║              Демонстрация всех заданий                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        System.out.println("\n⚠️  Для решения всех задач используется Stream API!");

        while (true) {
            showMenu();
            int choice = getIntInput(scanner);
            System.out.println();

            switch (choice) {
                case 1:
                    NumberStreamTasks.runTasks();
                    break;
                case 2:
                    ProductStreamTasks.runTasks();
                    break;
                case 3:
                    DeviceStreamTasks.runTasks();
                    break;
                case 4:
                    ProjectorStreamTasks.runTasks();
                    break;
                case 0:
                    System.out.println("\n👋 До свидания!");
                    return;
                default:
                    System.out.println("❌ Неверный выбор. Попробуйте снова.");
            }

            System.out.println("\n" + "=".repeat(70) + "\n");
        }
    }

    private static void showMenu() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                      МЕНЮ ЗАДАНИЙ (STREAM API)                  │");
        System.out.println("├─────────────────────────────────────────────────────────────────┤");
        System.out.println("│ 1  - Задание 1: Анализ чисел                                    │");
        System.out.println("│      (положительные, отрицательные, двузначные, зеркальные)     │");
        System.out.println("│                                                                 │");
        System.out.println("│ 2  - Задание 2: Анализ продуктов                                │");
        System.out.println("│      (фильтрация, поиск, группировка)                           │");
        System.out.println("│                                                                 │");
        System.out.println("│ 3  - Задание 3: Устройства                                      │");
        System.out.println("│      (фильтрация по различным критериям)                        │");
        System.out.println("│                                                                 │");
        System.out.println("│ 4  - Задание 4: Проекторы                                       │");
        System.out.println("│      (фильтрация и сортировка)                                  │");
        System.out.println("├─────────────────────────────────────────────────────────────────┤");
        System.out.println("│ 0  - Выход                                                      │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
    }

    private static int getIntInput(Scanner scanner) {
        System.out.print("Выберите задание: ");
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Введите число: ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }
}