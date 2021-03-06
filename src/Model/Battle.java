package Model;

import Controller.BattleFieldController;
import Controller.BattleManager;
import View.Request;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.paint.Color.BLACK;

@SuppressWarnings("Duplicates")
public class Battle
{
    private Player firstPlayer;
    private Player secondPlayer;
    private Player playerTurn;
    private BattleField battleField = new BattleField();
    private NonSpellCard selectedCard;
    private Item selectedICollectibleItem;
    private BattleMode battleMode;
    private BattleType battleType;
    private int numOfFlagsInGatheringFlagsMatchMode;
    private Random random = new Random();
    private Player victoriousPlayer = null;
    private Player loserPlayer = null;
    private transient GridPane battleFieldGridPane;
    private transient Pane[] firstPlayerHandPanes;
    private transient Pane[] secondPlayerHandPanes;
    private transient Pane[][] battleFieldPanes;
    private transient Pane nextCardPane;


    public Battle(Player firstPlayer, Player secondPlayer, BattleMode battleMode, BattleType battleType)
    {
        this.setFirstPlayer(firstPlayer);
        this.setSecondPlayer(secondPlayer);
        this.setPlayerTurn(firstPlayer);
        this.setBattleMode(battleMode);
        this.setBattleType(battleType);
        this.getBattleField().makeCells();
        /*Item usableItemFirstPlayer = this.getFirstPlayer().getMainDeck().getItem().get(0);
        usableItemFirstPlayer.applyUsableItem(this.getFirstPlayer());
        Item usableItemSecondPlayer = this.getSecondPlayer().getMainDeck().getItem().get(0);
        usableItemSecondPlayer.applyUsableItem(this.getSecondPlayer());*/
    }

    public void selectCard(NonSpellCard card)
    {
        setSelectedCard(card);
        card.setCardSelectedInBattle(true);
    }

    public void selectCollectibleItem(Item item)
    {
        setSelectedICollectibleItem(item);
        item.setCollectibleItemSelectedInBattle(true);
    }

    public void moveCard(NonSpellCard selectedCard, int x, int y)
    {
        this.getBattleField().getBattleFieldMatrix()[selectedCard.getRow()][selectedCard.getColumn()].isFull();
        this.getBattleField().getBattleFieldMatrix()[selectedCard.getRow()][selectedCard.getColumn()].remove(selectedCard);
        this.getBattleField().getBattleFieldMatrix()[selectedCard.getRow()][selectedCard.getColumn()].setCard(null);
        selectedCard.setRow(x);
        selectedCard.setColumn(y);
        this.getBattleField().getBattleFieldMatrix()[x][y].setCard(selectedCard);
        selectedCard.setMoveAble(false);
    }

    public void damageCard(NonSpellCard selectedCard, NonSpellCard opponentCard)
    {
        int currentHP = opponentCard.getCurrentHP();
        opponentCard.setCurrentHP(currentHP - selectedCard.getCurrentAP());
    }

    public void attackToOpponent(NonSpellCard selectedCard, NonSpellCard opponentCard)
    {
        this.damageCard(selectedCard, opponentCard);
        this.counterAttack(opponentCard);
        (selectedCard).setAttackAble(false);
    }

    public void counterAttack(NonSpellCard opponentCard)
    {
        if (opponentCard.isCounterAttackAble())
        {
            if (opponentCard.getImpactType() == ImpactType.melee)
            {
                if (Card.checkNeighborhood(selectedCard, opponentCard))
                {
                    damageCard(opponentCard, selectedCard);
                }
            }
            else if (opponentCard.getImpactType() == ImpactType.ranged)
            {
                if (Card.findDestination(selectedCard, opponentCard) <= opponentCard.getRangeOfAttack() && !(Card.checkNeighborhood(selectedCard, opponentCard)))
                {
                    damageCard(opponentCard, selectedCard);
                }
            }
            else if (opponentCard.getImpactType() == ImpactType.hybrid)
            {
                if (Card.findDestination(selectedCard, opponentCard) <= opponentCard.getRangeOfAttack())
                {
                    damageCard(opponentCard, selectedCard);
                }
            }
        }
    }

