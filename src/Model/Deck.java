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
        try
        {
            switch (i)
            {
                case 1:
                    Deck deck1 = new Deck("storyDeck1");
                    deck1.addHeroToDeck((Hero) (Hero.findHero("Dave White")).clone(), true);
                    deck1.addNonHeroCardToDeck((Card) Card.findCard("totalDisarm").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("lightingBolt").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("allDisarm").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("allPoison").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("dispel").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("sacrifice").clone(), true).
                            addNonHeroCardToDeck((Card) Card.findCard("shock").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("kamandarFars").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("neizedarTorani").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("GorgSefid").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Gorzdar Torani").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Gorzdar Torani").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Dive siah").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Ghoul TakCheshm").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Mar Sammi").clone(), true).
                            addNonHeroCardToDeck((Card) Card.findCard("Mar Ghoul Peykar").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("JadoGar Azam").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Siavash").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Nane Sarma").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Arzhang Div").clone(), true);
                    deck1.addItemToDeck((Item) Item.findItem("CrownOfWisdom").clone(), true);
                    return deck1;
                case 2:
                    Deck deck2 = new Deck("storyDeck2");
                    deck2.addHeroToDeck((Hero) Hero.findHero("Zahak").clone(), true);
                    deck2.addNonHeroCardToDeck((Card) Card.findCard("areaDispel").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("empower").clone(), true).addNonHeroCardToDeck(Card.findCard("godStrength"), true).addNonHeroCardToDeck((Card) Card.findCard("madness").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("poisonLake").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("healthWithProfit").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("kingGuard").clone(), true).
                            addNonHeroCardToDeck((Card) Card.findCard("shamsirzanFars").clone() ,true).addNonHeroCardToDeck((Card) Card.findCard("neizedarFars").clone() , true).addNonHeroCardToDeck((Card) Card.findCard("pahlavanFars").clone() , true).addNonHeroCardToDeck((Card) Card.findCard("gholabsangdarTorani").clone() , true).addNonHeroCardToDeck((Card) Card.findCard("Shahzade Torani").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Oghab").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Oghab").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Ezhdehaye Atash Andaz").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Palang").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Genn").clone(), true).
                            addNonHeroCardToDeck((Card) Card.findCard("Giv").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Iraj").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Shah Ghoul").clone(), true);
                    deck2.addItemToDeck((Item) Item.findItem("Soul Eater").clone(), true);
                    return deck2;
                case 3:
                    Deck deck3 = new Deck("storyDeck3");
                    deck3.addHeroToDeck((Hero) Hero.findHero("Arash").clone(), true);
                    deck3.addNonHeroCardToDeck((Card) Card.findCard("hellFire").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("allDisarm").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("dispel").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("powerUp").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("allPower").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("allAttack").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("weakening").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Ghoul Sang Andaz").clone(), true).
                            addNonHeroCardToDeck((Card) Card.findCard("Div GorazSavar").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Div GorazSavar").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Shir Darande").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Piran").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Bahman").clone(), true).addNonHeroCardToDeck((Card) Card.findCard("Ghoul Bozorg").clone(), true);
                    deck3.addItemToDeck((Item) Item.findItem("Terror Hood").clone(), true);
            }
        }
        catch (CloneNotSupportedException ignored)
        {

        }
        return null;
    }
}
