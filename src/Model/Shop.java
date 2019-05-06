package Model;

import java.util.ArrayList;

public class Shop
{
    private static Shop shop = new Shop();
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
            return new Shop();
        }
        return shop;
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
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, card);
        Account.loggedInAccount.decreaseMoney(card.getPrice());
    }

    public void setCardID(Account account, Card card)
    {
        String cardID = account.getAccountName() + "_" + card.getCardName() + "_" + account.getDefaultID();
        card.setCardID(cardID);
        account.increaseDefaultID();
    }

     public void buyItem(Item item)
     {
         Account.loggedInAccount.getCollection().addItem(Account.loggedInAccount, item);
         Account.loggedInAccount.decreaseMoney(item.getPrice());
     }

    public void setItemID(Account account, Item item)
    {
        String itemID = account.getAccountName() + "_" + item.getItemName() + "_" + account.getDefaultID();
        item.setItemID(itemID);
        account.increaseDefaultID();
    }

    public void sellCard(Card cardToSell)
    {
        Account.loggedInAccount.getCollection().getCards().remove(cardToSell);
        Account.loggedInAccount.addMoney(cardToSell.getPrice());
    }

    public void sellItem(Item itemToSell)
    {
        Account.loggedInAccount.getCollection().getItems().remove(itemToSell);
        Account.loggedInAccount.addMoney(itemToSell.getPrice());
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
