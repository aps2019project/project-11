package Network;

import java.io.IOException;
import java.net.Socket;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("127.0.0.1", 8000);

        SendMessageServer sendMessage = new SendMessageServer(socket.getOutputStream());
        sendMessage.start();

        SendMessageServer output = new SendMessageServer(System.out);
        output.start();

        ReceiveMessageServer scanner = new ReceiveMessageServer(System.in);
        scanner.addListener(new CommandReceivedListener()
        {
            @Override
            public void onMessageReceived(String message) throws InterruptedException
            {
                sendMessage.addMessage(message);
            }
        });
        scanner.start();

        ReceiveMessageServer receiveMessage = new ReceiveMessageServer(socket.getInputStream());
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
