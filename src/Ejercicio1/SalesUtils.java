package Ejercicio1;

public final class SalesUtils {

    public static final double WEEK_GOAL = 10000.0;

    private SalesUtils() {
    }

    public static void printResultTable(String[] weekDays, double[] sells) {
        System.out.println("=== REPORTE DE VENTAS SEMANAL ===");
        System.out.println("Dia \t\t\t Venta \t\t Nivel");

        int bestSellDays = 0;
        final double dayAverage = calculateAverage(sells);

        for (int i = 0; i < weekDays.length; i++) {
            if (sells[i] >= dayAverage) {
                bestSellDays++;
            }
            final String level = classify(sells[i], dayAverage);
            System.out.printf("%-10s %12.2f \t %s%n", weekDays[i], sells[i], level);
        }

        System.out.println("---------------------------------");
        final double totalWeekSells = calculateTotal(sells);
        System.out.printf("Total semanal: %2.2f \n", totalWeekSells);
        System.out.printf("Promedio diario: %2.2f \n", dayAverage);
        final int maxIdx = getIndex(sells, true);
        final String bestSellDay = weekDays[maxIdx];
        System.out.printf("Mejor día: %s (%2.2f) \n", bestSellDay, sells[maxIdx]);
        final int minIdx = getIndex(sells, false);
        final String worseSellDay = weekDays[minIdx];
        System.out.printf("Mejor día: %s (%2.2f) \n", worseSellDay, sells[minIdx]);
        System.out.printf("Dias por encima del promedio: %d \n", bestSellDays);

        final double goalSellDifference = (totalWeekSells - WEEK_GOAL);
        final String finalMessage = goalSellDifference > 0 ? "Meta cumplida. Superaste la meta por: %2.2f" : "Meta no cumplida. faltó: (%2.2f)";
        System.out.printf(finalMessage, goalSellDifference);
    }


    private static double calculateTotal(double[] values) {

        double total = 0;
        for (double value : values) {
            total += value;
        }

        return total;
    }

    private static double calculateAverage(double[] values) {
        return calculateTotal(values) / values.length;
    }


    private static String classify(double sell, double average) {
        if (sell >= average * 1.2) {
            return "ALTA";
        } else if (sell < average * 0.8) {
            return "BAJA";
        }

        return "NORMAL";

    }

    /**
     * Finds the index of the maximum or minimum element according to the provided comparator.
     *
     * @param values array of values to evaluate
     * @param getMax comparator that defines the selection order
     * @return index of the selected element
     *
     * @author Emilio Vargas
     * @author ChatGPT (OpenAI) - Method design assistance
     */
    public static int getIndex(double[] values, boolean getMax){
        int idx = 0;
        for (int i = 0; i < values.length; i++) {
            if (getMax ? values[i] > values[idx] : values[i] < values[idx]){
                idx = i;
            }
        }

        return idx;
    }
}
