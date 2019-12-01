import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class fxGraph extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/graph.fxml"));
        Scene scene = new Scene(root, 800,900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Planet trajectory");
        primaryStage.show();

    }
}
