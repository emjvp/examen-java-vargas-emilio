package Ejercicio2;

public final class EvaluatorUtils {

    public enum State { APROBADO, RECUPERACION, REPROBADO }

    private EvaluatorUtils() {}

    public static double calculateWeightedAverage(double[] studentGrades, double[] weights){
        double totalWeightedAverage = 0;
        for(int i = 0; i < studentGrades.length; i++){
            totalWeightedAverage += studentGrades[i] * weights[i];
        }
        return totalWeightedAverage;
    }

    public static State evaluate(double average, double attendance){

        if(attendance < 0.75){
            return State.REPROBADO;
        } else if(average >= 3.0){
            return State.APROBADO;
        } else if(average >= 2.5){
            return State.RECUPERACION;
        }

        return State.REPROBADO;
    }
}
