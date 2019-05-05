package Model;

import java.util.ArrayList;

public class BattleField
{
    private Cell battleFieldMatrix[][] = new Cell[5][9];
    private ArrayList<NonSpellCards> allCardsInTheBattleField = new ArrayList<>();

    public Cell[][] getBattleFieldMatrix()
    {
        return battleFieldMatrix;
    }

    public NonSpellCards findCardInBattleField(int cardID)
    {
        for (NonSpellCards nonSpellCard : this.getAllCardsInTheBattleField())
        {
            if (nonSpellCard.getCardID() == cardID)
            {
                return nonSpellCard;
            }
        }
        return null;
    }

    public ArrayList<NonSpellCards> getAllCardsInTheBattleField()
    {
        return allCardsInTheBattleField;
    }

    public void addCardInTheBattleField(NonSpellCards cardInTheBattleField)
    {
        allCardsInTheBattleField.add(cardInTheBattleField);
    }
}
