package Network;

public class InputCommandHandler extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessageServer sendMessage;

    public InputCommandHandler(SendMessageServer sendMessage)
    {
        this.sendMessage = sendMessage;
    }

    @Override
    public void run()
    {

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

    public SendMessageServer getSendMessage()
    {
        return sendMessage;
    }

}