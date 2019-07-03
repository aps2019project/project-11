package Network;

public enum ServerCommand
{
    OK,
    ERROR,
    ;

    private String errorMessage;

    private static ServerCommand serverCommand;

    public static ServerCommand getServerCommand()
    {
        return serverCommand;
    }

    public static void setServerCommand(ServerCommand serverCommand)
    {
        ServerCommand.serverCommand = serverCommand;
    }
}
