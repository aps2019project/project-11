import java.util.ArrayList;

public class Spell extends Card
{

    private TargetCommunities targetCommunity;
    private DescriptionTypeOfSpell descriptionTypeOfSpell;
    private Effect effect;

    private static ArrayList<Spell> spells = new ArrayList<>();

    private int effectMultiplicity;

    public Spell(String name, int price, int mana, TargetCommunities targetCommunity, Effect effect, int effectMultiplicity)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMana(mana);
        this.targetCommunity = targetCommunity;
        this.effect = effect;
        //todo //effectmultiplicity
        spells.add(this);
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

    public static ArrayList<Spell> getSpells()
    {
        return spells;
    }

    public void printSpellCardStats(int counter)
    {
        System.out.println(counter + " : Type : Spell - Name : " + getCardName() + " - MP : " + getRequiredMana() + " – Description : " /*todo*/ + " Sell Cost : " + getPrice());
    }
}
