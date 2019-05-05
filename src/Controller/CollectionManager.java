package Controller;

import Model.*;
import View.ShowOutput;

public class CollectionManager
{
    private DeckManager deckManager = new DeckManager();

    public void detectID(String ID, String deckName, String command)
    {
        Deck deck = DeckManager.findDeck(deckName);
        if (deck != null)
        {
            if (command.equals("add"))
            {
                this.checkIDValidityToAddToDeck(deck, ID);
            }
            else if (command.equals("remove"))
            {
                this.checkIDValidityToRemoveFromDeck(deck, ID);
            }
        }
        else
        {
            ShowOutput.printOutput("There is no deck with this name");
        }
    }

    public void checkIDValidityToAddToDeck(Deck deck, String ID)
    {
        if (Account.loggedInAccount.getCollection().findCardinCollection(ID) != null)
        {
            for (Card card : Account.loggedInAccount.getCollection().getCards())
            {
                if (ID.equals(card.getCardID()))
                {
                    if (card instanceof Hero)
                    {
                        deckManager.checkCircumstanceToAddHeroCardToDeck(deck, (Hero) card);
                    }
                    else
                    {
                        deckManager.checkCircumstancesToAddNonHeroCardToDeck(deck, card);
                    }
                    return;
                }
            }
            ShowOutput.printOutput("This card isn't in the collection");
        }
        else if (Account.loggedInAccount.getCollection().findItemInTheCollection(ID) != null)
        {
            for (Item item : Account.loggedInAccount.getCollection().getItems())
            {
                if (ID.equals(item.getItemID()))
                {
                    deckManager.checkCircumstancesToAddItemToDeck(deck, item);
                    return;
                }
            }
            ShowOutput.printOutput("This item isn't in the collection");
        }
        else
        {
            ShowOutput.printOutput("Invalid ID");
        }
    }

    public void checkIDValidityToRemoveFromDeck(Deck deck, String ID)
    {
        if (Account.loggedInAccount.getCollection().findCardinCollection(ID) != null)
        {
            for (Card card : Account.loggedInAccount.getCollection().getCards())
            {
                if (ID.equals(card.getCardID()))
                {
                    if (card instanceof Hero)
                    {
                        deckManager.checkHeroCardExistenceInDeckToRemove(deck, (Hero) card);
                    }
                    else
                    {
                        deckManager.checkNonHeroCardExistenceInDeckToRemove(deck, card);
                    }
                    return;
                }
            }
            ShowOutput.printOutput("This card isn't in the collection");
        }
        else if (Account.loggedInAccount.getCollection().findItemInTheCollection(ID) != null)
        {
            for (Item item : Account.loggedInAccount.getCollection().getItems())
            {
                if (ID.equals(item.getItemID()))
                {
                    deckManager.checkItemExistenceInDeckToRemove(deck, item);
                    return;
                }
            }
            ShowOutput.printOutput("This item isn't in the collection");
        }
        else
        {
            ShowOutput.printOutput("Invalid ID");
        }
    }

    public void searchCollection(String name)
    {
        boolean existInTheCollection = false;
        for (Card card : Account.loggedInAccount.getCollection().getCards())
        {
            if (card.getCardName().equals(name))
            {
                ShowOutput.printOutput("Card exists in the collection");
                ShowOutput.printOutput("Card ID : " + card.getCardID());
                existInTheCollection = true;
            }
        }
        for (Item item : Account.loggedInAccount.getCollection().getItems())
        {
            if (item.getItemName().equals(name))
            {
                ShowOutput.printOutput("Item exists in the collection");
                ShowOutput.printOutput("Item ID : " + item.getItemID());
                existInTheCollection = true;
            }
        }
        if (!existInTheCollection)
        {
            ShowOutput.printOutput("The item or card doesn't exist in the collection");
        }
    }

    public void createDeck(String deckName)
    {
        Deck deck = DeckManager.findDeck(deckName);
        if (deck != null)
        {
            ShowOutput.printOutput("Deck exists with this name");
            return;
        }
        Deck newDeck = new Deck(deckName);
        Account.loggedInAccount.addDeck(newDeck);
        ShowOutput.printOutput("Deck created");
    }

    public void deleteDeck(String deckName)
    {
        Deck deck = DeckManager.findDeck(deckName);
        if (deck != null)
        {
            Account.loggedInAccount.deleteDeck(deck);
            ShowOutput.printOutput("Deck deleted");
        }
        else
        {
            ShowOutput.printOutput("There is no deck with this name");
        }
    }
}
