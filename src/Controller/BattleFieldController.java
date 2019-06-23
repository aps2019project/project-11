package Controller;

import Model.*;
import View.Request;
import View.ShowOutput;
import View.SpriteAnimation;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BattleFieldController extends Thread
{
    private boolean isCardSelectedForInsert = false;
    private boolean isCardSelectedInBattle = false;
    private Card selectedCardForInsertingCard;
    private Card selectedCard;
    private Group rootBattleField;
    private BattleManager battleManager = new BattleManager();
    private Scene sceneBattleField;

    public BattleFieldController(Group rootBattleField, Scene sceneBattleField)
    {
        setSceneBattleField(sceneBattleField);
        setRootBattleField(rootBattleField);
    }

    @Override
    public void run()
    {
        super.run();

        setSelectedCard(null);
        setSelectedCardForInsertingCard(null);
        Battle.getCurrentBattle().unSelectCard();


        getCardInformation();
        checkInsertingCard();
        checkSelectingCard();
    }

    private void getCardInformation() {
        int counter = 0;

        ShowOutput showOutput = new ShowOutput();

        /*for(Pane pane : Battle.getCurrentBattle().getFirstPlayerHandPanes()){
            int finalCounter = counter;
            pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    showOutput.showCardInfo(Battle.getCurrentBattle().getFirstPlayer().getHand().getCards().get(finalCounter).getCardID());
                    Text text = new Text("asfsfsddfsdfaaaaaaaaaaaaaaaaa");
                    text.relocate(100 , 110);
                    rootBattleField.getChildren().add(text);
                }
            });
            pane.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    rootBattleField.getChildren().remove(rootBattleField.getChildren().size() - 1);
                }
            });
            counter++;
        }*/
    }

    private void checkInsertingCard() {
        Pane[] firstPlayerHandPanes = Battle.getCurrentBattle().getFirstPlayerHandPanes();
        Pane[] secondPlayerHandPanes = Battle.getCurrentBattle().getSecondPlayerHandPanes();

        for (int number = 0; number < firstPlayerHandPanes.length; number++)
        {
            int finalNumber = number;

            firstPlayerHandPanes[number].setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    if (isCardSelectedForInsert)
                    {
                        //todo to Show error
                    }
                    else
                    {

                        setCardSelectedForInsert(true);
                        setSelectedCardForInsertingCard(Battle.getCurrentBattle().getFirstPlayer().getHand().getCards().get(finalNumber));
                        insertCard();
                    }
                }
            });
        }

        for (int number = 0; number < secondPlayerHandPanes.length; number++)
        {
            int finalNumber = number;

            secondPlayerHandPanes[number].setOnMouseClicked(new EventHandler<MouseEvent>()
            {

                @Override
                public void handle(MouseEvent event) {
                    if (isCardSelectedForInsert) {
                        //todo to show error
                    } else {
                        setCardSelectedForInsert(true);
                        setSelectedCardForInsertingCard(Battle.getCurrentBattle().getSecondPlayer().getHand().getCards().get(finalNumber));
                        insertCard();
                    }
                }
            });
        }

    }

    private void insertCard() {
        Cell[][] battleFieldCells = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {
                int finalColumn1 = column;
                int finalRow1 = row;

                battleFieldCells[row][column].getCellPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (isCardSelectedForInsert) {
                            if (selectedCardForInsertingCard instanceof Minion) {
                                if (battleManager.checkCircumstancesToInsertMinionBoolean((Minion) selectedCardForInsertingCard, finalRow1, finalColumn1)) {
                                    ImageView imageView = Card.getCardImageView(selectedCardForInsertingCard);
                                    setSpriteAnimation(imageView);
                                    battleFieldCells[finalRow1][finalColumn1].getCellPane().getChildren().add(imageView);
                                    battleFieldCells[finalRow1][finalColumn1].setCard(selectedCardForInsertingCard);

                                    ((NonSpellCard) selectedCardForInsertingCard).setMoveAble(false);

                                    Battle.getCurrentBattle().setNextCardPane(rootBattleField);
                                    Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().add(Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard());
                                    Battle.getCurrentBattle().setHandIcons();
                                    Request.getInstance().setMPIcons(rootBattleField);

                                    setCardSelectedForInsert(false);
                                    setSelectedCardForInsertingCard(null);
                                    checkSelectingCard();
                                    run();
                                }
                            } else if (selectedCardForInsertingCard instanceof Spell) {
                                if (battleManager.checkCircumstancesToInsertSpellBoolean((Spell) selectedCardForInsertingCard, finalRow1, finalColumn1)) {

                                    Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().add(Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard());
                                    Battle.getCurrentBattle().setHandIcons();
                                    Request.getInstance().setMPIcons(rootBattleField);

                                    setSelectedCardForInsertingCard(null);
                                    setCardSelectedForInsert(false);
                                    checkSelectingCard();

                                    //todo Animation

                                    run();
                                }
                            }

                        } else {
                            //todo error
                        }
                    }
                });
            }
        }
    }

    private void checkSelectingCard() {
        Cell[][] battleFieldCells = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {

                int finalColumn = column;
                int finalRow = row;

                battleFieldCells[row][column].getCellPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (Battle.getCurrentBattle().getPlayerTurn().getInsertedCards().contains(battleFieldCells[finalRow][finalColumn].getCard()) || Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0).equals((battleFieldCells[finalRow][finalColumn].getCard()))) {
                            System.out.println(battleFieldCells[finalRow][finalColumn].getCard().getCardName());
                            setSelectedCard(battleFieldCells[finalRow][finalColumn].getCard());
                            setCardSelectedInBattle(true);
                            Battle.getCurrentBattle().selectCard(battleFieldCells[finalRow][finalColumn].getCard());
                            selectedCardActions(finalRow, finalColumn);
                        }
                    }
                });
            }
        }
    }

    private void selectedCardActions(int sourceRow, int sourceColumn) {
        Cell[][] battleFieldCells = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {

                int finalRow = row;
                int finalColumn = column;

                battleFieldCells[row][column].getCellPane().setOnScroll(new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent event)
                    {
                        if (Battle.getCurrentBattle().getOpponentPlayer().getInsertedCards().contains(battleFieldCells[finalRow][finalColumn].getCard()) || Battle.getCurrentBattle().getOpponentPlayer().getMainDeck().getHero().get(0).equals((battleFieldCells[finalRow][finalColumn].getCard())))
                        {
                            System.out.println("attackTo");
                            attackTo();
                        } else if (!battleFieldCells[finalRow][finalColumn].isFull()) {
                            moveTo(finalRow, finalColumn, sourceRow, sourceColumn);
                        }
                    }
                });
            }
        }
    }

    private void moveTo(int destinationRow, int destinationColumn, int sourceRow, int sourceColumn)
    {
        if (battleManager.moveCardBoolean(destinationRow, destinationColumn))
        {
            Battle.getCurrentBattle().getBattleFieldPanes()[sourceColumn][sourceRow].getChildren().remove(1);
            ImageView imageView = Card.getCardImageView(selectedCard);
            setSpriteAnimation(imageView);
            Battle.getCurrentBattle().getBattleFieldPanes()[destinationColumn][destinationRow].getChildren().add(imageView);
        }
    }

    private void attackTo() {
    }

    public static void setSpriteAnimation(ImageView imageView) {
        final Animation animation = new SpriteAnimation(imageView, Duration.millis(1000), 3, 3, 0, 0, 80, 80);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public Card getSelectedCardForInsertingCard() {
        return selectedCardForInsertingCard;
    }

    private void setSelectedCardForInsertingCard(Card selectedCardForInsertingCard) {
        this.selectedCardForInsertingCard = selectedCardForInsertingCard;
    }

    public boolean isCardSelectedForInsert() {
        return isCardSelectedForInsert;
    }

    private void setCardSelectedForInsert(boolean cardSelectedForInsert) {
        isCardSelectedForInsert = cardSelectedForInsert;
    }

    public Group getRootBattleField() {
        return rootBattleField;
    }

    private void setRootBattleField(Group rootBattleField) {
        this.rootBattleField = rootBattleField;
    }

    public boolean isCardSelectedInBattle() {
        return isCardSelectedInBattle;
    }

    public void setCardSelectedInBattle(boolean cardSelectedInBattle) {
        isCardSelectedInBattle = cardSelectedInBattle;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public BattleManager getBattleManager() {
        return battleManager;
    }

    public void setBattleManager(BattleManager battleManager) {
        this.battleManager = battleManager;
    }

    public Scene getSceneBattleField() {
        return sceneBattleField;
    }

    public void setSceneBattleField(Scene sceneBattleField) {
        this.sceneBattleField = sceneBattleField;
    }
}
