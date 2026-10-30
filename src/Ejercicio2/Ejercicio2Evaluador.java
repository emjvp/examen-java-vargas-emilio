package Ejercicio2;

import static Ejercicio2.EvaluatorUtils.*;

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
        double[] quizAverages = calculateAverageByQuiz(grades);
        int [] bestGradesIdx = bestGradesByStudentIdx(grades);
        printAnalysisByEvaluation(quices, quizAverages, grades, students, bestGradesIdx);

        double[] studentsAverages = new double[grades.length];
        for (int i = 0; i < grades.length; i++) {
            studentsAverages[i] = calculateWeightedAverage(grades[i], weights);
        }

        int[] order = getInitalOrderArray(grades.length);
        order = bubbleSortOrderArrayByAvg(order, studentsAverages);

        System.out.println("=== RANKING ===");
        for (int p = 0; p < students.length; p++) {
            System.out.printf("%d. %s %f \n", p+1, students[order[p]], studentsAverages[order[p]]);
        }

    }



}
