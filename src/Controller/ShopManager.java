package Controller;

import Model.*;
import View.*;

public class ShopManager
{
    public void searchShop(String name)
    {
        for (Card card : Shop.shop.getCards())
        {
            if (card.getCardName().equals(name))
            {
                ShowOutput.printOutput("The card exists in the shop.");
                ShowOutput.printOutput("CardID : " + card.getCardID());
                return;
            }
        }
        for (Item item : Shop.shop.getItems())
        {
            if (item.getItemName().equals(name))
            {
                ShowOutput.printOutput("The item exists in the shop.");
                ShowOutput.printOutput("ItemID : " + item.getItemID());
                return;
            }
        }
        ShowOutput.printOutput("The item  or card doesn't exist in the shop.");
    }

    public void buyCard(Card card)
    {
        if (card.getPrice() > Account.loggedInAccount.getMoney())
        {
            ShowOutput.printOutput("you haven't enough money.");
        }
        else
        {
            Shop.shop.buyCard(card);
            ShowOutput.printOutput("Successful purchase");
        }
    }

    public void buyItem(Item item)
    {
        if (item.getPrice() > Account.loggedInAccount.getMoney())
        {
            ShowOutput.printOutput("you haven't enough money.");
        }
        else if (Account.loggedInAccount.getCollection().getItems().size() == 3)
        {
            ShowOutput.printOutput("You have 3 items in your collection and you can't buy another item.");
        }
        else
        {
            Shop.shop.buyItem(item);
            ShowOutput.printOutput("Successful purchase");
        }
    }

    public void detectIDToSell(int ID)
    {
        for (Card card : Account.loggedInAccount.getCollection().getCards())
        {
            if (card.getCardID() == ID)
            {
                removeCardFromDeckToSell(card);
                return;
            }
        }
        for (Item item : Account.loggedInAccount.getCollection().getItems())
        {
            if (item.getItemID() == ID)
            {
                removeItemFromDeckToSell(item);
                return;
            }
        }
        ShowOutput.printOutput("You haven't Card or Item with this ID!");
    }

    public void removeCardFromDeckToSell(Card cardToSell)
    {
        if (cardToSell instanceof Hero)
        {
            new DeckManager().searchDecksToRemoveHeroOnSale((Hero) cardToSell);
        }
        else
        {
            new DeckManager().searchDecksToRemoveNonHeroCardOnSale(cardToSell);
        }
        Shop.shop.sellCard(cardToSell);
        ShowOutput.printOutput("Successful Sale");
    }

    public void removeItemFromDeckToSell(Item itemToSell)
    {
        new DeckManager().searchDecksToRemoveItemOnSale(itemToSell);
        Shop.shop.sellItem(itemToSell);
        ShowOutput.printOutput("Successful Sale");
    }
}
