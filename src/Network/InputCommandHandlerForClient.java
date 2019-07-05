package Network;

import com.google.gson.Gson;

public class InputCommandHandlerForClient extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessage sendMessage;
    private Client client;

    public InputCommandHandlerForClient(Client client, SendMessage sendMessage)
    {
        this.client = client;
        this.sendMessage = sendMessage;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                synchronized (validMessageLock)
                {
                    if (message == null)
                    {
                        validMessageLock.wait();
                    }
                }
                checkMassageSentByServer(getMessage());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void checkMassageSentByServer(String commandSentByServer)
    {
        ServerCommand serverCommand = new Gson().fromJson(commandSentByServer, ServerCommand.class);
        switch (serverCommand.getServerCommandEnum())
        {
            case ERROR:
                client.getRequest().setMessageFromServer(serverCommand.getErrorMessage());
                synchronized (client.getRequest().validMessageFromServer)
                {
                    client.getRequest().validMessageFromServer.notify();
                }
                break;
            case OK:
                client.getRequest().setMessageFromServer("OK");
                if (serverCommand.isLogin())
                {
                    client.setAuthToken(serverCommand.getAuthToken());
                }
                synchronized (client.getRequest().validMessageFromServer)
                {
                    client.getRequest().validMessageFromServer.notify();
                }
                break;
        }

        message = null;
    }

    public synchronized void setMessage(String message)
    {
        this.message = message;
        synchronized (validMessageLock)
        {
            validMessageLock.notify();
        }
    }

    public synchronized String getMessage() throws InterruptedException
    {
        return message;
    }

    public SendMessage getSendMessage()
    {
        return sendMessage;
    }

}