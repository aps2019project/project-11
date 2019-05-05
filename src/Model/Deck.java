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

    public Deck addNonHeroCardToDeck(Card card, boolean isDefaultCard)     //Default cards are story opponents cards
    {
        if (isDefaultCard)
        {
            card.setDefaultCardID();
        }
        this.nonHeroCards.add(card);
        return this;
    }

    public void deleteNonHeroCardFromDeck(Card card)
    {
        this.nonHeroCards.remove(card);
    }

    public void addItemToDeck(Item item, boolean isDefaultCard)
    {
        if (isDefaultCard)
        {
            item.setDefaultCardID();
        }
        this.item.add(item);
    }

    public void deleteItemFromDeck(Item item)
    {
        this.item.remove(item);
    }

    public void addHeroToDeck(Hero hero, boolean isDefaultCard)
    {
        if (isDefaultCard)
        {
            hero.setDefaultCardID();
        }
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
                deck1.addHeroToDeck(Hero.findHero("Dave White"), true);
                deck1.addNonHeroCardToDeck(Card.findCard("totalDisarm"), true).addNonHeroCardToDeck(Card.findCard("lightingBolt"), true).addNonHeroCardToDeck(Card.findCard("allDisarm"), true).addNonHeroCardToDeck(Card.findCard("allPoison"), true).addNonHeroCardToDeck(Card.findCard("dispel"), true).addNonHeroCardToDeck(Card.findCard("sacrifice"), true).
                        addNonHeroCardToDeck(Card.findCard("shock"), true)./*todo*//*todo*/addNonHeroCardToDeck(Card.findCard("Gorzdar Torani"), true).addNonHeroCardToDeck(Card.findCard("Gorzdar Torani"), true).addNonHeroCardToDeck(Card.findCard("Dive siah"), true).addNonHeroCardToDeck(Card.findCard("Ghoul TakCheshm"), true).addNonHeroCardToDeck(Card.findCard("Mar Sammi"), true).
                        addNonHeroCardToDeck(Card.findCard("Mar Ghoul Peykar"), true)./*todo*/addNonHeroCardToDeck(Card.findCard("JadoGar Azam"), true).addNonHeroCardToDeck(Card.findCard("Siavash"), true).addNonHeroCardToDeck(Card.findCard("Nane Sarma"), true).addNonHeroCardToDeck(Card.findCard("Arzhang Div"), true);
                deck1.addItemToDeck(Item.findItem("CrownOfWisdom"), true);
                return deck1;
            case 2:
                Deck deck2 = new Deck("storyDeck2");
                deck2.addHeroToDeck(Hero.findHero("Zahak"), true);
                deck2.addNonHeroCardToDeck(Card.findCard("areaDispel"), true).addNonHeroCardToDeck(Card.findCard("empower"), true).addNonHeroCardToDeck(Card.findCard("godStrength"), true).addNonHeroCardToDeck(Card.findCard("madness"), true).addNonHeroCardToDeck(Card.findCard("poisonLake"), true).addNonHeroCardToDeck(Card.findCard("healthWithProfit"), true).addNonHeroCardToDeck(Card.findCard("kingGuard"), true).
                        /*todo*//*todo*//*todo*//*todo*/addNonHeroCardToDeck(Card.findCard("Shahzade Torani"), true).addNonHeroCardToDeck(Card.findCard("Oghab"), true).addNonHeroCardToDeck(Card.findCard("Oghab"), true).addNonHeroCardToDeck(Card.findCard("Ezhdehaye Atash Andaz"), true).addNonHeroCardToDeck(Card.findCard("Palang"), true).addNonHeroCardToDeck(Card.findCard("Genn"), true).
                        addNonHeroCardToDeck(Card.findCard("Giv"), true).addNonHeroCardToDeck(Card.findCard("Iraj"), true).addNonHeroCardToDeck(Card.findCard("Shah Ghoul"), true);
                deck2.addItemToDeck(Item.findItem("Soul Eater"), true);
                return deck2;
            case 3:
                Deck deck3 = new Deck("storyDeck3");
                deck3.addHeroToDeck(Hero.findHero("Arash"), true);
                deck3.addNonHeroCardToDeck(Card.findCard("hellFire"), true).addNonHeroCardToDeck(Card.findCard("allDisarm"), true).addNonHeroCardToDeck(Card.findCard("dispel"), true).addNonHeroCardToDeck(Card.findCard("powerUp"), true).addNonHeroCardToDeck(Card.findCard("allPower"), true).addNonHeroCardToDeck(Card.findCard("allAttack"), true).addNonHeroCardToDeck(Card.findCard("weakening"), true).addNonHeroCardToDeck(Card.findCard("Ghoul Sang Andaz"), true).
                        addNonHeroCardToDeck(Card.findCard("Div GorazSavar"), true).addNonHeroCardToDeck(Card.findCard("Div GorazSavar"), true).addNonHeroCardToDeck(Card.findCard("Shir Darande"), true).addNonHeroCardToDeck(Card.findCard("Piran"), true).addNonHeroCardToDeck(Card.findCard("Bahman"), true).addNonHeroCardToDeck(Card.findCard("Ghoul Bozorg"), true);
        }
        return null;
    }
}
