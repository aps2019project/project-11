package Network;

public interface CommandReceivedListener
{
    void onMessageReceived(String message) throws InterruptedException;
}
