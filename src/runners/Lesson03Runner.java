package runners;

import lesson03_abstract_generics.*;

public class Lesson03Runner {
    public static void main(String[] args) {
        System.out.println("========== УРОК 3: Абстракция и обобщения ==========\n");

        System.out.println("=== Геометрические фигуры ===");
        Circle circle = new Circle("Красный", 5.0);
        circle.printInfo();

        System.out.println();
        Rectangle rectangle = new Rectangle("Синий", 4.0, 6.0);
        rectangle.printInfo();

        System.out.println("\n=== Полиморфизм ===");
        Shape[] shapes = {
                new Circle("Зелёный", 3.0),
                new Rectangle("Жёлтый", 2.5, 4.5)
        };
        for (Shape shape : shapes) {
            shape.printInfo();
            System.out.println();
        }

        System.out.println("=== Обобщенный класс Pair ===");
        Pair<String, Integer> student = new Pair<>("Иванов", 95);
        Pair<Double, Double> coordinates = new Pair<>(55.751244, 37.618423);
        Pair<String, String> employee = new Pair<>("Петрова", "Инженер");

        System.out.println(student);
        System.out.println(coordinates);
        System.out.println(employee);
    }
}