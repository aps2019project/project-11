package Controller;

import Model.*;
import View.*;

public class ShopManager
{
    private ShowOutput showOutput = ShowOutput.getInstance();

    public void searchShop(String name)
    {
        for (Card card : Shop.getInstance().getCards())
        {
            if (card.getCardName().equals(name))
            {
                showOutput.printOutput("The card exists in the shop.");
                return;
            }
        }
        for (Item item : Shop.getInstance().getItems())
        {
            if (item.getItemName().equals(name))
            {
                showOutput.printOutput("The item exists in the shop.");
                return;
            }
        }
        showOutput.printOutput("The item or card doesn't exist in the shop.");
    }

    public void buyCard(Card card)
    {
        if (card.getPrice() > Account.loggedInAccount.getMoney())
        {
            showOutput.printOutput("you haven't enough money.");
        }
        else
        {
            Shop.getInstance().buyCard(card);
            showOutput.printOutput("Successful purchase");
        }
    }

    public void buyItem(Item item)
    {
        if (item.getPrice() > Account.loggedInAccount.getMoney())
        {
            showOutput.printOutput("you haven't enough money.");
        }
        else if (Account.loggedInAccount.getCollection().getItems().size() == 3)
        {
            showOutput.printOutput("You have 3 items in your collection and you can't buy another item.");
        }
        else
        {
            Shop.getInstance().buyItem(item);
            showOutput.printOutput("Successful purchase");
        }
    }

    public void detectIDToSell(String ID)
    {
        for (Hero hero : Account.loggedInAccount.getCollection().getHeroes())
        {
            if (hero.getCardID().equals(ID))
            {
                removeCardFromDeckToSell(hero);
                return;
            }
        }
        for (Minion minion : Account.loggedInAccount.getCollection().getMinions())
        {
            if (minion.getCardID().equals(ID))
            {
                removeCardFromDeckToSell(minion);
                return;
            }
        }
        for (Spell spell : Account.loggedInAccount.getCollection().getSpells())
        {
            if (spell.getCardID().equals(ID))
            {
                removeCardFromDeckToSell(spell);
                return;
            }
        }
        for (Item item : Account.loggedInAccount.getCollection().getItems())
        {
            if (item.getItemID().equals(ID))
            {
                removeItemFromDeckToSell(item);
                return;
            }
        }
        showOutput.printOutput("You haven't Card or Item with this ID!");
    }

    private void removeCardFromDeckToSell(Card cardToSell)
    {
        new DeckManager().searchDecksToRemoveCardOnSale(cardToSell);
        Shop.getInstance().sellCard(cardToSell);
        showOutput.printOutput("Successful Sale");
    }

    private void removeItemFromDeckToSell(Item itemToSell)
    {
        new DeckManager().searchDecksToRemoveItemOnSale(itemToSell);
        Shop.getInstance().sellItem(itemToSell);
        showOutput.printOutput("Successful Sale");
    }
}
