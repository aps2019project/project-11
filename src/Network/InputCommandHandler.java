package Network;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class InputCommandHandler extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessage sendMessage;

    public InputCommandHandler(SendMessage sendMessage)
    {
        this.sendMessage = sendMessage;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                while (true) {
                    System.out.println("Waiting to finish the game");

                }
            }
        }
        catch (Exception ignored)
        {

        }
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