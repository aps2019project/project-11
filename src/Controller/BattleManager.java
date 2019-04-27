package Controller;

import Model.*;

public class BattleManager
{

    public void checkSecondPlayerExistence(String userName)
    {
        for (Account account : new AccountManager().getAccounts())
        {
            if (account.getAccountName().equals(userName))
            {
                return new Player(account);
            }
        }
    }

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
