package Network;

import Model.*;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ClientCommand
{
    private ClientCommandEnum clientCommandEnum;
    private String userName;
    private String password;
    private transient ArrayList<TextField> textFieldsToMakeCustom;
    private Card card;
    private Item item;
    private Deck deck;
    private String deckName;
    private String authToken;

    private transient int battleMode;
    private transient Pane[][] battleFieldPanes;
    private transient GridPane gridPane;
    private transient Account account;
    private transient Account secondAccount;
    private transient Player player;
    private transient Group root;

    public ClientCommand(ClientCommandEnum clientCommandEnum)
    {
        this.clientCommandEnum = clientCommandEnum;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, String userName, String password)  //login & signUp
    {
        this.clientCommandEnum = clientCommandEnum;
        this.userName = userName;
        this.password = password;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, String authToken)  //leaderBoard & logout
    {
        this.clientCommandEnum = clientCommandEnum;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Account account, String authToken) //save
    {
        this.clientCommandEnum = clientCommandEnum;
        this.account = account;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, ArrayList<TextField> textFieldsToMakeCustom, String authToken) //makeCustom
    {
        this.clientCommandEnum = clientCommandEnum;
        this.textFieldsToMakeCustom = textFieldsToMakeCustom;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Card card, String authToken)  //buy & sell card
    {
        this.clientCommandEnum = clientCommandEnum;
        this.card = card;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Item item, String authToken)  //buy & sell item
    {
        this.clientCommandEnum = clientCommandEnum;
        this.item = item;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Deck deck, String authToken)   //delete deck & import
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deck = deck;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Deck deck, Card card, String authToken)  //add & remove card from deck
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deck = deck;
        this.card = card;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Deck deck, Item item, String authToken)  //add & remove item from deck
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deck = deck;
        this.item = item;
        this.authToken = authToken;
    }

    public ClientCommand(String authToken, ClientCommandEnum clientCommandEnum, String deckName)  //create deck & export
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deckName = deckName;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, int battleMode, Account account)
    {  //9
        this.clientCommandEnum = clientCommandEnum;
        this.battleMode = battleMode;
        this.account = account;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Pane[][] battleFieldPanes, GridPane gridPane)
    { //10
        this.clientCommandEnum = clientCommandEnum;
        this.battleFieldPanes = battleFieldPanes;
        this.gridPane = gridPane;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, int battleMode, Account account, Deck deck)
    {   //11
        this.clientCommandEnum = clientCommandEnum;
        this.battleMode = battleMode;
        this.account = account;
        this.deck = deck;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, int battleMode, Account account, Account secondAccount)
    {   //12
        this.clientCommandEnum = clientCommandEnum;
        this.battleMode = battleMode;
        this.account = account;
        this.secondAccount = secondAccount;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Group root)
    {
        this.clientCommandEnum = clientCommandEnum;
        this.root = root;
    }

    public ClientCommandEnum getClientCommandEnum()
    {
        return clientCommandEnum;
    }

    public void setClientCommandEnum(ClientCommandEnum clientCommandEnum)
    {
        this.clientCommandEnum = clientCommandEnum;
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

    public ArrayList<TextField> getTextFieldsToMakeCustom()
    {
        return textFieldsToMakeCustom;
    }

    public void setTextFieldsToMakeCustom(ArrayList<TextField> textFieldsToMakeCustom)
    {
        this.textFieldsToMakeCustom = textFieldsToMakeCustom;
    }

    public Card getCard()
    {
        return card;
    }

    public void setCard(Card card)
    {
        this.card = card;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public String getDeckName()
    {
        return deckName;
    }

    public void setDeckName(String deckName)
    {
        this.deckName = deckName;
    }

    public Deck getDeck()
    {
        return deck;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }

    public int getBattleMode()
    {
        return battleMode;
    }

    public void setBattleMode(int battleMode)
    {
        this.battleMode = battleMode;
    }

    public Pane[][] getBattleFieldPanes()
    {
        return battleFieldPanes;
    }

    public void setBattleFieldPanes(Pane[][] battleFieldPanes)
    {
        this.battleFieldPanes = battleFieldPanes;
    }

    public GridPane getGridPane()
    {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane)
    {
        this.gridPane = gridPane;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public Account getSecondAccount()
    {
        return secondAccount;
    }

    public void setSecondAccount(Account secondAccount)
    {
        this.secondAccount = secondAccount;
    }

    public Group getRoot()
    {
        return root;
    }

    public void setRoot(Group root)
    {
        this.root = root;
    }

    public String getAuthToken()
    {
        return authToken;
    }
}