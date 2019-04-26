package Controller;

import Model.*;

public class BattleManager
{
    public void CheckCircumstancesToInsertCard(String cardName, int x, int y)
    {
        Card card = Card.findCard(cardName);
        for (Card playerHandCard : Battle.currentBattle.getPlayerTurn().getHand().getCards())
        {
            if (card.getCardID() == playerHandCard.getCardID())
            {

            }
        }
    }
}
