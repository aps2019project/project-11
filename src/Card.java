public class Card
{


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

    enum typeOfCard
    {
        spell, hero, minion
    }
    private boolean cardSelectedInBattle = false;

    private int cardID;
    private int price;
    private String cardName;
    private int row;
    private int column;

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


}
