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

    public void searchCollection(String name)
    {
        for (Card card : cards)
        {
            if (card.getCardName().equals(name))
            {
                System.out.println("Card exists in the collection");
                System.out.println("Card ID : " + card.getCardID());
                return;
            }
        }
        for (Item item : items)
        {
            if (item.getItemName().equals(name))
            {
                System.out.println("Item exists in the collection");
                System.out.println("Item ID : " + item.getItemID());
                return;
            }
        }
    }

    public void createDeck(String deckName)
    {
        Deck deck = Deck.findDeck(deckName);
        if (deck != null)
        {
            System.out.println("Deck exists with this name");
            return;
        }
        Deck newDeck = new Deck(deckName);
        Account.loggedInAccount.addDeck(newDeck);
    }

    public void deleteDeck(String deckName)
    {
        Deck deck = Deck.findDeck(deckName);
        if (deck == null)
        {
            System.out.println("There is no deck with this name");
            return;
        }
        deck.deleteDeck(deck);
    }

    public void show()
    {
        int counter = 0;
        System.out.println("Heroes :");
        for (Card card : this.getCards())
        {
            if (card instanceof Hero)
            {
                ((Hero) card).printHeroStats(counter);
            }
        }

        counter = 0;
        System.out.println("Items :");
        for (Item item : this.getItems())
        {
            item.printItemStats(counter);
        }

        counter = 0;
        System.out.println("Cards :");
        for (Card card : this.getCards())
        {
            card.printCardStats(counter);
        }
    }

    public void help()
    {

    }
}
