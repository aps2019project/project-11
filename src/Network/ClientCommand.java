package Network;

import Model.Card;
import Model.Deck;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public enum ClientCommand
{
    SIGN_UP,
    LOGIN,
    LOGOUT,
    SAVE_ACCOUNT_INFO,
    MAKE_CUSTOM_SPELL,
    MAKE_CUSTOM_MINION,
    MAKE_CUSTOM_HERO,
    LEADER_BOARD,
    BUY,
    SELL,
    CREATE_DECK,
    DELETE_DECK,
    IMPORT_DECK,
    EXPORT_DECK,
    ADD_CARD_TO_DECK,
    REMOVE_CARD_FROM_DECK,
    SET_MAIN_DECK,
    ;

    private String authToken;
    private String userName;
    private String password;
    private Card card;
    private Deck deck;
    private String deckName;      //just for creating deck with this name
    private ArrayList<TextField> textFieldsToMakeCustomCards;

    private static ClientCommand clientCommand;

    public static ClientCommand getClientCommand()
    {
        return clientCommand;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Card getCard()
    {
        return card;
    }

    public void setCard(Card card)
    {
        this.card = card;
    }

    public Deck getDeck()
    {
        return deck;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }

    public String getDeckName()
    {
        return deckName;
    }

    public void setDeckName(String deckName)
    {
        this.deckName = deckName;
    }

    public ArrayList<TextField> getTextFieldsToMakeCustomCards()
    {
        return textFieldsToMakeCustomCards;
    }

    public void setTextFieldsToMakeCustomCards(ArrayList<TextField> textFieldsToMakeCustomCards)
    {
        this.textFieldsToMakeCustomCards = textFieldsToMakeCustomCards;
    }

    public static void setClientCommand(ClientCommand clientCommand)
    {
        ClientCommand.clientCommand = clientCommand;
    }
}
