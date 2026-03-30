package lesson06_taxDatabase;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String taxId;
    private String fullName;
    private String address;
    private String phone;
    private final List<Fine> fines;

    public Person(String taxId, String fullName, String address, String phone) {
        this.taxId = taxId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.fines = new ArrayList<>();
    }

    public void addFine(Fine fine) {
        fines.add(fine);
        System.out.println("Штраф добавлен для " + fullName);
    }

    public void removeFine(int fineId) {
        for (int i = 0; i < fines.size(); i++) {
            if (fines.get(i).getId() == fineId) {
                fines.remove(i);
                System.out.println("Штраф #" + fineId + " удален");
                return;
            }
        }
        System.out.println("Штраф #" + fineId + " не найден");
    }

    public Fine getFine(int fineId) {
        for (Fine fine : fines) {
            if (fine.getId() == fineId) {
                return fine;
            }
        }
        return null;
    }

    public List<Fine> getFines() {
        return new ArrayList<>(fines);
    }

    public List<Fine> getFinesByType(String type) {
        List<Fine> result = new ArrayList<>();
        for (Fine fine : fines) {
            if (fine.getType().equalsIgnoreCase(type)) {
                result.add(fine);
            }
        }
        return result;
    }

    public List<Fine> getFinesByCity(String city) {
        List<Fine> result = new ArrayList<>();
        for (Fine fine : fines) {
            if (fine.getCity().equalsIgnoreCase(city)) {
                result.add(fine);
            }
        }
        return result;
    }

    public double getTotalFinesAmount() {
        double total = 0;
        for (Fine fine : fines) {
            if (!fine.isPaid()) {
                total += fine.getAmount();
            }
        }
        return total;
    }

    public void display() {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│ ИНН: " + taxId);
        System.out.println("│ ФИО: " + fullName);
        System.out.println("│ Адрес: " + address);
        System.out.println("│ Телефон: " + phone);
        System.out.println("│ Количество штрафов: " + fines.size());
        System.out.printf(" │ Общая сумма неоплаченных штрафов: %.2f руб.\n", getTotalFinesAmount());
        System.out.println("└─────────────────────────────────────────────────┘");

        if (!fines.isEmpty()) {
            System.out.println("Штрафы:");
            for (Fine fine : fines) {
                fine.display();
            }
        } else {
            System.out.println("Штрафов нет");
        }
    }

    public void displayShort() {
        System.out.printf("%-15s | %-25s | Штрафов: %d | Сумма: %.2f руб.\n",
                taxId, fullName, fines.size(), getTotalFinesAmount());
    }

    public String getTaxId() { return taxId; }
    public void setTaxId(String taxId) { this.taxId = taxId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}