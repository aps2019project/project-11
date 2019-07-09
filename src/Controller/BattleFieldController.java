package Controller;

import Model.*;
import View.Request;
import View.ShowOutput;
import View.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static javafx.scene.paint.Color.CYAN;

@SuppressWarnings("ALL")
public class BattleFieldController extends Thread
{
    private boolean isCardSelectedToInsert = false;
    private boolean isCardSelectedInBattle = false;
    private Request request;
    private Card selectedCardForInserting;
    private Card selectedCard;
    private Group rootBattleField;
    private BattleManager battleManager;
    private Scene sceneBattleField;
    private Text attackedCardInfo;
    private static boolean firstWorks = true;
    private Text battleInfo;
    private Battle battle;

    public BattleFieldController(Request request, Group rootBattleField, Scene sceneBattleField, Text battleInfo, Battle battle)
    {
        this.request = request;
        setSceneBattleField(sceneBattleField);
        setRootBattleField(rootBattleField);
        setBattleInfo(battleInfo);
        setBattle(battle);
        setBattleManager(new BattleManager(battle));
    }

    @Override
    public void run()
    {
        super.run();
        if (firstWorks)
        {
            firstLoad();
        }
        showGameInfo();
        preLoad();
    }

    private void showGameInfo()
    {
        battleInfo.setText("");
        ShowOutput showOutput = ShowOutput.getInstance();
        String string = showOutput.getGameInfo(getBattle());
        battleInfo.setText(string);
    }

    private void firstLoad()
    {
        for (Card card : getBattle().getPlayerTurn().getNonHeroCards())
        {
            if (card instanceof Minion)
            {
                ((Minion) card).setCurrentAP(((Minion) card).getDefaultAP());
                ((Minion) card).setCurrentHP(((Minion) card).getDefaultHP());
            }
        }
        for (Hero hero : getBattle().getPlayerTurn().getMainDeck().getHero())
        {
            hero.setCurrentAP(hero.getDefaultAP());
            hero.setCurrentHP(hero.getDefaultHP());
        }
        firstWorks = false;
    }

    void preLoad()
    {
        setSelectedCard(null);
        setSelectedCardForInserting(null);
        getBattle().unSelectCard();

        getCardInformation();
        checkInsertingCard();
        checkSelectingCard();
        checkEndOfgame();
    }


