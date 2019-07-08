package Controller;


import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sun.net.TelnetInputStream;

public class BattleTimer extends Thread{
    private Group rootBattleField;
    private Text counterText;

    public BattleTimer(Group rootBattleField, Text counterText){
        this.rootBattleField = rootBattleField;
        this.counterText = counterText;
    }


    @Override
    public void run() {
        super.run();
        timerLoop();
    }

    private void timerLoop() {
        int counter;
        while (true){
            for(counter = 0 ; counter <= 60 ; counter++){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counterText.setText(String.valueOf(counter));
            }
        }
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
}
