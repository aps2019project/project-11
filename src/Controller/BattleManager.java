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
                return new Player(account , false);
            }
            ShowOutput.printOutput("second player has no valid MainDeck");
            return null;
        }
        ShowOutput.printOutput("Invalid UserName");
        return null;
    }

    public void CheckCircumstancesToInsertCard(String cardName, int x, int y)
    {
        //todo target
        Card card = Battle.getCurrentBattle().getPlayerTurn().getHand().findCardInHand(cardName);
        if (card != null)
        {
            if (Battle.getCurrentBattle().getPlayerTurn().getMP() >= card.getRequiredMP())
            {
                card.setRow(x);
                card.setColumn(y);
                Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[x][y].setCard(Battle.getCurrentBattle().getSelectedCard());
                Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().remove(card);
                if (Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().size() > 5)
                {
                    Battle.getCurrentBattle().getPlayerTurn().getHand().setNextCard(Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().get(5));
                }
                return;
            }
            ShowOutput.printOutput("You don′t have enough MP");
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
                ShowOutput.printOutput("SelectedCard doesn't have special power");
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
