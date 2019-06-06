package View;

import Controller.*;
import View.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.BURLYWOOD;

public class Main extends Application
{
    private static Group rootSignUpMenu = new Group();
    private static Scene sceneSignUpMenu = new Scene(rootSignUpMenu, 400, 400);
    private static Group rootLoginMenu = new Group();
    private static Scene sceneLoginMenu = new Scene(rootLoginMenu, 400, 400);
    private static Group rootMainMenu = new Group();
    private static Scene sceneMainMenu = new Scene(rootMainMenu, 1000, 562);
    private static Group rootLeaderBoard = new Group();
    private static Scene sceneLeaderBoard = new Scene(rootLeaderBoard, 300, 700);
    private static Group rootShop = new Group();
    private static ScrollPane scrollPane = new ScrollPane();
    private static Scene sceneShop = new Scene(scrollPane, 1000, 562, BURLYWOOD);
    private static Group rootCollection = new Group();
    private static Scene sceneCollection = new Scene(rootCollection, 1000, 562);
    private static Group rootBattle = new Group();
    private static Scene sceneBattle = new Scene(rootBattle, 1000, 562);
    private static Group rootSinglePlayer = new Group();
    private static Scene sceneSinglePlayer = new Scene(rootSinglePlayer, 1000, 562);
    private static Group rootMultiPlayer = new Group();
    private static Scene sceneMultiPlayer = new Scene(rootMultiPlayer, 1000, 562);

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

    public static Group getRootSignUpMenu()
    {
        return rootSignUpMenu;
    }

    public static Scene getSceneSignUpMenu()
    {
        return sceneSignUpMenu;
    }

    public static Group getRootLoginMenu()
    {
        return rootLoginMenu;
    }

    public static Scene getSceneLoginMenu()
    {
        return sceneLoginMenu;
    }

    public static Group getRootMainMenu()
    {
        return rootMainMenu;
    }

    public static Scene getSceneMainMenu()
    {
        return sceneMainMenu;
    }

    public static Group getRootLeaderBoard()
    {
        return rootLeaderBoard;
    }

    public static Scene getSceneLeaderBoard()
    {
        return sceneLeaderBoard;
    }

    public static Group getRootShop()
    {
        return rootShop;
    }

    public static ScrollPane getScrollPane()
    {
        return scrollPane;
    }

    public static Scene getSceneShop()
    {
        return sceneShop;
    }

    public static Group getRootCollection()
    {
        return rootCollection;
    }

    public static Scene getSceneCollection()
    {
        return sceneCollection;
    }

    public static Group getRootBattle()
    {
        return rootBattle;
    }

    public static Scene getSceneBattle()
    {
        return sceneBattle;
    }

    public static Group getRootSinglePlayer()
    {
        return rootSinglePlayer;
    }

    public static Scene getSceneSinglePlayer()
    {
        return sceneSinglePlayer;
    }

    public static Group getRootMultiPlayer()
    {
        return rootMultiPlayer;
    }

    public static Scene getSceneMultiPlayer()
    {
        return sceneMultiPlayer;
    }
}