package Network;

import Model.Account;
import Model.CommandType;
import Model.Deck;
import javafx.scene.Group;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ClientCommands {
    private CommandsEnum clientCommand;
    private String userName;
    private String passWord;
    private Account accountForSave;
    private String userNameForSave;
    private boolean isNewAccount;
    private Account account;    //someTime use it for login
    private CommandType commandType;
    private ArrayList<TextField> textFieldsToMkeCustom;
    private Group rootLeaderBoard;
    private Account loggedInAccount;
    private Deck deck;


    public ClientCommands(CommandsEnum clientCommand , String userName , String passWord ){ //1
        this.clientCommand = clientCommand;
        this.userName = userName;
        this.passWord = passWord;
    }

    public ClientCommands(CommandsEnum clientCommand , Account accountForSave , String userNameForSave , boolean isNewAccount) //2
    {
        this.clientCommand = clientCommand;
        this.accountForSave = accountForSave;
        this.userNameForSave = userNameForSave;
        this.isNewAccount = isNewAccount;
    }

    public ClientCommands(CommandsEnum clientCommand , Account account){ // 3
        this.clientCommand = clientCommand;
        this.account = account;
    }

    public ClientCommands(CommandsEnum clientCommand, CommandType commandType) {   //4
        this.clientCommand = clientCommand;
        this.commandType = commandType;
    }

    public ClientCommands(CommandsEnum clientCommand, ArrayList<TextField> textFieldsToMkeCustom) { //5
        this.clientCommand = clientCommand;
        this.textFieldsToMkeCustom = textFieldsToMkeCustom;
    }

    public ClientCommands(CommandsEnum clientCommand, Group rootLeaderBoard) {  //6
        this.clientCommand = clientCommand;
        this.rootLeaderBoard = rootLeaderBoard;
    }

    public ClientCommands(CommandsEnum clientCommand) { //7
        this.clientCommand = clientCommand;
    }

    public ClientCommands(CommandsEnum clientCommand , Deck deck){
        this.clientCommand = clientCommand;
        this.deck = deck;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Account getAccountForSave() {
        return accountForSave;
    }

    public void setAccountForSave(Account accountForSave) {
        this.accountForSave = accountForSave;
    }

    public String getUserNameForSave() {
        return userNameForSave;
    }

    public void setUserNameForSave(String userNameForSave) {
        this.userNameForSave = userNameForSave;
    }

    public boolean isNewAccount() {
        return isNewAccount;
    }

    public void setNewAccount(boolean newAccount) {
        isNewAccount = newAccount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public ArrayList<TextField> getTextFieldsToMkeCustom() {
        return textFieldsToMkeCustom;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }

    public Group getRootLeaderBoard() {
        return rootLeaderBoard;
    }

    public void setRootLeaderBoard(Group rootLeaderBoard) {
        this.rootLeaderBoard = rootLeaderBoard;
    }
}
