package NetWork;

public interface CommandReceivedListener
{
    void onMessageReceived(String message) throws InterruptedException;
}
