package Model;

import java.util.ArrayList;

public class Player
{
    private Account account;
    private Deck mainDeck;
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Item> collectibleItems = new ArrayList<>();
    private ArrayList<Card> graveYard = new ArrayList<>();
    private int MP;

    public Card findCardInGraveYard(int ID){
        for (Card card : graveYard)
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

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<Card> getGraveYard() {
        return graveYard;
    }
}
