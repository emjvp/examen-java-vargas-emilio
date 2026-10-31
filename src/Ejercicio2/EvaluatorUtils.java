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

    static void printAnalysisByEvaluation(String[] quices, double[] averages, double[][] grades, String[] students, int[] bestGradesIdx) {
        System.out.println("=== ANALISIS POR EVALUACION ===");
        for (int i = 0; i < quices.length; i++) {
            System.out.printf("%s promedio: %2.2f | mejor: %s (%2.2f)\n", quices[i], averages[i], students[bestGradesIdx[i]], grades[bestGradesIdx[i]][i]);
        }
    }

    static void printCourseResults(double[][] grades, double[] weights, double[] attendance, String[] students) {
        double[] averages = new double[6];
        EvaluatorUtils.State[] states = new EvaluatorUtils.State[6];

        int approved = 0;
        int recover = 0;
        int failed = 0;

        System.out.println("=== RESULTADOS DEL CURSO ===");
        System.out.println("Estudiante \t Promedio \t Asistencia \t Estado");
        for (int i = 0; i < averages.length; i++) {

            double wAverage = calculateWeightedAverage(grades[i], weights);
            averages[i] = wAverage;
            states[i] = evaluate(averages[i], attendance[i]);
            System.out.printf("%s %16.2f %10.0f%%      %s \n",  students[i], wAverage, attendance[i] * 100, states[i]);
            switch (states[i]) {
                case APROBADO: approved++; break;
                case RECUPERACION: recover++; break;
                case REPROBADO: failed++; break;
            }

        }

        System.out.println("Aprobados: "+approved+" | Recuperacion: "+recover+" | Reprobados: "+failed);
        System.out.println();
    }

    static double[] calculateAverageByQuiz(double[][] grades) {
        double[] averages = new double[4];
        for (int j = 0; j < grades[j].length; j++) {
            double sum = 0;
            for (int i = 0; i < grades.length; i++) {
                sum += grades[i][j];
            }
            averages[j] = sum / grades.length;
        }

        return averages;
    }



    static int[] bestGradesByStudentIdx(double[][] grades) {
        int[] bestGradesIdx = new int[grades.length];

        for (int j = 0; j < grades[j].length; j++) {
            double maxGrade = 0.0f;
            int maxIdx = 0;
            for (int i = 0; i < grades.length; i++) {
                if(grades[i][j] > maxGrade){
                    maxGrade = grades[i][j];
                    maxIdx = i;
                }
            }
            bestGradesIdx[j] = maxIdx;
        }

        return bestGradesIdx;
    }

    static int[] getInitalOrderArray(int size) {
        int[] order = new int[size];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        return order;
    }

    static int[] invertedBubbleSortOrderArrayByAvg(int [] orderParam, double[] averages) {

        int [] order = orderParam.clone();
        for (int i = 0; i < order.length - 2; i++) {
            for (int j = 0; j < order.length - 2 - j; j++) {

                if (averages[order[j]] < averages[order[j + 1]]) {
                    int temp = order[j];
                    order[j] = order[j + 1];
                    order[j + 1] = temp;
                }


            }
        }

        return order;
    }

    static int[] bubbleSortOrder(int [] orderParam, double[] averages, boolean invertedOrder) {

        int [] order = orderParam.clone();
        for (int i = 0; i < order.length; i++) {
            for (int j = i + 1; j < order.length; j++) {
                if (invertedOrder ? averages[order[i]] < averages[order[j]] : averages[order[i]] > averages[order[j]]) {
                    int swap = order[i];
                    order[i] = order[j];
                    order[j] = swap;
                }
            }
        }

        return order;
    }


}
