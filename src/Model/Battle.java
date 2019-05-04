package Model;

import Controller.BattleManager;
import Controller.DeckManager;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
    private static Battle currentBattle;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player playerTurn;
    private BattleField battleField;
    private NonSpellCards selectedCard;
    private Item selectedICollectibleItem;
    private BattleMode battleMode;
    private int numOfFlagsInGatheringFlagsMatchMode;
    private Random random = new Random();

    public Battle(Player firstPlayer, Player secondPlayer, BattleMode battleMode)
    {
        this.setFirstPlayer(firstPlayer);
        this.setSecondPlayer(secondPlayer);
        this.setBattleMode(battleMode);
        currentBattle = this;
    }

    public Battle(){}

    public static Battle getCurrentBattle() {
        return currentBattle;
    }

    public static void setCurrentBattle(Battle currentBattle) {
        Battle.currentBattle = currentBattle;
    }

    public static Player makeStoryPlayer(int selectedNumber) {
        switch (selectedNumber){
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

    public static Player makeCustomGamePlayer(String deckNameForCustomGame) {
        Account account = new Account();
        account.addDeck(DeckManager.findDeck(deckNameForCustomGame));
        return new Player(account);
    }

    public static BattleMode getBattleMode(int customGameMode) {
        switch (customGameMode){
            case 1:
                return BattleMode.KILLING_ENEMY_HERO;
            case 2:
                return BattleMode.KEEP_FLAG_FOR_6_TURNS;
            case 3:
                return BattleMode.GATHERING_FLAGS;
        }
    }

    public void customMode() {

    }

    public void killHeroMode() {

    }

    public void keepFlagMode() {

    }

    public void gatherFlags(int numOfFlags) {

    }

    public void showAllOwnForcesInfo() {

    }

    public void showAllOpponentForcesInfo() {

    }

    public void selectCard(NonSpellCards card) {
        setSelectedCard(card);
        card.setCardSelectedInBattle(true);
    }

    public void selectCollectibleItem(Item item) {
        setSelectedICollectibleItem(item);
        item.setCollectibleItemSelectedInBattle(true);
    }

    public void moveCard(int x, int y) {
        if (selectedCard.isCardSelectedInBattle()) {

        }
    }

    private void damageCard(NonSpellCards selectedCard, NonSpellCards opponentCard) {
        int currentHP = opponentCard.getCurrentHP();
        opponentCard.setCurrentHP(currentHP - selectedCard.getCurrentAP());
    }

    public void attackToOpponent(int cardID) {
        if (Card.findCard(cardID) == null) {
            System.out.println("Invalid card id");
            return;
        }
        Card opponentCard = Card.findCard(cardID);
        if (selectedCard.isCardSelectedInBattle()) {
            if (((NonSpellCards) selectedCard).isAttackAble()) {
                if (((Minion) selectedCard).getImpactType() == ImpactType.melee) {
                    if (Card.checkNeighborhood(selectedCard, opponentCard)) {
                        damageCard((NonSpellCards) selectedCard, (NonSpellCards) opponentCard);
                        ((Minion) selectedCard).setAttackAble(false);
                    } else {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                } else if (((Minion) selectedCard).getImpactType() == ImpactType.ranged) {
                    if (Card.findDestination(selectedCard, opponentCard) <= ((Minion) selectedCard).getRangeOfAttack() && !(Card.checkNeighborhood(selectedCard, opponentCard))) {
                        damageCard((NonSpellCards) selectedCard, (NonSpellCards) opponentCard);
                        ((Minion) selectedCard).setAttackAble(false);
                    } else {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                } else if (((Minion) selectedCard).getImpactType() == ImpactType.hybrid) {
                    if (Card.findDestination(selectedCard, opponentCard) <= ((Minion) selectedCard).getRangeOfAttack()) {
                        damageCard((NonSpellCards) selectedCard, (NonSpellCards) opponentCard);
                        ((Minion) selectedCard).setAttackAble(false);
                    } else {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                }

            } else {
                System.out.println("Card with " + selectedCard.getCardID() + " can′t attack");
            }
        }
    }


    public void counterAttack(int cardID) {
        Card opponentCard = Card.findCard(cardID);
        if (((NonSpellCards) opponentCard).isCounterAttackAble()) {
            if (((Minion) opponentCard).getImpactType() == ImpactType.melee) {
                if (Card.checkNeighborhood(selectedCard, opponentCard)) {
                    damageCard((NonSpellCards) opponentCard, (NonSpellCards) selectedCard);
                }
            } else if (((Minion) opponentCard).getImpactType() == ImpactType.ranged) {
                if (Card.findDestination(selectedCard, opponentCard) <= ((Minion) opponentCard).getRangeOfAttack() && !(Card.checkNeighborhood(selectedCard, opponentCard))) {
                    damageCard((NonSpellCards) opponentCard, (NonSpellCards) selectedCard);
                }
            } else if (((Minion) opponentCard).getImpactType() == ImpactType.hybrid) {
                if (Card.findDestination(selectedCard, opponentCard) <= ((Minion) opponentCard).getRangeOfAttack()) {
                    damageCard((NonSpellCards) opponentCard, (NonSpellCards) selectedCard);
                }
            }
        }
    }

    public void counterAttack(int cardID1 , int cardID2)
    {
        selectedCard = (NonSpellCards) Card.findCard(cardID2);
        counterAttack(cardID1);
        selectedCard = null;

    }

    public void comboAttack(int enemyCardID, ArrayList<Integer> cardsIDForComboAttack) {
        checkComboCondition(cardsIDForComboAttack);
        for (int cardID : cardsIDForComboAttack) {
            new BattleManager().selectCard(cardID);
            attackToOpponent(enemyCardID);
        }
        //todo //counterAttcak\\
    }

    private void checkComboCondition(ArrayList<Integer> cardsIDForComboAttack) {
        for (int cardID : cardsIDForComboAttack) {
            if (Card.findCard(cardID) == null) {
                continue;
            }
            Card card = Card.findCard(cardID);
            if (!(card instanceof Minion) || !((Minion) card).isAbleToCombo()) {
                cardsIDForComboAttack.removeIf(n -> n == cardID);
            }
        }
    }



    public void insertCard(String cardName, int x, int y) {

    }

    public void endTurn() {

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
        return Battle.getCurrentBattle().getPlayerTurn().getInsertedCards().get(randomMinionNumber);
    }

    public void showGraveYardCardInfo(int cardID) {
        Card card = playerTurn.findCardInGraveYard(cardID);
        if (card != null) {
            card.printCardStats();
        }
    }

    public void showAllCardsInTheGraveYard() {
        int counter = 1;
        System.out.println("first Player Grave Yard :");
        for (Card card : firstPlayer.getGraveYard().getCards()) {
            card.printCardStats(counter);
            counter++;
        }
        counter = 1;
        System.out.println("second Player Grave Yard :");
        for (Card card : secondPlayer.getGraveYard().getCards()) {
            card.printCardStats(counter);
            counter++;
        }
    }

    public void logicEndGame() {

    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void setBattleField(BattleField battleField) {
        this.battleField = battleField;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(NonSpellCards selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Item getSelectedICollectibleItem() {
        return selectedICollectibleItem;
    }

    public void setSelectedICollectibleItem(Item selectedICollectibleItem) {
        this.selectedICollectibleItem = selectedICollectibleItem;
    }

    public BattleMode getBattleMode() {
        return battleMode;
    }

    public void setBattleMode(BattleMode battleMode) {
        this.battleMode = battleMode;
    }

    public int getNumOfFlagsInGatheringFlagsMatchMode() {
        return numOfFlagsInGatheringFlagsMatchMode;
    }

    public void setNumOfFlagsInGatheringFlagsMatchMode(int numOfFlagsInGatheringFlagsMatchMode) {
        this.numOfFlagsInGatheringFlagsMatchMode = numOfFlagsInGatheringFlagsMatchMode;
    }
}
