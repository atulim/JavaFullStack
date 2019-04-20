package sample;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML AnchorPane holderPane;
    AnchorPane home;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Text welcome;

    @FXML
    private Text welcome1;

    @FXML
    private ToolBar toolbar;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnExit;

    private static HomePageController instance;

    public HomePageController()
    {
        instance = this;
    }

    public static HomePageController getInstance()
    {
        return instance;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createPage(home,"Home.fxml");

        setUsername(Controller.getInstance().username());
    }

    public void setUsername(String user)
    {
        this.welcome.setText(" "+user);
        this.welcome1.setText(" "+user);
    }

    private void setNode(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node)node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();

    }
    public void createPage(AnchorPane homen,String loc) {

        try {
            homen = FXMLLoader.load(getClass().getResource(loc));
            setNode(homen);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @FXML
    public void logOut(ActionEvent event) throws IOException
    {
        btnLogout.getScene().getWindow().hide();
        Stage login = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));



        Scene scene =  new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    public void exit(ActionEvent event)
    {
        Platform.exit();

    }
}
