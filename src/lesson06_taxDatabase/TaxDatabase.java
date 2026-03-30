package lesson06_taxDatabase;

import java.time.LocalDate;
import java.util.*;

public class TaxDatabase {
    private final Map<String, Person> database;
    private final Map<String, List<Person>> finesByType;
    private final Map<String, List<Person>> finesByCity;

    public TaxDatabase() {
        this.database = new HashMap<>();
        this.finesByType = new HashMap<>();
        this.finesByCity = new HashMap<>();
    }

    public void initializeTestData() {
        System.out.println("\n--- Загрузка тестовых данных ---");

        Person p1 = new Person("123456789012", "Иванов Иван Иванович",
                "г. Москва, ул. Ленина, д. 1", "+7-999-123-4567");
        Person p2 = new Person("234567890123", "Петрова Мария Сергеевна",
                "г. Санкт-Петербург, Невский пр., д. 10", "+7-912-345-6789");
        Person p3 = new Person("345678901234", "Сидоров Алексей Петрович",
                "г. Москва, ул. Тверская, д. 5", "+7-916-789-0123");
        Person p4 = new Person("456789012345", "Козлова Елена Владимировна",
                "г. Казань, ул. Баумана, д. 15", "+7-927-345-6789");

        p1.addFine(new Fine("Превышение скорости", 500, LocalDate.of(2023, 1, 15), "Москва", "Превышение на 25 км/ч"));
        p1.addFine(new Fine("Неправильная парковка", 3000, LocalDate.of(2023, 2, 20), "Москва", "Парковка на тротуаре"));

        p2.addFine(new Fine("Проезд на красный", 1000, LocalDate.of(2023, 3, 10), "Санкт-Петербург", "Проезд на запрещающий сигнал"));
        p2.addFine(new Fine("Превышение скорости", 800, LocalDate.of(2023, 4, 5), "Санкт-Петербург", "Превышение на 40 км/ч"));

        p3.addFine(new Fine("Неправильная парковка", 2500, LocalDate.of(2023, 5, 12), "Москва", "Парковка в неположенном месте"));
        p3.addFine(new Fine("Отсутствие техосмотра", 800, LocalDate.of(2023, 6, 18), "Москва", "Просрочен техосмотр"));

        p4.addFine(new Fine("Превышение скорости", 1500, LocalDate.of(2023, 7, 22), "Казань", "Превышение на 60 км/ч"));

        addPerson(p1);
        addPerson(p2);
        addPerson(p3);
        addPerson(p4);

        System.out.println("Загружено " + database.size() + " записей");
    }

    public void addPerson(Person person) {
        database.put(person.getTaxId(), person);
        updateIndexes(person);
        System.out.println("Человек добавлен: " + person.getFullName());
    }

    private void updateIndexes(Person person) {
        for (Fine fine : person.getFines()) {
            // Индекс по типу штрафа
            finesByType.computeIfAbsent(fine.getType(), _ -> new ArrayList<>())
                    .add(person);
            // Индекс по городу
            finesByCity.computeIfAbsent(fine.getCity(), _ -> new ArrayList<>())
                    .add(person);
        }
    }

    public Person getPerson(String taxId) {
        return database.get(taxId);
    }

    public void printAll() {
        System.out.println("\n=== ПОЛНАЯ БАЗА ДАННЫХ НАЛОГОВОЙ ИНСПЕКЦИИ ===");
        System.out.println("Всего записей: " + database.size());
        System.out.println("=".repeat(70));

        for (Person person : database.values()) {
            person.display();
            System.out.println();
        }
    }

    public void printByTaxId(String taxId) {
        Person person = database.get(taxId);
        if (person != null) {
            System.out.println("\n=== ИНФОРМАЦИЯ ПО ИНН: " + taxId + " ===");
            person.display();
        } else {
            System.out.println("Человек с ИНН " + taxId + " не найден");
        }
    }

    public void printByFineType(String type) {
        List<Person> persons = finesByType.get(type);
        System.out.println("\n=== ШТРАФЫ ТИПА: " + type.toUpperCase() + " ===");

        if (persons == null || persons.isEmpty()) {
            System.out.println("Штрафы такого типа не найдены");
            return;
        }

        for (Person person : persons) {
            List<Fine> fines = person.getFinesByType(type);
            if (!fines.isEmpty()) {
                System.out.println("\n" + person.getFullName() + " (" + person.getTaxId() + "):");
                for (Fine fine : fines) {
                    fine.display();
                }
            }
        }
    }

    public void printByCity(String city) {
        List<Person> persons = finesByCity.get(city);
        System.out.println("\n=== ШТРАФЫ В ГОРОДЕ: " + city.toUpperCase() + " ===");

        if (persons == null || persons.isEmpty()) {
            System.out.println("Штрафы в этом городе не найдены");
            return;
        }

        Set<Person> uniquePersons = new HashSet<>(persons);
        for (Person person : uniquePersons) {
            List<Fine> fines = person.getFinesByCity(city);
            if (!fines.isEmpty()) {
                System.out.println("\n" + person.getFullName() + " (" + person.getTaxId() + "):");
                for (Fine fine : fines) {
                    fine.display();
                }
            }
        }
    }

    public void addFineToPerson(String taxId, Fine fine) {
        Person person = database.get(taxId);
        if (person != null) {
            person.addFine(fine);
            updateIndexes(person);
        } else {
            System.out.println("Человек с ИНН " + taxId + " не найден");
        }
    }

    public void removeFine(String taxId, int fineId) {
        Person person = database.get(taxId);
        if (person != null) {
            person.removeFine(fineId);
        } else {
            System.out.println("Человек с ИНН " + taxId + " не найден");
        }
    }

    public void updatePerson(String taxId, String newFullName, String newAddress, String newPhone) {
        Person person = database.get(taxId);
        if (person != null) {
            person.setFullName(newFullName);
            person.setAddress(newAddress);
            person.setPhone(newPhone);
            System.out.println("Информация о человеке обновлена");
        } else {
            System.out.println("Человек с ИНН " + taxId + " не найден");
        }
    }

    public Set<String> getAllTaxIds() {
        return database.keySet();
    }

    public void printSummary() {
        System.out.println("\n=== КРАТКАЯ СВОДКА ===");
        System.out.printf("Всего налогоплательщиков: %d\n", database.size());

        int totalFines = 0;
        double totalAmount = 0;
        for (Person person : database.values()) {
            totalFines += person.getFines().size();
            totalAmount += person.getTotalFinesAmount();
        }

        System.out.printf("Всего штрафов: %d\n", totalFines);
        System.out.printf("Общая сумма неоплаченных штрафов: %.2f руб.\n", totalAmount);
    }
}