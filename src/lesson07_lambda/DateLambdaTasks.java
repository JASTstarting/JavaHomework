package lesson07_lambda;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class DateLambdaTasks {

    // Проверка является ли год високосным
    public static Predicate<Integer> isLeapYear = year -> (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

    // Подсчет количества дней между двумя датами
    public static BiFunction<LocalDate, LocalDate, Long> daysBetween = (date1, date2) -> Math.abs(ChronoUnit.DAYS.between(date1, date2));

    // Подсчет количества полных недель между двумя датами
    public static BiFunction<LocalDate, LocalDate, Long> weeksBetween = (date1, date2) -> Math.abs(ChronoUnit.WEEKS.between(date1, date2));

    // Подсчет дня недели по полученной дате
    public static Function<LocalDate, String> getDayOfWeek = date -> date.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.forLanguageTag("ru"));

    public static void runTasks() {
        System.out.println("=== ЗАДАНИЕ 1: Лямбда-выражения для работы с датами ===\n");

        // Тестирование проверки високосного года
        System.out.println("--- Проверка високосного года ---");
        int[] years = {2020, 2021, 2024, 1900, 2000};
        for (int year : years) {
            System.out.println(year + " год: " + (isLeapYear.test(year) ? "високосный" : "не високосный"));
        }

        // Тестирование подсчета дней между датами
        System.out.println("\n--- Подсчет дней между датами ---");
        LocalDate date1 = LocalDate.of(2024, 1, 1);
        LocalDate date2 = LocalDate.of(2024, 12, 31);
        LocalDate date3 = LocalDate.of(1969, 7, 20);
        LocalDate date4 = LocalDate.now();

        System.out.println("Дней между " + date1 + " и " + date2 + ": " + daysBetween.apply(date1, date2));
        System.out.println("Дней между 01.01.2024 и сегодня: " + daysBetween.apply(date1, date4));
        System.out.println("Дней между 20.07.1969 и сегодня: " + daysBetween.apply(date3, date4));

        // Тестирование подсчета полных недель
        System.out.println("\n--- Подсчет полных недель между датами ---");
        System.out.println("Недель между " + date1 + " и " + date2 + ": " + weeksBetween.apply(date1, date2));
        System.out.println("Недель между 01.01.2024 и сегодня: " + weeksBetween.apply(date1, date4));

        // Тестирование определения дня недели
        System.out.println("\n--- Определение дня недели ---");
        LocalDate[] dates = {
                LocalDate.of(1969, 7, 20),  // Высадка на Луну
                LocalDate.of(1961, 4, 12),  // Первый полёт человека в космос
                LocalDate.of(1945, 5, 9),   // День Победы
                LocalDate.now()              // Сегодня
        };

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (LocalDate date : dates) {
            System.out.println(date.format(formatter) + " - " + getDayOfWeek.apply(date));
        }

        System.out.println("\n--- Дополнительные примеры ---");
        LocalDate birthday = LocalDate.of(2000, 1, 1);
        System.out.println("День рождения 01.01.2000 был в " + getDayOfWeek.apply(birthday));

        LocalDate future = LocalDate.of(2030, 1, 1);
        System.out.println("01.01.2030 будет " + getDayOfWeek.apply(future));
    }
}