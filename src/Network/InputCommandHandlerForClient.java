package Network;

public class InputCommandHandlerForClient extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessage sendMessage;

    public InputCommandHandlerForClient(SendMessage sendMessage)
    {
        this.sendMessage = sendMessage;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                checkMassageSentByServer(getMessage());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void checkMassageSentByServer(String CommandSentByServer)
    {
        // switch case
        message = null;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage() throws InterruptedException
    {
        synchronized (validMessageLock)
        {
            if (message == null)
            {
                validMessageLock.wait();
            }

        }
        return message.trim();
    }

    public SendMessage getSendMessage()
    {
        return sendMessage;
    }

}