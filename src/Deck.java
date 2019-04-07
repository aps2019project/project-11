import java.util.ArrayList;

public class Deck
{
    private String deckName;
    private ArrayList<Hero> hero = new ArrayList<>(1);
    private ArrayList<Item> item = new ArrayList<>(1);
    private ArrayList<Card> cards = new ArrayList<>(20);

    public Deck(String deckName){

    }

    public String getDeckName() {
        return deckName;
    }

    public void deleteDeck(String deckName){

    }

    public Deck seracDeck(String deckName){
        //ToDo
        return null;
    }

    public ArrayList<Hero> getHero() {
        return hero;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
