public abstract class NonSpellCards extends Card
{
    private int defaultHP;
    private int defaultAP;
    private int currentHP;
    private int currentAP;
    private Spell specialPower;
    private ImpactType impactType;
    private int maxAttackRange;
    private boolean enabledHolyBuff = false;
    private boolean enableStunBuff = false;
    private boolean enableDisarmBuff = false;

    public Cell [][] setAttackAbleCells()
    {

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

    public Spell getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(Spell specialPower) {
        this.specialPower = specialPower;
    }

    public int getMaxAttackRange() {
        return maxAttackRange;
    }

    public void setMaxAttackRange(int maxAttackRange) {
        this.maxAttackRange = maxAttackRange;
    }

    public void setImpactType(ImpactType impactType) {
        this.impactType = impactType;
    }
}
