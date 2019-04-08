public class Battle
{

    enum battleMode
    {
        killOpponentHero, keepFlag ,gatherFlags
    }

    static Battle currentBattle;
    private Account firstPlayer;
    private Account secondPlayer;
    private BattleField battleField;
    static Card selectedCard;

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

    public void moveCard(int x , int y){
        if(selectedCard.isCardSelectedInBattle()){

        }
    }

    public void attackToOpponent(int cardID){

    }

    public void comboAttack(String[] commandParts){

    }



}
