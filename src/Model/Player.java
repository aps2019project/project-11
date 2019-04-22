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

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
