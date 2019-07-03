package Network;

import Model.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ClientCommand
{
    private ClientCommandEnum clientCommandEnum;
    private String userName;
    private String password;
    private Account accountForSave;
    private transient ArrayList<TextField> textFieldsToMakeCustom;
    private Card card;
    private Item item;
    private Deck deck;
    private String deckName;

    private transient int battleMode;
    private transient Pane[][] battleFieldPanes;
    private transient GridPane gridPane;

    public ClientCommand(ClientCommandEnum clientCommandEnum, String userName , String password)  //login & signUp & logout
    {
        this.clientCommandEnum = clientCommandEnum;
        this.userName = userName;
        this.password = password;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum)  //leaderBoard
    {
        this.clientCommandEnum = clientCommandEnum;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Account accountForSave) //save
    {
        this.clientCommandEnum = clientCommandEnum;
        this.accountForSave = accountForSave;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, ArrayList<TextField> textFieldsToMakeCustom) //makeCustom
    {
        this.clientCommandEnum = clientCommandEnum;
        this.textFieldsToMakeCustom = textFieldsToMakeCustom;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Card card)  //buy & sell card
    {
        this.clientCommandEnum = clientCommandEnum;
        this.card = card;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Item item)  //buy & sell item
    {
        this.clientCommandEnum = clientCommandEnum;
        this.item = item;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Deck deck)   //delete deck & import
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deck = deck;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Deck deck, Card card)  //add & remove card from deck
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deck = deck;
        this.card = card;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Deck deck, Item item)  //add & remove item from deck
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deck = deck;
        this.item = item;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, String deckName)  //create deck & export
    {
        this.clientCommandEnum = clientCommandEnum;
        this.deckName = deckName;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, int battleMode) {  //9
        this.clientCommandEnum = clientCommandEnum;
        this.battleMode = battleMode;
    }

    public ClientCommand(ClientCommandEnum clientCommandEnum, Pane[][] battleFieldPanes, GridPane gridPane) { //10
        this.clientCommandEnum = clientCommandEnum;
        this.battleFieldPanes = battleFieldPanes;
        this.gridPane = gridPane;
    }

    public ClientCommandEnum getClientCommandEnum() {
        return clientCommandEnum;
    }

    public void setClientCommandEnum(ClientCommandEnum clientCommandEnum) {
        this.clientCommandEnum = clientCommandEnum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccountForSave() {
        return accountForSave;
    }

    public void setAccountForSave(Account accountForSave) {
        this.accountForSave = accountForSave;
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

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getBattleMode() {
        return battleMode;
    }

    public void setBattleMode(int battleMode) {
        this.battleMode = battleMode;
    }

    public Pane[][] getBattleFieldPanes() {
        return battleFieldPanes;
    }

    public void setBattleFieldPanes(Pane[][] battleFieldPanes) {
        this.battleFieldPanes = battleFieldPanes;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }
}