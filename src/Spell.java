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

    /*public Spell()
    {
        //todo constructor for heroes
    }*/

    public static void setSpells()
    {
        new Spell("totalDisarm","1000","0",TargetCommunities.opponentForce,Effect.disarmBuff,,DescriptionTypeOfSpell.disarm);
        new Spell("areaDispel","1500","2",TargetCommunities.twoInTwoSquare,,,DescriptionTypeOfSpell.removingBadAndGoodBuffs);
        new Spell("empower","250","1",TargetCommunities.ownForce,,"2",DescriptionTypeOfSpell.adding2ToAP);
        new Spell("fireball","400","1",TargetCommunities.opponentForce,,"4",DescriptionTypeOfSpell.impact4Time);
        new Spell("godStrength","450","2",TargetCommunities.ownHero,,"4",DescriptionTypeOfSpell.adding4ToHeroAP);
        new Spell("hellFire","600","3",TargetCommunities.twoInTwoSquare,,"2",DescriptionTypeOfSpell.hotHouseFor2turn);
        new Spell("lightingBolt","1250","2",TargetCommunities.opponentHero,,"8",DescriptionTypeOfSpell.impactFor8TimeToEnemyHero);
        new Spell("poisonLake","900","5",TargetCommunities.threeInThreeSquare,,"1",DescriptionTypeOfSpell.poisonHouseFor1Turn);
        new Spell("madness","650","0",TargetCommunities.ownForce,,"4",DescriptionTypeOfSpell.adding4ToAPButDisarmFor3Turn);
        new Spell("allDisarm","2000","9",TargetCommunities.allOpponentForces,,"//",DescriptionTypeOfSpell.disarmFor1Turn);
        new Spell("allPoison","1500","8",TargetCommunities.allOpponentForces,,"//",DescriptionTypeOfSpell.poisonAllForcesFor4Turn);
        new Spell("dispel","2100","0",TargetCommunities.anyForce,,"//",DescriptionTypeOfSpell.removingBadAndGoodBuffs);
        new Spell("healthWithProfit","2250","0",TargetCommunities.ownForce,,"//",DescriptionTypeOfSpell.weaknessBuffDecreasing6HPHaving2holyBuffFor3Turn);
        new Spell("powerUp","2500","2",TargetCommunities.ownForce,,"//",DescriptionTypeOfSpell.powerBuffIncrease6AP);
        new Spell("allPower","2000","4",TargetCommunities.allOwnForces,,"//",DescriptionTypeOfSpell.powerBuffWith2APConstant);
        new Spell("allAttack","1500","4",TargetCommunities.allOpponentForesInColumn,,"//",DescriptionTypeOfSpell.impactToAllEnemy6impact);
        new Spell("weakening","1000","1",TargetCommunities.opponentMinion,,"//",DescriptionTypeOfSpell.weaknessWithdecreasing4APInOneForse);
        new Spell("sacrifice","1600","2",TargetCommunities.ownMinion,,"//",DescriptionTypeOfSpell.weaknessWithdecreasing6HPAndPowerBuffWithIncreasing8APInOneForse);
        new Spell("kingGuard","1750","9",TargetCommunities.opponentMinionAroundOwnHero,,"//",DescriptionTypeOfSpell.killingEnemy);
        new Spell("shock","1200","1",TargetCommunities.opponentForce,Effect.stunBuff ,,DescriptionTypeOfSpell.stunFor2Turn);

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
