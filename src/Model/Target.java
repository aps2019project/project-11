package Model;

public class Target
{
    private int numOfOwnMinions;
    private int numOfOpponentMinions;
    private boolean ownHero;
    private boolean opponentHero;
    private int numOfOpponentBothNonSpellCards;
    private int numOfOwnBothNonSpellCards;
    private boolean isCellBuff;
    private int attackRangeLength;
    private int attackRangeWidth;
    private NonSpellCard nextNoneSpellCard;
    private boolean allOwnMinions;
    private boolean allOwnBothNonSpellCards;
    private boolean allOpponentNonSpellCards;

    public Target()
    {
        //for CustomCard
    }

    public Target(int numOfOwnMinions, int numOfOpponentMinions, boolean ownHero, boolean opponentHero, int numOfOpponentBothNonSpellCards, int numOfOwnBothNonSpellCards, boolean isCellBuff, int attackRangeLength, int attackRangeWidth, NonSpellCard nextNoneSpellCard , boolean allOwnMinions, boolean allOpponentNonSpellCards, boolean allOwnBothNonSpellCards)
    {
        this.numOfOwnMinions = numOfOwnMinions;
        this.numOfOpponentMinions = numOfOpponentMinions;
        this.ownHero = ownHero;
        this.opponentHero = opponentHero;
        this.numOfOpponentBothNonSpellCards = numOfOpponentBothNonSpellCards;
        this.numOfOwnBothNonSpellCards = numOfOwnBothNonSpellCards;
        this.isCellBuff = isCellBuff;
        this.attackRangeLength = attackRangeLength;
        this.attackRangeWidth = attackRangeWidth;
        this.nextNoneSpellCard = nextNoneSpellCard;
        this.allOwnMinions = allOwnMinions;
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

    public void setOwnHero(boolean ownHero) {
        this.ownHero = ownHero;
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

    public boolean isCellBuff()
    {
        return isCellBuff;
    }

    public int getAttackRangeLength()
    {
        return attackRangeLength;
    }

    public int getAttackRangeWidth()
    {
        return attackRangeWidth;
    }

    public NonSpellCard getNextNoneSpellCard()
    {
        return nextNoneSpellCard;
    }

    public void setNextNoneSpellCard(NonSpellCard nextNoneSpellCard) {
        this.nextNoneSpellCard = nextNoneSpellCard;
    }

    public boolean isAllOwnMinions() {
        return allOwnMinions;
    }

    public void setAllOwnMinions(boolean allOwnMinions) {
        this.allOwnMinions = allOwnMinions;
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

    public void setNumOfOwnBothNonSpellCards(int numOfOwnBothNonSpellCards) {
        this.numOfOwnBothNonSpellCards = numOfOwnBothNonSpellCards;
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
