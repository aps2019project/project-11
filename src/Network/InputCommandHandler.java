package Network;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class InputCommandHandler extends Thread
{
    private static Pattern patternNewGame = Pattern.compile("new game \\w+");
    private static Pattern patternSetDefaultTable = Pattern.compile("set table");
    private static Pattern patternSetDesiredTable = Pattern.compile("set table \\d+\\*\\d+");
    private static Pattern patternNumber = Pattern.compile("\\d+");

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
                while (true)
                {
                    System.out.println("Waiting to finish the game");
                    /*if (!this.getPlayer().isAlreadyPlaying())
                    {
                        break;
                    }*/
                }
                //mainMenu();
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