package Network;

import java.io.IOException;
import java.net.Socket;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("127.0.0.1", 8000);

        SendMessage sendMessage = new SendMessage(socket.getOutputStream());
        sendMessage.start();

        SendMessage output = new SendMessage(System.out);
        output.start();

        ReceiveMessage scanner = new ReceiveMessage(System.in);
        scanner.addListener(new CommandReceivedListener()
        {
            @Override
            public void onMessageReceived(String message) throws InterruptedException
            {
                sendMessage.addMessage(message);
            }
        });
        scanner.start();

        ReceiveMessage receiveMessage = new ReceiveMessage(socket.getInputStream());
        receiveMessage.addListener(new CommandReceivedListener()
        {
            @Override
            public void onMessageReceived(String message) throws InterruptedException
            {
                output.addMessage(message);
            }
        });
        receiveMessage.start();
    }
}
