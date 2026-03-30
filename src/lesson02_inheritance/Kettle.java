package lesson02_inheritance;

public class Kettle extends Device {
    private double volume;
    private String heatingElement;

    public Kettle(String name, String brand, int power, String color,
                  double volume, String heatingElement) {
        super(name, brand, power, color);
        this.volume = volume;
        this.heatingElement = heatingElement;
    }

    @Override
    public void sound() {
        System.out.println(name + " кипит: Ш-Ш-Ш-Ш, щёлк!");
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("  Объём: " + volume + " л");
        System.out.println("  Нагревательный элемент: " + heatingElement);
    }

    public void boil() {
        System.out.println(name + " кипятит воду");
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getHeatingElement() {
        return heatingElement;
    }

    public void setHeatingElement(String heatingElement) {
        this.heatingElement = heatingElement;
    }
}