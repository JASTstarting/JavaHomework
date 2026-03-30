package lesson02_inheritance;

public class Car extends Device {
    private int maxSpeed;
    private String fuelType;
    private int doors;

    public Car(String name, String brand, int power, String color,
               int maxSpeed, String fuelType, int doors) {
        super(name, brand, power, color);
        this.maxSpeed = maxSpeed;
        this.fuelType = fuelType;
        this.doors = doors;
    }

    @Override
    public void sound() {
        System.out.println(name + " издаёт звук двигателя: ВРУМ-ВРУМ!");
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("  Максимальная скорость: " + maxSpeed + " км/ч");
        System.out.println("  Тип топлива: " + fuelType);
        System.out.println("  Количество дверей: " + doors);
    }

    public void drive() {
        System.out.println(name + " едет по дороге");
    }


    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}