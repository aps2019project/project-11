package Controller;

import Model.*;
import View.*;

public class DeckManager
{
    private ShowOutput showOutput = new ShowOutput();

    public static Deck findDeck(String deckName)
    {
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            if (deck.getDeckName().equals(deckName))
            {
                return deck;
            }
        }
        return null;
    }

    public void checkCircumstancesToAddNonHeroCardToDeck(Deck deck, Card card)
    {
        for (Card deckCard : deck.getNonHeroCards())
        {
            if (card.getCardID().equals(deckCard.getCardID()))
            {
                showOutput.printOutput("This card is in the deck");
                return;
            }
        }
        if (deck.getNonHeroCards().size() == 20)
        {
            showOutput.printOutput("Deck is full");
            return;
        }
        deck.addNonHeroCardToDeck(card, false);
        showOutput.printOutput("Card added to deck");
    }

    public void checkCircumstanceToAddHeroCardToDeck(Deck deck, Hero hero)
    {
        for (Hero deckHero : deck.getHero())
        {
            if (hero.getCardID().equals(deckHero.getCardID()))
            {
                showOutput.printOutput("This hero is in the deck");
                return;
            }
        }
        if (deck.getHero().size() == 1)
        {
            showOutput.printOutput("Deck is full");
            return;
        }
        deck.addHeroToDeck(hero, false);
        showOutput.printOutput("Card added to deck");
    }

    public void checkCircumstancesToAddItemToDeck(Deck deck, Item item)
    {
        for (Item deckItem : deck.getItem())
        {
            if (item.getItemID().equals(deckItem.getItemID()))
            {
                showOutput.printOutput("This item is in the deck");
                return;
            }
        }
        if (deck.getItem().size() == 1)
        {
            showOutput.printOutput("Deck is full");
            return;
        }
        deck.addItemToDeck(item, false);
    }

    public void checkNonHeroCardExistenceInDeckToRemove(Deck deck, Card card)
    {
        for (Card deckCard : deck.getNonHeroCards())
        {
            if (card.getCardName().equals(deckCard.getCardName()))
            {
                deck.deleteNonHeroCardFromDeck(card);
                showOutput.printOutput("Card removed from deck");
                return;
            }
        }
        showOutput.printOutput("This card isn't in the deck");
    }

    public void searchDecksToRemoveNonHeroCardOnSale(Card card)
    {
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            for (Card deckCard : deck.getNonHeroCards())
            {
                if (card.getCardName().equals(deckCard.getCardName()))
                {
                    deck.deleteNonHeroCardFromDeck(card);
                    return;
                }
            }
        }
    }

    public void checkHeroCardExistenceInDeckToRemove(Deck deck, Hero hero)
    {
        for (Hero deckHero : deck.getHero())
        {
            if (hero.getCardID().equals(deckHero.getCardID()))
            {
                deck.deleteHeroFromDeck(hero);
                showOutput.printOutput("Card removed from deck");
                return;
            }
        }
        showOutput.printOutput("This hero isn't in the deck");
    }

    public void searchDecksToRemoveHeroOnSale(Hero hero)
    {
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            for (Hero deckHero : deck.getHero())
            {
                if (hero.getCardID().equals(deckHero.getCardID()))
                {
                    deck.deleteHeroFromDeck(hero);
                    return;
                }
            }
        }
    }

    public void checkItemExistenceInDeckToRemove(Deck deck, Item item)
    {
        for (Item deckItem : deck.getItem())
        {
            if (item.getItemID().equals(deckItem.getItemID()))
            {
                deck.deleteItemFromDeck(item);
                return;
            }
        }
        showOutput.printOutput("This item isn't in the deck");
    }

    public void searchDecksToRemoveItemOnSale(Item item)
    {
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            for (Item deckItem : deck.getItem())
            {
                if (item.getItemID().equals(deckItem.getItemID()))
                {
                    deck.deleteItemFromDeck(item);
                    return;
                }
            }
        }
    }

    public boolean checkDeckValidity(String deckName)
    {
        Deck deck = findDeck(deckName);
        if (deck != null)
        {
            if (deck.getNonHeroCards().size() == 20 && deck.getHero().size() == 1)
            {
                showOutput.printOutput("Deck is valid");
                return true;
            }
            else
            {
                showOutput.printOutput("Deck isn't valid");
                return false;
            }
        }
        else
        {
            showOutput.printOutput("There is no deck with this name");
        }
        return false;
    }

    public void setDeckAsMainDeck(String deckName)
    {
        if (checkDeckValidity(deckName))
        {
            Deck deck = DeckManager.findDeck(deckName);
            Account.loggedInAccount.setMainDeck(deck);
            showOutput.printOutput("MainDeck set");
        }
    }
}
