package Model;

public class SpecialPower
{
    private String descriptionTypeOfSpell;
    private SpellEffect spellEffect = new SpellEffect();

    public SpecialPower(String descriptionTypeOfSpell)
    {
        this.descriptionTypeOfSpell = descriptionTypeOfSpell;
    }

    public String getDescriptionTypeOfSpell() {
        return descriptionTypeOfSpell;
    }

    public void setDescriptionTypeOfSpell(String descriptionTypeOfSpell) {
        this.descriptionTypeOfSpell = descriptionTypeOfSpell;
    }

    public SpellEffect getSpellEffect()
    {
        return spellEffect;
    }

    public void setSpellEffect(SpellEffect spellEffect)
    {
        this.spellEffect = spellEffect;
    }

}
