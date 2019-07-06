package Network;

import Controller.*;
import View.Request;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

import static javafx.scene.paint.Color.BURLYWOOD;

public class Client extends Application
{
    private static CallTheAppropriateFunction callTheAppropriateFunction;
    private static SendMessage sendMessage;
    private Request request = new Request(this);
    private String authToken;
    private ServerCommand messageFromServer;

    private static Group rootSignUpMenu = new Group();
    private static Scene sceneSignUpMenu = new Scene(rootSignUpMenu, 400, 400);
    private static Group rootLoginMenu = new Group();
    private static Scene sceneLoginMenu = new Scene(rootLoginMenu, 400, 400);
    private static Group rootMainMenu = new Group();
    private static Scene sceneMainMenu = new Scene(rootMainMenu, 1000, 562);
    private static Group rootLeaderBoard = new Group();
    private static Scene sceneLeaderBoard = new Scene(rootLeaderBoard, 300, 700);
    private static Group rootProfile = new Group();
    private static Scene sceneProfile = new Scene(rootProfile, 300, 300);
    private static Group rootShop = new Group();
    private static ScrollPane scrollPaneShop = new ScrollPane();
    private static Scene sceneShop = new Scene(scrollPaneShop, 1000, 562, BURLYWOOD);
    private static Group rootCollection = new Group();
    private static ScrollPane scrollPaneCollection = new ScrollPane();
    private static Scene sceneCollection = new Scene(scrollPaneCollection, 1000, 562);
    private static Group rootDeck = new Group();
    private static ScrollPane scrollPaneDeck = new ScrollPane();
    private static Scene sceneDeck = new Scene(scrollPaneDeck, 1000, 562);
    private static Group rootBattle = new Group();
    private static Scene sceneBattle = new Scene(rootBattle, 1000, 562);
    private static Group rootSinglePlayer = new Group();
    private static Scene sceneSinglePlayer = new Scene(rootSinglePlayer, 1000, 562);
    private static Group rootMultiPlayer = new Group();
    private static Scene sceneMultiPlayer = new Scene(rootMultiPlayer, 1000, 562);
    private static Group rootCustomGame = new Group();
    private static Scene sceneCustomGame = new Scene(rootCustomGame, 1000, 562);
    private static Group rootBattleField = new Group();
    private static Scene sceneBattleField = new Scene(rootBattleField, 1366, 768);
    private static Group rootImportingDeck = new Group();
    private static Scene sceneImportingDeck = new Scene(rootImportingDeck, 1000, 562);
    private static Group rootMakingCustomCards = new Group();
    private static Scene sceneMakingCustomCards = new Scene(rootMakingCustomCards, 1000, 505);
    private static Group rootGraveYard = new Group();
    private static ScrollPane scrollPaneGraveYard = new ScrollPane();
    private static Scene sceneGraveYard = new Scene(scrollPaneGraveYard, 1000, 562);
    private static Group rootHeroCustom = new Group();
    private static Scene sceneHeroCustom = new Scene(rootHeroCustom, 1000, 562);
    private static Group rootMinionCustom = new Group();
    private static Scene sceneMinionCustom = new Scene(rootMinionCustom, 1000, 562);
    private static Group rootSpellCustom = new Group();
    private static Scene sceneSpellCustom = new Scene(rootSpellCustom, 1000, 548);
    private static Group rootChatMenu = new Group();
    private static Scene sceneChatMenu = new Scene(rootChatMenu, 540, 540);
    private static Group rootChatPage = new Group();
    private static Scene sceneChatPage = new Scene(rootChatPage, 540, 540);

    public static Scene getSceneHeroCustom()
    {
        return sceneHeroCustom;
    }

    public static void setSceneHeroCustom(Scene sceneHeroCustom)
    {
        Client.sceneHeroCustom = sceneHeroCustom;
    }

    public static Group getRootMinionCustom()
    {
        return rootMinionCustom;
    }

    public static Group getRootHeroCustom()
    {
        return rootHeroCustom;
    }

    public static Scene getSceneMinionCustom()
    {
        return sceneMinionCustom;
    }

    public static void setSceneMinionCustom(Scene sceneMinionCustom)
    {
        Client.sceneMinionCustom = sceneMinionCustom;
    }

    public static Scene getSceneSpellCustom()
    {
        return sceneSpellCustom;
    }

    public static Group getRootSpellCustom()
    {
        return rootSpellCustom;
    }

