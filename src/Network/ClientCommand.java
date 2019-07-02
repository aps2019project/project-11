package Network;

public enum ClientCommand
{
    SIGN_UP,
    LOG_IN,
    SAVE_ACCOUNT_INFO,
    LOGIN_IN_ACCOUNT_MANAGER,
    MAIN_MENU_COMMAND,
    MAKE_CUSTOM_SPELL,
    MAKE_CUSTOM_MINION,
    MAKE_CUSTOM_HERO,
    LEADER_BOARD,
    ;

    private String userName;
    private String password;

    private static ClientCommand clientCommand;

    public static ClientCommand getClientCommand()
    {
        return clientCommand;
    }
}
