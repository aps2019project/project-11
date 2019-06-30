package Model;

import java.util.ArrayList;

public class NonSpellCard extends Card
{
    private Cell currentCell = new Cell();
    private int defaultHP;
    private int defaultAP;
    private int currentHP;
    private int currentAP;
    private SpecialPower specialPower;
    private ArrayList<SpellChange> activeSpellsOnThisCard = new ArrayList<>();
    private ArrayList<ItemChange> activeItemsOnThisCard = new ArrayList<>();
    private boolean moveAble = true;
    private boolean attackAble = true;
    private boolean counterAttackAble = true;
    private int numOfHolyBuffs;
    private int rangeOfAttack;
    private ImpactType impactType;

    public int[][] getMoveAbleCells()
    {
        Cell[][] cells = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix();
        int[][] moveAbleCells = new int[5][9];
        currentCell.setRow(this.getRow());
        currentCell.setColumn(this.getColumn());
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if (Math.abs(this.currentCell.getRow() - i) + Math.abs(this.currentCell.getColumn() - j) < 3)
                {
                    moveAbleCells[i][j] = 1;
                }
                if (cells[i][j].isFull())
                {
                    moveAbleCells[i][j] = 0;
                }
                //todo
            }
        }
        return moveAbleCells;
    }

    public static void setNonSpellCards()
    {
        Hero.setHeroes();
        Minion.setMinions();
    }

    public int getDefaultHP()
    {
        return defaultHP;
    }

    public void setDefaultHP(int defaultHP)
    {
        this.defaultHP = defaultHP;
        this.currentHP = defaultHP;
    }

    public int getDefaultAP()
    {
        return defaultAP;
    }

    public void setDefaultAP(int defaultAP)
    {
        this.defaultAP = defaultAP;
        this.currentAP = defaultAP;
    }

    public int getCurrentHP()
    {
        return currentHP;
    }

    public int getCurrentAP()
    {
        return currentAP;
    }

    public SpecialPower getSpecialPower()
    {
        return specialPower;
    }

    public void setSpecialPower(SpecialPower specialPower)
    {
        this.specialPower = specialPower;
    }

    public boolean isMoveAble()
    {
        return moveAble;
    }

    public void setMoveAble(boolean moveAble)
    {
        this.moveAble = moveAble;
    }

    public boolean isAttackAble()
    {
        return attackAble;
    }

    public void setAttackAble(boolean attackAble)
    {
        this.attackAble = attackAble;
    }

    public boolean isCounterAttackAble()
    {
        return counterAttackAble;
    }

    public void setCounterAttackAble(boolean counterAttackAble)
    {
        this.counterAttackAble = counterAttackAble;
    }

    public void setCurrentHP(int currentHP)
    {
        this.currentHP = currentHP;
    }

    public void setCurrentAP(int currentAP)
    {
        this.currentAP = currentAP;
    }

    public ArrayList<SpellChange> getActiveSpellsOnThisCard()
    {
        return activeSpellsOnThisCard;
    }

    public void addActiveSpellOnThisCard(SpellChange activeSpellOnThisCard)
    {
        activeSpellsOnThisCard.add(activeSpellOnThisCard);
    }

    public ArrayList<ItemChange> getActiveItemsOnThisCard()
    {
        return activeItemsOnThisCard;
    }

    public void addActiveItemOnThisCard(ItemChange activeItemOnThisCard)
    {
        activeItemsOnThisCard.add(activeItemOnThisCard);
    }

    public ImpactType getImpactType()
    {
        return this.impactType;
    }

    public void setImpactType(ImpactType impactType)
    {
        this.impactType = impactType;
    }

    public int getRangeOfAttack()
    {
        return rangeOfAttack;
    }

    public void setRangeOfAttack(int rangeOfAttack)
    {
        this.rangeOfAttack = rangeOfAttack;
    }

    public Cell getCurrentCell()
    {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell)
    {
        this.currentCell = currentCell;
    }

    public int getNumOfHolyBuffs()
    {
        return numOfHolyBuffs;
    }

    public void setNumOfHolyBuffs(int numOfHolyBuffs)
    {
        this.numOfHolyBuffs = numOfHolyBuffs;
    }

    public void setDefaultAPHP(){
        this.setCurrentAP(defaultAP);
        this.setCurrentHP(defaultHP);
    }
}
