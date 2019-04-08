public class Card
{
    enum typeOfCard
    {
        spell, hero, minion
    }

    private int cardID;
    private int price;

    public int getCardID()
    {
        return cardID;
    }

    public int getPrice()
    {
        return price;
    }

    public Card findCard(int cardID, String command)
    {

    }

}
