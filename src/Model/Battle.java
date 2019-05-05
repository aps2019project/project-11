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
        this.setBattleMode(battleMode);
        this.battleType = battleType;
        currentBattle = this;
    }

    public static Battle getCurrentBattle()
    {
        return currentBattle;
    }

    public static void setCurrentBattle(Battle currentBattle)
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
                return new Player(account1);
            case 2:
                Account account2 = new Account();
                Deck deck2 = Deck.createMainDeckForStoryAccount(2);
                account2.addDeck(deck2);
                return new Player(account2);
            case 3:
                Account account3 = new Account();
                Deck deck3 = Deck.createMainDeckForStoryAccount(3);
                account3.addDeck(deck3);
                return new Player(account3);
        }
        return null;
    }

    public static Player makeCustomGamePlayer(String deckNameForCustomGame)
    {
        Account account = new Account();
        account.addDeck(DeckManager.findDeck(deckNameForCustomGame));
        return new Player(account);
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
        int[][] moveAbleCells = Battle.getCurrentBattle().getSelectedCard().setMoveAbleCells();
        if (moveAbleCells[x][y] == 1)
        {
            selectedCard.setRow(x);
            selectedCard.setColumn(y);
            this.getBattleField().getBattleFieldMatrix()[x][y].setCard(Battle.getCurrentBattle().getSelectedCard());
            System.out.println(selectedCard.getCardID() + " moved to " + x + " " + y);
        }
        else
        {
            System.out.println("Invalid Target");
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
                        damageCard( selectedCard, opponentCard);
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
                    damageCard(opponentCard, (NonSpellCards) selectedCard);
                }
            }
            else if (opponentCard.getImpactType() == ImpactType.ranged)
            {
                if (Card.findDestination(selectedCard, opponentCard) <= opponentCard.getRangeOfAttack() && !(Card.checkNeighborhood(selectedCard, opponentCard)))
                {
                    damageCard(opponentCard, (NonSpellCards) selectedCard);
                }
            }
            else if (opponentCard.getImpactType() == ImpactType.hybrid)
            {
                if (Card.findDestination(selectedCard, opponentCard) <= opponentCard.getRangeOfAttack())
                {
                    damageCard(opponentCard, (NonSpellCards) selectedCard);
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
        //todo //counterAttack\\
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

    public void endTurn()
    {
        if (Battle.getCurrentBattle().getPlayerTurn() == Battle.getCurrentBattle().getFirstPlayer())
        {
            Battle.getCurrentBattle().setPlayerTurn(Battle.getCurrentBattle().getSecondPlayer());
        }
        else
        {
            Battle.getCurrentBattle().setPlayerTurn(Battle.getCurrentBattle().getFirstPlayer());
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
                    setVictorious(secondPlayer);
                    setLoserPlayer(firstPlayer);
                    return true;
                }
                else if ((secondPlayer.getMainDeck().getHero().get(0)).getCurrentHP() <= 0)
                {
                    setVictorious(secondPlayer);
                    setLoserPlayer(firstPlayer);
                    return true;
                }
        }
        return false;
    }

    public void setVictorious(Player player)
    {
        victoriousPlayer = player;
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
    }

    public Player getFirstPlayer()
    {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer)
    {
        this.firstPlayer = firstPlayer;
    }

    public BattleField getBattleField()
    {
        return battleField;
    }

    public void setBattleField(BattleField battleField)
    {
        this.battleField = battleField;
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

    public Player getVictoriousPlayer()
    {
        return victoriousPlayer;
    }

    public void setVictoriousPlayer(Player victoriousPlayer)
    {
        this.victoriousPlayer = victoriousPlayer;
    }

    public BattleType getBattleType()
    {
        return battleType;
    }

    public void setBattleType(BattleType battleType)
    {
        this.battleType = battleType;
    }

    public void tasksAtEndOfGame()
    {
        switch (battleType)
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
            //todo
        }
    }

    public Player getLoserPlayer()
    {
        return loserPlayer;
    }

    public void setLoserPlayer(Player loserPlayer)
    {
        this.loserPlayer = loserPlayer;
    }
}
