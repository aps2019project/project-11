package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
    private static ArrayList<InputCommandHandler> commandHandlers = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true)
        {
            Socket socket = serverSocket.accept();
            System.out.println("new Client accepted");

            SendMessage output = new SendMessage(System.out);
            output.start();

            SendMessage sendMessage = new SendMessage(socket.getOutputStream());
            sendMessage.start();

            InputCommandHandler handleCommand = new InputCommandHandler(sendMessage);
            handleCommand.setDaemon(true);
            commandHandlers.add(handleCommand);
            handleCommand.start();

            ReceiveMessage receiveMessage = new ReceiveMessage(socket.getInputStream());
            receiveMessage.addListener(new CommandReceivedListener()
            {
                @Override
                public void onMessageReceived(String message) throws InterruptedException
                {
                    output.addMessage(message);
                    handleCommand.setMessage(message);
                    synchronized (handleCommand.validMessageLock)
                    {
                        handleCommand.validMessageLock.notify();
                    }
                }
            });
            receiveMessage.start();
        }
    }

    /*public static InputCommandHandler findCommandHandler(Player player)
    {
        for (InputCommandHandler inputCommandHandler : commandHandlers)
        {
            if (inputCommandHandler.getPlayer().getName().equals(player.getName()))
            {
                return inputCommandHandler;
            }
        }
        return null;
    }*/
}
