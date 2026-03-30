package lesson01_basics;

public class Person {
    private String fullName;
    private String birthDate;
    private String phone;
    private String city;
    private String country;
    private String homeAddress;

    public void inputData(String fullName, String birthDate, String phone,
                          String city, String country, String homeAddress) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.homeAddress = homeAddress;
    }

    public void displayData() {
        System.out.println("=== Данные о человеке ===");
        System.out.println("ФИО: " + fullName);
        System.out.println("Дата рождения: " + birthDate);
        System.out.println("Телефон: " + phone);
        System.out.println("Город: " + city);
        System.out.println("Страна: " + country);
        System.out.println("Домашний адрес: " + homeAddress);
        System.out.println("--------------------------");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
}