import java.util.ArrayList;

public class Battle
{


    public Account getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Account playerTurn) {
        this.playerTurn = playerTurn;
    }

    enum battleMode
    {
        killOpponentHero, keepFlag ,gatherFlags
    }

    static Battle currentBattle;
    private Account firstPlayer;
    private Account secondPlayer;
    private Account playerTurn;
    private BattleField battleField;
    static Card selectedCard;
    static Item selectedICollectibleItem;

    public Battle(Account firstPlayer, Account secondPlayer)
    {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public Account getSecondPlayer() {
        return secondPlayer;
    }

    public Account getFirstPlayer() {
        return firstPlayer;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public static void singlePlayerMatch()
    {
        while (true)
        {
            String line = Main.myScanner.nextLine();

        }
    }

    public static void multiPlayerMatch()
    {
        Account.showAllPlayers();
        String secondPlayerName = Main.myScanner.nextLine();
        Account secondPlayer = Account.findAccount(secondPlayerName);
        currentBattle = new Battle(Account.loggedInAccount, secondPlayer);
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

    public void showGameInfo()
    {

    }

    public void showAllOwnForcesInfo()
    {

    }

    public void showAllOpponentForcesInfo()
    {

    }

    public void showCardInfo(int cardID)
    {

    }

    public void selectCard(int cardID){
        Card card = Card.findCard(cardID);
        card.setCardSelectedInBattle(true);
        selectedCard = card;
    }

    public void selectCollectibleItem(int collectibleItemID)
    {

    }

    public void moveCard(int x , int y){
        if(selectedCard.isCardSelectedInBattle()){

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

    }

    public void showAllCardsInTheGraveYard()
    {

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
