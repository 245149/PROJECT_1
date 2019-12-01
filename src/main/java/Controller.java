import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<Number, Number> graph;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button btnPlotLinear;

    @FXML
    private Button btnPlotQuadratic;


    @FXML
    void onLinearClick(ActionEvent event) {
        System.out.println("Linear clicked");

        graph.getData().clear();


        // utworzenie serii danych
        XYChart.Series series= new XYChart.Series();
        for(double x=-10; x<=10; x+=0.1)
            series.getData().add( new XYChart.Data(x,x));
        // dodaj dane do wykresu
        graph.getData().add(series);

    }

    @FXML
    void onQuadraticClick(ActionEvent event) {

        graph.getData().clear();

        // utworzenie serii danych
        XYChart.Series series= new XYChart.Series();
        for(double x=-10; x<=10; x+=0.1)
            series.getData().add( new XYChart.Data(x,x*x));
        // dodaj dane do wykresu
        graph.getData().add(series);

    }

    @FXML
    void initialize() {
        assert graph != null : "fx:id=\"graph\" was not injected: check your FXML file 'graph.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'graph.fxml'.";
        assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'graph.fxml'.";
        assert btnPlotLinear != null : "fx:id=\"btnPlotLinear\" was not injected: check your FXML file 'graph.fxml'.";
        assert btnPlotQuadratic != null : "fx:id=\"btnPlotQuadratic\" was not injected: check your FXML file 'graph.fxml'.";
    }
}