package lesson10_stream_api;

import java.util.Objects;

public record Device(String name, int year, double price, String color, String type) {

    @Override
    public String toString() {
        return String.format("%-20s | %4d | %10.2f руб. | %-10s | %s",
                name, year, price, color, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return year == device.year &&
                Double.compare(device.price, price) == 0 &&
                Objects.equals(name, device.name) &&
                Objects.equals(color, device.color) &&
                Objects.equals(type, device.type);
    }

}