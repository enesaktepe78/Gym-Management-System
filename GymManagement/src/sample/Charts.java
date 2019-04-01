package sample;

import javafx.scene.chart.PieChart;

public class Charts {
    public static void pieChart(PieChart pieChart, Integer x, Integer y){
        PieChart.Data slice1 = new PieChart.Data("Male", x);
        PieChart.Data slice2 = new PieChart.Data("Famale", y);
        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
    }

}
