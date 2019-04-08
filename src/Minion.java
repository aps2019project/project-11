public class Minion extends Card
{
    private int defaultHP;
    private int defaultAP;
    private int HP;
    private int AP;
    private Spell specialPower;

    public int getAP()
    {
        return AP;
    }

    public Spell getSpecialPower()
    {
        return specialPower;
    }

    public int getHP()
    {
        return HP;
    }

    public int getDefaultHP() {
        return defaultHP;
    }

    public int getDefaultAP() {
        return defaultAP;
    }
}
