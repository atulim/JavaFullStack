package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try
        {Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));

            primaryStage.setScene(new Scene(root, 732, 329));

            primaryStage.show();
            primaryStage.setResizable(false);}
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
