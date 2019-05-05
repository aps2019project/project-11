package Controller;

import Model.*;
import View.*;

public class DeckManager
{
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
            if (card.getCardID() == deckCard.getCardID())
            {
                ShowOutput.printOutput("This card is in the deck");
                return;
            }
        }
        if (deck.getNonHeroCards().size() == 20)
        {
            ShowOutput.printOutput("Deck is full");
            return;
        }
        deck.addNonHeroCardToDeck(card , false);
        ShowOutput.printOutput("Card added to deck");
    }

    public void checkCircumstanceToAddHeroCardToDeck(Deck deck, Hero hero)
    {
        for (Hero deckHero : deck.getHero())
        {
            if (hero.getHeroID() == deckHero.getHeroID())
            {
                ShowOutput.printOutput("This hero is in the deck");
                return;
            }
        }
        if (deck.getHero().size() == 1)
        {
            ShowOutput.printOutput("Deck is full");
            return;
        }
        deck.addHeroToDeck(hero ,false);
        ShowOutput.printOutput("Card added to deck");
    }

    public void checkCircumstancesToAddItemToDeck(Deck deck, Item item)
    {
        for (Item deckItem : deck.getItem())
        {
            if (item.getItemID() == deckItem.getItemID())
            {
                ShowOutput.printOutput("This item is in the deck");
                return;
            }
        }
        if (deck.getItem().size() == 1)
        {
            ShowOutput.printOutput("Deck is full");
            return;
        }
        deck.addItemToDeck(item);
    }

    public void checkNonHeroCardExistenceInDeckToRemove(Deck deck, Card card)
    {
        for (Card deckCard : deck.getNonHeroCards())
        {
            if (card.getCardName().equals(deckCard.getCardName()))
            {
                deck.deleteNonHeroCardFromDeck(card);
                ShowOutput.printOutput("Card removed from deck");
                return;
            }
        }
        ShowOutput.printOutput("This card isn't in the deck");
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
            if (hero.getHeroID() == deckHero.getHeroID())
            {
                deck.deleteHeroFromDeck(hero);
                ShowOutput.printOutput("Card removed from deck");
                return;
            }
        }
        ShowOutput.printOutput("This hero isn't in the deck");
    }

    public void searchDecksToRemoveHeroOnSale(Hero hero)
    {
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            for (Hero deckHero : deck.getHero())
            {
                if (hero.getHeroID() == deckHero.getHeroID())
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
            if (item.getItemID() == deckItem.getItemID())
            {
                deck.deleteItemFromDeck(item);
                return;
            }
        }
        ShowOutput.printOutput("This item isn't in the deck");
    }

    public void searchDecksToRemoveItemOnSale(Item item)
    {
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            for (Item deckItem : deck.getItem())
            {
                if (item.getItemID() == deckItem.getItemID())
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
                ShowOutput.printOutput("Deck is valid");
                return true;
            }
            else
            {
                ShowOutput.printOutput("Deck isn't valid");
                return false;
            }
        }
        else
        {
            ShowOutput.printOutput("There is no deck with this name");
        }
        return false;
    }

    public void setDeckAsMainDeck(String deckName)
    {
        if (checkDeckValidity(deckName))
        {
            Deck deck = DeckManager.findDeck(deckName);
            Account.loggedInAccount.setMainDeck(deck);
            ShowOutput.printOutput("MainDeck set");
        }
    }
}
