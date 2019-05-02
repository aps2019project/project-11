package Controller;

import Model.*;
import View.*;

public class BattleManager
{

    public Player selectSecondPlayer(String userName)
    {
        Account account = new AccountManager().findAccount(userName);
        if (account != null)
        {
            if (account.getMainDeck() != null)
            {
                return new Player(account);
            }
            ShowOutput.printOutput("selected deck for second player is invalid");
        }
        return null;
    }

    public void CheckCircumstancesToInsertCard(String cardName, int x, int y)
    {
        Card card = Card.findCard(cardName);
        for (Card playerHandCard : Battle.getCurrentBattle().getPlayerTurn().getHand().getCards())
        {
            if (card.getCardID() == playerHandCard.getCardID())
            {

            }
        }
    }
}
