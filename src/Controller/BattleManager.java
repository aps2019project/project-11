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
            ShowOutput.printOutput("second player has no valid MainDeck");
            return null;
        }
        ShowOutput.printOutput("Invalid UserName");
        return null;
    }

    public void CheckCircumstancesToInsertCard(String cardName, int x, int y)
    {
        //todo
        Card card = Battle.getCurrentBattle().getPlayerTurn().getHand().findCardInCollection(cardName);
        if (card != null)
        {
            card.setRow(x);
            card.setColumn(y);
            Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[x][y].setCard(Battle.getCurrentBattle().getSelectedCard());
            return;
        }
        ShowOutput.printOutput("Invalid card name");
    }

    public void useSpecialPower(int x, int y)
    {
        if (Battle.getCurrentBattle().getSelectedCard().isCardSelectedInBattle())
        {
            NonSpellCards SelectedCard = Battle.getCurrentBattle().getSelectedCard();
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

    public void selectCard(String cardID)
    {
        for (Card card : Battle.getCurrentBattle().getPlayerTurn().getHand().getCards())
        {
            if (card.getCardID().equals(cardID))
            {
                Battle.getCurrentBattle().selectCard((NonSpellCards) card);
                return;
            }
        }
        ShowOutput.printOutput("Invalid card id");
    }


    public void selectItem(String itemID)
    {
        for (Item item : Battle.getCurrentBattle().getPlayerTurn().getCollectibleItems())
        {
            if (item.getItemID().equals(itemID))
            {
                Battle.getCurrentBattle().selectCollectibleItem(item);
            }
        }
    }
}
