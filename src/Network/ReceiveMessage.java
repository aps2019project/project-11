package Network;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReceiveMessage extends Thread
{
    private ArrayList<CommandReceivedListener> listeners = new ArrayList<>();
    private InputStream inputStream;

    public ReceiveMessage(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    @Override
    public void run()
    {
        Scanner scanner = new Scanner(inputStream);
        while (true)
        {
            try
            {
                String inputCommand = scanner.nextLine();
                for (CommandReceivedListener listener : listeners)
                {
                    try
                    {
                        listener.onMessageReceived(inputCommand);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            catch (NoSuchElementException ignored)
            {

            }
        }
    }

    public void addListener(CommandReceivedListener listener)
    {
        listeners.add(listener);
    }
}
