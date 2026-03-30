package runners;

import lesson02_inheritance.*;

public class Lesson02Runner {
    public static void main(String[] args) {
        System.out.println("========== УРОК 2: Наследование ==========\n");

        System.out.println("=== Человек и профессии ===");
        Builder builder = new Builder("Петров Петр", 35, "Мужской", "Каменщик", 15, "Кельма");
        builder.displayInfo();
        builder.work();

        System.out.println("\n=== Животные ===");
        Tiger tiger = new Tiger("Шерхан", 8, "Джунгли", "олени", "Тёмные полосы", 7.5);
        tiger.makeSound();
        tiger.hunt();

        System.out.println("\n=== Деньги и товары ===");
        Money price = new Money(1500, 50, "руб");
        Product product = new Product("Ноутбук", price, "Asus", 10);
        product.displayInfo();

        System.out.println("\n=== Устройства ===");
        Car car = new Car("Седан", "Toyota", 150, "Белый", 220, "Бензин", 4);
        car.show();
        car.sound();

        System.out.println("\n=== Музыкальные инструменты ===");
        Violin violin = new Violin("Скрипка", "Ель", 1550, 4, "Конский волос");
        violin.show();
        violin.sound();
        violin.history();
    }
}