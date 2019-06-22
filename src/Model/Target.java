package Model;

public class Target
{
    private int numOfOwnMinions;
    private int numOfOpponentMinions;
    private boolean ownHero;
    private boolean opponentHero;
    private int numOfOpponentBothNonSpellCards;
    private int numOfOwnBothNonSpellCards;
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private int maxAttackRange;
    private NonSpellCard nextNoneSpellCard;
    private boolean allOwnMinion;
    private boolean allOwnBothNonSpellCards;
    private boolean allOpponentNonSpellCards;

    public Target(int numOfOwnMinions, int numOfOpponentMinions, boolean ownHero, boolean opponentHero, int numOfOpponentBothNonSpellCards, int numOfOwnBothNonSpellCards, int startRow, int startColumn, int endRow, int endColumn, int maxAttackRange , NonSpellCard nextNoneSpellCard , boolean allOwnMinion, boolean allOpponentNonSpellCards, boolean allOwnBothNonSpellCards)
    {
        this.numOfOwnMinions = numOfOwnMinions;
        this.numOfOpponentMinions = numOfOpponentMinions;
        this.ownHero = ownHero;
        this.opponentHero = opponentHero;
        this.numOfOpponentBothNonSpellCards = numOfOpponentBothNonSpellCards;
        this.numOfOwnBothNonSpellCards = numOfOwnBothNonSpellCards;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
        this.maxAttackRange = maxAttackRange;
        this.nextNoneSpellCard = nextNoneSpellCard;
        this.allOwnMinion = allOwnMinion;
        this.allOpponentNonSpellCards = allOpponentNonSpellCards;
        this.allOwnBothNonSpellCards = allOwnBothNonSpellCards;
    }

    public Target(int numOfOwnMinions, int numOfOpponentMinions, boolean ownHero, boolean opponentHero, int numOfOpponentBothNonSpellCards, int numOfOwnBothNonSpellCards, int startRow, int startColumn, int endRow, int endColumn, NonSpellCard nextNoneSpellCard , boolean allOwnMinion, boolean allOpponentNonSpellCards, boolean allOwnBothNonSpellCards)
    {
        this.numOfOwnMinions = numOfOwnMinions;
        this.numOfOpponentMinions = numOfOpponentMinions;
        this.ownHero = ownHero;
        this.opponentHero = opponentHero;
        this.numOfOpponentBothNonSpellCards = numOfOpponentBothNonSpellCards;
        this.numOfOwnBothNonSpellCards = numOfOwnBothNonSpellCards;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
        this.nextNoneSpellCard = nextNoneSpellCard;
        this.allOwnMinion = allOwnMinion;
        this.allOpponentNonSpellCards = allOpponentNonSpellCards;
        this.allOwnBothNonSpellCards = allOwnBothNonSpellCards;
    }


    public int getNumOfOwnMinions()
    {
        return numOfOwnMinions;
    }

    public int getNumOfOpponentMinions()
    {
        return numOfOpponentMinions;
    }

    public boolean isOwnHero() {
        return ownHero;
    }

    public boolean isOpponentHero() {
        return opponentHero;
    }

    public int getNumOfOpponentBothNonSpellCards() {
        return numOfOpponentBothNonSpellCards;
    }

    public int getNumOfOwnBothNonSpellCards() {
        return numOfOwnBothNonSpellCards;
    }

    public int getStartRow()
    {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getEndRow()
    {
        return endRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public int getMaxAttackRange() {
        return maxAttackRange;
    }

    public NonSpellCard getNextNoneSpellCard()
    {
        return nextNoneSpellCard;
    }

    public void setNextNoneSpellCard(NonSpellCard nextNoneSpellCard) {
        this.nextNoneSpellCard = nextNoneSpellCard;
    }

    public boolean isAllOwnMinion() {
        return allOwnMinion;
    }

    public void setAllOwnMinion(boolean allOwnMinion) {
        this.allOwnMinion = allOwnMinion;
    }

    public boolean isAllOpponentNonSpellCards() {
        return allOpponentNonSpellCards;
    }

    public void setAllOpponentNonSpellCards(boolean allOpponentNonSpellCards) {
        this.allOpponentNonSpellCards = allOpponentNonSpellCards;
    }

    public boolean isAllOwnBothNonSpellCards() {
        return allOwnBothNonSpellCards;
    }

    public void setAllOwnBothNonSpellCards(boolean allOwnBothNonSpellCards) {
        this.allOwnBothNonSpellCards = allOwnBothNonSpellCards;
    }

    public void setNumOfOpponentBothNonSpellCards(int numOfOpponentBothNonSpellCards) {
        this.numOfOpponentBothNonSpellCards = numOfOpponentBothNonSpellCards;
    }

    public void setNumOfOwnMinions(int numOfOwnMinions) {
        this.numOfOwnMinions = numOfOwnMinions;
    }

    public void setNumOfOpponentMinions(int numOfOpponentMinions) {
        this.numOfOpponentMinions = numOfOpponentMinions;
    }

    public void setOpponentHero(boolean opponentHero) {
        this.opponentHero = opponentHero;
    }
}
