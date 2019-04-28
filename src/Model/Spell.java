package Model;

import java.util.ArrayList;

public class Spell extends Card
{
    private static ArrayList<Spell> spells = new ArrayList<>();
    private String descriptionTypeOfSpell;
    private SpellEffect spellEffect = new SpellEffect();

    public Spell(String name, int price, int MP, ArrayList<SpellChange> spellChanges, ArrayList<Target> targets)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.getSpellEffect().setSpellChanges(spellChanges);
        this.getSpellEffect().setTargets(targets);
        spells.add(this);
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
