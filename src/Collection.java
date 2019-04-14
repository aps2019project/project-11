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

    public void detectID(int ID, String deckName, String command)
    {
        Deck deck = Deck.findDeck(deckName);
        if (deck == null)
        {
            System.out.println("There is no deck with this name");
            return;
        }
        if (command.equals("add"))
        {
            checkIDValidityToAddToDeck(deck, ID);
        }
        else if (command.equals("remove"))
        {
            checkIDValidityToRemoveFromDeck(deck, ID);
        }
    }

    public void checkIDValidityToAddToDeck(Deck deck, int ID)
    {
        if (Card.findCard(ID) != null)
        {
            for (Card card : cards)
            {
                if (ID == card.getCardID())
                {
                    if (card instanceof Hero)
                    {
                        deck.addHeroToDeck((Hero) card);
                    }
                    else
                    {
                        deck.addNonHeroCardToDeck(card);
                    }
                    return;
                }
            }
            System.out.println("This card isn't in the collection");
        }
        if (Item.findItem(ID) != null)
        {
            for (Item item : items)
            {
                if (ID == item.getItemID())
                {
                    deck.addItemToDeck(item);
                    return;
                }
            }
            System.out.println("This item isn't in the collection");
        }
    }

    public void checkIDValidityToRemoveFromDeck(Deck deck, int ID)
    {
        if (Card.findCard(ID) != null)
        {
            for (Card card : cards)
            {
                if (ID == card.getCardID())
                {
                    if (card instanceof Hero)
                    {
                        deck.deleteHeroFromDeck((Hero) card);
                    }
                    else
                    {
                        deck.deleteNonHeroCardFromDeck(card);
                    }
                    return;
                }
            }
            System.out.println("This card isn't in the collection");
        }
        if (Item.findItem(ID) != null)
        {
            for (Item item : items)
            {
                if (ID == item.getItemID())
                {
                    deck.deleteItemFromDeck(item);
                    return;
                }
            }
            System.out.println("This item isn't in the collection");
        }
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
                counter ++;
            }
        }

        counter = 0;
        System.out.println("Items :");
        for (Item item : this.getItems())
        {
            item.printItemStats(counter);
            counter ++;
        }

        counter = 0;
        System.out.println("Cards :");
        for (Card card : this.getCards())
        {
            if (card instanceof Hero)
            {
                continue;
            }
            card.printCardStats(counter);
            counter ++;
        }
    }

    public static void help()
    {
        System.out.println("exit");
        System.out.println("show");
        System.out.println("search [cardName | itemName]");
        System.out.println("save");
        System.out.println("create deck [deckName]");
        System.out.println("delete deck [deckName]");
        System.out.println("add [cardID | heroID | itemID] to deck [deckName]");
        System.out.println("remove [cardID | heroID | itemID] from deck [deckName]");
        System.out.println("validate deck [deckName]");
        System.out.println("select deck [deckName]");
        System.out.println("show all decks");
        System.out.println("show deck [deckName]");
        System.out.println("help");
    }
}
