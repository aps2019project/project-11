package Model;

import java.util.ArrayList;

public class Target
{
    private int numOfOwnMinions = 0;
    private int numOfOpponentMinions = 0;
    private boolean ownHero = false;
    private boolean opponentHero = false;
    private int numOfOpponentBothNonSpellCards = 0;
    private int numOfOwnBothNonSpellCards = 0;
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;

    public Target(int numOfOwnMinions, int numOfOpponentMinions, boolean ownHero, boolean opponentHero, int numOfOpponentBothNonSpellCards, int numOfOwnBothNonSpellCards, int startRow, int startColumn, int endRow, int endColumn, ImpactType impactType, int maxAttackRange) {
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

    private ImpactType impactType;
    private int maxAttackRange;
}
