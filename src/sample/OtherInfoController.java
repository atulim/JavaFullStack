package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OtherInfoController implements Initializable {

    @FXML
    private AnchorPane otherAnchor;

    @FXML
            private Button back1;

    AnchorPane info;

   @FXML
   private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void backAction(ActionEvent e)
    {
        HomePageController.getInstance().createPage(info, "HOME.fxml");
    }
}
