package Network;

import Model.Account;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ServerGraphic extends Application implements Runnable{
    private Stage primaryMessage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryMessage(primaryStage);
        makeMenu();
    }

    private void makeMenu() {
        Group rootServerMenu = new Group();
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
        primaryMessage.setScene(sceneServerMenu);
        primaryMessage.show();
    }

    private void showCards() {

    }

    private void showOnlinePlayers() {
        Group rootShowOnlinePlayers = new Group();
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
        Scene sceneShowOnlinePlayers = new Scene(rootShowOnlinePlayers , 500 , 500);
        primaryMessage.setScene(sceneShowOnlinePlayers);
        primaryMessage.show();
    }


    public Stage getPrimaryMessage() {
        return primaryMessage;
    }

    private void setPrimaryMessage(Stage primaryMessage) {
        this.primaryMessage = primaryMessage;
    }

    @Override
    public void run() {
        launch();
    }
}
