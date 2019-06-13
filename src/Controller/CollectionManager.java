package Controller;

import Model.*;
import View.ShowOutput;

public class CollectionManager
{
    private DeckManager deckManager = new DeckManager();
    private ShowOutput showOutput = ShowOutput.getInstance();

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
            showOutput.printOutput("There is no deck with this name");
        }
    }

    public void checkIDValidityToAddToDeck(Deck deck, String ID)
    {
        if (Account.loggedInAccount.getCollection().findCardinCollection(ID) != null)
        {
            for (Hero hero : Account.loggedInAccount.getCollection().getHeroes())
            {
                if (ID.equals(hero.getCardID()))
                {
                    deckManager.checkCircumstanceToAddHeroCardToDeck(deck, hero);
                }
            }
            for (Minion minion : Account.loggedInAccount.getCollection().getMinions())
            {
                if (ID.equals(minion.getCardID()))
                {
                    deckManager.checkCircumstancesToAddCardToDeck(deck, minion);
                }
            }
            for (Spell spell : Account.loggedInAccount.getCollection().getSpells())
            {
                if (ID.equals(spell.getCardID()))
                {
                    deckManager.checkCircumstancesToAddCardToDeck(deck, spell);
                }
            }
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
            showOutput.printOutput("This item isn't in the collection");
        }
        else
        {
            showOutput.printOutput("Invalid ID");
        }
    }

    public void checkIDValidityToRemoveFromDeck(Deck deck, String ID)
    {
        if (Account.loggedInAccount.getCollection().findCardinCollection(ID) != null)
        {
            for (Hero hero : Account.loggedInAccount.getCollection().getHeroes())
            {
                if (ID.equals(hero.getCardID()))
                {
                    deckManager.checkCardExistenceInDeckToRemove(deck, hero);
                }
            }
            for (Minion minion : Account.loggedInAccount.getCollection().getMinions())
            {
                if (ID.equals(minion.getCardID()))
                {
                    deckManager.checkCardExistenceInDeckToRemove(deck, minion);
                }
            }
            for (Spell spell : Account.loggedInAccount.getCollection().getSpells())
            {
                if (ID.equals(spell.getCardID()))
                {
                    deckManager.checkCardExistenceInDeckToRemove(deck, spell);
                }
            }
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
            showOutput.printOutput("This item isn't in the collection");
        }
        else
        {
            showOutput.printOutput("Invalid ID");
        }
    }

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

    public void createDeck(String deckName)
    {
        Deck deck = DeckManager.findDeck(deckName);
        if (deck != null)
        {
            showOutput.printOutput("Deck exists with this name");
            return;
        }
        Deck newDeck = new Deck(deckName);
        Account.loggedInAccount.addDeck(newDeck);
        showOutput.printOutput("Deck created");
    }

    public void deleteDeck(String deckName)
    {
        Deck deck = DeckManager.findDeck(deckName);
        if (deck != null)
        {
            Account.loggedInAccount.deleteDeck(deck);
            showOutput.printOutput("Deck deleted");
        }
        else
        {
            showOutput.printOutput("There is no deck with this name");
        }
    }
}
