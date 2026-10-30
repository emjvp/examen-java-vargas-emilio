package Ejercicio1;

import static Ejercicio1.SalesUtils.printResultTable;

public class Ejercicio1Ventas {

        static void main(String[] args) {

            String[] weekDays = {"Lunes", "Martes", "Miercoles", "Jueves",
                    "Viernes", "Sabado", "Domingo"};

            double[] sells = {1250.5, 980.0, 1430.75, 760.25,
                    2100.0, 2650.5, 1890.0};

            printResultTable(weekDays, sells);

    }



}