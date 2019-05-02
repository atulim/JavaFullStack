package sample;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNewDoctorController  implements Initializable {

    @FXML
    private AnchorPane otherAnchor;

    @FXML
    TextField name;

    @FXML
    TextField address;

     @FXML
     TextField Complaint;

     @FXML
     Button submit;




    @FXML
    private Button back1;

    @FXML
    private Button ocrb;

    @FXML
    ComboBox<String> city;

    @FXML
    ComboBox<String> state;

    AnchorPane info;

    @FXML
    private Button back;

    AnchorPane doctor;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
        ObservableList<String> list = FXCollections.observableArrayList("Agra","Bhopal","Kanpur");
        city.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList("Arunachal Pradesh","Madhya Pradesh","Uttar Pradesh");
        state.setItems(list2);

    }

    public void backAction(ActionEvent e) {
        HomePageController.getInstance().createPage(info, "HOME.fxml");
    }

    @FXML
    public void AddDoctor(ActionEvent aee1)
    {




        String insert = "INSERT INTO doctorinfo(name,address,city,state, complaint)"
                + "VALUES (?,?,?,?,?)";

        connection = handler.getConnection();
        try{
            pst = connection.prepareStatement(insert);
        }
        catch(SQLException e1)
        {
            e1.printStackTrace();
        }
        try{
            pst.setString(1,name.getText());
            pst.setString(2,address.getText());
            pst.setString(3,city.getValue());
            pst.setString(4,state.getValue());
            pst.setString(5,Complaint.getText());

            pst.executeUpdate();
            submitAction();
        }
        catch(SQLException e1)
        {
            e1.printStackTrace();
        }

    }

    public void OCRRead() throws Exception {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            System.out.println(selectedFile.getAbsolutePath());
        } else {
            System.out.println("File is not valid");
        }

        String inputFilePath = selectedFile.getAbsolutePath();
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Tesseract/");
        String fullText = tesseract.doOCR(new File(inputFilePath));
        System.out.println(fullText);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScannedText.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ScannedTextController sec= fxmlLoader.getController();
            sec.ButtonAction(fullText);
            Stage stage = new Stage();
            stage.setTitle("Scanned Text");
            stage.setScene(new Scene(root1));
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load New Window");

        }


    }

    private void setNode(Node node) {
        otherAnchor.getChildren().clear();
        otherAnchor.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(3000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    private void submitAction()
    {
        try {
            doctor = FXMLLoader.load(getClass().getResource("Registered.fxml"));

            setNode(doctor);
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }
}

