package Model;

import java.util.ArrayList;

public class Hand
{
    private ArrayList<Card> cards = new ArrayList<>();
    private Card nextCard;

    public Card findCardInHand(String cardName)
    {
        for (Card card : this.getCards())
        {
            if (card.getCardName().equals(cardName))
            {
                return card;
            }
        }
        return null;
    }

    public Card getNextCard()
    {
        return nextCard;
    }

    public void setNextCard(Card nextCard)
    {
        this.nextCard = nextCard;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