    public static void setSceneSpellCustom(Scene sceneSpellCustom)
    {
        Client.sceneSpellCustom = sceneSpellCustom;
    }

    public static Group getRootChatMenu() {
        return rootChatMenu;
    }

    public static void setRootChatMenu(Group rootChatMenu) {
        Client.rootChatMenu = rootChatMenu;
    }

    public static Scene getSceneChatMenu() {
        return sceneChatMenu;
    }

    public static void setSceneChatMenu(Scene sceneChatMenu) {
        Client.sceneChatMenu = sceneChatMenu;
    }

    public static Group getRootChatPage() {
        return rootChatPage;
    }

    public static void setRootChatPage(Group rootChatPage) {
        Client.rootChatPage = rootChatPage;
    }

    public static Scene getSceneChatPage() {
        return sceneChatPage;
    }

    public static void setSceneChatPage(Scene sceneChatPage) {
        Client.sceneChatPage = sceneChatPage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        connectToServer();
        callTheAppropriateFunction = new CallTheAppropriateFunction(request);
        callTheAppropriateFunction.setDaemon(true);
        callTheAppropriateFunction.start();
        Media sound = new Media(new File("Sounds and Music/StarSky.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        request.signUpMenu(primaryStage);
        Image iconImage = new Image("file:Icon Image.jpg");
        primaryStage.getIcons().add(iconImage);
        primaryStage.setTitle("Duelyst");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    private void connectToServer() throws IOException
    {
        Socket socket = new Socket("127.0.0.1", 9000);

        sendMessage = new SendMessage(socket.getOutputStream());
        sendMessage.start();

        SendMessage output = new SendMessage(System.out);
        output.start();

        InputCommandHandlerForClient handleCommand = new InputCommandHandlerForClient(this, sendMessage);
        handleCommand.setDaemon(true);
        handleCommand.start();

        ReceiveMessage receiveMessage = new ReceiveMessage(socket.getInputStream());
        receiveMessage.addListener(new CommandReceivedListener()
        {
            @Override
            public void onMessageReceived(String message) throws InterruptedException
            {
                handleCommand.setMessage(message);
            }
        });
        receiveMessage.start();
    }

    public static SendMessage getSendMessage()
    {
        return sendMessage;
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

    public static ScrollPane getScrollPaneShop()
    {
        return scrollPaneShop;
    }

    public static Scene getSceneShop()
    {
        return sceneShop;
    }

    public static Group getRootCollection()
    {
        return rootCollection;
    }

    public static ScrollPane getScrollPaneCollection()
    {
        return scrollPaneCollection;
    }

    public static Scene getSceneCollection()
    {
        return sceneCollection;
    }

    public static Group getRootDeck()
    {
        return rootDeck;
    }

    public static ScrollPane getScrollPaneDeck()
    {
        return scrollPaneDeck;
    }

    public static Scene getSceneDeck()
    {
        return sceneDeck;
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

    public static Scene getSceneCustomGame()
    {
        return sceneCustomGame;
    }

    public static Group getRootCustomGame()
    {
        return rootCustomGame;
    }

    public static CallTheAppropriateFunction getCallTheAppropriateFunction()
    {
        return callTheAppropriateFunction;
    }

    public static Group getRootProfile()
    {
        return rootProfile;
    }

    public static Scene getSceneProfile()
    {
        return sceneProfile;
    }

    public static Scene getSceneImportingDeck()
    {
        return sceneImportingDeck;
    }

    public static Group getRootImportingDeck()
    {
        return rootImportingDeck;
    }

    public static Scene getSceneBattleField()
    {
        return sceneBattleField;
    }

    public static Group getRootBattleField()
    {
        return rootBattleField;
    }

    public static Scene getSceneMakingCustomCards()
    {
        return sceneMakingCustomCards;
    }

    public static Group getRootMakingCustomCards()
    {
        return rootMakingCustomCards;
    }

    public static Group getRootGraveYard()
    {
        return rootGraveYard;
    }

    public static ScrollPane getScrollPaneGraveYard()
    {
        return scrollPaneGraveYard;
    }

    public static Scene getSceneGraveYard()
    {
        return sceneGraveYard;
    }

    public Request getRequest()
    {
        return request;
    }

    public String getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public ServerCommand getMessageFromServer()
    {
        return messageFromServer;
    }

    public void setMessageFromServer(ServerCommand messageFromServer)
    {
        this.messageFromServer = messageFromServer;
    }
}