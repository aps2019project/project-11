import java.util.ArrayList;

public class Hero extends NonSpellCards
{
    private static ArrayList<Hero> heroes = new ArrayList<>();
    private int heroID;
    private int MP;
    private int turnsRemainingToEndCoolDown;
    private int coolDown;

    public int getHeroID()
    {
        return heroID;
    }

    public Hero findHero(int heroID, String command)
    {

    }

    public static ArrayList<Hero> getHeroes()
    {
        return heroes;
    }

    public static void setHeroes()
    {

    }
}
