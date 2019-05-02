package Model;

import Controller.BattleManager;

import java.util.ArrayList;

public class Battle
{
    private static Battle currentBattle;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player playerTurn;
    private BattleField battleField;
    private Card selectedCard;
    private Item selectedICollectibleItem;
    private BattleMode battleMode;
    private int numOfFlagsInGatheringFlagsMatchMode;

    public Battle(Player firstPlayer, Player secondPlayer, BattleMode battleMode)
    {
        this.setFirstPlayer(firstPlayer);
        this.setSecondPlayer(secondPlayer);
        this.setBattleMode(battleMode);
        currentBattle = this;
    }

    public void storyMode()
    {

    }
    public void customMode()
    {

    }

    public void killHeroMode()
    {

    }

    public void keepFlagMode()
    {

    }

    public void gatherFlags(int numOfFlags)
    {

    }

    public void showAllOwnForcesInfo()
    {

    }

    public void showAllOpponentForcesInfo()
    {

    }

    public void selectCard(Card card)
    {
        setSelectedCard(card);
        card.setCardSelectedInBattle(true);
    }

    public void selectCollectibleItem(Item item)
    {
        setSelectedICollectibleItem(item);
        item.setCollectibleItemSelectedInBattle(true);
    }

    public void moveCard(int x , int y)
    {
        if(selectedCard.isCardSelectedInBattle())
        {

        }
    }

    public void attackToOpponent(int cardID){
        if(selectedCard.isCardSelectedInBattle())
        {

        }
    }

    public void comboAttack(int enemyCardID , ArrayList<Integer> cardsIDForComboAttack)
    {
        checkComboCondition(cardsIDForComboAttack);
        for(int cardID : cardsIDForComboAttack)
        {
            new BattleManager().selectCard(cardID);
            attackToOpponent(enemyCardID);
        }
        //todo //counterAttcak\\
    }

    private void checkComboCondition(ArrayList<Integer> cardsIDForComboAttack) {
        for(int cardID : cardsIDForComboAttack) {
            if(Card.findCard(cardID) == null){
                continue;
            }
            Card card = Card.findCard(cardID);
            if(!(card instanceof Minion) || !((Minion)card).isAbleToCombo()){
                cardsIDForComboAttack.removeIf(n -> n == cardID);
            }
        }
    }

    public void useSpecialPower(int x, int y)
    {
        if(selectedCard.isCardSelectedInBattle()){

        }
    }

    public void insertCard(String cardName, int x, int y)
    {

    }

    public void endTurn()
    {

    }

    public void showCollectibleItems()
    {

    }

    public void showCollectibleItemInfo()
    {

    }

    public void useCollectibleItem(int x, int y)
    {

    }

    public void showGraveYardCardInfo(int cardID)
    {
        Card card = playerTurn.findCardInGraveYard(cardID);
        if(card != null)
        {
            card.printCardStats();
        }
    }

    public void showAllCardsInTheGraveYard()
    {
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

    public void logicEndGame()
    {

    }

    public Player getPlayerTurn()
    {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn)
    {
        this.playerTurn = playerTurn;
    }

    public static Battle getCurrentBattle() {
        return currentBattle;
    }

    public static void setCurrentBattle(Battle currentBattle) {
        Battle.currentBattle = currentBattle;
    }
    public Player getSecondPlayer() {
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

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public BattleField getBattleField()
    {
        return battleField;
    }

    public void setBattleField(BattleField battleField) {
        this.battleField = battleField;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard)
    {
        this.selectedCard = selectedCard;
    }

    public Item getSelectedICollectibleItem() {
        return selectedICollectibleItem;
    }

    public void setSelectedICollectibleItem(Item selectedICollectibleItem)
    {
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
