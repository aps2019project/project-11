package Model;

public class SpecialPower
{
    private String descriptionTypeOfSpecialPower;
    private SpellEffect spellEffect = new SpellEffect();

    public SpecialPower(String descriptionTypeOfSpecialPower)
    {
        this.descriptionTypeOfSpecialPower = descriptionTypeOfSpecialPower;
    }

    public String getDescriptionTypeOfSpecialPower() {
        return descriptionTypeOfSpecialPower;
    }

    public void setDescriptionTypeOfSpecialPower(String descriptionTypeOfSpecialPower) {
        this.descriptionTypeOfSpecialPower = descriptionTypeOfSpecialPower;
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
