package Controller;

import Model.*;
import View.*;

public class DeckManager
{
    private ShowOutput showOutput = ShowOutput.getInstance();

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

    public void checkCircumstancesToAddCardToDeck(Deck deck, Card card)
    {
        if (card instanceof  Hero)
        {
            checkCircumstanceToAddHeroCardToDeck(deck, (Hero) card);
        }
        else
        {
            for (Minion deckMinion : deck.getMinions())
            {
                if (card.getCardID().equals(deckMinion.getCardID()))
                {
                    showOutput.printOutput("This card is in the deck");
                    return;
                }
            }
            for (Spell deckSpell : deck.getSpells())
            {
                if (card.getCardID().equals(deckSpell.getCardID()))
                {
                    showOutput.printOutput("This card is in the deck");
                    return;
                }
            }
            if (deck.getMinions().size() + deck.getSpells().size() == 20)
            {
                showOutput.printOutput("Deck is full");
                return;
            }
            deck.addCardToDeck(card, false);
            showOutput.printOutput("Card added to deck");
        }
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
        deck.addCardToDeck(hero, false);
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

    public void checkCardExistenceInDeckToRemove(Deck deck, Card card)
    {
        for (Minion minion : deck.getMinions())
        {
            if (card.getCardName().equals(minion.getCardName()))
            {
                deck.deleteCardFromDeck(card);
                showOutput.printOutput("Card removed from deck");
                return;
            }
        }
        for (Spell spell : deck.getSpells())
        {
            if (card.getCardName().equals(spell.getCardName()))
            {
                deck.deleteCardFromDeck(card);
                showOutput.printOutput("Card removed from deck");
                return;
            }
        }
        for (Hero deckHero : deck.getHero())
        {
            if (card.getCardID().equals(deckHero.getCardID()))
            {
                deck.deleteCardFromDeck(card);
                showOutput.printOutput("Card removed from deck");
                return;
            }
        }
        showOutput.printOutput("This card isn't in the deck");
    }

    public void searchDecksToRemoveCardOnSale(Card card,Account account)
    {
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            for (Hero deckHero : deck.getHero())
            {
                if (card.getCardID().equals(deckHero.getCardID()))
                {
                    deck.deleteCardFromDeck(card);
                    break;
                }
            }
            for (Minion deckMinion : deck.getMinions())
            {
                if (card.getCardID().equals(deckMinion.getCardID()))
                {
                    deck.deleteCardFromDeck(card);
                    break;
                }
            }
            for (Spell deckSpell : deck.getSpells())
            {
                if (card.getCardID().equals(deckSpell.getCardID()))
                {
                    deck.deleteCardFromDeck(card);
                    break;
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
                showOutput.printOutput("Item removed from deck");
                return;
            }
        }
        showOutput.printOutput("This item isn't in the deck");
    }

    public void searchDecksToRemoveItemOnSale(Item item,Account account)
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
            if (deck.getMinions().size() + deck.getSpells().size() == 20 && deck.getHero().size() == 1)
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
