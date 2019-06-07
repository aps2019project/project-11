package View;

import Model.Battle;
import javafx.scene.input.MouseEvent;

public class Controller {
    public void endTurn(){
        Battle.getCurrentBattle().endTurn();
    }

}
