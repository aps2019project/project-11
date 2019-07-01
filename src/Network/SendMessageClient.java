package Network;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class SendMessageClient extends Thread
{
    private BlockingDeque<String> messages = new LinkedBlockingDeque<>();
    private OutputStream outputStream;

    SendMessageClient(OutputStream outputStream)
    {
        this.outputStream = outputStream;
    }

    @Override
    public void run()
    {
        while (true)
        {
            PrintStream printStream = new PrintStream(outputStream);
            try
            {
                printStream.println(messages.take());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    void addMessage(String message) throws InterruptedException
    {
        messages.put(message);
    }
}
