package Controller;

import Model.*;
import View.ShowOutput;

public class CollectionManager
{
    private DeckManager deckManager = new DeckManager();
    private ShowOutput showOutput = ShowOutput.getInstance();






    /*public void searchCollection(String name)
    {
        boolean existInTheCollection = false;
        for (Card card : Account.loggedInAccount.getCollection().getCards())
        {
            if (card.getCardName().equals(name))
            {
                showOutput.printOutput("Card exists in the collection");
                showOutput.printOutput("Card ID : " + card.getCardID());
                existInTheCollection = true;
            }
        }
        for (Item item : Account.loggedInAccount.getCollection().getItems())
        {
            if (item.getItemName().equals(name))
            {
                showOutput.printOutput("Item exists in the collection");
                showOutput.printOutput("Item ID : " + item.getItemID());
                existInTheCollection = true;
            }
        }
        if (!existInTheCollection)
        {
            showOutput.printOutput("The item or card doesn't exist in the collection");
        }
    }*/




}
