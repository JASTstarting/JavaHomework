package lesson02_inheritance;

public class Product {
    private String name;
    private Money price;
    private String manufacturer;
    private int quantity;

    public Product(String name, Money price, String manufacturer, int quantity) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
    }

    // Уменьшить цену на заданное число (в копейках)
    public void decreasePrice(int centsAmount) {
        if (centsAmount <= 0) {
            System.out.println("Ошибка: сумма уменьшения должна быть положительной");
            return;
        }
        System.out.print("Старая цена: ");
        price.displayAmount();
        price.decrease(centsAmount);
        System.out.print("Новая цена: ");
        price.displayAmount();
    }

    // Уменьшить цену на процент
    public void decreasePricePercent(double percent) {
        if (percent <= 0 || percent > 100) {
            System.out.println("Ошибка: процент должен быть от 0 до 100");
            return;
        }
        long totalCents = price.getTotalCents();
        long decreaseAmount = (long)(totalCents * percent / 100);
        decreasePrice((int)decreaseAmount);
    }

    public void displayInfo() {
        System.out.println("=== Информация о товаре ===");
        System.out.println("Название: " + name);
        System.out.print("Цена: ");
        price.displayAmount();
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Количество: " + quantity);
        System.out.println("--------------------------");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}