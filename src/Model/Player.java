package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Player
{
    private Account account;
    private Deck mainDeck;
    private Hand hand = new Hand();
    private ArrayList<Item> collectibleItems = new ArrayList<>();
    private GraveYard graveYard = new GraveYard();
    private int defaultMP = 2;
    private int MP;
    private ArrayList<Minion> insertedCards = new ArrayList<>();
    private ArrayList<ItemChange> activeItemsOnPlayer = new ArrayList<>();
    private ArrayList<SpellChange> activeSpellsOnPlayer = new ArrayList<>();
    private boolean isAIPlayer = false;

    public Player(Account account , boolean isAIPlayer)
    {
        this.account = account;
        this.mainDeck = account.getMainDeck();
        this.setAIPlayer(isAIPlayer);
        Collections.shuffle(account.getMainDeck().getNonHeroCards());
        for (int i = 0;i < 5;i++)
        {
            this.getHand().addCardToHand(account.getMainDeck().getNonHeroCards().get(i));
        }
        this.getHand().setNextCard(account.getMainDeck().getNonHeroCards().get(5));
    }

    public Card findCardInGraveYard(String ID)
    {
        for (Card card : graveYard.getCards())
        {
            if (card.getCardID().equals(ID))
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

    public ArrayList<Minion> getInsertedCards() {
        return insertedCards;
    }

    public  void setInsertedCards(ArrayList<Minion> insertedCards)
    {
        this.insertedCards = insertedCards;
    }

    public ArrayList<Item> getCollectibleItems() {
        return collectibleItems;
    }

    public void setCollectibleItems(ArrayList<Item> collectibleItems) {
        this.collectibleItems = collectibleItems;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public void setGraveYard(GraveYard graveYard) {
        this.graveYard = graveYard;
    }

    public int getMP() {
        return MP;
    }

    public void setMP()
    {
        this.MP = defaultMP;
    }

    public void increaseDefaultMP()
    {
        this.defaultMP = defaultMP + 1;
    }

    public void decreaseMP(int number){
        MP -= number;
    }

    public ArrayList<ItemChange> getActiveItemsOnPlayer()
    {
        return activeItemsOnPlayer;
    }

    public void addActiveItemOnPlayer(ItemChange activeItemOnPlayer)
    {
        activeItemsOnPlayer.add(activeItemOnPlayer);
    }

    public ArrayList<SpellChange> getActiveSpellsOnPlayer()
    {
        return activeSpellsOnPlayer;
    }

    public void addActiveSpellOnPlayer(SpellChange activeSpellOnPlayer)
    {
        activeSpellsOnPlayer.add(activeSpellOnPlayer);
    }

    public boolean isAIPlayer() {
        return isAIPlayer;
    }

    public void setAIPlayer(boolean AIPlayer) {
        isAIPlayer = AIPlayer;
    }
}
