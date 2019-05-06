package Model;

import java.util.ArrayList;

public class Collection
{
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public Card findCardinCollection(String cardID)
    {
        for (Card card : this.getCards())
        {
            if (card.getCardID().equals(cardID))
            {
                return card;
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

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public void addCard(Account account, Card card)
    {
        Shop.getInstance().setCardID(account, card);
        this.cards.add(card);
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void addItem(Account account, Item item)
    {
        Shop.getInstance().setItemID(account, item);
        this.items.add(item);
    }
}
