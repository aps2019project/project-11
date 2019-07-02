package Network;

import Model.Account;

public class ClientCommands {
    private ClientCommandsEnum clientCommand;
    private String signUpUserName;
    private String signUpPassWord;
    private Account accountForSave;
    private String userNameForSave;
    private boolean isNewAccount;

    public ClientCommands(ClientCommandsEnum clientCommand ,String signUpUserName ,String signUpPassWord ){ //1
        this.clientCommand = clientCommand;
        this.signUpUserName = signUpUserName;
        this.signUpPassWord = signUpPassWord;
    }

    public ClientCommands(ClientCommandsEnum clientCommand , Account accountForSave , String userNameForSave , boolean isNewAccount) //2
    {
        this.clientCommand = clientCommand;
        this.accountForSave = accountForSave;
        this.userNameForSave = userNameForSave;
        this.isNewAccount = isNewAccount;
    }


    public ClientCommandsEnum getClientCommand() {
        return clientCommand;
    }

    public void setClientCommand(ClientCommandsEnum clientCommand) {
        this.clientCommand = clientCommand;
    }

    public String getSignUpUserName() {
        return signUpUserName;
    }

    public void setSignUpUserName(String signUpUserName) {
        this.signUpUserName = signUpUserName;
    }

    public String getSignUpPassWord() {
        return signUpPassWord;
    }

    public void setSignUpPassWord(String signUpPassWord) {
        this.signUpPassWord = signUpPassWord;
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
}
