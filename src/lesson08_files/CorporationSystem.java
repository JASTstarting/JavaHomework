package lesson08_files;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class CorporationSystem {
    private final List<Employee> employees;
    private String dataFile;
    private final Scanner scanner;
    private boolean dataChanged;

    public CorporationSystem() {
        this.employees = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dataChanged = false;
    }

    public void start() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("  ║        ИНФОРМАЦИОННАЯ СИСТЕМА «КОРПОРАЦИЯ»                 ║");
        System.out.println("  ╚════════════════════════════════════════════════════════════╝");

        loadDataAutomatically();

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("Выберите действие: ");
            System.out.println();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    editEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    searchByLastName();
                    break;
                case 5:
                    showEmployeesByAge();
                    break;
                case 6:
                    showEmployeesByLastNameStart();
                    break;
                case 7:
                    showAllEmployees();
                    break;
                case 8:
                    saveToFile();
                    break;
                case 9:
                    changeDataFile();
                    break;
                case 10:
                    showStatistics();
                    break;
                case 0:
                    if (dataChanged) {
                        System.out.print("Есть несохраненные изменения. Сохранить? (да/нет): ");
                        String save = scanner.nextLine();
                        if (save.equalsIgnoreCase("да")) {
                            saveToFile();
                        }
                    }
                    System.out.println("До свидания!");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void loadDataAutomatically() {
        // Сначала проверяем тестовый файл
        String testFile = TestFilesCreator.getTestFilePath("employees.txt");
        File file = new File(testFile);

        if (file.exists()) {
            System.out.println("\nНайден тестовый файл: " + testFile);
            System.out.print("Загрузить данные из этого файла? (да/нет/свой): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("да")) {
                this.dataFile = testFile;
                loadFromFile();
                return;
            } else if (answer.equalsIgnoreCase("свой")) {
                System.out.print("Введите путь к файлу: ");
                this.dataFile = scanner.nextLine();
                loadFromFile();
                return;
            }
        }

        System.out.print("Введите путь к файлу с данными (или Enter для создания новой базы): ");
        String filePath = scanner.nextLine();

        if (filePath.trim().isEmpty()) {
            System.out.println("✓ Создана новая база данных сотрудников");
            // Добавляем тестового сотрудника для демонстрации
            addSampleEmployee();
        } else {
            this.dataFile = filePath;
            loadFromFile();
        }
    }

    private void addSampleEmployee() {
        Employee sample = new Employee(
                "Иванов", "Иван", "Иванович",
                LocalDate.of(1990, 1, 15),
                "Сотрудник", 50000, "Общий"
        );
        employees.add(sample);
        dataChanged = true;
        System.out.println("✓ Добавлен пример сотрудника для демонстрации");
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            employees.clear();
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (!line.trim().isEmpty()) {
                    try {
                        employees.add(Employee.fromFileString(line));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Ошибка в строке " + lineNumber + ": " + e.getMessage());
                    }
                }
            }
            System.out.println("✓ Загружено " + employees.size() + " сотрудников из файла: " + dataFile);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Будет создана новая база данных.");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private void saveToFile() {
        if (dataFile == null || dataFile.isEmpty()) {
            changeDataFile();
        }

        if (dataFile == null) {
            System.out.println("Файл не выбран. Данные не сохранены.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            for (Employee emp : employees) {
                writer.write(emp.toFileString());
                writer.newLine();
            }
            System.out.println("✓ Данные сохранены в файл: " + dataFile);
            System.out.println("  Сохранено " + employees.size() + " записей");
            dataChanged = false;
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    private void changeDataFile() {
        System.out.print("Введите путь к файлу для сохранения/загрузки: ");
        String newFile = scanner.nextLine();
        if (!newFile.trim().isEmpty()) {
            this.dataFile = newFile;
            System.out.println("Файл данных изменен на: " + dataFile);
        }
    }

    private void showMenu() {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│                      МЕНЮ СИСТЕМЫ                        │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ 1. Добавить сотрудника                                  │");
        System.out.println("│ 2. Редактировать сотрудника                             │");
        System.out.println("│ 3. Удалить сотрудника                                   │");
        System.out.println("│ 4. Поиск сотрудника по фамилии                          │");
        System.out.println("│ 5. Показать сотрудников указанного возраста             │");
        System.out.println("│ 6. Показать сотрудников по первой букве фамилии         │");
        System.out.println("│ 7. Показать всех сотрудников                            │");
        System.out.println("│ 8. Сохранить данные в файл                              │");
        System.out.println("│ 9. Сменить файл данных                                  │");
        System.out.println("│10. Статистика системы                                   │");
        System.out.println("│ 0. Выход                                                │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("\nТекущий файл: " + (dataFile == null ? "не выбран" : dataFile));
        if (dataChanged) {
            System.out.println("⚠ Есть несохраненные изменения!");
        }
        System.out.println("Всего сотрудников: " + employees.size());
    }

    private void showStatistics() {
        if (employees.isEmpty()) {
            System.out.println("Нет данных для статистики");
            return;
        }

        double totalSalary = employees.stream().mapToDouble(Employee::getSalary).sum();
        double avgSalary = totalSalary / employees.size();
        double maxSalary = employees.stream().mapToDouble(Employee::getSalary).max().orElse(0);
        double minSalary = employees.stream().mapToDouble(Employee::getSalary).min().orElse(0);

        Map<String, Long> departmentStats = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        System.out.println("\n--- СТАТИСТИКА СИСТЕМЫ ---");
        System.out.println("Всего сотрудников: " + employees.size());
        System.out.printf("Общая сумма зарплат: %.2f руб.\n", totalSalary);
        System.out.printf("Средняя зарплата: %.2f руб.\n", avgSalary);
        System.out.printf("Максимальная зарплата: %.2f руб.\n", maxSalary);
        System.out.printf("Минимальная зарплата: %.2f руб.\n", minSalary);
        System.out.println("\nРаспределение по отделам:");
        departmentStats.forEach((dept, count) ->
                System.out.printf("  %s: %d сотрудников\n", dept, count));
    }

    private void addEmployee() {
        System.out.println("\n--- Добавление нового сотрудника ---");

        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();

        System.out.print("Имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Отчество: ");
        String middleName = scanner.nextLine();

        LocalDate birthDate = getDateInput();

        System.out.print("Должность: ");
        String position = scanner.nextLine();

        double salary = getDoubleInput();

        System.out.print("Отдел: ");
        String department = scanner.nextLine();

        Employee employee = new Employee(lastName, firstName, middleName,
                birthDate, position, salary, department);
        employees.add(employee);
        dataChanged = true;

        System.out.println("✓ Сотрудник добавлен! ID: " + employee.getId());
    }

    private void editEmployee() {
        if (employees.isEmpty()) {
            System.out.println("Нет сотрудников для редактирования");
            return;
        }

        System.out.println("\n--- Редактирование сотрудника ---");
        int id = getIntInput("Введите ID сотрудника: ");

        Employee employee = findById(id);
        if (employee == null) {
            System.out.println("Сотрудник с ID " + id + " не найден");
            return;
        }

        System.out.println("\nТекущая информация:");
        System.out.println(employee);
        System.out.println("\nВведите новые данные (оставьте пустым, если не хотите менять):");

        System.out.print("Фамилия (" + employee.getLastName() + "): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) employee.setLastName(lastName);

        System.out.print("Имя (" + employee.getFirstName() + "): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) employee.setFirstName(firstName);

        System.out.print("Отчество (" + employee.getMiddleName() + "): ");
        String middleName = scanner.nextLine();
        if (!middleName.isEmpty()) employee.setMiddleName(middleName);

        System.out.print("Дата рождения (" + employee.getBirthDate() + "): ");
        String dateStr = scanner.nextLine();
        if (!dateStr.isEmpty()) {
            LocalDate newDate = parseDate(dateStr);
            if (newDate != null) employee.setBirthDate(newDate);
        }

        System.out.print("Должность (" + employee.getPosition() + "): ");
        String position = scanner.nextLine();
        if (!position.isEmpty()) employee.setPosition(position);

        System.out.print("Оклад (" + employee.getSalary() + "): ");
        String salaryStr = scanner.nextLine();
        if (!salaryStr.isEmpty()) {
            try {
                employee.setSalary(Double.parseDouble(salaryStr));
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод, оклад не изменен");
            }
        }

        System.out.print("Отдел (" + employee.getDepartment() + "): ");
        String department = scanner.nextLine();
        if (!department.isEmpty()) employee.setDepartment(department);

        dataChanged = true;
        System.out.println("✓ Данные сотрудника обновлены");
    }

    private void deleteEmployee() {
        if (employees.isEmpty()) {
            System.out.println("Нет сотрудников для удаления");
            return;
        }

        System.out.println("\n--- Удаление сотрудника ---");
        int id = getIntInput("Введите ID сотрудника: ");

        Employee employee = findById(id);
        if (employee == null) {
            System.out.println("Сотрудник с ID " + id + " не найден");
            return;
        }

        System.out.println("Удаляемый сотрудник:");
        System.out.println(employee);

        String confirm = getStringInput("Вы уверены? (да/нет): ");
        if (confirm.equalsIgnoreCase("да")) {
            employees.remove(employee);
            dataChanged = true;
            System.out.println("✓ Сотрудник удален");
        } else {
            System.out.println("Удаление отменено");
        }
    }

    private void searchByLastName() {
        if (employees.isEmpty()) {
            System.out.println("Нет сотрудников для поиска");
            return;
        }

        System.out.println("\n--- Поиск по фамилии ---");
        String lastName = getStringInput("Введите фамилию: ");

        List<Employee> found = employees.stream()
                .filter(e -> e.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());

        displaySearchResults(found, "фамилией \"" + lastName + "\"");
    }

    private void showEmployeesByAge() {
        if (employees.isEmpty()) {
            System.out.println("Нет сотрудников для отображения");
            return;
        }

        System.out.println("\n--- Поиск по возрасту ---");
        int age = getIntInput("Введите возраст: ");

        List<Employee> found = employees.stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());

        displaySearchResults(found, "возрастом " + age + " лет");
    }

    private void showEmployeesByLastNameStart() {
        if (employees.isEmpty()) {
            System.out.println("Нет сотрудников для отображения");
            return;
        }

        System.out.println("\n--- Поиск по первой букве фамилии ---");
        String letterStr = getStringInput("Введите букву: ");
        char letter = !letterStr.isEmpty() ? letterStr.charAt(0) : ' ';

        List<Employee> found = employees.stream()
                .filter(e -> !e.getLastName().isEmpty() &&
                        Character.toUpperCase(e.getLastName().charAt(0)) == Character.toUpperCase(letter))
                .collect(Collectors.toList());

        displaySearchResults(found, "фамилией на букву '" + letter + "'");
    }

    private void displaySearchResults(List<Employee> found, String criteria) {
        if (found.isEmpty()) {
            System.out.println("Сотрудники с " + criteria + " не найдены");
        } else {
            System.out.println("\nНайдено " + found.size() + " сотрудников с " + criteria + ":");
            System.out.println("=".repeat(70));
            for (Employee emp : found) {
                System.out.println(emp);
            }
            System.out.println("=".repeat(70));

            saveSearchResultsToFile(found);
        }
    }

    private void showAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Нет сотрудников в базе данных");
            return;
        }

        System.out.println("\n--- ВСЕ СОТРУДНИКИ (" + employees.size() + ") ---");
        System.out.println("=".repeat(70));
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        System.out.println("=".repeat(70));
    }

    private void saveSearchResultsToFile(List<Employee> results) {
        String save = getStringInput("\nСохранить результаты в файл? (да/нет): ");
        if (save.equalsIgnoreCase("да")) {
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("=== РЕЗУЛЬТАТЫ ПОИСКА ===");
                writer.newLine();
                writer.write("Дата: " + LocalDate.now());
                writer.newLine();
                writer.write("=".repeat(50));
                writer.newLine();
                writer.newLine();

                for (Employee emp : results) {
                    writer.write(emp.toString());
                    writer.newLine();
                }

                System.out.println("✓ Результаты сохранены в файл: " + fileName);
            } catch (IOException e) {
                System.err.println("Ошибка при сохранении: " + e.getMessage());
            }
        }
    }

    private Employee findById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private LocalDate getDateInput() {
        while (true) {
            System.out.print("Дата рождения (дд.мм.гггг): ");
            String dateStr = scanner.nextLine();
            LocalDate date = parseDate(dateStr);
            if (date != null) {
                return date;
            }
            System.out.println("Неверный формат даты. Используйте дд.мм.гггг");
        }
    }

    private LocalDate parseDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число");
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            System.out.print("Оклад: ");
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}