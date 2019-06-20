package Controller;

import Model.*;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class BattleFieldController extends Thread {
    private boolean isCardSelected = false;
    private Card selectedCard;

    @Override
    public void run() {
        super.run();
        checkInsertingCard();
    }

    private void checkInsertingCard() {
        Pane[] firstPlayerHandPanes = Battle.getCurrentBattle().getFirstPlayerHandPanes();
        Pane[] secondPlayerHandPanes = Battle.getCurrentBattle().getSecondPlayerHandPanes();

        for (int number = 0; number < firstPlayerHandPanes.length; number++) {
            int finalNumber = number;
            firstPlayerHandPanes[number].setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (isCardSelected) {
                        //todo to Show error
                    } else {
                        setCardSelected(true);
                        setSelectedCard(Battle.getCurrentBattle().getFirstPlayer().getHand().getCards().get(finalNumber));
                        insertCard();
                    }
                }
            });
        }

        for (int number = 0; number < secondPlayerHandPanes.length; number++) {
            int finalNumber = number;
            secondPlayerHandPanes[number].setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (isCardSelected) {
                        //todo to show error
                    } else {
                        setCardSelected(true);
                        setSelectedCard(Battle.getCurrentBattle().getSecondPlayer().getHand().getCards().get(finalNumber));
                        insertCard();
                    }
                }
            });
        }



    }

    private void insertCard(){
        Cell[][] battleFieldCells = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {
                int finalColumn1 = column;
                int finalRow1 = row;
                battleFieldCells[row][column].getCellPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (isCardSelected){
                            BattleManager battleManager = new BattleManager();
                            if(selectedCard instanceof Minion){
                                if(battleManager.checkCircumstancesToInsertMinionBoolean((Minion) selectedCard , finalRow1 , finalColumn1)){
                                    ImageView imageView = Card.getCardImageView(selectedCard);
                                    battleFieldCells[finalRow1][finalColumn1].getCellPane().getChildren().add(imageView);
                                    Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().add(Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard());
                                    Battle.getCurrentBattle().setHandIcons();
                                }
                            }
                            else if(selectedCard instanceof Spell){
                                if(battleManager.checkCircumstancesToInsertSpellBoolean((Spell) selectedCard , finalRow1 , finalColumn1)){

                                    Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().add(Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard());
                                    Battle.getCurrentBattle().setHandIcons();
                                    //todo Animation
                                }
                            }

                        }
                        else {
                            //todo error
                        }
                    }
                });
            }
        }
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
