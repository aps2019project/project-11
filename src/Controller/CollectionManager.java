package Controller;

import Model.*;
import View.ShowOutput;

public class CollectionManager
{
    private DeckManager deckManager = new DeckManager();

    public void detectID(int ID, String deckName, String command)
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
    }

    public void checkIDValidityToAddToDeck(Deck deck, int ID)
    {
        if (Card.findCard(ID) != null)
        {
            for (Card card : Shop.getInstance().getCards())
            {
                if (ID == card.getCardID())
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
        if (Item.findItem(ID) != null)
        {
            for (Item item : Item.getItems())
            {
                if (ID == item.getItemID())
                {
                    deckManager.checkCircumstancesToAddItemToDeck(deck, item);
                    return;
                }
            }
            ShowOutput.printOutput("This item isn't in the collection");
        }
    }

    public void checkIDValidityToRemoveFromDeck(Deck deck, int ID)
    {
        if (Card.findCard(ID) != null)
        {
            for (Card card : Shop.getInstance().getCards())
            {
                if (ID == card.getCardID())
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
        if (Item.findItem(ID) != null)
        {
            for (Item item : Item.getItems())
            {
                if (ID == item.getItemID())
                {
                    deckManager.checkItemExistenceInDeckToRemove(deck, item);
                    return;
                }
            }
            ShowOutput.printOutput("This item isn't in the collection");
        }
    }

    public void searchCollection(String name)
    {
        for (Card card : Shop.getInstance().getCards())
        {
            if (card.getCardName().equals(name))
            {
                ShowOutput.printOutput("Card exists in the collection");
                ShowOutput.printOutput("Card ID : " + card.getCardID());
                return;
            }
        }
        for (Item item : Item.getItems())
        {
            if (item.getItemName().equals(name))
            {
                ShowOutput.printOutput("Item exists in the collection");
                ShowOutput.printOutput("Item ID : " + item.getItemID());
                return;
            }
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
    }

    public void deleteDeck(String deckName)
    {
        Deck deck = DeckManager.findDeck(deckName);
        if (deck != null)
        {
            Account.loggedInAccount.deleteDeck(deck);
        }
    }
}
