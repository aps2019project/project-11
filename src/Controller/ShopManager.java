package Controller;

import Model.*;
import Network.Server;
import View.*;

public class ShopManager
{
    private ShowOutput showOutput = ShowOutput.getInstance();

    public String buyCard(Card card, Account account) throws Exception
    {
        if (card.getPrice() > account.getMoney())
        {
            return "you don't have enough money.";
        }
        else if (card.getCapacityOfSell() <= 0)
        {
            return "this card has no capacity to buy";
        }
        else
        {
            Server.getShop().buyCard(card, account);
            return "Successful purchase" + "\n" + card.getCardName() + "'s capacity is " + card.getCapacityOfSell() + " now";
        }
    }

    public String buyItem(Item item, Account account) throws Exception
    {
        if (item.getPrice() > account.getMoney())
        {
            return "you don't have enough money.";
        }
        else if (item.getCapacityOfSell() <= 0)
        {
            return "this item has no capacity to buy";
        }
        else if (account.getCollection().getItems().size() == 3)
        {
            return "You have 3 items in your collection and you can't buy another item.";
        }
        else
        {
            Server.getShop().buyItem(item, account);
            return "Successful purchase" + "\n" + item.getItemName() + "'s capacity is " + item.getCapacityOfSell() + " now";
        }
    }

    public String detectIDToSell(String ID,Account account)
    {
        for (Hero hero : Account.loggedInAccount.getCollection().getHeroes())
        {
            if (hero.getCardID().equals(ID))
            {
                removeCardFromDeckToSell(hero,account);
                return "Successful sell";
            }
        }
        for (Minion minion : Account.loggedInAccount.getCollection().getMinions())
        {
            if (minion.getCardID().equals(ID))
            {
                removeCardFromDeckToSell(minion,account);
                return "successful sell";
            }
        }
        for (Spell spell : Account.loggedInAccount.getCollection().getSpells())
        {
            if (spell.getCardID().equals(ID))
            {
                removeCardFromDeckToSell(spell,account);
                return "successful sell";
            }
        }

        return "You don't have Card  with this ID!";
    }

    public String detectIDForSellItem(String ID,Account account)
    {
        for (Item item : Account.loggedInAccount.getCollection().getItems())
        {
            if (item.getItemID().equals(ID))
            {
                removeItemFromDeckToSell(item,account);
                return "successful sell";
            }
        }
        return "You don't have Item  with this ID!";
    }

    private void removeCardFromDeckToSell(Card cardToSell,Account account)
    {
        new DeckManager().searchDecksToRemoveCardOnSale(cardToSell,account);
        Server.getShop().sellCard(cardToSell);
        showOutput.printOutput("Successful Sale");
        showOutput.printOutput(cardToSell.getCardName() + "'s capacity is " + cardToSell.getCapacityOfSell() + " now");
    }

    private void removeItemFromDeckToSell(Item itemToSell,Account account)
    {
        new DeckManager().searchDecksToRemoveItemOnSale(itemToSell,account);
        Server.getShop().sellItem(itemToSell);
        showOutput.printOutput("Successful Sale");
        showOutput.printOutput(itemToSell.getItemName() + "'s capacity is " + itemToSell.getCapacityOfSell() + " now");
    }
}
