package View;

import Controller.BattleFieldController;
import Model.*;
import Network.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import static javafx.scene.paint.Color.*;

@SuppressWarnings({"Duplicates", "SwitchStatementWithoutDefaultBranch"})
public class Request
{
    public static Scanner myScanner = new Scanner(System.in);

    private final static Pattern patternSearch = Pattern.compile("search [a-zA-Z_0-9]+");
    private final static Pattern patternCreateDeck = Pattern.compile("create deck [a-zA-Z_0-9]+");
    private final static Pattern patternDeleteDeck = Pattern.compile("delete deck [a-zA-Z_0-9]+");
    private final static Pattern patternAddCardToDeck = Pattern.compile("add [a-zA-Z_0-9]+ to deck [a-zA-Z_0-9]+");
    private final static Pattern patternRemoveCardFromDeck = Pattern.compile("remove [a-zA-Z_0-9]+ from deck [a-zA-Z_0-9]+");
    private final static Pattern patternValidateDeck = Pattern.compile("validate deck [a-zA-Z_0-9]+");
    private final static Pattern patternSelectMainDeck = Pattern.compile("select deck [a-zA-Z_0-9]+");
    private final static Pattern patternShowDeck = Pattern.compile("show deck [a-zA-Z_0-9]+");
    private final static Pattern patternCreateAccount = Pattern.compile("create account [a-zA-Z_0-9]+");
    private final static Pattern patternAccountLogin = Pattern.compile("login [a-zA-Z_0-9]+");
    private final static Pattern patternShopSearchCollection = Pattern.compile("search collection [a-zA-Z_0-9]+");
    private final static Pattern patternShopBuy = Pattern.compile("buy [a-zA-Z_0-9]+");
    private final static Pattern patternShopSell = Pattern.compile("sell [a-zA-Z_0-9]+");
    private final static Pattern patternShowInfoOfCardInGraveYard = Pattern.compile("Show info [a-zA-Z_0-9]+");
    private final static Pattern patternShowCardInfo = Pattern.compile("Show info [a-zA-Z_0-9]+");
    private final static Pattern patternSelect = Pattern.compile("Select (.)+");
    private final static Pattern patternSelectUser = Pattern.compile("Select User [a-zA-Z_0-9]+");
    private final static Pattern patternStartMultiPlayerGame = Pattern.compile("Start MultiPlayer Game [a-zA-Z_0-9]+[\\t\\f\\r]?[0-9]*");
    private final static Pattern patternComboAttack = Pattern.compile("Attack combo (([a-zA-Z_0-9]+)(\\s))+");
    private final static Pattern patternSelectItem = Pattern.compile("Select [a-zA-Z_0-9]+");
    private final static Pattern patternUseItem = Pattern.compile("Use [0-9]+ [0-9]+");
    private final static Pattern patternNormalAttack = Pattern.compile("Attack [a-zA-Z_0-9]+");
    private final static Pattern patternUseSpecialPower = Pattern.compile("Use special power( [0-9]+ [0-9]+ )");
    private final static Pattern patternInsertCard = Pattern.compile("Insert [a-zA-Z 0-9]+ in ((\\() [0-9]+ [,] [0-9]+ (\\)))");

    private ShowOutput showOutput = ShowOutput.getInstance();
    private CommandType command;
    public final Object requestLock = new Object();
    public final Object validMessageFromServer = new Object();
    private Client client;

    public CommandType getCommand()
    {
        return command;
    }

    public void setCommand(CommandType command)
    {
        this.command = command;
    }

    private static final int ROW_BLANK = 20;
    private static final int COLUMN_BLANK = 80;
    private static final int BLANK_BETWEEN_CARDS = 50;
    private static final int CARDS_RECTANGLE_WIDTH = 200;
    private static final int CARDS_RECTANGLE_HEIGHT = 250;

    private Group rootSignUpMenu = Client.getRootSignUpMenu();
    private Scene sceneSignUpMenu = Client.getSceneSignUpMenu();
    private Group rootLoginMenu = Client.getRootLoginMenu();
    private Scene sceneLoginMenu = Client.getSceneLoginMenu();
    private Group rootMainMenu = Client.getRootMainMenu();
    private Scene sceneMainMenu = Client.getSceneMainMenu();
    private Group rootLeaderBoard = Client.getRootLeaderBoard();
    private Scene sceneLeaderBoard = Client.getSceneLeaderBoard();
    private Group rootProfile = Client.getRootProfile();
    private Scene sceneProfile = Client.getSceneProfile();
    private Group rootShop = Client.getRootShop();
    private ScrollPane scrollPaneShop = Client.getScrollPaneShop();
    private Scene sceneShop = Client.getSceneShop();
    private Group rootCollection = Client.getRootCollection();
    private ScrollPane scrollPaneCollection = Client.getScrollPaneCollection();
    private Group rootDeck = Client.getRootDeck();
    private ScrollPane scrollPaneDeck = Client.getScrollPaneDeck();
    private Scene sceneDeck = Client.getSceneDeck();
    private Scene sceneCollection = Client.getSceneCollection();
    private Group rootBattleMenu = Client.getRootBattle();
    private Scene sceneBattleMenu = Client.getSceneBattle();
    private Group rootSinglePlayer = Client.getRootSinglePlayer();
    private Scene sceneSinglePlayer = Client.getSceneSinglePlayer();
    private Group rootMultiPlayer = Client.getRootMultiPlayer();
    private Scene sceneMultiPlayer = Client.getSceneMultiPlayer();
    private Group rootStoryMode = Client.getRootMultiPlayer();
    private Scene sceneStoryMode = Client.getSceneMultiPlayer();
    private Group rootCustomGame = Client.getRootCustomGame();
    private Scene sceneCustomGame = Client.getSceneCustomGame();
    private Scene sceneImportingDeck = Client.getSceneImportingDeck();
    private Group rootImportingDeck = Client.getRootImportingDeck();
    private Group rootBattleField = Client.getRootBattleField();
    private Scene sceneBattleField = Client.getSceneBattleField();
    private Group rootMakingCustomCard = Client.getRootMakingCustomCards();
    private Scene sceneMakingCustomCards = Client.getSceneMakingCustomCards();
    private Group rootGraveYard = Client.getRootGraveYard();
    private ScrollPane scrollPaneGraveYard = Client.getScrollPaneGraveYard();
    private Scene sceneGraveYard = Client.getSceneGraveYard();
    private Scene sceneHeroCustom = Client.getSceneHeroCustom();
    private Group rootHeroCustom = Client.getRootHeroCustom();
    private Scene sceneMinionCustom = Client.getSceneMinionCustom();
    private Group rootMinionCustom = Client.getRootMinionCustom();
    private Group rootSpellCustom = Client.getRootSpellCustom();
    private Scene sceneSpellCustom = Client.getSceneSpellCustom();
    private Group rootChatPage = Client.getRootChatPage();
    private Scene sceneChatPage = Client.getSceneChatPage();
    private Deck selectedDeckForCustomGame = null;
    private BattleFieldController battleFieldController;
    private Account multiPlayerAccountToBattle;
    private Text battleInfo;
    private Account accountConnectedToThisClient;

    public Request(Client client)
    {
        this.client = client;
    }

