package Network;

import com.google.gson.Gson;

public class InputCommandHandlerForServer extends Thread
{
    public final Object validMessageLock = new Object();
    private String message;
    private SendMessage sendMessage;

    public InputCommandHandlerForServer(SendMessage sendMessage)
    {
        this.sendMessage = sendMessage;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                synchronized (validMessageLock)
                {
                    if (message == null)
                    {
                        validMessageLock.wait();
                    }
                }
                checkMassageSentByClient(getMessage());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void checkMassageSentByClient(String commandSentByClient)
    {
        ClientCommand clientCommand = new Gson().fromJson(commandSentByClient, ClientCommand.class);
        switch (clientCommand.getClientCommandEnum())
        {
            case SIGN_UP:

                break;
            case LOGIN:
                break;
            case LOGOUT:
                break;
            case MAKE_CUSTOM_SPELL:
                break;
            case MAKE_CUSTOM_MINION:
                break;
            case MAKE_CUSTOM_HERO:
                break;
            case LEADER_BOARD:
                break;
            case SAVE_ACCOUNT_INFO:
                break;
            case BUY:
                break;
            case SELL:
                break;
            case IMPORT_DECK:
                break;
            case EXPORT_DECK:
                break;
            case CREATE_DECK:
                break;
            case REMOVE_CARD_FROM_DECK:
                break;
            case ADD_CARD_TO_DECK:
                break;


                case MAKE_STORY_BATTLE:
                break;
            case MAKE_CUSTOM_BATTLE:
                break;
            case MAKE_MULTI_PLAYER_BATTLE:
                break;
            case GET_ALL_OF_THE_ACCOUNTS:
                break;
            case SET_BATTLEFIELD_PANES_AND_GRIDPANE:
                break;
            case SET_NEXT_CARD_PANE:
                break;
            case SHOW_BATTLE_INFO:
                break;
            case GET_FIRST_PLAYER:
                break;
            case GET_SECOND_PLAYER:
                break;
            case TASKS_WHEN_SURRENDER:
                break;
            case GET_PLAYER_TURN_GRAVE_YARD_CARDS:
                break;
            case CLEAR_HAND_PANES_IMAGEVIEW_AND_END_TURN_AND_SET_HAND_ICON:
                break;
            case SET_HEROES_FIRST_PLACE:
                break;
            case SET_HERO_ICONS:
                break;
            case SET_HAND_ICONS:
                break;
            case SET_GRID_PANE:
                break;
        }
        message = null;
    }

    public synchronized void setMessage(String message)
    {
        this.message = message;
        synchronized (validMessageLock)
        {
            validMessageLock.notify();
        }
    }

    public synchronized String getMessage()
    {
        return message;
    }

    public SendMessage getSendMessage()
    {
        return sendMessage;
    }
}