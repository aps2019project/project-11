package View;

import Model.*;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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

public class Controller
{
    private ObservableList<GridPane> gridPane = FXCollections.observableArrayList();
    private ObservableList<Pane> battleFieldPanes = FXCollections.observableArrayList();

    public GridPane battleFieldGridPane = new GridPane();
    public ImageView player1Hero;
    public ImageView player2Hero;
    public static Label firstPlayerMana;
    public static Label secondPlayerMana;
    Request request = Request.getInstance();


    public void endTurn()
    {
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

    public static Label getFirstPlayerMana()
    {
        return firstPlayerMana;
    }

    public static Label getSecondPlayerMana()
    {
        return secondPlayerMana;
    }

    public static void setFirstPlayerMana(Label firstPlayerMana)
    {
        Controller.firstPlayerMana = firstPlayerMana;
    }

    public static void setSecondPlayerMana(Label secondPlayerMana)
    {
        Controller.secondPlayerMana = secondPlayerMana;
    }

    void setCells()
    {
        for (int row = 0; row < 5; row++)
        {
            for (int column = 0; column < 9; column++)
            {
                Pane pane = new Pane();
                battleFieldPanes.add(pane);
                Cell cell = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[row][column];
                cell.setCellPane(pane);
                pane.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        if (Battle.getCurrentBattle().getSelectedCard() == cell.getCard())
                        {
                            Battle.getCurrentBattle().setSelectedCard(null);
                            request.setCommand(CommandType.EXIT);
                            request.getCommand().cardOrItemID = null;
                        }
                        else if (Battle.getCurrentBattle().getSelectedCard() == null)
                        {
                            if (cell.getCard() != null)
                            {
                                Battle.getCurrentBattle().setSelectedCard(cell.getCard());
                                request.setCommand(CommandType.SELECT);
                                request.getCommand().cardOrItemID = cell.getCard().getCardID();
                            }
                        }
                        else
                        {
                            request.setCommand(CommandType.NORMAL_ATTACK);
                            request.getCommand().enemyCardIDForNormalAttack = cell.getCard().getCardID();
                        }
                    }
                });
            }
        }
        gridPane.add(battleFieldGridPane);
        Battle.getCurrentBattle().setBattleFieldGridPane(gridPane.get(0));
    }
}
