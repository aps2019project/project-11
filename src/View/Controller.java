package View;

import Model.*;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class Controller {
    public GridPane gridPane;
    public ImageView player1Hero;
    public ImageView player2Hero;
    public static Label firstPlayerMana ;
    public static Label secondPlayerMana ;


    public void endTurn() {
        Battle.getCurrentBattle().endTurn();
    }

    /*@FXML
    public void handleDragDetection(MouseEvent mouseEvent) {
        Dragboard dragboard = player1Hero.startDragAndDrop(TransferMode.ANY);

        ClipboardContent clipboard = new ClipboardContent();
        clipboard.put(player1Hero.getImage());

        dragboard.setContent(clipboard);
    }

    public void handleImageDragOver(DragEvent event) {

    }*/

    public static Label getFirstPlayerMana() {
        return firstPlayerMana;
    }

    public static Label getSecondPlayerMana() {
        return secondPlayerMana;
    }

    public static void setFirstPlayerMana(Label firstPlayerMana) {
        Controller.firstPlayerMana = firstPlayerMana;
    }

    public static void setSecondPlayerMana(Label secondPlayerMana) {
        Controller.secondPlayerMana = secondPlayerMana;
    }
}
