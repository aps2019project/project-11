import java.util.ArrayList;

public class Deck
{
    private String deckName;
    private ArrayList<Hero> hero = new ArrayList<>();
    private ArrayList<Item> item = new ArrayList<>();
    private ArrayList<Card> nonHeroCards = new ArrayList<>();

    public Deck(String deckName)
    {
        this.deckName = deckName;
    }

    public void deleteDeck(Deck deck)
    {
        Account.loggedInAccount.getPlayerDecks().remove(deck);
    }

    public static Deck findDeck(String deckName)
    {
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            if (deck.getDeckName().equals(deckName))
            {
                return deck;
            }
        }
        return null;
    }

    public void addNonHeroCardToDeck(Card card)
    {
        for (Card deckCard : nonHeroCards)
        {
            if (card.getCardName().equals(deckCard.getCardName()))
            {
                System.out.println("This card is in the deck");
                return;
            }
        }
        if (nonHeroCards.size() == 20)
        {
            System.out.println("Deck is full");
            return;
        }
        this.nonHeroCards.add(card);
    }

    public void deleteNonHeroCardFromDeck(Card card)
    {
        for (Card deckCard : nonHeroCards)
        {
            if (card.getCardName().equals(deckCard.getCardName()))
            {
                this.nonHeroCards.remove(card);
                return;
            }
        }
        System.out.println("This card isn't in the deck");
    }

    public void addItemToDeck(Item item)
    {
        for (Item deckItem : this.item)
        {
            if (item.getItemID() == deckItem.getItemID())
            {
                System.out.println("This item is in the deck");
                return;
            }
        }
        if (this.item.size() == 1)
        {
            System.out.println("Deck is full");
            return;
        }
        this.item.add(item);
    }

    public void deleteItemFromDeck(Item item)
    {
        for (Item deckItem : this.item)
        {
            if (item.getItemID() == deckItem.getItemID())
            {
                this.item.remove(item);
                return;
            }
        }
        System.out.println("This item isn't in the deck");
    }

    public void addHeroToDeck(Hero hero)
    {
        for (Hero deckHero : this.hero)
        {
            if (hero.getHeroID() == deckHero.getHeroID())
            {
                System.out.println("This hero is in the deck");
                return;
            }
        }
        if (this.hero.size() == 1)
        {
            System.out.println("Deck is full");
            return;
        }
        this.hero.add(hero);
    }

    public void deleteHeroFromDeck(Hero hero)
    {
        for (Hero deckHero : this.hero)
        {
            if (hero.getHeroID() == deckHero.getHeroID())
            {
                this.hero.remove(hero);
                return;
            }
        }
        System.out.println("This hero isn't in the deck");
    }

    public static boolean checkDeckValidity(String deckName)
    {
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            if (deck.getDeckName().equals(deckName))
            {
                if (deck.nonHeroCards.size() == 20 && deck.hero.size() == 1)
                {
                    System.out.println("Deck is valid");
                    return true;
                }
                else
                {
                    System.out.println("Deck isn't valid");
                    return false;
                }
            }
        }
        System.out.println("Deck doesn't exist");
        return false;
    }

    public static void setDeckAsMainDeck(String deckName)
    {
        if (checkDeckValidity(deckName))
        {
            Deck deck = findDeck(deckName);
            Account.loggedInAccount.setMainDeck(deck);
            System.out.println("MainDeck changed");
        }
    }

    public static void showAllDecks()
    {
        int counter = 0;
        Deck mainDeck = Account.loggedInAccount.getMainDeck();
        if (mainDeck != null)
        {
            System.out.println(counter + " : " + mainDeck.getDeckName() + " : ");
            mainDeck.printDeckStats();
            counter ++;
        }
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            if (deck.equals(mainDeck))
            {
                continue;
            }
            System.out.println(counter + " : " + deck.getDeckName() + " : ");
            deck.printDeckStats();
            counter ++;
        }
    }

    public static void showDeck(String deckName)
    {
        Deck deck = findDeck(deckName);
        if (deck != null)
        {
            deck.printDeckStats();
            return;
        }
        System.out.println("Deck doesn't exist");
    }

    public void printDeckStats()
    {
        int counter = 0;
        System.out.println("Heroes :");
        for (Hero hero : hero)
        {
            hero.printHeroStats(counter);
            counter ++;
        }

        counter = 0;
        System.out.println("Items :");
        for (Item item : item)
        {
            item.printItemStats(counter);
            counter ++;
        }

        counter = 0;
        System.out.println("Cards :");
        for (Card card : nonHeroCards)
        {
            card.printCardStats(counter);
            counter ++;
        }
    }

    public ArrayList<Hero> getHero() {
        return hero;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public ArrayList<Card> getNonHeroCards()
    {
        return nonHeroCards;
    }

    public String getDeckName()
    {
        return deckName;
    }
}
