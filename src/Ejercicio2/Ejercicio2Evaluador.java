package Ejercicio2;

import static Ejercicio2.EvaluatorUtils.evaluate;
import static Ejercicio2.EvaluatorUtils.calculateWeightedAverage;

public class Ejercicio2Evaluador {

    static void main(String[] args) {

        String[] students = {
                "Ana",
                "Luis",
                "Sofia",
                "Carlos",
                "Valentina",
                "Mateo"
        };

        String[] quices = {"Quiz 1", "Quiz 2", "Taller", "Proyecto"};

        double[][] grades = {
                {4.5, 4.9, 3.8, 4.2}, // Ana
                {3.5, 4.0, 4.9, 4.0}, // Luis
                {2.5, 3.0, 2.8, 2.6}, // Sofia
                {3.0, 2.5, 3.2, 3.4}, // Carlos
                {5.0, 4.8, 4.6, 4.9}, // Valentina
                {2.0, 2.5, 1.8, 2.2} // Mateo
        };

        double[] weights = {0.20, 0.20, 0.25, 0.35}; // suman 1.0
        double[] attendance = {0.95, 0.70, 0.88, 0.80, 1.00, 0.76}; // 0.95 = 95%


        printCourseResults(grades, weights, attendance, students);
        double[] avg = calculateAverageByQuiz(grades, weights);
        System.out.println();
/*
        int[] order = new int[6];
        for (int i = 0; i < 6; i++) {
            order[i] = i;
            if(averages[order[i]] < attendance[order[i + 1]]){
                int temp = order[i];
                order[i] = order[i + 1];
                order[i + 1] = temp;
            }
        }*/



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
            System.out.printf("%s %15.2f %10.0f%% %s \n",  students[i], wAverage, attendance[i] * 100, states[i]);
            switch (states[i]) {
                case APROBADO: approved++; break;
                case RECUPERACION: recover++; break;
                case REPROBADO: failed++; break;
            }

        }

        System.out.println("Aprobados: "+approved+" | Recuperacion: "+recover+" | Reprobados: "+failed);
    }

    static double[] calculateAverageByQuiz(double[][] grades, double[] weights) {
        // System.out.println("=== ANALISIS POR EVALUACION ===");
        double[] averages = new double[4];
        for (int j = 0; j < grades.length; j++) {
            double sum = 0;
            for (int i = 0; i < grades[j].length; i++) {
                sum += calculateWeightedAverage(grades[j], weights);
            }
            // averages[j] = sum /  grades.length;
        }

        return averages;
    }



}
