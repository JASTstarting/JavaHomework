package lesson02_inheritance;

public class MusicalInstrument {
    protected String name;
    protected String type;
    protected String material;
    protected int yearCreated;

    public MusicalInstrument(String name, String type, String material, int yearCreated) {
        this.name = name;
        this.type = type;
        this.material = material;
        this.yearCreated = yearCreated;
    }

    public void sound() {
        System.out.println(name + " издаёт музыкальный звук");
    }

    public void show() {
        System.out.println("Музыкальный инструмент: " + name);
    }

    public void desc() {
        System.out.println("Описание инструмента:");
        System.out.println("  Тип: " + type);
        System.out.println("  Материал: " + material);
        System.out.println("  Год создания: " + yearCreated);
    }

    public void history() {
        System.out.println("История создания " + name + ":");
        System.out.println("  Точная история происхождения не установлена.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }
}