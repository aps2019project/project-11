package Model;

import java.util.ArrayList;

public class Shop
{
    public static Shop shop = new Shop();
    private static int heroID = 10000;
    private static int minionID = 20000;
    private static int spellID = 30000;
    private static int itemID = 40000;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public void buyCard(Card card)
    {
        setCardID(card);
        Account.loggedInAccount.getCollection().addCard(card);
        Account.loggedInAccount.decreaseMoney(card.getPrice());
    }

    private void setCardID(Card card)
    {
        if (card instanceof Hero)
        {
            card.setCardID(heroID ++);
        }
        else if (card instanceof Minion)
        {
            card.setCardID(minionID ++);
        }
        else if (card instanceof Spell)
        {
            card.setCardID(spellID ++);
        }
    }

     public void buyItem(Item item)
     {
         setItemID(item);
         Account.loggedInAccount.getCollection().addItem(item);
         Account.loggedInAccount.decreaseMoney(item.getPrice());
     }

    private void setItemID(Item item)
    {
        item.setItemID(itemID ++);
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
