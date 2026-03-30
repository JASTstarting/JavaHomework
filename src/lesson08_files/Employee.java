package lesson08_files;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private String position;
    private double salary;
    private String department;

    private static int nextId = 1;

    public Employee(String lastName, String firstName, String middleName,
                    LocalDate birthDate, String position, double salary, String department) {
        this.id = nextId++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.position = position;
        this.salary = salary;
        this.department = department;
    }

    public Employee(int id, String lastName, String firstName, String middleName,
                    LocalDate birthDate, String position, double salary, String department) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.position = position;
        this.salary = salary;
        this.department = department;
        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public int getId() { return id; }

    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return String.format("ID: %d | %s | %s | %s | %.2f руб. | %s | %d лет",
                id, getFullName(), birthDate.format(formatter), position, salary, department, getAge());
    }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return id + ";" + lastName + ";" + firstName + ";" + middleName + ";" +
                birthDate.format(formatter) + ";" + position + ";" + salary + ";" + department;
    }

    public static Employee fromFileString(String line) {
        String[] parts = line.split(";");
        if (parts.length != 8) {
            throw new IllegalArgumentException("Неверный формат строки: " + line);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            return new Employee(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    parts[2],
                    parts[3],
                    LocalDate.parse(parts[4], formatter),
                    parts[5],
                    Double.parseDouble(parts[6]),
                    parts[7]
            );
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Ошибка парсинга даты: " + parts[4]);
        }
    }
}