import java.util.ArrayList;

public class Collection
{
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public void addCard(Card card)
    {
        this.cards.add(card);
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void addItem(Item item)
    {
        this.items.add(item);
    }

    private void detectID(int ID, String deckName, String command)
    {
        //find
        //add remove
    }

    public void show(){

    }

    public void searchCollection(String name){

    }

    public void createDeck(String deckName){

    }

    public void deleteDeck(String deckName){

    }

    public void help()
    {

    }
}
