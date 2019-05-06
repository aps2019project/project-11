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
                return new Player(account, false);
            }
            ShowOutput.printOutput("second player has no valid MainDeck");
            return null;
        }
        ShowOutput.printOutput("Invalid UserName");
        return null;
    }

    public void CheckCircumstancesToInsertCard(String cardName, int x, int y) {
        //todo target
        Card card = Battle.getCurrentBattle().getPlayerTurn().getHand().findCardInHand(cardName);
        if (card != null) {
            if (insertCardToBattleField(card, x, y)) {
                return;
            }
            else {
                System.out.println();
            }
        } else {
            ShowOutput.printOutput("Invalid card name");
        }
    }

    public void CheckCircumstancesToInsertCard(Card card)
    {
        //todo target
        if (card != null)
        {
            int condition = 0;
            double x = 0, y = 0;
            for (x = 0; x < 5; x++)
            {
                for (y = 0; y < 9; y++)
                {
                    if (Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[(int) x][(int) y].getCard() == null)
                    {
                        condition = 1;
                        break;
                    }
                }
            }
            if (condition == 0)
            {
                return;
            }
            if (insertCardToBattleField(card, (int) x, (int) y))
            {
                return;
            }
        }
        ShowOutput.printOutput("Invalid card name");
    }

    private boolean insertCardToBattleField(Card card, int x, int y)
    {
        if(!(Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[x][y].isFull())) {
            if (Battle.getCurrentBattle().getPlayerTurn().getMP() >= card.getRequiredMP()) {
                card.setRow(x);
                card.setColumn(y);
                Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[x][y].setCard(Battle.getCurrentBattle().getSelectedCard());
                Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().remove(card);
                Battle.getCurrentBattle().getPlayerTurn().decreaseMP(card.getRequiredMP());
                if (card instanceof Minion) {
                    Battle.getCurrentBattle().getPlayerTurn().getInsertedCards().add((Minion) card);
                }
                if (Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getNonHeroCards().size() > 5) {
                    Battle.getCurrentBattle().getPlayerTurn().getHand().setNextCard(Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getNonHeroCards().get(5));
                }
                return true;
            }
            ShowOutput.printOutput("You don′t have enough MP");
            return false;
        }
        ShowOutput.printOutput("The selected Cell is full");
        return false;
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
