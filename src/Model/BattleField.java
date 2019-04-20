package Model;

public class BattleField
{
    private Cell battleFieldMatrix[][] = new Cell[5][9];

    public Cell[][] getBattleFieldMatrix()
    {
        return battleFieldMatrix;
    }
}
