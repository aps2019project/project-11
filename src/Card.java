import java.util.ArrayList;

public abstract class Card
{
    private static ArrayList<Card> cards = new ArrayList<>();
    private boolean cardSelectedInBattle = false;
    private int cardID;
    private int price;
    private int requiredMana;
    private String cardName;
    private int row;
    private int column;

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRequiredMana() {
        return requiredMana;
    }

    public int getCardID()
    {
        return cardID;
    }

    public int getPrice()
    {
        return price;
    }

    public static Card findCard(int cardID)
    {

    }

    public static Card findCard(String cardName)
    {

    }

    public boolean isCardSelectedInBattle() {
        return cardSelectedInBattle;
    }

    public void setCardSelectedInBattle(boolean cardSelectedInBattle) {
        this.cardSelectedInBattle = cardSelectedInBattle;
    }

    public String getCardName() {
        return cardName;
    }

    public static void setCards()
    {
        Spell.setSpells();
        NonSpellCards.setNonSpellCards();
    }
}
