package Model;

import View.Request;
import View.ShowOutput;

import java.util.ArrayList;
import java.util.Map;

public class Battle
{
    public static Battle currentBattle;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player playerTurn;
    private BattleField battleField;
    static Card selectedCard;
    static Item selectedICollectibleItem;
    private BattleMode battleMode;

    public Player getPlayerTurn()
    {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn)
    {
        this.playerTurn = playerTurn;
    }

    public Battle(Player firstPlayer, Player secondPlayer)
    {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
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

    public BattleField getBattleField()
    {
        return battleField;
    }

    public static void multiPlayerMatch()
    {
        String secondPlayerName = null; //todo get from scanner
        Player playerOne = new Player();
        Player playerTwo = new Player();
        playerOne.setAccount(Account.loggedInAccount);
        //playerTwo.setAccount(Account.findAccount(secondPlayerName));
        currentBattle = new Battle(playerOne, playerTwo);
        currentBattle.selectMatchMode();

    }

    public void selectMatchMode()
    {

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

    public static void selectCard(int cardID)
    {
        Card card = Card.findCard(cardID);
        if (card != null)
        {
            card.setCardSelectedInBattle(true);
            selectedCard = card;
        }
    }

    public void selectCollectibleItem(int collectibleItemID)
    {

    }

    public static void moveCard(int x , int y)
    {
        if(selectedCard.isCardSelectedInBattle())
        {

        }
    }

    public void attackToOpponent(int cardID){
        Card opponentCard = Card.findCard(cardID);
        if(selectedCard.isCardSelectedInBattle())
        {
            if(((Minion)selectedCard).getTypeOfImpact() == ImpactType.melee){

            }
        }
    }

    public void comboAttack(int enemyCardID , ArrayList<Integer> cardsIDForComboAttack){
        checkComboCondition(cardsIDForComboAttack);
        for(int cardID : cardsIDForComboAttack){
            selectCard(cardID);
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

    public void showNextCard()
    {
        ShowOutput.showNextCardInfo(playerTurn.getHand().getNextCard());
    }

    public void showGraveYardCardInfo(int cardID)
    {
        Card card = playerTurn.findCardInGraveYard(Request.command.cardOrItemIDInGraveYard);
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

    public void help()
    {

    }

    public void showBattleCommands()
    {

    }

    public void logicEndGame()
    {

    }
}
