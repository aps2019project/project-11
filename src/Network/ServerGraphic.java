package Network;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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

            }
        });

        Button showCard = new Button("Show Cards");
        showCard.relocate(220 , 280);
        showCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        rootServerMenu.getChildren().addAll(showCard , showOnlinePlayers);
        Scene sceneServerMenu = new Scene(rootServerMenu , 500 , 500);
        primaryMessage.setScene(sceneServerMenu);
        primaryMessage.show();
    }


    public Stage getPrimaryMessage() {
        return primaryMessage;
    }

    public void setPrimaryMessage(Stage primaryMessage) {
        this.primaryMessage = primaryMessage;
    }

    @Override
    public void run() {
        launch();
    }
}
