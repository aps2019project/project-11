package Model;

import java.util.ArrayList;

public class Collection
{
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public Card findCardinCollection(int cardID)
    {
        for (Card card : this.getCards())
        {
            if (card.getCardID() == cardID)
            {
                return card;
            }
        }
        return null;
    }

    public Item findItemInTheCollection(int itemID)
    {
        for (Item item : this.getItems())
        {
            if (item.getItemID() == itemID)
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

    public void addCard(Card card)
    {
        this.cards.add(card);
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void addItem(Item item)
    {
        this.items.add(item);
    }
}
