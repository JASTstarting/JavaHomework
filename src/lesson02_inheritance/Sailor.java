package lesson02_inheritance;

public class Sailor extends Human {
    private String rank;
    private int seaExperience;
    private String shipName;

    public Sailor(String fullName, int age, String gender,
                  String rank, int seaExperience, String shipName) {
        super(fullName, age, gender);
        this.rank = rank;
        this.seaExperience = seaExperience;
        this.shipName = shipName;
    }

    @Override
    public void work() {
        System.out.println(fullName + " плавает на корабле. Звание: " + rank);
    }

    public void sail() {
        System.out.println(fullName + " управляет кораблём \"" + shipName + "\"");
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Звание: " + rank);
        System.out.println("Морской стаж: " + seaExperience + " лет");
        System.out.println("Название корабля: " + shipName);
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getSeaExperience() {
        return seaExperience;
    }

    public void setSeaExperience(int seaExperience) {
        this.seaExperience = seaExperience;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}