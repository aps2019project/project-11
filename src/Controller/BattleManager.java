package Controller;

import Model.*;
import View.ShowOutput;

public class BattleManager
{
    private ShowOutput showOutput = ShowOutput.getInstance();
    private Battle battle;

    public BattleManager(Battle battle)
    {
        this.battle = battle;
    }

    boolean checkCircumstancesToInsertMinionBoolean(Minion minion, int x, int y)
    {
        if (setInsertAbleCellsMatrixForMinion()[x][y] != 1)
        {
            showOutput.printOutput("Invalid target");
            return false;
        }
        if (insertCardToBattleField(minion, x, y))
        {
            System.out.println(minion.getCardName() + " with " + minion.getCardID() + " inserted to (" + x + ", " + y + ")");
            return true;
        }
        else
        {
            System.out.println("Card insertion failed!");
            return false;
        }
    }

    public boolean checkCircumstancesToInsertSpellBoolean(Spell spell, int x, int y)
    {
        NonSpellCard nonSpellCard = battle.getBattleField().getBattleFieldMatrix()[x][y].getCard();
        if (getPossibilityToInsertSpell(spell, nonSpellCard))
        {
            System.out.println(spell.getCardName() + " with " + spell.getCardID() + " inserted to (" + x + ", " + y + ")");
            return true;
        }
        else
        {
            System.out.println("Invalid target");
            return false;
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
        {
            return target.getNumOfOwnMinions() > 0 && nonSpellCard instanceof Minion;
        }
    }

    private int[][] setInsertAbleCellsMatrixForMinion()
    {
        int[][] insertAbleCells = new int[5][9];
        for (Minion minion : battle.getPlayerTurn().getInsertedCards())
        {
            generateMatrix(insertAbleCells, minion);
        }
        Hero hero = battle.getPlayerTurn().getMainDeck().getHero().get(0);
        generateMatrix(insertAbleCells, hero);
        insertAbleCells[hero.getRow()][hero.getColumn()] = 0;
        for (Card card : battle.getPlayerTurn().getInsertedCards())
        {
            insertAbleCells[card.getRow()][card.getColumn()] = 0;
        }
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
                    if (getBattle().getBattleField().getBattleFieldMatrix()[(int) x][(int) y].getCard() == null && setInsertAbleCellsMatrixForMinion()[(int) x][(int) y] == 1)
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
        if (!(getBattle().getBattleField().getBattleFieldMatrix()[x][y].isFull()))
        {
            if (getBattle().getPlayerTurn().getMP() >= card.getRequiredMP())
            {
                card.setRow(x);
                card.setColumn(y);
                getBattle().getBattleField().getBattleFieldMatrix()[x][y].setCard(getBattle().getSelectedCard());
                getBattle().getPlayerTurn().getHand().getCards().remove(card);
                getBattle().getPlayerTurn().decreaseMP(card.getRequiredMP());
                getBattle().getBattleField().getBattleFieldMatrix()[x][y].setCard(card);
                if (card instanceof Minion)
                {
                    getBattle().getPlayerTurn().getInsertedCards().add((Minion) card);
                    getBattle().getBattleField().getAllCardsInTheBattleField().add((NonSpellCard) card);
                }
                else if (card instanceof Hero)
                {
                    getBattle().getBattleField().getAllCardsInTheBattleField().add((NonSpellCard) card);
                    System.out.println("Hero sat in BattleField");
                }
                getBattle().getBattleField().addCardInTheBattleField((NonSpellCard) card);
                if (getBattle().getPlayerTurn().getNonHeroCards().size() > 5)
                {
                    getBattle().getPlayerTurn().getHand().setNextCard(getBattle().getPlayerTurn().getNonHeroCards().get(5));
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
        if (getBattle().getSelectedCard().isCardSelectedInBattle())
        {
            NonSpellCard SelectedCard = getBattle().getSelectedCard();
            if (SelectedCard.getSpecialPower() == null)
            {
                showOutput.printOutput("Selected Card doesn't have special power");
            }
            else
            {
                SpellChange spellChange = specialPower.getSpellEffect().getSpellChanges().get(0);
                NonSpellCard card = getBattle().getBattleField().getBattleFieldMatrix()[x][y].getCard();
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
        if (getBattle().getPlayerTurn().getMainDeck().getHero().get(0).getCardID().equals(cardID))
        {
            getBattle().selectCard(getBattle().getPlayerTurn().getMainDeck().getHero().get(0));
            System.out.println("Card selected");
            return;
        }
        for (Card card : getBattle().getPlayerTurn().getInsertedCards())
        {
            if (card.getCardID().equals(cardID))
            {
                getBattle().selectCard((NonSpellCard) card);
                System.out.println("Card selected");
                return;
            }
        }
        showOutput.printOutput("Invalid card id");
    }

    public void selectItem(String itemID)
    {
        for (Item item : getBattle().getPlayerTurn().getCollectibleItems())
        {
            if (item.getItemID().equals(itemID))
            {
                getBattle().selectCollectibleItem(item);
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
        NonSpellCard selectedCard = getBattle().getSelectedCard();
        int[][] moveAbleCells = selectedCard.getMoveAbleCells(getBattle());
        if (selectedCard.isMoveAble())
        {
            if (moveAbleCells[x][y] == 1)
            {
                getBattle().moveCard(selectedCard, x, y);
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

    boolean moveCardBoolean(int x, int y)
    {
        if (getBattle().getSelectedCard() != null)
        {
            if (x < 0 || x > 4 || y < 0 || y > 8)
            {
                showOutput.printOutput("Invalid target");
                return false;
            }
            NonSpellCard selectedCard = getBattle().getSelectedCard();
            int[][] moveAbleCells = selectedCard.getMoveAbleCells(getBattle());
            if (selectedCard.isMoveAble())
            {
                if (moveAbleCells[x][y] == 1)
                {
                    getBattle().moveCard(selectedCard, x, y);
                    showOutput.printOutput(selectedCard.getCardID() + " moved to " + x + " " + y);
                    return true;
                }
                else
                {
                    showOutput.printOutput("Invalid Target");
                    return false;
                }
            }
            else
            {
                showOutput.printOutput("this card is not movable");
                return false;
            }
        }
        showOutput.printOutput("You Didn't Select any Card");
        return false;
    }

    public void attackToOpponent(String cardName)
    {
        if (getBattle().getOpponentPlayer().getAccount().getCollection().findCardinCollectionByName(cardName) == null)
        {
            showOutput.printOutput("Invalid card name");
            return;
        }
        NonSpellCard opponentCard = getBattle().getBattleField().findCardInBattleFieldByName(cardName);
        NonSpellCard selectedCard = getBattle().getSelectedCard();
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

    public void attackToOpponent(Card opponentCard)
    {
        if (!getBattle().getBattleField().getAllCardsInTheBattleField().contains(opponentCard))
        {
            showOutput.printOutput("Invalid card name");
            return;
        }
        NonSpellCard selectedCard = getBattle().getSelectedCard();
        if(selectedCard != null) {
            if (selectedCard.isCardSelectedInBattle()) {
                if ((selectedCard).isAttackAble()) {
                    showOutput.printOutput("Card with " + selectedCard.getCardID() + " attacks " + opponentCard.getCardID());
                    if ((selectedCard).getImpactType() == ImpactType.melee) {
                        meleeCardAttack(selectedCard, (NonSpellCard) opponentCard);
                    } else if ((selectedCard).getImpactType() == ImpactType.ranged) {
                        rangedCardAttack(selectedCard, (NonSpellCard) opponentCard);
                    } else if ((selectedCard).getImpactType() == ImpactType.hybrid) {
                        hybridCardAttack(selectedCard, (NonSpellCard) opponentCard);
                    }
                } else {
                    showOutput.printOutput("Card with " + selectedCard.getCardID() + " is not attackAble");
                }
            }
        }
    }

    private void meleeCardAttack(NonSpellCard selectedCard, NonSpellCard opponentCard)
    {
        if (Card.checkNeighborhood(selectedCard, opponentCard))
        {
            getBattle().attackToOpponent(selectedCard, opponentCard);
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
            getBattle().attackToOpponent(selectedCard, opponentCard);
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
            getBattle().attackToOpponent(selectedCard, opponentCard);
        }
        else
        {
            showOutput.printOutput("opponent minion is unavailable for attack");
        }
    }

    public Battle getBattle()
    {
        return battle;
    }
}
