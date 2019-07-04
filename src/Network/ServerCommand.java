package Network;

public class ServerCommand
{
    private ServerCommandEnum serverCommandEnum;
    private String errorMessage;
    private boolean login;
    private String authToken;

    public ServerCommand(ServerCommandEnum serverCommandEnum, String errorMessage)   //error
    {
        this.serverCommandEnum = serverCommandEnum;
        this.errorMessage = errorMessage;
    }

    public ServerCommand(ServerCommandEnum serverCommandEnum)    //ok
    {
        this.serverCommandEnum = serverCommandEnum;
    }

    public ServerCommandEnum getServerCommandEnum()
    {
        return serverCommandEnum;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public String getAuthToken()
    {
        return authToken;
    }

    public boolean isLogin()
    {
        return login;
    }

    public void setLogin(boolean login)
    {
        this.login = login;
    }
}
