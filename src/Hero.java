import java.util.ArrayList;

public class Hero extends Card
{
    private static ArrayList<Hero> heroes = new ArrayList<>();
    private int heroID;
    private int defaultHP;
    private int defaultAP;
    private int HP;
    private int AP;
    private Spell specialPower;
    public int getHeroID()
    {
        return heroID;
    }

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

    public Hero findHero(int heroID, String command)
    {

    }

    public static ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public int getDefaultAP()
    {
        return defaultAP;
    }

    public int getDefaultHP()
    {
        return defaultHP;
    }

    public static void setHeroes()
    {

    }
}
