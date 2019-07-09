package Model;

import java.util.ArrayList;

public class Shop
{
    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<Minion> minions = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public void addCardToShop(Card card)
    {
        if (card instanceof Hero)
        {
            this.getHeroes().add((Hero) card);
        }
        if (card instanceof Minion)
        {
            this.getMinions().add((Minion) card);
        }
        if (card instanceof Spell)
        {
            this.getSpells().add((Spell) card);
        }
    }

    public void addItemToShop(Item item)
    {
        this.items.add(item);
    }

    public void buyCard(Card card, Account account, boolean isBidCard) throws Exception
    {
        account.getCollection().addCard(account, (Card) card.clone(), false);
        if (isBidCard)
        {
            account.decreaseMoney(card.getBidWinnerPrice());
        }
        else
        {
            account.decreaseMoney(card.getPrice());
        }

        card.decreaseCapacityOfSell();
    }

    public void setCardID(Account account, Card card)
    {
        String cardID = account.getAccountName() + "_" + card.getCardName() + "_" + account.getDefaultID();
        card.setCardID(cardID);
        account.increaseDefaultID();
    }

    public void buyItem(Item item, Account account, boolean isBidItem) throws Exception
    {
        account.getCollection().addItem(account, (Item) item.clone(), false);
        if (isBidItem)
        {
            account.decreaseMoney(item.getBidWinnerPrice());
        }
        else
        {
            account.decreaseMoney(item.getPrice());
        }
        item.decreaseCapacityOfItemSell();
    }

    public void setItemID(Account account, Item item)
    {
        String itemID = account.getAccountName() + "_" + item.getItemName() + "_" + account.getDefaultID();
        item.setItemID(itemID);
        account.increaseDefaultID();
    }

    public void sellCard(Card cardToSell, Account account)
    {
        if (cardToSell instanceof Hero)
        {
            account.getCollection().getHeroes().remove(cardToSell);
        }
        if (cardToSell instanceof Minion)
        {
            account.getCollection().getMinions().remove(cardToSell);
        }
        if (cardToSell instanceof Spell)
        {
            account.getCollection().getSpells().remove(cardToSell);
        }
        account.addMoney(cardToSell.getPrice());
        cardToSell.increaseCapacityOfSell();
    }

    public void sellItem(Item itemToSell, Account account)
    {
        account.getCollection().getItems().remove(itemToSell);
        account.addMoney(itemToSell.getPrice());
        itemToSell.increaseCapacityOfItemSell();
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

    public ArrayList<Item> getItems()
    {
        return items;
    }
}
