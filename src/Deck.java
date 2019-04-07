import java.util.ArrayList;

public class Deck
{
    private String deckName;
    private ArrayList<Hero> hero = new ArrayList<>();
    private ArrayList<Item> item = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();

    public Deck(String deckName){

    }

    public String getDeckName() {
        return deckName;
    }

    public void deleteDeck(String deckName){

    }

    public Deck searchDeck(String deckName){
        //ToDo
        return null;
    }

    public void addCardToDeck(Card card)
    {
        //todo
        this.cards.add(card);
    }

    public void deleteCardFromDeck(Card card)
    {
        //todo
        this.cards.remove(card);
    }

    public void addItemToDeck(Item item)
    {
        //todo
        this.item.add(item);
    }

    public void deleteItemFromDeck(Item item)
    {
        //todo
        this.item.remove(item);
    }

    public void addHeroToDeck(Hero hero)
    {
        //todo
        this.hero.add(hero);
    }

    public void deleteHeroFromDeck(Hero hero)
    {
        //todo
        this.hero.remove(hero);
    }

    public ArrayList<Hero> getHero() {
        return hero;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public static void checkDeckValidity(String deckName)
    {

    }

    public static void setDeckAsMainDeck(String deckName)
    {

    }

    public static void showAllDecks()
    {
        boolean mainDeckPrintedBefore = false;
        //todo
    }

    public static void showDeck(String deckName)
    {

    }
}
