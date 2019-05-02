package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setOnCloseRequest( e-> {
            e.consume();
            closeProgram();
        });

        Scene scene = new Scene(root,700,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void closeProgram()
    {
        Boolean answer = ConfirmBox.display("Title","Are you sure you want to exit?");
        if(answer)
            window.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
