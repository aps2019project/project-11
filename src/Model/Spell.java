package Model;

import java.util.ArrayList;

public class Spell extends Card
{
    private static ArrayList<Spell> spells = new ArrayList<>();
    private String descriptionTypeOfSpell;
    private SpellEffect spellEffect = new SpellEffect();

    public Spell(String name, int price, int MP, String descriptionTypeOfSpell)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setDescriptionTypeOfSpell(descriptionTypeOfSpell);
        spells.add(this);
    }

    public static void setSpells()
    {
        Spell spellOne = new Spell("totalDisarm",1000,0, "disarm");
        spellOne.getSpellEffect().addSpellChange( new SpellChange(0 , false , true , null , 0 , 0 , 0 , false , true , false , false ,false , false));
        spellOne.getSpellEffect().addTarget(new Target(0 , 0 ,false , false , 1 , 0 , 0 , 0 , 0 , 0 , null , 0 , null));
        Spell spellTwo = new Spell("areaDispel",1500,2, "removingAllBadAndGoodBuffs" );



        Spell minionSpelll5 = new Spell(null , 0 , 0 , "10 power buff" );
        minionSpelll5.getSpellEffect().addSpellChange(new SpellChange(0 , true , false ,TimeToActivateSpecialPower.passive ,0 ,10 , 0 , false , false , false , false , false , false));
        minionSpelll5.getSpellEffect().addTarget(new Target(1 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged ,0 , null));

        Spell minionSpelll7 = new Spell(null , 0 , 0 , "make 2 damage on around minions" );
        minionSpelll7.getSpellEffect().addSpellChange(new SpellChange(0 , false , false ,TimeToActivateSpecialPower.onDeath ,0 ,-2 , 0 , false , false , false , false , false , false));
        minionSpelll7.getSpellEffect().addTarget(new Target(0 , 8 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.hybrid ,0 , null));

        Spell minionSpell8 = new Spell(null , 0 , 0 , "made toxic enemy force 3 turn" );
        minionSpell8.getSpellEffect().addSpellChange(new SpellChange(3 , false , false ,TimeToActivateSpecialPower.onAttack ,0 ,-1 , 0 , false , false , false , false , false , false));
        minionSpell8.getSpellEffect().addTarget(new Target(0 , 0 , false ,false , 1 , 0 ,0 , 0 , 0 , 0 ,ImpactType.ranged ,0 , null));

        Spell minionSpell20 = new Spell(null , 0 , 0 , "holy buff" );
        minionSpell20.getSpellEffect().addSpellChange(new SpellChange(0, true , false ,TimeToActivateSpecialPower.onAttack ,0 ,0 , 0 , false , false , true , false , false , false));
        minionSpell20.getSpellEffect().addTarget(new Target(1 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee ,0 , null));

        /*Spell minionSpell21 = new Spell(null , 0 , 0 , "" );
        minionSpell21.getSpellEffect().addSpellChange(new SpellChange(0, true , false ,TimeToActivateSpecialPower.onAttack ,0 ,0 , 0 , false , false , true , false , false , false));
        minionSpell21.getSpellEffect().addTarget(new Target(1 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee ,0 ));*/

        /*Spell minionSpell22 = new Spell(null , 0 , 0 , "" );
        minionSpell22.getSpellEffect().addSpellChange(new SpellChange(0, true , false ,TimeToActivateSpecialPower.onAttack ,0 ,0 , 0 , false , false , true , false , false , false));
        minionSpell22.getSpellEffect().addTarget(new Target(1 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee ,0 ));*/

        /*Spell minionSpell23 = new Spell(null , 0 , 0 , "holy buff" );
        minionSpell23.getSpellEffect().addSpellChange(new SpellChange(0, true , false ,TimeToActivateSpecialPower.onAttack ,0 ,0 , 0 , false , false , true , false , false , false));
        minionSpell23.getSpellEffect().addTarget(new Target(1 , 0 , false ,false , 0 , 0 ,0 , 0 , 0 , 0 ,ImpactType.melee ,0 ));*/


    }








        new Spell("totalDisarm",1000,0,TargetCommunities.opponentForce,Effect.disarmBuff,TypeOfMultiplicity.spellUntilEndGame,"disarm");
        new Spell("areaDispel",1500,2,TargetCommunities.twoInTwoSquare,null/*todo*/,TypeOfMultiplicity.spellUnit/*todo*/,"removingBadAndGoodBuffs");
        new Spell("empower",250,1,TargetCommunities.ownForce,Effect.powerBuff,TypeOfMultiplicity.spellUnit,2,"adding2ToAP");
        new Spell("fireball",400,1,TargetCommunities.opponentForce,Effect.holyBuff,TypeOfMultiplicity.spellUnit,4,"impact4Time");
        new Spell("godStrength",450,2,TargetCommunities.ownHero,Effect.powerBuff,TypeOfMultiplicity.spellUnit,4,"adding4ToHeroAP");
        new Spell("hellFire",600,3,TargetCommunities.twoInTwoSquare,Effect.fieryCell,TypeOfMultiplicity.spellTurn,2,"hotHouseFor2turn");
        new Spell("lightingBolt",1250,2,TargetCommunities.opponentHero,Effect.holyBuff,TypeOfMultiplicity.spellUnit,8,"impactFor8TimeToEnemyHero");
        new Spell("poisonLake",900,5,TargetCommunities.threeInThreeSquare,Effect.toxicCell,TypeOfMultiplicity.spellTurn,1,"poisonHouseFor1Turn");
        new Spell("madness",650,0,TargetCommunities.ownForce,Effect.powerBuff,TypeOfMultiplicity.spellTurnAndUnit,43,"adding4ToAPButDisarmFor3Turn");
        new Spell("allDisarm",2000,9,TargetCommunities.allOpponentForces,Effect.disarmBuff,TypeOfMultiplicity.spellUnit,1,"disarmFor1Turn");
        new Spell("allPoison",1500,8,TargetCommunities.allOpponentForces,Effect.poisonBuff,TypeOfMultiplicity.spellUnit,4,"poisonAllForcesFor4Turn");
        new Spell("dispel",2100,0,TargetCommunities.anyForce,null/*todo*/,TypeOfMultiplicity.spellTurnAndUnit,63,"removingBadAndGoodBuffs");
        new Spell("healthWithProfit",2250,0,TargetCommunities.ownForce,null/*todo*/,TypeOfMultiplicity.spellUnit,6,"weaknessBuffDecreasing6HPHaving2holyBuffFor3Turn");
        new Spell("powerUp",2500,2,TargetCommunities.ownForce,Effect.powerBuff,TypeOfMultiplicity.spellUnit,6,"powerBuffIncrease6AP");
        new Spell("allPower",2000,4,TargetCommunities.allOwnForces,Effect.powerBuff,TypeOfMultiplicity.spellUnit,2,"powerBuffWith2APConstant");
        new Spell("allAttack",1500,4,TargetCommunities.allOpponentForesInColumn,Effect.holyBuff,TypeOfMultiplicity.spellUnit,6,"impactToAllEnemy6impact");
        new Spell("weakening",1000,1,TargetCommunities.opponentMinion,Effect.weaknessBuff,TypeOfMultiplicity.spellUnit,4,"weaknessWithdecreasing4APInOneForse");
        new Spell("sacrifice",1600,2,TargetCommunities.ownMinion,null/*todo*/,TypeOfMultiplicity.spellUnit/*todo*/,"weaknessWithdecreasing6HPAndPowerBuffWithIncreasing8APInOneForse");
        new Spell("kingGuard",1750,9,TargetCommunities.opponentMinionAroundOwnHero,null/*todo*/,TypeOfMultiplicity.spellUnit/*todo*/,"killingEnemy");
        new Spell("shock",1200,1,TargetCommunities.opponentForce,Effect.stunBuff ,TypeOfMultiplicity.spellUnit,2,"stunFor2Turn");


    public void effectHolyBuff(Card card)
    {

    }

    public void effectPowerBuff(Card card,boolean isHP)
    {

    }

    public void effectPoisonBuff(Card card)
    {

    }

    public void effectWeaknessBuff(Card card,boolean isHP)
    {

    }

    public void effectStunBuff(Card card)
    {

    }

    public void effectDisarmBuff(Card card)
    {

    }

    public void effectToxicCell(int x, int y)
    {

    }

    public void effectFieryCell(int x, int y)
    {

    }

    public void effectHolyCell(int x, int y)
    {

    }
    public void effectAddingMP(Card card)
    {

    }
    public String getDescriptionTypeOfSpell() {
        return descriptionTypeOfSpell;
    }

    public void setDescriptionTypeOfSpell(String descriptionTypeOfSpell) {
        this.descriptionTypeOfSpell = descriptionTypeOfSpell;
    }
    public SpellEffect getSpellEffect() {
        return spellEffect;
    }


    public static ArrayList<Spell> getSpells()
    {
        return spells;
    }

    public void printSpellCardStats(int counter)
    {
        System.out.println(counter + " : Type : Spell - Name : " + getCardName() + " - MP : " + getRequiredMP() + " – Description : " /*todo*/ + " Sell Cost : " + getPrice());
    }

    public void printSpellCardStats()
    {
        System.out.println("Type : Spell - Name : " + getCardName() + " - MP : " + getRequiredMP() + " – Description : " /*todo*/ + " Sell Cost : " + getPrice());
    }
}
