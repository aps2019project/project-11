package Controller;


import Model.Battle;
import Model.BattleMode;
import View.Request;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BattleTimer extends Thread
{
    private Group root;
    private Text counterText;
    private Request request;
    private Battle battle;
    private Stage primaryStage;
    private static BattleTimer battleTimer;
    private static int time = 60;

    @Override
    public void run()
    {
        timerLoop();
    }

    public BattleTimer(Group root, Text counterText, Request request, Battle battle, Stage primaryStage)
    {
        this.root = root;
        this.counterText = counterText;
        this.request = request;
        this.battle = battle;
        this.primaryStage = primaryStage;
        setBattleTimer(this);
    }

    public void timerLoop()
    {
        int counter;
        while (true)
        {
            for (counter = getTime(); counter > 0; counter--)
            {
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                counterText.setText(String.valueOf(counter));
            }
            endTurn();
            counterText.setText(null);
        }
    }

    private void endTurn()
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                getBattle().clearTheHandPictures();
                getBattle().endTurn();
                request.setMPIcons(root, getBattle());
                getBattle().setHandIcons();

                request.setNextCard(root, getBattle());

                for (int number = 0; number < 5; number++)
                {
                    root.getChildren().add(getBattle().getCurrentPlayerHand()[number]);
                }
                request.makeBattleFieldController(getBattle());
                request.setGlobalChatButton(primaryStage, root);
            }
        });
    }


    public Group getRoot()
    {
        return root;
    }

    public void setRoot(Group root)
    {
        this.root = root;
    }

    public Text getCounterText()
    {
        return counterText;
    }

    public void setCounterText(Text counterText)
    {
        this.counterText = counterText;
    }

    public Request getRequest()
    {
        return request;
    }

    public void setRequest(Request request)
    {
        this.request = request;
    }

    public static BattleTimer getBattleTimer()
    {
        return battleTimer;
    }

    public void setBattleTimer(BattleTimer battleTimer)
    {
        BattleTimer.battleTimer = battleTimer;
    }

    public void end()
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                counterText.setText(null);
            }
        });
        this.stop();
    }

    public Battle getBattle()
    {
        return battle;
    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        BattleTimer.time = time;
    }
}
