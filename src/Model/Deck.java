package Model;

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

    public Deck addNonHeroCardToDeck(Card card)
    {
        this.nonHeroCards.add(card);
        return this;
    }

    public void deleteNonHeroCardFromDeck(Card card)
    {
        this.nonHeroCards.remove(card);
    }

    public void addItemToDeck(Item item)
    {
        this.item.add(item);
    }

    public void deleteItemFromDeck(Item item)
    {
        this.item.remove(item);
    }

    public void addHeroToDeck(Hero hero)
    {
        this.hero.add(hero);
    }

    public void deleteHeroFromDeck(Hero hero)
    {
        this.hero.remove(hero);
    }

    public ArrayList<Hero> getHero()
    {
        return hero;
    }

    public ArrayList<Item> getItem()
    {
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

    public static Deck creatMainDeckForStoryAccount(int i) {
        switch (i){
            case 1:
                Deck deck = new Deck("storyAccount1");
                deck.addHeroToDeck(Hero.findHero("Dave White"));
                deck.addNonHeroCardToDeck(Card.findCard("totalDisarm")).addNonHeroCardToDeck(Card.findCard("lightingBolt")).addNonHeroCardToDeck(Card.findCard("allDisarm")).addNonHeroCardToDeck(Card.findCard("allPoison")).addNonHeroCardToDeck(Card.findCard("dispel")).addNonHeroCardToDeck(Card.findCard("sacrifice")).
                        addNonHeroCardToDeck(Card.findCard("shock"))./*todo*//*todo*/addNonHeroCardToDeck(Card.findCard("Gorzdar Torani")).addNonHeroCardToDeck(Card.findCard("Gorzdar Torani")).addNonHeroCardToDeck(Card.findCard("Dive siah")).addNonHeroCardToDeck(Card.findCard("Ghoul TakCheshm")).addNonHeroCardToDeck(Card.findCard("Mar Sammi")).
                        addNonHeroCardToDeck(Card.findCard("Mar Ghoul Peykar"))./*todo*/addNonHeroCardToDeck(Card.findCard("JadoGar Azam")).addNonHeroCardToDeck(Card.findCard("Siavash")).addNonHeroCardToDeck(Card.findCard("Nane Sarma")).addNonHeroCardToDeck(Card.findCard("Arzhang Div"));
                deck.addItemToDeck(Item.findItem("CrownOfWisdom"));
                return deck;
        }
    }
}
