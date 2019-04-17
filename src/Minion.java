import java.util.ArrayList;

public class Minion extends NonSpellCards
{
    private static ArrayList<Minion> minions = new ArrayList<>();

    public static ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public static void setMinions()
    {

    }

    public void printMinionStats(int counter)
    {
        System.out.println(counter + " : Type : Minion - Name : " + getCardName() + " – Class:" + getTypeOfImpact() + " - AP : " + getAP() + " - HP : " + getHP() + " - MP : " + getRequiredMP() + " - Special power : " + /*todo*/ " – Sell Cost : " + getPrice());
    }
}
