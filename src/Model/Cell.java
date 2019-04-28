package Model;

public class Cell
{
    private int row;
    private int column;
    private Spell spell = null;
    private Hero hero = null;
    private Minion minion = null;
    private Item item = null;
    private boolean holyCell = false;
    private boolean toxicCell = false;
    private boolean fieryCell = false;

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setCard(Card card)
    {
        if (card instanceof Hero)
        {
            this.hero = (Hero) card;
        }
        else if (card instanceof Spell)
        {
            this.spell = (Spell) card;
        }
        else
        {
            this.minion = (Minion) card;
        }
    }

    public Card getCard()
    {
        if (this.spell != null)
        {
            return spell;
        }
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

    public boolean isHolyCell()
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
}
