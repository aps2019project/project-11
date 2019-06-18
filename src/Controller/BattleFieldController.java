package Controller;

import Model.Card;

public class BattleFieldController extends Thread {
    private boolean isCardSelected = false;
    private Card selectedCard;

    @Override
    public void run() {
        super.run();

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
