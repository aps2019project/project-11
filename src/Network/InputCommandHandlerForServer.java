package Network;

import Controller.AccountManager;
import Model.Account;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class InputCommandHandlerForServer extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessage sendMessage;

    public InputCommandHandlerForServer(SendMessage sendMessage)
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
                checkMassageSentByClient(getMessage());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void checkMassageSentByClient(String commandSentByClient)
    {
        //switch case
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