package Model;

import Controller.AccountManager;
import View.Request;

import java.util.regex.Pattern;

public class Battle
{
    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    enum battleMode
    {
        killOpponentHero, keepFlag ,gatherFlags
    }

    static Battle currentBattle;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player playerTurn;
    private BattleField battleField;
    static Card selectedCard;
    static Item selectedICollectibleItem;

    public Battle(Player firstPlayer, Player secondPlayer)
    {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public static void singlePlayerMatch()
    {
        while (true)
        {

        }
    }

    public static void multiPlayerMatch()
    {
        Account.showAllPlayers();
        String secondPlayerName = null; //todo get from scanner
        Player playerOne = new Player();
        Player playerTwo = new Player();
        playerOne.setAccount(Account.loggedInAccount);
        playerTwo.setAccount(AccountManager.findAccount(secondPlayerName));
        currentBattle = new Battle(playerOne, playerTwo);
        currentBattle.selectMatchMode();
        //TODO
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

    public static void showGameInfo()
    {

    }

    public static void showMyMinions()
    {

    }

    public static void showOpponentMinions()
    {

    }

    public void showAllOwnForcesInfo()
    {

    }

    public void showAllOpponentForcesInfo()
    {

    }

    public static void showCardInfo(int cardID)
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

    public static void moveCard(int x , int y){
        if(selectedCard.isCardSelectedInBattle())
        {

        }
    }

    public void attackToOpponent(int cardID){
        if(selectedCard.isCardSelectedInBattle())
        {

        }
    }

    public void comboAttack(String[] commandParts){


    }

    public void useSpecialPower(int x, int y)
    {
        if(selectedCard.isCardSelectedInBattle()){

        }
    }

    public void showHand()
    {

    }

    public void insertCard(String cardName, int x, int y)
    {

    }

    public void endTurn()
    {

    }

    public void showCollectableItems()
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

    }

    public void showGraveYardCardInfo(int cardID)
    {
        Card card = playerTurn.findCardInGraveYard(Request.command.cardOrItemIDInGraveYard);
        if(card != null) {
            card.printCardStats();
        }
    }

    public void showAllCardsInTheGraveYard() {
        int counter = 1;
        System.out.println("first Player Grave Yard :");
        for (Card card : firstPlayer.getGraveYard()) {
            card.printCardStats(counter);
            counter++;
        }
        counter = 1;
        System.out.println("second Player Grave Yard :");
        for (Card card : secondPlayer.getGraveYard()) {
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
