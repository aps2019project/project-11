package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
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
        ObjectInputStream objectInputStream = null;
        try
        {
            objectInputStream = new ObjectInputStream(inputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            try
            {
                String inputCommand = (String) Objects.requireNonNull(objectInputStream).readObject();
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
            catch (Exception ignored)
            {

            }
        }
    }

    public void addListener(CommandReceivedListener listener)
    {
        listeners.add(listener);
    }
}
