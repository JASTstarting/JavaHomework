package lesson06_taxDatabase;

import java.time.LocalDate;
import java.util.Scanner;

public class TaxDatabaseRunner {
    private static TaxDatabase database;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        database = new TaxDatabase();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         БАЗА ДАННЫХ НАЛОГОВОЙ ИНСПЕКЦИИ (ШТРАФЫ)           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        database.initializeTestData();

        while (true) {
            showMenu();
            int choice = getIntInput("Выберите действие: ");
            System.out.println();

            switch (choice) {
                case 1:
                    database.printAll();
                    break;
                case 2:
                    printByTaxId();
                    break;
                case 3:
                    printByFineType();
                    break;
                case 4:
                    printByCity();
                    break;
                case 5:
                    addPerson();
                    break;
                case 6:
                    addFineToPerson();
                    break;
                case 7:
                    removeFine();
                    break;
                case 8:
                    updatePerson();
                    break;
                case 9:
                    database.printSummary();
                    break;
                case 0:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
            System.out.println("\n" + "=".repeat(70) + "\n");
        }
    }

    private static void showMenu() {
        System.out.println("\n--- МЕНЮ НАЛОГОВОЙ БАЗЫ ---");
        System.out.println("1. Полная распечатка базы данных");
        System.out.println("2. Распечатка по ИНН");
        System.out.println("3. Распечатка по типу штрафа");
        System.out.println("4. Распечатка по городу");
        System.out.println("5. Добавить нового человека");
        System.out.println("6. Добавить штраф существующему человеку");
        System.out.println("7. Удалить штраф");
        System.out.println("8. Заменить информацию о человеке");
        System.out.println("9. Краткая сводка");
        System.out.println("0. Выход");
    }

    private static void printByTaxId() {
        String taxId = getStringInput("Введите ИНН (12 цифр): ");
        database.printByTaxId(taxId);
    }

    private static void printByFineType() {
        System.out.println("Доступные типы штрафов: Превышение скорости, Неправильная парковка, Проезд на красный, Отсутствие техосмотра");
        String type = getStringInput("Введите тип штрафа: ");
        database.printByFineType(type);
    }

    private static void printByCity() {
        System.out.println("Доступные города: Москва, Санкт-Петербург, Казань");
        String city = getStringInput("Введите город: ");
        database.printByCity(city);
    }

    private static void addPerson() {
        System.out.println("\n--- Добавление нового человека ---");
        String taxId = getStringInput("Введите ИНН (12 цифр): ");

        if (database.getPerson(taxId) != null) {
            System.out.println("Человек с таким ИНН уже существует!");
            return;
        }

        String fullName = getStringInput("Введите ФИО: ");
        String address = getStringInput("Введите адрес: ");
        String phone = getStringInput("Введите телефон: ");

        Person newPerson = new Person(taxId, fullName, address, phone);
        database.addPerson(newPerson);

        String addFines = getStringInput("Добавить штрафы? (да/нет): ");
        if (addFines.equalsIgnoreCase("да")) {
            addFinesToNewPerson(newPerson);
        }
    }

    private static void addFinesToNewPerson(Person person) {
        while (true) {
            System.out.println("\n--- Добавление штрафа ---");
            String type = getStringInput("Тип штрафа: ");
            double amount = getDoubleInput();
            LocalDate date = LocalDate.now();
            String city = getStringInput("Город: ");
            String description = getStringInput("Описание: ");

            Fine fine = new Fine(type, amount, date, city, description);
            person.addFine(fine);

            String more = getStringInput("Добавить еще штраф? (да/нет): ");
            if (!more.equalsIgnoreCase("да")) {
                break;
            }
        }
    }

    private static void addFineToPerson() {
        String taxId = getStringInput("Введите ИНН человека: ");
        Person person = database.getPerson(taxId);

        if (person == null) {
            System.out.println("Человек не найден!");
            return;
        }

        System.out.println("\n--- Добавление штрафа для " + person.getFullName() + " ---");
        String type = getStringInput("Тип штрафа: ");
        double amount = getDoubleInput();
        LocalDate date = LocalDate.now();
        String city = getStringInput("Город: ");
        String description = getStringInput("Описание: ");

        Fine fine = new Fine(type, amount, date, city, description);
        database.addFineToPerson(taxId, fine);
    }

    private static void removeFine() {
        String taxId = getStringInput("Введите ИНН человека: ");
        Person person = database.getPerson(taxId);

        if (person == null) {
            System.out.println("Человек не найден!");
            return;
        }

        person.display();
        int fineId = getIntInput("Введите ID штрафа для удаления: ");
        database.removeFine(taxId, fineId);
    }

    private static void updatePerson() {
        String taxId = getStringInput("Введите ИНН человека: ");
        Person person = database.getPerson(taxId);

        if (person == null) {
            System.out.println("Человек не найден!");
            return;
        }

        System.out.println("\nТекущая информация:");
        person.displayShort();

        System.out.println("\nВведите новые данные (оставьте пустым, если не хотите менять):");
        String newName = getStringInput("Новое ФИО (" + person.getFullName() + "): ");
        if (newName.isEmpty()) newName = person.getFullName();

        String newAddress = getStringInput("Новый адрес (" + person.getAddress() + "): ");
        if (newAddress.isEmpty()) newAddress = person.getAddress();

        String newPhone = getStringInput("Новый телефон (" + person.getPhone() + "): ");
        if (newPhone.isEmpty()) newPhone = person.getPhone();

        database.updatePerson(taxId, newName, newAddress, newPhone);
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Введите число: ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    private static double getDoubleInput() {
        System.out.print("Сумма штрафа: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Введите число: ");
            scanner.next();
        }
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }
}