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

public class BattleFieldController extends Thread
{
    private boolean isCardSelectedForInsert = false;
    private boolean isCardSelectedForMove = false;
    private Card selectedCard;
    private Group rootBattleField;

    public BattleFieldController(Group rootBattleField)
    {
        setRootBattleField(rootBattleField);
    }

    @Override
    public void run()
    {
        super.run();
        checkInsertingCard();
        checkSelectingCard();
    }

    private void checkInsertingCard()
    {
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
                        setSelectedCard(Battle.getCurrentBattle().getFirstPlayer().getHand().getCards().get(finalNumber));
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
                public void handle(MouseEvent event)
                {
                    if (isCardSelectedForInsert)
                    {
                        //todo to show error
                    }
                    else
                    {
                        setCardSelectedForInsert(true);
                        setSelectedCard(Battle.getCurrentBattle().getSecondPlayer().getHand().getCards().get(finalNumber));
                        insertCard();
                    }
                }
            });
        }

    }

    private void insertCard()
    {
        Cell[][] battleFieldCells = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++)
        {
            for (int column = 0; column < 9; column++)
            {
                int finalColumn1 = column;
                int finalRow1 = row;

                battleFieldCells[row][column].getCellPane().setOnMouseClicked(new EventHandler<MouseEvent>()
                {

                    @Override
                    public void handle(MouseEvent event)
                    {
                        if (isCardSelectedForInsert)
                        {
                            BattleManager battleManager = new BattleManager();
                            if (selectedCard instanceof Minion)
                            {
                                if (battleManager.checkCircumstancesToInsertMinionBoolean((Minion) selectedCard, finalRow1, finalColumn1))
                                {
                                    ImageView imageView = Card.getCardImageView(selectedCard);
                                    setSpriteAnimation(imageView);
                                    battleFieldCells[finalRow1][finalColumn1].getCellPane().getChildren().add(imageView);

                                    Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().add(Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard());
                                    Battle.getCurrentBattle().setHandIcons();
                                    Battle.getCurrentBattle().setMPIcons(rootBattleField);

                                    setCardSelectedForInsert(false);
                                    setSelectedCard(null);
                                    checkSelectingCard();
                                }
                            }
                            else if (selectedCard instanceof Spell)
                            {
                                if (battleManager.checkCircumstancesToInsertSpellBoolean((Spell) selectedCard, finalRow1, finalColumn1))
                                {

                                    Battle.getCurrentBattle().getPlayerTurn().getHand().getCards().add(Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard());
                                    Battle.getCurrentBattle().setHandIcons();
                                    Battle.getCurrentBattle().setMPIcons(rootBattleField);

                                    setSelectedCard(null);
                                    setCardSelectedForInsert(false);
                                    checkSelectingCard();


                                    //todo Animation
                                }
                            }

                        }
                        else
                        {
                            //todo error
                        }
                    }
                });
            }
        }
    }

    private void checkSelectingCard()
    {
        Cell[][] battleFieldCells = Battle.getCurrentBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++)
        {
            for (int column = 0; column < 9; column++)
            {

                int finalColumn1 = column;
                int finalRow1 = row;

                battleFieldCells[row][column].getCellPane().setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        System.out.println(battleFieldCells[finalRow1][finalColumn1].getCard().getCardName());
                    }
                });
            }
        }
    }

    public static void setSpriteAnimation(ImageView imageView)
    {
        final Animation animation = new SpriteAnimation(imageView, Duration.millis(1000), 3, 3, 0, 0, 80, 80);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public Card getSelectedCard()
    {
        return selectedCard;
    }

    private void setSelectedCard(Card selectedCard)
    {
        this.selectedCard = selectedCard;
    }

    public boolean isCardSelectedForInsert()
    {
        return isCardSelectedForInsert;
    }

    private void setCardSelectedForInsert(boolean cardSelectedForInsert)
    {
        isCardSelectedForInsert = cardSelectedForInsert;
    }

    public Group getRootBattleField()
    {
        return rootBattleField;
    }

    private void setRootBattleField(Group rootBattleField)
    {
        this.rootBattleField = rootBattleField;
    }

    public boolean isCardSelectedForMove()
    {
        return isCardSelectedForMove;
    }

    public void setCardSelectedForMove(boolean cardSelectedForMove)
    {
        isCardSelectedForMove = cardSelectedForMove;
    }
}
