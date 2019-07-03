package Network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class SendMessage extends Thread
{
    private BlockingDeque<String> messages = new LinkedBlockingDeque<>();
    private OutputStream outputStream;

    public SendMessage(OutputStream outputStream)
    {
        this.outputStream = outputStream;
    }

    @Override
    public void run()
    {
        ObjectOutputStream objectOutputStream = null;
        try
        {
            objectOutputStream = new ObjectOutputStream(outputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            try
            {
                Objects.requireNonNull(objectOutputStream).writeObject(messages.take());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void addMessage(String message) throws InterruptedException
    {
        messages.put(message);
    }
}
