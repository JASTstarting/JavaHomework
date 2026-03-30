package lesson02_inheritance;

public class Steamship extends Device {
    private double length;
    private int passengerCapacity;
    private String engineType;

    public Steamship(String name, String brand, int power, String color,
                     double length, int passengerCapacity, String engineType) {
        super(name, brand, power, color);
        this.length = length;
        this.passengerCapacity = passengerCapacity;
        this.engineType = engineType;
    }

    @Override
    public void sound() {
        System.out.println(name + " даёт гудок: У-У-У-У-У!");
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("  Длина: " + length + " м");
        System.out.println("  Вместимость пассажиров: " + passengerCapacity);
        System.out.println("  Тип двигателя: " + engineType);
    }

    public void sail() {
        System.out.println(name + " плывёт по морю");
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
}