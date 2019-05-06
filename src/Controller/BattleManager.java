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

    public void CheckCircumstancesToInsertCard(String cardName, int x, int y)
    {
        Card card = Battle.getCurrentBattle().getPlayerTurn().getHand().findCardInHand(cardName);
        if (setMovableCellsMatrix()[x][y] != 1)
        {
            ShowOutput.printOutput("Invalid target");
            return;
        }
        if (card != null)
        {
            if (insertCardToBattleField(card, x, y))
            {
                System.out.println("card sat in the battlefield");
                return;
            }
            else
            {
                System.out.println("Card insertion failed!");
            }
        }
        else
        {
            ShowOutput.printOutput("Invalid card name");
        }
    }

    private int[][] setMovableCellsMatrix()
    {
        int[][] matrix = new int[5][9];
        for (Minion minion : Battle.getCurrentBattle().getPlayerTurn().getInsertedCards())
        {
            generateMatrix(matrix, minion);
        }
        Hero hero = Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0);
        generateMatrix(matrix, hero);
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        return matrix;
    }

    private void generateMatrix(int[][] matrix, Card card)
    {
        for (int row = card.getRow() - 1; row <= card.getRow() + 1; row++)
        {
            if (row < 0 || row >= 5)
            {
                continue;
            }
            for (int column = card.getColumn() - 1; column <= card.getColumn() + 1; column++)
            {
                if (column < 0 || column >= 9)
                {
                    continue;
                }
                matrix[row][column] = 1;
            }
        }
    }

    public void CheckCircumstancesToInsertCard(Card card)
    {
        if (card != null)
        {
            int condition = 0;
            double x = 0, y = 0;
            for (x = 0; x < 5; x++)
            {
                for (y = 0; y < 9; y++)
                {
                    if (Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[(int) x][(int) y].getCard() == null && setMovableCellsMatrix()[(int) x][(int) y] == 1)
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

    public boolean insertCardToBattleField(Card card, int x, int y)
    {
        if (!(Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[x][y].isFull()))
        {
            if (Battle.getCurrentBattle().getPlayerTurn().getMP() >= card.getRequiredMP())
            {
                card.setRow(x);
                card.setColumn(y);
                Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[x][y].setCard(Battle.getCurrentBattle().getSelectedCard());
                Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().remove(card);
                Battle.getCurrentBattle().getPlayerTurn().decreaseMP(card.getRequiredMP());
                if (card instanceof Minion)
                {
                    Battle.getCurrentBattle().getPlayerTurn().getInsertedCards().add((Minion) card);
                    Battle.getCurrentBattle().getBattleField().getAllCardsInTheBattleField().add((NonSpellCards) card);
                }
                else if (card instanceof Hero)
                {
                    Battle.getCurrentBattle().getBattleField().getAllCardsInTheBattleField().add((NonSpellCards) card);
                    System.out.println("Hero sat in BattleField");
                }
                if (Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getNonHeroCards().size() > 5)
                {
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


    public void useSpecialPower(SpecialPower specialPower, int x, int y)
    {
        if (Battle.getCurrentBattle().getSelectedCard().isCardSelectedInBattle())
        {
            NonSpellCards SelectedCard = Battle.getCurrentBattle().getSelectedCard();
            if (SelectedCard.getSpecialPower() == null)
            {
                ShowOutput.printOutput("Selected Card doesn't have special power");
            }
            else
            {
                SpellChange spellChange = specialPower.getSpellEffect().getSpellChanges().get(0);
                NonSpellCards card = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[x][y].getCard();
                if (card != null)
                {
                    card.addActiveSpellOnThisCard(spellChange);
                    spellChange.applySpellChangeOnCard(card);
                }
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
