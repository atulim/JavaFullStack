package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ScannedTextController implements Initializable {


    @FXML
    private TextField listview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void ButtonAction(String text)
    {
        if(text !=null)
        listview.setText(text);
        else
            System.out.println("File is not valid");
    }
}
