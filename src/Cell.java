public class Cell
{
    private int row;
    private int column;
    private Spell spell = null;
    private Hero hero = null;
    private Minion minion = null;
    private Item item = null;
    private Effect effect = null;

    public void setEffect(Effect effect)
    {
        this.effect = effect;
    }

    public void setItem(Item item)
    {
        this.item = item;
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



}
