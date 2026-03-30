package lesson03_abstract_generics;

public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double getArea();

    public void printInfo() {
        System.out.println("Цвет фигуры: " + color);
        System.out.printf("Площадь фигуры: %.2f\n", getArea());
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}