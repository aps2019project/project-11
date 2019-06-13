package Controller;

import Model.*;
import View.ShowOutput;

public class BattleManager
{
    private ShowOutput showOutput = ShowOutput.getInstance();

    public Player selectSecondPlayer(String userName)
    {
        Account account = new AccountManager().findAccount(userName);
        if (account != null)
        {
            if (account.getMainDeck() != null)
            {
                return new Player(account, false);
            }
            showOutput.printOutput("second player has no valid MainDeck");
            return null;
        }
        showOutput.printOutput("Invalid UserName");
        return null;
    }

    public void checkCircumstancesToInsertCard(String cardName, int x, int y)
    {
        Card card = Battle.getCurrentBattle().getPlayerTurn().getHand().findCardInHand(cardName);
        if (card == null)
        {
            showOutput.printOutput("Invalid card name");
            return;
        }
        if (card instanceof Minion)
        {
            checkCircumstancesToInsertMinion((Minion) card, x, y);
        }
        else
        {
            checkCircumstancesToInsertSpell((Spell) card, x, y);
        }
    }

    private void checkCircumstancesToInsertMinion(Minion minion, int x, int y)
    {
        if (setInsertAbleCellsMatrixForMinion()[x][y] != 1)
        {
            showOutput.printOutput("Invalid target");
            return;
        }
        if (insertCardToBattleField(minion, x, y))
        {
            System.out.println(minion.getCardName() + " with " + minion.getCardID() + " inserted to (" + x + ", " + y + ")");
        }
        else
        {
            System.out.println("Card insertion failed!");
        }
    }

    private void checkCircumstancesToInsertSpell(Spell spell, int x, int y)
    {
        NonSpellCard nonSpellCard = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[x][y].getCard();
        if (getPossibilityToInsertSpell(spell, nonSpellCard))
        {
            System.out.println(spell.getCardName() + " with " + spell.getCardID() + " inserted to (" + x + ", " + y + ")");
        }
        else
        {
            System.out.println("Invalid target");
        }
    }

    private boolean getPossibilityToInsertSpell(Spell spell, NonSpellCard nonSpellCard)
    {
        Target target = spell.getSpellEffect().getTargets().get(0);
        if (target.isOwnHero() && nonSpellCard instanceof Hero)
        {
            return true;
        }
        else
            return target.getNumOfOwnMinions() > 0 && nonSpellCard instanceof Minion;
    }

