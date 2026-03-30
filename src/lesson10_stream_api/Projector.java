package lesson10_stream_api;

import java.util.Objects;

public record Projector(String name, int year, double price, String manufacturer) {

    @Override
    public String toString() {
        return String.format("%-25s | %4d | %12.2f руб. | %s",
                name, year, price, manufacturer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projector projector = (Projector) o;
        return year == projector.year &&
                Double.compare(projector.price, price) == 0 &&
                Objects.equals(name, projector.name) &&
                Objects.equals(manufacturer, projector.manufacturer);
    }

}