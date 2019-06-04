import Controller.*;
import View.Request;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Request request = new Request();
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