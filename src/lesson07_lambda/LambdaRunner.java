package lesson07_lambda;

public class LambdaRunner {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ЛЯМБДА-ВЫРАЖЕНИЯ                          ║");
        System.out.println("║              Демонстрация всех заданий                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        // Работа с датами
        DateLambdaTasks.runTasks();

        // Работа с дробями
        FractionLambdaTasks.runTasks();

        // Поиск максимума и минимума
        MinMaxLambdaTasks.runTasks();

        // Лямбда как параметр метода
        ArrayFilterLambdaTasks.runTasks();

        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("Все задания успешно выполнены!");
        System.out.println("=".repeat(70));
    }
}