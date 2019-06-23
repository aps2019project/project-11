package Model;

import java.util.ArrayList;

public class BattleField
{
    private Cell[][] battleFieldMatrix = new Cell[5][9];
    private ArrayList<NonSpellCard> allCardsInTheBattleField = new ArrayList<>();

    public void makeCells()
    {
        for (int i = 0;i < 5;i++)
        {
            for (int j = 0;j < 9;j++)
            {
               battleFieldMatrix[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getBattleFieldMatrix()
    {
        return battleFieldMatrix;
    }

    public NonSpellCard findCardInBattleField(String cardID)
    {
        for (NonSpellCard nonSpellCard : this.getAllCardsInTheBattleField())
        {
            if (nonSpellCard.getCardID().equals(cardID))
            {
                return nonSpellCard;
            }
        }
        return null;
    }

    public NonSpellCard findCardInBattleFieldByName(String cardName)
    {
        for (NonSpellCard nonSpellCard : this.getAllCardsInTheBattleField())
        {
            if (nonSpellCard.getCardName().equals(cardName))
            {
                return nonSpellCard;
            }
        }
        return null;
    }

    public Cell getCellWithGivenCoordinate (int x, int y)
    {
        return battleFieldMatrix[x][y];
    }

    public ArrayList<NonSpellCard> getAllCardsInTheBattleField()
    {
        return allCardsInTheBattleField;
    }

    public void addCardInTheBattleField(NonSpellCard cardInTheBattleField)
    {
        allCardsInTheBattleField.add(cardInTheBattleField);
    }
}
