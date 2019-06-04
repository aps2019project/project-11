package View;

import Model.Card;
import Model.CommandType;
import Model.Shop;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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

    private ShowOutput showOutput = new ShowOutput();

    public CommandType getCommand()
    {
        return command;
    }

    private static void setCommand(CommandType command)
    {
        Request.command = command;
    }

    private static final int ROW_BLANK = 20;
    private static final int Column_BLANK = 20;
    private static final int BLANK_BETWEEN_CARDS = 50;


    private static CommandType command;
    public static final Object requestLock = new Object();
    private Group rootMainMenu = new Group();
    private Scene sceneMainMenu = new Scene(rootMainMenu, 1000, 562);
    private Group rootShop = new Group();
    private ScrollPane scrollPane = new ScrollPane();
    private Scene sceneShop;
    private Group rootCollection = new Group();
    private Scene sceneCollection = new Scene(rootCollection,1000,562);
    private Group rootBattle = new Group();
    private Scene sceneBattle = new Scene(rootBattle,1000,562);

    public void mainMenu(Stage primaryStage)
    {
        setBackGroundImage(rootMainMenu, "file:Duelyst Menu.jpg");

        Text duelyst = new Text("Duelyst");
        duelyst.setTextOrigin(VPos.TOP);
        duelyst.setFont(Font.font(null, FontWeight.BOLD, 60));
        duelyst.layoutXProperty().bind(sceneMainMenu.widthProperty().subtract(duelyst.prefWidth(-1)).divide(2));
        rootMainMenu.getChildren().add(duelyst);

        setMainMenuText(primaryStage, "Battle", 100);
        setMainMenuText(primaryStage, "Shop", 170);
        setMainMenuText(primaryStage, "Collection", 250);
        setMainMenuText(primaryStage, "Save", 330);
        setMainMenuText(primaryStage, "Logout", 410);
        setMainMenuText(primaryStage, "Exit", 490);

        primaryStage.setScene(sceneMainMenu);
    }

    private void setBackGroundImage(Group root, String ur2l)
    {
        Image backGroundImage = new Image(ur2l);
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
                        command = CommandType.ENTER_SHOP;
                        shopMenu(primaryStage);
                        break;
                    case "Collection":
                        command = CommandType.ENTER_COLLECTION;
                        break;
                    case "Battle":
                        command = CommandType.ENTER_BATTLE;
                        battleMenu(primaryStage);
                        break;
                    case "Save":
                        command = CommandType.SAVE;
                        break;
                    case "Logout":
                        command = CommandType.LOGOUT;
                        break;
                    case "Exit":
                        command = CommandType.EXIT;
                        break;
                }
                synchronized (requestLock)
                {
                    requestLock.notify();
                }
            }
        });
        rootMainMenu.getChildren().add(text);
    }

    public void shopMenu(Stage primaryStage)
    {
        setBackGroundImage(rootShop, "file:Duelyst Menu Blurred.jpg");

        scrollPane.setContent(rootShop);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        sceneShop = new Scene(scrollPane, 1000, 562);

        int counter = 0;
        ArrayList<Card> cards = Shop.getInstance().getCards();
        for (Card card : cards)
        {
            int x = ROW_BLANK + (counter%4) * (200 + BLANK_BETWEEN_CARDS);
            int y = Column_BLANK + counter/4 * (250 + BLANK_BETWEEN_CARDS);
            makeCards(rootShop, x, y);
            counter ++;
        }

        /*setShopMenu("Show Collection",primaryStage,100);
        setShopMenu("Search",primaryStage,170);
        setShopMenu("Search Collection",primaryStage,250);
        setShopMenu("Buy",primaryStage,330);
        setShopMenu("Sell",primaryStage,500);*/

        primaryStage.setScene(sceneShop);
    }

    private void makeCards(Group root,int x, int y)
    {
        Image image = new Image("file:download.jpg");
        ImageView imageView = new ImageView(image);

        Rectangle rectangle = new Rectangle(200, 250);
        rectangle.setFill(Color.DARKGRAY);

        StackPane stackPane = new StackPane(rectangle, imageView);
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.relocate(x, y);

        Text AP = new Text("5");
        AP.setFont(Font.font(15));
        AP.setFill(Color.RED);
        AP.relocate(x + 150, y + 200);

        Text HP = new Text("3");
        HP.setFont(Font.font(15));
        HP.setFill(Color.YELLOW);
        HP.relocate(x + 50, y + 200);

        root.getChildren().addAll(stackPane, AP, HP);
    }

    /*public void setShopMenu(String titlesInShop , Stage stage, int x)
    {
        Text text = new Text(titlesInShop);
        text.setTextOrigin(VPos.TOP);
        text.setFont(Font.font(null, FontWeight.BLACK, 40));
        text.layoutXProperty().bind(sceneShop.widthProperty().subtract(text.prefWidth(-2)).divide(2));
        text.setY(x);
        text.setFill(Color.BLACK);
        text.setOnMouseEntered(event -> text.setFill(Color.RED));
        text.setOnMouseExited(event -> text.setFill(Color.BLACK));
        text.setOnMouseClicked(event -> {
             switch (titlesInShop)
              {
                  case "Show Collection":
                      command = CommandType.SHOW_COLLECTION;
                      break;
                  case "Search":
                      command = CommandType.SEARCH;
                      break;
                  case "Search Collection" :
                      command = CommandType.SEARCH_COLLECTION;
                      break;
                  case  "Buy" :
                      command = CommandType.BUY;
                      break;
                  case "Sell" :
                      command = CommandType.SELL;
                      break;
                   synchronized (requestLock)
                    {
                        requestLock.notify();
                    }
              }
           });
        rootShop.getChildren().add(text);
    }*/


    public void battleMenu(Stage stage)
    {
        setBattleMenu("Single Player",stage,170);
        setBattleMenu("Multi Player",stage,300);
        stage.setScene(sceneBattle);
    }

    public void setBattleMenu(String titleOfBattleMenu, Stage stage , int location)
    {
        Text title = new Text("Choose One Of The Below");
        title.setTextOrigin(VPos.TOP);
        title.setFont(Font.font(null, FontPosture.ITALIC,45));
        title.layoutXProperty().bind(sceneBattle.widthProperty().subtract(title.prefWidth(-2)).divide(2));
        title.setY(50);

        Text text = new Text(titleOfBattleMenu);
        text.setTextOrigin(VPos.TOP);
        text.setFont(Font.font(null, FontWeight.BLACK, 40));
        text.layoutXProperty().bind(sceneBattle.widthProperty().subtract(text.prefWidth(-2)).divide(2));
        text.setY(location);
        text.setFill(Color.BLACK);
        text.setOnMouseEntered(event -> text.setFill(Color.RED));
        text.setOnMouseExited(event -> text.setFill(Color.BLACK));
        text.setOnMouseClicked(event -> {
            switch (titleOfBattleMenu)
            {
                case "Single Player":
                    command = CommandType.SINGLE_PLAYER;
                    break;
                case  "Multi Player" :
                    command = CommandType.MULTI_PLAYER;
                        break;

                /*synchronized (requestLock)
                {
                    requestLock.notify();
                }*/
            }
        });
        rootBattle.getChildren().add(text);
        rootBattle.getChildren().add(title);
    }
    public void collectionMenu(Stage primaryStage)
    {
        primaryStage.setScene(sceneCollection);
    }

    public void getMainMenuCommands()
    {
        String input = myScanner.nextLine();
        if (input.equalsIgnoreCase("Enter Shop"))
        {
            setCommand(CommandType.ENTER_SHOP);
        }
        else if (input.equalsIgnoreCase("Enter Collection"))
        {
            setCommand(CommandType.ENTER_COLLECTION);
        }
        else if (input.equalsIgnoreCase("Enter Battle"))
        {
            setCommand(CommandType.ENTER_BATTLE);
        }
        else if (input.equalsIgnoreCase("save"))
        {
            setCommand(CommandType.SAVE);
        }
        else if (input.equalsIgnoreCase("logout"))
        {
            setCommand(CommandType.LOGOUT);
        }
        else if (input.equalsIgnoreCase("Help"))
        {
            setCommand(CommandType.HELP);
        }
        else if (input.equalsIgnoreCase("Exit"))
        {
            setCommand(CommandType.EXIT);
        }
        else
        {
            showOutput.printOutput("invalid command");
            setCommand(null);
        }
    }

    public void getAccountCommands()
    {
        String input = myScanner.nextLine();
        String[] separatedInput = input.split(" ");
        if (input.equalsIgnoreCase("Exit"))
        {
            setCommand(CommandType.EXIT);
        }
        else if (patternCreateAccount.matcher(input).matches())
        {
            setCommand(CommandType.CREATE_ACCOUNT);
            getCommand().username = separatedInput[2];
        }
        else if (patternAccountLogin.matcher(input).matches())
        {
            setCommand(CommandType.LOGIN);
            getCommand().username = separatedInput[1];
        }
        else if (input.equalsIgnoreCase("show leaderBoard"))
        {
            setCommand(CommandType.SHOW_LEADER_BOARD);
        }
        else if (input.equalsIgnoreCase("help"))
        {
            setCommand(CommandType.HELP);
        }
        else
        {
            showOutput.printOutput("invalid command");
            setCommand(null);
        }
    }

    public void getShopCommands()
    {
        String input = myScanner.nextLine();
        String[] partedInput = input.split("\\s");
        if (input.equals("exit"))
        {
            setCommand(CommandType.EXIT);
        }
        else if (input.equals("show collection"))
        {
            setCommand(CommandType.SHOW_COLLECTION);
        }
        else if (patternSearch.matcher(input).matches())
        {
            setCommand(CommandType.SEARCH);
            getCommand().cardOrItemName = partedInput[1];
        }
        else if (patternShopSearchCollection.matcher(input).matches())
        {
            setCommand(CommandType.SEARCH_COLLECTION);
            getCommand().cardOrItemName = partedInput[2];
        }
        else if (patternShopBuy.matcher(input).matches())
        {
            setCommand(CommandType.BUY);
            getCommand().cardOrItemName = partedInput[1];
        }
        else if (patternShopSell.matcher(input).matches())
        {
            setCommand(CommandType.SELL);
            getCommand().cardOrItemID = partedInput[1];
        }
        else if (input.equals("show"))
        {
            setCommand(CommandType.SHOW);
        }
        else if (input.equals("help"))
        {
            setCommand(CommandType.HELP);
        }
        else
        {
            showOutput.printOutput("invalid command");
            setCommand(null);
        }
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
        }
        catch (NumberFormatException e)
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
