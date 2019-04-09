public abstract class NonSpellCards extends Card
{
    enum typesOfImpact
    {
        ranged, melee, hybrid
    }
    private int defaultHP;
    private int defaultAP;
    private int HP;
    private int AP;
    private Spell specialPower;
    private typesOfImpact typeOfImpact;
    private int maxAttackRange;
    private boolean enabledHolyBuff = false;
    private boolean enableStunBuff = false;
    private boolean enableDisarmBuff = false;

    public int [][] setAttackableCells()
    {

    }

    public static void setNonSpellCards()
    {
        Hero.setHeroes();
        Minion.setMinions();
    }

}