    private void getCardInformation()
    {
        int counter = 0;

        ShowOutput showOutput = ShowOutput.getInstance();

        final Text[] text = new Text[1];

        for (Pane pane : getBattle().getTurnPlayerHandPane())
        {
            int finalCounter = counter;
            pane.setOnMouseEntered(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    text[0] = new Text();
                    rootBattleField.getChildren().add(text[0]);
                    String s = showOutput.showCardInfoString(getBattle().getPlayerTurn().getHand().getCards().get(finalCounter));
                    assert false;
                    text[0].setText(s);
                    text[0].setFont(Font.font(15));
                    text[0].relocate(50, 170);
                    text[0].setFill(Color.CYAN);
                }
            });
            pane.setOnMouseExited(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    text[0].setText("");
                }
            });
            counter++;
        }

        Cell[][] cells = getBattle().getBattleField().getBattleFieldMatrix();
        for (int row = 0; row < 5; row++)
        {
            for (int column = 0; column < 9; column++)
            {

                if (cells[row][column].isFull() && cells[row][column].getCard() != null)
                {
                    int finalColumn = column;
                    int finalRow = row;
                    cells[row][column].getCellPane().setOnMouseEntered(new EventHandler<MouseEvent>()
                    {
                        @Override
                        public void handle(MouseEvent event)
                        {
                            try
                            {
                                text[0] = new Text();
                                rootBattleField.getChildren().add(text[0]);
                                String s = showOutput.showCardInfoInBattleString(cells[finalRow][finalColumn].getCard());
                                assert false;
                                text[0].setText(s);
                                text[0].setFont(Font.font(15));
                                text[0].relocate(50, 170);
                                text[0].setFill(Color.CYAN);
                                attackedCardInfo = text[0];
                            } catch (Exception e)
                            {
                            }
                        }
                    });

                    cells[row][column].getCellPane().setOnMouseExited(new EventHandler<MouseEvent>()
                    {
                        @Override
                        public void handle(MouseEvent event)
                        {
                            if (text[0] != null)
                            {
                                text[0].setText("");
                            }
                        }
                    });
                }
            }
        }

    }

    private void checkInsertingCard()
    {
        Pane[] firstPlayerHandPanes = getBattle().getFirstPlayerHandPanes();
        Pane[] secondPlayerHandPanes = getBattle().getSecondPlayerHandPanes();

        for (int number = 0; number < firstPlayerHandPanes.length; number++)
        {
            int finalNumber = number;

            firstPlayerHandPanes[number].setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    if (isCardSelectedToInsert)
                    {
                        //todo to Show error
                    }
                    else
                    {
                        setCardSelectedToInsert(true);
                        setSelectedCardForInserting(getBattle().getFirstPlayer().getHand().getCards().get(finalNumber));
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
                    if (isCardSelectedToInsert)
                    {
                        //todo to show error
                    }
                    else
                    {
                        setCardSelectedToInsert(true);
                        setSelectedCardForInserting(getBattle().getSecondPlayer().getHand().getCards().get(finalNumber));
                        insertCard();
                    }
                }
            });
        }

    }

    private void insertCard()
    {
        Cell[][] battleFieldCells = getBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++)
        {
            for (int column = 0; column < 9; column++)
            {
                int finalColumn = column;
                int finalRow = row;

                battleFieldCells[row][column].getCellPane().setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        if (isCardSelectedToInsert)
                        {
                            if (selectedCardForInserting instanceof Minion)
                            {
                                if (battleManager.checkCircumstancesToInsertMinionBoolean((Minion) selectedCardForInserting, finalRow, finalColumn))
                                {
                                    ImageView imageView = Card.getCardImageView(selectedCardForInserting);
                                    setSpriteAnimation(imageView);
                                    battleFieldCells[finalRow][finalColumn].getCellPane().getChildren().add(imageView);
                                    battleFieldCells[finalRow][finalColumn].setCard(selectedCardForInserting);

                                    getBattle().getBattleField().addCardInTheBattleField((NonSpellCard) selectedCardForInserting);

                                    ((Minion) selectedCardForInserting).setDefaultAPHP();

                                    ((NonSpellCard) selectedCardForInserting).setMoveAble(false);

                                    getBattle().setNextCardPane(rootBattleField);
                                    getBattle().getPlayerTurn().getHand().getCards().add(getBattle().getPlayerTurn().getHand().getNextCard());
                                    getBattle().setHandIcons();
                                    request.setMPIcons(rootBattleField, getBattle());

                                    setCardSelectedToInsert(false);
                                    setSelectedCardForInserting(null);
                                    checkSelectingCard();
                                    preLoad();
                                }
                                else
                                {
                                    setSelectedCard(null);
                                    setSelectedCardForInserting(null);
                                    getBattle().unSelectCard();
                                    checkInsertingCard();
                                    checkSelectingCard();
                                }
                            }
                            else if (selectedCardForInserting instanceof Spell)
                            {
                                if (battleManager.checkCircumstancesToInsertSpellBoolean((Spell) selectedCardForInserting, finalRow, finalColumn))
                                {

                                    getBattle().getPlayerTurn().getHand().getCards().add(getBattle().getPlayerTurn().getHand().getNextCard());
                                    getBattle().setHandIcons();
                                    request.setMPIcons(rootBattleField, getBattle());

                                    setSelectedCardForInserting(null);
                                    setCardSelectedToInsert(false);
                                    checkSelectingCard();
                                    preLoad();

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
        Cell[][] battleFieldCells = getBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++)
        {
            for (int column = 0; column < 9; column++)
            {

                int finalColumn = column;
                int finalRow = row;

                battleFieldCells[row][column].getCellPane().setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        if (getBattle().getPlayerTurn().getInsertedCards().contains(battleFieldCells[finalRow][finalColumn].getCard()) || getBattle().getPlayerTurn().getMainDeck().getHero().get(0).equals((battleFieldCells[finalRow][finalColumn].getCard())))
                        {
                            System.out.println(battleFieldCells[finalRow][finalColumn].getCard().getCardName());
                            setSelectedCard(battleFieldCells[finalRow][finalColumn].getCard());
                            setCardSelectedInBattle(true);
                            getBattle().selectCard(battleFieldCells[finalRow][finalColumn].getCard());
                            selectedCardActions(finalRow, finalColumn);
                        }
                    }
                });
            }
        }

    }

    private void selectedCardActions(int sourceRow, int sourceColumn)
    {
        Cell[][] battleFieldCells = getBattle().getBattleField().getBattleFieldMatrix();

        for (int row = 0; row < 5; row++)
        {
            for (int column = 0; column < 9; column++)
            {

                int finalRow = row;
                int finalColumn = column;

                battleFieldCells[row][column].getCellPane().setOnScroll(new EventHandler<ScrollEvent>()
                {
                    @Override
                    public void handle(ScrollEvent event)
                    {
                        if (getBattle().getOpponentPlayer().getInsertedCards().contains(battleFieldCells[finalRow][finalColumn].getCard()) || getBattle().getOpponentPlayer().getMainDeck().getHero().get(0).equals((battleFieldCells[finalRow][finalColumn].getCard())))
                        {
                            Card opponentCard = getBattle().getBattleField().getBattleFieldMatrix()[finalRow][finalColumn].getCard();
                            attackTo(opponentCard, sourceRow, sourceColumn);
                        }
                        else if (!battleFieldCells[finalRow][finalColumn].isFull())
                        {
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
            getBattle().getBattleFieldPanes()[sourceColumn][sourceRow].getChildren().remove(1);
            ImageView imageView = Card.getCardImageView(selectedCard);
            try
            {
                cardMoveAnimation(imageView, sourceRow, sourceColumn, destinationRow, destinationColumn);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            setSpriteAnimation(imageView);
            getBattle().getBattleFieldPanes()[destinationColumn][destinationRow].getChildren().add(imageView);
            getBattle().unSelectCard();
            preLoad();
        }

    }

    private void cardMoveAnimation(ImageView imageView, int sourceRow, int sourceColumn, int destinationRow, int destinationColumn) throws InterruptedException
    {

    }

    private void attackTo(Card opponentCard, int sourceRow, int sourceColumn)
    {
        battleManager.attackToOpponent(opponentCard);
        checkHPOfCards();
        setAttackAnimation();
        attackedCardInfo.setText("");
        preLoad();
    }

    private void checkHPOfCards()
    {
        for (int row = 0; row < 5; row++)
        {
            for (int column = 0; column < 9; column++)
            {
                if (getBattle().getBattleField().getBattleFieldMatrix()[row][column].getCard() != null && getBattle().getBattleField().getBattleFieldMatrix()[row][column].getCard().getCurrentHP() <= 0)
                {
                    getBattle().getBattleField().getBattleFieldMatrix()[row][column].getCellPane().getChildren().remove(getBattle().getBattleField().getBattleFieldMatrix()[row][column].getCellPane().getChildren().size() - 1);
                    getBattle().getBattleField().getBattleFieldMatrix()[row][column].setCard(null);
                }
            }
        }
    }

    private void setAttackAnimation()
    {

    }

    public static void setSpriteAnimation(ImageView imageView)
    {
        final Animation animation = new SpriteAnimation(imageView, Duration.millis(1000), 3, 3, 0, 0, 80, 80);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    private void checkEndOfgame()
    {
        switch (getBattle().getBattleMode())
        {
            case KILLING_ENEMY_HERO:
                if (getBattle().isGameEnded(1))
                {
                    getBattle().tasksAtEndOfGame();
                }
                break;
        }
    }

    public Card getSelectedCardForInserting()
    {
        return selectedCardForInserting;
    }

    private void setSelectedCardForInserting(Card selectedCardForInserting)
    {
        this.selectedCardForInserting = selectedCardForInserting;
    }

    public boolean isCardSelectedToInsert()
    {
        return isCardSelectedToInsert;
    }

    private void setCardSelectedToInsert(boolean cardSelectedToInsert)
    {
        isCardSelectedToInsert = cardSelectedToInsert;
    }

    public Group getRootBattleField()
    {
        return rootBattleField;
    }

    private void setRootBattleField(Group rootBattleField)
    {
        this.rootBattleField = rootBattleField;
    }

    public boolean isCardSelectedInBattle()
    {
        return isCardSelectedInBattle;
    }

    public void setCardSelectedInBattle(boolean cardSelectedInBattle)
    {
        isCardSelectedInBattle = cardSelectedInBattle;
    }

    public Card getSelectedCard()
    {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard)
    {
        this.selectedCard = selectedCard;
    }

    public BattleManager getBattleManager()
    {
        return battleManager;
    }

    public void setBattleManager(BattleManager battleManager)
    {
        this.battleManager = battleManager;
    }

    public Scene getSceneBattleField()
    {
        return sceneBattleField;
    }

    public void setSceneBattleField(Scene sceneBattleField)
    {
        this.sceneBattleField = sceneBattleField;
    }

    public void setBattleInfo(Text battleInfo)
    {
        this.battleInfo = battleInfo;
    }

    public Text getBattleInfo()
    {
        return battleInfo;
    }

    public Battle getBattle()
    {
        return battle;
    }

    public void setBattle(Battle battle)
    {
        this.battle = battle;
    }
}
