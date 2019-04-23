package Model;

import Controller.CollectionManager;
import View.ShowOutput;

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

    public void searchShop(String name)
    {
        for (Card card : cards)
        {
            if (card.getCardName().equals(name))
            {
                System.out.println("The card exists in the shop.");
                System.out.println("CardID : " + card.getCardID());
                return;
            }
        }
        for (Item item : items) {
            if (item.getItemName().equals(name)) {
                System.out.println("The item exists in the shop.");
                System.out.println("ItemID : " + item.getItemID());
                return;
            }
        }
        System.out.println("The item  or card doesn't exist in the shop.");
    }

    public void searchCollection(String name)
    {
        new CollectionManager().searchCollection(name);
    }

    public void buyCard(Card card)
    {
        if (card.getPrice() > Account.loggedInAccount.getMoney())
        {
            System.out.println("you haven't enough money.");
        }
        else
        {
            setCardID(card);
            Account.loggedInAccount.getCollection().addCard(card);
            Account.loggedInAccount.decreaseMoney(card.getPrice());
            System.out.println("Successful purchase");
        }
    }

    private void setCardID(Card card)
    {
        if (card instanceof Hero)
        {
            card.setCardID(heroID ++);
        } else if (card instanceof Minion)
        {
            card.setCardID(minionID ++);
        } else if (card instanceof Spell)
        {
            card.setCardID(spellID ++);
        }
    }

     public void buyItem(Item item)
     {
        if (item.getPrice() > Account.loggedInAccount.getMoney()) {
            System.out.println("you haven't enough money.");
        } else if (Account.loggedInAccount.getCollection().getItems().size() == 3) {
            System.out.println("You have 3 items in your collection and you can't buy another item.");
        } else {
            setItemID(item);
            Account.loggedInAccount.getCollection().addItem(item);
            Account.loggedInAccount.decreaseMoney(item.getPrice());
            System.out.println("Successful purchase");
        }
    }

    private void setItemID(Item item)
    {
        item.setItemID(itemID ++);
    }

    public void detectIDToSell(int ID) {
        Card cardToSell = null;
        Item itemToSell = null;
        for (Card card : Account.loggedInAccount.getCollection().getCards()) {
            if (card.getCardID() == ID) {
                cardToSell = card;
            }
        }
        for (Item item : Account.loggedInAccount.getCollection().getItems()) {
            if (item.getItemID() == ID) {
                itemToSell = item;
            }
        }
        if (cardToSell == null && itemToSell == null) {
            System.out.println("You haven't Card or Item with this ID!");
            return;
        }
        this.sell(cardToSell, itemToSell);
    }

    private void sell(Card cardToSell, Item itemToSell)
    {
        if (cardToSell != null)
        {
            for (Deck deck : Account.loggedInAccount.getPlayerDecks())
            {
                if (cardToSell instanceof Hero)
                {
                    deck.deleteHeroFromDeck((Hero) cardToSell);
                } else
                    {
                    deck.deleteNonHeroCardFromDeck(cardToSell);
                }
            }
            Account.loggedInAccount.getCollection().getCards().remove(cardToSell);
            Account.loggedInAccount.addMoney(cardToSell.getPrice());
            System.out.println("Successful Sale");
        } else {
            for (Deck deck : Account.loggedInAccount.getPlayerDecks()) {
                deck.deleteItemFromDeck(itemToSell);
            }
            Account.loggedInAccount.getCollection().getItems().remove(itemToSell);
            Account.loggedInAccount.addMoney(itemToSell.getPrice());
            System.out.println("Successful Sale");
        }
    }

    public void show()
    {
        int counter = 1;
        for (Hero hero : Hero.getHeroes())
        {
            ShowOutput.printHeroStats(hero, counter);
            counter ++;
        }
        counter = 1;
        for (Item item : Item.getItems())
        {
            item.printItemStats(counter);
            counter ++;
        }
        counter = 1;
        for (Card card : Card.getCards())
        {
            if (card instanceof Spell)
            {
                Spell spell = (Spell) card;
                spell.printSpellCardStats(counter);
                counter ++;
            } else if (card instanceof Minion)
            {
                Minion minion = (Minion) card;
                minion.printMinionStats(counter);
                counter ++;
            }
        }
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
