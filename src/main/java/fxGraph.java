import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;

import java.io.IOException;

public class fxGraph extends Application {

    /**
     * Basic stage set class;
     */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/graph.fxml"));
        Scene scene = new Scene(root, 1200,900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Orb trajectory");
        primaryStage.show();

    }
}
