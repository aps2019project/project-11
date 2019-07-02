package Model;

import java.util.ArrayList;

public class Shop
{
    private static Shop shop = null;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    private Shop()
    {
        //just added to make Shop singleton
    }

    public static Shop getInstance()
    {
        if (shop == null)
        {
            shop = new Shop();
        }
        return shop;
    }

    public static void setShop(Shop shop) {
        Shop.shop = shop;
    }

    public void addCardToShop(Card card)
    {
        this.cards.add(card);
    }

    public void addItemToShop(Item item)
    {
        this.items.add(item);
    }

    public void buyCard(Card card)
    {
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, card, false);
        Account.loggedInAccount.decreaseMoney(card.getPrice());
        card.decreaseCapacityOfSell();
    }

    public void setCardID(Account account, Card card)
    {
        String cardID = account.getAccountName() + "_" + card.getCardName() + "_" + account.getDefaultID();
        card.setCardID(cardID);
        account.increaseDefaultID();
    }

     public void buyItem(Item item)
     {
         Account.loggedInAccount.getCollection().addItem(Account.loggedInAccount, item, false);
         Account.loggedInAccount.decreaseMoney(item.getPrice());
         item.decreaseCapacityOfItemSell();
     }

    public void setItemID(Account account, Item item)
    {
        String itemID = account.getAccountName() + "_" + item.getItemName() + "_" + account.getDefaultID();
        item.setItemID(itemID);
        account.increaseDefaultID();
    }

    public void sellCard(Card cardToSell)
    {
        if (cardToSell instanceof Hero)
        {
            Account.loggedInAccount.getCollection().getHeroes().remove(cardToSell);
        }
        if (cardToSell instanceof Minion)
        {
            Account.loggedInAccount.getCollection().getMinions().remove(cardToSell);
        }
        if (cardToSell instanceof Spell)
        {
            Account.loggedInAccount.getCollection().getSpells().remove(cardToSell);
        }
        Account.loggedInAccount.addMoney(cardToSell.getPrice());
        cardToSell.increaseCapacityOfSell();
    }

    public void sellItem(Item itemToSell)
    {
        Account.loggedInAccount.getCollection().getItems().remove(itemToSell);
        Account.loggedInAccount.addMoney(itemToSell.getPrice());
        itemToSell.increaseCapacityOfItemSell();
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }
}
