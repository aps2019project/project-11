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
    private ImpactType impactType;
    private int maxAttackRange;

    public Target(int numOfOwnMinions, int numOfOpponentMinions, boolean ownHero, boolean opponentHero, int numOfOpponentBothNonSpellCards, int numOfOwnBothNonSpellCards, int startRow, int startColumn, int endRow, int endColumn, ImpactType impactType, int maxAttackRange , NonSpellCards nextNoneSpellCard)
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
        this.impactType = impactType;
        this.maxAttackRange = maxAttackRange;
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

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public ImpactType getImpactType() {
        return impactType;
    }

    public int getMaxAttackRange() {
        return maxAttackRange;
    }
}
