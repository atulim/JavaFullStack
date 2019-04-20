package sample;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DBHandler;

public class Controller implements Initializable {

        @FXML
        public Button signup;

        @FXML
        public TextField username;

        @FXML
        public CheckBox remember;

        @FXML
        public Button login;

        @FXML
        public Button forgotpassword;

        @FXML
        public ImageView progress;

        @FXML
        public PasswordField password;

        private DBHandler handler;
        private Connection connection;
        private PreparedStatement pst;
        private static Controller instance;

        public Controller()
        {
            instance = this;
        }
        public static Controller getInstance()
        {
            return instance;
        }
        public String username()
        {
            return username.getText();
        }


        @Override
        public void initialize(URL arg0, ResourceBundle arg1) {

            progress.setVisible(false);
            username.setStyle("-fx-text-inner-color: #a0a2ab;");
            password.setStyle("-fx-text-inner-color: #a0a2ab;");

            handler = new DBHandler();
        }


        @FXML
        public void loginAction(ActionEvent e)
        {
            progress.setVisible(true);
            PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(3));
            pt.setOnFinished(ev -> {

                connection = handler.getConnection();
                String q1 = "SELECT * from doctors where username=? and password=?";
                try
                {
                    pst = connection.prepareStatement(q1);
                    pst.setString(1,username.getText());
                    pst.setString(2,password.getText());
                    ResultSet rs = pst.executeQuery();

                    int count = 0;

                    while(rs.next())
                    {
                        count=count+1;
                    }
                    if(count==1)
                    {
                        login.getScene().getWindow().hide();
                        Stage home = new Stage();
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                            Scene scene = new Scene(root);
                            home.setScene(scene);
                            home.show();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }
                    else
                    {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Username or Password Invalid");
                        progress.setVisible(false);
                        alert.show();
                    }

                }
                catch(SQLException e1)
                {
                    e1.printStackTrace();
                }
                finally
                {
                    try {
                        connection.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

            });

            pt.play();


        }

        @FXML
    public void SignUp(ActionEvent e1) throws IOException
        {
            login.getScene().getWindow().hide();

            Stage signup = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene scene = new Scene(root);
            signup.setScene(scene);
            signup.show();
            signup.setResizable(false);
        }
}