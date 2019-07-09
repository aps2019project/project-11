package Network;

import Model.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;

public class ServerGraphic extends Application implements Runnable{
    private Stage primaryStage;
    private Group rootServerMenu;
    private Scene sceneServerMenu;

    private static final int ROW_BLANK = 20;
    private static final int COLUMN_BLANK = 80;
    private static final int BLANK_BETWEEN_CARDS = 50;
    private static final int CARDS_RECTANGLE_WIDTH = 200;
    private static final int CARDS_RECTANGLE_HEIGHT = 250;


    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        makeMenu();
    }

    private void makeMenu() {
        Group rootServerMenu = new Group();
        setRootServerMenu(rootServerMenu);
        setBackGroundImage(rootServerMenu);
        Button showOnlinePlayers = new Button("Show Online Players");
        showOnlinePlayers.relocate(220 , 250);
        showOnlinePlayers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showOnlinePlayers();
            }
        });

        Button showCard = new Button("Show Cards");
        showCard.relocate(220 , 280);
        showCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showCards();
            }
        });
        rootServerMenu.getChildren().addAll(showCard , showOnlinePlayers);
        Scene sceneServerMenu = new Scene(rootServerMenu , 500 , 500);
        setSceneServerMenu(sceneServerMenu);
        primaryStage.setScene(sceneServerMenu);
        primaryStage.show();
    }

    private void showCards() {
        Group rootCardsInServer = new Group();
        setBackGroundImage(rootCardsInServer);
        ScrollPane scrollPaneShop = new ScrollPane();

        scrollPaneShop.setContent(rootCardsInServer);
        scrollPaneShop.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneShop.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int xPosition = 0, yPosition = 0, x = 0, y = 0;
        setShopText(rootCardsInServer, "Heroes", 50);
        for (Hero hero :Server.getHeroes())
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = COLUMN_BLANK + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            xPosition++;
            yPosition++;
            showNonSpellCards(rootCardsInServer, x, y, hero);
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
        setShopText(rootCardsInServer,"Minions", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Minion minion : Server.getMinions())
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 2 * COLUMN_BLANK - BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            showNonSpellCards(rootCardsInServer, x, y, minion);
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
        setShopText(rootCardsInServer,"Spells", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Spell spell : Server.getSpells())
        {

            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 3 * COLUMN_BLANK - 2 * BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
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
        setShopText(rootCardsInServer,"Items", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Item item : Server.getItems())
        {
            x = ROW_BLANK + (xPosition % 4) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 4 * COLUMN_BLANK - 3 * BLANK_BETWEEN_CARDS + yPosition / 4 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            xPosition++;
            yPosition++;
        }

        backButton(rootCardsInServer, 20, 15);

        Scene sceneShop = new Scene(rootCardsInServer , 500 , 500);
        primaryStage.setScene(sceneShop);
        primaryStage.show();
    }

    private void showNonSpellCards(Group root, int x, int y, NonSpellCard nonSpellCard)
    {
        int AP = nonSpellCard.getDefaultAP();
        int HP = nonSpellCard.getDefaultHP();

        showCardAndItemImageAndFeatures(root, x, y, nonSpellCard.getCardName());

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

    }

    private void showCardAndItemImageAndFeatures(Group root, int x, int y, String nameOrID)
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

        root.getChildren().addAll(stackPane, textCardName);

    }

    private void setShopText(Group root, String str, int y)
    {
        Text text = new Text(str);
        text.setLayoutX((500 - text.getLayoutBounds().getWidth()) / 2 - 40);
        text.setLayoutY(y);
        text.setFont(Font.font(null, FontWeight.SEMI_BOLD, 40));
        root.getChildren().addAll(text);
    }

    private void showOnlinePlayers() {
        Group rootShowOnlinePlayers = new Group();
        setBackGroundImage(rootShowOnlinePlayers);
        int counter = 0 ;
        Text onlinePlayerText = new Text("Online Players");
        onlinePlayerText.setFont(Font.font("verdana" , 25));
        onlinePlayerText.relocate(170 , 5);
        rootShowOnlinePlayers.getChildren().add(onlinePlayerText);
        for(Account account :Server.getAccounts()){
            if(account.getAuthToken() != null){
                Text text = new Text(account.getAccountName());
                text.relocate(5 , 50 + counter * 10);
                rootShowOnlinePlayers.getChildren().add(text);
                counter++;
            }
        }

        backButton(rootShowOnlinePlayers , 400 , 400);

        Scene sceneShowOnlinePlayers = new Scene(rootShowOnlinePlayers , 500 , 500);
        primaryStage.setScene(sceneShowOnlinePlayers);
        primaryStage.show();
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setPrimaryStage(Stage primaryMessage) {
        this.primaryStage = primaryMessage;
    }

    private void setBackGroundImage(Group root)
    {
        Image backGroundImage = new Image("file:BackGround Images/simple-background-blue-simple-minimalism-wallpaper.jpg");
        ImageView backGroundImageView = new ImageView(backGroundImage);
        root.getChildren().add(backGroundImageView);
    }

    private Button backButton(Group root, int x, int y)
    {
        Button backButton = new Button("Back");
        backButton.setFont(Font.font(25));
        backButton.relocate(x, y);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                primaryStage.setScene(getSceneServerMenu());
                makeMenu();
            }
        });
        root.getChildren().add(backButton);
        return backButton;
    }

    @Override
    public void run() {
        launch();
    }


    public Group getRootServerMenu() {
        return rootServerMenu;
    }

    public void setRootServerMenu(Group rootServerMenu) {
        this.rootServerMenu = rootServerMenu;
    }

    public Scene getSceneServerMenu() {
        return sceneServerMenu;
    }

    public void setSceneServerMenu(Scene sceneServerMenu) {
        this.sceneServerMenu = sceneServerMenu;
    }
}
