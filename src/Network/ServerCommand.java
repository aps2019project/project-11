package Network;

public class ServerCommand
{
    private ServerCommand serverCommand;
    private String errorMessage;

    public ServerCommand(ServerCommand serverCommand, String errorMessage)
    {
        this.serverCommand = serverCommand;
        this.errorMessage = errorMessage;
    }

    public ServerCommand getServerCommand()
    {
        return serverCommand;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}
