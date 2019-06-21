package Controller;

import Model.*;
import View.SpriteAnimation;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BattleFieldController extends Thread {
    private boolean isCardSelectedForInsert = false;
    private boolean isCardSelectedInBattle = false;
    private Card selectedCardForInsertingCard;
    private Card selectedCard;
    private Group rootBattleField;

    public BattleFieldController(Group rootBattleField) {
        setRootBattleField(rootBattleField);
    }

    @Override
    public void run() {
        super.run();
        checkInsertingCard();
        checkSelectingCard();
    }

    private void checkInsertingCard() {
        Pane[] firstPlayerHandPanes = Battle.getCurrentBattle().getFirstPlayerHandPanes();
        Pane[] secondPlayerHandPanes = Battle.getCurrentBattle().getSecondPlayerHandPanes();

        for (int number = 0; number < firstPlayerHandPanes.length; number++) {
            int finalNumber = number;

            firstPlayerHandPanes[number].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isCardSelectedForInsert) {
                        //todo to Show error
                    } else {

                        setCardSelectedForInsert(true);
                        setSelectedCardForInsertingCard(Battle.getCurrentBattle().getFirstPlayer().getHand().getCards().get(finalNumber));
                        insertCard();
                    }
                }
            });
        }

        for (int number = 0; number < secondPlayerHandPanes.length; number++) {
            int finalNumber = number;

            secondPlayerHandPanes[number].setOnMouseClicked(new EventHandler<MouseEvent>() {

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
                            BattleManager battleManager = new BattleManager();
                            if (selectedCardForInsertingCard instanceof Minion) {
                                if (battleManager.checkCircumstancesToInsertMinionBoolean((Minion) selectedCardForInsertingCard, finalRow1, finalColumn1)) {
                                    ImageView imageView = Card.getCardImageView(selectedCardForInsertingCard);
                                    setSpriteAnimation(imageView);
                                    battleFieldCells[finalRow1][finalColumn1].getCellPane().getChildren().add(imageView);

                                    Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().add(Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard());
                                    Battle.getCurrentBattle().setHandIcons();
                                    Battle.getCurrentBattle().setMPIcons(rootBattleField);

                                    setCardSelectedForInsert(false);
                                    setSelectedCardForInsertingCard(null);
                                    checkSelectingCard();
                                    event.consume();
                                } else {
                                    event.consume();
                                }
                            } else if (selectedCardForInsertingCard instanceof Spell) {
                                if (battleManager.checkCircumstancesToInsertSpellBoolean((Spell) selectedCardForInsertingCard, finalRow1, finalColumn1)) {

                                    Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().add(Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard());
                                    Battle.getCurrentBattle().setHandIcons();
                                    Battle.getCurrentBattle().setMPIcons(rootBattleField);

                                    setSelectedCardForInsertingCard(null);
                                    setCardSelectedForInsert(false);
                                    checkSelectingCard();


                                    //todo Animation

                                    event.consume();
                                } else {
                                    event.consume();
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

                int finalColumn1 = column;
                int finalRow1 = row;

                battleFieldCells[row][column].getCellPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (Battle.getCurrentBattle().getPlayerTurn().getInsertedCards().contains(battleFieldCells[finalRow1][finalColumn1].getCard()) ||
                                Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0).equals((battleFieldCells[finalRow1][finalColumn1].getCard()))) {
                            System.out.println(battleFieldCells[finalRow1][finalColumn1].getCard().getCardName());
                            setSelectedCard(battleFieldCells[finalRow1][finalColumn1].getCard());
                            setCardSelectedInBattle(true);
                            selectedCardActions();
                        }
                    }
                });
            }
        }
    }

    private void selectedCardActions() {
        Cell[][] battleFieldCells = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {

                int finalColumn1 = column;
                int finalRow1 = row;

                battleFieldCells[row][column].getCellPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(Battle.getCurrentBattle().getOpponentPlayer().getInsertedCards().contains(battleFieldCells[finalRow1][finalColumn1].getCard()) ||
                                Battle.getCurrentBattle().getOpponentPlayer().getMainDeck().getHero().get(0).equals((battleFieldCells[finalRow1][finalColumn1].getCard()))){
                            System.out.println("attackTo");
                            attackTo();
                        }

                        else if(!battleFieldCells[finalRow1][finalColumn1].isFull()){
                            System.out.println("move");
                        }


                    }
                });
            }
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
}
