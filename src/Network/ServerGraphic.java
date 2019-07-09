package Network;

import Model.Account;
import Model.CommandType;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ServerGraphic extends Application implements Runnable
{
    private Stage primaryMessage;
    private Group rootServerMenu;
    private Scene sceneServerMenu;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        setPrimaryMessage(primaryStage);
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
        primaryMessage.setScene(sceneServerMenu);
        primaryMessage.show();
    }

    private void showCards()
    {

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
        primaryMessage.setScene(sceneShowOnlinePlayers);
        primaryMessage.show();
    }


    public Stage getPrimaryMessage()
    {
        return primaryMessage;
    }

    private void setPrimaryMessage(Stage primaryMessage)
    {
        this.primaryMessage = primaryMessage;
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
                primaryMessage.setScene(getSceneServerMenu());
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
