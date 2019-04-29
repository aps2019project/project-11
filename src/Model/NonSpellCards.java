package Model;

import java.util.ArrayList;

public abstract class NonSpellCards extends Card
{
    private int defaultHP;
    private int defaultAP;
    private int currentHP;
    private int currentAP;
    private SpecialPower specialPower;
    private ArrayList<Spell> spellsAppliedOnThisCard = new ArrayList<>();
    private ImpactType impactType;
    private boolean moveAble = true;
    private boolean attackAble = true;
    private boolean counterAttackAble = true;

    public Cell [][] setAttackAbleCells()
    {
        Cell[][] cells = new Cell[5][9];
        //TODO
        return cells;
    }

    public static void setNonSpellCards()
    {
        Hero.setHeroes();
        Minion.setMinions();
    }

    public ImpactType getTypeOfImpact()
    {
        return impactType;
    }

    public int getDefaultHP() {
        return defaultHP;
    }

    public void setDefaultHP(int defaultHP) {
        this.defaultHP = defaultHP;
    }

    public int getDefaultAP() {
        return defaultAP;
    }

    public void setDefaultAP(int defaultAP) {
        this.defaultAP = defaultAP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getCurrentAP() {
        return currentAP;
    }

    public SpecialPower getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(SpecialPower specialPower) {
        this.specialPower = specialPower;
    }

    public void setImpactType(ImpactType impactType) {
        this.impactType = impactType;
    }

    public boolean isMoveAble() {
        return moveAble;
    }

    public void setMoveAble(boolean moveAble) {
        this.moveAble = moveAble;
    }

    public boolean isAttackAble() {
        return attackAble;
    }

    public void setAttackAble(boolean attackAble) {
        this.attackAble = attackAble;
    }

    public boolean isCounterAttackAble() {
        return counterAttackAble;
    }

    public void setCounterAttackAble(boolean counterAttackAble) {
        this.counterAttackAble = counterAttackAble;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setCurrentAP(int currentAP) {
        this.currentAP = currentAP;
    }
}
