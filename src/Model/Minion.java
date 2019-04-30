package Model;

import java.util.ArrayList;

public class Minion extends NonSpellCards
{
    private static ArrayList<Minion> minions = new ArrayList<>();

    Minion(String name, int price, int MP, int HP, int AP, SpecialPower specialPower)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setDefaultHP(HP);
        this.setDefaultAP(AP);
        this.setSpecialPower(specialPower);
        minions.add(this);
    }

    public static ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public static void setMinions()
    {
        Spell minionSpell2 = new Spell("persianSwordsMan",400,2,"stun in current turn");
        minionSpell2.getSpellEffect().addSpellChange(new SpellChange(0,false,false,TimeToActivateSpecialPower.onAttack,0,0,0,true,false,false,false,false,false,false,false,false,true));
        minionSpell2.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,0,0,0,0,ImpactType.melee,0,null,false,false,false));

        Spell minionSpell5 = new Spell("persianAthlete",600,9,"confusing");
        //todo

        Spell minionSpell6 = new Spell("persianGeneralissimo",800,7,"combo");
        minionSpell6.getSpellEffect().addSpellChange(new SpellChange(0,false,false,TimeToActivateSpecialPower.combo,0,0,0,false,false,false,false,false,false,false,false,true,false));
        minionSpell6.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,0,0,0,0,ImpactType.melee,4,null,false,false,false));

        Spell minionSpell10 = new Spell("toranianSpy",700,4,"disarm and poison");
        minionSpell10.getSpellEffect().addSpellChange(new SpellChange(4,false,false,TimeToActivateSpecialPower.onAttack,0,-1,0,false,true,false,false,false,false,false,false,false,false));
        minionSpell10.getSpellEffect().addTarget(new Target(0,0,false,false,1,0,0,0,0,0,ImpactType.melee,0,null,false,false,false));

        Spell minionSpell15 = new Spell(null , 0 , 0 , "10 power buff" );
        minionSpell15.getSpellEffect().addSpellChange(new SpellChange(0 , true , false ,TimeToActivateSpecialPower.passive ,0 ,10 , 0 , false , false , false , false, false , false , false , false));
        minionSpell15.getSpellEffect().addTarget(new Target(1 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged ,0 , null, false,false,false));

        Spell minionSpell17 = new Spell(null , 0 , 0 , "make 2 damage on around minions" );
        minionSpell17.getSpellEffect().addSpellChange(new SpellChange(0 , false , false ,TimeToActivateSpecialPower.onDeath ,0 ,-2 , 0 , false , false , false ,false, false , false , false ,false));
        minionSpell17.getSpellEffect().addTarget(new Target(0 , 8 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.hybrid ,0 , null, false,false,false));

        Spell minionSpell18 = new Spell(null , 0 , 0 , "made toxic enemy force 3 turn" );
        minionSpell18.getSpellEffect().addSpellChange(new SpellChange(3 , false , false ,TimeToActivateSpecialPower.onAttack ,0 ,-1 , 0 , false , false , false ,false, false , false , false , false));
        minionSpell18.getSpellEffect().addTarget(new Target(0 , 0 , false ,false , 1 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged ,0 , null,false,false,false));

        Spell minionSpell20 = new Spell(null , 0 , 0 , "holy buff" );
        minionSpell20.getSpellEffect().addSpellChange(new SpellChange(0, true , false ,TimeToActivateSpecialPower.onAttack ,0 ,0 , 0 , false , false , true ,false, false , false , false , false));
        minionSpell20.getSpellEffect().addTarget(new Target(1 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee ,0 , null, false,false,false));

        Spell minionSpell21 = new Spell(null , 0 , 0 , "minions around the card receive one more damage when receive damage" );
        minionSpell21.getSpellEffect().addSpellChange(new SpellChange(0, false , false ,TimeToActivateSpecialPower.onSpawn ,0 ,0 , 0 , false , false , false , true , false , false , false, false));
        minionSpell21.getSpellEffect().addTarget(new Target(0 , 8 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee ,0 , null , false,false,false));

        /*Spell minionSpell22 = new Spell(null , 0 , 0 , "" );
        minionSpell22.getSpellEffect().addSpellChange(new SpellChange(0, true , false ,TimeToActivateSpecialPower.onAttack ,0 ,0 , 0 , false , false , true , false , false , false));
        minionSpell22.getSpellEffect().addTarget(new Target(1 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee ,0 ));*/

        Spell minionSpell23 = new Spell(null , 0 , 0 , "if attack to opponent minion , next minion receive 8 damage" );
        minionSpell23.getSpellEffect().addSpellChange(new SpellChange(0, false , false ,TimeToActivateSpecialPower.onAttack ,0 ,-8 , 0 , false , false , false , false, false , false , false , false));
        minionSpell23.getSpellEffect().addTarget(new Target(0 , 1 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee,0 , null , false,false,false));

        Spell minionSpell24 = new Spell(null , 0 , 0 , "if attack to opponent minion , next minion receive 6 damage" );
        minionSpell24.getSpellEffect().addSpellChange(new SpellChange(0, false , false ,TimeToActivateSpecialPower.onAttack ,0 ,-6 , 0 , false , false , false , false, false , false , false , false));
        minionSpell24.getSpellEffect().addTarget(new Target(0 , 1 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee,0 , null ,false,false,false));

        Spell minionSpell25 = new Spell(null , 0 , 0 , "give 2 power buff and 1 poison buff to around minion cards in one turn" );
        minionSpell25.getSpellEffect().addSpellChange(new SpellChange(1, false , false ,TimeToActivateSpecialPower.passive ,2 ,-1 , 0 , false , false , false , false, false , false , false , false));
        minionSpell25.getSpellEffect().addTarget(new Target(0 , 8 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged ,0 , null , false,false,false));
        minionSpell25.getSpellEffect().addTarget(new Target(8 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged ,0 , null , false,false,false));

        Spell minionSpell26 = new Spell(null , 0 , 0 , "give 2 power buff and 1 poison buff to around minion cards in continuously" );
        minionSpell26.getSpellEffect().addSpellChange(new SpellChange(0, true , true ,TimeToActivateSpecialPower.passive ,2 , 0, 0 , false , false , true , false, false , false , false , false));
        minionSpell26.getSpellEffect().addTarget(new Target(0 , 8 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged ,0 , null , false,false,false));
        minionSpell26.getSpellEffect().addTarget(new Target(8 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged ,0 , null , false,false,false));

        Spell minionSpell27 = new Spell(null , 0 , 0 , "increase 1 AP to all own minions" );
        minionSpell27.getSpellEffect().addSpellChange(new SpellChange(0, true , true ,TimeToActivateSpecialPower.onTurn ,1 ,0, 0 , false , false , false , false, false , false , false , false));
        minionSpell27.getSpellEffect().addTarget(new Target(0 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged,0 , null , true,false,false));

        Spell minionSpell31 = new Spell(null , 0 , 0 , "random damage one opponent 16 damage" );
        minionSpell31.getSpellEffect().addSpellChange(new SpellChange(0, false , false ,TimeToActivateSpecialPower.onSpawn ,0 ,-16, 0 , false , false , false , false, false , false , false , false));
        minionSpell31.getSpellEffect().addTarget(new Target(0 , 1 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee,0 , null , false,false,false));

        Spell minionSpell35 = new Spell(null , 0 , 0 , "delete all positive buff" );
        minionSpell35.getSpellEffect().addSpellChange(new SpellChange(0, false , false ,TimeToActivateSpecialPower.onAttack ,0 ,0, 0 , false , false , false , false, false , false , false , true));
        minionSpell35.getSpellEffect().addTarget(new Target(0, 0 , false ,false , 1 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee,0 , null , false,false,false));

        Spell minionSpell36 = new Spell(null , 0 , 0 , "around minions stun" );
        minionSpell36.getSpellEffect().addSpellChange(new SpellChange(0, false , false ,TimeToActivateSpecialPower.onSpawn ,0 ,0, 0 , true , false , false , false, false , false , false , false));
        minionSpell36.getSpellEffect().addTarget(new Target(0, 8 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged,0 , null , false,false,false));

        Spell minionSpell37 = new Spell(null , 0 , 0 , "12 holy buff" );
        minionSpell37.getSpellEffect().addSpellChange(new SpellChange(0, true , false ,TimeToActivateSpecialPower.passive ,0 ,0, 0 , true , false , true , false, false , false , false , false));
        minionSpell37.getSpellEffect().addTarget(new Target(1, 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee,0 , null , false,false,false));

        Spell minionSpell38 = new Spell(null , 0 , 0 , "on death make 6 damage on opponent hero" );
        minionSpell38.getSpellEffect().addSpellChange(new SpellChange(0, false , false ,TimeToActivateSpecialPower.onDeath,0 ,-6, 0 , false , false , true , false, false , false , false , false));
        minionSpell38.getSpellEffect().addTarget(new Target(0, 0 , false ,true , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee,0 , null , false,false,false));




      /*
        new Minion("Gorzdar Torani" ,450 , 2 ,3 , 10 , null );
        new Minion("Shahzade Torani" ,800 , 6 ,6 , 10 , null);
        new Minion("Dive Sepid" ,300 , 9 ,14 , 10 , null);
        new Minion("Ghoul Sang Andaz" ,300 , 9 ,12 , 12 , null);
        new Minion("Oghab" ,200 , 2 ,0 , 2, minionSpell15);
        new Minion("Div GorazSavar" ,300 , 6 ,16 , 8 ,null);
        new Minion("Ghoul TakCheshm" ,500 , 7 ,12 , 11 , minionSpell17);
        new Minion("Mar Sammi" ,300 , 4 ,5 , 6 , minionSpell18);
        new Minion("Ezhdehaye Atash Andaz" ,250 , 5 ,9 , 5 , null);
        new Minion("Shir Darande" ,600 , 2 ,1 , 8 , minionSpell20);
        new Minion("Mar Ghoul Peykar" ,500 , 8 ,14 , 7 , minionSpell21);
        //new Minion("Gorg Sefid" ,400 , 5 ,8 , 2 , minionSpell22);
        new Minion("Palang" ,400 , 4 ,6 , 2 , minionSpell23);
        new Minion("Gorg" ,400 , 3 ,6 , 1 , minionSpell24);
        new Minion("JadoGar" ,550 , 4 ,5 , 4 , minionSpell25);
        new Minion("JadoGar Azam" ,550 , 6 ,6 , 6 , minionSpell26);
        new Minion("Genn" ,500 , 6 ,10 , 4 , minionSpell27);
        new Minion("Goraz Vahshi" ,500 , 6 ,10 , 14 , null); //28
        new Minion("Piran" ,400 , 8 ,20 , 12 , null);
        new Minion("Giv" ,450 , 4 ,5 , 7 , null); //30
        new Minion("Bahman" ,450 , 8 ,16 , 9 , minionSpell31);
        new Minion("AshkBos" ,400 , 7 ,14 , 8 , null); //32
        new Minion("Iraj" ,500 , 4 ,6 , 20 , null);
        new Minion("Ghoul Bozorg" ,600 , 9 ,30 , 8 ,   null); //34
        new Minion("Ghoul Dosar" ,550 , 4 ,10 , 4 , minionSpell35);
        new Minion("Nane Sarma" ,500 , 3 ,3 , 4 , minionSpell36); //36
        new Minion("Folad Zereh" ,650 , 3 ,1 , 1 , minionSpell37);
        new Minion("Siavash" ,350 , 4 ,8 , 5 , minionSpell38); //38
        new Minion("Shah Ghoul" ,600 , 5 ,10 , 4 , null);
        new Minion("Arzhang Div" ,600 , 3 ,6 , 6 , null);  //40*/



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
