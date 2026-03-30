package lesson06_taxDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fine {
    private final int id;
    private String type;
    private double amount;
    private LocalDate date;
    private String city;
    private String description;
    private boolean isPaid;

    private static int nextId = 1;

    public Fine(String type, double amount, LocalDate date, String city, String description) {
        this.id = nextId++;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.city = city;
        this.description = description;
        this.isPaid = false;
    }

    public void pay() {
        this.isPaid = true;
        System.out.println("Штраф #" + id + " оплачен");
    }

    public void display() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.printf("  Штраф #%d | %s | %.2f руб. | %s | %s | %s | %s\n",
                id, type, amount, date.format(formatter), city, description,
                isPaid ? "ОПЛАЧЕН" : "НЕ ОПЛАЧЕН");
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }

    @Override
    public String toString() {
        return "Fine{" + "id=" + id + ", type='" + type + '\'' + ", amount=" + amount + '}';
    }
}