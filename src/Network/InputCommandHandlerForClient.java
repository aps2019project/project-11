package Network;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
        checkMassage();
    }

    private void checkMassage()
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

    public SendMessage getSendMessage()
    {
        return sendMessage;
    }

}