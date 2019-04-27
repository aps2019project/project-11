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

    public SpellEffect getSpellEffect() {
        return spellEffect;
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
