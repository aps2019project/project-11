package Controller;

import Model.Battle;
import Model.Card;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BattleFieldController extends Thread {
    private boolean isCardSelected = false;
    private Card selectedCard;

    @Override
    public void run() {
        super.run();
        checkInsertingCard();
    }

    public void checkInsertingCard(){
        Pane[] firstPlayerHandPanes = Battle.getCurrentBattle().getFirstPlayerHandPanes();
        Pane[] secondPlayerHandPanes = Battle.getCurrentBattle().getSecondPlayerHandPanes();

        for(int number =0 ; number < firstPlayerHandPanes.length ; number++){
            int finalNumber = number;
            firstPlayerHandPanes[number].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(isCardSelected){
                        //todo to Show error
                    }
                    else {
                        setCardSelected(true);
                        setSelectedCard(Battle.getCurrentBattle().getFirstPlayer().getHand().getCards().get(finalNumber));
                    }
                }
            });
        }

        for(int number =0 ; number < secondPlayerHandPanes.length ; number++){
            int finalNumber = number;
            secondPlayerHandPanes[number].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(isCardSelected){
                        //todo to show error
                    }
                    else
                    {
                        setCardSelected(true);
                        setSelectedCard(Battle.getCurrentBattle().getSecondPlayer().getHand().getCards().get(finalNumber));
                    }
                }
            });
        }

        GridPane gridPane = Battle.getCurrentBattle().getBattleFieldGridPane();
    }



    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public boolean isCardSelected() {
        return isCardSelected;
    }

    public void setCardSelected(boolean cardSelected) {
        isCardSelected = cardSelected;
    }
}
