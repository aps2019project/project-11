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

    public void useSpecialPower(int x, int y) {
        if (Battle.getCurrentBattle().getSelectedCard().isCardSelectedInBattle())
        {
            NonSpellCards SelectedCard = (NonSpellCards)Battle.getCurrentBattle().getSelectedCard();
            if (SelectedCard.getSpecialPower() == null)
            {
                System.out.println("SelectedCard doesn't have special power");
            }
            else
            {
                Battle.getCurrentBattle().getSelectedCard().setRow(x);
                Battle.getCurrentBattle().getSelectedCard().setColumn(y);
            }

        }
    }

    public void selectCard(int cardID)
    {
        for (Card card : Battle.getCurrentBattle().getPlayerTurn().getHand().getCards())
        {
            if (card.getCardID() == cardID)
            {
                Battle.getCurrentBattle().selectCard((NonSpellCards) card);
                return;
            }
        }
        ShowOutput.printOutput("Invalid card id");
    }


    public void selectItem(int itemID)
    {
        for (Item item : Battle.getCurrentBattle().getPlayerTurn().getCollectibleItems())
        {
            if (item.getItemID() == itemID)
            {
                Battle.getCurrentBattle().selectCollectibleItem(item);
            }
        }
    }
}
