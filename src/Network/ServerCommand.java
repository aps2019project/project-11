package Network;

public class ServerCommand
{
    private ServerCommandEnum serverCommandEnum;
    private String errorMessage;

    public ServerCommand(ServerCommandEnum serverCommandEnum, String errorMessage)
    {
        this.serverCommandEnum = serverCommandEnum;
        this.errorMessage = errorMessage;
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
