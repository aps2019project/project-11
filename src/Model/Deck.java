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

    public void addCardToDeck(Card card, Account account, boolean isDefaultCard)     //Default cards are story opponents cards
    {
        if (isDefaultCard)
        {
            card.setDefaultCardID(account);
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
    }

    public void deleteCardFromDeck(Card card, Account account)
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
        if (this.equals(account.getMainDeck()))
        {
            account.setMainDeck(null);
        }
    }

    public void addItemToDeck(Item item, Account account, boolean isDefaultCard)
    {
        if (isDefaultCard)
        {
            item.setDefaultCardID(account);
        }
        this.item.add(item);
    }

    public void deleteItemFromDeck(Item item, Account account)
    {
        this.item.remove(item);
        if (this.equals(account.getMainDeck()))
        {
            account.setMainDeck(null);
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

    public static Deck createMainDeckForStoryAccount(Account storyAccount, int missionNumber)
    {
        try
        {
            switch (missionNumber)
            {
                case 1:
                    Deck deck1 = new Deck("storyDeck1");
                    deck1.addCardToDeck((Hero) (Hero.findHero("Dave White")).clone(), storyAccount, true);
                    deck1.addCardToDeck((Spell) Card.findCard("totalDisarm").clone(), storyAccount, true);
                    deck1.addCardToDeck((Spell) Card.findCard("lightingBolt").clone(), storyAccount, true);
                    deck1.addCardToDeck((Spell) Card.findCard("allDisarm").clone(), storyAccount, true);
                    deck1.addCardToDeck((Spell) Card.findCard("allPoison").clone(), storyAccount, true);
                    deck1.addCardToDeck((Spell) Card.findCard("dispel").clone(), storyAccount, true);
                    deck1.addCardToDeck((Spell) Card.findCard("sacrifice").clone(), storyAccount, true);
                    deck1.addCardToDeck((Spell) Card.findCard("shock").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("kamandarFars").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("neizedarTorani").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("GorgSefid").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("GorzdarTorani").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("GorzdarTorani").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("Divesiah").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("GhoulTakCheshm").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("MarSammi").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("MarGhoulPeykar").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("JadoGarAzam").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("Siavash").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("NaneSarma").clone(), storyAccount, true);
                    deck1.addCardToDeck((Minion) Card.findCard("ArzhangDiv").clone(), storyAccount, true);
                    deck1.addItemToDeck((Item) Item.findItem("CrownOfWisdom").clone(), storyAccount, true);
                    return deck1;
                case 2:
                    Deck deck2 = new Deck("storyDeck2");
                    deck2.addCardToDeck((Hero) Hero.findHero("Zahak").clone(), storyAccount, true);
                    deck2.addCardToDeck((Spell) Card.findCard("areaDispel").clone(), storyAccount, true);
                    deck2.addCardToDeck((Spell) Card.findCard("empower").clone(), storyAccount, true);
                    deck2.addCardToDeck((Spell) Card.findCard("godStrength"), storyAccount, true);
                    deck2.addCardToDeck((Spell) Card.findCard("madness").clone(), storyAccount, true);
                    deck2.addCardToDeck((Spell) Card.findCard("poisonLake").clone(), storyAccount, true);
                    deck2.addCardToDeck((Spell) Card.findCard("healthWithProfit").clone(), storyAccount, true);
                    deck2.addCardToDeck((Spell) Card.findCard("kingGuard").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("shamsirzanFars").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("neizedarFars").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("pahlavanFars").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("gholabsangdarTorani").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("ShahzadeTorani").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("Oghab").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("Oghab").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("EzhdehayeAtashAndaz").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("Palang").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("Genn").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("Giv").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("Iraj").clone(), storyAccount, true);
                    deck2.addCardToDeck((Minion) Card.findCard("ShahGhoul").clone(), storyAccount, true);
                    deck2.addItemToDeck((Item) Item.findItem("Soul Eater").clone(), storyAccount, true);
                    return deck2;
                case 3:
                    Deck deck3 = new Deck("storyDeck3");
                    deck3.addCardToDeck((Hero) Hero.findHero("Arash").clone(), storyAccount, true);
                    deck3.addCardToDeck((Spell) Card.findCard("hellFire").clone(), storyAccount, true);
                    deck3.addCardToDeck((Spell) Card.findCard("allDisarm").clone(), storyAccount, true);
                    deck3.addCardToDeck((Spell) Card.findCard("dispel").clone(), storyAccount, true);
                    deck3.addCardToDeck((Spell) Card.findCard("powerUp").clone(), storyAccount, true);
                    deck3.addCardToDeck((Spell) Card.findCard("allPower").clone(), storyAccount, true);
                    deck3.addCardToDeck((Spell) Card.findCard("allAttack").clone(), storyAccount, true);
                    deck3.addCardToDeck((Spell) Card.findCard("weakening").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("jasosTorani").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("kamandarTorani").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("sepahsalarFars").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("GhoulSangAndaz").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("DivGorazSavar").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("DivGorazSavar").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("GorazVahshi").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("JadoGar").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("Gorg").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("ShirDarande").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("Piran").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("Bahman").clone(), storyAccount, true);
                    deck3.addCardToDeck((Minion) Card.findCard("GhoulBozorg").clone(), storyAccount, true);
                    deck3.addItemToDeck((Item) Item.findItem("Terror Hood").clone(), storyAccount, true);
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
