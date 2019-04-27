package Model;

import java.util.ArrayList;

public class Spell extends Card
{
    public TypeOfMultiplicity getTypeOfMultiplicity() {
        return typeOfMultiplicity;
    }

    public void setTypeOfMultiplicity(TypeOfMultiplicity typeOfMultiplicity) {
        this.typeOfMultiplicity = typeOfMultiplicity;
    }

    public void setSpellTurn(int spellTurn) {
        this.spellTurn = spellTurn;
    }

    public int getSpellUnit() {
        return spellUnit;
    }

    public void setSpellUnit(int spellUnit) {
        this.spellUnit = spellUnit;
    }

    public int getSpellTurn() {
        return spellTurn;
    }

    public ImpactType getImpactType() {
        return impactType;
    }

    public void setImpactType(ImpactType impactType) {
        this.impactType = impactType;
    }

    public enum DescriptionTypeOfSpell
    {
        disarm, removingBadAndGoodBuffs, adding2ToAP, impact4Time, adding4ToHeroAP, hotHouseFor2turn, impactFor8TimeToEnemyHero, poisonHouseFor1Turn ,hotHouseFor1Turn, adding4ToAPButDisarmFor3Turn, disarmFor1Turn, poisonAllForcesFor4Turn, weaknessBuffDecreasing6HPHaving2holyBuffFor3Turn, powerBuffIncrease6AP,powerBuffWith2APConstant, impactToAllEnemy6impact, weaknessWithdecreasing4APInOneForse, weaknessWithdecreasing6HPAndPowerBuffWithIncreasing8APInOneForse, killingEnemy, stunFor2Turn
    }

    private static ArrayList<Spell> spells = new ArrayList<>();
    private String descriptionTypeOfSpell;
    private SpellEffect spellEffect;

    public Spell(String name, int price,int MP,TargetCommunities targetCommunities,Effect effect,TypeOfMultiplicity typeOfMultiplicity,int number,String descriptionTypeOfSpell)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setTargetCommunity(targetCommunities);
        this.setEffect(effect);
        this.setTypeOfMultiplicity(typeOfMultiplicity);
        this.setDescriptionTypeOfSpell(descriptionTypeOfSpell);
        if (typeOfMultiplicity==TypeOfMultiplicity.spellTurn)
        {
            this.setSpellTurn(number);
        }
        else if (typeOfMultiplicity==(TypeOfMultiplicity.spellUnit))
        {
            this.setSpellUnit(number);
        }
        else if (typeOfMultiplicity==(TypeOfMultiplicity.spellTurnAndUnit))
        {
            int turn=number%10;
            int unit=number/10;
            this.setSpellUnit(unit);
            this.setSpellTurn(turn);
        }
        spells.add(this);
    }

    public Spell(String name, int price,int MP,TargetCommunities targetCommunities,Effect effect,TypeOfMultiplicity typeOfMultiplicity,String descriptionTypeOfSpell)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setTargetCommunity(targetCommunities);
        this.setEffect(effect);
        this.setTypeOfMultiplicity(typeOfMultiplicity);
        this.setDescriptionTypeOfSpell(descriptionTypeOfSpell);
        spells.add(this);
    }
    public Spell(TypeOfMultiplicity typeOfMultiplicity,int number,TargetCommunities targetCommunity,ImpactType impactType,Effect effect)
    {
        this.setTypeOfMultiplicity(typeOfMultiplicity);
        this.setImpactType(impactType);
        this.setEffect(effect);
        this.setTargetCommunity(targetCommunity);
        if (typeOfMultiplicity==TypeOfMultiplicity.spellTurn)
        {
            this.setSpellTurn(number);
        }
        if (typeOfMultiplicity==TypeOfMultiplicity.spellUnit)
        {
            this.setSpellUnit(number);
        }
        else if (typeOfMultiplicity==TypeOfMultiplicity.spellTurnAndUnit)
        {
            int Unit=number/10;
            int Turn=number%10;
            this.setSpellUnit(Unit);
            this.setSpellTurn(Turn);
        }
    }

    public static void setSpells()
    {
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
    }

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

    public int getEffectMultiplicity() {
        return effectMultiplicity;
    }

    public void setEffectMultiplicity(int effectMultiplicity) {
        this.effectMultiplicity = effectMultiplicity;
    }

    public TargetCommunities getTargetCommunity() {
        return targetCommunity;
    }

    public void setTargetCommunity(TargetCommunities targetCommunity) {
        this.targetCommunity = targetCommunity;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public String getDescriptionTypeOfSpell() {
        return descriptionTypeOfSpell;
    }

    public void setDescriptionTypeOfSpell(String descriptionTypeOfSpell) {
        this.descriptionTypeOfSpell = descriptionTypeOfSpell;
    }

    public TimeToActivateSpecialPower getTimeToActivateSpecialPower() {
        return timeToActivateSpecialPower;
    }

    public void setTimeToActivateSpecialPower(TimeToActivateSpecialPower timeToActivateSpecialPower) {
        this.timeToActivateSpecialPower = timeToActivateSpecialPower;
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
