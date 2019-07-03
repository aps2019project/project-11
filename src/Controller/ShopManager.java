package Controller;

import Model.*;
import View.*;

public class ShopManager
{
    private ShowOutput showOutput = ShowOutput.getInstance();

    public void buyCard(Card card) throws Exception
    {
        if (card.getPrice() > Account.loggedInAccount.getMoney())
        {
            showOutput.printOutput("you don't have enough money.");
        }
        else if (card.getCapacityOfSell() <= 0)
        {
            showOutput.printOutput("this card has no capacity to buy");
        }
        else
        {
            Shop.getInstance().buyCard(card);
            showOutput.printOutput("Successful purchase");
            showOutput.printOutput(card.getCardName() + "'s capacity is " + card.getCapacityOfSell() + " now");
        }
    }

    public void buyItem(Item item) throws Exception
    {
        if (item.getPrice() > Account.loggedInAccount.getMoney())
        {
            showOutput.printOutput("you don't have enough money.");
        }
        else if (item.getCapacityOfItemSell() <= 0)
        {
            showOutput.printOutput("this item has no capacity to buy");
        }
        else if (Account.loggedInAccount.getCollection().getItems().size() == 3)
        {
            showOutput.printOutput("You have 3 items in your collection and you can't buy another item.");
        }
        else
        {
            Shop.getInstance().buyItem(item);
            showOutput.printOutput("Successful purchase");
            showOutput.printOutput(item.getItemName() + "'s capacity is " + item.getCapacityOfItemSell() + " now");
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
        showOutput.printOutput("You don't have Card or Item with this ID!");
    }

    private void removeCardFromDeckToSell(Card cardToSell)
    {
        new DeckManager().searchDecksToRemoveCardOnSale(cardToSell);
        Shop.getInstance().sellCard(cardToSell);
        showOutput.printOutput("Successful Sale");
        showOutput.printOutput(cardToSell.getCardName() + "'s capacity is " + cardToSell.getCapacityOfSell() + " now");
    }

    private void removeItemFromDeckToSell(Item itemToSell)
    {
        new DeckManager().searchDecksToRemoveItemOnSale(itemToSell);
        Shop.getInstance().sellItem(itemToSell);
        showOutput.printOutput("Successful Sale");
        showOutput.printOutput(itemToSell.getItemName() + "'s capacity is " + itemToSell.getCapacityOfItemSell() + " now");
    }
}
