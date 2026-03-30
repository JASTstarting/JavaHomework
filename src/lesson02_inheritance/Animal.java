package lesson02_inheritance;

public class Animal {
    protected String name;
    protected int age;
    protected String habitat;
    protected String food;

    public Animal(String name, int age, String habitat, String food) {
        this.name = name;
        this.age = age;
        this.habitat = habitat;
        this.food = food;
    }

    public void makeSound() {
        System.out.println(name + " издаёт звук");
    }

    public void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age + " лет");
        System.out.println("Среда обитания: " + habitat);
        System.out.println("Пища: " + food);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
}