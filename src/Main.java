import Controller.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        CallTheAppropriateFunction callTheAppropriateFunction = new CallTheAppropriateFunction();
        launch(args);
    }
}