package Network;

import Model.Account;
import Model.CommandType;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ClientCommands {
    private ClientCommandsEnum clientCommand;
    private String userName;
    private String passWord;
    private Account accountForSave;
    private String userNameForSave;
    private boolean isNewAccount;
    private Account accountForLogin;
    private CommandType commandType;
    private ArrayList<TextField> textFieldsToMkeCustom;


    public ClientCommands(ClientCommandsEnum clientCommand ,String userName ,String passWord ){ //1
        this.clientCommand = clientCommand;
        this.userName = userName;
        this.passWord = passWord;
    }

    public ClientCommands(ClientCommandsEnum clientCommand , Account accountForSave , String userNameForSave , boolean isNewAccount) //2
    {
        this.clientCommand = clientCommand;
        this.accountForSave = accountForSave;
        this.userNameForSave = userNameForSave;
        this.isNewAccount = isNewAccount;
    }

    public ClientCommands(ClientCommandsEnum clientCommand , Account accountForLogIn){ // 3
        this.clientCommand = clientCommand;
        this.accountForLogin = accountForLogIn;
    }

    public ClientCommands(ClientCommandsEnum clientCommand, CommandType commandType) {   //4
        this.clientCommand = clientCommand;
        this.commandType = commandType;
    }

    public ClientCommands(ClientCommandsEnum clientCommand, ArrayList<TextField> textFieldsToMkeCustom) { //5
        this.clientCommand = clientCommand;
        this.textFieldsToMkeCustom = textFieldsToMkeCustom;
    }

    public ClientCommandsEnum getClientCommand() {
        return clientCommand;
    }

    public void setClientCommand(ClientCommandsEnum clientCommand) {
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

    public Account getAccountForLogin() {
        return accountForLogin;
    }

    public void setAccountForLogin(Account accountForLogin) {
        this.accountForLogin = accountForLogin;
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
}
