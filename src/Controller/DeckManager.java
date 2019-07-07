package Controller;

import Model.*;
import View.*;

public class DeckManager
{
    private ShowOutput showOutput = ShowOutput.getInstance();

    public static Deck findDeck(String deckName, Account account)
    {
        for (Deck deck : account.getPlayerDecks())
        {
            if (deck.getDeckName().equals(deckName))
            {
                return deck;
            }
        }
        return null;
    }

    public String checkCircumstancesToAddCardToDeck(Deck deck, Card card, Account account)
    {
        if (card instanceof  Hero)
        {
            return checkCircumstanceToAddHeroCardToDeck(deck, (Hero) card, account);
        }
        else
        {
            for (Minion deckMinion : deck.getMinions())
            {
                if (card.getCardID().equals(deckMinion.getCardID()))
                {
                    return "This card is in the deck";
                }
            }
            for (Spell deckSpell : deck.getSpells())
            {
                if (card.getCardID().equals(deckSpell.getCardID()))
                {
                    return "This card is in the deck";
                }
            }
            if (deck.getMinions().size() + deck.getSpells().size() == 20)
            {
                return "Deck is full";
            }
            deck.addCardToDeck(card, account, false);
            return "Card added to deck";
        }
    }

    public String checkCircumstanceToAddHeroCardToDeck(Deck deck, Hero hero, Account account)
    {
        for (Hero deckHero : deck.getHero())
        {
            if (hero.getCardID().equals(deckHero.getCardID()))
            {
                return "This hero is in the deck";
            }
        }
        if (deck.getHero().size() == 1)
        {
            return "Deck is full";
        }
        deck.addCardToDeck(hero, account, false);
        return "Card added to deck";
    }

    public String checkCircumstancesToAddItemToDeck(Deck deck, Item item, Account account)
    {
        for (Item deckItem : deck.getItem())
        {
            if (item.getItemID().equals(deckItem.getItemID()))
            {
                return "This item is in the deck";
            }
        }
        if (deck.getItem().size() == 1)
        {
            return "Deck is full";
        }
        deck.addItemToDeck(item, account, false);
        return "Item added to deck";
    }

    public void checkCardExistenceInDeckToRemove(Deck deck, Card card, Account account)
    {
        for (Minion minion : deck.getMinions())
        {
            if (card.getCardName().equals(minion.getCardName()))
            {
                deck.deleteCardFromDeck(card, account);
                showOutput.printOutput("Card removed from deck");
                return;
            }
        }
        for (Spell spell : deck.getSpells())
        {
            if (card.getCardName().equals(spell.getCardName()))
            {
                deck.deleteCardFromDeck(card, account);
                showOutput.printOutput("Card removed from deck");
                return;
            }
        }
        for (Hero deckHero : deck.getHero())
        {
            if (card.getCardID().equals(deckHero.getCardID()))
            {
                deck.deleteCardFromDeck(card, account);
                showOutput.printOutput("Card removed from deck");
                return;
            }
        }
        showOutput.printOutput("This card isn't in the deck");
    }

    public void searchDecksToRemoveCardOnSale(Card card, Account account)
    {
        for (Deck deck : account.getPlayerDecks())
        {
            for (Hero deckHero : deck.getHero())
            {
                if (card.getCardID().equals(deckHero.getCardID()))
                {
                    deck.deleteCardFromDeck(card, account);
                    break;
                }
            }
            for (Minion deckMinion : deck.getMinions())
            {
                if (card.getCardID().equals(deckMinion.getCardID()))
                {
                    deck.deleteCardFromDeck(card, account);
                    break;
                }
            }
            for (Spell deckSpell : deck.getSpells())
            {
                if (card.getCardID().equals(deckSpell.getCardID()))
                {
                    deck.deleteCardFromDeck(card, account);
                    break;
                }
            }
        }
    }

    public void checkItemExistenceInDeckToRemove(Deck deck, Item item, Account account)
    {
        for (Item deckItem : deck.getItem())
        {
            if (item.getItemID().equals(deckItem.getItemID()))
            {
                deck.deleteItemFromDeck(item, account);
                showOutput.printOutput("Item removed from deck");
                return;
            }
        }
        showOutput.printOutput("This item isn't in the deck");
    }

    public void searchDecksToRemoveItemOnSale(Item item, Account account)
    {
        for (Deck deck : account.getPlayerDecks())
        {
            for (Item deckItem : deck.getItem())
            {
                if (item.getItemID().equals(deckItem.getItemID()))
                {
                    deck.deleteItemFromDeck(item, account);
                    return;
                }
            }
        }
    }

    public boolean checkDeckValidity(Deck deck)
    {
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

    public void setDeckAsMainDeck(Deck deck, Account account)
    {
        if (checkDeckValidity(deck))
        {
            account.setMainDeck(deck);
            showOutput.printOutput("MainDeck set");
        }
    }
}
