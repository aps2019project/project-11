package Model;

import java.util.ArrayList;

public class Cell
{
    private int row;
    private int column;
    private Hero hero = null;
    private Minion minion = null;
    private boolean holyCell = false;
    private boolean toxicCell = false;
    private boolean fieryCell = false;
    private ArrayList<SpellChange> spellChanges = new ArrayList<>();

    public void setCard(Card card)
    {
        if (card instanceof Hero)
        {
            this.hero = (Hero) card;
        }
        else if (card instanceof Minion)
        {
            this.minion = (Minion) card;
        }
    }

    public Card getCard()
    {
        if (this.hero != null)
        {
            return hero;
        }
        if (this.minion != null)
        {
            return minion;
        }
        return null;
    }

    public int getDistance (Cell cell)
    {
        return Math.abs(this.getRow() - cell.getRow()) + Math.abs(this.getColumn() - cell.getColumn());
    }

    public boolean isFull()
    {
        if (this.getCard() == null)
        {
            return false;
        }
        return true;
    }

    public  boolean isHolyCell()
    {
        return holyCell;
    }

    public void setHolyCell(boolean holyCell)
    {
        this.holyCell = holyCell;
    }

    public boolean isToxicCell()
    {
        return toxicCell;
    }

    public void setToxicCell(boolean toxicCell)
    {
        this.toxicCell = toxicCell;
    }

    public boolean isFieryCell()
    {
        return fieryCell;
    }

    public void setFieryCell(boolean fieryCell)
    {
        this.fieryCell = fieryCell;
    }

    public ArrayList<SpellChange> getSpellChanges() {
        return spellChanges;
    }

    public void setSpellChanges(ArrayList<SpellChange> spellChanges)
    {
        this.spellChanges = spellChanges;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
