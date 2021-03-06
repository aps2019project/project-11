package Model;

import Network.Server;

import java.util.ArrayList;

public class Collection
{
    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<Minion> minions = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public Card findCardinCollection(String cardID)
    {
        for (Hero hero : getHeroes())
        {
            if (hero.getCardID().equals(cardID))
            {
                return hero;
            }
        }
        for (Spell spell : getSpells())
        {
            if (spell.getCardID().equals(cardID))
            {
                return spell;
            }
        }
        for (Minion minion : getMinions())
        {
            if (minion.getCardID().equals(cardID))
            {
                return minion;
            }
        }
        return null;
    }

    public Card findCardinCollectionByName(String cardName)
    {
        for (Hero hero : getHeroes())
        {
            if (hero.getCardName().equals(cardName))
            {
                return hero;
            }
        }
        for (Spell spell : getSpells())
        {
            if (spell.getCardName().equals(cardName))
            {
                return spell;
            }
        }
        for (Minion minion : getMinions())
        {
            if (minion.getCardName().equals(cardName))
            {
                return minion;
            }
        }
        return null;
    }


    public Item findItemInTheCollection(String itemID)
    {
        for (Item item : this.getItems())
        {
            if (item.getItemID().equals(itemID))
            {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Hero> getHeroes()
    {
        return heroes;
    }

    public ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public ArrayList<Spell> getSpells()
    {
        return spells;
    }

    public void addCard(Account account, Card card, boolean isImported)
    {
        if (!isImported)
        {
            Server.getShop().setCardID(account, card);
        }
        if (card instanceof Hero)
        {
            heroes.add((Hero) card);
        }
        else if (card instanceof Minion)
        {
            minions.add((Minion) card);
        }
        else
        {
            spells.add((Spell) card);
        }
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void addItem(Account account, Item item, boolean isImported)
    {
        if (!isImported)
        {
            Server.getShop().setItemID(account, item);
        }
        this.items.add(item);
    }
}
