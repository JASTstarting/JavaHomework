package lesson02_inheritance;

public class Violin extends MusicalInstrument {
    private int stringsCount;
    private String bowMaterial;

    public Violin(String name, String material, int yearCreated,
                  int stringsCount, String bowMaterial) {
        super(name, "Струнный", material, yearCreated);
        this.stringsCount = stringsCount;
        this.bowMaterial = bowMaterial;
    }

    @Override
    public void sound() {
        System.out.println(name + " издаёт нежный, певучий звук: Ви-и-и-и!");
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("  Количество струн: " + stringsCount);
        System.out.println("  Материал смычка: " + bowMaterial);
    }

    @Override
    public void history() {
        System.out.println("История создания скрипки:");
        System.out.println("  Скрипка появилась в Италии в начале XVI века.");
        System.out.println("  Лучшие мастера: Амати, Гварнери, Страдивари.");
        System.out.println("  Современный вид скрипка приобрела в XVIII веке.");
    }

    public int getStringsCount() {
        return stringsCount;
    }

    public void setStringsCount(int stringsCount) {
        this.stringsCount = stringsCount;
    }

    public String getBowMaterial() {
        return bowMaterial;
    }

    public void setBowMaterial(String bowMaterial) {
        this.bowMaterial = bowMaterial;
    }
}