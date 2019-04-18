import java.util.ArrayList;

public class Minion extends NonSpellCards
{
    private static ArrayList<Minion> minions = new ArrayList<>();

    Minion(String name , int price , int MP , int HP , int AP , ImpactType impactType , int rangeOfAttack , Spell specialPower , TimeToActivateSpecialPower timeToActivateSpecialPower ){
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setDefaultHP(HP);
        this.setDefaultAP(AP);
        this.setImpactType(impactType);
        this.setMaxAttackRange(rangeOfAttack);
        this.setSpecialPower(specialPower);
        //TimeToActivateSpecialPower
        minions.add(this);
    }

    public static ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public static void setMinions()
    {
        Minion minion11 = new Minion("Gorzdar Torani" ,450 , 2 ,3 , 10 , ImpactType.melee , 0 , null , null);
        Minion minion12 = new Minion("Shahzade Torani" ,800 , 6 ,6 , 10 , ImpactType.melee , 0 , null /*todo*/ , TimeToActivateSpecialPower.combo);
        Minion minion13 = new Minion("Dive Sepid" ,300 , 9 ,14 , 10 , ImpactType.hybrid , 7 , null , null);
        Minion minion14 = new Minion("Ghoul Sang Andaz" ,300 , 9 ,12 , 12 , ImpactType.ranged , 7 , null , null);
        Minion minion15 = new Minion("Oghab" ,200 , 2 ,0 , 2 , ImpactType.ranged , 3 , /*todo*/ , TimeToActivateSpecialPower.passive);


    }

    public void printMinionStats(int counter)
    {
        System.out.println(counter + " : Type : Minion - Name : " + getCardName() + " – Class:" + getTypeOfImpact() + " - AP : " + getAP() + " - HP : " + getHP() + " - MP : " + getRequiredMP() + " - Special power : " + /*todo*/ " – Sell Cost : " + getPrice());
    }
}
