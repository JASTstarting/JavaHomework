package lesson02_inheritance;

public class Money {
    private long rubles;        // Целая часть (рубли, доллары, евро и т.д.)
    private int cents;          // Дробная часть (копейки, центы)
    private String currency;    // Валюта (руб, USD, EUR и т.д.)

    // Конструкторы
    public Money() {
        this.rubles = 0;
        this.cents = 0;
        this.currency = "руб";
    }

    public Money(long rubles, int cents, String currency) {
        this.rubles = rubles;
        this.currency = currency;
        normalizeCents(cents); // Используем переименованный метод
    }

    // Метод для задания суммы
    public void setAmount(long rubles, int cents) {
        this.rubles = rubles;
        normalizeCents(cents);
    }

    // Нормализация копеек (если копеек >= 100)
    private void normalizeCents(int cents) {
        if (cents >= 100) {
            this.rubles += cents / 100;
            this.cents = cents % 100;
        } else if (cents < 0) {
            this.rubles -= (Math.abs(cents) / 100) + 1;
            this.cents = 100 - (Math.abs(cents) % 100);
            if (this.cents == 100) {
                this.cents = 0;
            }
        } else {
            this.cents = cents;
        }
    }

    // Вывод суммы
    public void displayAmount() {
        System.out.printf("%d %s %02d\n", rubles, currency, cents);
    }

    // Сложение денег
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            System.out.println("Ошибка: нельзя складывать разные валюты");
            return this;
        }
        long totalCents = (this.rubles * 100 + this.cents) +
                (other.rubles * 100 + other.cents);
        return new Money(totalCents / 100, (int)(totalCents % 100), this.currency);
    }

    // Вычитание денег
    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            System.out.println("Ошибка: нельзя вычитать разные валюты");
            return this;
        }
        long totalCents = (this.rubles * 100 + this.cents) -
                (other.rubles * 100 + other.cents);
        return new Money(totalCents / 100, (int)(totalCents % 100), this.currency);
    }

    // Уменьшение суммы на заданное число (в копейках)
    public void decrease(int centsAmount) {
        long totalCents = (this.rubles * 100 + this.cents) - centsAmount;
        if (totalCents < 0) {
            System.out.println("Ошибка: сумма не может быть отрицательной");
            return;
        }
        this.rubles = totalCents / 100;
        this.cents = (int)(totalCents % 100);
    }

    // Геттеры и сеттеры
    public long getRubles() {
        return rubles;
    }

    public void setRubles(long rubles) {
        this.rubles = rubles;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        normalizeCents(cents);  // Вызываем переименованный метод
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    // Получение суммы в копейках
    public long getTotalCents() {
        return rubles * 100 + cents;
    }
}