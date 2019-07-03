package Network;

import Model.*;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ClientCommands
{
    private CommandsEnum clientCommand;
    private String userName;
    private String password;
    private Account accountForSave;
    private ArrayList<TextField> textFieldsToMakeCustom;
    private Card card;
    private Item item;
    private Deck deck;
    private String deckName;

    private int battleMode;
    private Pane[][] battleFieldPanes;
    private GridPane gridPane;

    public ClientCommands(CommandsEnum clientCommand , String userName , String password)  //login & signUp & logout
    {
        this.clientCommand = clientCommand;
        this.userName = userName;
        this.password = password;
    }

    public ClientCommands(CommandsEnum clientCommand)  //leaderBoard
    {
        this.clientCommand = clientCommand;
    }

    public ClientCommands(CommandsEnum clientCommand, Account accountForSave) //save
    {
        this.clientCommand = clientCommand;
        this.accountForSave = accountForSave;
    }

    public ClientCommands(CommandsEnum clientCommand, ArrayList<TextField> textFieldsToMakeCustom) //makeCustom
    {
        this.clientCommand = clientCommand;
        this.textFieldsToMakeCustom = textFieldsToMakeCustom;
    }

    public ClientCommands(CommandsEnum clientCommand, Card card)  //buy & sell card
    {
        this.clientCommand = clientCommand;
        this.card = card;
    }

    public ClientCommands(CommandsEnum clientCommand, Item item)  //buy & sell item
    {
        this.clientCommand = clientCommand;
        this.item = item;
    }

    public ClientCommands(CommandsEnum clientCommand, Deck deck)   //delete deck & import
    {
        this.clientCommand = clientCommand;
        this.deck = deck;
    }

    public ClientCommands(CommandsEnum clientCommand, Deck deck, Card card)  //add & remove card from deck
    {
        this.clientCommand = clientCommand;
        this.deck = deck;
        this.card = card;
    }

    public ClientCommands(CommandsEnum clientCommand, Deck deck, Item item)  //add & remove item from deck
    {
        this.clientCommand = clientCommand;
        this.deck = deck;
        this.item = item;
    }

    public ClientCommands(CommandsEnum clientCommand, String deckName)  //create deck & export
    {
        this.clientCommand = clientCommand;
        this.deckName = deckName;
    }

    public ClientCommands(CommandsEnum clientCommand, int battleMode) {  //9
        this.clientCommand = clientCommand;
        this.battleMode = battleMode;
    }

    public ClientCommands(CommandsEnum clientCommand, Pane[][] battleFieldPanes, GridPane gridPane) { //10
        this.clientCommand = clientCommand;
        this.battleFieldPanes = battleFieldPanes;
        this.gridPane = gridPane;
    }

    public CommandsEnum getClientCommand() {
        return clientCommand;
    }

    public void setClientCommand(CommandsEnum clientCommand) {
        this.clientCommand = clientCommand;
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