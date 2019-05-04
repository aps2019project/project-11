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

    public static Deck createMainDeckForStoryAccount(int i)
    {
        switch (i)
        {
            case 1:
                Deck deck1 = new Deck("storyDeck1");
                deck1.addHeroToDeck(Hero.findHero("Dave White"));
                deck1.addNonHeroCardToDeck(Card.findCard("totalDisarm")).addNonHeroCardToDeck(Card.findCard("lightingBolt")).addNonHeroCardToDeck(Card.findCard("allDisarm")).addNonHeroCardToDeck(Card.findCard("allPoison")).addNonHeroCardToDeck(Card.findCard("dispel")).addNonHeroCardToDeck(Card.findCard("sacrifice")).
                        addNonHeroCardToDeck(Card.findCard("shock"))./*todo*//*todo*/addNonHeroCardToDeck(Card.findCard("Gorzdar Torani")).addNonHeroCardToDeck(Card.findCard("Gorzdar Torani")).addNonHeroCardToDeck(Card.findCard("Dive siah")).addNonHeroCardToDeck(Card.findCard("Ghoul TakCheshm")).addNonHeroCardToDeck(Card.findCard("Mar Sammi")).
                        addNonHeroCardToDeck(Card.findCard("Mar Ghoul Peykar"))./*todo*/addNonHeroCardToDeck(Card.findCard("JadoGar Azam")).addNonHeroCardToDeck(Card.findCard("Siavash")).addNonHeroCardToDeck(Card.findCard("Nane Sarma")).addNonHeroCardToDeck(Card.findCard("Arzhang Div"));
                deck1.addItemToDeck(Item.findItem("CrownOfWisdom"));
                return deck1;
            case 2:
                Deck deck2 = new Deck("storyDeck2");
                deck2.addHeroToDeck(Hero.findHero("Zahak"));
                deck2.addNonHeroCardToDeck(Card.findCard("areaDispel")).addNonHeroCardToDeck(Card.findCard("empower")).addNonHeroCardToDeck(Card.findCard("godStrength")).addNonHeroCardToDeck(Card.findCard("madness")).addNonHeroCardToDeck(Card.findCard("poisonLake")).addNonHeroCardToDeck(Card.findCard("healthWithProfit")).addNonHeroCardToDeck(Card.findCard("kingGuard")).
                        /*todo*//*todo*//*todo*//*todo*/addNonHeroCardToDeck(Card.findCard("Shahzade Torani")).addNonHeroCardToDeck(Card.findCard("Oghab")).addNonHeroCardToDeck(Card.findCard("Oghab")).addNonHeroCardToDeck(Card.findCard("Ezhdehaye Atash Andaz")).addNonHeroCardToDeck(Card.findCard("Palang")).addNonHeroCardToDeck(Card.findCard("Genn")).
                        addNonHeroCardToDeck(Card.findCard("Giv")).addNonHeroCardToDeck(Card.findCard("Iraj")).addNonHeroCardToDeck(Card.findCard("Shah Ghoul"));
                deck2.addItemToDeck(Item.findItem("Soul Eater"));
                return deck2;
            case 3:
                Deck deck3 = new Deck("storyDeck3");
                deck3.addHeroToDeck(Hero.findHero("Arash"));
                deck3.addNonHeroCardToDeck(Card.findCard("hellFire")).addNonHeroCardToDeck(Card.findCard("allDisarm")).addNonHeroCardToDeck(Card.findCard("dispel")).addNonHeroCardToDeck(Card.findCard("powerUp")).addNonHeroCardToDeck(Card.findCard("allPower")).addNonHeroCardToDeck(Card.findCard("allAttack")).addNonHeroCardToDeck(Card.findCard("weakening")).addNonHeroCardToDeck(Card.findCard("Ghoul Sang Andaz")).
                        addNonHeroCardToDeck(Card.findCard("Div GorazSavar")).addNonHeroCardToDeck(Card.findCard("Div GorazSavar")).addNonHeroCardToDeck(Card.findCard("Shir Darande")).addNonHeroCardToDeck(Card.findCard("Piran")).addNonHeroCardToDeck(Card.findCard("Bahman")).addNonHeroCardToDeck(Card.findCard("Ghoul Bozorg"));
        }
        return null;
    }
}