    private int[][] setInsertAbleCellsMatrixForMinion()
    {
        int[][] insertAbleCells = new int[5][9];
        for (Minion minion : Battle.getCurrentBattle().getPlayerTurn().getInsertedCards())
        {
            generateMatrix(insertAbleCells, minion);
        }
        Hero hero = Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0);
        generateMatrix(insertAbleCells, hero);
        insertAbleCells[hero.getRow()][hero.getColumn()] = 0;
        for (Card card : Battle.getCurrentBattle().getPlayerTurn().getInsertedCards())
        {
            insertAbleCells[card.getRow()][card.getColumn()] = 0;
        }
        showOutput.printMatrixValues(insertAbleCells, "InsertAble Cells :");
        return insertAbleCells;
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
            double x, y = 0;
            for (x = 0; x < 5; x++)
            {
                for (y = 0; y < 9; y++)
                {
                    if (Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[(int) x][(int) y].getCard() == null && setInsertAbleCellsMatrixForMinion()[(int) x][(int) y] == 1)
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
        showOutput.printOutput("Invalid card name");
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
                    Battle.getCurrentBattle().getBattleField().getAllCardsInTheBattleField().add((NonSpellCard) card);
                }
                else if (card instanceof Hero)
                {
                    Battle.getCurrentBattle().getBattleField().getAllCardsInTheBattleField().add((NonSpellCard) card);
                    System.out.println("Hero sat in BattleField");
                }
                Battle.getCurrentBattle().getBattleField().addCardInTheBattleField((NonSpellCard) card);
                if (Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getNonHeroCards().size() > 5)
                {
                    Battle.getCurrentBattle().getPlayerTurn().getHand().setNextCard(Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getNonHeroCards().get(5));
                }
                return true;
            }
            showOutput.printOutput("You don′t have enough MP");
            return false;
        }
        showOutput.printOutput("The selected Cell is full");
        return false;
    }


    public void useSpecialPower(SpecialPower specialPower, int x, int y)
    {
        if (Battle.getCurrentBattle().getSelectedCard().isCardSelectedInBattle())
        {
            NonSpellCard SelectedCard = Battle.getCurrentBattle().getSelectedCard();
            if (SelectedCard.getSpecialPower() == null)
            {
                showOutput.printOutput("Selected Card doesn't have special power");
            }
            else
            {
                SpellChange spellChange = specialPower.getSpellEffect().getSpellChanges().get(0);
                NonSpellCard card = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix()[x][y].getCard();
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
        if (Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0).getCardID().equals(cardID))
        {
            Battle.getCurrentBattle().selectCard(Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0));
            System.out.println("Card selected");
            return;
        }
        for (Card card : Battle.getCurrentBattle().getPlayerTurn().getInsertedCards())
        {
            if (card.getCardID().equals(cardID))
            {
                Battle.getCurrentBattle().selectCard((NonSpellCard) card);
                System.out.println("Card selected");
                return;
            }
        }
        showOutput.printOutput("Invalid card id");
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

    public void moveCard(int x, int y)
    {
        if (x < 0 || x > 4 || y < 0 || y > 8)
        {
            showOutput.printOutput("Invalid target");
            return;
        }
        NonSpellCard selectedCard = Battle.getCurrentBattle().getSelectedCard();
        int[][] moveAbleCells = selectedCard.getMoveAbleCells();
        showOutput.printMatrixValues(moveAbleCells, "MoveAble Cells :");
        if (selectedCard.isMoveAble())
        {
            if (moveAbleCells[x][y] == 1)
            {
                Battle.getCurrentBattle().moveCard(selectedCard, x, y);
                showOutput.printOutput(selectedCard.getCardID() + " moved to " + x + " " + y);
            }
            else
            {
                showOutput.printOutput("Invalid Target");
            }
        }
        else
        {
            showOutput.printOutput("this card is not movable");
        }
    }

    public void attackToOpponent(String cardID)
    {
        if (Battle.getCurrentBattle().getOpponentPlayer().getAccount().getCollection().findCardinCollection(cardID) == null)
        {
            showOutput.printOutput("Invalid card ID");
            return;
        }
        NonSpellCard opponentCard = Battle.getCurrentBattle().getBattleField().findCardInBattleField(cardID);
        NonSpellCard selectedCard = Battle.getCurrentBattle().getSelectedCard();
        if (selectedCard.isCardSelectedInBattle())
        {
            if ((selectedCard).isAttackAble())
            {
                if ((selectedCard).getImpactType() == ImpactType.melee)
                {
                    meleeCardAttack(selectedCard, opponentCard);
                }
                else if ((selectedCard).getImpactType() == ImpactType.ranged)
                {
                    rangedCardAttack(selectedCard, opponentCard);
                }
                else if ((selectedCard).getImpactType() == ImpactType.hybrid)
                {
                    hybridCardAttack(selectedCard, opponentCard);
                }
            }
            else
            {
                showOutput.printOutput("Card with " + selectedCard.getCardID() + " can′t attack");
            }
        }
    }

    private void meleeCardAttack(NonSpellCard selectedCard, NonSpellCard opponentCard)
    {
        if (Card.checkNeighborhood(selectedCard, opponentCard))
        {
            Battle.getCurrentBattle().attackToOpponent(selectedCard, opponentCard);
        }
        else
        {
            showOutput.printOutput("opponent minion is unavailable for attack");
        }
    }

    private void rangedCardAttack(NonSpellCard selectedCard, NonSpellCard opponentCard)
    {
        if (Card.findDestination(selectedCard, opponentCard) <= (selectedCard).getRangeOfAttack() && !(Card.checkNeighborhood(selectedCard, opponentCard)))
        {
            Battle.getCurrentBattle().attackToOpponent(selectedCard, opponentCard);
        }
        else
        {
            showOutput.printOutput("opponent minion is unavailable for attack");
        }
    }

    private void hybridCardAttack(NonSpellCard selectedCard, NonSpellCard opponentCard)
    {
        if (Card.findDestination(selectedCard, opponentCard) <= (selectedCard).getRangeOfAttack())
        {
            Battle.getCurrentBattle().attackToOpponent(selectedCard, opponentCard);
        }
        else
        {
            showOutput.printOutput("opponent minion is unavailable for attack");
        }
    }
}
