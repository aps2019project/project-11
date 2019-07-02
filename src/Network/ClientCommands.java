package Network;

import Model.Account;

public enum ClientCommands {
    SIGN_UP
    ,SIGN_UP_FIND_ACCOUNT
    ,SAVE_ACCOUNT_INFO
    ,
    ;



    String signUpUserName;
    String signUpPassWord;
    Account accountForSave;
    String userNameForSave;
    boolean isNewAcount;

}
