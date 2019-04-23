package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewDoctorController  implements Initializable {

    @FXML
    private AnchorPane otherAnchor;

    @FXML
    private Button back1;

    @FXML
    private Button ocrb;

    AnchorPane info;

    @FXML
    private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backAction(ActionEvent e) {
        HomePageController.getInstance().createPage(info, "HOME.fxml");
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
}

