package lesson02_inheritance;

public class Tiger extends Animal {
    private String stripePattern;
    private double clawLength;

    public Tiger(String name, int age, String habitat, String food,
                 String stripePattern, double clawLength) {
        super(name, age, habitat, food);
        this.stripePattern = stripePattern;
        this.clawLength = clawLength;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " рычит: Р-Р-Р-Р!");
    }

    public void hunt() {
        System.out.println(name + " охотится на " + food);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Рисунок полос: " + stripePattern);
        System.out.println("Длина когтей: " + clawLength + " см");
    }

    public String getStripePattern() {
        return stripePattern;
    }

    public void setStripePattern(String stripePattern) {
        this.stripePattern = stripePattern;
    }

    public double getClawLength() {
        return clawLength;
    }

    public void setClawLength(double clawLength) {
        this.clawLength = clawLength;
    }
}