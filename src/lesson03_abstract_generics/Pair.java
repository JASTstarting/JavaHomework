package lesson03_abstract_generics;

import java.util.Objects;

public class Pair<T, U> {
    private T first;   // Первое значение пары
    private U second;  // Второе значение пары

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    // Переопределение equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    // Переопределение hashCode
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    // Переопределение toString
    @Override
    public String toString() {
        return "Pair{" + first + ", " + second + "}";
    }
}