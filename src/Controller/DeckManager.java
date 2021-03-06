package Controller;

import Model.*;
import Network.ServerCommand;
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

    @SuppressWarnings("Duplicates")
    public String checkCircumstancesToAddCardToDeck(Deck deck, Card card, Account account)
    {
        if (card instanceof Hero)
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

    @SuppressWarnings("Duplicates")
    public String checkCircumstancesToRemoveCardFromDeck(Deck deck, Card card, Account account)
    {
        if (card instanceof Hero)
        {
            return checkCircumstanceToRemoveHeroCardFromDeck(deck, (Hero) card, account);
        }
        else
        {
            for (Minion deckMinion : deck.getMinions())
            {
                if (card.getCardID().equals(deckMinion.getCardID()))
                {
                    deck.deleteCardFromDeck(card, account);
                    return "Card removed from deck";
                }
            }
            for (Spell deckSpell : deck.getSpells())
            {
                if (card.getCardID().equals(deckSpell.getCardID()))
                {
                    deck.deleteCardFromDeck(card, account);
                    return "Card removed from deck";
                }
            }
            if (deck.getMinions().size() + deck.getSpells().size() == 0)
            {
                return "Deck is empty";
            }
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    public String checkCircumstancesToRemoveItemFromDeck(Deck deck, Item item, Account account)
    {
        for (Item deckItem : deck.getItem())
        {
            if (item.getItemID().equals(deckItem.getItemID()))
            {
                deck.deleteItemFromDeck(item, account);
                return "Item removed From deck";
            }
        }
        if (deck.getItem().size() == 0)
        {
            return "Deck is empty";
        }
        return null;
    }

    public String checkCircumstanceToRemoveHeroCardFromDeck(Deck deck, Hero hero, Account account)
    {
        for (Hero removingHero : deck.getHero())
        {
            if (hero.getCardID().equals(removingHero.getCardID()))
            {
                deck.deleteCardFromDeck(hero, account);
                return "hero removed from deck";
            }
        }
        if (deck.getHero().size() == 0)
        {
            return "Deck is empty";
        }
        return null;
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

    public boolean checkDeckValidityForAIAccount(Deck deck)
    {
        if (deck != null)
        {
            if (deck.getMinions().size() + deck.getSpells().size() == 20 && deck.getHero().size() == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public boolean checkDeckValidity(Deck deck, ServerCommand serverCommand)
    {
        if (deck != null)
        {
            if (deck.getMinions().size() + deck.getSpells().size() == 20 && deck.getHero().size() == 1)
            {
                serverCommand.setMessage("Deck is valid");
                return true;
            }
            else
            {
                serverCommand.setMessage("Deck isn't valid");
                return false;
            }
        }
        else
        {
            serverCommand.setMessage("There is no deck with this name");
        }
        return false;
    }

    public void setDeckAsMainDeck(Deck deck, Account account, ServerCommand serverCommand)
    {
        if (checkDeckValidity(deck, serverCommand))
        {
            account.setMainDeck(deck);
            serverCommand.setMessage(serverCommand.getMessage() + "\n" + "MainDeck set");
        }
    }
}
