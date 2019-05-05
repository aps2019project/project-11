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
        setCardID(card);
        Account.loggedInAccount.getCollection().addCard(card);
        Account.loggedInAccount.decreaseMoney(card.getPrice());
    }

    private void setCardID(Card card)
    {
        Account account = Account.loggedInAccount;
        String cardID = account.getAccountName() + "_" + card.getCardName() + "_" + account.getDefaultID();
        card.setCardID(cardID);
        account.increaseDefaultID();
    }

     public void buyItem(Item item)
     {
         setItemID(item);
         Account.loggedInAccount.getCollection().addItem(item);
         Account.loggedInAccount.decreaseMoney(item.getPrice());
     }

    private void setItemID(Item item)
    {
        Account account = Account.loggedInAccount;
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
