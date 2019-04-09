import java.util.ArrayList;

public class Minion extends Card
{
    private static ArrayList<Minion> minions = new ArrayList<>();
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

    public static ArrayList<Minion> getMinions() {
        return minions;
    }

    public int getDefaultHP() {
        return defaultHP;
    }

    public int getDefaultAP() {
        return defaultAP;
    }

    public static void setMinions()
    {

    }
}
