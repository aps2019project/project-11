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
    private ArrayList<String> textFieldsToMakeCustom = new ArrayList<>();
    private String cardOrItemID;
    private Hero hero;
    private Minion minion;
    private Spell spell;
    private Item item;
    private Deck deck;
    private String deckName;
    private String authToken;
    private ChatMessage chatMessage;

    private transient int battleMode;
    private transient Pane[][] battleFieldPanes;
    private transient GridPane gridPane;
    private transient Account account;
    private transient Account secondAccount;
    private Player player;
    private transient Group root;

    public ClientCommand(ClientCommandEnum clientCommandEnum) //Story
    {
        this.clientCommandEnum = clientCommandEnum;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, String userName, String password)  //login & signUp
    {
        this.clientCommandEnum = clientCommandEnum;
        this.userName = userName;
        this.password = password;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, String authToken)
    {
        this.clientCommandEnum = clientCommandEnum;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, ArrayList<String> textFieldsToMakeCustom, String authToken) //makeCustom
    {
        this.clientCommandEnum = clientCommandEnum;
        this.textFieldsToMakeCustom = textFieldsToMakeCustom;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Card card, String authToken)  //buy & sell card
    {
        this.clientCommandEnum = clientCommandEnum;
        if (card instanceof Hero)
        {
            this.hero = (Hero) card;
        }
        if (card instanceof Minion)
        {
            this.minion = (Minion) card;
        }
        if (card instanceof Spell)
        {
            this.spell = (Spell) card;
        }
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Item item, String authToken)  //buy & sell item
    {
        this.clientCommandEnum = clientCommandEnum;
        this.item = item;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Deck deck, String authToken)   //import
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deck = deck;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, String deckName, String cardOrItemID, String authToken)  //add & remove card from deck
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deckName = deckName;
        this.cardOrItemID = cardOrItemID;
        this.authToken = authToken;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Deck deck, Item item, String authToken)  //add & remove item from deck
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deck = deck;
        this.item = item;
        this.authToken = authToken;
    }

    public ClientCommand(String authToken, ClientCommandEnum clientCommandEnum, String deckName)  //create & delete deck & export & import
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

    public ClientCommand(ClientCommandEnum clientCommandEnum, ChatMessage chatMessage, String authToken) //chat
    {
        this.clientCommandEnum = clientCommandEnum;
        this.chatMessage = chatMessage;
        this.authToken = authToken;
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

    public ArrayList<String> getTextFieldsToMakeCustom()
    {
        return textFieldsToMakeCustom;
    }

    public void setTextFieldsToMakeCustom(ArrayList<String> textFieldsToMakeCustom)
    {
        this.textFieldsToMakeCustom = textFieldsToMakeCustom;
    }

    public Hero getHero()
    {
        return hero;
    }

    public Minion getMinion()
    {
        return minion;
    }

    public Spell getSpell()
    {
        return spell;
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

    public ChatMessage getChatMessage()
    {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage)
    {
        this.chatMessage = chatMessage;
    }

    public String getCardOrItemID()
    {
        return cardOrItemID;
    }
}