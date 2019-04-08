import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        accountCommands();
        showCommandLine();
    }

    private static void showCommandLine() {
        //ToDo
        while (true) {
            String line = myScanner.nextLine();
        }
    }

    public static void accountCommands() {
        while (true) {

        }
    }

    public static void shopCommands() {
        while (true) {
            String line = myScanner.nextLine();
            if (line.matches("exit")) {
                return;
            }
        }
    }

    public static void collectionCommands() {
        while (true) {
            String line = myScanner.nextLine();
            if (line.matches("exit")) {
                return;
            }
        }
    }

    public static void battleCommands()
    {
        String battleMode = myScanner.nextLine();
        //todo
        while (true)
        {
            String line = myScanner.nextLine();

        }
    }
}
