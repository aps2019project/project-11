package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;


public class Controller {


    public ImageView player1Hero;
    public ImageView player2Hero;
    public ImageView imageView;


    public void endTurn() {
        Battle.getCurrentBattle().endTurn();
    }

    public void handle() {

    }

    @FXML
    public void handleDragDetection(MouseEvent mouseEvent) {
        Dragboard dragboard = player1Hero.startDragAndDrop(TransferMode.ANY);

        ClipboardContent clipboard = new ClipboardContent();
        clipboard.putImage(player1Hero.getImage());

        dragboard.setContent(clipboard);
    }

    public void handleImageDragOver(DragEvent event) {
        Image image = event.getDragboard().getImage();
        imageView.setImage(image);

    }
}
