package Model;

import java.util.ArrayList;

public class Deck
{
    private String deckName;
    private ArrayList<Hero> hero = new ArrayList<>();
    private ArrayList<Minion> minions = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<>();
    private ArrayList<Item> item = new ArrayList<>();

    public Deck(String deckName)
    {
        this.deckName = deckName;
    }

    public Deck addCardToDeck(Card card, boolean isDefaultCard)     //Default cards are story opponents cards
    {
        if (isDefaultCard)
        {
            card.setDefaultCardID();
        }
        if (card instanceof Minion)
        {
            minions.add((Minion) card);
        }
        else if (card instanceof Spell)
        {
            spells.add((Spell) card);
        }
        else
        {
            hero.add((Hero) card);
        }
        return this;
    }

    public void deleteCardFromDeck(Card card)
    {
        if (card instanceof Minion)
        {
            minions.remove(card);
        }
        else if (card instanceof Spell)
        {
            spells.remove(card);
        }
        else if (card instanceof Hero)
        {
            hero.remove(card);
        }
        if (this.equals(Account.loggedInAccount.getMainDeck()))
        {
            Account.loggedInAccount.setMainDeck(null);
        }
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
        if (this.equals(Account.loggedInAccount.getMainDeck()))
        {
            Account.loggedInAccount.setMainDeck(null);
        }
    }

    public ArrayList<Hero> getHero()
    {
        return hero;
    }

    public ArrayList<Item> getItem()
    {
        return item;
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
                    deck1.addCardToDeck((Hero) (Hero.findHero("Dave White")).clone(), true);
                    deck1.addCardToDeck((Spell) Card.findCard("totalDisarm").clone(), true);
                    deck1.addCardToDeck((Spell) Card.findCard("lightingBolt").clone(), true);
                    deck1.addCardToDeck((Spell) Card.findCard("allDisarm").clone(), true);
                    deck1.addCardToDeck((Spell) Card.findCard("allPoison").clone(), true);
                    deck1.addCardToDeck((Spell) Card.findCard("dispel").clone(), true);
                    deck1.addCardToDeck((Spell) Card.findCard("sacrifice").clone(), true);
                    deck1.addCardToDeck((Spell) Card.findCard("shock").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("kamandarFars").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("neizedarTorani").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("GorgSefid").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("GorzdarTorani").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("GorzdarTorani").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("Divesiah").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("GhoulTakCheshm").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("MarSammi").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("MarGhoulPeykar").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("JadoGarAzam").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("Siavash").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("NaneSarma").clone(), true);
                    deck1.addCardToDeck((Minion) Card.findCard("ArzhangDiv").clone(), true);
                    deck1.addItemToDeck((Item) Item.findItem("CrownOfWisdom").clone(), true);
                    return deck1;
                case 2:
                    Deck deck2 = new Deck("storyDeck2");
                    deck2.addCardToDeck((Hero) Hero.findHero("Zahak").clone(), true);
                    deck2.addCardToDeck((Spell) Card.findCard("areaDispel").clone(), true);
                    deck2.addCardToDeck((Spell) Card.findCard("empower").clone(), true);
                    deck2.addCardToDeck((Spell) Card.findCard("godStrength"), true);
                    deck2.addCardToDeck((Spell) Card.findCard("madness").clone(), true);
                    deck2.addCardToDeck((Spell) Card.findCard("poisonLake").clone(), true);
                    deck2.addCardToDeck((Spell) Card.findCard("healthWithProfit").clone(), true);
                    deck2.addCardToDeck((Spell) Card.findCard("kingGuard").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("shamsirzanFars").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("neizedarFars").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("pahlavanFars").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("gholabsangdarTorani").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("ShahzadeTorani").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("Oghab").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("Oghab").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("EzhdehayeAtashAndaz").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("Palang").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("Genn").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("Giv").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("Iraj").clone(), true);
                    deck2.addCardToDeck((Minion) Card.findCard("ShahGhoul").clone(), true);
                    deck2.addItemToDeck((Item) Item.findItem("Soul Eater").clone(), true);
                    return deck2;
                case 3:
                    Deck deck3 = new Deck("storyDeck3");
                    deck3.addCardToDeck((Hero) Hero.findHero("Arash").clone(), true);
                    deck3.addCardToDeck((Spell) Card.findCard("hellFire").clone(), true);
                    deck3.addCardToDeck((Spell) Card.findCard("allDisarm").clone(), true);
                    deck3.addCardToDeck((Spell) Card.findCard("dispel").clone(), true);
                    deck3.addCardToDeck((Spell) Card.findCard("powerUp").clone(), true);
                    deck3.addCardToDeck((Spell) Card.findCard("allPower").clone(), true);
                    deck3.addCardToDeck((Spell) Card.findCard("allAttack").clone(), true);
                    deck3.addCardToDeck((Spell) Card.findCard("weakening").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("jasosTorani").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("kamandarTorani").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("sepahsalarFars").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("GhoulSangAndaz").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("DivGorazSavar").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("DivGorazSavar").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("GorazVahshi").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("JadoGar").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("Gorg").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("ShirDarande").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("Piran").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("Bahman").clone(), true);
                    deck3.addCardToDeck((Minion) Card.findCard("GhoulBozorg").clone(), true);
                    deck3.addItemToDeck((Item) Item.findItem("Terror Hood").clone(), true);
                    return deck3;
            }
        }
        catch (CloneNotSupportedException ignored)
        {

        }
        return null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Deck)
        {
            if (((Deck) obj).getDeckName().equals(this.getDeckName()))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public ArrayList<Spell> getSpells()
    {
        return spells;
    }

    public void setDeckName(String deckName)
    {
        this.deckName = deckName;
    }
}
