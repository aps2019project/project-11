package Model;

import Controller.*;

import java.util.ArrayList;
import java.util.Random;

public class Battle
{
    private static Battle currentBattle;
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

    public Battle(Player firstPlayer, Player secondPlayer, BattleMode battleMode, BattleType battleType)
    {
        this.setFirstPlayer(firstPlayer);
        this.setSecondPlayer(secondPlayer);
        this.setPlayerTurn(firstPlayer);
        this.setBattleMode(battleMode);
        this.setBattleType(battleType);
        this.getBattleField().makeCells();
        Battle.setCurrentBattle(this);
        Item usableItemFirstPlayer = this.getFirstPlayer().getMainDeck().getItem().get(0);
        usableItemFirstPlayer.applyUsableItem(this.getFirstPlayer());
        Item usableItemSecondPlayer = this.getSecondPlayer().getMainDeck().getItem().get(0);
        usableItemSecondPlayer.applyUsableItem(this.getSecondPlayer());
    }

    public static Battle getCurrentBattle()
    {
        return currentBattle;
    }

    private static void setCurrentBattle(Battle currentBattle)
    {
        Battle.currentBattle = currentBattle;
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
        this.getBattleField().getBattleFieldMatrix()[selectedCard.getRow()][selectedCard.getColumn()].remove(selectedCard);
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
        Battle.getCurrentBattle().damageCard(selectedCard, opponentCard);
        Battle.getCurrentBattle().counterAttack(opponentCard);
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
        selectedCard = (NonSpellCard) Battle.getCurrentBattle().getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID1);
        NonSpellCard opponentCard = Battle.getCurrentBattle().getBattleField().findCardInBattleField(cardID2);
        counterAttack(opponentCard);
        setSelectedCard(null);
    }

    public void comboAttack(String enemyCardID, ArrayList<String> cardsIDForComboAttack)
    {
        checkComboCondition(cardsIDForComboAttack);
        NonSpellCard opponentCard = Battle.getCurrentBattle().getBattleField().findCardInBattleField(enemyCardID);
        for (String cardID : cardsIDForComboAttack)
        {
            NonSpellCard selectedCard = Battle.getCurrentBattle().getBattleField().findCardInBattleField(cardID);
            attackToOpponent(selectedCard, opponentCard);
        }
    }

    private void checkComboCondition(ArrayList<String> cardsIDForComboAttack)
    {
        for (String cardID : cardsIDForComboAttack)
        {
            if (Battle.getCurrentBattle().getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID) == null)
            {
                continue;
            }
            Card card = Battle.getCurrentBattle().getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID);
            if (!(card instanceof Minion) || !((Minion) card).isAbleToCombo())
            {
                cardsIDForComboAttack.removeIf(n -> n.equals(cardID));
            }
        }
    }

    public void checkInsertedCardsToApplySpellChange()
    {
        for (NonSpellCard nonSpellCard : Battle.getCurrentBattle().getBattleField().getAllCardsInTheBattleField())
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
        for (NonSpellCard nonSpellCard : Battle.getCurrentBattle().getBattleField().getAllCardsInTheBattleField())
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
        }
        playerTurn.getMainDeck().getHero().get(0).setMoveAble(true);
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
        int numOfInsertedMinions = Battle.getCurrentBattle().getPlayerTurn().getInsertedCards().size();
        int randomMinionNumber = random.nextInt(numOfInsertedMinions);
        return Battle.getCurrentBattle().getPlayerTurn().getInsertedCards().get(randomMinionNumber);
    }

    public Minion findRandomOwnRangedHybridMinionToApplyItem()
    {
        ArrayList<Minion> minions = new ArrayList<>();
        for (Minion minion : Battle.getCurrentBattle().getPlayerTurn().getInsertedCards())
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
                    setVictoriousPlayer(secondPlayer);
                    setLoserPlayer(firstPlayer);
                    return true;
                }
                else if ((secondPlayer.getMainDeck().getHero().get(0)).getCurrentHP() <= 0)
                {
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
        this.setLoserPlayer(this.getPlayerTurn());
        if (this.getSecondPlayer() == this.getPlayerTurn())
        {
            this.setVictoriousPlayer(this.getFirstPlayer());
        }
        else
        {
            this.setVictoriousPlayer(this.getSecondPlayer());
        }
        tasksAtEndOfGame();
    }

    public void tasksAtEndOfGame()
    {

        switch (this.getBattleType())
        {
            case STORY_GAME_1:
                victoriousPlayer.getAccount().addMoney(500);
                victoriousPlayer.getAccount().getMatchHistory().add(new FinishedMatch(loserPlayer.getAccount().getAccountName(), MatchResult.WIN, 0));
                loserPlayer.getAccount().getMatchHistory().add(new FinishedMatch(victoriousPlayer.getAccount().getAccountName(), MatchResult.LOSE, 0));
                break;
            case STORY_GAME_2:
                victoriousPlayer.getAccount().addMoney(1000);
                victoriousPlayer.getAccount().getMatchHistory().add(new FinishedMatch(loserPlayer.getAccount().getAccountName(), MatchResult.WIN, 0));
                loserPlayer.getAccount().getMatchHistory().add(new FinishedMatch(victoriousPlayer.getAccount().getAccountName(), MatchResult.LOSE, 0));
                break;
            case STORY_GAME_3:
                victoriousPlayer.getAccount().addMoney(1500);
                victoriousPlayer.getAccount().getMatchHistory().add(new FinishedMatch(loserPlayer.getAccount().getAccountName(), MatchResult.WIN, 0));
                loserPlayer.getAccount().getMatchHistory().add(new FinishedMatch(victoriousPlayer.getAccount().getAccountName(), MatchResult.LOSE, 0));
                break;
            case CUSTOM_GAME:
                victoriousPlayer.getAccount().addMoney(1000);
                victoriousPlayer.getAccount().getMatchHistory().add(new FinishedMatch(loserPlayer.getAccount().getAccountName(), MatchResult.WIN, 0));
                loserPlayer.getAccount().getMatchHistory().add(new FinishedMatch(victoriousPlayer.getAccount().getAccountName(), MatchResult.LOSE, 0));
                break;
            case MULTI_PLAYER_GAME:
                victoriousPlayer.getAccount().addMoney(1000);
                victoriousPlayer.getAccount().getMatchHistory().add(new FinishedMatch(loserPlayer.getAccount().getAccountName(), MatchResult.WIN, 0));
                loserPlayer.getAccount().getMatchHistory().add(new FinishedMatch(victoriousPlayer.getAccount().getAccountName(), MatchResult.LOSE, 0));
                break;
        }
    }

    public void AIPlayerWorks(BattleManager battleManager)
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
    }


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
        currentBattle.getBattleField().addCardInTheBattleField(firstPlayer.getMainDeck().getHero().get(0));
        currentBattle.getBattleField().addCardInTheBattleField(secondPlayer.getMainDeck().getHero().get(0));
    }
}
