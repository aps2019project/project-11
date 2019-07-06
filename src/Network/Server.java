package Network;

import Model.*;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server
{
    private static ArrayList<InputCommandHandlerForServer> commandHandlers = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Shop shop = new Shop();
    private static ArrayList<Hero> heroes = new ArrayList<>();
    private static ArrayList<Minion> minions = new ArrayList<>();
    private static ArrayList<Spell> spells = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();
    private static int numberOfAccount = 1;

    public static void main(String[] args) throws Exception
    {
        Card.setCards();
        Item.setItems();
        convertingToAccounts();
        convertingToShop();
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true)
        {
            Socket socket = serverSocket.accept();
            System.out.println("new Client accepted");

            SendMessage output = new SendMessage(System.out);
            output.start();

            SendMessage sendMessage = new SendMessage(socket.getOutputStream());
            sendMessage.start();

            InputCommandHandlerForServer handleCommand = new InputCommandHandlerForServer(sendMessage);
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
                }
            });
            receiveMessage.start();
        }
    }

    private static void convertingToShop()
    {
        try
        {
            FileReader reader = new FileReader("shop.json");
            JsonParser jsonParser = new JsonParser();
            Object object = jsonParser.parse(reader);
            setShop(new Gson().fromJson(object.toString(),Shop.class));
        }
        catch (FileNotFoundException ignored)
        {

        }
    }

    private static void convertingToAccounts() throws Exception
    {
        InputStream inputStream = new FileInputStream("SavedAccounts/SavedAccountPath.txt");
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext())
        {
            String fileName = scanner.nextLine();

            JsonParser jsonParser = new JsonParser();
            FileReader reader = new FileReader("SavedAccounts/" + fileName + ".json");
            Object obj = jsonParser.parse(reader);
            System.out.println(obj);
            Server.getAccounts().add(new Gson().fromJson(obj.toString(), Account.class));
        }
    }

    public static ArrayList<InputCommandHandlerForServer> getCommandHandlers()
    {
        return commandHandlers;
    }

    public static ArrayList<Account> getAccounts()
    {
        return accounts;
    }

    public static void addAccount(Account account)
    {
        accounts.add(account);
    }

    public static ArrayList<Hero> getHeroes()
    {
        return heroes;
    }

    public static void addHero(Hero hero)
    {
        heroes.add(hero);
    }

    public static ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public static void addMinion(Minion minion)
    {
        minions.add(minion);
    }

    public static ArrayList<Spell> getSpells()
    {
        return spells;
    }

    public static void addSpell(Spell spell)
    {
        spells.add(spell);
    }

    public static ArrayList<Item> getItems()
    {
        return items;
    }

    public static void addItem(Item item)
    {
        items.add(item);
    }

    public static int getNumberOfAccount()
    {
        return numberOfAccount;
    }

    public static void setNumberOfAccount(int numberOfAccount)
    {
        Server.numberOfAccount = numberOfAccount;
    }

    public static Shop getShop()
    {
        return shop;
    }

    public static void setShop(Shop shop)
    {
        Server.shop = shop;
    }
}
