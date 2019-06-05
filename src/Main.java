import Controller.*;
import View.Request;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Request request = Request.getInstance();
        request.signUpMenu(primaryStage);
        primaryStage.setTitle("Duelyst");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        CallTheAppropriateFunction callTheAppropriateFunction = new CallTheAppropriateFunction();
        callTheAppropriateFunction.setDaemon(true);
        callTheAppropriateFunction.start();
        launch(args);
    }
}