package lesson02_inheritance;

public class Trombone extends MusicalInstrument {
    private String tuning;
    private int tubeLength;


    public Trombone(String name, String material, int yearCreated,
                    String tuning, int tubeLength) {
        super(name, "Медный духовой", material, yearCreated);
        this.tuning = tuning;
        this.tubeLength = tubeLength;
    }

    @Override
    public void sound() {
        System.out.println(name + " издаёт мощный, яркий звук: Ту-ру-ру-ру!");
    }

    @Override
    public void desc() {
        super.desc();
        System.out.println("  Строй: " + tuning);
        System.out.println("  Длина трубы: " + tubeLength + " см");
    }

    @Override
    public void history() {
        System.out.println("История создания тромбона:");
        System.out.println("  Тромбон появился в XV веке в Европе.");
        System.out.println("  Название происходит от итальянского 'tromba' (труба).");
        System.out.println("  Использовался в церковной и военной музыке.");
    }

    public String getTuning() {
        return tuning;
    }

    public void setTuning(String tuning) {
        this.tuning = tuning;
    }

    public int getTubeLength() {
        return tubeLength;
    }

    public void setTubeLength(int tubeLength) {
        this.tubeLength = tubeLength;
    }
}