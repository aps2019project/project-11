package Controller;

import Model.*;
import Network.Server;

public class ShopManager
{
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

    public String removeCardFromCollectionToSell(Card cardToSell, Account account)
    {
        new DeckManager().searchDecksToRemoveCardOnSale(cardToSell, account);
        return Server.getShop().sellCard(cardToSell, account);
    }

    public String removeItemFromCollectionToSell(Item itemToSell, Account account)
    {
        new DeckManager().searchDecksToRemoveItemOnSale(itemToSell, account);
        return Server.getShop().sellItem(itemToSell, account);
    }
}
