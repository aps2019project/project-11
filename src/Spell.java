import java.util.ArrayList;

public class Spell extends Card
{
    public enum DescriptionTypeOfSpell
    {
        disarm, removingBadAndGoodBuffs, adding2ToAP, impact4Time, adding4ToHeroAP, hotHouseFor2turn, impactFor8TimeToEnemyHero, hotHouseFor1Turn, adding4ToAPButDisarm, disarmFor1Turn, poisonAllForcesFor4Turn, removingGoodAndBadBuffsForOneForse, weaknessBuffDecreasing6HPHaving2holyBuffFor3Turn, powerBuffIncrease6AP,powerBuffWith2APConstant, impactToAllEnemy6impact, weaknessWithdecreasing4APInOneForse, weaknessWithdecreasing6HPAndPowerBuffWithIncreasing8APInOneForse, killingEnemy, stunFor2Turn
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
