package Model;

public abstract class Card implements Cloneable
{
    private boolean cardSelectedInBattle = false;
    private int cardID;
    private int price;
    private int requiredMP;
    private String cardName;
    private int row;
    private int column;
    private static int defaultHeroID = 11000;
    private static int defaultMinionID = 21000;
    private static int defaultSpellID = 31000;
    private static int defaultItemID = 41000;

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

    public int getRequiredMP() {
        return requiredMP;
    }

    public int getCardID()
    {
        return cardID;
    }

    public int getPrice()
    {
        return price;
    }

    public static Card findCard(String cardName)
    {
        for (Card card : Shop.getInstance().getCards())
        {
            if (card.getCardName().equals(cardName))
            {
                return card;
            }
        }
        return null;
    }

    public boolean isCardSelectedInBattle() {
        return cardSelectedInBattle;
    }

    public void setCardSelectedInBattle(boolean cardSelectedInBattle)
    {
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

    public void printCardStats(int counter)
    {
        if (this instanceof Spell)
        {
            ((Spell) this).printSpellCardStats(counter);
        }
        else if (this instanceof Minion)
        {
            ((Minion) this).printMinionStats(counter);
        }
    }

    public void printCardStats()
    {
        if (this instanceof Spell)
        {
            ((Spell) this).printSpellCardStats();
        }
        else if (this instanceof Minion)
        {
            ((Minion) this).printMinionStats();
        }
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public void setRequiredMP(int requiredMP) {
        this.requiredMP = requiredMP;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardID(int cardID)
    {
        this.cardID = cardID;
    }

    public static int findDestination(Card card1, Card card2)
    {
        return Math.abs(card1.getRow() - card2.getRow()) + Math.abs(card1.getColumn() - card2.getColumn());
    }

    public static boolean checkNeighborhood(Card card1 , Card card2){
        int[][] matrix = new int[5][9];
        int row = card1.getRow();
        int column = card1.getColumn();
        matrix[row][column] = 1;
        for(int rowCounter = row - 1 ; rowCounter <= row + 1 ; rowCounter++) {
            for (int columnCounter = column - 1; columnCounter <= column + 1; columnCounter++) {
                matrix [rowCounter][columnCounter] = 1;
            }
        }
        return matrix[card2.getRow()][card2.getColumn()] == 1;
    }

    @Override
    public Object clone()throws CloneNotSupportedException
    {
        return super.clone();
    }

    public void setDefaultCardID(){
        if (this instanceof Hero)
        {
            this.setCardID(defaultHeroID ++);
        }
        else if (this instanceof Minion)
        {
            this.setCardID(defaultMinionID ++);
        }
        else if (this instanceof Spell)
        {
            this.setCardID(defaultSpellID ++);
        }
    }
}
