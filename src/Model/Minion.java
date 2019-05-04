package Model;

import java.util.ArrayList;

public class Minion extends NonSpellCards
{
    private static ArrayList<Minion> minions = new ArrayList<>();
    private boolean ableToCombo;

    Minion(String name, int price, int MP, int HP, int AP, SpecialPower specialPower, boolean ableToCombo, ImpactType impactType, int rangeOfAttack)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setDefaultHP(HP);
        this.setDefaultAP(AP);
        this.setSpecialPower(specialPower);
        this.ableToCombo = ableToCombo;
        this.setImpactType(impactType);
        this.setRangeOfAttack(rangeOfAttack);
        minions.add(this);
        Shop.getInstance().addCardToShop(this);
    }

    public static ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public static void setMinions()
    {
        SpecialPower minionSpell2 = new SpecialPower("stun in current turn");
        minionSpell2.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onAttack, 0, 0, 0, true, false, false, false, false, false, false, false, false, true));
        minionSpell2.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, 0, null, false, false, false));

        /*SpecialPower minionSpell5 = new SpecialPower("five impact plus number of times that you impact in last turns");
        minionSpell5.getSpellEffect().addSpellChange(new SpellChange(0,false,false,TimeToActivateSpecialPower.onAttack,0,,0,false,false,false,false,false,false,false,false,false,false));
        minionSpell5.getSpellEffect().addTarget(new Target(0,0,false,false,1,0,0,0,0,0,0,null,false,false,false));*/

        SpecialPower minionSpell10 = new SpecialPower("disarm and poison");
        minionSpell10.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onAttack, 0, 0, 0, false, true, false, false, false, false, false, false, false, false));
        minionSpell10.getSpellEffect().addSpellChange(new SpellChange(4, false, false, TimeToActivateSpecialPower.onAttack, 0, -1, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell10.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, 0, null, false, false, false));

        SpecialPower minionSpell15 = new SpecialPower("10 power buff");
        minionSpell15.getSpellEffect().addSpellChange(new SpellChange(0, true, false, TimeToActivateSpecialPower.passive, 0, 10, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell15.getSpellEffect().addTarget(new Target(1, 0, false, false, 0, 0, 0, 0, 0, 0, 3, null, false, false, false));

        SpecialPower minionSpell17 = new SpecialPower("make 2 damage on around minions");
        minionSpell17.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onDeath, 0, -2, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell17.getSpellEffect().addTarget(new Target(0, 8, false, false, 0, 0, 0, 0, 0, 0, 3, null, false, false, false));

        SpecialPower minionSpell18 = new SpecialPower("made toxic enemy force 3 turn");

        minionSpell18.getSpellEffect().addSpellChange(new SpellChange(3, false, false, TimeToActivateSpecialPower.onAttack, 0, -1, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell18.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, 4, null, false, false, false));

        SpecialPower minionSpell20 = new SpecialPower("holy buff");
        minionSpell20.getSpellEffect().addSpellChange(new SpellChange(0, true, false, TimeToActivateSpecialPower.onAttack, 0, 0, 0, false, false, true, false, false, false, false, false, false, false));
        minionSpell20.getSpellEffect().addTarget(new Target(1, 0, false, false, 0, 0, 0, 0, 0, 0, 0, null, false, false, false));

        SpecialPower minionSpell21 = new SpecialPower("minions around the card receive one more damage when receive damage");
        minionSpell21.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onSpawn, 0, 0, 0, false, false, false, true, false, false, false, false, false, false));
        minionSpell21.getSpellEffect().addTarget(new Target(0, 8, false, false, 0, 0, 0, 0, 0, 0, 5, null, false, false, false));

        /*Spell minionSpell22 = new Spell(null , 0 , 0 , "" );
        minionSpell22.getSpellEffect().addSpellChange(new SpellChange(0, true , false ,TimeToActivateSpecialPower.onAttack ,0 ,0 , 0 , false , false , true , false , false , false));
        minionSpell22.getSpellEffect().addTarget(new Target(1 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee ,0 ));*/

        SpecialPower minionSpell23 = new SpecialPower("if attack to opponent minion , next minion receive 8 damage");
        minionSpell23.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onAttack, 0, -8, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell23.getSpellEffect().addTarget(new Target(0, 1, false, false, 0, 0, 0, 0, 0, 0, 0, null, false, false, false));

        SpecialPower minionSpell24 = new SpecialPower("if attack to opponent minion , next minion receive 6 damage");
        minionSpell24.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onAttack, 0, -6, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell24.getSpellEffect().addTarget(new Target(0, 1, false, false, 0, 0, 0, 0, 0, 0, 0, null, false, false, false));

        SpecialPower minionSpell25 = new SpecialPower("give 2 power buff and 1 poison buff to around minion cards in one turn");
        minionSpell25.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.passive, 2, -1, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell25.getSpellEffect().addTarget(new Target(0, 8, false, false, 0, 0, 0, 0, 0, 0, 3, null, false, false, false));
        minionSpell25.getSpellEffect().addTarget(new Target(8, 0, false, false, 0, 0, 0, 0, 0, 0, 3, null, false, false, false));

        SpecialPower minionSpell26 = new SpecialPower("give 2 power buff and 1 poison buff to around minion cards in continuously");
        minionSpell26.getSpellEffect().addSpellChange(new SpellChange(0, true, true, TimeToActivateSpecialPower.passive, 2, 0, 0, false, false, true, false, false, false, false, false, false, false));
        minionSpell26.getSpellEffect().addTarget(new Target(0, 8, false, false, 0, 0, 0, 0, 0, 0, 5, null, false, false, false));
        minionSpell26.getSpellEffect().addTarget(new Target(8, 0, false, false, 0, 0, 0, 0, 0, 0, 5, null, false, false, false));

        SpecialPower minionSpell27 = new SpecialPower("increase 1 AP to all own minions");
        minionSpell27.getSpellEffect().addSpellChange(new SpellChange(0, true, true, TimeToActivateSpecialPower.onTurn, 1, 0, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell27.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, 0, 0, 0, 0, 4, null, true, false, false));

        SpecialPower minionSpell31 = new SpecialPower("random damage one opponent 16 damage");
        minionSpell31.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onSpawn, 0, -16, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell31.getSpellEffect().addTarget(new Target(0, 1, false, false, 0, 0, 0, 0, 0, 0, 0, null, false, false, false));

        SpecialPower minionSpell35 = new SpecialPower("delete all positive buff");
        minionSpell35.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onAttack, 0, 0, 0, false, false, false, false, false, false, false, true, false, false));
        minionSpell35.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, 0, null, false, false, false));

        SpecialPower minionSpell36 = new SpecialPower("around minions stun");
        minionSpell36.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onSpawn, 0, 0, 0, true, false, false, false, false, false, false, false, false, false));
        minionSpell36.getSpellEffect().addTarget(new Target(0, 8, false, false, 0, 0, 0, 0, 0, 0, 5, null, false, false, false));

        SpecialPower minionSpell37 = new SpecialPower("12 holy buff");
        minionSpell37.getSpellEffect().addSpellChange(new SpellChange(0, true, false, TimeToActivateSpecialPower.passive, 0, 0, 0, true, false, true, false, false, false, false, false, false, false));
        minionSpell37.getSpellEffect().addTarget(new Target(1, 0, false, false, 0, 0, 0, 0, 0, 0, 0, null, false, false, false));

        SpecialPower minionSpell38 = new SpecialPower("on death make 6 damage on opponent hero");
        minionSpell38.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onDeath, 0, -6, 0, false, false, true, false, false, false, false, false, false, false));
        minionSpell38.getSpellEffect().addTarget(new Target(0, 0, false, true, 0, 0, 0, 0, 0, 0, 0, null, false, false, false));

        new Minion("kamandar Fars" ,300, 2, 6, 4, null, false,ImpactType.ranged, 7);
        new Minion("shamsirzan Fars", 400, 2, 6, 4 ,minionSpell2,false,ImpactType.melee, 0 );
        new Minion("neizedar Fars", 500, 1, 5, 3, null,false, ImpactType.hybrid, 3);
        new Minion("asbsavar Fars", 200, 4, 10, 6, null,false,ImpactType.melee, 0);
        //new Minion("pahlavan Fars", 600, 9, 24, 6, minionSpell5,false,ImpactType.melee, 0);
        new Minion("sepahsalar Fars", 800, 7, 12, 4, null,true,ImpactType.melee ,0);
        new Minion("kamandar Torani", 500, 1, 3, 4,null,false,ImpactType.ranged, 5 );
        new Minion("gholabsangdar Torani", 600, 1, 4, 2, null,false,ImpactType.ranged, 7);
        new Minion("neizedar Torani", 600, 1, 4, 4, null,false,ImpactType.hybrid, 3);
        new Minion("jasos Torani", 700, 4, 6, 6, minionSpell10,false, ImpactType.melee, 0);
        new Minion("Gorzdar Torani", 450, 2, 3, 10, null, false, ImpactType.melee, 0);
        new Minion("Shahzade Torani", 800, 6, 6, 10, null, true, ImpactType.melee, 0);
        new Minion("Dive siah", 300, 9, 14, 10, null, false, ImpactType.hybrid, 7);
        new Minion("Ghoul Sang Andaz", 300, 9, 12, 12, null, false, ImpactType.ranged, 7);
        new Minion("Oghab", 200, 2, 0, 2, minionSpell15, false, ImpactType.ranged, 3);
        new Minion("Div GorazSavar", 300, 6, 16, 8, null, false, ImpactType.melee, 0);
        new Minion("Ghoul TakCheshm", 500, 7, 12, 11, minionSpell17, false, ImpactType.hybrid, 3);
        new Minion("Mar Sammi", 300, 4, 5, 6, minionSpell18, false, ImpactType.ranged, 4);
        new Minion("Ezhdehaye Atash Andaz", 250, 5, 9, 5, null, false, ImpactType.ranged, 4);
        new Minion("Shir Darande", 600, 2, 1, 8, minionSpell20, false, ImpactType.melee, 0);
        new Minion("Mar Ghoul Peykar", 500, 8, 14, 7, minionSpell21, false, ImpactType.ranged, 5);
        //new Minion("Gorg Sefid" ,400 , 5 ,8 , 2 , minionSpell22);
        new Minion("Palang", 400, 4, 6, 2, minionSpell23, false, ImpactType.melee, 0);
        new Minion("Gorg", 400, 3, 6, 1, minionSpell24, false, ImpactType.melee, 0);
        new Minion("JadoGar", 550, 4, 5, 4, minionSpell25, false, ImpactType.ranged, 3);
        new Minion("JadoGar Azam", 550, 6, 6, 6, minionSpell26, false, ImpactType.ranged, 5);
        new Minion("Genn", 500, 6, 10, 4, minionSpell27, false, ImpactType.ranged, 4);
        new Minion("Goraz Vahshi", 500, 6, 10, 14, null, false, ImpactType.melee, 0); //28
        new Minion("Piran", 400, 8, 20, 12, null, false, ImpactType.melee, 0);
        new Minion("Giv", 450, 4, 5, 7, null, false, ImpactType.ranged, 5); //30
        new Minion("Bahman", 450, 8, 16, 9, minionSpell31, false, ImpactType.melee, 0);
        new Minion("AshkBos", 400, 7, 14, 8, null, false, ImpactType.melee, 0); //32
        new Minion("Iraj", 500, 4, 6, 20, null, false, ImpactType.ranged, 3);
        new Minion("Ghoul Bozorg", 600, 9, 30, 8, null, false, ImpactType.hybrid, 2); //34
        new Minion("Ghoul Dosar", 550, 4, 10, 4, minionSpell35, false, ImpactType.melee, 0);
        new Minion("Nane Sarma", 500, 3, 3, 4, minionSpell36, false, ImpactType.ranged, 5); //36
        new Minion("Folad Zereh", 650, 3, 1, 1, minionSpell37, false, ImpactType.melee, 0);
        new Minion("Siavash", 350, 4, 8, 5, minionSpell38, false, ImpactType.melee, 0); //38
        new Minion("Shah Ghoul", 600, 5, 10, 4, null, true, ImpactType.melee, 0);
        new Minion("Arzhang Div", 600, 3, 6, 6, null, true, ImpactType.melee, 0);  //40

    }

    public void printMinionStats(int counter)
    {
        if (getSpecialPower() != null)
        {
            System.out.println(counter + " : Type : Minion - Name : " + getCardName() + " – Class : " + getImpactType() + " - AP : " + getDefaultAP() + " - HP : " + getDefaultHP() + " - MP : " + getRequiredMP() + " - Special power : " + getSpecialPower().getDescriptionTypeOfSpecialPower() + " – Sell Cost : " + getPrice());
        }
        else
        {
            System.out.println(counter + " : Type : Minion - Name : " + getCardName() + " – Class : " + getImpactType() + " - AP : " + getDefaultAP() + " - HP : " + getDefaultHP() + " - MP : " + getRequiredMP() + " – Sell Cost : " + getPrice());
        }
    }

    public void printMinionStats()
    {
        System.out.println("Type : Minion - Name : " + getCardName() + " – Class:" + getImpactType() + " - AP : " + getDefaultAP() + " - HP : " + getDefaultHP() + " - MP : " + getRequiredMP() + " - Special power : " + getSpecialPower().getDescriptionTypeOfSpecialPower() + " – Sell Cost : " + getPrice());
    }

    public boolean isAbleToCombo()
    {
        return ableToCombo;
    }

    public void setAbleToCombo(boolean ableToCombo)
    {
        this.ableToCombo = ableToCombo;
    }
}