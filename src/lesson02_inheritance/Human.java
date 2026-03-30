package lesson02_inheritance;

public class Human {
    protected String fullName;
    protected int age;
    protected String gender;

    public Human(String fullName, int age, String gender) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    public void displayInfo() {
        System.out.println("ФИО: " + fullName);
        System.out.println("Возраст: " + age);
        System.out.println("Пол: " + gender);
    }

    public void work() {
        System.out.println(fullName + " работает");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}