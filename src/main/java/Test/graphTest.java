package Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class graphTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // utworzenie wykresu
        NumberAxis xAxis= new NumberAxis();
        xAxis.setLabel("x");
        NumberAxis yAxis= new NumberAxis();
        yAxis.setLabel("f(x)");
        LineChart<Number,Number> graph= new LineChart<>(xAxis,yAxis);
        graph.setLegendVisible(false);

        // utworzenie serii danych
        XYChart.Series series= new XYChart.Series();
        /*
        series.getData().add( new XYChart.Data(1., 1.));
        series.getData().add( new XYChart.Data(2., 2.));
        series.getData().add( new XYChart.Data(3., 3.));
        */

        for(double x=-10; x<=10; x+=0.1)
            series.getData().add( new XYChart.Data(x,2*x));

        // dodaj dane do wykresu
        graph.getData().add(series);

        Scene scene= new Scene(graph,800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo");
        primaryStage.show();

    }
}