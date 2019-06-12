package View;

import Model.*;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ObservableList<GridPane> gridPane = FXCollections.observableArrayList();
    private ObservableList<Pane> battleFieldPanes = FXCollections.observableArrayList();

    public GridPane battleFieldGridPane = new GridPane();
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



    void setCells() {
        for(int row = 0 ; row < 5 ; row++){
            for(int column = 0 ; column < 9 ; column++){
                Pane pane = new Pane();
                battleFieldPanes.add(pane);
                Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[row][column].setCellPane(pane);
            }
        }
        gridPane.add(battleFieldGridPane);
        Battle.getCurrentBattle().setBattleFieldGridPane(gridPane.get(0));
    }
}
