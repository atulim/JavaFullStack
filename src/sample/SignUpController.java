package sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import sample.DBHandler;
public class SignUpController implements Initializable {

    @FXML
    private AnchorPane parentPane;

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private TextField emailid;

    @FXML
    private Button signup;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private ImageView progress;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordconfirm;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        progress.setVisible(false);

        handler = new DBHandler();

    }

    @FXML
    public void signUP(ActionEvent ae1)
    {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished( e ->{
          System.out.println("SignUp Successfully");
        });
        pt.play();

        String insert = "INSERT INTO doctors(username,emailid,password,passwordconfirm,gender)"
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
            pst.setString(1,username.getText());
            pst.setString(2,emailid.getText());
            pst.setString(3,password.getText());
            pst.setString(4,passwordconfirm.getText());
            pst.setString(5,getGender());

          pst.executeUpdate();
        }
        catch(SQLException e1)
        {
            e1.printStackTrace();
        }

    }

    @FXML
    public void loginAction(ActionEvent ae2) throws IOException
    {
        signup.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);

    }

    public String getGender(){
        String gen = "";

        if(male.isSelected())
        {
            gen = "Male";
        }
        else
            gen = "Female";
        return gen;
    }
}
