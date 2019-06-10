package View;

import Controller.AccountManager;
import Model.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import static javafx.scene.paint.Color.*;

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
    private Group rootShop = Main.getRootShop();
    private ScrollPane scrollPaneShop = Main.getScrollPaneShop();
    private Scene sceneShop = Main.getSceneShop();
    private Group rootCollection = Main.getRootCollection();
    private ScrollPane scrollPaneCollection = Main.getScrollPaneCollection();
    private Scene sceneCollection = Main.getSceneCollection();
    private Group rootBattleMenu = Main.getRootBattle();
    private Scene sceneBattleMenu = Main.getSceneBattle();
    private Group rootSinglePlayer = Main.getRootSinglePlayer();
    private Scene sceneSinglePlayer = Main.getSceneSinglePlayer();
    private Group rootMultiPlayer = Main.getRootMultiPlayer();
    private Scene sceneMultiPlayer = Main.getSceneMultiPlayer();
    private Group rootStoryMode = Main.getRootMultiPlayer();
    private Scene sceneStoryMode = Main.getSceneMultiPlayer();

    public void signUpMenu(Stage primaryStage)
    {
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
                    accountManager.createAccount(userName, password);
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
        setBackGroundImage(rootMainMenu, "file:Duelyst Menu.jpg");

        Text duelyst = new Text("Duelyst");
        duelyst.setTextOrigin(VPos.TOP);
        duelyst.setFont(Font.font(null, FontWeight.BOLD, 60));
        duelyst.layoutXProperty().bind(sceneMainMenu.widthProperty().subtract(duelyst.prefWidth(-1)).divide(2));
        rootMainMenu.getChildren().add(duelyst);

        setMainMenuText(primaryStage, "Battle", 100);
        setMainMenuText(primaryStage, "Shop", 160);
        setMainMenuText(primaryStage, "Collection", 220);
        setMainMenuText(primaryStage, "LeaderBoard", 280);
        setMainMenuText(primaryStage, "Save", 340);
        setMainMenuText(primaryStage, "Logout", 400);
        setMainMenuText(primaryStage, "Exit", 460);

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
        text.setFont(Font.font(null, FontWeight.SEMI_BOLD, 35));
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
                }
                catch (Exception e)
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
                        if (menuName.equalsIgnoreCase("CollectionMenu"))
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
        setBackGroundImage(rootShop, "file:Duelyst Menu Blurred.jpg");

        scrollPaneShop.setContent(rootShop);
        scrollPaneShop.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneShop.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int xPosition = 0, yPosition = 0, x = 0, y = 0;
        setShopMenuText("Heroes", 50);
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
            xPosition ++;
            yPosition ++;
            StackPane stackPane = showNonSpellCards(rootShop, x, y, hero, hero.getCardName());
            setShopStackPanesOnMouseClicked(stackPane, hero.getCardName(), hero.getPrice());
        }

        if (xPosition == 0)
        {
            y += BLANK_BETWEEN_CARDS;
            yPosition += 4;
        }
        xPosition = 0;
        if (yPosition % 4 !=0)
        {
            yPosition = yPosition + 4 - yPosition % 4;
        }
        setShopMenuText("Minions", y + CARDS_RECTANGLE_HEIGHT + 50);
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
            StackPane stackPane = showNonSpellCards(rootShop, x, y, minion, minion.getCardName());
            setShopStackPanesOnMouseClicked(stackPane, minion.getCardName(), minion.getPrice());
            xPosition ++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 4;
        }
        xPosition = 0;
        if (yPosition % 4 !=0)
        {
            yPosition = yPosition + 4 - yPosition % 4;
        }
        setShopMenuText("Spells", y + CARDS_RECTANGLE_HEIGHT + 50);
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
            StackPane stackPane = showCardAndItemImageAndFeatures(rootShop, x, y, spell.getCardName(), spell.getPrice());
            setShopStackPanesOnMouseClicked(stackPane, spell.getCardName(), spell.getPrice());
            xPosition ++;
            yPosition ++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 4;
        }
        else
        {
            xPosition = 0;
            if (yPosition % 4 !=0)
            {
                yPosition = yPosition + 4 - yPosition % 4;
            }
        }
        setShopMenuText("Items", y + CARDS_RECTANGLE_HEIGHT + 50);
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
            StackPane stackPane = showCardAndItemImageAndFeatures(rootShop, x, y, item.getItemName(), item.getPrice());
            setShopStackPanesOnMouseClicked(stackPane, item.getItemName(), item.getPrice());
            xPosition ++;
            yPosition ++;
        }

        backButton(primaryStage, rootShop, 20, 15);
        searchField(primaryStage, sceneShop, rootShop, "ShopMenu");

        primaryStage.setScene(sceneShop);
    }

    private void setShopMenuText(String str, int y)
    {
        Text text = new Text(str);
        text.setLayoutX((sceneShop.getWidth() - text.getLayoutBounds().getWidth()) / 2 - 40);
        text.setLayoutY(y);
        text.setFont(Font.font(null, FontWeight.SEMI_BOLD, 40));
        rootShop.getChildren().addAll(text);
    }

    private StackPane showNonSpellCards(Group root, int x, int y, NonSpellCard nonSpellCard, String cardNameOrID)
    {
        int AP = nonSpellCard.getDefaultAP();
        int HP = nonSpellCard.getDefaultHP();
        int price = nonSpellCard.getPrice();

        StackPane stackPane = showCardAndItemImageAndFeatures(root, x, y, cardNameOrID, price);

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

    private StackPane showCardAndItemImageAndFeatures(Group root, int x, int y, String nameOrID, int price)
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

        Text textPrice = new Text(Integer.toString(price));
        textPrice.setFont(Font.font(15));
        textPrice.setFill(GREEN);
        textPrice.setLayoutX(x + (rectangle.getWidth() - textPrice.getLayoutBounds().getWidth()) / 2);
        textPrice.setLayoutY(y + 240);

        root.getChildren().addAll(stackPane, textCardName, textPrice);

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

    public void collectionMenu(Stage primaryStage, boolean isSearchedElement, String searchedElement)
    {
        setBackGroundImage(rootCollection, "file:Duelyst Menu Blurred.jpg");

        scrollPaneCollection.setContent(rootCollection);
        scrollPaneCollection.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneCollection.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Button importButton = new Button();
        importButton.relocate(600,30);
        importButton.setText("import Deck");
        importButton.setOnMouseClicked(event ->
                importingDeck()
        );
        Button exportButton = new Button();
        exportButton.relocate(700,30);
        exportButton.setText("export Deck");
        exportButton.setOnMouseClicked(event ->
               exportingDeck()
        );
        rootCollection.getChildren().add(importButton);
        rootCollection.getChildren().addAll(exportButton);
        setCollectionMenuText("Heroes", 50, false);
        int xPosition = 0, yPosition = 0, x = 0, y = 0;
        for (Card card : Account.loggedInAccount.getCollection().getCards())
        {
            if (card instanceof Hero)
            {
                if (isSearchedElement)
                {
                    if (!card.getCardName().contains(searchedElement))
                    {
                        continue;
                    }
                }
                x = ROW_BLANK + (xPosition % 3) * (200 + BLANK_BETWEEN_CARDS);
                y = COLUMN_BLANK + yPosition / 3 * (250 + BLANK_BETWEEN_CARDS);
                StackPane stackPane = showNonSpellCards(rootCollection, x, y, (Hero) card, card.getCardID());
                xPosition ++;
                yPosition ++;
            }
        }

        if (xPosition == 0)
        {
            y += BLANK_BETWEEN_CARDS;
            yPosition += 3;
        }
        if (yPosition % 3 !=0)
        {
            yPosition = yPosition + 3 - yPosition % 3;
        }
        xPosition = 0;
        setCollectionMenuText("Minions", y + 250 + 50, false);
        if (yPosition % 3 !=0)
        {
            yPosition = yPosition + 3 - yPosition % 3;
        }
        for (Card card : Account.loggedInAccount.getCollection().getCards())
        {
            if (card instanceof Minion)
            {
                if (isSearchedElement)
                {
                    if (!card.getCardName().contains(searchedElement))
                    {
                        continue;
                    }
                }
                y = 2 * COLUMN_BLANK - BLANK_BETWEEN_CARDS + yPosition / 3 * (250 + BLANK_BETWEEN_CARDS);
                x = ROW_BLANK + (xPosition % 3) * (200 + BLANK_BETWEEN_CARDS);
                StackPane stackPane = showNonSpellCards(rootCollection, x, y, (Minion) card, card.getCardID());
                xPosition ++;
                yPosition ++;
            }
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 3;
        }
        xPosition = 0;
        if (yPosition % 3 !=0)
        {
            yPosition = yPosition + 3 - yPosition % 3;
        }
        setCollectionMenuText("Spells", y + 250 + 50, false);
        if (yPosition % 3 !=0)
        {
            yPosition = yPosition + 3 - yPosition % 3;
        }
        for (Card card : Account.loggedInAccount.getCollection().getCards())
        {
            if (card instanceof Spell)
            {
                if (isSearchedElement)
                {
                    if (!card.getCardName().contains(searchedElement))
                    {
                        continue;
                    }
                }
                x = ROW_BLANK + (xPosition % 3) * (200 + BLANK_BETWEEN_CARDS);
                y = 3 * COLUMN_BLANK - 2 * BLANK_BETWEEN_CARDS + yPosition / 3 * (250 + BLANK_BETWEEN_CARDS);
                StackPane stackPane = showCardAndItemImageAndFeatures(rootCollection, x, y, card.getCardID(), card.getPrice());
                xPosition ++;
                yPosition ++;
            }
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 3;
        }
        else
        {
            xPosition = 0;
            if (yPosition % 3 !=0)
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
            StackPane stackPane = showCardAndItemImageAndFeatures(rootCollection, x, y, item.getItemID(), item.getPrice());
            xPosition ++;
            yPosition ++;
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
            yPosition ++;
        }

        backButton(primaryStage, rootCollection, 20, 15);
        searchField(primaryStage, sceneCollection, rootCollection, "CollectionMenu");

        primaryStage.setScene(sceneCollection);
        primaryStage.centerOnScreen();
    }

    private void importingDeck()
    {

    }
    private void exportingDeck()
    {

    }
    private StackPane showDecksImageAndFeatures(Group root, int x, int y, Deck deck)
    {
        Image image = new Image("file:Deck.jpg");
        ImageView imageView = new ImageView(image);

        Rectangle rectangle = new Rectangle(CARDS_RECTANGLE_WIDTH, CARDS_RECTANGLE_HEIGHT, LIGHTBLUE);

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
            x = (sceneCollection.getWidth() * 1/4 - text.getLayoutBounds().getWidth()) / 2 - 40 + sceneCollection.getWidth() * 3/4;
        }
        else
        {
            x = (sceneCollection.getWidth() * 3/4 - text.getLayoutBounds().getWidth()) / 2 - 40;
        }
        text.setLayoutX(x);
        text.setLayoutY(y);
        text.setFont(Font.font(null, FontWeight.SEMI_BOLD, 40));
        rootCollection.getChildren().addAll(text);
    }

    private void battleMenu(Stage primaryStage)
    {
        setBackGroundImage(rootBattleMenu, "file:duelystBattle.jpg");

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
        setBackGroundImage(rootSinglePlayer, "file:SinglePlayer.jpg");
        setSinglePlayerMenu("Story", primaryStage, 100);
        setSinglePlayerMenu("Custom Game", primaryStage, 250);
        Button backButton = backButton(primaryStage, rootSinglePlayer, 50, 450);
        backButton.setOnMouseClicked(event -> {
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
    private void storyModeMenu(Stage primaryStage) {
        setBackGroundImage(rootStoryMode , "file:StoryModeBackground.jpg");
        setStoryModeMenu("Mode1" , primaryStage , 100);
        setStoryModeMenu("Mode2" , primaryStage , 200);
        setStoryModeMenu("Mode3" , primaryStage , 300);
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
    title.setOnMouseExited(event -> title.setFill(BLACK));    title.setOnMouseClicked(event -> {
        switch (string)
        {
            case "Mode1":
               // Battle.getCurrentBattle().setBattleMode(BattleMode.KILLING_ENEMY_HERO);
                try {
                    setBattleField(primaryStage ,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Mode2":
               // Battle.getCurrentBattle().setBattleMode(BattleMode.KEEP_FLAG_FOR_6_TURNS);
                try {
                    setBattleField(primaryStage , 2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Mode3":
                //Battle.getCurrentBattle().setBattleMode(BattleMode.GATHERING_FLAGS);
                try {
                    setBattleField(primaryStage , 3);
                } catch (IOException e) {
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
        setBackGroundImage(rootMultiPlayer,"file:MultiPlayerrr.jpg");
        setMultiPlayerMenu("Choose  One Player", primaryStage, 75);
        Button backButton = new Button("Back");
        backButton.relocate(50,490);
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
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.setScene(sceneMultiPlayer);
        rootMultiPlayer.getChildren().add(backButton);
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

        public void setBattleField(Stage primaryStage, int mapNumber) throws IOException
    {
        Parent root;
        Scene battleScene = null;
        switch (mapNumber)
        {
            case 1:
                root = FXMLLoader.load(getClass().getResource("BattleFieldFXML1.fxml"));//FXMLLoader.load(getClass().getResource("BattleFieldFXML1.fxml"));
                battleScene = new Scene(root);
                break;
            case 2:
                root = FXMLLoader.load(Request.class.getResource("BattleFieldFXML2.fxml"));//FXMLLoader.load(getClass().getResource("BattleFieldFXML1.fxml"));
                battleScene = new Scene(root);
                break;
            case 3:
                root = FXMLLoader.load(Request.class.getResource("BattleFieldFXML3.fxml"));//FXMLLoader.load(getClass().getResource("BattleFieldFXML1.fxml"));
                battleScene = new Scene(root);
                break;
            case 4:
                root = FXMLLoader.load(Request.class.getResource("BattleFieldFXML4.fxml"));//FXMLLoader.load(getClass().getResource("BattleFieldFXML1.fxml"));
                battleScene = new Scene(root);
                break;
            case 5:
                root = FXMLLoader.load(Request.class.getResource("BattleFieldFXML5.fxml"));//FXMLLoader.load(getClass().getResource("BattleFieldFXML1.fxml"));
                battleScene = new Scene(root);
                break;
        }

        primaryStage.centerOnScreen();
        primaryStage.setScene(battleScene);
        primaryStage.centerOnScreen();

        primaryStage.setFullScreen(true);

        primaryStage.show();

    }

    public void getCollectionCommands()
    {
        String input = myScanner.nextLine();
        String[] inputParts = input.split(" ");
        if (input.equals("show"))
        {
            setCommand(CommandType.SHOW);
        }
        else if (patternSearch.matcher(input).matches())
        {
            setCommand(CommandType.SEARCH);
            getCommand().cardOrItemName = inputParts[1];
        }
        else if (input.equals("save"))
        {
            setCommand(CommandType.SAVE);
        }
        else if (patternCreateDeck.matcher(input).matches())
        {
            setCommand(CommandType.CREATE_DECK);
            getCommand().deckName = inputParts[2];
        }
        else if (patternDeleteDeck.matcher(input).matches())
        {
            setCommand(CommandType.DELETE_DECK);
            getCommand().deckName = inputParts[2];
        }
        else if (patternAddCardToDeck.matcher(input).matches())
        {
            setCommand(CommandType.ADD_TO_DECK);
            getCommand().deckName = inputParts[4];
            getCommand().cardOrItemID = inputParts[1];
        }
        else if (patternRemoveCardFromDeck.matcher(input).matches())
        {
            setCommand(CommandType.REMOVE_FROM_DECK);
            getCommand().deckName = inputParts[4];
            getCommand().cardOrItemID = inputParts[1];
        }
        else if (patternValidateDeck.matcher(input).matches())
        {
            setCommand(CommandType.VALIDATE_DECK);
            getCommand().deckName = inputParts[2];
        }
        else if (patternSelectMainDeck.matcher(input).matches())
        {
            setCommand(CommandType.SET_MAIN_DECK);
            getCommand().deckName = inputParts[2];
        }
        else if (patternShowDeck.matcher(input).matches())
        {
            setCommand(CommandType.SHOW_DECK);
            getCommand().deckName = inputParts[2];
        }
        else if (input.equals("show all decks"))
        {
            setCommand(CommandType.SHOW_ALL_DECKS);
        }
        else if (input.equals("help"))
        {
            setCommand(CommandType.HELP);
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

    public void getBattleMenuCommands()
    {
        String input = myScanner.nextLine();
        if (input.equalsIgnoreCase("Single Player"))
        {
            setCommand(CommandType.SINGLE_PLAYER);
        }
        else if (input.equalsIgnoreCase("Multi Player"))
        {
            setCommand(CommandType.MULTI_PLAYER);
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

    public void getSinglePlayerMatchMode()
    {
        String input = myScanner.nextLine();
        if (input.equalsIgnoreCase("Story"))
        {
            setCommand(CommandType.STORY);
        }
        else if (input.equalsIgnoreCase("Custom Game"))
        {
            setCommand(CommandType.CUSTOM_GAME);
        }
        else
        {
            getShowMenuAndExitCommand(input);
        }
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
