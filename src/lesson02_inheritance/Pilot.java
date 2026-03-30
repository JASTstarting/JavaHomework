package lesson02_inheritance;

public class Pilot extends Human {
    private String licenseNumber;
    private int flightHours;
    private String aircraftType;

    public Pilot(String fullName, int age, String gender,
                 String licenseNumber, int flightHours, String aircraftType) {
        super(fullName, age, gender);
        this.licenseNumber = licenseNumber;
        this.flightHours = flightHours;
        this.aircraftType = aircraftType;
    }

    @Override
    public void work() {
        System.out.println(fullName + " управляет самолётом. Тип: " + aircraftType);
    }

    public void fly() {
        System.out.println(fullName + " выполняет полёт на " + aircraftType);
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Номер лицензии: " + licenseNumber);
        System.out.println("Налёт часов: " + flightHours);
        System.out.println("Тип самолёта: " + aircraftType);
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }
}