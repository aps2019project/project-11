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
    private NonSpellCards selectedCard;
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
    }

    public static Battle getCurrentBattle()
    {
        return currentBattle;
    }

    private static void setCurrentBattle(Battle currentBattle)
    {
        Battle.currentBattle = currentBattle;
    }

    public static Player makeStoryPlayer(int selectedNumber)
    {
        switch (selectedNumber)
        {
            case 1:
                Account account1 = new Account();
                Deck deck1 = Deck.createMainDeckForStoryAccount(1);
                account1.addDeck(deck1);
                account1.setMainDeck(deck1);
                return new Player(account1, true);
            case 2:
                Account account2 = new Account();
                Deck deck2 = Deck.createMainDeckForStoryAccount(2);
                account2.addDeck(deck2);
                account2.setMainDeck(deck2);
                return new Player(account2, true);
            case 3:
                Account account3 = new Account();
                Deck deck3 = Deck.createMainDeckForStoryAccount(3);
                account3.addDeck(deck3);
                account3.setMainDeck(deck3);
                return new Player(account3, true);
        }
        return null;
    }

    public static Player makeCustomGamePlayer(String deckNameForCustomGame)
    {
        Account account = new Account();
        account.addDeck(DeckManager.findDeck(deckNameForCustomGame));
        return new Player(account, true);
    }

    public static BattleMode getBattleMode(int customGameMode)
    {
        switch (customGameMode)
        {
            case 1:
                return BattleMode.KILLING_ENEMY_HERO;
            case 2:
                return BattleMode.KEEP_FLAG_FOR_6_TURNS;
            case 3:
                return BattleMode.GATHERING_FLAGS;
        }
        return null;
    }

    public void selectCard(NonSpellCards card)
    {
        setSelectedCard(card);
        card.setCardSelectedInBattle(true);
    }

    public void selectCollectibleItem(Item item)
    {
        setSelectedICollectibleItem(item);
        item.setCollectibleItemSelectedInBattle(true);
    }

    public void moveCard(int x, int y)
    {
        NonSpellCards card = Battle.getCurrentBattle().getSelectedCard();
        int[][] moveAbleCells = card.setMoveAbleCells();
        if (selectedCard.isMoveAble())
        {
            if (moveAbleCells[x][y] == 1)
            {
                this.getBattleField().getBattleFieldMatrix()[card.getRow()][card.getColumn()].remove(card);
                card.setRow(x);
                card.setColumn(y);
                this.getBattleField().getBattleFieldMatrix()[x][y].setCard(card);
                System.out.println(card.getCardID() + " moved to " + x + " " + y);
                card.setMoveAble(false);
            }
            else
            {
                System.out.println("Invalid Target");
            }
        }
        else
        {
            System.out.println("this card is not movable");
        }
    }

    private void damageCard(NonSpellCards selectedCard, NonSpellCards opponentCard)
    {
        int currentHP = opponentCard.getCurrentHP();
        opponentCard.setCurrentHP(currentHP - selectedCard.getCurrentAP());
    }

    public void attackToOpponent(String cardID)
    {
        if (Battle.getCurrentBattle().getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID) == null)
        {
            System.out.println("Invalid card id");
            return;
        }
        NonSpellCards opponentCard = Battle.getCurrentBattle().getBattleField().findCardInBattleField(cardID);
        if (selectedCard.isCardSelectedInBattle())
        {
            if ((selectedCard).isAttackAble())
            {
                if ((selectedCard).getImpactType() == ImpactType.melee)
                {
                    if (Card.checkNeighborhood(selectedCard, opponentCard))
                    {
                        damageCard(selectedCard, opponentCard);
                        (selectedCard).setAttackAble(false);
                    }
                    else
                    {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                }
                else if ((selectedCard).getImpactType() == ImpactType.ranged)
                {
                    if (Card.findDestination(selectedCard, opponentCard) <= (selectedCard).getRangeOfAttack() && !(Card.checkNeighborhood(selectedCard, opponentCard)))
                    {
                        damageCard(selectedCard, opponentCard);
                        (selectedCard).setAttackAble(false);
                    }
                    else
                    {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                }
                else if ((selectedCard).getImpactType() == ImpactType.hybrid)
                {
                    if (Card.findDestination(selectedCard, opponentCard) <= (selectedCard).getRangeOfAttack())
                    {
                        damageCard(selectedCard, opponentCard);
                        (selectedCard).setAttackAble(false);
                    }
                    else
                    {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                }

            }
            else
            {
                System.out.println("Card with " + selectedCard.getCardID() + " can′t attack");
            }
        }
    }


    public void counterAttack(String cardID)
    {
        NonSpellCards opponentCard = Battle.getCurrentBattle().getBattleField().findCardInBattleField(cardID);
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

    public void counterAttack(String cardID1, String cardID2)
    {
        selectedCard = (NonSpellCards) Battle.getCurrentBattle().getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID2);
        counterAttack(cardID1);
        selectedCard = null;

    }

    public void comboAttack(String enemyCardID, ArrayList<String> cardsIDForComboAttack)
    {
        checkComboCondition(cardsIDForComboAttack);
        for (String cardID : cardsIDForComboAttack)
        {
            new BattleManager().selectCard(cardID);
            attackToOpponent(enemyCardID);
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
        for (NonSpellCards nonSpellCard : Battle.getCurrentBattle().getBattleField().getAllCardsInTheBattleField())
        {
            for (SpellChange spellChange : nonSpellCard.getActiveSpellsOnThisCard())
            {
                //todo
                spellChange.applySpellChangeOnCard(nonSpellCard);
            }
        }
    }

    public void checkUsedItemsToApplyItemChange()
    {
        for (NonSpellCards nonSpellCard : Battle.getCurrentBattle().getBattleField().getAllCardsInTheBattleField())
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
        //todo apply special powers

        checkUsedItemsToApplyItemChange();
        this.getPlayerTurn().increaseDefaultMP();
        if (this.getPlayerTurn() == this.getFirstPlayer())
        {
            this.setPlayerTurn(this.getSecondPlayer());
        }
        else
        {
            this.setPlayerTurn(this.getFirstPlayer());
        }
        this.getPlayerTurn().setMP();
        checkInsertedCardsToApplySpellChange();
        checkUsedItemsToApplyItemChange();
        for (ItemChange itemChange : this.getPlayerTurn().getActiveItemsOnPlayer())
        {
            itemChange.applyItemChange(this.getPlayerTurn());
        }
    }

    public NonSpellCards findRandomOwnForce()
    {
        ArrayList<NonSpellCards> ownNonSpellCards = new ArrayList<>();
        for (NonSpellCards nonSpellCard : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (NonSpellCards ownNonSpellCard : this.getPlayerTurn().getInsertedCards())
            {
                if (nonSpellCard.getCardID().equals(ownNonSpellCard.getCardID()))
                {
                    ownNonSpellCards.add(ownNonSpellCard);
                }
            }
        }
        int randomMinionNumber = random.nextInt(ownNonSpellCards.size());
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

    public NonSpellCards findRandomOpponentNonSpellCardToApplyUsableItem()
    {
        ArrayList<NonSpellCards> opponentNonSpellCards = new ArrayList<>();
        Outer:
        for (NonSpellCards nonSpellCard : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (NonSpellCards ownNonSpellCard : this.getPlayerTurn().getInsertedCards())
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

    public ArrayList<NonSpellCards> findingOpponentNonSpellCards()
    {
        ArrayList<NonSpellCards> opponentNonSpellCards = new ArrayList<>();
        FirstFor:
        for (NonSpellCards nonSpellCards : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (NonSpellCards ownNonSpellCards : this.getPlayerTurn().getInsertedCards())
            {
                if (Integer.parseInt(nonSpellCards.getCardID()) == Integer.parseInt(ownNonSpellCards.getCardID()))
                {
                    continue FirstFor;
                }
            }
            opponentNonSpellCards.add(nonSpellCards);
        }
        return opponentNonSpellCards;
    }

    public ArrayList<NonSpellCards> findingOwnNonSpellCards()
    {
        ArrayList<NonSpellCards> ownNonSpellCards = new ArrayList<>();
        for (NonSpellCards nonSpellCards : this.getBattleField().getAllCardsInTheBattleField())
        {
            for (NonSpellCards AllOwnNonSpellCards : this.getFirstPlayer().getInsertedCards())
            {
                if (Integer.parseInt(AllOwnNonSpellCards.getCardID()) == Integer.parseInt(nonSpellCards.getCardID()))
                {
                    ownNonSpellCards.add(AllOwnNonSpellCards);
                }
            }
        }
        return ownNonSpellCards;
    }

    public void showGraveYardCardInfo(String cardID)
    {
        Card card = playerTurn.findCardInGraveYard(cardID);
        if (card != null)
        {
            card.printCardStats();
        }
    }

    public void showAllCardsInTheGraveYard()
    {
        int counter = 1;
        System.out.println("first Player Grave Yard :");
        for (Card card : firstPlayer.getGraveYard().getCards())
        {
            card.printCardStats(counter);
            counter++;
        }
        counter = 1;
        System.out.println("second Player Grave Yard :");
        for (Card card : secondPlayer.getGraveYard().getCards())
        {
            card.printCardStats(counter);
            counter++;
        }
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
                    setVictoriousPlayer(secondPlayer);
                    setLoserPlayer(firstPlayer);
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
        Item usableItem = this.getSecondPlayer().getMainDeck().getItem().get(0);
        usableItem.applyUsableItem();
    }

    public Player getFirstPlayer()
    {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer)
    {
        this.firstPlayer = firstPlayer;
        this.getFirstPlayer().setMP();
        Item usableItem = this.getFirstPlayer().getMainDeck().getItem().get(0);
        usableItem.applyUsableItem();
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

    public NonSpellCards getSelectedCard()
    {
        return selectedCard;
    }

    public void setSelectedCard(NonSpellCards selectedCard)
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
            NonSpellCards firstPlayerCard = firstPlayer.getInsertedCards().get((int) (Math.random() % playerTurn.getInsertedCards().size()));
            attackToOpponent(firstPlayerCard.getCardName());
            counterAttack(selectedCard.getCardName(), firstPlayerCard.getCardID());
        }
        this.setPlayerTurn(firstPlayer);
        selectedCard = null;
    }


    public void help()
    {
        for (Card card : playerTurn.getInsertedCards())
        {
            if (((NonSpellCards) card).isMoveAble())
            {
                System.out.println(card.getCardName() + " is movable");
            }
        }
        for (Card card : playerTurn.getInsertedCards())
        {
            if (((NonSpellCards) card).isAttackAble())
            {
                int[][] matrix = setAttackRangeMatrix((NonSpellCards) card);
                for (NonSpellCards enemyCard : getOpponentPlayer().getInsertedCards())
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

    private int[][] setAttackRangeMatrix(NonSpellCards card)
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

    public void setHeroesInBattlefield(BattleManager battleManager) {
        battleManager.insertCardToBattleField(firstPlayer.getMainDeck().getHero().get(0), 2, 0);
        battleManager.insertCardToBattleField(secondPlayer.getMainDeck().getHero().get(0) , 2 , 8);

    }
}
