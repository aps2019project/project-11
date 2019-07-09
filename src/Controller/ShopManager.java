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
            Server.getShop().buyCard(card, account, false);
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
            Server.getShop().buyItem(item, account, false);
            return "Successful purchase" + "\n" + item.getItemName() + "'s capacity is " + item.getCapacityOfSell() + " now";
        }
    }

    public String bidCard(Card card, Account account)
    {
        card.setBidWinner(account);
        card.setBidWinnerPrice(card.getBidWinnerPrice() + 500);
        return "You bid on " + card.getCardName() + " for " + card.getBidWinnerPrice();
    }

    public String bidItem(Item item, Account account)
    {
        item.setBidWinner(account);
        item.setBidWinnerPrice(item.getBidWinnerPrice() + 500);
        return "You bid on " + item.getItemName() + " for " + item.getBidWinnerPrice();
    }

    public String detectIDToSell(String ID, Account account)
    {
        for (Hero hero : account.getCollection().getHeroes())
        {
            if (hero.getCardID().equals(ID))
            {
                removeCardFromDeckToSell(hero, account);
                return " Successful Sale" + "\n" + hero.getCardName() + "'s capacity is " + hero.getCapacityOfSell() + " now";
            }
        }
        for (Minion minion : account.getCollection().getMinions())
        {
            if (minion.getCardID().equals(ID))
            {
                removeCardFromDeckToSell(minion, account);
                return " Successful Sale" + "\n" + minion.getCardName() + "'s capacity is " + minion.getCapacityOfSell() + " now";
            }
        }
        for (Spell spell : account.getCollection().getSpells())
        {
            if (spell.getCardID().equals(ID))
            {
                removeCardFromDeckToSell(spell, account);
                return " Successful Sale" + "\n" + spell.getCardName() + "'s capacity is " + spell.getCapacityOfSell() + " now";
            }
        }
        return "You don't have Card with this ID!";
    }

    public String sellItem(Account account,String ID)
    {
        for (Item item : account.getCollection().getItems())
        {
            if (item.getItemID().equals(ID))
            {
                removeItemFromDeckToSell(item, account);
                return " successful sell";
            }
        }
        return "You don't have Item with this ID!";
    }

    private void removeCardFromDeckToSell(Card cardToSell, Account account)
    {
        new DeckManager().searchDecksToRemoveCardOnSale(cardToSell, account);
        Server.getShop().sellCard(Card.findCard(cardToSell.getCardName()), account);
        showOutput.printOutput("Successful Sale");
        showOutput.printOutput(cardToSell.getCardName() + "'s capacity is " + cardToSell.getCapacityOfSell() + " now");
    }

    private void removeItemFromDeckToSell(Item itemToSell, Account account)
    {
        new DeckManager().searchDecksToRemoveItemOnSale(Item.findItem(itemToSell.getItemName()), account);
        Server.getShop().sellItem(itemToSell, account);
        showOutput.printOutput("Successful Sale");
        showOutput.printOutput(itemToSell.getItemName() + "'s capacity is " + itemToSell.getCapacityOfSell() + " now");
    }
}
