/**
 * Skeleton for 'graph.fxml' Controller Class
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.sun.tools.javac.comp.Enter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.PointLight;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.imageio.ImageIO;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.StrictMath.sqrt;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="fieldNameInput"
    private TextField fieldNameInput; // Value injected by FXMLLoader

    @FXML // fx:id="fieldPeriodInput"
    private TextField fieldPeriodInput; // Value injected by FXMLLoader

    @FXML // fx:id="fieldDistanceInput"
    private TextField fieldDistanceInput; // Value injected by FXMLLoader

    @FXML // fx:id="FieldEccentricityInput"
    private TextField fieldEccentricityInput; // Value injected by FXMLLoader

    @FXML // fx:id="unfocus"
    private TextField unfocus; // Value injected by FXMLLoader

    @FXML // fx:id="switchMethod"
    private ChoiceBox<String> switchMethod; // Value injected by FXMLLoader
    private ObservableList<String> availableChoices = FXCollections.observableArrayList
            ("Fixed point iteration", "Newton-Raphson", "Secant line", "Bisection");

    @FXML // fx:id="btnDrawOrbit"
    private Button btnDrawOrbit; // Value injected by FXMLLoader

    @FXML // fx:id="btnSaveFile"
    private Button btnSaveFile; // Value injected by FXMLLoader

    @FXML
    private Button btnClear;

    @FXML // fx:id="graph"
    private LineChart<Number, Number> graph; // Value injected by FXMLLoader

    @FXML // fx:id="OX"
    private NumberAxis OX; // Value injected by FXMLLoader

    @FXML // fx:id="OY"
    private NumberAxis OY; // Value injected by FXMLLoader

    /**
     * onDrawOrbit ("START" button pressed) - method calculates all output X and Y coordinates
     * for the temporary user "Planet" object
     * (under user method chosen by switchMethod choiceBox)
     * for points set on the orbit perimeter, passes it to chart series and gives the final orbit on graph;
     *
     * @return
     */

    @FXML
    double onDrawOrbit(ActionEvent actionEvent) {

        double e = Double.parseDouble(passEccentricity(fieldEccentricityInput));
        double a = Double.parseDouble(passDistance(fieldDistanceInput));
        String name = passName(fieldNameInput);
        double T = Double.parseDouble(passPeriod(fieldPeriodInput));

        Planet tempPlanet =new Planet(e, a, T, name);
        tempPlanet.samplingM();
//        tempPlanet.samplingM();
//        tempPlanet.FPIsolveE();
//        tempPlanet.NRsolveE();
//        tempPlanet.SLsolveE();
        //double[] tempDataE;
        double tempDataE1 = 0;

        /**
         * Switch choiceBox (calculations of the variable "E" choice)
         */

        String method = switchMethod.getSelectionModel().getSelectedItem();
        switch (method) {
            case "Fixed point iteration":
                tempDataE1 = tempPlanet.FPIsolveE();
                break;
            case "Newton-Raphson":
                tempDataE1 = tempPlanet.NRsolveE();
                break;
            case "Secant line":
                tempDataE1 = tempPlanet.SLsolveE();
                break;
            case "Bisection":
                tempDataE1 = tempPlanet.BsolveE();
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }

        double M;
        double x;
        double y;
        double dataX[] = new double[(int) tempPlanet.getT()];
        double dataY[] = new double[(int) tempPlanet.getT()];

        XYChart.Series series = new XYChart.Series();
        Function function = new Function() {
            @Override
            public double function(double e, double E, double M) {
                return M + e * Math.sin(E) - E;
            }
        };
        double En;
        for (int i = 0; i <= tempPlanet.getT()-1; i++) {
            M = Math.PI * 2 / tempPlanet.getT() * (double) i;
            En = function.function(tempPlanet.getE(), tempDataE1 , M);
            x = (tempPlanet.getA()) * Math.cos(En - tempPlanet.getE());
            dataX[i] = x;
            y = (tempPlanet.getA()) * Math.sqrt(1 - (tempPlanet.getE() * tempPlanet.getE())) * Math.sin(En);
            dataY[i] = y;
            series.getData().add(new XYChart.Data(x, y));
            series.setName(fieldNameInput.getText());
        }

        System.out.println("dataX: " + Arrays.toString(dataX));
        System.out.println("dataY: " + Arrays.toString(dataY));
        System.out.println("tempDataE: " + tempDataE1);
//        System.out.println("tempDataX: " + Arrays.toString());
//        System.out.println("tempDataY: " + Arrays.toString());
        graph.getData().addAll(series);

        return tempPlanet.getT();

    }

    @FXML
    double onKeyPressedDistance(KeyEvent event) {
        double a = 0;
        if (event.getCode().equals(KeyCode.ENTER)) {
            a = Double.parseDouble(passDistance(fieldDistanceInput));
            System.out.println(a);
        }
        return a;
    }

    @FXML
    double onKeyPressedEccentricity(KeyEvent event) {
        double e = 0;
        if (event.getCode().equals(KeyCode.ENTER)) {
            e = Double.parseDouble(passEccentricity(fieldEccentricityInput));
            System.out.println(e);
        }
        return e;
    }

    @FXML
    String onKeyPressedName(KeyEvent event) {
        String finalName = passName(fieldNameInput);
        if (event.getCode().equals(KeyCode.ENTER)) {
            System.out.println(finalName);
        }
        return passName(fieldNameInput);
    }

    @FXML
    double onKeyPressedPeriod(KeyEvent event) {
        double T = 0;
        if (event.getCode().equals(KeyCode.ENTER)) {
            T = Double.parseDouble(passPeriod(fieldPeriodInput));
            System.out.println(T);
        }
        return T;
    }

    /**
     * Basic save file methods to receive a coordinates *.txt file and a chart *.png
     * keeps LocalDate and LocalTime .now() data within the file name;
     */

    @FXML
    void onSaveFile(ActionEvent event) {

        String fileName = "src/main/resources/" + "Orbit " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss")) + ".txt";

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (int i = 1; i <= Double.parseDouble(fieldPeriodInput.getText()); i++)
                fileWriter.write(OX.getValueForDisplay(i) + "\t " + OY.getValueForDisplay(i) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        WritableImage image = graph.snapshot(new SnapshotParameters(), null);

        // TODO: probably use a file chooser here
        File file = new File("src/main/resources/" + "Orbit " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss")) + ".png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }

    }

    /**
     * Clear graph method;
     */

    @FXML
    void onClear(ActionEvent event) {

        graph.getData().clear();

    }


    @FXML
    String passDistance(TextField event) {
        String a;
        event.setFocusTraversable(true);
        a = event.getText();
        return a;
    }

    @FXML
    String passEccentricity(TextField event) {
        String e;
        event.setFocusTraversable(true);
        e = event.getText();
        return e;
    }

    @FXML
    String passName(TextField event) {
        String name;
        event.setFocusTraversable(true);
        name = event.getText();
        return name;
    }

    @FXML
    String passPeriod(TextField event) {
        String T;
        event.setFocusTraversable(true);
        T = event.getText();
        return T;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert fieldNameInput != null : "fx:id=\"fieldNameInput\" was not injected: check your FXML file 'graph.fxml'.";
        assert fieldPeriodInput != null : "fx:id=\"fieldPeriodInput\" was not injected: check your FXML file 'graph.fxml'.";
        assert fieldDistanceInput != null : "fx:id=\"fieldDistanceInput\" was not injected: check your FXML file 'graph.fxml'.";
        assert fieldEccentricityInput != null : "fx:id=\"FieldEccentricityInput\" was not injected: check your FXML file 'graph.fxml'.";
        assert unfocus != null : "fx:id=\"unfocus\" was not injected: check your FXML file 'graph.fxml'.";
        assert switchMethod != null : "fx:id=\"switchMethod\" was not injected: check your FXML file 'graph.fxml'.";
        assert btnDrawOrbit != null : "fx:id=\"btnDrawOrbit\" was not injected: check your FXML file 'graph.fxml'.";
        assert btnSaveFile != null : "fx:id=\"btnSaveFile\" was not injected: check your FXML file 'graph.fxml'.";
        assert graph != null : "fx:id=\"graph\" was not injected: check your FXML file 'graph.fxml'.";
        assert OX != null : "fx:id=\"OX\" was not injected: check your FXML file 'graph.fxml'.";
        assert OY != null : "fx:id=\"OY\" was not injected: check your FXML file 'graph.fxml'.";
        switchMethod.getItems().removeAll(switchMethod.getItems());
        switchMethod.setItems(availableChoices);

        //unfocus pathField
        switchMethod.requestFocus();
        Platform.runLater(() -> fieldNameInput.requestFocus());
        Platform.runLater(() -> fieldPeriodInput.requestFocus());
        Platform.runLater(() -> fieldDistanceInput.requestFocus());
        Platform.runLater(() -> fieldEccentricityInput.requestFocus());
    }

}
