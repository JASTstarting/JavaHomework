package lesson02_inheritance;

public class Crocodile extends Animal {
    private double bodyLength;
    private int teethCount;


    public Crocodile(String name, int age, String habitat, String food,
                     double bodyLength, int teethCount) {
        super(name, age, habitat, food);
        this.bodyLength = bodyLength;
        this.teethCount = teethCount;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " шипит: Ш-Ш-Ш-Ш!");
    }

    public void swim() {
        System.out.println(name + " плавает в воде");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Длина тела: " + bodyLength + " м");
        System.out.println("Количество зубов: " + teethCount);
    }

    public double getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(double bodyLength) {
        this.bodyLength = bodyLength;
    }

    public int getTeethCount() {
        return teethCount;
    }

    public void setTeethCount(int teethCount) {
        this.teethCount = teethCount;
    }
}