package Model;

import java.util.ArrayList;

public class Player
{
    private Account account;
    private Deck mainDeck;
    private Hand hand;
    private ArrayList<Item> collectibleItems = new ArrayList<>();
    private GraveYard graveYard = new GraveYard();
    private int MP;
    private ArrayList<Card> insertedCards = new ArrayList<>();

    public Player(Account account)
    {
        this.account = account;
        this.mainDeck = account.getMainDeck();
    }

    public Card findCardInGraveYard(int ID)
    {
        for (Card card : graveYard.getCards())
        {
            if (card.getCardID() == ID)
            {
                return card;
            }
        }
        return null;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public GraveYard getGraveYard()
    {
        return graveYard;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getInsertedCards() {
        return insertedCards;
    }

    public  void setInsertedCards(ArrayList<Card> insertedCards)
    {
        this.insertedCards = insertedCards;
    }
}
