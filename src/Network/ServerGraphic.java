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

public class ServerGraphic extends Application implements Runnable
{
    private Stage primaryStage;
    private Group rootServerMenu;
    private Scene sceneServerMenu;

    private static final int ROW_BLANK = 20;
    private static final int COLUMN_BLANK = 80;
    private static final int BLANK_BETWEEN_CARDS = 50;
    private static final int CARDS_RECTANGLE_WIDTH = 200;
    private static final int CARDS_RECTANGLE_HEIGHT = 250;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        setPrimaryStage(primaryStage);
        makeMenu();
    }

    private void makeMenu()
    {
        Group rootServerMenu = new Group();
        setRootServerMenu(rootServerMenu);
        setBackGroundImage(rootServerMenu);
        Button showOnlinePlayers = new Button("Show Online Players");
        showOnlinePlayers.relocate(220, 250);
        showOnlinePlayers.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                showOnlinePlayers();
            }
        });

        Button showCard = new Button("Show Cards");
        showCard.relocate(220, 280);
        showCard.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                showCards();
            }
        });
        rootServerMenu.getChildren().addAll(showCard, showOnlinePlayers);
        Scene sceneServerMenu = new Scene(rootServerMenu, 500, 500);
        setSceneServerMenu(sceneServerMenu);
        primaryStage.setScene(sceneServerMenu);
        primaryStage.show();
    }

    private void showCards()
    {
        Group rootCardsInServer = new Group();
        setBackGroundImage(rootCardsInServer);
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(rootCardsInServer);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        int xPosition = 0, yPosition = 0, x = 0, y = 0;
        setText(rootCardsInServer, "Heroes", 50);
        for (Hero hero : Server.getHeroes())
        {
            x = ROW_BLANK + (xPosition % 2) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = COLUMN_BLANK + yPosition / 2 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            xPosition++;
            yPosition++;
            showCardAndItemImageAndFeatures(rootCardsInServer, x, y, hero.getCardName());
        }

        if (xPosition == 0)
        {
            y += BLANK_BETWEEN_CARDS;
            yPosition += 2;
        }
        xPosition = 0;
        if (yPosition % 2 != 0)
        {
            yPosition = yPosition + 2 - yPosition % 2;
        }
        setText(rootCardsInServer, "Minions", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Minion minion : Server.getMinions())
        {
            x = ROW_BLANK + (xPosition % 2) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 2 * COLUMN_BLANK - BLANK_BETWEEN_CARDS + yPosition / 2 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            showCardAndItemImageAndFeatures(rootCardsInServer, x, y, minion.getCardName());
            xPosition++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 2;
        }
        xPosition = 0;
        if (yPosition % 2 != 0)
        {
            yPosition = yPosition + 2 - yPosition % 2;
        }
        setText(rootCardsInServer, "Spells", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Spell spell : Server.getSpells())
        {

            x = ROW_BLANK + (xPosition % 2) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 3 * COLUMN_BLANK - 2 * BLANK_BETWEEN_CARDS + yPosition / 2 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            showCardAndItemImageAndFeatures(rootCardsInServer, x, y, spell.getCardName());
            xPosition++;
            yPosition++;
        }

        if (xPosition == 0)
        {
            y += CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS;
            yPosition += 2;
        }
        else
        {
            xPosition = 0;
            if (yPosition % 2 != 0)
            {
                yPosition = yPosition + 2 - yPosition % 2;
            }
        }
        setText(rootCardsInServer, "Items", y + CARDS_RECTANGLE_HEIGHT + 50);
        for (Item item : Server.getItems())
        {
            x = ROW_BLANK + (xPosition % 2) * (CARDS_RECTANGLE_WIDTH + BLANK_BETWEEN_CARDS);
            y = 4 * COLUMN_BLANK - 3 * BLANK_BETWEEN_CARDS + yPosition / 2 * (CARDS_RECTANGLE_HEIGHT + BLANK_BETWEEN_CARDS);
            showCardAndItemImageAndFeatures(rootCardsInServer, x, y, item.getItemName());
            xPosition++;
            yPosition++;
        }

        backButton(rootCardsInServer, 20, 15);

        Scene sceneShop = new Scene(scrollPane, 500, 500);
        primaryStage.setScene(sceneShop);
        primaryStage.show();
    }

    private void showCardAndItemImageAndFeatures(Group root, int x, int y, String name)
    {
        Item item = Item.findItem(name);
        Card card = Card.findCard(name);

        Image image = new Image("file:Cards.jpg");
        ImageView imageView = new ImageView(image);

        Rectangle rectangle = new Rectangle(CARDS_RECTANGLE_WIDTH, CARDS_RECTANGLE_HEIGHT);
        rectangle.setFill(GRAY);

        StackPane stackPane = new StackPane(rectangle, imageView);
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.relocate(x, y);

        Text textCardName = new Text(name);
        textCardName.setFont(Font.font(15));
        textCardName.setLayoutX(x + (rectangle.getWidth() - textCardName.getLayoutBounds().getWidth()) / 2);
        textCardName.setLayoutY(y + 160);

        Text textSellCapacity = new Text();
        if (card != null)
        {
            textSellCapacity.setText(Integer.toString(card.getCapacityOfSell()));
        }
        else if (item != null)
        {
            textSellCapacity.setText(Integer.toString(item.getCapacityOfSell()));
        }
        textSellCapacity.setFont(Font.font(15));
        textSellCapacity.setFill(RED);
        textSellCapacity.setLayoutX(x + (rectangle.getWidth() - textSellCapacity.getLayoutBounds().getWidth()) / 2);
        textSellCapacity.setLayoutY(y + 200);

        root.getChildren().addAll(stackPane, textCardName, textSellCapacity);

    }

    private void setText(Group root, String str, int y)
    {
        Text text = new Text(str);
        text.setLayoutX((500 - text.getLayoutBounds().getWidth()) / 2 - 40);
        text.setLayoutY(y);
        text.setFont(Font.font(null, FontWeight.SEMI_BOLD, 40));
        root.getChildren().addAll(text);
    }

    private void showOnlinePlayers()
    {
        Group rootShowOnlinePlayers = new Group();
        setBackGroundImage(rootShowOnlinePlayers);
        int counter = 0;
        Text onlinePlayerText = new Text("Online Players");
        onlinePlayerText.setFont(Font.font("verdana", 25));
        onlinePlayerText.relocate(170, 5);
        rootShowOnlinePlayers.getChildren().add(onlinePlayerText);
        for (Account account : Server.getAccounts())
        {
            if (account.getAuthToken() != null)
            {
                Text text = new Text(account.getAccountName());
                text.relocate(5, 50 + counter * 10);
                rootShowOnlinePlayers.getChildren().add(text);
                counter++;
            }
        }

        backButton(rootShowOnlinePlayers, 400, 400);

        Scene sceneShowOnlinePlayers = new Scene(rootShowOnlinePlayers, 500, 500);
        primaryStage.setScene(sceneShowOnlinePlayers);
        primaryStage.show();
    }


    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    private void setPrimaryStage(Stage primaryMessage)
    {
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
    public void run()
    {
        launch();
    }


    public Group getRootServerMenu()
    {
        return rootServerMenu;
    }

    public void setRootServerMenu(Group rootServerMenu)
    {
        this.rootServerMenu = rootServerMenu;
    }

    public Scene getSceneServerMenu()
    {
        return sceneServerMenu;
    }

    public void setSceneServerMenu(Scene sceneServerMenu)
    {
        this.sceneServerMenu = sceneServerMenu;
    }
}
