package main;

import java.util.Scanner;
import runners.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           УЧЕБНЫЙ ПРОЕКТ ПО JAVA                           ║");
        System.out.println("║           Все задания сгруппированы по урокам              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        while (true) {
            System.out.println("Выберите урок для запуска:");
            System.out.println("1  - Урок 1: Базовые классы (Person, City, Country, Fraction)");
            System.out.println("2  - Урок 2: Наследование (Профессии, Животные, Товары, Устройства)");
            System.out.println("3  - Урок 3: Абстракция и обобщения (Фигуры, Pair)");
            System.out.println("4  - Урок 4: Коллекции ArrayList (Сумма, Поиск слов, Палиндром)");
            System.out.println("5  - Урок 5: Словарь (Англо-Русский с подсчетом популярности)");
            System.out.println("6  - Урок 6: Налоговая база данных (Штрафы)");
            System.out.println("7  - Урок 7: Лямбда-выражения (Даты, Дроби, Минимум/Максимум)");
            System.out.println("8  - Урок 8: Работа с файлами (Сравнение, Поиск, Массивы, Корпорация)");
            System.out.println("9  - Урок 9: Многопоточность (Массивы, Файлы, Копирование, Поиск)");
            System.out.println("10 - Урок 10: Stream API (Числа, Продукты, Устройства, Проекторы)");
            System.out.println("0  - Выход");
            System.out.print("\nВаш выбор: ");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.println(">>> ЗАПУСК УРОКА 1 <<<\n");
                    Lesson01Runner.main(new String[]{});
                    break;
                case 2:
                    System.out.println(">>> ЗАПУСК УРОКА 2 <<<\n");
                    Lesson02Runner.main(new String[]{});
                    break;
                case 3:
                    System.out.println(">>> ЗАПУСК УРОКА 3 <<<\n");
                    Lesson03Runner.main(new String[]{});
                    break;
                case 4:
                    System.out.println(">>> ЗАПУСК УРОКА 4 <<<\n");
                    Lesson04Runner.main(new String[]{});
                    break;
                case 5:
                    System.out.println(">>> ЗАПУСК УРОКА 5: СЛОВАРЬ <<<\n");
                    Lesson05Runner.main(new String[]{});
                    break;
                case 6:
                    System.out.println(">>> ЗАПУСК УРОКА 6: НАЛОГОВАЯ БАЗА <<<\n");
                    Lesson06Runner.main(new String[]{});
                    break;
                case 7:
                    System.out.println(">>> ЗАПУСК УРОКА 7: ЛЯМБДА-ВЫРАЖЕНИЯ <<<\n");
                    Lesson07Runner.main(new String[]{});
                    break;
                case 8:
                    System.out.println(">>> ЗАПУСК УРОКА 8: РАБОТА С ФАЙЛАМИ <<<\n");
                    Lesson08Runner.main(new String[]{});
                    break;
                case 9:
                    System.out.println(">>> ЗАПУСК УРОКА 9: МНОГОПОТОЧНОСТЬ <<<\n");
                    Lesson09Runner.main(new String[]{});
                    break;
                case 10:
                    System.out.println(">>> ЗАПУСК УРОКА 10: STREAM API <<<\n");
                    Lesson10Runner.main(new String[]{});
                    break;
                case 0:
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.\n");
            }

            System.out.println("\n" + "=".repeat(60) + "\n");
        }
    }
}