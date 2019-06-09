package View;

import Model.*;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class Controller {


    public ImageView player1Hero;
    public ImageView player2Hero;
    public ImageView imageView;
    Pane pane = new Pane();

    public void endTurn() {
        Battle.getCurrentBattle().endTurn();
    }

    public void handle() {

    }


}
