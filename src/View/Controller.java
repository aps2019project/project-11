package View;

import Model.Battle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Controller {
    public GridPane gridPane;
    public ImageView[][] imageViews = new ImageView[5][9];
    public ImageView imageView1;
    public GridPane battleFieldGridPane;
    public static Label firstPlayerMana;
    public static Label secondPlayerMana;
    private Request request = Request.getInstance();
    public ImageView minion1;
    public ImageView minion2;
    public ImageView minion3;
    public ImageView minion4;
    public ImageView minion5;
    public ImageView minion6;
    public ImageView minion7;
    public ImageView minion8;
    public ImageView hero1;
    public ImageView hero2;
    public ImageView hero3;
    public ImageView hero4;
    public ImageView hero5;
    public ImageView hero6;
    public ImageView hero7;
    public ImageView hero8;
    public ImageView hero9;
    @FXML
    public ImageView hero10;

    private static final Controller controller = new Controller();


    private Controller(){
    }

    public static Controller getController() {
        return controller;
    }

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

    /*void setCells() {
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {
                Tile pane = new Tile(row, column);
                Cell cell = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[row][column];
                cell.setCellPane(pane);
                pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (Battle.getCurrentBattle().getSelectedCard() == cell.getCard()) {
                            Battle.getCurrentBattle().setSelectedCard(null);
                            request.setCommand(CommandType.EXIT);
                            request.getCommand().cardOrItemID = null;
                        } else if (Battle.getCurrentBattle().getSelectedCard() == null) {
                            if (cell.getCard() != null) {
                                Battle.getCurrentBattle().setSelectedCard(cell.getCard());
                                request.setCommand(CommandType.SELECT);
                                request.getCommand().cardOrItemID = cell.getCard().getCardID();
                            }
                        } else {
                            request.setCommand(CommandType.NORMAL_ATTACK);
                            request.getCommand().enemyCardIDForNormalAttack = cell.getCard().getCardID();
                        }
                    }
                });
            }
        }
        gridPane = new GridPane();
        Battle.getCurrentBattle().setBattleFieldGridPane(gridPane);
    }*/

    @FXML
    void setBeforeGame() {
        switch (Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0).getImageNumber()) {
            case 5:
                Battle.getCurrentBattle().getBattleFieldGridPane().add(hero1, 2, 0);
                break;
            case 6:
                Battle.getCurrentBattle().getBattleFieldGridPane().add(hero2, 2, 0);
                break;
            case 7:
                Battle.getCurrentBattle().getBattleFieldGridPane().add(hero3, 2, 0);
                break;
            case 8:
                Battle.getCurrentBattle().getBattleFieldGridPane().add(hero4, 2, 0);
                break;
            case 9:
                Battle.getCurrentBattle().getBattleFieldGridPane().add(hero5, 2, 0);
                break;
            case 10:
                Battle.getCurrentBattle().getBattleFieldGridPane().add(hero6, 2, 0);
                break;
            case 11:
                Battle.getCurrentBattle().getBattleFieldGridPane().add(hero7, 2, 0);
                break;
            case 12:
                Battle.getCurrentBattle().getBattleFieldGridPane().add(hero8, 2, 0);
                break;
            case 13:
                Battle.getCurrentBattle().getBattleFieldGridPane().add(hero9, 2, 0);
                break;
            case 14:
                hero10.setX(50);
                hero10.setY(150);
                break;
        }
    }


}
