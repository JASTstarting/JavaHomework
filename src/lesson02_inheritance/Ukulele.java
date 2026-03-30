package lesson02_inheritance;

public class Ukulele extends MusicalInstrument {
    private int stringsCount;
    private String size;

    public Ukulele(String name, String material, int yearCreated,
                   int stringsCount, String size) {
        super(name, "Струнный (щипковый)", material, yearCreated);
        this.stringsCount = stringsCount;
        this.size = size;
    }

    @Override
    public void sound() {
        System.out.println(name + " издаёт весёлый, звонкий звук: Брынь-брынь!");
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("  Количество струн: " + stringsCount);
        System.out.println("  Размер: " + size);
    }

    @Override
    public void history() {
        System.out.println("История создания укулеле:");
        System.out.println("  Укулеле появилась на Гавайях в 1880-х годах.");
        System.out.println("  Произошла от португальской гитары кавакиньо.");
        System.out.println("  Название переводится как 'прыгающая блоха'.");
    }

    public int getStringsCount() {
        return stringsCount;
    }

    public void setStringsCount(int stringsCount) {
        this.stringsCount = stringsCount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}