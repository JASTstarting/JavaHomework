package runners;

import lesson01_basics.*;

public class Lesson01Runner {
    public static void main(String[] args) {
        System.out.println("========== УРОК 1: Базовые классы ==========\n");

        System.out.println("=== Person ===");
        Person person = new Person();
        person.inputData("Иванов Иван Иванович", "15.05.1990", "+7-999-123-4567",
                "Москва", "Россия", "ул. Ленина, д. 10, кв. 5");
        person.displayData();

        System.out.println("\n=== City ===");
        City city = new City();
        city.inputData("Москва", "Московская область", "Россия",
                12500000, "101000", "495");
        city.displayData();

        System.out.println("\n=== Country ===");
        Country country = new Country();
        String[] cities = {"Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург"};
        country.inputData("Россия", "Евразия", 146000000, "+7", "Москва", cities);
        country.displayData();

        System.out.println("\n=== Fraction ===");
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);

        System.out.print("1/2 + 1/3 = ");
        f1.add(f2).displayData();
        System.out.print("1/2 * 1/3 = ");
        f1.multiply(f2).displayData();
    }
}