    //TODO how below method works?? i apply some changes check it works properly(to Ali)
    private void counterAttack(String cardID1, String cardID2)
    {
        selectedCard = (NonSpellCard) this.getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID1);
        NonSpellCard opponentCard = this.getBattleField().findCardInBattleField(cardID2);
        counterAttack(opponentCard);
        setSelectedCard(null);
    }

    public void comboAttack(String enemyCardID, ArrayList<String> cardsIDForComboAttack)
    {
        checkComboCondition(cardsIDForComboAttack);
        NonSpellCard opponentCard = this.getBattleField().findCardInBattleField(enemyCardID);
        for (String cardID : cardsIDForComboAttack)
        {
            NonSpellCard selectedCard = this.getBattleField().findCardInBattleField(cardID);
            attackToOpponent(selectedCard, opponentCard);
        }
    }

    private void checkComboCondition(ArrayList<String> cardsIDForComboAttack)
    {
        for (String cardID : cardsIDForComboAttack)
        {
            if (this.getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID) == null)
            {
                continue;
            }
            Card card = this.getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID);
            if (!(card instanceof Minion) || !((Minion) card).isAbleToCombo())
            {
                cardsIDForComboAttack.removeIf(n -> n.equals(cardID));
            }
        }
    }

    public void checkInsertedCardsToApplySpellChange()
    {
        for (NonSpellCard nonSpellCard : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (SpellChange spellChange : nonSpellCard.getActiveSpellsOnThisCard())
            {
                if (spellChange.getTimeToActivateSpecialPower() == TimeToActivateSpecialPower.onAttack)
                {
                    spellChange.applySpellChangeOnCard(nonSpellCard);
                }
                if (spellChange.getTimeToActivateSpecialPower() == TimeToActivateSpecialPower.onDefend)
                {

                }
            }
        }
    }

    public void checkUsedItemsToApplyItemChange()
    {
        for (NonSpellCard nonSpellCard : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (ItemChange itemChange : nonSpellCard.getActiveItemsOnThisCard())
            {
                //todo
                itemChange.applyItemChange(nonSpellCard);
            }
        }
    }

    public void endTurn()
    {
        this.getPlayerTurn().increaseDefaultMP();
        if (this.getPlayerTurn() == this.getFirstPlayer())
        {
            this.setPlayerTurn(this.getSecondPlayer());
        }
        else
        {
            this.setPlayerTurn(this.getFirstPlayer());
        }
        for (NonSpellCard card : playerTurn.getInsertedCards())
        {
            card.setMoveAble(true);
            card.setAttackAble(true);
        }
        playerTurn.getMainDeck().getHero().get(0).setMoveAble(true);
        playerTurn.getMainDeck().getHero().get(0).setAttackAble(true);
        this.getPlayerTurn().setMP();
        checkInsertedCardsToApplySpellChange();
        checkUsedItemsToApplyItemChange();
        for (ItemChange itemChange : this.getPlayerTurn().getActiveItemsOnPlayer())
        {
            itemChange.applyItemChange(this.getPlayerTurn());
        }
    }

    public NonSpellCard findRandomOwnForce()
    {
        ArrayList<NonSpellCard> ownNonSpellCards = new ArrayList<>();
        for (NonSpellCard nonSpellCard : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (NonSpellCard ownNonSpellCard : this.getPlayerTurn().getInsertedCards())
            {
                if (nonSpellCard.getCardID().equals(ownNonSpellCard.getCardID()))
                {
                    ownNonSpellCards.add(ownNonSpellCard);
                }
            }
        }
        int randomMinionNumber = (int) (Math.random() % ownNonSpellCards.size());
        if (randomMinionNumber == 0)
        {
            return null;
        }
        return ownNonSpellCards.get(randomMinionNumber);
    }

    public Minion findRandomOwnMinionToApplyItem()
    {
        int numOfInsertedMinions = this.getPlayerTurn().getInsertedCards().size();
        int randomMinionNumber = random.nextInt(numOfInsertedMinions);
        return this.getPlayerTurn().getInsertedCards().get(randomMinionNumber);
    }

    public Minion findRandomOwnRangedHybridMinionToApplyItem()
    {
        ArrayList<Minion> minions = new ArrayList<>();
        for (Minion minion : this.getPlayerTurn().getInsertedCards())
        {
            if (minion.getImpactType() == ImpactType.hybrid || minion.getImpactType() == ImpactType.ranged)
            {
                minions.add(minion);
            }
        }
        int randomMinionNumber = random.nextInt(minions.size());
        return minions.get(randomMinionNumber);
    }

    public NonSpellCard findRandomOpponentNonSpellCardToApplyUsableItem()
    {
        ArrayList<NonSpellCard> opponentNonSpellCards = new ArrayList<>();
        Outer:
        for (NonSpellCard nonSpellCard : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (NonSpellCard ownNonSpellCard : this.getPlayerTurn().getInsertedCards())
            {
                if (nonSpellCard.getCardID().equals(ownNonSpellCard.getCardID()))
                {
                    continue Outer;
                }
            }
            opponentNonSpellCards.add(nonSpellCard);
        }
        int randomMinionNumber = random.nextInt(opponentNonSpellCards.size());
        return opponentNonSpellCards.get(randomMinionNumber);
    }

    public Hero getOpponentHero()
    {
        Hero opponentHero;
        if (this.getPlayerTurn() == this.getFirstPlayer())
        {
            opponentHero = this.getSecondPlayer().getMainDeck().getHero().get(0);
        }
        else
        {
            opponentHero = this.getFirstPlayer().getMainDeck().getHero().get(0);
        }
        return opponentHero;
    }

    public ArrayList<Minion> getOpponentMinions()
    {
        if (this.getPlayerTurn() == this.getFirstPlayer())
        {
            return this.getSecondPlayer().getInsertedCards();
        }
        else
        {
            return this.getFirstPlayer().getInsertedCards();
        }
    }

    public ArrayList<Minion> getOwnMinion()
    {
        if (this.getPlayerTurn() == this.getFirstPlayer())
        {
            return this.getFirstPlayer().getInsertedCards();
        }
        else
        {
            return this.getSecondPlayer().getInsertedCards();
        }
    }

    public ArrayList<NonSpellCard> findingOpponentNonSpellCards()
    {
        ArrayList<NonSpellCard> opponentNonSpellCards = new ArrayList<>();
        FirstFor:
        for (NonSpellCard nonSpellCard : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (NonSpellCard ownNonSpellCard : this.getPlayerTurn().getInsertedCards())
            {
                if (Integer.parseInt(nonSpellCard.getCardID()) == Integer.parseInt(ownNonSpellCard.getCardID()))
                {
                    continue FirstFor;
                }
            }
            opponentNonSpellCards.add(nonSpellCard);
        }
        return opponentNonSpellCards;
    }

    public ArrayList<NonSpellCard> findingOwnNonSpellCards()
    {
        ArrayList<NonSpellCard> ownNonSpellCards = new ArrayList<>();
        for (NonSpellCard nonSpellCard : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (NonSpellCard allOwnNonSpellCard : this.getFirstPlayer().getInsertedCards())
            {
                if (Integer.parseInt(allOwnNonSpellCard.getCardID()) == Integer.parseInt(nonSpellCard.getCardID()))
                {
                    ownNonSpellCards.add(allOwnNonSpellCard);
                }
            }
        }
        return ownNonSpellCards;
    }

    public boolean isGameEnded(int gameMode)
    {
        switch (gameMode)
        {
            case 1:
                if ((firstPlayer.getMainDeck().getHero().get(0)).getCurrentHP() <= 0)
                {
                    System.out.println("Player2 Win!!!");
                    setVictoriousPlayer(secondPlayer);
                    setLoserPlayer(firstPlayer);
                    return true;
                }
                else if ((secondPlayer.getMainDeck().getHero().get(0)).getCurrentHP() <= 0)
                {
                    System.out.println("Player1 Win!!!");
                    setVictoriousPlayer(firstPlayer);
                    setLoserPlayer(secondPlayer);
                    return true;
                }
        }
        return false;
    }

    public Player getPlayerTurn()
    {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn)
    {
        this.playerTurn = playerTurn;
    }

    public Player getSecondPlayer()
    {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer)
    {
        System.out.println(secondPlayer);
        this.secondPlayer = secondPlayer;
        this.getSecondPlayer().setMP();
    }

    public Player getFirstPlayer()
    {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer)
    {
        this.firstPlayer = firstPlayer;
        this.getFirstPlayer().setMP();
    }

    public Player getLoserPlayer()
    {
        return loserPlayer;
    }

    public void setLoserPlayer(Player loserPlayer)
    {
        this.loserPlayer = loserPlayer;
    }

    public Player getVictoriousPlayer()
    {
        return victoriousPlayer;
    }

    public void setVictoriousPlayer(Player victoriousPlayer)
    {
        this.victoriousPlayer = victoriousPlayer;
    }

    public BattleField getBattleField()
    {
        return battleField;
    }

    public NonSpellCard getSelectedCard()
    {
        return selectedCard;
    }

    public void setSelectedCard(NonSpellCard selectedCard)
    {
        this.selectedCard = selectedCard;
    }

    public Item getSelectedICollectibleItem()
    {
        return selectedICollectibleItem;
    }

    public void setSelectedICollectibleItem(Item selectedICollectibleItem)
    {
        this.selectedICollectibleItem = selectedICollectibleItem;
    }

    public BattleMode getBattleMode()
    {
        return battleMode;
    }

    public void setBattleMode(BattleMode battleMode)
    {
        this.battleMode = battleMode;
    }

    public int getNumOfFlagsInGatheringFlagsMatchMode()
    {
        return numOfFlagsInGatheringFlagsMatchMode;
    }

    public void setNumOfFlagsInGatheringFlagsMatchMode(int numOfFlagsInGatheringFlagsMatchMode)
    {
        this.numOfFlagsInGatheringFlagsMatchMode = numOfFlagsInGatheringFlagsMatchMode;
    }

    private BattleType getBattleType()
    {
        return battleType;
    }

    private void setBattleType(BattleType battleType)
    {
        this.battleType = battleType;
    }

    public void tasksWhenSurrender()
    {
        setLoserPlayer(getPlayerTurn());
        if (getSecondPlayer() == getPlayerTurn())
        {
            setVictoriousPlayer(getFirstPlayer());
        }
        else
        {
            setVictoriousPlayer(getSecondPlayer());
        }
        tasksAtEndOfGame();
    }

    public void tasksAtEndOfGame()
    {
        victoriousPlayer.getAccount().increaseNumberOfWins();
        switch (this.getBattleType())
        {
            case STORY_GAME_1:
                victoriousPlayer.getAccount().addMoney(500);
                System.out.println("500 added to " + victoriousPlayer.getAccount().getAccountName() + "'s money");
                victoriousPlayer.getAccount().getMatchHistory().add(new FinishedMatch(loserPlayer.getAccount().getAccountName(), MatchResult.WIN, 0));
                loserPlayer.getAccount().getMatchHistory().add(new FinishedMatch(victoriousPlayer.getAccount().getAccountName(), MatchResult.LOSE, 0));
                break;
            case STORY_GAME_2:
                victoriousPlayer.getAccount().addMoney(1000);
                System.out.println("1000 added to " + victoriousPlayer.getAccount().getAccountName() + "'s money");
                victoriousPlayer.getAccount().getMatchHistory().add(new FinishedMatch(loserPlayer.getAccount().getAccountName(), MatchResult.WIN, 0));
                loserPlayer.getAccount().getMatchHistory().add(new FinishedMatch(victoriousPlayer.getAccount().getAccountName(), MatchResult.LOSE, 0));
                break;
            case STORY_GAME_3:
                victoriousPlayer.getAccount().addMoney(1500);
                System.out.println("1500 added to " + victoriousPlayer.getAccount().getAccountName() + "'s money");
                victoriousPlayer.getAccount().getMatchHistory().add(new FinishedMatch(loserPlayer.getAccount().getAccountName(), MatchResult.WIN, 0));
                loserPlayer.getAccount().getMatchHistory().add(new FinishedMatch(victoriousPlayer.getAccount().getAccountName(), MatchResult.LOSE, 0));
                break;
            case CUSTOM_GAME:
            case MULTI_PLAYER_GAME:
                victoriousPlayer.getAccount().addMoney(1000);
                victoriousPlayer.getAccount().getMatchHistory().add(new FinishedMatch(loserPlayer.getAccount().getAccountName(), MatchResult.WIN, 0));
                loserPlayer.getAccount().getMatchHistory().add(new FinishedMatch(victoriousPlayer.getAccount().getAccountName(), MatchResult.LOSE, 0));
                break;
        }

    }

    //todo
    /*public void AIPlayerWorks(BattleManager battleManager)
    {
        for (int counter = 1; counter <= 10 && playerTurn.getMP() > 0; counter++)
        {
            String randomCardName = playerTurn.getHand().getCards().get((int) (Math.random() % playerTurn.getHand().getCards().size())).getCardName();
            battleManager.selectCard(randomCardName);
            battleManager.CheckCircumstancesToInsertCard(selectedCard);
        }
        for (int counter = 1; counter <= 20; counter++)
        {
            selectedCard = playerTurn.getInsertedCards().get((int) (Math.random() % playerTurn.getInsertedCards().size()));
            NonSpellCard firstPlayerCard = firstPlayer.getInsertedCards().get((int) (Math.random() % playerTurn.getInsertedCards().size()));
            battleManager.attackToOpponent(firstPlayerCard.getCardID());
            counterAttack(selectedCard.getCardName(), firstPlayerCard.getCardID());
        }
        this.setPlayerTurn(firstPlayer);
        selectedCard = null;
    }*/


    public void help()
    {
        for (Card card : playerTurn.getInsertedCards())
        {
            if (((NonSpellCard) card).isMoveAble())
            {
                System.out.println(card.getCardName() + " is movable");
            }
        }
        for (Card card : playerTurn.getInsertedCards())
        {
            if (((NonSpellCard) card).isAttackAble())
            {
                int[][] matrix = setAttackRangeMatrix((NonSpellCard) card);
                for (NonSpellCard enemyCard : getOpponentPlayer().getInsertedCards())
                {
                    try
                    {
                        assert matrix != null;
                        if (matrix[enemyCard.getRow()][enemyCard.getColumn()] == 1)
                        {
                            System.out.println(card.getCardName() + " can attack to " + enemyCard.getCardName());
                        }
                    } catch (Exception ignored)
                    {
                    }
                }
            }
        }
        for (Card card : playerTurn.getHand().getCards())
        {
            if (card.getRequiredMP() <= playerTurn.getMP())
            {
                System.out.println(playerTurn.getAccount().getAccountName() + " can insert " + card.getCardName());
            }
        }
    }

    private int[][] setAttackRangeMatrix(NonSpellCard card)
    {
        switch (card.getImpactType())
        {
            case melee:
                int[][] matrix1 = new int[3][3];
                for (int row = 0; row < 3; row++)
                {
                    for (int column = 0; column < 3; column++)
                    {
                        matrix1[row][column] = 1;
                    }
                }
                return matrix1;
            case ranged:
                int rangeOfAttack = card.getRangeOfAttack();
                int[][] matrix2 = new int[rangeOfAttack][rangeOfAttack];
                for (int row = 0; row < rangeOfAttack; row++)
                {
                    for (int column = 0; column < rangeOfAttack; column++)
                    {
                        matrix2[row][column] = 1;
                    }
                }
                for (int row = card.getRow() - 1; row <= card.getRow() + 1 && row >= 0; row++)
                {
                    for (int column = card.getColumn() - 1; column <= card.getColumn() + 1 && column >= 0; column++)
                    {
                        matrix2[row][column] = 0;
                    }
                }
                return matrix2;
            case hybrid:
                rangeOfAttack = card.getRangeOfAttack();
                int[][] matrix3 = new int[rangeOfAttack][rangeOfAttack];
                for (int row = 0; row < rangeOfAttack; row++)
                {
                    for (int column = 0; column < rangeOfAttack; column++)
                    {
                        matrix3[row][column] = 1;
                    }
                }
                return matrix3;
        }
        return null;
    }

    public Player getOpponentPlayer()
    {
        if (playerTurn == firstPlayer)
        {
            return secondPlayer;
        }
        return firstPlayer;
    }

    public void setHeroesInBattlefield()
    {
        firstPlayer.getMainDeck().getHero().get(0).setRow(2);
        firstPlayer.getMainDeck().getHero().get(0).setColumn(0);
        secondPlayer.getMainDeck().getHero().get(0).setRow(2);
        secondPlayer.getMainDeck().getHero().get(0).setColumn(8);
        this.getBattleField().addCardInTheBattleField(firstPlayer.getMainDeck().getHero().get(0));
        this.getBattleField().addCardInTheBattleField(secondPlayer.getMainDeck().getHero().get(0));
    }

    public GridPane getBattleFieldGridPane()
    {
        return battleFieldGridPane;
    }

    public void setBattleFieldGridPane(GridPane battleFieldGridPane)
    {
        this.battleFieldGridPane = battleFieldGridPane;
    }

    public Pane[] getFirstPlayerHandPanes()
    {
        return firstPlayerHandPanes;
    }

    public Pane[] getSecondPlayerHandPanes()
    {
        return secondPlayerHandPanes;
    }

    public void setFirstPlayerHandPanes(Pane[] firstPlayerHandPanes)
    {
        this.firstPlayerHandPanes = firstPlayerHandPanes;
    }

    public void setSecondPlayerHandPanes(Pane[] secondPlayerHandPanes)
    {
        this.secondPlayerHandPanes = secondPlayerHandPanes;
    }

    public Pane[] getCurrentPlayerHand()
    {
        if (getPlayerTurn() == firstPlayer)
        {
            return firstPlayerHandPanes;
        }
        if (getPlayerTurn() == secondPlayer)
        {
            return secondPlayerHandPanes;
        }
        return null;
    }

    public Pane[][] getBattleFieldPanes()
    {
        return battleFieldPanes;
    }

    public void setBattleFieldPanes(Pane[][] battleFieldPanes)
    {
        for (int row = 0; row < 9; row++)
        {
            for (int column = 0; column < 5; column++)
            {
                getBattleField().getBattleFieldMatrix()[column][row].setCellPane(battleFieldPanes[row][column]);
            }
        }
        this.battleFieldPanes = battleFieldPanes;
    }

    public void setHandIcons()
    {
        Pane[] firstPlayerHandPanes = new Pane[this.getFirstPlayer().getHand().getCards().size()];
        Pane[] secondPlayerHandPanes = new Pane[this.getSecondPlayer().getHand().getCards().size()];

        for (int number = 0; number < this.getFirstPlayer().getHand().getCards().size(); number++)
        {
            ImageView imageView;

            Card card = this.getFirstPlayer().getHand().getCards().get(number);

            firstPlayerHandPanes[number] = new Pane();

            imageView = Card.getCardIcon(card);

            firstPlayerHandPanes[number].getChildren().clear();

            firstPlayerHandPanes[number].getChildren().add(imageView);
        }

        for (int number = 0; number < this.getSecondPlayer().getHand().getCards().size(); number++)
        {
            ImageView imageView;

            Card card = this.getSecondPlayer().getHand().getCards().get(number);

            secondPlayerHandPanes[number] = new Pane();

            imageView = Card.getCardIcon(card);

            secondPlayerHandPanes[number].getChildren().clear();

            if(imageView != null) {
                secondPlayerHandPanes[number].getChildren().add(imageView);
            }
        }

        firstPlayerHandPanes[0].relocate(400, 620);
        firstPlayerHandPanes[1].relocate(525, 620);
        firstPlayerHandPanes[2].relocate(650, 620);
        firstPlayerHandPanes[3].relocate(775, 620);
        firstPlayerHandPanes[4].relocate(900, 620);
        secondPlayerHandPanes[0].relocate(400, 620);
        secondPlayerHandPanes[1].relocate(525, 620);
        secondPlayerHandPanes[2].relocate(650, 620);
        secondPlayerHandPanes[3].relocate(775, 620);
        secondPlayerHandPanes[4].relocate(900, 620);

        this.setFirstPlayerHandPanes(firstPlayerHandPanes);
        this.setSecondPlayerHandPanes(secondPlayerHandPanes);
    }

    public Pane getNextCardPane()
    {
        return nextCardPane;
    }

    public void setNextCardPane(Group rootBattleField)
    {
        try {
            Pane pane = new Pane();
            pane.relocate(100, 400);
            pane.getChildren().add(new ImageView(Card.getCardIcon(getPlayerTurn().getHand().getNextCard()).getImage()));
            nextCardPane = pane;
            rootBattleField.getChildren().add(nextCardPane);
        }
        catch (Exception ignored){}
    }

    public void unSelectCard()
    {
        if (selectedCard != null)
        {
            selectedCard.setCardSelectedInBattle(false);
            setSelectedCard(null);
        }
    }

    public Pane[] getTurnPlayerHandPane()
    {
        if (getPlayerTurn() == firstPlayer)
        {
            return firstPlayerHandPanes;
        }
        return secondPlayerHandPanes;
    }

    public void clearTheHandPictures()
    {
        for (int number = 0; number < 5; number++)
        {
            this.getFirstPlayerHandPanes()[number].getChildren().clear();
            this.getSecondPlayerHandPanes()[number].getChildren().clear();
        }

    }

    public void setHeroesFirstPlace()
    {
        Card.setCardsImageView();
        ImageView firstPlayerHero = Card.getCardStandingImageView(this.getFirstPlayer().getMainDeck().getHero().get(0));
        BattleFieldController.setSpriteAnimation(firstPlayerHero);

        ImageView secondPlayerHero = Card.getCardStandingImageView(this.getSecondPlayer().getMainDeck().getHero().get(0));
        BattleFieldController.setSpriteAnimation(secondPlayerHero);

        this.getBattleField().getBattleFieldMatrix()[2][0].getCellPane().getChildren().add(firstPlayerHero);
        this.getBattleField().getBattleFieldMatrix()[2][8].getCellPane().getChildren().add(secondPlayerHero);
        this.getFirstPlayer().getMainDeck().getHero().get(0).setRow(2);
        this.getFirstPlayer().getMainDeck().getHero().get(0).setColumn(0);
        this.getSecondPlayer().getMainDeck().getHero().get(0).setRow(2);
        this.getSecondPlayer().getMainDeck().getHero().get(0).setColumn(8);

        this.getFirstPlayer().getMainDeck().getHero().get(0).setDefaultAPHP();
        this.getSecondPlayer().getMainDeck().getHero().get(0).setDefaultAPHP();

        this.getBattleField().addCardInTheBattleField(this.getFirstPlayer().getMainDeck().getHero().get(0));
        this.getBattleField().addCardInTheBattleField(this.getSecondPlayer().getMainDeck().getHero().get(0));

        this.getBattleField().getBattleFieldMatrix()[2][0].setCard(this.getFirstPlayer().getMainDeck().getHero().get(0));
        this.getBattleField().getBattleFieldMatrix()[2][8].setCard(this.getSecondPlayer().getMainDeck().getHero().get(0));
    }

    public void setHeroIcons(Group rootBattleField)
    {
        Pane paneHero1 = new Pane();
        Pane paneHero2 = new Pane();
        paneHero1.relocate(100, -40);
        paneHero2.relocate(1100, -40);
        ImageView firstPlayerHeroIcon = Card.getCardIcon(this.getFirstPlayer().getMainDeck().getHero().get(0));
        ImageView secondPlayerHeroIcon = Card.getCardIcon(this.getSecondPlayer().getMainDeck().getHero().get(0));
        paneHero1.getChildren().addAll(firstPlayerHeroIcon);
        paneHero2.getChildren().addAll(secondPlayerHeroIcon);
        rootBattleField.getChildren().addAll(paneHero1, paneHero2);
    }

    public void setHandIcons(Group rootBattleField)
    {
        Pane[] firstPlayerHandPanes = new Pane[5];
        Pane[] secondPlayerHandPanes = new Pane[5];
        Card.setCardIcons();
        for (int number = 0; number < 5; number++)
        {
            ImageView imageView1;
            ImageView imageView2;

            Card card1 = this.getFirstPlayer().getHand().getCards().get(number);//Card.getCardsIcon().get(getBattle().getFirstPlayer().getHand().getCards().get(number));
            Card card2 = this.getSecondPlayer().getHand().getCards().get(number);//Card.getCardsIcon().get(getBattle().getSecondPlayer().getHand().getCards().get(number));

            firstPlayerHandPanes[number] = new Pane();
            secondPlayerHandPanes[number] = new Pane();

            imageView1 = Card.getCardIcon(card1);
            imageView2 = Card.getCardIcon(card2 );

            firstPlayerHandPanes[number].getChildren().add(imageView1);
            if(imageView2 != null) {
                secondPlayerHandPanes[number].getChildren().add(imageView2);
            }
        }

        firstPlayerHandPanes[0].relocate(400, 620);
        firstPlayerHandPanes[1].relocate(525, 620);
        firstPlayerHandPanes[2].relocate(650, 620);
        firstPlayerHandPanes[3].relocate(775, 620);
        firstPlayerHandPanes[4].relocate(900, 620);
        secondPlayerHandPanes[0].relocate(400, 620);
        secondPlayerHandPanes[1].relocate(525, 620);
        secondPlayerHandPanes[2].relocate(650, 620);
        secondPlayerHandPanes[3].relocate(775, 620);
        secondPlayerHandPanes[4].relocate(900, 620);
        for (int number = 0; number < 5; number++)
        {
            rootBattleField.getChildren().add(firstPlayerHandPanes[number]);
        }
        this.setFirstPlayerHandPanes(firstPlayerHandPanes);
        this.setSecondPlayerHandPanes(secondPlayerHandPanes);
    }

    public void setGridPane(Group rootBattleField)
    {
        Pane[][] panes = new Pane[9][5];
        GridPane gridPane = new GridPane();

        gridPane.relocate(300, 200);

        for (int row = 0; row < 9; row++)
        {
            for (int column = 0; column < 5; column++)
            {
                Pane pane = new Pane();
                panes[row][column] = pane;
                gridPane.add(pane, row, column);
                ImageView imageView = new ImageView("battleField BackGround/normal.png");
                pane.getChildren().add(imageView);
            }
        }
        rootBattleField.getChildren().add(gridPane);
        this.setBattleFieldPanes(panes);
        this.setBattleFieldGridPane(gridPane);
    }

    public void setPlayersName(Group rootBattleField)
    {
        Label firstPlayerName = new Label(this.getFirstPlayer().getAccount().getAccountName());   //1
        firstPlayerName.relocate(250, 50);
        firstPlayerName.setFont(Font.font(20));
        firstPlayerName.setTextFill(BLACK);
        rootBattleField.getChildren().add(firstPlayerName);

        Label secondPlayerName = new Label(this.getSecondPlayer().getAccount().getAccountName());  //1
        secondPlayerName.relocate(920, 50);
        secondPlayerName.setFont(Font.font(20));
        secondPlayerName.setTextFill(BLACK);
        rootBattleField.getChildren().add(secondPlayerName);
    }

    public void setMPIcons(Group rootBattleField)
    {
        for (int i = 0; i < 10; i++)
        {
            ImageView firstPlayerMPIcon = new ImageView("ManaIcons/icon_mana_inactive.png");
            if (this.getFirstPlayer().getMP() > i)                              //7
            {
                firstPlayerMPIcon = new ImageView("ManaIcons/icon_mana.png");
            }
            firstPlayerMPIcon.relocate(250 + i * 20, 90);

            rootBattleField.getChildren().add(firstPlayerMPIcon);
        }

        for (int i = 0; i < 10; i++)
        {
            ImageView secondPlayerMPIcon = new ImageView("ManaIcons/icon_mana_inactive.png");
            if (this.getSecondPlayer().getMP() > i)
            {
                secondPlayerMPIcon = new ImageView("ManaIcons/icon_mana.png");
            }
            secondPlayerMPIcon.relocate(1100 - i * 20, 90);

            rootBattleField.getChildren().add(secondPlayerMPIcon);
        }
    }
}

