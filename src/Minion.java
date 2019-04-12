import java.util.ArrayList;

public class Minion extends NonSpellCards
{
    enum typesTimeToActivateSpecialPower
    {
        passive, onAttack, onSpawn, onDeath, combo, onDefend, onRespawn
    }

    private static ArrayList<Minion> minions = new ArrayList<>();
    private typesTimeToActivateSpecialPower timeToActivateSpeciallPower;

    public static ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public static void setMinions()
    {

    }

    public void printMinionStats(int counter)
    {
        System.out.println(counter + " : Type : Minion - Name : " + getCardName() + " – Class:" + getTypeOfImpact() + " - AP : " + getAP() + " - HP : " + getHP() + " - MP : " + getRequiredMana() + " - Special power : " + /*todo*/ " – Sell Cost : " + getPrice());
    }
}
