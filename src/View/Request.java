package View;

import Controller.AccountManager;
import Controller.BattleFieldController;
import Model.*;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
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

    private static Request request = null;
    private ShowOutput showOutput = ShowOutput.getInstance();
    private AccountManager accountManager = new AccountManager();
    private CommandType command;
    public final Object requestLock = new Object();

    public CommandType getCommand()
    {
        return command;
    }

    public void setCommand(CommandType command)
    {
        Request.getInstance().command = command;
    }

    private Request()
    {
        //just added to make Request singleton
    }

    public static Request getInstance()
    {
        if (request == null)
        {
            request = new Request();
        }
        return request;
    }

    private static final int ROW_BLANK = 20;
    private static final int COLUMN_BLANK = 80;
    private static final int BLANK_BETWEEN_CARDS = 50;
    private static final int CARDS_RECTANGLE_WIDTH = 200;
    private static final int CARDS_RECTANGLE_HEIGHT = 250;

    private Group rootSignUpMenu = Main.getRootSignUpMenu();
    private Scene sceneSignUpMenu = Main.getSceneSignUpMenu();
    private Group rootLoginMenu = Main.getRootLoginMenu();
    private Scene sceneLoginMenu = Main.getSceneLoginMenu();
    private Group rootMainMenu = Main.getRootMainMenu();
    private Scene sceneMainMenu = Main.getSceneMainMenu();
    private Group rootLeaderBoard = Main.getRootLeaderBoard();
    private Scene sceneLeaderBoard = Main.getSceneLeaderBoard();
    private Group rootProfile = Main.getRootProfile();
    private Scene sceneProfile = Main.getSceneProfile();
    private Group rootShop = Main.getRootShop();
    private ScrollPane scrollPaneShop = Main.getScrollPaneShop();
    private Scene sceneShop = Main.getSceneShop();
    private Group rootCollection = Main.getRootCollection();
    private ScrollPane scrollPaneCollection = Main.getScrollPaneCollection();
    private Group rootDeck = Main.getRootDeck();
    private ScrollPane scrollPaneDeck = Main.getScrollPaneDeck();
    private Scene sceneDeck = Main.getSceneDeck();
    private Scene sceneCollection = Main.getSceneCollection();
    private Group rootBattleMenu = Main.getRootBattle();
    private Scene sceneBattleMenu = Main.getSceneBattle();
    private Group rootSinglePlayer = Main.getRootSinglePlayer();
    private Scene sceneSinglePlayer = Main.getSceneSinglePlayer();
    private Group rootMultiPlayer = Main.getRootMultiPlayer();
    private Scene sceneMultiPlayer = Main.getSceneMultiPlayer();
    private Group rootStoryMode = Main.getRootMultiPlayer();
    private Scene sceneStoryMode = Main.getSceneMultiPlayer();
    private Group rootCustomGame = Main.getRootCustomGame();
    private Scene sceneCustomGame = Main.getSceneCustomGame();
    private Scene sceneImportingDeck = Main.getSceneImportingDeck();
    private Group rootImportingDeck = Main.getRootImportingDeck();
    private Group rootBattleField = Main.getRootBattleField();
    private Scene sceneBattleField = Main.getSceneBattleField();
    private GridPane BattleFieldGridPane = new GridPane();
    private Group rootMakingCustomCard = Main.getRootMakingCustomCards();
    private Scene sceneMakingCustomCards = Main.getSceneMakingCustomCards();
    private Group rootGraveYard = Main.getRootGraveYard();
    private ScrollPane scrollPaneGraveYard = Main.getScrollPaneGraveYard();
    private Scene sceneGraveYard = Main.getSceneGraveYard();
    private Scene sceneHeroCustom = Main.getSceneHeroCustom();
    private Group rootHeroCustom = Main.getRootHeroCustom();
    private Scene sceneMinionCustom = Main.getSceneMinionCustom();
    private Group rootMinionCustom = Main.getRootMinionCustom();
    private Group rootSpellCustom = Main.getRootSpellCustom();
    private Scene sceneSpellCustom = Main.getSceneSpellCustom();
    private Deck selectedDeckForCustomGame = null;
    private BattleFieldController battleFieldController;
    private Account multiPlayerAccountToBattle;
    private Text battleInfo;

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
                if (userName.isEmpty() || password.isEmpty())
                {
                    rootSignUpMenu.getChildren().add(labelInvalidInput);
                    labelInvalidInput.setText("you must Fill both TextFields");
                    return;
                }
                Account account = accountManager.findAccount(userName);
                if (account == null)
                {
                    rootSignUpMenu.getChildren().remove(labelInvalidInput);
                    account = accountManager.createAccount(userName, password);
                    try
                    {
                        accountManager.saveAccountInfo(account, userName, true);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    primaryStage.setScene(sceneLoginMenu);
                    primaryStage.centerOnScreen();
                    login(primaryStage);
                }
                else
                {
                    labelInvalidInput.setText("Account exists with this name");
                    rootSignUpMenu.getChildren().add(labelInvalidInput);
                }
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
                if (name.isEmpty() || password.isEmpty())
                {
                    labelInvalidInput.setText("you must Fill both TextFields");
                    rootLoginMenu.getChildren().add(labelInvalidInput);
                    return;
                }
                Account account = accountManager.findAccount(name);
                if (account == null)
                {
                    labelInvalidInput.setText("Invalid name or password");
                    rootLoginMenu.getChildren().add(labelInvalidInput);
                }
                else if (account.getPassword().equals(password))
                {
                    accountManager.login(account);
                    rootSignUpMenu.getChildren().remove(labelInvalidInput);
                    primaryStage.setScene(sceneMainMenu);
                    primaryStage.centerOnScreen();
                    try
                    {
                        mainMenu(primaryStage);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    labelInvalidInput.setText("Password is Wrong.Try again");
                    rootLoginMenu.getChildren().add(labelInvalidInput);
                }
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
                        setCommand(CommandType.SAVE);
                        synchronized (requestLock)
                        {
                            requestLock.notify();
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
                        setCommand(CommandType.LOGOUT);
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        login(primaryStage);
                        break;
                    case "Exit":
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
            workingOnSpellText(textFields);
        });
        rootSpellCustom.getChildren().addAll(back, apply);
        stage.setScene(sceneSpellCustom);
    }

    private void workingOnSpellText(ArrayList<TextField> textFields)
    {
        String name = textFields.get(0).getText();
        String numOfTarget = textFields.get(1).getText();
        String kindOfMinion = textFields.get(2).getText();
        String nameOfBuff = textFields.get(3).getText();
        String buffType = textFields.get(4).getText();
        String effectValue = textFields.get(5).getText();
        String delay = textFields.get(6).getText();
        String last = textFields.get(7).getText();
        String friendOrEnemy = textFields.get(8).getText();
        String numOfFriendOrEnemy = textFields.get(9).getText();
        String isAll = textFields.get(10).getText();
        String mp = textFields.get(11).getText();
        String numOfHolyBuff = textFields.get(12).getText();
        String changingHP = textFields.get(13).getText();
        String changingAP = textFields.get(14).getText();
        String changingMp = textFields.get(15).getText();
        String cost = textFields.get(16).getText();
        makingSpellCard(name, numOfTarget, kindOfMinion, nameOfBuff, buffType, effectValue, delay, last, friendOrEnemy, numOfFriendOrEnemy, isAll, mp, numOfHolyBuff, changingAP, changingHP, changingMp, cost);
    }

    private void makingSpellCard(String name, String numOfTarget, String kindOfMinion, String nameOfBuff, String buffType, String effectValue, String delay, String last, String friendOrEnemy, String numOfFriendOrEnemy, String isAll, String MP, String numOfHolyBuff, String changingAp, String changingHp, String changingMp, String cost)
    {
        Spell spell = new Spell();
        spell.setCardName(name);
        int mp = Integer.parseInt(MP);
        spell.setRequiredMP(mp);
        int holyBuffNumber = Integer.parseInt(numOfHolyBuff);
        int apChanging = Integer.parseInt(changingAp);
        int HpChanging = Integer.parseInt(changingHp);
        int MPChanging = Integer.parseInt(changingMp);
        int lasting = Integer.parseInt(last);
        int price = Integer.parseInt(cost);
        spell.setPrice(price);

        spell.getSpellEffect().addSpellChange(new SpellChange());
        spell.getSpellEffect().addTarget(new Target());
        spell.getSpellEffect().getSpellChanges().get(0).setTurnsToApplyChange(lasting);
        spell.getSpellEffect().getSpellChanges().get(0).setChangeMP(MPChanging);
        if (buffType.equalsIgnoreCase("holy"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setActivateHolyBuff(true);
            spell.getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(holyBuffNumber);
        }
        if (buffType.equalsIgnoreCase("stun"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setStunOpponent(true);
        }
        if (buffType.equalsIgnoreCase("disarm"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setDisarmOpponent(true);
        }
        if (buffType.equalsIgnoreCase("power"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setChangeAP(apChanging);
            spell.getSpellEffect().getSpellChanges().get(0).setChangeHP(HpChanging);
        }
        if (buffType.equalsIgnoreCase("weakness"))
        {
            spell.getSpellEffect().getSpellChanges().get(0).setChangeHP(HpChanging);
            spell.getSpellEffect().getSpellChanges().get(0).setChangeAP(apChanging);
        }
        if (friendOrEnemy.equalsIgnoreCase("friend"))
        {
            spell.getSpellEffect().getTargets().get(0).setNumOfOwnMinions(Integer.parseInt(numOfFriendOrEnemy));
            if (isAll.equalsIgnoreCase("true"))
            {
                spell.getSpellEffect().getTargets().get(0).setAllOwnBothNonSpellCards(true);
            }
        }
        else if (friendOrEnemy.equalsIgnoreCase("enemy"))
        {
            spell.getSpellEffect().getTargets().get(0).setNumOfOpponentBothNonSpellCards(Integer.parseInt(numOfFriendOrEnemy));
            if (isAll.equalsIgnoreCase("true"))
            {
                spell.getSpellEffect().getTargets().get(0).setAllOpponentNonSpellCards(true);
            }
        }
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, spell, false);
        Shop.getInstance().addCardToShop(spell);
        Spell.getSpells().add(spell);
        showOutput.printOutput("Custom card " + spell.getCardID() + " added to your collection");
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
            workingOnMinionText(textFields);
        });

        rootMinionCustom.getChildren().addAll(back, apply);
        stage.setScene(sceneMinionCustom);
    }

    private void workingOnMinionText(ArrayList<TextField> textFields)
    {
        String name = textFields.get(0).getText();
        String Ap = textFields.get(1).getText();
        String Hp = textFields.get(2).getText();
        String AttackType = textFields.get(3).getText();
        String Range = textFields.get(4).getText();
        String specialPowerActivation = textFields.get(5).getText();
        String cost = textFields.get(6).getText();
        String turnsToApply = textFields.get(7).getText();
        String isPositive = textFields.get(8).getText();
        String untilEnd = textFields.get(9).getText();
        String changeAp = textFields.get(10).getText();
        String changeHp = textFields.get(11).getText();
        String changeMp = textFields.get(12).getText();
        String stun = textFields.get(13).getText();
        String disarm = textFields.get(14).getText();
        String numOfHolyBuff = textFields.get(15).getText();
        String toxic = textFields.get(16).getText();
        String holyCell = textFields.get(17).getText();
        String fiery = textFields.get(18).getText();
        String combo = textFields.get(19).getText();
        String numOfOwnMinion = textFields.get(20).getText();
        String numOfOpponentMinion = textFields.get(21).getText();
        String ownHero = textFields.get(22).getText();
        String opponentHero = textFields.get(23).getText();
        String numOfOpponentBothNonSpell = textFields.get(24).getText();
        String numOfOwnBothNonSpell = textFields.get(25).getText();
        String allOwnMinion = textFields.get(26).getText();
        String allOpponentBothNonSpell = textFields.get(27).getText();
        String allOwnBothNonSpell = textFields.get(28).getText();
        makingMinionCard(numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOwnBothNonSpell, allOpponentBothNonSpell, name, Ap, Hp, AttackType, Range, specialPowerActivation, cost, turnsToApply, isPositive, untilEnd, changeAp, changeHp, changeMp, stun, disarm, numOfHolyBuff, toxic, holyCell, fiery, combo);
    }

    private void makingMinionCard(String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOwnBothNonSpell, String allOpponentBothNonSpell, String name, String ap, String hp, String attackType, String range, String specialPowerActivation, String cost, String turn, String isPositive, String UntilEnd, String changeAP, String changeHP, String ChangeMP, String stun, String disarm, String numOfHolyBuff, String toxic, String holycell, String fiery, String combo)
    {
        int AP = Integer.parseInt(ap);
        int HP = Integer.parseInt(hp);
        int price = Integer.parseInt(cost);
        int rangeOfAttack = Integer.parseInt(range);
        int apChange = Integer.parseInt(changeAP);
        int hpChange = Integer.parseInt(changeHP);
        int MpChange = Integer.parseInt(ChangeMP);
        int turnToApply = Integer.parseInt(turn);
        int numberOfOwnMinion = Integer.parseInt(numOfOwnMinion);
        int numberOfOpponentMinion = Integer.parseInt(numOfOpponentMinion);
        int numberOfOpponentBothNonSpell = Integer.parseInt(numOfOpponentBothNonSpell);
        int numberOfOwnBothNonSpell = Integer.parseInt(numOfOwnBothNonSpell);
        int numberOfHolyBuff = Integer.parseInt(numOfHolyBuff);
        Minion minion = new Minion();
        minion.setCardName(name);
        minion.setDefaultAP(AP);
        minion.setDefaultHP(HP);
        minion.setRangeOfAttack(rangeOfAttack);
        SpecialPower specialPower = new SpecialPower("Custom card");
        specialPower.getSpellEffect().addSpellChange(new SpellChange());
        specialPower.getSpellEffect().addTarget(new Target());
        minion.setSpecialPower(specialPower);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(numberOfHolyBuff);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeAP(apChange);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeHP(hpChange);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeMP(MpChange);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTurnsToApplyChange(turnToApply);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentBothNonSpellCards(numberOfOpponentBothNonSpell);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnMinions(numberOfOwnMinion);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentMinions(numberOfOpponentMinion);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnBothNonSpellCards(numberOfOwnBothNonSpell);
        if (ownHero.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setOwnHero(true);
        }
        if (opponentHero.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setOpponentHero(true);
        }
        if (allOpponentBothNonSpell.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOpponentNonSpellCards(true);
        }
        if (allOwnBothNonSpell.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnBothNonSpellCards(true);
        }
        if (allOwnMinion.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnMinions(true);
        }
        if (attackType.equalsIgnoreCase("melee"))
        {
            minion.setImpactType(ImpactType.melee);
        }
        if (attackType.equalsIgnoreCase("ranged"))
        {
            minion.setImpactType(ImpactType.ranged);
        }
        if (attackType.equalsIgnoreCase("hybrid"))
        {
            minion.setImpactType(ImpactType.hybrid);
        }
        if (isPositive.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setPositiveChange(true);
        }
        if (stun.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setStunOpponent(true);
        }
        if (fiery.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellFiery(true);
        }
        if (combo.equalsIgnoreCase("true"))
        {
            minion.setAbleToCombo(true);
        }
        if (UntilEnd.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setApplyChangeUntilEndOfTheGame(true);
        }
        if (disarm.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setDisarmOpponent(true);
        }
        if (toxic.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellToxic(true);
        }
        if (holycell.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellHoly(true);
        }
        if (specialPowerActivation.equalsIgnoreCase("combo"))
        {
            minion.setAbleToCombo(true);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onTurn"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onTurn);
        }
        else if (specialPowerActivation.equalsIgnoreCase("passive"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.passive);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onAttack"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onAttack);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onSpawn"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onSpawn);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onDeath"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onDeath);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onDefend"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onDefend);
        }
        minion.setPrice(price);
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, minion, false);
        Shop.getInstance().addCardToShop(minion);
        Minion.getMinions().add(minion);
        showOutput.printOutput("Custom card " + minion.getCardID() + " added to your collection");
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
            workingOnHeroText(textFields);
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

    private void workingOnHeroText(ArrayList<TextField> textFields)
    {
        String name = textFields.get(0).getText();
        String Ap = textFields.get(1).getText();
        String Hp = textFields.get(2).getText();
        String AttackType = textFields.get(3).getText();
        String Range = textFields.get(4).getText();
        String coolDown = textFields.get(5).getText();
        String cost = textFields.get(6).getText();
        String turnsToApply = textFields.get(7).getText();
        String isPositive = textFields.get(8).getText();
        String untilEnd = textFields.get(9).getText();
        String changeAp = textFields.get(10).getText();
        String changeHp = textFields.get(11).getText();
        String changeMp = textFields.get(12).getText();
        String stun = textFields.get(13).getText();
        String disarm = textFields.get(14).getText();
        String numOfHolyBuff = textFields.get(15).getText();
        String toxic = textFields.get(16).getText();
        String holyCell = textFields.get(17).getText();
        String fiery = textFields.get(18).getText();
        String kill = textFields.get(19).getText();
        String numOfOwnMinion = textFields.get(20).getText();
        String numOfOpponentMinion = textFields.get(21).getText();
        String ownHero = textFields.get(22).getText();
        String opponentHero = textFields.get(23).getText();
        String numOfOpponentBothNonSpell = textFields.get(24).getText();
        String numOfOwnBothNonSpell = textFields.get(25).getText();
        String allOwnMinion = textFields.get(26).getText();
        String allOpponentBothNonSpell = textFields.get(27).getText();
        String allOwnBothNonSpell = textFields.get(28).getText();

        makingHeroCard(numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOpponentBothNonSpell, allOwnBothNonSpell, name, Ap, Hp, AttackType, Range, coolDown, cost, turnsToApply, isPositive, untilEnd, changeAp, changeHp, changeMp, stun, disarm, numOfHolyBuff, toxic, holyCell, fiery, kill);
    }

    private void makingHeroCard(String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOpponentBothNonSpell, String allOwnBothNonSpell, String name, String ap, String hp, String attackType, String range, String coolDown, String cost, String turnsToApply, String isPositive, String untilEnd, String changeAp, String changeHp, String changeMP, String stun, String disarm, String numOfHolyBuff, String toxic, String holyCell, String fiery, String kill)
    {
        int AP = Integer.parseInt(ap);
        int HP = Integer.parseInt(hp);
        int price = Integer.parseInt(cost, 10);
        int rangeOfAttack = Integer.parseInt(range);
        int cooldown = Integer.parseInt(coolDown, 10);
        int turn = Integer.parseInt(turnsToApply);
        int apChange = Integer.parseInt(changeAp);
        int hpChange = Integer.parseInt(changeHp);
        int mpChange = Integer.parseInt(changeMP);
        int holyNumber = Integer.parseInt(numOfHolyBuff);
        int numberOfOwnMinion = Integer.parseInt(numOfOwnMinion);
        int numberOfOpponentMinion = Integer.parseInt(numOfOpponentMinion);
        int numberOfOpponentBothNonSpell = Integer.parseInt(numOfOpponentBothNonSpell);
        int numberOfOwnBothNonSpell = Integer.parseInt(numOfOwnBothNonSpell);
        int numberOfHolyBuff = Integer.parseInt(numOfHolyBuff);
        Hero hero = new Hero();
        hero.setCardName(name);
        hero.setDefaultAP(AP);
        hero.setDefaultHP(HP);
        SpecialPower specialPower = new SpecialPower("Custom card");
        specialPower.getSpellEffect().addTarget(new Target());
        specialPower.getSpellEffect().addSpellChange(new SpellChange());
        hero.setSpecialPower(specialPower);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTurnsToApplyChange(turn);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnBothNonSpellCards(numberOfOwnBothNonSpell);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentMinions(numberOfOpponentMinion);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnMinions(numberOfOwnMinion);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentBothNonSpellCards(numberOfOpponentBothNonSpell);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(numberOfHolyBuff);
        if (allOpponentBothNonSpell.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOpponentNonSpellCards(true);
        }
        if (allOwnBothNonSpell.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnBothNonSpellCards(true);
        }
        if (allOwnMinion.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnMinions(true);
        }
        if (ownHero.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setOwnHero(true);
        }
        if (opponentHero.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setOpponentHero(true);
        }
        if (attackType.equalsIgnoreCase("melee"))
        {
            hero.setImpactType(ImpactType.melee);
        }
        if (attackType.equalsIgnoreCase("ranged"))
        {
            hero.setImpactType(ImpactType.ranged);
        }
        if (attackType.equalsIgnoreCase("hybrid"))
        {
            hero.setImpactType(ImpactType.hybrid);
        }

        if (isPositive.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setPositiveChange(true);
        }
        if (untilEnd.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setApplyChangeUntilEndOfTheGame(true);
        }
        if (stun.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setStunOpponent(true);
        }
        if (disarm.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setDisarmOpponent(true);
        }
        if (toxic.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellToxic(true);
        }
        if (kill.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setKilling(true);
        }
        if (fiery.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellFiery(true);
        }
        if (holyCell.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellHoly(true);
        }
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(holyNumber);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeHP(hpChange);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeAP(apChange);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeMP(mpChange);
        hero.setPrice(price);
        hero.setCoolDown(cooldown);
        hero.setRangeOfAttack(rangeOfAttack);
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, hero, false);
        Shop.getInstance().addCardToShop(hero);
        Hero.getHeroes().add(hero);
        showOutput.printOutput("Custom card " + hero.getCardID() + " added to your collection");
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
        rootProfile.getChildren().clear();

        Label labelProfile = new Label(Account.loggedInAccount.getAccountName());
        labelProfile.setFont(Font.font(40));
        labelProfile.setTextFill(Color.RED);
        labelProfile.relocate(50, 25);
        labelProfile.layoutXProperty().bind(sceneProfile.widthProperty().subtract(labelProfile.prefWidth(-1)).divide(2));
        labelProfile.setLayoutY(25);
        rootProfile.getChildren().add(labelProfile);

        Label labelMoney = new Label("your Money : " + Account.loggedInAccount.getMoney());
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
        Label labelTop10 = new Label("Top 10");
        labelTop10.setTextFill(YELLOW);
        labelTop10.setFont(Font.font(30));
        labelTop10.relocate(100, 0);
        rootLeaderBoard.getChildren().clear();
        rootLeaderBoard.getChildren().add(labelTop10);
        showOutput.showRankingPlayers();
        backButton(primaryStage, rootLeaderBoard, 100, 600);
        primaryStage.setScene(sceneLeaderBoard);
        primaryStage.centerOnScreen();
    }

    public Button backButton(Stage primaryStage, Group root, int x, int y)
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
        setBackGroundImage(rootShop, "file:BackGround Images/Duelyst Menu Blurred.jpg");

        scrollPaneShop.setContent(rootShop);
        scrollPaneShop.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneShop.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int xPosition = 0, yPosition = 0, x = 0, y = 0;
        setShopAndDeckAndGraveYardMenuText(rootShop, sceneShop, "Heroes", 50);
        for (Hero hero : Hero.getHeroes())
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
            setShopStackPanesOnMouseClicked(stackPane, hero.getCardName(), hero.getPrice());
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
        for (Minion minion : Minion.getMinions())
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
            setShopStackPanesOnMouseClicked(stackPane, minion.getCardName(), minion.getPrice());
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
        for (Spell spell : Spell.getSpells())
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
            setShopStackPanesOnMouseClicked(stackPane, spell.getCardName(), spell.getPrice());
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
        for (Item item : Item.getItems())
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
            setShopStackPanesOnMouseClicked(stackPane, item.getItemName(), item.getPrice());
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

    private void setShopStackPanesOnMouseClicked(StackPane stackPane, String name, int price)
    {
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
                    Media sound = new Media(new File("Sounds and Music/Buy card.mp3").toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                    if (specifyKindOfCard(name)==1)
                    {
                        Item.decreaseCapacityOfItemSell();
                        System.out.println(Item.getCapacityOfItemSell());
                    }
                    else
                    {
                        Card.decreaseCapacityOfSell();
                        System.out.println(Card.getCapacityOfSell());
                    }
                    setCommand(CommandType.BUY);
                    request.getCommand().cardOrItemName = name;
                    synchronized (requestLock)
                    {
                        requestLock.notify();
                    }
                }
            }
        });
    }

    public int specifyKindOfCard(String name)
    {
        for (int i = 0; i<Item.getItems().size();i++)
        {
            if (Item.getItems().get(i).getItemName() == name)
            {
                return 1;
            }
        }
        return -1;
    }
    public void collectionMenu(Stage primaryStage, boolean isSearchedElement, String searchedElement)
    {
        setBackGroundImage(rootCollection, "file:BackGround Images/Duelyst Menu Blurred.jpg");

        scrollPaneCollection.setContent(rootCollection);
        scrollPaneCollection.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneCollection.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        setCollectionMenuText("Heroes", 50, false);
        int xPosition = 0, yPosition = 0, x = 0, y = 0;
        for (Hero hero : Account.loggedInAccount.getCollection().getHeroes())
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
            setCollectionCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, hero.getCardID(), hero.getPrice());
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
        for (Minion minion : Account.loggedInAccount.getCollection().getMinions())
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
            setCollectionCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, minion.getCardID(), minion.getPrice());
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
        for (Spell spell : Account.loggedInAccount.getCollection().getSpells())
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
            setCollectionCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, spell.getCardID(), spell.getPrice());
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
        for (Item item : Account.loggedInAccount.getCollection().getItems())
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
            setCollectionCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, item.getItemID(), item.getPrice());
            xPosition++;
            yPosition++;
        }

        setCollectionMenuText("Decks", 50, true);
        yPosition = 0;
        x = ROW_BLANK + 3 * (200 + BLANK_BETWEEN_CARDS);
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
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
        if (Account.loggedInAccount.getMainDeck() != null && deck.equals(Account.loggedInAccount.getMainDeck()))
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

    private void setCollectionCardAndItemStackPanesOnMouseClicked(Stage primaryStage, StackPane stackPane, String ID, int price)
    {
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
                    setCommand(CommandType.SELL);

                    request.getCommand().cardOrItemID = ID;
                    synchronized (requestLock)
                    {
                        requestLock.notify();
                    }
                    collectionMenu(primaryStage, false, null);
                }
                else if (option.get() == buttonTypeAddToDeck)
                {
                    showAllDecks(primaryStage, ID);
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
                ButtonType buttonTypeSetMainDeck = new ButtonType("Set as Main deck");
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
                    setCommand(CommandType.DELETE_DECK);
                    request.getCommand().deckName = deck.getDeckName();
                    synchronized (requestLock)
                    {
                        requestLock.notify();
                    }
                    collectionMenu(primaryStage, false, null);
                }
                else if (option.get() == buttonTypeSetMainDeck)
                {
                    setCommand(CommandType.SET_MAIN_DECK);
                    request.getCommand().deckName = deck.getDeckName();
                    synchronized (requestLock)
                    {
                        requestLock.notify();
                    }
                    collectionMenu(primaryStage, false, null);
                }
                else if (option.get() == buttonTypeValidateDeck)
                {
                    setCommand(CommandType.VALIDATE_DECK);
                    request.getCommand().deckName = deck.getDeckName();
                    synchronized (requestLock)
                    {
                        requestLock.notify();
                    }
                    collectionMenu(primaryStage, false, null);
                }
                else if (option.get() == buttonTypeExportDeck)
                {
                    String accountName = Account.loggedInAccount.getAccountName();
                    String exportingDeckJson = new GsonBuilder().setPrettyPrinting().create().toJson(deck);
                    try
                    {
                        writeExportedDeckNameInFile(accountName + deck.getDeckName());

                        FileWriter fileWriter = new FileWriter("SavedDecks/" + accountName + deck.getDeckName() + ".json");
                        fileWriter.write(exportingDeckJson);
                        System.out.println(accountName + deck.getDeckName());
                        fileWriter.close();
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void writeExportedDeckNameInFile(String exportedDeckName) throws Exception
    {
        InputStream inputStream = new FileInputStream("SavedDecks/savedDecksPath.txt");
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext())
        {
            if (scanner.nextLine().equals(exportedDeckName))
            {
                return;
            }
        }
        FileWriter savedDecksPath = new FileWriter("SavedDecks/savedDecksPath.txt", true);
        savedDecksPath.write(exportedDeckName + "\n");
        savedDecksPath.close();
    }

    private void showAllDecks(Stage primaryStage, String ID)
    {
        rootCollection.getChildren().clear();

        setBackGroundImage(rootCollection, "file:BackGround Images/Duelyst Menu Blurred.jpg");

        int xPosition = 0, yPosition = 0, x, y;
        setShopAndDeckAndGraveYardMenuText(rootCollection, sceneCollection, "Decks", 50);
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
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
                    ButtonType buttonTypeSell = new ButtonType("Add");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel");
                    alert.getButtonTypes().addAll(buttonTypeSell, buttonTypeCancel);
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get() == buttonTypeSell)
                    {
                        Media sound = new Media(new File("Sounds and Music/Add card to deck.wav").toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.play();
                        setCommand(CommandType.ADD_TO_DECK);
                        getCommand().cardOrItemID = ID;
                        getCommand().deckName = deck.getDeckName();
                        synchronized (requestLock)
                        {
                            requestLock.notify();
                        }
                        rootCollection.getChildren().clear();
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
                    if (!createDeckTextField.getText().isEmpty())
                    {
                        setCommand(CommandType.CREATE_DECK);
                        getCommand().deckName = createDeckTextField.getText();
                        synchronized (request.requestLock)
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
                    importingToCollection(deckNames.get(finalI));
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

    private void importingToCollection(String deckName) throws IOException, ParseException
    {
        JsonParser jsonParser = new JsonParser();
        FileReader reader = new FileReader("SavedDecks/" + deckName + ".json");
        Object obj = jsonParser.parse(reader);
        System.out.println(obj);
        Deck deck = new Gson().fromJson(obj.toString(), Deck.class);
        deck.setDeckName("Imported " + deck.getDeckName());
        Account.loggedInAccount.getPlayerDecks().add(deck);
        addImportedDeckCardsAndItemsToCollection(deck);
    }

    private void addImportedDeckCardsAndItemsToCollection(Deck deck)
    {
        for (Hero hero : deck.getHero())
        {
            Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, hero, true);
        }
        for (Minion minion : deck.getMinions())
        {
            Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, minion, true);
        }
        for (Spell spell : deck.getSpells())
        {
            Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, spell, true);
        }
        for (Item item : deck.getItem())
        {
            Account.loggedInAccount.getCollection().addItem(Account.loggedInAccount, item, true);
        }
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
        for (Hero hero : deck.getHero())
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = COLUMN_BLANK + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            xPosition++;
            yPosition++;
            StackPane stackPane = showNonSpellCards(rootDeck, x, y, hero, hero.getCardID(), hero.getRequiredMP());
            setDeckCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, deck, hero.getCardID());
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
        for (Minion minion : deck.getMinions())
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 2 * COLUMN_BLANK - BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showNonSpellCards(rootDeck, x, y, minion, minion.getCardID(), minion.getRequiredMP());
            setDeckCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, deck, minion.getCardID());
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
        for (Spell spell : deck.getSpells())
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 3 * COLUMN_BLANK - 2 * BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showCardAndItemImageAndFeatures(rootDeck, x, y, spell.getCardID(), spell.getPrice(), spell.getRequiredMP());
            setDeckCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, deck, spell.getCardID());
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
        for (Item item : deck.getItem())
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 4 * COLUMN_BLANK - 3 * BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            StackPane stackPane = showCardAndItemImageAndFeatures(rootDeck, x, y, item.getItemID(), item.getPrice(), 0);
            setDeckCardAndItemStackPanesOnMouseClicked(primaryStage, stackPane, deck, item.getItemID());
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

    private void setDeckCardAndItemStackPanesOnMouseClicked(Stage primaryStage, StackPane stackPane, Deck deck, String ID)
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
                    multiPlayerMenu(primaryStage);
                    //todo battlefield
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
                    storyModeMenu(primaryStage);
                    break;
                case "Custom Game":
                    setCommand(CommandType.CUSTOM_GAME);
                    customGameMenuToChooseDeck(primaryStage);
                    break;
            }
            synchronized (requestLock)
            {
                requestLock.notify();
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
                    if (!Main.getCallTheAppropriateFunction().customGameBattleMaker(selectedDeckForCustomGame, 1))
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
                    if (!Main.getCallTheAppropriateFunction().customGameBattleMaker(selectedDeckForCustomGame, 2))
                    {
                        return;
                    }
                    try
                    {
                        setBattleField(primaryStage, "customGameBackGround", false , BattleMode.GATHERING_FLAGS);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "Mode 3":
                    if (!Main.getCallTheAppropriateFunction().customGameBattleMaker(selectedDeckForCustomGame, 3))
                    {
                        return;
                    }
                    try
                    {
                        setBattleField(primaryStage, "customGameBackGround", false , BattleMode.KEEP_FLAG_FOR_6_TURNS);
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

        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
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
                    Main.getCallTheAppropriateFunction().storyModeBattleMaker(1);
                    try
                    {
                        setBattleField(primaryStage, "backgroundStory1", false , BattleMode.KILLING_ENEMY_HERO);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "Mission 2":
                    Main.getCallTheAppropriateFunction().storyModeBattleMaker(2);
                    try
                    {
                        setBattleField(primaryStage, "backgroundStory2", false , BattleMode.KEEP_FLAG_FOR_6_TURNS);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "Mission 3":
                    Main.getCallTheAppropriateFunction().storyModeBattleMaker(3);
                    try
                    {
                        setBattleField(primaryStage, "backgroundStory3", false , BattleMode.GATHERING_FLAGS);
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


    private void multiPlayerMenu(Stage primaryStage)
    {
        setBackGroundImage(rootMultiPlayer, "file:BackGround Images/MultiPlayerrr.jpg");
        setMultiPlayerMenu("Choose  One Player", primaryStage, 75);
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
                MultiPlayerChooseModeMenu(rootMultiPlayer, primaryStage);
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
                    singlePlayerMenu(primaryStage);
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
                    Main.getCallTheAppropriateFunction().multiPayerBattleMaker(1, new Player(multiPlayerAccountToBattle, false));
                    try
                    {
                        setBattleField(primaryStage, "customGameBackGround", false , BattleMode.KILLING_ENEMY_HERO);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "Mode 2":
                    Main.getCallTheAppropriateFunction().multiPayerBattleMaker(2, new Player(multiPlayerAccountToBattle, false));
                    try
                    {
                        setBattleField(primaryStage, "customGameBackGround", false , BattleMode.GATHERING_FLAGS);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "Mode 3":
                    Main.getCallTheAppropriateFunction().multiPayerBattleMaker(3, new Player(multiPlayerAccountToBattle, false));
                    try
                    {
                        setBattleField(primaryStage, "customGameBackGround", false , BattleMode.KEEP_FLAG_FOR_6_TURNS);
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
        rootMultiPlayer.getChildren().add(title);
    }

    private void showChoosePlayerMenu(Group rootMultiPlayer)
    {
        Menu decksMenu = new Menu("Players");

        for (Account account : AccountManager.getAccounts())
        {
            MenuItem menuItem = new MenuItem(account.getAccountName());
            decksMenu.getItems().add(menuItem);
            menuItem.setOnAction(e -> {
                multiPlayerAccountToBattle = account;
            });
        }

        MenuBar menuBar = new MenuBar(decksMenu);
        menuBar.relocate(500, 230);
        rootMultiPlayer.getChildren().add(menuBar);
    }

    private void setMultiPlayerMenu(String string, Stage primaryStage, int location)
    {
        Text multiPlayerText = new Text(string);
        multiPlayerText.setFont(Font.font(null, FontPosture.ITALIC, 50));
        multiPlayerText.setTextOrigin(VPos.TOP);
        multiPlayerText.setFill(BLUE);
        multiPlayerText.layoutXProperty().bind(sceneMultiPlayer.widthProperty().subtract(multiPlayerText.prefWidth(-1)).divide(2));
        multiPlayerText.setY(location);
        rootMultiPlayer.getChildren().add(multiPlayerText);
    }

    private void setBattleField(Stage primaryStage, String map, boolean backFromGraveYard , BattleMode battleMode) throws IOException
    {
        if (!backFromGraveYard)
        {
            setBackGroundImage(rootBattleField, "battleField BackGround/" + map + ".jpg");
            setGridPane(rootBattleField);
            setHeroIcons(rootBattleField);
            setHandIcons(rootBattleField);
            setPlayersName(rootBattleField);
            setMPIcons(rootBattleField);
            setHeroFirstPlace(rootBattleField);
            setGraveYardButton(primaryStage, rootBattleField, map , battleMode);
            setSurrenderButton(primaryStage, rootBattleField, map);
            setNextCard(rootBattleField);
            showGameInfo(rootBattleField);
            setEndTurnButton(rootBattleField , battleMode);
        }
        battleFieldController = new BattleFieldController(rootBattleField, sceneBattleField , battleInfo , battleMode);
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
        Label firstPlayerName = new Label(Battle.getCurrentBattle().getFirstPlayer().getAccount().getAccountName());
        firstPlayerName.relocate(250, 50);
        firstPlayerName.setFont(Font.font(20));
        firstPlayerName.setTextFill(BLACK);
        rootBattleField.getChildren().add(firstPlayerName);

        Label secondPlayerName = new Label(Battle.getCurrentBattle().getSecondPlayer().getAccount().getAccountName());
        secondPlayerName.relocate(920, 50);
        secondPlayerName.setFont(Font.font(20));
        secondPlayerName.setTextFill(BLACK);
        rootBattleField.getChildren().add(secondPlayerName);
    }

    public void setMPIcons(Group rootBattleField)
    {
        for (int i = 0; i < 10; i++)
        {
            ImageView firstPlayerMPIcon = new ImageView("ManaIcons/icon_mana_inactive.png");
            if (Battle.getCurrentBattle().getFirstPlayer().getMP() > i)
            {
                firstPlayerMPIcon = new ImageView("ManaIcons/icon_mana.png");
            }
            firstPlayerMPIcon.relocate(250 + i * 20, 90);

            rootBattleField.getChildren().add(firstPlayerMPIcon);
        }

        for (int i = 0; i < 10; i++)
        {
            ImageView secondPlayerMPIcon = new ImageView("ManaIcons/icon_mana_inactive.png");
            if (Battle.getCurrentBattle().getSecondPlayer().getMP() > i)
            {
                secondPlayerMPIcon = new ImageView("ManaIcons/icon_mana.png");
            }
            secondPlayerMPIcon.relocate(1100 - i * 20, 90);

            rootBattleField.getChildren().add(secondPlayerMPIcon);
        }
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
                request.setCommand(CommandType.END_GAME);
                synchronized (request.requestLock)
                {
                    request.requestLock.notify();
                }
                primaryStage.setScene(sceneMainMenu);
                primaryStage.centerOnScreen();
                mainMenu(primaryStage);
            }
        });
        rootBattleField.getChildren().add(graveYardButton);
    }

    private void setGraveYardButton(Stage primaryStage, Group rootBattleField, String url , BattleMode battleMode)
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
                showGraveYard(primaryStage, url , battleMode);
            }
        });
        rootBattleField.getChildren().add(graveYardButton);
    }

    private void showGraveYard(Stage primaryStage, String map , BattleMode battleMode)
    {
        rootGraveYard.getChildren().clear();

        setBackGroundImage(rootGraveYard, "file:BackGround Images/GraveYard Image.jpg");

        scrollPaneGraveYard.setContent(rootGraveYard);
        scrollPaneGraveYard.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneGraveYard.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        setShopAndDeckAndGraveYardMenuText(rootGraveYard, sceneGraveYard, "Cards", 50);

        int xPosition = 0, yPosition = 0, x, y;
        for (Minion minion : Battle.getCurrentBattle().getPlayerTurn().getGraveYard().getCards())
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
                    setBattleField(primaryStage, map, true , battleMode);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setEndTurnButton(Group rootBattleField , BattleMode battleMode)
    {
        ImageView endTurnButton = new ImageView("battleField BackGround/button_end_turn_mine_glow.png");
        endTurnButton.relocate(1100, 620);
        endTurnButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                for (int number = 0; number < 5; number++)
                {
                    Battle.getCurrentBattle().getFirstPlayerHandPanes()[number].getChildren().clear();
                    Battle.getCurrentBattle().getSecondPlayerHandPanes()[number].getChildren().clear();
                }

                Battle.getCurrentBattle().endTurn();
                setMPIcons(rootBattleField);
                Battle.getCurrentBattle().setHandIcons();

                setNextCard(rootBattleField);

                for (int number = 0; number < 5; number++)
                {
                    rootBattleField.getChildren().add(Battle.getCurrentBattle().getCurrentPlayerHand()[number]);
                }
                battleFieldController = new BattleFieldController(rootBattleField, sceneBattleField , battleInfo , battleMode);
                battleFieldController.start();
            }
        });
        rootBattleField.getChildren().add(endTurnButton);
    }

    private void setHeroFirstPlace(Group rootBattleField)
    {
        Card.setCardsImageView();
        ImageView firstPlayerHero = Card.getCardImageView(Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0));
        BattleFieldController.setSpriteAnimation(firstPlayerHero);

        ImageView secondPlayerHero = Card.getCardImageView(Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0));
        BattleFieldController.setSpriteAnimation(secondPlayerHero);

        Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[2][0].getCellPane().getChildren().add(firstPlayerHero);
        Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[2][8].getCellPane().getChildren().add(secondPlayerHero);
        Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0).setRow(2);
        Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0).setColumn(0);
        Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0).setRow(2);
        Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0).setColumn(8);

        Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0).setDefaultAPHP();
        Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0).setDefaultAPHP();

        Battle.getCurrentBattle().getBattleField().addCardInTheBattleField(Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0));
        Battle.getCurrentBattle().getBattleField().addCardInTheBattleField(Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0));

        Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[2][0].setCard(Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0));
        Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[2][8].setCard(Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0));
    }

    private void setHeroIcons(Group rootBattleField)
    {
        Pane paneHero1 = new Pane();
        Pane paneHero2 = new Pane();
        paneHero1.relocate(100, -40);
        paneHero2.relocate(1100, -40);
        ImageView firstPlayerHeroIcon = Card.getCardIcon(Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0));
        ImageView secondPlayerHeroIcon = Card.getCardIcon(Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0));
        paneHero1.getChildren().addAll(firstPlayerHeroIcon);
        paneHero2.getChildren().addAll(secondPlayerHeroIcon);
        rootBattleField.getChildren().addAll(paneHero1, paneHero2);
    }

    private void setHandIcons(Group rootBattleField)
    {
        Pane[] firstPlayerHandPanes = new Pane[5];
        Pane[] secondPlayerHandPanes = new Pane[5];
        Card.setCardIcons();
        for (int number = 0; number < 5; number++)
        {
            ImageView imageView1;
            ImageView imageView2;

            Card card1 = Battle.getCurrentBattle().getFirstPlayer().getHand().getCards().get(number);//Card.getCardsIcon().get(Battle.getCurrentBattle().getFirstPlayer().getHand().getCards().get(number));
            Card card2 = Battle.getCurrentBattle().getSecondPlayer().getHand().getCards().get(number);//Card.getCardsIcon().get(Battle.getCurrentBattle().getSecondPlayer().getHand().getCards().get(number));

            firstPlayerHandPanes[number] = new Pane();
            secondPlayerHandPanes[number] = new Pane();

            imageView1 = Card.getCardIcon(card1);
            imageView2 = Card.getCardIcon(card2);

            firstPlayerHandPanes[number].getChildren().add(imageView1);
            secondPlayerHandPanes[number].getChildren().add(imageView2);
        }

        firstPlayerHandPanes[0].relocate(400, 620);
        firstPlayerHandPanes[1].relocate(525, 620);
        firstPlayerHandPanes[2].relocate(650, 620);
        firstPlayerHandPanes[3].relocate(775, 620);
        firstPlayerHandPanes[4].relocate(900, 620);
        secondPlayerHandPanes[0].relocate(400, 620);
        secondPlayerHandPanes[1].relocate(525, 620);
        secondPlayerHandPanes[2].relocate(650, 620);
        secondPlayerHandPanes[3].relocate(775, 620);
        secondPlayerHandPanes[4].relocate(900, 620);
        for (int number = 0; number < 5; number++)
        {
            rootBattleField.getChildren().add(firstPlayerHandPanes[number]);
        }
        Battle.getCurrentBattle().setFirstPlayerHandPanes(firstPlayerHandPanes);
        Battle.getCurrentBattle().setSecondPlayerHandPanes(secondPlayerHandPanes);
    }

    private void setGridPane(Group rootBattleField)
    {
        Pane[][] panes = new Pane[9][5];
        GridPane gridPane = new GridPane();

        gridPane.relocate(300, 200);

        for (int row = 0; row < 9; row++)
        {
            for (int column = 0; column < 5; column++)
            {
                Pane pane = new Pane();
                panes[row][column] = pane;
                gridPane.add(pane, row, column);
                Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[column][row].setCellPane(pane);
                ImageView imageView = new ImageView("battleField BackGround/normal.png");
                pane.getChildren().add(imageView);
            }
        }
        rootBattleField.getChildren().add(gridPane);
        Battle.getCurrentBattle().setBattleFieldPanes(panes);
        Battle.getCurrentBattle().setBattleFieldGridPane(gridPane);
    }

    public void getSecondPlayerInMultiPlayerMatch()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (patternSelectUser.matcher(input).matches())
        {
            setCommand(CommandType.SELECT_USER);
            getCommand().username = inputParts[2];
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
    }

    public void getMultiPlayerMatchMode()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (patternStartMultiPlayerGame.matcher(input).matches())
        {
            setCommand(CommandType.START_MULTI_PLAYER_GAME);
            getCommand().multiPlayerMatchMode = inputParts[3];
            if (inputParts.length > 4)
            {
                getCommand().numOfFlags = Integer.parseInt(inputParts[4]);
            }
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
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

    public void getGraveYardCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (patternShowInfoOfCardInGraveYard.matcher(input).matches())
        {
            setCommand(CommandType.SHOW_INFO);
            getCommand().cardOrItemIDInGraveYard = inputParts[2];
        }
        else if (input.equalsIgnoreCase("Show cards"))
        {
            setCommand(CommandType.SHOW_CARDS);
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

    public String getPassword()
    {
        return myScanner.nextLine();
    }

    public int getStoryMatchLevel()
    {
        try
        {
            showOutput.printOutput("Enter Level number");
            String input = myScanner.nextLine();
            getCommand().storyGameMode = Integer.parseInt(input);
        } catch (NumberFormatException e)
        {
            showOutput.printOutput("Try Again");
            getStoryMatchLevel();
        }
        return getCommand().storyGameMode;
    }

    public void getCustomGameCommands()
    {
        String line = myScanner.nextLine();
        String[] partedLine = line.split(" ");
        getCommand().deckNameForCustomGame = partedLine[2];
        getCommand().customGameMode = Integer.parseInt(partedLine[3]);
        if (partedLine.length == 5)    ///I have doubt about it
        {
            getCommand().customGameFlagNumber = Integer.parseInt(partedLine[4]);
        }
    }

}
