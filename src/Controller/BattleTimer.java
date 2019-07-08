package Controller;


import Model.Battle;
import Model.BattleMode;
import View.Request;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BattleTimer extends Thread{
    private Group rootBattleField;
    private Text counterText;
    private Request request;
    private BattleMode battleMode;
    private Stage primaryStage;
    private static BattleTimer battleTimer;


    @Override
    public void run() {
        timerLoop();
    }

    public BattleTimer(Group rootBattleField, Text counterText, Request request, BattleMode battleMode, Stage primaryStage){
        this.rootBattleField = rootBattleField;
        this.counterText = counterText;
        this.request = request;
        this.battleMode = battleMode;
        this.primaryStage = primaryStage;
        setBattleTimer(this);
    }

    public void timerLoop() {
        int counter;
        while (true){
            for(counter = 60 ; counter > 0  ; counter--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counterText.setText(String.valueOf(counter));
            }
            endTurn();
            counterText.setText(null);
        }
    }

    private void endTurn() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Battle.getCurrentBattle().clearTheHandPictures();
                Battle.getCurrentBattle().endTurn();
                request.setMPIcons(rootBattleField);
                Battle.getCurrentBattle().setHandIcons();

                request.setNextCard(rootBattleField);

                for (int number = 0; number < 5; number++)
                {
                    rootBattleField.getChildren().add(Battle.getCurrentBattle().getCurrentPlayerHand()[number]);
                }
                request.makeBattleFieldController(battleMode);
                request.setGlobalChatButton(primaryStage, rootBattleField);
            }
        });
    }


    public Group getRootBattleField() {
        return rootBattleField;
    }

    public void setRootBattleField(Group rootBattleField) {
        this.rootBattleField = rootBattleField;
    }

    public Text getCounterText() {
        return counterText;
    }

    public void setCounterText(Text counterText) {
        this.counterText = counterText;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public static BattleTimer getBattleTimer() {
        return battleTimer;
    }

    public void setBattleTimer(BattleTimer battleTimer) {
        BattleTimer.battleTimer = battleTimer;
    }

    public void end() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                counterText.setText(null);
            }
        });
        this.stop();
    }
}
