package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane homeAnchor;

    @FXML
    private Button otherInfo;

    @FXML
    private Button add;

    AnchorPane doctor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private void setNode(Node node)
    {
       homeAnchor.getChildren().clear();
        homeAnchor.getChildren().add((Node)node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();

    }
    @FXML
    public void otherAction(ActionEvent e)
    {
        try {
            doctor = FXMLLoader.load(getClass().getResource("OtherInfo.fxml"));

            setNode(doctor);
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    @FXML
    public void addNew(ActionEvent e1)
    {
        try {
            doctor = FXMLLoader.load(getClass().getResource("AddNewDoctor.fxml"));

            setNode(doctor);
        } catch (IOException e2) {

            e2.printStackTrace();
        }
    }
}