    public void signUpMenu(Stage primaryStage)
    {
        rootSignUpMenu.getChildren().clear();

        TextField textFieldName = new TextField();
        PasswordField textFieldPassword = new PasswordField();
        nameAndPasswordFields(rootSignUpMenu, textFieldName, textFieldPassword);

        Label labelSignUp = new Label("Sign Up");
        rootSignUpMenu.getChildren().add(labelSignUp);
        labelSignUp.setFont(Font.font(25));
        labelSignUp.relocate(150, 30);
        labelSignUp.setTextFill(Color.BLACK);

        Button buttonSignUp = new Button("Submit");
        Label labelInvalidInput = new Label();
        submitButton(buttonSignUp, labelInvalidInput);
        buttonSignUp.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                rootSignUpMenu.getChildren().remove(labelInvalidInput);
                String userName = textFieldName.getText();
                String password = textFieldPassword.getText();
                ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.SIGN_UP, userName, password);
                String signUpJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                System.out.println(signUpJson);
                try
                {
                    Client.getSendMessage().addMessage(signUpJson);
                    synchronized (validMessageFromServer)
                    {
                        validMessageFromServer.wait();
                    }
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (client.getMessageFromServer().getServerCommandEnum().equals(ServerCommandEnum.OK))
                {
                    primaryStage.setScene(Client.getSceneLoginMenu());
                    primaryStage.centerOnScreen();
                    login(primaryStage);
                }
                else
                {
                    labelInvalidInput.setText(client.getMessageFromServer().getErrorMessage());
                }
                rootSignUpMenu.getChildren().add(labelInvalidInput);
            }
        });
        rootSignUpMenu.getChildren().add(buttonSignUp);

        Button buttonAlreadyHaveAccount = new Button("Already have account");
        buttonAlreadyHaveAccount.relocate(150, 300);
        buttonAlreadyHaveAccount.setFont(Font.font(20));
        buttonAlreadyHaveAccount.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                rootSignUpMenu.getChildren().remove(labelInvalidInput);
                primaryStage.setScene(sceneLoginMenu);
                primaryStage.centerOnScreen();
                login(primaryStage);
            }
        });
        rootSignUpMenu.getChildren().add(buttonAlreadyHaveAccount);

        primaryStage.setScene(sceneSignUpMenu);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }


    private void login(Stage primaryStage)
    {
        Label labelLogin = new Label("Login");
        rootLoginMenu.getChildren().add(labelLogin);
        labelLogin.relocate(150, 30);
        labelLogin.setFont(Font.font(25));
        labelLogin.setTextFill(Color.BLACK);

        TextField textFieldName = new TextField();
        PasswordField textFieldPassword = new PasswordField();
        nameAndPasswordFields(rootLoginMenu, textFieldName, textFieldPassword);

        Button buttonLogin = new Button("Submit");
        Label labelInvalidInput = new Label();
        submitButton(buttonLogin, labelInvalidInput);
        buttonLogin.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                rootLoginMenu.getChildren().remove(labelInvalidInput);
                String name = textFieldName.getText();
                String password = textFieldPassword.getText();
                ClientCommand LogInClientCommand = new ClientCommand(ClientCommandEnum.LOGIN, name, password);
                String loginJson = new GsonBuilder().setPrettyPrinting().create().toJson(LogInClientCommand);
                System.out.println(loginJson);
                try
                {
                    Client.getSendMessage().addMessage(loginJson);
                    synchronized (validMessageFromServer)
                    {
                        validMessageFromServer.wait();
                    }
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (client.getMessageFromServer().getServerCommandEnum().equals(ServerCommandEnum.OK))
                {
                    client.setAuthToken(client.getMessageFromServer().getAuthToken());
                    primaryStage.setScene(sceneMainMenu);
                    primaryStage.centerOnScreen();
                    ClientCommand saveClientCommand = new ClientCommand(ClientCommandEnum.GET_ACCOUNT, client.getAuthToken());
                    String saveJson = new GsonBuilder().setPrettyPrinting().create().toJson(saveClientCommand);
                    System.out.println(saveJson);
                    try
                    {
                        Client.getSendMessage().addMessage(saveJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    accountConnectedToThisClient = client.getMessageFromServer().getAccount();
                    mainMenu(primaryStage);
                }
                else
                {
                    labelInvalidInput.setText(client.getMessageFromServer().getMessage());
                }
                rootLoginMenu.getChildren().add(labelInvalidInput);
            }
        });
        rootLoginMenu.getChildren().add(buttonLogin);

        Button buttonNeedToSignUp = new Button("Sign Up");
        buttonNeedToSignUp.relocate(260, 300);
        buttonNeedToSignUp.setFont(Font.font(20));
        buttonNeedToSignUp.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                rootSignUpMenu.getChildren().remove(labelInvalidInput);
                primaryStage.setScene(sceneSignUpMenu);
                primaryStage.centerOnScreen();
                try
                {
                    signUpMenu(primaryStage);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        rootLoginMenu.getChildren().add(buttonNeedToSignUp);

        primaryStage.setScene(sceneLoginMenu);
        primaryStage.centerOnScreen();
    }

    private void nameAndPasswordFields(Group root, TextField textFieldName, PasswordField passwordFieldPassword)
    {
        Label labelName = new Label("Name");
        root.getChildren().add(labelName);
        labelName.relocate(20, 130);
        labelName.setFont(Font.font(15));
        labelName.setTextFill(Color.BLACK);

        HBox hBoxName = new HBox(textFieldName);
        textFieldName.setPromptText("Name");
        hBoxName.relocate(115, 130);
        root.getChildren().add(hBoxName);

        Label labelPassword = new Label("Password");
        root.getChildren().add(labelPassword);
        labelPassword.relocate(20, 210);
        labelPassword.setFont(Font.font(15));
        labelPassword.setTextFill(Color.BLACK);

        HBox hBoxPassword = new HBox(passwordFieldPassword);
        passwordFieldPassword.setPromptText("Password");
        hBoxPassword.relocate(115, 210);
        root.getChildren().add(hBoxPassword);
    }

    private void submitButton(Button button, Label labelInvalidInput)
    {
        button.relocate(25, 300);
        button.setFont(Font.font(20));
        labelInvalidInput.relocate(100, 100);
        labelInvalidInput.setFont(Font.font(15));
        labelInvalidInput.setTextFill(Color.RED);
    }

    private void mainMenu(Stage primaryStage)
    {
        setBackGroundImage(rootMainMenu, "file:BackGround Images/Duelyst Menu.jpg");

        Text duelyst = new Text("Duelyst");
        duelyst.setTextOrigin(VPos.TOP);
        duelyst.setFont(Font.font(null, FontWeight.BOLD, 60));
        duelyst.layoutXProperty().bind(sceneMainMenu.widthProperty().subtract(duelyst.prefWidth(-1)).divide(2));
        rootMainMenu.getChildren().add(duelyst);

        setGlobalChatButton(primaryStage, rootMainMenu);

        setMainMenuText(primaryStage, "Battle", 80);
        setMainMenuText(primaryStage, "Shop", 135);
        setMainMenuText(primaryStage, "Collection", 190);
        setMainMenuText(primaryStage, "LeaderBoard", 245);
        setMainMenuText(primaryStage, "Save", 300);
        setMainMenuText(primaryStage, "Profile", 355);
        setMainMenuText(primaryStage, "CustomCard", 410);
        setMainMenuText(primaryStage, "Logout", 465);
        setMainMenuText(primaryStage, "Exit", 520);

        primaryStage.setScene(sceneMainMenu);
    }

    private void setBackGroundImage(Group root, String url)
    {
        Image backGroundImage = new Image(url);
        ImageView backGroundImageView = new ImageView(backGroundImage);
        root.getChildren().add(backGroundImageView);
    }

    private void setMainMenuText(Stage primaryStage, String string, int yProperty)
    {
        Text text = new Text(string);
        text.setTextOrigin(VPos.TOP);
        text.setFont(Font.font(null, FontWeight.SEMI_BOLD, 30));
        text.layoutXProperty().bind(sceneMainMenu.widthProperty().subtract(text.prefWidth(-1)).divide(2));
        text.setY(yProperty);
        text.setFill(Color.BLUE);
        text.setOnMouseEntered(event -> text.setFill(Color.RED));
        text.setOnMouseExited(event -> text.setFill(Color.BLUE));
        text.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Media sound = new Media(new File("Sounds and Music/Click.mp3").toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                switch (string)
                {
                    case "Shop":
                        setCommand(CommandType.ENTER_SHOP);
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        shopMenu(primaryStage, false, null);
                        break;
                    case "Collection":
                        setCommand(CommandType.ENTER_COLLECTION);
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        rootCollection.getChildren().clear();
                        collectionMenu(primaryStage, false, null);
                        break;
                    case "Battle":
                        setCommand(CommandType.ENTER_BATTLE);
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        battleMenu(primaryStage);
                        break;
                    case "LeaderBoard":
                        setCommand(CommandType.SHOW_LEADER_BOARD);
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        leaderBoard(primaryStage);
                        break;
                    case "Save":
                        ClientCommand saveClientCommand = new ClientCommand(ClientCommandEnum.SAVE_ACCOUNT_INFO, client.getAuthToken());
                        String saveJson = new GsonBuilder().setPrettyPrinting().create().toJson(saveClientCommand);
                        System.out.println(saveJson);
                        try
                        {
                            Client.getSendMessage().addMessage(saveJson);
                            synchronized (validMessageFromServer)
                            {
                                validMessageFromServer.wait();
                            }
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    case "Profile":
                        setCommand(CommandType.SHOW_PROFILE);
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        showProfile(primaryStage);
                        break;
                    case "CustomCard":
                        setCommand(CommandType.CUSTOMCARDS);
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        makingCustomCards(primaryStage);
                        break;
                    case "Logout":
                        ClientCommand LogoutClientCommand = new ClientCommand(ClientCommandEnum.LOGOUT, client.getAuthToken());
                        String logoutJson = new GsonBuilder().setPrettyPrinting().create().toJson(LogoutClientCommand);
                        System.out.println(logoutJson);
                        try
                        {
                            Client.getSendMessage().addMessage(logoutJson);
                            synchronized (validMessageFromServer)
                            {
                                validMessageFromServer.wait();
                            }
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        login(primaryStage);
                        break;
                    case "Exit":
                        ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.SAVE_SHOP, client.getAuthToken());
                        String buyJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                        try
                        {
                            Client.getSendMessage().addMessage(buyJson);
                            synchronized (validMessageFromServer)
                            {
                                validMessageFromServer.wait();
                            }
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        ClientCommand closeClientCommand = new ClientCommand(ClientCommandEnum.LOGOUT, client.getAuthToken());
                        String closeJson = new GsonBuilder().setPrettyPrinting().create().toJson(closeClientCommand);
                        System.out.println(closeJson);
                        try
                        {
                            Client.getSendMessage().addMessage(closeJson);
                            synchronized (validMessageFromServer)
                            {
                                validMessageFromServer.wait();
                            }
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        setCommand(CommandType.EXIT);
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        break;
                }
            }
        });
        rootMainMenu.getChildren().add(text);
    }

    private void makingCustomCards(Stage stage)
    {
        setBackGroundImage(rootMakingCustomCard, "file:BackGround Images/custom1.jpg");

        Text text = new Text("Choose one of the below");
        text.setFont(Font.font(45));
        text.layoutXProperty().bind(sceneMakingCustomCards.widthProperty().subtract(text.prefWidth(-1)).divide(2));
        text.setY(50);
        text.setFill(BLACK);

        Text hero = new Text("Hero");
        hero.setFont(Font.font(null, FontWeight.SEMI_BOLD, 40));
        hero.layoutXProperty().bind(sceneMakingCustomCards.widthProperty().subtract(hero.prefWidth(-1)).divide(2));
        hero.setY(150);

        Text spell = new Text("Spell");
        spell.setFont(Font.font(null, FontWeight.SEMI_BOLD, 40));
        spell.layoutXProperty().bind(sceneMakingCustomCards.widthProperty().subtract(spell.prefWidth(-1)).divide(2));
        spell.setY(250);

        Text minion = new Text("Minion");
        minion.setFont(Font.font(null, FontWeight.SEMI_BOLD, 40));
        minion.layoutXProperty().bind(sceneMakingCustomCards.widthProperty().subtract(minion.prefWidth(-1)).divide(2));
        minion.setY(350);

        hero.setOnMouseEntered(event -> hero.setFill(BLUE));
        hero.setOnMouseExited(event -> hero.setFill(BLACK));
        hero.setOnMouseClicked(event -> {
            heroPage(stage);
        });
        minion.setOnMouseEntered(event -> minion.setFill(BLUE));
        minion.setOnMouseExited(event -> minion.setFill(BLACK));
        minion.setOnMouseClicked(event -> {
            minionPage(stage);
        });
        spell.setOnMouseEntered(event -> spell.setFill(BLUE));
        spell.setOnMouseExited(event -> spell.setFill(BLACK));
        spell.setOnMouseClicked(event -> {
            spellPage(stage);
        });
        backButton(stage, rootMakingCustomCard, 900, 450);
        rootMakingCustomCard.getChildren().addAll(minion, spell, hero, text);
        stage.setScene(sceneMakingCustomCards);
    }

    private void spellPage(Stage stage)
    {
        setBackGroundImage(rootSpellCustom, "file:BackGround Images/spellCustom1.jpg");
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(makingTextField(rootSpellCustom, 30, 30, "name"));
        textFields.add(makingTextField(rootSpellCustom, 30, 130, "numOfTarget"));
        textFields.add(makingTextField(rootSpellCustom, 30, 230, "kindOfMinion"));
        textFields.add(makingTextField(rootSpellCustom, 30, 330, "nameOfBuff"));
        textFields.add(makingTextField(rootSpellCustom, 30, 450, "buffType"));
        textFields.add(makingTextField(rootSpellCustom, 200, 30, "effectValue"));
        textFields.add(makingTextField(rootSpellCustom, 200, 130, "delay"));
        textFields.add(makingTextField(rootSpellCustom, 200, 230, "last"));
        textFields.add(makingTextField(rootSpellCustom, 200, 330, "friendOrEnemy"));
        textFields.add(makingTextField(rootSpellCustom, 200, 450, "numOfFriendOrEnemy"));
        textFields.add(makingTextField(rootSpellCustom, 370, 30, "isAll"));
        textFields.add(makingTextField(rootSpellCustom, 370, 130, "Mp"));
        textFields.add(makingTextField(rootSpellCustom, 370, 230, "numOfHolyBuff"));
        textFields.add(makingTextField(rootSpellCustom, 370, 330, "changingHP"));
        textFields.add(makingTextField(rootSpellCustom, 370, 450, "changingAp"));
        textFields.add(makingTextField(rootSpellCustom, 540, 30, "changingMp"));
        textFields.add(makingTextField(rootSpellCustom, 540, 130, "cost"));
        textFields.add(makingTextField(rootSpellCustom, 540, 230, "numOfOwnMinion"));
        textFields.add(makingTextField(rootSpellCustom, 540, 330, "numOfOpponentMinion"));
        textFields.add(makingTextField(rootSpellCustom, 540, 450, "ownHero"));
        textFields.add(makingTextField(rootSpellCustom, 710, 30, "opponentHero"));
        textFields.add(makingTextField(rootSpellCustom, 710, 130, "numOfOpponentBothNonSpell"));
        textFields.add(makingTextField(rootSpellCustom, 710, 230, "numOfOwnBothNonSpell"));
        textFields.add(makingTextField(rootSpellCustom, 710, 330, "allOwnMinion"));
        textFields.add(makingTextField(rootSpellCustom, 710, 450, "allOpponentBothNonSpell"));
        textFields.add(makingTextField(rootSpellCustom, 865, 30, "allOwnBothNonSpell"));
        Button back = new Button("Back");
        back.setFont(Font.font(25));
        back.relocate(900, 490);
        back.setOnMouseClicked(event -> {
            stage.setScene(sceneMakingCustomCards);
        });
        Button apply = new Button("Apply");
        apply.relocate(780, 490);
        apply.setFont(Font.font(25));
        apply.setOnMouseClicked(event -> {
            ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.MAKE_CUSTOM_SPELL, textFields, client.getAuthToken());
            String spellJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
            try
            {
                Client.getSendMessage().addMessage(spellJson);
                synchronized (validMessageFromServer)
                {
                    validMessageFromServer.wait();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            //workingOnSpellText(textFields);  //moved to spell                //5 constructor
        });
        rootSpellCustom.getChildren().addAll(back, apply);
        stage.setScene(sceneSpellCustom);
    }


    private void minionPage(Stage stage)
    {
        setBackGroundImage(rootMinionCustom, "file:BackGround Images/minionCustom1.jpg");
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(makingTextField(rootMinionCustom, 30, 30, "name"));
        textFields.add(makingTextField(rootMinionCustom, 30, 130, "Ap"));
        textFields.add(makingTextField(rootMinionCustom, 30, 230, "Hp"));
        textFields.add(makingTextField(rootMinionCustom, 30, 330, "AttackType"));
        textFields.add(makingTextField(rootMinionCustom, 30, 450, "Range"));
        textFields.add(makingTextField(rootMinionCustom, 200, 30, "specialActivation"));
        textFields.add(makingTextField(rootMinionCustom, 200, 130, "cost"));
        textFields.add(makingTextField(rootMinionCustom, 200, 230, "turnsToApply"));
        textFields.add(makingTextField(rootMinionCustom, 200, 330, "isPositive"));
        textFields.add(makingTextField(rootMinionCustom, 200, 450, "applyChangeToUntil"));
        textFields.add(makingTextField(rootMinionCustom, 370, 30, "changeAp"));
        textFields.add(makingTextField(rootMinionCustom, 370, 130, "changeHP"));
        textFields.add(makingTextField(rootMinionCustom, 370, 230, "changeMP"));
        textFields.add(makingTextField(rootMinionCustom, 370, 330, "stun"));
        textFields.add(makingTextField(rootMinionCustom, 370, 450, "disarm"));
        textFields.add(makingTextField(rootMinionCustom, 540, 30, "numOfHolyBuff"));
        textFields.add(makingTextField(rootMinionCustom, 540, 130, "toxic"));
        textFields.add(makingTextField(rootMinionCustom, 540, 230, "cellHoly"));
        textFields.add(makingTextField(rootMinionCustom, 540, 330, "Fiery"));
        textFields.add(makingTextField(rootMinionCustom, 540, 450, "combo"));
        textFields.add(makingTextField(rootMinionCustom, 710, 30, "numOfOwnMinion"));
        textFields.add(makingTextField(rootMinionCustom, 710, 130, "numOfOpponentMinion"));
        textFields.add(makingTextField(rootMinionCustom, 710, 230, "ownHero"));
        textFields.add(makingTextField(rootMinionCustom, 710, 330, "opponentHero"));
        textFields.add(makingTextField(rootMinionCustom, 710, 450, "numOfOpponentBothNonSpell"));
        textFields.add(makingTextField(rootMinionCustom, 865, 30, "numOfOwnBothNonSpell"));
        textFields.add(makingTextField(rootMinionCustom, 865, 130, "allOwnMinion"));
        textFields.add(makingTextField(rootMinionCustom, 865, 230, "allOpponentBothNonSpell"));
        textFields.add(makingTextField(rootMinionCustom, 865, 330, "allOwnBothNonSpell"));
        Button back = new Button("Back");
        back.setFont(Font.font(25));
        back.relocate(900, 505);
        back.setOnMouseClicked(event -> {
            stage.setScene(sceneMakingCustomCards);
        });
        Button apply = new Button("Apply");
        apply.relocate(780, 505);
        apply.setFont(Font.font(25));
        apply.setOnMouseClicked(event -> {
            ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.MAKE_CUSTOM_MINION, textFields, client.getAuthToken());
            String MinionJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
            try
            {
                Client.getSendMessage().addMessage(MinionJson);
                synchronized (validMessageFromServer)
                {
                    validMessageFromServer.wait();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            //workingOnMinionText(textFields);   //5
        });

        rootMinionCustom.getChildren().addAll(back, apply);
        stage.setScene(sceneMinionCustom);
    }


    private void heroPage(Stage stage)
    {
        setBackGroundImage(rootHeroCustom, "file:BackGround Images/HeroCustom.jpg");
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(makingTextField(rootHeroCustom, 30, 30, "name"));
        textFields.add(makingTextField(rootHeroCustom, 30, 130, "Ap"));
        textFields.add(makingTextField(rootHeroCustom, 30, 230, "Hp"));
        textFields.add(makingTextField(rootHeroCustom, 30, 330, "AttackType"));
        textFields.add(makingTextField(rootHeroCustom, 30, 450, "Range"));
        textFields.add(makingTextField(rootHeroCustom, 200, 30, "coolDown"));
        textFields.add(makingTextField(rootHeroCustom, 200, 130, "cost"));
        textFields.add(makingTextField(rootHeroCustom, 200, 230, "turnsToApply"));
        textFields.add(makingTextField(rootHeroCustom, 200, 330, "isPositive"));
        textFields.add(makingTextField(rootHeroCustom, 200, 450, "applyChangeToUntil"));
        textFields.add(makingTextField(rootHeroCustom, 370, 30, "changeAp"));
        textFields.add(makingTextField(rootHeroCustom, 370, 130, "changeHP"));
        textFields.add(makingTextField(rootHeroCustom, 370, 230, "changeMP"));
        textFields.add(makingTextField(rootHeroCustom, 370, 330, "stun"));
        textFields.add(makingTextField(rootHeroCustom, 370, 450, "disarm"));
        textFields.add(makingTextField(rootHeroCustom, 540, 30, "numOfHolyBuff"));
        textFields.add(makingTextField(rootHeroCustom, 540, 130, "toxic"));
        textFields.add(makingTextField(rootHeroCustom, 540, 230, "cellHoly"));
        textFields.add(makingTextField(rootHeroCustom, 540, 330, "Fiery"));
        textFields.add(makingTextField(rootHeroCustom, 540, 450, "killing"));
        textFields.add(makingTextField(rootHeroCustom, 710, 30, "numOfOwnMinion"));
        textFields.add(makingTextField(rootHeroCustom, 710, 130, "numOfOpponentMinion"));
        textFields.add(makingTextField(rootHeroCustom, 710, 230, "ownHero"));
        textFields.add(makingTextField(rootHeroCustom, 710, 330, "opponentHero"));
        textFields.add(makingTextField(rootHeroCustom, 710, 450, "numOfOpponentBothNonSpell"));
        textFields.add(makingTextField(rootHeroCustom, 865, 30, "numOfOwnBothNonSpell"));
        textFields.add(makingTextField(rootHeroCustom, 865, 130, "allOwnMinion"));
        textFields.add(makingTextField(rootHeroCustom, 865, 230, "allOpponentBothNonSpell"));
        textFields.add(makingTextField(rootHeroCustom, 865, 330, "allOwnBothNonSpell"));
        Button apply = new Button("Apply");
        apply.relocate(780, 505);
        apply.setFont(Font.font(25));
        apply.setOnMouseClicked(event -> {
            ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.MAKE_CUSTOM_HERO, textFields, client.getAuthToken());
            String HeroJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
            try
            {
                Client.getSendMessage().addMessage(HeroJson);
                synchronized (validMessageFromServer)
                {
                    validMessageFromServer.wait();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            //workingOnHeroText(textFields);    //5 constructor
        });
        Button back = new Button("Back");
        back.relocate(900, 505);
        back.setFont(Font.font(25));
        back.setOnMouseClicked(event -> {
            stage.setScene(sceneMakingCustomCards);
        });
        rootHeroCustom.getChildren().addAll(back, apply);
        stage.setScene(sceneHeroCustom);
    }


    private TextField makingTextField(Group root, int x, int y, String text)
    {
        TextField textField = new TextField();
        textField.setFont(Font.font("SanSerif", 15));
        textField.setPromptText(text);
        textField.setMaxWidth(150);
        textField.relocate(x, y);
        root.getChildren().add(textField);
        return textField;
    }

    private void showProfile(Stage primaryStage)
    {
        ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.GET_ACCOUNT, client.getAuthToken());
        String buyJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
        try
        {
            Client.getSendMessage().addMessage(buyJson);
            synchronized (validMessageFromServer)
            {
                validMessageFromServer.wait();
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Account account = client.getMessageFromServer().getAccount();

        rootProfile.getChildren().clear();

        Label labelProfile = new Label(account.getAccountName());          //3 constructor
        labelProfile.setFont(Font.font(40));
        labelProfile.setTextFill(Color.RED);
        labelProfile.relocate(50, 25);
        labelProfile.layoutXProperty().bind(sceneProfile.widthProperty().subtract(labelProfile.prefWidth(-1)).divide(2));
        labelProfile.setLayoutY(25);
        rootProfile.getChildren().add(labelProfile);

        Label labelMoney = new Label("your Money : " + account.getMoney());
        labelMoney.setFont(Font.font(20));
        labelMoney.setTextFill(Color.GREEN);
        labelMoney.relocate(75, 120);
        rootProfile.getChildren().add(labelMoney);

        backButton(primaryStage, rootProfile, 110, 200);

        primaryStage.setScene(sceneProfile);
        primaryStage.centerOnScreen();
    }

    private void leaderBoard(Stage primaryStage)
    {
        ClientCommand LeaderBoardClientCommand = new ClientCommand(ClientCommandEnum.GET_ALL_ACCOUNTS, client.getAuthToken());
        String leaderBoardJson = new GsonBuilder().setPrettyPrinting().create().toJson(LeaderBoardClientCommand);
        System.out.println(leaderBoardJson);
        try
        {
            Client.getSendMessage().addMessage(leaderBoardJson);
            synchronized (validMessageFromServer)
            {
                validMessageFromServer.wait();
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Label labelTop10 = new Label("Top 10");
        labelTop10.setTextFill(YELLOW);
        labelTop10.setFont(Font.font(30));
        labelTop10.relocate(100, 0);
        rootLeaderBoard.getChildren().clear();
        rootLeaderBoard.getChildren().add(labelTop10);
        showRankingPlayers(client.getMessageFromServer().getAccounts());
        backButton(primaryStage, rootLeaderBoard, 100, 600);
        primaryStage.setScene(sceneLeaderBoard);
        primaryStage.centerOnScreen();
    }

    private void showRankingPlayers(ArrayList<Account> accounts)
    {
        Group rootLeaderBoard = Client.getRootLeaderBoard();
        int counter = 1;
        try
        {
            for (Account account : accounts)
            {
                if (counter > 10)
                {
                    break;
                }
                Text textPlayerName = new Text(counter + "- " + account.getAccountName());
                textPlayerName.setFont(Font.font(15));
                textPlayerName.relocate(25, counter * 50);
                rootLeaderBoard.getChildren().add(textPlayerName);

                Text textPlayerHighScore = new Text(Integer.toString(account.getNumOfWins()));
                textPlayerHighScore.setFont(Font.font(15));
                textPlayerHighScore.relocate(250, counter * 50);
                rootLeaderBoard.getChildren().add(textPlayerHighScore);

                if (account.getAuthToken() != null)
                {
                    Circle circle = new Circle();
                    circle.setFill(Color.GREEN);
                    circle.setCenterY(counter * 50 + 10);
                    circle.setCenterX(15);
                    circle.setRadius(5);
                    rootLeaderBoard.getChildren().add(circle);
                }

                if (account.getAuthToken() != null && account.getAuthToken().equals(client.getAuthToken()))
                {
                    textPlayerName.setFill(Color.RED);
                    textPlayerHighScore.setFill(Color.RED);
                }
                counter++;
            }
        } catch (Exception e)
        {
            showRankingPlayers(accounts);
        }
    }

    private Button backButton(Stage primaryStage, Group root, int x, int y)
    {
        Button backButton = new Button("Back");
        backButton.setFont(Font.font(25));
        backButton.relocate(x, y);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.EXIT);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                try
                {
                    primaryStage.setScene(sceneMainMenu);
                    primaryStage.centerOnScreen();
                    mainMenu(primaryStage);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        root.getChildren().add(backButton);
        return backButton;
    }

    private void searchField(Stage primaryStage, Scene scene, Group root, String menuName)
    {
        TextField searchField = new TextField();
        searchField.setFont(Font.font("SanSerif", 15));
        searchField.setPromptText("Search");
        searchField.setMaxWidth(150);
        searchField.relocate(150, 20);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode().equals(KeyCode.ENTER))
                {
                    if (!searchField.getText().isEmpty())
                    {
                        root.getChildren().clear();
                        if (menuName.equals("CollectionMenu"))
                        {
                            collectionMenu(primaryStage, true, searchField.getText());
                        }
                        else if (menuName.equals("ShopMenu"))
                        {
                            shopMenu(primaryStage, true, searchField.getText());
                        }
                    }
                }
            }
        });
        root.getChildren().addAll(searchField);
    }

    public void shopMenu(Stage primaryStage, boolean isSearchedElement, String searchedElement)
    {
        ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.GET_SHOP_CARDS_AND_ITEMS, client.getAuthToken());
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
        System.out.println(json);
        try
        {
            Client.getSendMessage().addMessage(json);
            synchronized (validMessageFromServer)
            {
                validMessageFromServer.wait();
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        rootShop.getChildren().clear();

        setBackGroundImage(rootShop, "file:BackGround Images/Duelyst Menu Blurred.jpg");

        scrollPaneShop.setContent(rootShop);
        scrollPaneShop.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneShop.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int xPosition = 0, yPosition = 0, x = 0, y = 0;
        setShopAndDeckAndGraveYardMenuText(rootShop, sceneShop, "Heroes", 50);
        for (Hero hero : client.getMessageFromServer().getHeroes())
        {
            if (isSearchedElement)
            {
                if (!hero.getCardName().contains(searchedElement))
                {
                    continue;
                }
            }
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = COLUMN_BLANK + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            xPosition++;
            yPosition++;
            StackPane stackPane = showNonSpellCards(rootShop, x, y, hero, hero.getCardName(), hero.getRequiredMP());
            setShopCardsStackPanesOnMouseClicked(stackPane, hero);
        }

        if (xPosition == 0)
        {
            y += BLANK_BETWEEN_CARDS;
            yPosition += 4;
        }
        xPosition = 0;
        if (yPosition % 4 != 0)
        {
            yPosition = yPosition + 4 - yPosition % 4;
        }
        setShopAndDeckAndGraveYardMenuText(rootShop, sceneShop, "Minions", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Minion minion : client.getMessageFromServer().getMinions())
        {
            if (isSearchedElement)
            {
                if (!minion.getCardName().contains(searchedElement))
                {
                    continue;
                }
            }
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 2 * COLUMN_BLANK - BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showNonSpellCards(rootShop, x, y, minion, minion.getCardName(), minion.getRequiredMP());
            setShopCardsStackPanesOnMouseClicked(stackPane, minion);
            xPosition++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 4;
        }
        xPosition = 0;
        if (yPosition % 4 != 0)
        {
            yPosition = yPosition + 4 - yPosition % 4;
        }
        setShopAndDeckAndGraveYardMenuText(rootShop, sceneShop, "Spells", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Spell spell : client.getMessageFromServer().getSpells())
        {
            if (isSearchedElement)
            {
                if (!spell.getCardName().contains(searchedElement))
                {
                    continue;
                }
            }
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 3 * COLUMN_BLANK - 2 * BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showCardAndItemImageAndFeatures(rootShop, x, y, spell.getCardName(), spell.getPrice(), spell.getRequiredMP());
            setShopCardsStackPanesOnMouseClicked(stackPane, spell);
            xPosition++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 4;
        }
        else
        {
            xPosition = 0;
            if (yPosition % 4 != 0)
            {
                yPosition = yPosition + 4 - yPosition % 4;
            }
        }
        setShopAndDeckAndGraveYardMenuText(rootShop, sceneShop, "Items", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Item item : client.getMessageFromServer().getItems())
        {
            if (item.getItemType() == ItemType.collectible)
            {
                continue;
            }
            if (isSearchedElement)
            {
                if (!item.getItemName().contains(searchedElement))
                {
                    continue;
                }
            }
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 4 * COLUMN_BLANK - 3 * BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showCardAndItemImageAndFeatures(rootShop, x, y, item.getItemName(), item.getPrice(), 0);
            setShopItemsStackPanesOnMouseClicked(stackPane, item);
            xPosition++;
            yPosition++;
        }

        backButton(primaryStage, rootShop, 20, 15);
        searchField(primaryStage, sceneShop, rootShop, "ShopMenu");
        showCollectionText(primaryStage, rootShop);

        primaryStage.setScene(sceneShop);
    }

    private void showCollectionText(Stage primaryStage, Group root)
    {
        Text text = new Text("Show Collection");
        text.setFont(Font.font(25));
        text.relocate(700, 20);
        text.setOnMouseEntered(event -> text.setFill(RED));
        text.setOnMouseExited(event -> text.setFill(BLACK));
        text.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.SHOW_COLLECTION);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                rootCollection.getChildren().clear();
                collectionMenu(primaryStage, false, null);
            }
        });
        root.getChildren().add(text);
    }

    private void setShopAndDeckAndGraveYardMenuText(Group root, Scene scene, String str, int y)
    {
        Text text = new Text(str);
        text.setLayoutX((scene.getWidth() - text.getLayoutBounds().getWidth()) / 2 - 40);
        text.setLayoutY(y);
        text.setFont(Font.font(null, FontWeight.SEMI_BOLD, 40));
        root.getChildren().addAll(text);
    }

    private StackPane showNonSpellCards(Group root, int x, int y, NonSpellCard nonSpellCard, String cardNameOrID, int MP)
    {
        int AP = nonSpellCard.getDefaultAP();
        int HP = nonSpellCard.getDefaultHP();
        int price = nonSpellCard.getPrice();

        StackPane stackPane = showCardAndItemImageAndFeatures(root, x, y, cardNameOrID, price, MP);

        Text textAP = new Text(Integer.toString(AP));
        textAP.setFont(Font.font(15));
        textAP.setFill(RED);
        textAP.setLayoutX(x + (CARDS_RECTANGLE_WIDTH - textAP.getLayoutBounds().getWidth()) / 2 - 40);
        textAP.setLayoutY(y + 200);

        Text textHP = new Text(Integer.toString(HP));
        textHP.setFill(YELLOW);
        textHP.setFont(Font.font(15));
        textHP.setLayoutX(x + (CARDS_RECTANGLE_WIDTH - textHP.getLayoutBounds().getWidth()) / 2 + 40);
        textHP.setLayoutY(y + 200);

        root.getChildren().addAll(textAP, textHP);

        return stackPane;
    }

    private StackPane showCardAndItemImageAndFeatures(Group root, int x, int y, String nameOrID, int price, int MP)
    {
        Image image = new Image("file:Cards.jpg");
        ImageView imageView = new ImageView(image);

        Rectangle rectangle = new Rectangle(CARDS_RECTANGLE_WIDTH, CARDS_RECTANGLE_HEIGHT);
        rectangle.setFill(GRAY);

        StackPane stackPane = new StackPane(rectangle, imageView);
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.relocate(x, y);

        Text textCardName = new Text(nameOrID);
        textCardName.setFont(Font.font(15));
        textCardName.setLayoutX(x + (rectangle.getWidth() - textCardName.getLayoutBounds().getWidth()) / 2);
        textCardName.setLayoutY(y + 160);

        Text textCardMP = new Text(Integer.toString(MP));
        textCardMP.setFont(Font.font(15));
        textCardMP.setFill(BLUE);
        textCardMP.setLayoutX(x + (rectangle.getWidth() - textCardMP.getLayoutBounds().getWidth()) / 2);
        textCardMP.setLayoutY(y + 200);

        Text textPrice = new Text(Integer.toString(price));
        textPrice.setFont(Font.font(15));
        textPrice.setFill(GREEN);
        textPrice.setLayoutX(x + (rectangle.getWidth() - textPrice.getLayoutBounds().getWidth()) / 2);
        textPrice.setLayoutY(y + 240);

        root.getChildren().addAll(stackPane, textCardName, textCardMP, textPrice);

        return stackPane;
    }

    private void setShopCardsStackPanesOnMouseClicked(StackPane stackPane, Card card)
    {
        String name = card.getCardName();
        int price = card.getPrice();
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Buy");
                alert.setHeaderText(null);
                alert.setContentText("Want to buy " + name + " for " + price + "?");
                alert.getButtonTypes().clear();
                ButtonType buttonTypeBuy = new ButtonType("Buy");
                ButtonType buttonTypeCancel = new ButtonType("Cancel");
                alert.getButtonTypes().addAll(buttonTypeBuy, buttonTypeCancel);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == buttonTypeBuy)
                {
                    ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.BUY, card, client.getAuthToken());
                    String buyJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(buyJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(client.getMessageFromServer().getMessage());
                    Media sound = new Media(new File("Sounds and Music/Buy card.mp3").toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                }
            }
        });
    }

    private void setShopItemsStackPanesOnMouseClicked(StackPane stackPane, Item item)
    {
        String name = item.getItemName();
        int price = item.getPrice();
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Buy");
                alert.setHeaderText(null);
                alert.setContentText("Want to buy " + name + " for " + price + "?");
                alert.getButtonTypes().clear();
                ButtonType buttonTypeBuy = new ButtonType("Buy");
                ButtonType buttonTypeCancel = new ButtonType("Cancel");
                alert.getButtonTypes().addAll(buttonTypeBuy, buttonTypeCancel);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == buttonTypeBuy)
                {
                    ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.BUY, item, client.getAuthToken());
                    String buyJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(buyJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(client.getMessageFromServer().getMessage());
                    Media sound = new Media(new File("Sounds and Music/Buy card.mp3").toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                }
            }
        });
    }

    public void collectionMenu(Stage primaryStage, boolean isSearchedElement, String searchedElement)
    {
        ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.GET_COLLECTION_CARDS_AND_ITEMS_AND_DECKS, client.getAuthToken());
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
        try
        {
            Client.getSendMessage().addMessage(json);
            synchronized (validMessageFromServer)
            {
                validMessageFromServer.wait();
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        rootCollection.getChildren().clear();

        setBackGroundImage(rootCollection, "file:BackGround Images/Duelyst Menu Blurred.jpg");

        scrollPaneCollection.setContent(rootCollection);
        scrollPaneCollection.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneCollection.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        setCollectionMenuText("Heroes", 50, false);
        int xPosition = 0, yPosition = 0, x = 0, y = 0;
        for (Hero hero : client.getMessageFromServer().getHeroes())
        {
            if (isSearchedElement)
            {
                if (!hero.getCardName().contains(searchedElement))
                {
                    continue;
                }
            }
            x = ROW_BLANK + (xPosition % 3) * (200 + BLANK_BETWEEN_CARDS);
            y = COLUMN_BLANK + yPosition / 3 * (250 + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showNonSpellCards(rootCollection, x, y, hero, hero.getCardID(), hero.getRequiredMP());
            setCollectionCardsStackPanesOnMouseClicked(primaryStage, stackPane, hero);
            xPosition++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += BLANK_BETWEEN_CARDS;
            yPosition += 3;
        }
        if (yPosition % 3 != 0)
        {
            yPosition = yPosition + 3 - yPosition % 3;
        }
        xPosition = 0;
        setCollectionMenuText("Minions", y + 250 + 50, false);
        if (yPosition % 3 != 0)
        {
            yPosition = yPosition + 3 - yPosition % 3;
        }
        for (Minion minion : client.getMessageFromServer().getMinions())
        {
            if (isSearchedElement)
            {
                if (!minion.getCardName().contains(searchedElement))
                {
                    continue;
                }
            }
            y = 2 * COLUMN_BLANK - BLANK_BETWEEN_CARDS + yPosition / 3 * (250 + BLANK_BETWEEN_CARDS);
            x = ROW_BLANK + (xPosition % 3) * (200 + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showNonSpellCards(rootCollection, x, y, minion, minion.getCardID(), minion.getRequiredMP());
            setCollectionCardsStackPanesOnMouseClicked(primaryStage, stackPane, minion);
            xPosition++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 3;
        }
        xPosition = 0;
        if (yPosition % 3 != 0)
        {
            yPosition = yPosition + 3 - yPosition % 3;
        }
        setCollectionMenuText("Spells", y + 250 + 50, false);
        if (yPosition % 3 != 0)
        {
            yPosition = yPosition + 3 - yPosition % 3;
        }
        for (Spell spell : client.getMessageFromServer().getSpells())
        {
            if (isSearchedElement)
            {
                if (!spell.getCardName().contains(searchedElement))
                {
                    continue;
                }
            }
            x = ROW_BLANK + (xPosition % 3) * (200 + BLANK_BETWEEN_CARDS);
            y = 3 * COLUMN_BLANK - 2 * BLANK_BETWEEN_CARDS + yPosition / 3 * (250 + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showCardAndItemImageAndFeatures(rootCollection, x, y, spell.getCardID(), spell.getPrice(), spell.getRequiredMP());
            setCollectionCardsStackPanesOnMouseClicked(primaryStage, stackPane, spell);
            xPosition++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 3;
        }
        else
        {
            xPosition = 0;
            if (yPosition % 3 != 0)
            {
                yPosition = yPosition + 3 - yPosition % 3;
            }
        }
        setCollectionMenuText("Items", y + 250 + 50, false);
        for (Item item : client.getMessageFromServer().getItems())
        {
            if (isSearchedElement)
            {
                if (!item.getItemName().contains(searchedElement))
                {
                    continue;
                }
            }
            if (item.getItemType() == ItemType.collectible)
            {
                continue;
            }
            x = ROW_BLANK + (xPosition % 3) * (200 + BLANK_BETWEEN_CARDS);
            y = 4 * COLUMN_BLANK - 3 * BLANK_BETWEEN_CARDS + yPosition / 3 * (250 + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showCardAndItemImageAndFeatures(rootCollection, x, y, item.getItemID(), item.getPrice(), 0);
            setCollectionItemsStackPanesOnMouseClicked(primaryStage, stackPane, item);
            xPosition++;
            yPosition++;
        }

        setCollectionMenuText("Decks", 50, true);
        yPosition = 0;
        x = ROW_BLANK + 3 * (200 + BLANK_BETWEEN_CARDS);
        for (Deck deck : client.getMessageFromServer().getDecks())
        {
            if (isSearchedElement)
            {
                if (!deck.getDeckName().contains(searchedElement))
                {
                    continue;
                }
            }
            y = COLUMN_BLANK + yPosition * (250 + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showDecksImageAndFeatures(rootCollection, x, y, deck);
            setCollectionDeckStackPanesOnMouseClicked(primaryStage, stackPane, deck);
            yPosition++;
        }

        backButton(primaryStage, rootCollection, 20, 15);
        searchField(primaryStage, sceneCollection, rootCollection, "CollectionMenu");
        createDeck(primaryStage, sceneCollection, rootCollection);

        Button importButton = new Button();
        importButton.relocate(480, 20);
        importButton.setFont(Font.font(15));
        importButton.setText("import Deck");
        importButton.setOnMouseClicked(event -> {
            try
            {
                importingDeck(primaryStage);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });

        rootCollection.getChildren().add(importButton);

        primaryStage.setScene(sceneCollection);
        primaryStage.centerOnScreen();
    }

    private StackPane showDecksImageAndFeatures(Group root, int x, int y, Deck deck)
    {
        Image image = new Image("file:Deck.jpg");
        ImageView imageView = new ImageView(image);

        Rectangle rectangle = new Rectangle(CARDS_RECTANGLE_WIDTH, CARDS_RECTANGLE_HEIGHT, LIGHTBLUE);
        if (accountConnectedToThisClient.getMainDeck() != null && deck.equals(accountConnectedToThisClient.getMainDeck()))
        {
            rectangle.setFill(LIGHTGREEN);
        }

        StackPane stackPane = new StackPane(rectangle, imageView);
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.relocate(x, y);

        Text textCardName = new Text(deck.getDeckName());
        textCardName.setFont(Font.font(15));
        textCardName.setLayoutX(x + (rectangle.getWidth() - textCardName.getLayoutBounds().getWidth()) / 2);
        textCardName.setLayoutY(y + 160);

        root.getChildren().addAll(stackPane, textCardName);

        return stackPane;
    }

    private void setCollectionMenuText(String str, int y, boolean isDeckText)
    {
        Text text = new Text(str);
        double x;
        if (isDeckText)
        {
            x = (sceneCollection.getWidth() * 1 / 4 - text.getLayoutBounds().getWidth()) / 2 - 40 + sceneCollection.getWidth() * 3 / 4;
        }
        else
        {
            x = (sceneCollection.getWidth() * 3 / 4 - text.getLayoutBounds().getWidth()) / 2 - 40;
        }
        text.setLayoutX(x);
        text.setLayoutY(y);
        text.setFont(Font.font(null, FontWeight.SEMI_BOLD, 40));
        rootCollection.getChildren().addAll(text);
    }

    private void setCollectionCardsStackPanesOnMouseClicked(Stage primaryStage, StackPane stackPane, Card card)
    {
        String ID = card.getCardID();
        int price = card.getPrice();
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sell");
                alert.setHeaderText(null);
                alert.setContentText("Want to sell " + ID + " for " + price + "or adding it to a deck ?");
                alert.getButtonTypes().clear();
                ButtonType buttonTypeAddToDeck = new ButtonType("Add to deck");
                ButtonType buttonTypeSell = new ButtonType("Sell");
                ButtonType buttonTypeCancel = new ButtonType("Cancel");
                alert.getButtonTypes().addAll(buttonTypeAddToDeck, buttonTypeSell, buttonTypeCancel);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == buttonTypeSell)
                {
                    ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.SELL, card, client.getAuthToken());
                    String sellJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(sellJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(client.getMessageFromServer().getMessage());
                    collectionMenu(primaryStage, false, null);
                }
                else if (option.get() == buttonTypeAddToDeck)
                {
                    addToDeck(primaryStage, ID);
                }
            }
        });
    }

    private void setCollectionItemsStackPanesOnMouseClicked(Stage primaryStage, StackPane stackPane, Item item)
    {
        String ID = item.getItemID();
        int price = item.getPrice();
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sell");
                alert.setHeaderText(null);
                alert.setContentText("Want to sell " + ID + " for " + price + "or adding it to a deck ?");
                alert.getButtonTypes().clear();
                ButtonType buttonTypeAddToDeck = new ButtonType("Add to deck");
                ButtonType buttonTypeSell = new ButtonType("Sell");
                ButtonType buttonTypeCancel = new ButtonType("Cancel");
                alert.getButtonTypes().addAll(buttonTypeAddToDeck, buttonTypeSell, buttonTypeCancel);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == buttonTypeSell)
                {
                    ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.SELL, item, client.getAuthToken());
                    String sellJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(sellJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(client.getMessageFromServer().getMessage());
                    collectionMenu(primaryStage, false, null);
                }
                else if (option.get() == buttonTypeAddToDeck)
                {
                    addToDeck(primaryStage, ID);
                }
            }
        });
    }

    private void setCollectionDeckStackPanesOnMouseClicked(Stage primaryStage, StackPane stackPane, Deck deck)
    {
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deck operations");
                alert.setHeaderText(null);
                alert.setContentText("Select the option to apply to " + deck.getDeckName());
                alert.getButtonTypes().clear();
                ButtonType buttonTypeShowDeck = new ButtonType("Show Deck");
                ButtonType buttonTypeValidateDeck = new ButtonType("Validate Deck");
                ButtonType buttonTypeSetMainDeck = new ButtonType("Set as Client deck");
                ButtonType buttonTypeExportDeck = new ButtonType("Export Deck");
                ButtonType buttonTypeRemoveDeck = new ButtonType("Remove deck");
                ButtonType buttonTypeCancel = new ButtonType("Cancel");
                alert.getButtonTypes().addAll(buttonTypeShowDeck, buttonTypeValidateDeck, buttonTypeSetMainDeck, buttonTypeExportDeck, buttonTypeRemoveDeck, buttonTypeCancel);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == buttonTypeShowDeck)
                {
                    deckMenu(primaryStage, deck);
                }
                else if (option.get() == buttonTypeRemoveDeck)
                {
                    ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.DELETE_DECK, deck, client.getAuthToken());
                    String removeJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                    System.out.println(removeJson);
                    try
                    {
                        Client.getSendMessage().addMessage(removeJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    collectionMenu(primaryStage, false, null);
                }
                else if (option.get() == buttonTypeSetMainDeck)
                {
                    ClientCommand LogInClientCommand = new ClientCommand(ClientCommandEnum.SET_MAIN_DECK, deck, client.getAuthToken());
                    String loginJson = new GsonBuilder().setPrettyPrinting().create().toJson(LogInClientCommand);
                    System.out.println(loginJson);
                    try
                    {
                        Client.getSendMessage().addMessage(loginJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(client.getMessageFromServer().getMessage());
                    collectionMenu(primaryStage, false, null);
                }
                else if (option.get() == buttonTypeValidateDeck)
                {
                    ClientCommand LogInClientCommand = new ClientCommand(ClientCommandEnum.VALIDATE_DECK, deck, client.getAuthToken());
                    String loginJson = new GsonBuilder().setPrettyPrinting().create().toJson(LogInClientCommand);
                    System.out.println(loginJson);
                    try
                    {
                        Client.getSendMessage().addMessage(loginJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(client.getMessageFromServer().getMessage());
                    collectionMenu(primaryStage, false, null);
                }
                else if (option.get() == buttonTypeExportDeck)
                {
                    ClientCommand clientCommand = new ClientCommand(client.getAuthToken(), ClientCommandEnum.EXPORT_DECK, deck.getDeckName());
                    String exportJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                    System.out.println(exportJson);
                    try
                    {
                        Client.getSendMessage().addMessage(exportJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void addToDeck(Stage primaryStage, String ID)
    {
        rootCollection.getChildren().clear();

        setBackGroundImage(rootCollection, "file:BackGround Images/Duelyst Menu Blurred.jpg");

        int xPosition = 0, yPosition = 0, x, y;
        setShopAndDeckAndGraveYardMenuText(rootCollection, sceneCollection, "Decks", 50);
        for (Deck deck : accountConnectedToThisClient.getPlayerDecks())
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = COLUMN_BLANK + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            xPosition++;
            yPosition++;
            StackPane stackPane = showDecksImageAndFeatures(rootCollection, x, y, deck);
            stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Add");
                    alert.setHeaderText(null);
                    alert.setContentText("Want to add " + ID + " to " + deck.getDeckName() + "?");
                    alert.getButtonTypes().clear();
                    ButtonType buttonTypeAdd = new ButtonType("Add");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel");
                    alert.getButtonTypes().addAll(buttonTypeAdd, buttonTypeCancel);
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get() == buttonTypeAdd)
                    {
                        Media sound = new Media(new File("Sounds and Music/Add card to deck.wav").toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.play();

                        ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.ADD_TO_DECK, deck, ID, client.getAuthToken());
                        String addJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                        try
                        {
                            Client.getSendMessage().addMessage(addJson);
                            synchronized (validMessageFromServer)
                            {
                                validMessageFromServer.wait();
                            }
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println(client.getMessageFromServer().getMessage());
                        collectionMenu(primaryStage, false, null);
                    }
                }
            });
        }

        Button backButton = backButton(primaryStage, rootCollection, 20, 15);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                rootCollection.getChildren().clear();
                primaryStage.setScene(sceneCollection);
                primaryStage.centerOnScreen();
                collectionMenu(primaryStage, false, null);
            }
        });
    }

    private void createDeck(Stage primaryStage, Scene scene, Group root)
    {
        TextField createDeckTextField = new TextField();
        createDeckTextField.setFont(Font.font("SanSerif", 15));
        createDeckTextField.setPromptText("Enter deckName to create");
        createDeckTextField.setMaxWidth(200);
        createDeckTextField.relocate(600, 20);
        createDeckTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode().equals(KeyCode.ENTER))
                {
                    ClientCommand clientCommand = new ClientCommand(client.getAuthToken(), ClientCommandEnum.CREATE_DECK, createDeckTextField.getText());
                    String createDeckJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                    System.out.println(createDeckJson);
                    try
                    {
                        Client.getSendMessage().addMessage(createDeckJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if (!createDeckTextField.getText().isEmpty())
                    {
                        setCommand(CommandType.CREATE_DECK);
                        getCommand().deckName = createDeckTextField.getText();
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        collectionMenu(primaryStage, false, null);
                    }
                }
            }
        });
        root.getChildren().add(createDeckTextField);
    }

    private void importingDeck(Stage primaryStage) throws IOException
    {
        rootImportingDeck.getChildren().clear();

        setBackGroundImage(rootImportingDeck, "file:BackGround Images/ImportingDeck.jpg");

        InputStream inputStream = new FileInputStream("SavedDecks/savedDecksPath.txt");
        ArrayList<String> deckNames = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext())
        {
            deckNames.add(scanner.nextLine());
            makingText(primaryStage, deckNames);
        }

        Button backButton = backButton(primaryStage, rootImportingDeck, 50, 450);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                primaryStage.setScene(sceneCollection);
                primaryStage.centerOnScreen();
                collectionMenu(primaryStage, false, null);
            }
        });

        primaryStage.setScene(sceneImportingDeck);
        primaryStage.centerOnScreen();
    }

    private void makingText(Stage primaryStage, ArrayList<String> deckNames)
    {
        for (int i = 0; i < deckNames.size(); i++)
        {
            Text deckName = new Text();
            deckName.setText(deckNames.get(i));
            deckName.setFont(Font.font(null, FontWeight.SEMI_BOLD, 20));
            deckName.setFill(YELLOW);
            deckName.layoutXProperty().bind(sceneImportingDeck.widthProperty().subtract(deckName.prefWidth(-1)).divide(2));
            deckName.setY(i * 50 + 100);
            int finalI = i;
            deckName.setOnMouseEntered(event -> deckName.setFill(GREEN));
            deckName.setOnMouseExited(event -> deckName.setFill(YELLOW));
            deckName.setOnMouseClicked(event -> {
                try
                {
                    ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.IMPORT_DECK, returningDeck(deckName.getText()), client.getAuthToken());
                    String importJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                    System.out.println(importJson);
                    try
                    {
                        Client.getSendMessage().addMessage(importJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    primaryStage.setScene(sceneCollection);
                    primaryStage.centerOnScreen();
                    collectionMenu(primaryStage, false, null);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            });
            System.out.println(deckName.getText());
            rootImportingDeck.getChildren().add(deckName);
        }
    }

    public Deck returningDeck(String deckName)
    {
        for (Deck deck : accountConnectedToThisClient.getPlayerDecks())
        {
            if (deck.getDeckName().equals(deckName))
            {
                return deck;
            }
        }
        return null;
    }

    private void importingToCollection(String deckName, Account account) throws IOException, ParseException
    {
        JsonParser jsonParser = new JsonParser();
        FileReader reader = new FileReader("SavedDecks/" + deckName + ".json");
        Object obj = jsonParser.parse(reader);
        System.out.println(obj);
        Deck deck = new Gson().fromJson(obj.toString(), Deck.class);
        deck.setDeckName("Imported " + deck.getDeckName());
        account.getPlayerDecks().add(deck);
        addImportedDeckCardsAndItemsToCollection(deck);
    }

    private void addImportedDeckCardsAndItemsToCollection(Deck deck)
    {
        //send deck to server    //
    }

    private void deckMenu(Stage primaryStage, Deck deck)
    {
        rootDeck.getChildren().clear();

        setBackGroundImage(rootDeck, "file:BackGround Images/Duelyst Menu Blurred.jpg");

        scrollPaneDeck.setContent(rootDeck);
        scrollPaneDeck.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneDeck.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int xPosition = 0, yPosition = 0, x = 0, y = 0;
        setShopAndDeckAndGraveYardMenuText(rootDeck, sceneDeck, "Heroes", 50);
        for (Hero hero : deck.getHero())   //3
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = COLUMN_BLANK + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            xPosition++;
            yPosition++;
            StackPane stackPane = showNonSpellCards(rootDeck, x, y, hero, hero.getCardID(), hero.getRequiredMP());
            setDeckCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, deck, hero.getCardID(), hero.getCardName());
        }

        if (xPosition == 0)
        {
            y += BLANK_BETWEEN_CARDS;
            yPosition += 4;
        }
        xPosition = 0;
        if (yPosition % 4 != 0)
        {
            yPosition = yPosition + 4 - yPosition % 4;
        }
        setShopAndDeckAndGraveYardMenuText(rootDeck, sceneDeck, "Minions", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Minion minion : deck.getMinions())    //3
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 2 * COLUMN_BLANK - BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showNonSpellCards(rootDeck, x, y, minion, minion.getCardID(), minion.getRequiredMP());
            setDeckCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, deck, minion.getCardID(), minion.getCardName());
            xPosition++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 4;
        }
        xPosition = 0;
        if (yPosition % 4 != 0)
        {
            yPosition = yPosition + 4 - yPosition % 4;
        }
        setShopAndDeckAndGraveYardMenuText(rootDeck, sceneDeck, "Spells", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Spell spell : deck.getSpells())    //3
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 3 * COLUMN_BLANK - 2 * BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showCardAndItemImageAndFeatures(rootDeck, x, y, spell.getCardID(), spell.getPrice(), spell.getRequiredMP());
            setDeckCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, deck, spell.getCardID(), spell.getCardName());
            xPosition++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 4;
        }
        else
        {
            xPosition = 0;
            if (yPosition % 4 != 0)
            {
                yPosition = yPosition + 4 - yPosition % 4;
            }
        }
        setShopAndDeckAndGraveYardMenuText(rootDeck, sceneDeck, "Items", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Item item : deck.getItem()) //3
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 4 * COLUMN_BLANK - 3 * BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showCardAndItemImageAndFeatures(rootDeck, x, y, item.getItemID(), item.getPrice(), 0);
            setDeckCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, deck, item.getItemID(), item.getItemName());
            xPosition++;
            yPosition++;
        }

        Button backButton = backButton(primaryStage, rootDeck, 20, 15);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                primaryStage.setScene(sceneCollection);
                primaryStage.centerOnScreen();
                collectionMenu(primaryStage, false, null);
            }
        });

        primaryStage.setScene(sceneDeck);
    }

    private void setDeckCardAndItemStackPanesOnMouseClicked(Stage primaryStage, StackPane stackPane, Deck deck, String ID, String cardName)
    {
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Remove");
                alert.setHeaderText(null);
                alert.setContentText("Want to remove " + ID + " from " + deck.getDeckName() + "?");
                alert.getButtonTypes().clear();
                ButtonType buttonTypeSell = new ButtonType("Remove");
                ButtonType buttonTypeCancel = new ButtonType("Cancel");
                alert.getButtonTypes().addAll(buttonTypeSell, buttonTypeCancel);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == buttonTypeSell)
                {
                    ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.REMOVE_CARD_FROM_DECK, deck, ID, client.getAuthToken());
                    String removeJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                    try
                    {
                        Client.getSendMessage().addMessage(removeJson);
                        synchronized (validMessageFromServer)
                        {
                            validMessageFromServer.wait();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    setCommand(CommandType.REMOVE_FROM_DECK);
                    getCommand().cardOrItemID = ID;
                    getCommand().deckName = deck.getDeckName();
                    synchronized (requestLock)
                    {
                        requestLock.notify();
                    }
                    rootDeck.getChildren().clear();
                    deckMenu(primaryStage, deck);
                }
            }
        });
    }

    private void battleMenu(Stage primaryStage)
    {
        setBackGroundImage(rootBattleMenu, "file:BackGround Images/duelystBattle.jpg");

        setBattleMenu("Single Player", primaryStage, 170);
        setBattleMenu("Multi Player", primaryStage, 270);
        backButton(primaryStage, rootBattleMenu, 50, 450);

        primaryStage.setScene(sceneBattleMenu);
    }

    private void setBattleMenu(String titleOfBattleMenu, Stage primaryStage, int location)
    {
        Text title = new Text("Select Duel");
        title.setFill(RED);
        title.setTextOrigin(VPos.TOP);
        title.setFont(Font.font(null, FontPosture.ITALIC, 45));
        title.layoutXProperty().bind(sceneBattleMenu.widthProperty().subtract(title.prefWidth(-2)).divide(2));
        title.setY(50);

        Text text = new Text(titleOfBattleMenu);
        text.setTextOrigin(VPos.TOP);
        text.setFont(Font.font(null, FontWeight.BLACK, 45));
        text.layoutXProperty().bind(sceneBattleMenu.widthProperty().subtract(text.prefWidth(-2)).divide(2));
        text.setY(location);
        text.setFill(BLACK);
        text.setOnMouseEntered(event -> text.setFill(PURPLE));
        text.setOnMouseExited(event -> text.setFill(BLACK));
        text.setOnMouseClicked(event -> {
            switch (titleOfBattleMenu)
            {
                case "Single Player":
                    command = CommandType.SINGLE_PLAYER;
                    singlePlayerMenu(primaryStage);
                    break;
                case "Multi Player":
                    command = CommandType.MULTI_PLAYER;
                    MultiPlayerChooseModeMenu(rootMultiPlayer, primaryStage);
                    break;
            }
            synchronized (requestLock)
            {
                requestLock.notify();
            }
        });
        rootBattleMenu.getChildren().add(text);
        rootBattleMenu.getChildren().add(title);
    }

    private void singlePlayerMenu(Stage primaryStage)
    {
        setBackGroundImage(rootSinglePlayer, "file:BackGround Images/SinglePlayer.jpg");
        setSinglePlayerMenu("Story", primaryStage, 100);
        setSinglePlayerMenu("Custom Game", primaryStage, 250);
        Button backButton = backButton(primaryStage, rootSinglePlayer, 50, 450);
        backButton.setOnMouseClicked(event -> {
            primaryStage.setScene(sceneBattleField);
        });
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @SuppressWarnings("Duplicates")
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.EXIT);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                primaryStage.setScene(sceneBattleMenu);
                primaryStage.centerOnScreen();
                try
                {
                    battleMenu(primaryStage);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.setScene(sceneSinglePlayer);
    }

    @SuppressWarnings("Duplicates")
    private void setSinglePlayerMenu(String string, Stage primaryStage, int place)
    {
        Text title = new Text(string);
        title.setTextOrigin(VPos.TOP);
        title.setFont(Font.font(null, FontWeight.BLACK, 45));
        title.setFill(BLUE);
        title.layoutXProperty().bind(sceneSinglePlayer.widthProperty().subtract(title.prefWidth(-1)).divide(2));
        title.setY(place);
        title.setOnMouseEntered(event -> title.setFont(Font.font(null, FontWeight.SEMI_BOLD, 50)));
        title.setOnMouseEntered(event -> title.setFill(AQUA));
        title.setOnMouseExited(event -> title.setFont(Font.font(null, FontWeight.SEMI_BOLD, 45)));
        title.setOnMouseExited(event -> title.setFill(BLACK));
        title.setOnMouseClicked(event -> {
            switch (string)
            {
                case "Story":
                    setCommand(CommandType.STORY);
                    synchronized (requestLock)
                    {
                        requestLock.notify();
                    }
                    storyModeMenu(primaryStage);
                    break;
                case "Custom Game":
                    setCommand(CommandType.CUSTOM_GAME);
                    synchronized (requestLock)
                    {
                        requestLock.notify();
                    }
                    customGameMenuToChooseDeck(primaryStage);
                    break;
            }
        });
        rootSinglePlayer.getChildren().add(title);
    }

    @SuppressWarnings("Duplicates")
    private void customGameMenuToChooseDeck(Stage primaryStage)
    {
        setBackGroundImage(rootCustomGame, "file:BackGround Images/CustomGame1.png");
        showDecksLists(rootCustomGame);

        Button nextButton = new Button("Next");
        nextButton.relocate(500, 270);
        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                customGameMenuToChooseMode(primaryStage);
            }
        });
        rootCustomGame.getChildren().add(nextButton);

        Button backButton = backButton(primaryStage, rootCustomGame, 50, 450);
        backButton.setOnMouseClicked(event -> {
            primaryStage.setScene(sceneSinglePlayer);
        });
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.EXIT);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                primaryStage.setScene(sceneBattleMenu);
                primaryStage.centerOnScreen();
                try
                {
                    singlePlayerMenu(primaryStage);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        primaryStage.setScene(sceneCustomGame);
    }

    private void customGameMenuToChooseMode(Stage primaryStage)
    {
        rootCustomGame.getChildren().clear();
        setBackGroundImage(rootCustomGame, "file:BackGround Images/CustomGame2.jpg");
        setCustomGameMenuToChooseMode("Mode 1", primaryStage, 100);
        setCustomGameMenuToChooseMode("Mode 2", primaryStage, 200);
        setCustomGameMenuToChooseMode("Mode 3", primaryStage, 300);
        Button backButton = backButton(primaryStage, rootCustomGame, 50, 450);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.EXIT);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                primaryStage.setScene(sceneSinglePlayer);
                primaryStage.centerOnScreen();
                try
                {
                    singlePlayerMenu(primaryStage);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        primaryStage.setScene(sceneCustomGame);
    }

    @SuppressWarnings("Duplicates")
    private void setCustomGameMenuToChooseMode(String string, Stage primaryStage, int place)
    {
        Text title = new Text(string);
        title.setTextOrigin(VPos.TOP);
        title.setFont(Font.font(null, FontWeight.THIN, 45));
        title.setFill(BLUE);
        title.layoutXProperty().bind(sceneCustomGame.widthProperty().subtract(title.prefWidth(-1)).divide(2));
        title.setY(place);
        title.setOnMouseEntered(event -> title.setFont(Font.font(null, FontWeight.SEMI_BOLD, 50)));
        title.setOnMouseEntered(event -> title.setFill(AQUA));
        title.setOnMouseExited(event -> title.setFont(Font.font(null, FontWeight.SEMI_BOLD, 45)));
        title.setOnMouseExited(event -> title.setFill(BLACK));
        title.setOnMouseClicked(event -> {
            switch (string)
            {
                case "Mode 1":
                    if (!Client.getCallTheAppropriateFunction().customGameBattleMaker(accountConnectedToThisClient, selectedDeckForCustomGame, 1))
                    {
                        return;
                    }
                    try
                    {
                        setBattleField(primaryStage, "customGameBackGround", false, BattleMode.KILLING_ENEMY_HERO);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "Mode 2":
                    if (!Client.getCallTheAppropriateFunction().customGameBattleMaker(accountConnectedToThisClient, selectedDeckForCustomGame, 2))
                    {
                        return;
                    }
                    try
                    {
                        setBattleField(primaryStage, "customGameBackGround", false, BattleMode.GATHERING_FLAGS);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "Mode 3":
                    if (!Client.getCallTheAppropriateFunction().customGameBattleMaker(accountConnectedToThisClient, selectedDeckForCustomGame, 3))
                    {
                        return;
                    }
                    try
                    {
                        setBattleField(primaryStage, "customGameBackGround", false, BattleMode.KEEP_FLAG_FOR_6_TURNS);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
            }
            synchronized (requestLock)
            {
                requestLock.notify();
            }
        });
        rootCustomGame.getChildren().add(title);
    }

    private void showDecksLists(Group rootCustomGame)
    {
        Menu decksMenu = new Menu("Decks");

        for (Deck deck : accountConnectedToThisClient.getPlayerDecks())
        {
            MenuItem menuItem = new MenuItem(deck.getDeckName());
            decksMenu.getItems().add(menuItem);
            menuItem.setOnAction(e -> {
                selectedDeckForCustomGame = deck;
            });
        }

        MenuBar menuBar = new MenuBar(decksMenu);
        menuBar.relocate(500, 230);
        rootCustomGame.getChildren().add(menuBar);
    }

    @SuppressWarnings("Duplicates")
    private void storyModeMenu(Stage primaryStage)
    {
        setBackGroundImage(rootStoryMode, "file:BackGround Images/StoryModeBackground.jpg");
        setStoryModeMenu("Mission 1", primaryStage, 100);
        setStoryModeMenu("Mission 2", primaryStage, 200);
        setStoryModeMenu("Mission 3", primaryStage, 300);
        Button backButton = backButton(primaryStage, rootStoryMode, 50, 450);
        backButton.setOnMouseClicked(event -> {
            primaryStage.setScene(sceneSinglePlayer);
        });
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.EXIT);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                primaryStage.setScene(sceneSinglePlayer);
                primaryStage.centerOnScreen();
                try
                {
                    singlePlayerMenu(primaryStage);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.setScene(sceneStoryMode);
    }

    @SuppressWarnings("Duplicates")
    private void setStoryModeMenu(String string, Stage primaryStage, int place)
    {
        Text title = new Text(string);
        title.setTextOrigin(VPos.TOP);
        title.setFont(Font.font(null, FontWeight.THIN, 45));
        title.setFill(BLUE);
        title.layoutXProperty().bind(sceneSinglePlayer.widthProperty().subtract(title.prefWidth(-1)).divide(2));
        title.setY(place);
        title.setOnMouseEntered(event -> title.setFont(Font.font(null, FontWeight.SEMI_BOLD, 50)));
        title.setOnMouseEntered(event -> title.setFill(AQUA));
        title.setOnMouseExited(event -> title.setFont(Font.font(null, FontWeight.SEMI_BOLD, 45)));
        title.setOnMouseExited(event -> title.setFill(BLACK));
        title.setOnMouseClicked(event -> {
            switch (string)
            {
                case "Mission 1":

                    //ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.MAKE_STORY_BATTLE , 1 , accountConnectedToThisClient );

                    Client.getCallTheAppropriateFunction().storyModeBattleMaker(accountConnectedToThisClient, 1);                                 //9
                    try
                    {
                        setBattleField(primaryStage, "backgroundStory1", false, BattleMode.KILLING_ENEMY_HERO);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "Mission 2":

                    //ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.MAKE_STORY_BATTLE , 2 , accountConnectedToThisClient );

                    Client.getCallTheAppropriateFunction().storyModeBattleMaker(accountConnectedToThisClient, 2);                                     //9
                    try
                    {
                        setBattleField(primaryStage, "backgroundStory2", false, BattleMode.KEEP_FLAG_FOR_6_TURNS);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "Mission 3":

                    //ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.MAKE_STORY_BATTLE , 3 , accountConnectedToThisClient );
                    Client.getCallTheAppropriateFunction().storyModeBattleMaker(accountConnectedToThisClient, 3);                                      //9
                    try
                    {
                        setBattleField(primaryStage, "backgroundStory3", false, BattleMode.GATHERING_FLAGS);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
            }
            synchronized (requestLock)
            {
                requestLock.notify();
            }
        });
        rootStoryMode.getChildren().add(title);
    }


    private void multiPlayerMenu(Stage primaryStage, BattleMode battleMode)
    {
        setBackGroundImage(rootMultiPlayer, "file:BackGround Images/MultiPlayerrr.jpg");
        setMultiPlayerMenu("Choose  One Player", 75);
        showChoosePlayerMenu(rootMultiPlayer);

        Button backButton = new Button("Back");
        backButton.relocate(50, 490);
        backButton.setFont(Font.font(25));
        Button nextButton = new Button("Next");
        nextButton.relocate(500, 270);
        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                goWaitingPage(primaryStage, battleMode);
            }
        });

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.EXIT);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                try
                {
                    primaryStage.setScene(sceneMainMenu);
                    primaryStage.centerOnScreen();
                    battleMenu(primaryStage);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.setScene(sceneMultiPlayer);
        rootMultiPlayer.getChildren().addAll(backButton, nextButton);
    }

    private void goWaitingPage(Stage primaryStage, BattleMode battleMode)
    {
        rootMultiPlayer.getChildren().clear();
        setBackGroundImage(rootMultiPlayer, "file:BackGround Images/MultiPlayerrr.jpg");

        /*Client.getCallTheAppropriateFunction().multiPayerBattleMaker(accountConnectedToThisClient, battleMode, new Player(multiPlayerAccountToBattle, false));
        try {
            setBattleField(primaryStage, "customGameBackGround", false, battleMode);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        Text waitingText = new Text("Waiting for other player ...");
        waitingText.setFont(Font.font("Verdana", 30));
        waitingText.relocate(250, 250);
        Button backButton = new Button("Back");
        backButton.relocate(50, 490);
        backButton.setFont(Font.font(25));

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.EXIT);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                try
                {
                    primaryStage.setScene(sceneMainMenu);
                    primaryStage.centerOnScreen();
                    battleMenu(primaryStage);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.setScene(sceneMultiPlayer);
        rootMultiPlayer.getChildren().addAll(backButton);
    }

    public void afterWaitingMultiPlayer()
    {
    }

    private void MultiPlayerChooseModeMenu(Group rootBattleField, Stage primaryStage)
    {
        rootBattleField.getChildren().clear();
        setBackGroundImage(rootBattleField, "file:BackGround Images/CustomGame2.jpg");
        setMultiPayerMenuToChooseMode("Mode 1", primaryStage, 100);
        setMultiPayerMenuToChooseMode("Mode 2", primaryStage, 200);
        setMultiPayerMenuToChooseMode("Mode 3", primaryStage, 300);
        Button backButton = backButton(primaryStage, rootBattleField, 50, 450);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.EXIT);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                primaryStage.setScene(sceneBattleMenu);
                primaryStage.centerOnScreen();
                try
                {
                    battleMenu(primaryStage);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.setScene(sceneMultiPlayer);
    }

    private void setMultiPayerMenuToChooseMode(String s, Stage primaryStage, int place)
    {
        Text title = new Text(s);
        title.setTextOrigin(VPos.TOP);
        title.setFont(Font.font(null, FontWeight.THIN, 45));
        title.setFill(BLUE);
        title.layoutXProperty().bind(sceneMultiPlayer.widthProperty().subtract(title.prefWidth(-1)).divide(2));
        title.setY(place);
        title.setOnMouseEntered(event -> title.setFont(Font.font(null, FontWeight.SEMI_BOLD, 50)));
        title.setOnMouseEntered(event -> title.setFill(AQUA));
        title.setOnMouseExited(event -> title.setFont(Font.font(null, FontWeight.SEMI_BOLD, 45)));
        title.setOnMouseExited(event -> title.setFill(BLACK));
        title.setOnMouseClicked(event -> {
            switch (s)
            {
                case "Mode 1":
                    multiPlayerMenu(primaryStage, BattleMode.KILLING_ENEMY_HERO);
                    break;
                case "Mode 2":
                    multiPlayerMenu(primaryStage, BattleMode.GATHERING_FLAGS);
                    break;
                case "Mode 3":
                    multiPlayerMenu(primaryStage, BattleMode.KEEP_FLAG_FOR_6_TURNS);
            }
            synchronized (requestLock)
            {
                requestLock.notify();
            }
        });
        rootMultiPlayer.getChildren().add(title);
    }

    private void showChoosePlayerMenu(Group rootMultiPlayer)
    {
        Menu decksMenu = new Menu("Players");

        ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.GET_ALL_ACCOUNTS, client.getAuthToken());
        String getAllOfAccountsJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
        System.out.println(getAllOfAccountsJson);
        try
        {
            Client.getSendMessage().addMessage(getAllOfAccountsJson);
            synchronized (validMessageFromServer)
            {
                validMessageFromServer.wait();
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if (client.getMessageFromServer().getServerCommandEnum().equals(ServerCommandEnum.OK))
        {
            for (Account account : client.getMessageFromServer().getAccounts())
            {
                MenuItem menuItem = new MenuItem(account.getAccountName());
                decksMenu.getItems().add(menuItem);
                menuItem.setOnAction(e -> {
                    multiPlayerAccountToBattle = account;
                });
            }
        }

        MenuBar menuBar = new MenuBar(decksMenu);
        menuBar.relocate(500, 230);
        rootMultiPlayer.getChildren().add(menuBar);
    }

    private void setMultiPlayerMenu(String string, int location)
    {
        Text multiPlayerText = new Text(string);
        multiPlayerText.setFont(Font.font(null, FontPosture.ITALIC, 50));
        multiPlayerText.setTextOrigin(VPos.TOP);
        multiPlayerText.setFill(BLUE);
        multiPlayerText.layoutXProperty().bind(sceneMultiPlayer.widthProperty().subtract(multiPlayerText.prefWidth(-1)).divide(2));
        multiPlayerText.setY(location);
        rootMultiPlayer.getChildren().add(multiPlayerText);
    }

    private void setBattleField(Stage primaryStage, String map, boolean backFromGraveYard, BattleMode battleMode) throws IOException
    {
        if (!backFromGraveYard)
        {
            setBackGroundImage(rootBattleField, "battleField BackGround/" + map + ".jpg");
            setGridPane(rootBattleField);
            setHeroIcons(rootBattleField);
            setHandIcons(rootBattleField);
            setPlayersName(rootBattleField);
            setMPIcons(rootBattleField);
            setHeroFirstPlace();
            setGraveYardButton(primaryStage, rootBattleField, map, battleMode);
            setSurrenderButton(primaryStage, rootBattleField, map);
            setNextCard(rootBattleField);
            showGameInfo(rootBattleField);
            setEndTurnButton(primaryStage, rootBattleField, battleMode);
            setGlobalChatButton(primaryStage, rootBattleField);
        }
        battleFieldController = new BattleFieldController(this, rootBattleField, sceneBattleField, battleInfo, battleMode);
        battleFieldController.start();
        primaryStage.setScene(sceneBattleField);
        primaryStage.centerOnScreen();
        primaryStage.setFullScreen(true);
    }

    private void setNextCard(Group rootBattleField)
    {
        Battle.getCurrentBattle().setNextCardPane(rootBattleField);
    }

    private void showGameInfo(Group rootBattleField)
    {
        Text text = new Text();
        ShowOutput showOutput = ShowOutput.getInstance();
        String string = showOutput.getGameInfo();
        text.setText(string);
        text.relocate(1050, 200);
        text.setFill(CYAN);
        if (!rootBattleField.getChildren().contains(text))
        {
            rootBattleField.getChildren().add(text);
        }
        battleInfo = text;
    }


    private void setPlayersName(Group rootBattleField)
    {
        Battle.getCurrentBattle().setPlayersName(rootBattleField);
    }

    public void setMPIcons(Group rootBattleField)
    {
        Battle.getCurrentBattle().setMPIcons(rootBattleField);
    }

    private void setSurrenderButton(Stage primaryStage, Group rootBattleField, String url)
    {
        ImageView graveYardButton = new ImageView("battleField BackGround/button_Surrender.png");
        graveYardButton.relocate(1135, 530);
        graveYardButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Battle.getCurrentBattle().tasksWhenSurrender();
                setCommand(CommandType.END_GAME);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                primaryStage.setScene(sceneMainMenu);
                primaryStage.centerOnScreen();
                mainMenu(primaryStage);
            }
        });
        rootBattleField.getChildren().add(graveYardButton);
    }

    private void setGraveYardButton(Stage primaryStage, Group rootBattleField, String url, BattleMode battleMode)
    {
        ImageView graveYardButton = new ImageView("battleField BackGround/button_GraveYard.png");
        graveYardButton.relocate(50, 640);
        graveYardButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                primaryStage.setScene(sceneGraveYard);
                primaryStage.centerOnScreen();
                showGraveYard(primaryStage, url, battleMode);
            }
        });
        rootBattleField.getChildren().add(graveYardButton);
    }

    private void showGraveYard(Stage primaryStage, String map, BattleMode battleMode)
    {
        rootGraveYard.getChildren().clear();

        setBackGroundImage(rootGraveYard, "file:BackGround Images/GraveYard Image.jpg");

        scrollPaneGraveYard.setContent(rootGraveYard);
        scrollPaneGraveYard.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneGraveYard.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        setShopAndDeckAndGraveYardMenuText(rootGraveYard, sceneGraveYard, "Cards", 50);

        int xPosition = 0, yPosition = 0, x, y;
        for (Minion minion : Battle.getCurrentBattle().getPlayerTurn().getGraveYard().getCards())   //7
        {
            x = ROW_BLANK + (xPosition % 4) * (200 + BLANK_BETWEEN_CARDS);
            y = COLUMN_BLANK + yPosition / 4 * (250 + BLANK_BETWEEN_CARDS);
            showNonSpellCards(rootGraveYard, x, y, minion, minion.getCardID(), minion.getRequiredMP());
            xPosition++;
            yPosition++;
        }

        Button backButton = backButton(primaryStage, rootGraveYard, 20, 15);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                try
                {
                    primaryStage.setScene(sceneBattleField);
                    primaryStage.centerOnScreen();
                    setBattleField(primaryStage, map, true, battleMode);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setEndTurnButton(Stage primaryStage, Group rootBattleField, BattleMode battleMode)
    {
        ImageView endTurnButton = new ImageView("battleField BackGround/button_end_turn_mine_glow.png");
        endTurnButton.relocate(1100, 620);
        endTurnButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Battle.getCurrentBattle().clearTheHandPictures();
                Battle.getCurrentBattle().endTurn();
                setMPIcons(rootBattleField);
                Battle.getCurrentBattle().setHandIcons();

                setNextCard(rootBattleField);

                for (int number = 0; number < 5; number++)
                {
                    rootBattleField.getChildren().add(Battle.getCurrentBattle().getCurrentPlayerHand()[number]);
                }
                makeBattleFieldController(battleMode);
                setGlobalChatButton(primaryStage, rootBattleField);
            }
        });
        rootBattleField.getChildren().add(endTurnButton);
    }

    private void makeBattleFieldController(BattleMode battleMode)
    {
        battleFieldController = new BattleFieldController(this, rootBattleField, sceneBattleField, battleInfo, battleMode);
        battleFieldController.start();
    }

    private void setHeroFirstPlace()
    {
        Battle.getCurrentBattle().setHeroesFirstPlace();
    }

    private void setHeroIcons(Group rootBattleField)
    {
        Battle.getCurrentBattle().setHeroIcons(rootBattleField);
    }

    private void setHandIcons(Group rootBattleField)
    {
        Battle.getCurrentBattle().setHandIcons();
    }

    private void setGridPane(Group rootBattleField)
    {
        Battle.getCurrentBattle().setGridPane(rootBattleField);
    }

    private void setGlobalChatButton(Stage primaryStage, Group root)
    {
        Button chatButton = new Button("Global Chat");
        chatButton.relocate(10, 380);
        chatButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setCommand(CommandType.CHAT);
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
                primaryStage.setScene(sceneChatPage);
                primaryStage.centerOnScreen();
                goToChatMenu(primaryStage);
            }
        });
        root.getChildren().add(chatButton);
    }

    private void goToChatMenu(Stage primaryStage)
    {
        rootChatPage.getChildren().clear();

        setBackGroundImage(rootChatPage, "file:battleField BackGround/chat background.jpg");

        TextField textField = new TextField();
        TilePane tilePane = new TilePane();
        tilePane.getChildren().add(textField);
        tilePane.relocate(10, 500);
        rootChatPage.getChildren().add(tilePane);
        showMessage();
        ImageView refresh = new ImageView("file:battleField BackGround/refresh.jpg");
        refresh.relocate(200, 200);
        refresh.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                showMessage();
            }
        });
        rootChatPage.getChildren().add(refresh);

        backButton(primaryStage, rootChatPage, 400, 450);
        makeSendButton(rootChatPage, textField);
    }

    private void showMessage()
    {
        ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.GET_ALL_MESSAGES_IN_CHAT, client.getAuthToken());
        String getAllMessagesInChat = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
        System.out.println(getAllMessagesInChat);
        try
        {
            Client.getSendMessage().addMessage(getAllMessagesInChat);
            synchronized (validMessageFromServer)
            {
                validMessageFromServer.wait();
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if (client.getMessageFromServer().getServerCommandEnum().equals(ServerCommandEnum.OK))
        {
            ArrayList<ChatMessage> chatMessages = client.getMessageFromServer().getChatMessages();
            if (chatMessages != null)
            {
                int counter = 0;
                for (ChatMessage chatMessage : chatMessages)
                {
                    Text text = new Text(chatMessage.getSender().getAccountName() + " : " + chatMessage.getMessage());
                    text.relocate(10, 10 + counter * 20);
                    rootChatPage.getChildren().add(text);
                    counter++;
                }
            }
        }
    }

    private void makeSendButton(Group rootChatPage, TextField textField)
    {
        Button button = new Button("SEND");
        button.setFont(Font.font("Verdana", 12));
        button.relocate(300, 500);
        button.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Text text = new Text();
                text.setText(textField.getText());
                ChatMessage chatMessage = new ChatMessage(accountConnectedToThisClient, text.getText());
                ClientCommand clientCommand = new ClientCommand(ClientCommandEnum.SEND_MESSAGE, chatMessage, client.getAuthToken());
                String sendMessageJson = new GsonBuilder().setPrettyPrinting().create().toJson(clientCommand);
                try
                {
                    Client.getSendMessage().addMessage(sendMessageJson);
                    synchronized (validMessageFromServer)
                    {
                        validMessageFromServer.wait();
                    }
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (client.getMessageFromServer().getServerCommandEnum().equals(ServerCommandEnum.OK))
                {
                    System.out.println("Message Sent");
                    textField.setText(null);
                    showMessage();
                }

            }
        });
        rootChatPage.getChildren().add(button);
    }


    public void getBattleCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (input.equalsIgnoreCase("Game Info"))
        {
            setCommand(CommandType.GAME_INFO);
        }
        else if (input.equalsIgnoreCase("Show My Minions"))
        {
            setCommand(CommandType.SHOW_MY_MINIONS);
        }
        else if (input.equalsIgnoreCase("Show Opponent Minions"))
        {
            setCommand(CommandType.SHOW_OPPONENT_MINIONS);
        }
        else if (patternShowCardInfo.matcher(input).matches())
        {
            setCommand(CommandType.SHOW_CARD_INFO);
            getCommand().cardOrItemID = inputParts[3];
        }
        else if (patternSelect.matcher(input).matches())
        {
            setCommand(CommandType.SELECT);
            getCommand().cardOrItemID = inputParts[1];
        }
        else if (input.equalsIgnoreCase("Show collectibles"))
        {
            setCommand(CommandType.SHOW_COLLECTIBLES);
        }
        else if (patternSelectItem.matcher(input).matches())
        {
            setCommand(CommandType.SELECT_ITEM);
            getCommand().cardOrItemID = inputParts[1];
        }
        else if (patternInsertCard.matcher(input).matches())
        {
            setCommand(CommandType.INSERT_CARD);
            getCommand().insertRow = Integer.parseInt(inputParts[4]);
            getCommand().insertColumn = Integer.parseInt(inputParts[6]);
            getCommand().insertCardName = inputParts[1];

        }
        else if (input.equalsIgnoreCase("Show Hand"))
        {
            setCommand(CommandType.SHOW_HAND);
        }
        else if (input.equalsIgnoreCase("Show Next Card"))
        {
            setCommand(CommandType.SHOW_NEXT_CARD);
        }
        else if (input.equalsIgnoreCase("Enter graveyard"))
        {
            setCommand(CommandType.ENTER_GRAVEYARD);
        }
        else if (input.equalsIgnoreCase("Help"))
        {
            setCommand(CommandType.HELP_BATTLE);
        }
        else if (input.equalsIgnoreCase("End Turn"))
        {
            setCommand(CommandType.END_TURN);
        }
        else if (input.equalsIgnoreCase("Surrender"))
        {
            setCommand(CommandType.SURRENDER);
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public void getAfterSelectCardCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (patternNormalAttack.matcher(input).matches())
        {
            setCommand(CommandType.NORMAL_ATTACK);
            getCommand().enemyCardIDForNormalAttack = inputParts[1];
        }
        else if (input.contains("Move To"))
        {
            setCommand(CommandType.MOVE_TO);
            getCommand().rowOfTheCell = Integer.parseInt(inputParts[2]);
            getCommand().columnOfTheCell = Integer.parseInt(inputParts[4]);
        }
        else if (patternUseSpecialPower.matcher(input).matches())
        {
            setCommand(CommandType.USE_SPECIAL_POWER);
            getCommand().rowOfTheCell = Integer.parseInt(inputParts[3]);
            getCommand().columnOfTheCell = Integer.parseInt(inputParts[4]);
        }
        else if (patternComboAttack.matcher(input).matches())
        {
            setCommand(CommandType.COMBO_ATTACK);
            getCommand().enemyCardIDForCombo = inputParts[2];
            for (int counter = 3; counter < inputParts.length; counter++)
            {
                getCommand().cardIDsForComboAttack.add(inputParts[counter]);
            }
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public void getAfterSelectItemCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (input.equalsIgnoreCase("Show Info"))
        {
            setCommand(CommandType.SHOW_ITEM_INFO);
        }
        else if (patternUseItem.matcher(input).matches())
        {
            setCommand(CommandType.USE_ITEM);
            getCommand().rowOfTheCell = Integer.parseInt(inputParts[1]);
            getCommand().columnOfTheCell = Integer.parseInt(inputParts[2]);
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public void getAfterGameEndedCommand()
    {
        String input = myScanner.nextLine();
        if (input.equalsIgnoreCase("End Game"))
        {
            setCommand(CommandType.END_GAME);
        }
        else
        {
            showOutput.printOutput("invalid command");
            setCommand(null);
        }
    }

    public void getShowMenuAndExitCommand(String input)
    {
        if (input.equalsIgnoreCase("Show menu"))
        {
            setCommand(CommandType.SHOW_MENU);
        }
        else if (input.equals("exit"))
        {
            setCommand(CommandType.EXIT);
        }
        else
        {
            showOutput.printOutput("invalid command");
            setCommand(null);
        }
    }

    public Account getAccountConnectedToThisClient()
    {
        return accountConnectedToThisClient;
    }
}
