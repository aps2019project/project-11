import java.util.ArrayList;

public class Spell extends Card
{
    public enum DescriptionTypeOfSpell
    {
        disarm, removingBadAndGoodBuffs, adding2ToAP, impact4Time, adding4ToHeroAP, hotHouseFor2turn, impactFor8TimeToEnemyHero, poisonHouseFor1Turn ,hotHouseFor1Turn, adding4ToAPButDisarmFor3Turn, disarmFor1Turn, poisonAllForcesFor4Turn, weaknessBuffDecreasing6HPHaving2holyBuffFor3Turn, powerBuffIncrease6AP,powerBuffWith2APConstant, impactToAllEnemy6impact, weaknessWithdecreasing4APInOneForse, weaknessWithdecreasing6HPAndPowerBuffWithIncreasing8APInOneForse, killingEnemy, stunFor2Turn
    }

    private static ArrayList<Spell> spells = new ArrayList<>();
    private TargetCommunities targetCommunity;
    private String descriptionTypeOfSpell;
    private Effect effect;
    private int effectMultiplicity;
    private TimeToActivateSpecialPower timeToActivateSpecialPower;


    public Spell(String name, int price, int MP, TargetCommunities targetCommunity, Effect effect, int effectMultiplicity, String descriptionTypeOfSpell)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setTargetCommunity(targetCommunity);
        this.setEffect(effect);
        this.setEffectMultiplicity(effectMultiplicity);
        this.setDescriptionTypeOfSpell(descriptionTypeOfSpell);
        spells.add(this);
    }

    public Spell()
    {
        //todo constructor for heroes
    }

    public static void setSpells()
    {
       Spell spell1 = new Spell("totalDisarm",1000,0,TargetCommunities.opponentForce,Effect.disarmBuff,0/*todo*/,"disarm");
       Spell spell2 = new Spell("areaDispel",1500,2,TargetCommunities.twoInTwoSquare,null/*todo*/,0/*todo*/,"removingBadAndGoodBuffs");
       Spell spell3 =  new Spell("empower",250,1,TargetCommunities.ownForce,Effect.powerBuff,2,"adding2ToAP");
       Spell spell4 =  new Spell("fireball",400,1,TargetCommunities.opponentForce,Effect.holyBuff,4,"impact4Time");
       Spell spell5 =  new Spell("godStrength",450,2,TargetCommunities.ownHero,Effect.powerBuff,4,"adding4ToHeroAP");
       Spell spell6 =  new Spell("hellFire",600,3,TargetCommunities.twoInTwoSquare,Effect.fieryCell,2,"hotHouseFor2turn");
       Spell spell7 = new Spell("lightingBolt",1250,2,TargetCommunities.opponentHero,Effect.holyBuff,8,"impactFor8TimeToEnemyHero");
       Spell spell8 = new Spell("poisonLake",900,5,TargetCommunities.threeInThreeSquare,Effect.toxicCell,1,"poisonHouseFor1Turn");
       Spell spell9 = new Spell("madness",650,0,TargetCommunities.ownForce,Effect.powerBuff,4,"adding4ToAPButDisarmFor3Turn");
       Spell spell10 = new Spell("allDisarm",2000,9,TargetCommunities.allOpponentForces,Effect.disarmBuff,0/*todo*/,"disarmFor1Turn");
       Spell spell11 = new Spell("allPoison",1500,8,TargetCommunities.allOpponentForces,Effect.poisonBuff,0/*todo*/,"poisonAllForcesFor4Turn");
       Spell spell12 = new Spell("dispel",2100,0,TargetCommunities.anyForce,null/*todo*/,0/*todo*/,"removingBadAndGoodBuffs");
       Spell spell13 = new Spell("healthWithProfit",2250,0,TargetCommunities.ownForce,null/*todo*/,0/*todo*/,"weaknessBuffDecreasing6HPHaving2holyBuffFor3Turn");
       Spell spell14 = new Spell("powerUp",2500,2,TargetCommunities.ownForce,Effect.powerBuff,0/*todo*/,"powerBuffIncrease6AP");
       Spell spell15 = new Spell("allPower",2000,4,TargetCommunities.allOwnForces,Effect.powerBuff,0/*todo*/,"powerBuffWith2APConstant");
       Spell spell16 = new Spell("allAttack",1500,4,TargetCommunities.allOpponentForesInColumn,Effect.holyBuff,0/*todo*/,"impactToAllEnemy6impact");
       Spell spell17 = new Spell("weakening",1000,1,TargetCommunities.opponentMinion,Effect.WeaknessBuff,0/*todo*/,"weaknessWithdecreasing4APInOneForse");
       Spell spell18 = new Spell("sacrifice",1600,2,TargetCommunities.ownMinion,null/*todo*/,0/*todo*/,"weaknessWithdecreasing6HPAndPowerBuffWithIncreasing8APInOneForse");
       Spell spell19 = new Spell("kingGuard",1750,9,TargetCommunities.opponentMinionAroundOwnHero,null/*todo*/,0/*todo*/,"killingEnemy");
       Spell spell20 = new Spell("shock",1200,1,TargetCommunities.opponentForce,Effect.stunBuff ,0/*todo*/,"stunFor2Turn");

    }

    public void effectHolyBuff(Card card)
    {

    }

    public void effectPowerBuff(Card card)
    {

    }

    public void effectPoisonBuff(Card card)
    {

    }

    public void effectWeaknessBuff(Card card)
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
}
