package Model;

import java.util.ArrayList;

public class Hand
{
    private ArrayList<Card> cards = new ArrayList<>();
    private Card nextCard;

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
}
