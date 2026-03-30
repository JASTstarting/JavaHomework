package lesson02_inheritance;

public class Cello extends MusicalInstrument {
    private int stringsCount;
    private String bowType;
    private double size;

    public Cello(String name, String material, int yearCreated,
                 int stringsCount, String bowType, double size) {
        super(name, "Струнный", material, yearCreated);
        this.stringsCount = stringsCount;
        this.bowType = bowType;
        this.size = size;
    }

    @Override
    public void sound() {
        System.out.println(name + " издаёт глубокий, бархатистый звук: М-м-м-м!");
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("  Количество струн: " + stringsCount);
        System.out.println("  Тип смычка: " + bowType);
        System.out.println("  Размер: " + size + "/4");
    }

    @Override
    public void history() {
        System.out.println("История создания виолончели:");
        System.out.println("  Виолончель появилась в XVI веке в Италии.");
        System.out.println("  Антонио Страдивари создал классическую форму виолончели.");
        System.out.println("  В отличие от скрипки, виолончель играют сидя.");
    }

    public int getStringsCount() {
        return stringsCount;
    }

    public void setStringsCount(int stringsCount) {
        this.stringsCount = stringsCount;
    }

    public String getBowType() {
        return bowType;
    }

    public void setBowType(String bowType) {
        this.bowType = bowType;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}