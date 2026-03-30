package lesson01_basics;

public class Country {
    private String countryName;
    private String continent;
    private long population;
    private String phoneCode;
    private String capital;
    private String[] cities;

    public void inputData(String countryName, String continent, long population,
                          String phoneCode, String capital, String[] cities) {
        this.countryName = countryName;
        this.continent = continent;
        this.population = population;
        this.phoneCode = phoneCode;
        this.capital = capital;
        this.cities = cities;
    }

    public void displayData() {
        System.out.println("=== Данные о стране ===");
        System.out.println("Название страны: " + countryName);
        System.out.println("Континент: " + continent);
        System.out.println("Население: " + population + " чел.");
        System.out.println("Телефонный код: " + phoneCode);
        System.out.println("Столица: " + capital);
        System.out.print("Города: ");
        if (cities != null) {
            for (int i = 0; i < cities.length; i++) {
                System.out.print(cities[i]);
                if (i < cities.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println("\n----------------------");
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String[] getCities() {
        return cities;
    }

    public void setCities(String[] cities) {
        this.cities = cities;
    }
}