package lesson02_inheritance;

public class Microwave extends Device {
    private int capacity;
    private boolean grill;

    public Microwave(String name, String brand, int power, String color,
                     int capacity, boolean grill) {
        super(name, brand, power, color);
        this.capacity = capacity;
        this.grill = grill;
    }

    @Override
    public void sound() {
        System.out.println(name + " гудит: У-У-У-У, дзынь!");
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("  Объём: " + capacity + " л");
        System.out.println("  Наличие гриля: " + (grill ? "Да" : "Нет"));
    }

    public void heat() {
        System.out.println(name + " разогревает еду");
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isGrill() {
        return grill;
    }

    public void setGrill(boolean grill) {
        this.grill = grill;
    }
}