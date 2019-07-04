package Network;

public class ServerCommand
{
    private ServerCommandEnum serverCommandEnum;
    private String errorMessage;

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
}
