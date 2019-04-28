package Model;

import java.util.ArrayList;

public class Minion extends NonSpellCards
{
    private static ArrayList<Minion> minions = new ArrayList<>();

    Minion(String name , int price , int MP , int HP , int AP , ImpactType impactType , int rangeOfAttack ,  TimeToActivateSpecialPower timeToActivateSpecialPower  ){
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setDefaultHP(HP);
        this.setDefaultAP(AP);
        this.setImpactType(impactType);
        this.setMaxAttackRange(rangeOfAttack);
        //TimeToActivateSpecialPower
        minions.add(this);
    }

    public static ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public static void setMinions()
    {
        Minion minion1 = new Minion("persianArcher",300,2,6,4, ImpactType.ranged,7,  null);
        Minion minion2 = new Minion("persianSwordsMan",400,2,6,4, ImpactType.melee,0, TimeToActivateSpecialPower.onAttack);
        Minion minion3 = new Minion("persianSpear",500,1,5,3, ImpactType.hybrid,3,null);
        Minion minion4 = new Minion("persianHorseMan",200,4,10,6, ImpactType.melee,0,null);
        Minion minion5 = new Minion("persianAthlete",600 ,9 ,24 ,6 , ImpactType.melee,0,null);
        Minion minion6 = new Minion("persianGeneralissimo",800,7,12,4, ImpactType.melee,0, TimeToActivateSpecialPower.combo);
        Minion minion7 = new Minion("toranianArcher",500,1,3,4, ImpactType.ranged,5,null);
        Minion minion8 = new Minion("toranianSling",600,1,4,2, ImpactType.ranged,7,null);
        Minion minion9 = new Minion("toranianSpear",600,1,4,4, ImpactType.hybrid,3,null);
        Minion minion10 = new Minion("toranianSpy",700,4,6,6, ImpactType.melee,0, TimeToActivateSpecialPower.onAttack);
        Minion minion11 = new Minion("Gorzdar Torani" ,450 , 2 ,3 , 10 , ImpactType.melee , 0 , null );
        Minion minion12 = new Minion("Shahzade Torani" ,800 , 6 ,6 , 10 , ImpactType.melee , 0  , TimeToActivateSpecialPower.combo);
        Minion minion13 = new Minion("Dive Sepid" ,300 , 9 ,14 , 10 , ImpactType.hybrid , 7 , null);
        Minion minion14 = new Minion("Ghoul Sang Andaz" ,300 , 9 ,12 , 12 , ImpactType.ranged , 7 , null);
        Minion minion15 = new Minion("Oghab" ,200 , 2 ,0 , 2 , ImpactType.ranged , 3 , TimeToActivateSpecialPower.passive);
        Minion minion16 = new Minion("Div GorazSavar" ,300 , 6 ,16 , 8 , ImpactType.melee , 0 , null);
        Minion minion17 = new Minion("Ghoul TakCheshm" ,500 , 7 ,12 , 11 , ImpactType.hybrid , 0, TimeToActivateSpecialPower.onDeath);
        Minion minion18 = new Minion("Mar Sammi" ,300 , 4 ,5 , 6 , ImpactType.ranged , 4 ,  TimeToActivateSpecialPower.onAttack);
        Minion minion19 = new Minion("Ezhdehaye Atash Andaz" ,250 , 5 ,9 , 5 , ImpactType.ranged , 4 ,  null);
        Minion minion20 = new Minion("Shir Darande" ,600 , 2 ,1 , 8 , ImpactType.melee , 0 ,TimeToActivateSpecialPower.onAttack);
        Minion minion21 = new Minion("Mar Ghoul Peykar" ,500 , 8 ,14 , 7 , ImpactType.ranged , 5 ,  TimeToActivateSpecialPower.onSpawn);
        Minion minion22 = new Minion("Gorg Sefid" ,400 , 5 ,8 , 2 , ImpactType.melee , 0 ,  TimeToActivateSpecialPower.onAttack);
        Minion minion23 = new Minion("Palang" ,400 , 4 ,6 , 2 , ImpactType.melee , 0 ,TimeToActivateSpecialPower.onAttack);
        Minion minion24 = new Minion("Gorg" ,400 , 3 ,6 , 1 , ImpactType.melee , 0 ,  TimeToActivateSpecialPower.onAttack);
        Minion minion25 = new Minion("JadoGar" ,550 , 4 ,5 , 4 , ImpactType.ranged , 3 ,  TimeToActivateSpecialPower.passive);
        Minion minion26 = new Minion("JadoGar Azam" ,550 , 6 ,6 , 6 , ImpactType.ranged , 5 ,  TimeToActivateSpecialPower.passive);
        Minion minion27 = new Minion("Genn" ,500 , 6 ,10 , 4 , ImpactType.ranged , 4 ,  TimeToActivateSpecialPower.onTurn);
        Minion minion28 = new Minion("Goraz Vahshi" ,500 , 6 ,10 , 14 , ImpactType.melee , 0 ,  TimeToActivateSpecialPower.onDefend);
        Minion minion29 = new Minion("Piran" ,400 , 8 ,20 , 12 , ImpactType.melee , 0 ,  TimeToActivateSpecialPower.onDefend);
        Minion minion30 = new Minion("Giv" ,450 , 4 ,5 , 7 , ImpactType.ranged , 5 ,  TimeToActivateSpecialPower.onDefend);
        Minion minion31 = new Minion("Bahman" ,450 , 8 ,16 , 9 , ImpactType.melee , 0 ,  TimeToActivateSpecialPower.onSpawn);
        Minion minion32 = new Minion("AshkBos" ,400 , 7 ,14 , 8 , ImpactType.melee , 0 ,  TimeToActivateSpecialPower.onDefend);
        Minion minion33 = new Minion("Iraj" ,500 , 4 ,6 , 20 , ImpactType.ranged , 3 ,  null);
        Minion minion34 = new Minion("Ghoul Bozorg" ,600 , 9 ,30 , 8 , ImpactType.hybrid , 2 ,  null);
        Minion minion35 = new Minion("Ghoul Dosar" ,550 , 4 ,10 , 4 , ImpactType.melee , 0 ,  TimeToActivateSpecialPower.onAttack);
        Minion minion36 = new Minion("Nane Sarma" ,500 , 3 ,3 , 4 , ImpactType.ranged , 5 ,  TimeToActivateSpecialPower.onSpawn );
        Minion minion37 = new Minion("Folad Zereh" ,650 , 3 ,1 , 1 , ImpactType.melee, 0 ,  TimeToActivateSpecialPower.passive );
        Minion minion38 = new Minion("Siavash" ,350 , 4 ,8 , 5 , ImpactType.melee, 0 ,  TimeToActivateSpecialPower.onDeath);
        Minion minion39 = new Minion("Shah Ghoul" ,600 , 5 ,10 , 4 , ImpactType.melee, 0 ,  TimeToActivateSpecialPower.combo);
        Minion minion40 = new Minion("Arzhang Div" ,600 , 3 ,6 , 6 , ImpactType.melee, 0 ,  TimeToActivateSpecialPower.combo);
    }

    public void printMinionStats(int counter)
    {
        System.out.println(counter + " : Type : Model.Minion - Name : " + getCardName() + " – Class:" + getTypeOfImpact() + " - AP : " + getDefaultAP() + " - HP : " + getDefaultHP() + " - MP : " + getRequiredMP() + " - Special power : " + /*todo*/ " – Sell Cost : " + getPrice());
    }

    public void printMinionStats()
    {
        System.out.println("Type : Model.Minion - Name : " + getCardName() + " – Class:" + getTypeOfImpact() + " - AP : " + getDefaultAP() + " - HP : " + getDefaultHP() + " - MP : " + getRequiredMP() + " - Special power : " + /*todo*/ " – Sell Cost : " + getPrice());
    }
}
