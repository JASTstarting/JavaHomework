package lesson02_inheritance;

public class Kangaroo extends Animal {
    private double jumpHeight;
    private double tailLength;

    public Kangaroo(String name, int age, String habitat, String food,
                    double jumpHeight, double tailLength) {
        super(name, age, habitat, food);
        this.jumpHeight = jumpHeight;
        this.tailLength = tailLength;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " фыркает: Ф-Ф-Ф-Ф!");
    }

    public void jump() {
        System.out.println(name + " прыгает на " + jumpHeight + " метров");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Высота прыжка: " + jumpHeight + " м");
        System.out.println("Длина хвоста: " + tailLength + " см");
    }

    public double getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(double jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public double getTailLength() {
        return tailLength;
    }

    public void setTailLength(double tailLength) {
        this.tailLength = tailLength;
    }
}