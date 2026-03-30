package lesson02_inheritance;

public class Device {
    protected String name;
    protected String brand;
    protected int power;
    protected String color;

    public Device(String name, String brand, int power, String color) {
        this.name = name;
        this.brand = brand;
        this.power = power;
        this.color = color;
    }

    public void sound() {
        System.out.println(name + " издаёт звук");
    }

    public void show() {
        System.out.println("Устройство: " + name);
    }

    public void desc() {
        System.out.println("Описание устройства:");
        System.out.println("  Бренд: " + brand);
        System.out.println("  Мощность: " + power + " Вт");
        System.out.println("  Цвет: " + color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}