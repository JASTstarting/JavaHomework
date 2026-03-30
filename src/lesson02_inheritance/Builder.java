package lesson02_inheritance;

public class Builder extends Human {
    private String specialization;
    private int experience;
    private String tools;

    public Builder(String fullName, int age, String gender,
                   String specialization, int experience, String tools) {
        super(fullName, age, gender);
        this.specialization = specialization;
        this.experience = experience;
        this.tools = tools;
    }

    @Override
    public void work() {
        System.out.println(fullName + " строит дом. Специализация: " + specialization);
    }

    public void buildHouse() {
        System.out.println(fullName + " строит дом, используя " + tools);
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Специализация: " + specialization);
        System.out.println("Опыт: " + experience + " лет");
        System.out.println("Инструменты: " + tools);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }
}