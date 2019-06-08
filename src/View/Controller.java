package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;


public class Controller {


    public ImageViewWithCard player1Hero = new ImageViewWithCard(2,0,Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0));
    public ImageViewWithCard player2Hero = new ImageViewWithCard(2,8,Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0));
    public ImageViewWithCard imageView;


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
