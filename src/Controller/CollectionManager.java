package Controller;

import Model.*;
import View.ShowOutput;

public class CollectionManager
{
    public void detectID(int ID, String deckName, String command)
    {
        Deck deck = Deck.findDeck(deckName);
        if (deck == null)
        {
            ShowOutput.printOutput("There is no deck with this name");
            return;
        }
        if (command.equals("add"))
        {
            this.checkIDValidityToAddToDeck(deck, ID);
        }
        else if (command.equals("remove"))
        {
            this.checkIDValidityToRemoveFromDeck(deck, ID);
        }
    }

    public void checkIDValidityToAddToDeck(Deck deck, int ID)
    {
        if (Card.findCard(ID) != null)
        {
            for (Card card : Card.getCards())
            {
                if (ID == card.getCardID())
                {
                    if (card instanceof Hero)
                    {
                        deck.addHeroToDeck((Hero) card);
                    }
                    else
                    {
                        deck.addNonHeroCardToDeck(card);
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
                    deck.addItemToDeck(item);
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
            for (Card card : Card.getCards())
            {
                if (ID == card.getCardID())
                {
                    if (card instanceof Hero)
                    {
                        deck.deleteHeroFromDeck((Hero) card);
                    }
                    else
                    {
                        deck.deleteNonHeroCardFromDeck(card);
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
                    deck.deleteItemFromDeck(item);
                    return;
                }
            }
            ShowOutput.printOutput("This item isn't in the collection");
        }
    }

    public void searchCollection(String name)
    {
        for (Card card : Card.getCards())
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
        Deck deck = Deck.findDeck(deckName);
        if (deck != null)
        {
            ShowOutput.printOutput("Model.Deck exists with this name");
            return;
        }
        Deck newDeck = new Deck(deckName);
        Account.loggedInAccount.addDeck(newDeck);
    }

    public void deleteDeck(String deckName)
    {
        Deck deck = Deck.findDeck(deckName);
        if (deck == null)
        {
            ShowOutput.printOutput("There is no deck with this name");
            return;
        }
        Account.loggedInAccount.deleteDeck(deck);
    }
}
