package Model;

import java.util.ArrayList;

public class Hero extends NonSpellCard
{
    private static ArrayList<Hero> heroes = new ArrayList<>();
    private int turnsRemainingToEndCoolDown;
    private int coolDown;

    public Hero(String name, int price, int HP, int AP, int Mp, int coolDown, SpecialPower specialPower, ImpactType impactType, int rangeOfAttack, int imageNumber)
    {
        setCardName(name);
        setPrice(price);
        setDefaultHP(HP);
        setDefaultAP(AP);
        setRequiredMP(Mp);
        setSpecialPower(specialPower);
        setCoolDown(coolDown);
        setImpactType(impactType);
        setRangeOfAttack(rangeOfAttack);
        this.setImageNumber(imageNumber);
        heroes.add(this);
        Shop.getInstance().addCardToShop(this);
    }
    public Hero()
    {

    }

    public static void setHeroes()
    {
        SpecialPower specialPowerHero1 = new SpecialPower("Activate a power buff with 4 units Increase impact strength permanently on itself");
        specialPowerHero1.getSpellEffect().addSpellChange(new SpellChange(0, true, true, 4, 0, 0, false, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero1.getSpellEffect().addTarget(new Target(0, 0, true, false, 0, 0, 0, 0, 0, 0, 0, null, false, false, false));
        new Hero("Dave White", 8000, 50, 4, 1, 2, specialPowerHero1, ImpactType.melee, 0 ,0);

        SpecialPower specialPowerHero2 = new SpecialPower("Stun all opponents forces for 1 turn");
        specialPowerHero2.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, true, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero2.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, 0, 0, 0, 0, 0, null, false, true, false));
        new Hero("Simurgh", 9000, 50, 4, 5, 8, specialPowerHero2, ImpactType.melee, 0, 1);

        SpecialPower specialPowerHero3 = new SpecialPower("Disarm one opponent force");
        specialPowerHero3.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, false, true, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero3.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, 0, null, false, false, false));
        new Hero("SevenHeadedDragon", 8000, 50, 4, 0, 1, specialPowerHero3, ImpactType.melee, 0,  2);

        SpecialPower specialPowerHero4 = new SpecialPower("Stun 1 opponent force for 1 turn");
        specialPowerHero4.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, true, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero4.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, 0, null, false, false, false));
        new Hero("Rakhsh", 8000, 50, 4, 1, 2, specialPowerHero4, ImpactType.melee, 0,  3);

        SpecialPower specialPowerHero5 = new SpecialPower("Poison opponent for 3 turns when it hurts");
        specialPowerHero5.getSpellEffect().addSpellChange(new SpellChange(3, false, false, 0, -1, 0, false, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero5.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, 0, null, false, false, false));
        new Hero("Zahak", 10000, 50, 2, 0, 0, specialPowerHero5, ImpactType.melee, 0,  4);

        SpecialPower specialPowerHero6 = new SpecialPower("Hallow a cell for 3 turns");
        specialPowerHero6.getSpellEffect().addSpellChange(new SpellChange(3, true, false, 0, 0, 0, false, false, false, 0, false, false, true, false, false, false, false, false));
        specialPowerHero6.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, 1, 1, 1, 1, 0, null, false, false, false));
        new Hero("Kaveh", 8000, 50, 4, 1, 3, specialPowerHero6, ImpactType.melee, 0,  5);

        SpecialPower specialPowerHero7 = new SpecialPower("Impact 4 units to all forces in heroes row");
        specialPowerHero7.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, -4, 0, false, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero7.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, 1, 1, 1, 9, 6, null, false, true, false));
        new Hero("Arash", 10000, 30, 2, 2, 2, specialPowerHero7, ImpactType.ranged, 6, 6);

        SpecialPower specialPowerHero8 = new SpecialPower("Dispel 1 opponent force");
        specialPowerHero8.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, false, false, false, 0, false, false, false, false, false, false, true, false));
        specialPowerHero8.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, 3, null, false, false, false));
        new Hero("Afsaneh", 11000, 40, 3, 1, 2, specialPowerHero8, ImpactType.ranged, 3, 7);

        SpecialPower specialPowerHero9 = new SpecialPower("3 continuous holly buffs");
        specialPowerHero9.getSpellEffect().addSpellChange(new SpellChange(0, true, false, 0, 0, 0, false, false, true, 3, false, false, false, false, false, false, false, true));
        specialPowerHero9.getSpellEffect().addTarget(new Target(0, 0, true, false, 0, 0, 0, 0, 0, 0, 3, null, false, false, false));
        new Hero("Esfandiar", 12000, 35, 3, 0, 0, specialPowerHero9, ImpactType.hybrid, 3, 8);

        SpecialPower specialPowerHero10 = new SpecialPower("Rostam has no specialPower");
        specialPowerHero10.getSpellEffect().addSpellChange(new SpellChange(0, false, false, 0, 0, 0, false, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero10.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, 0, 0, 0, 0, 4, null, false, false, false));
        new Hero("Rostam", 8000, 55, 7, 0, 0, specialPowerHero10, ImpactType.hybrid, 4,  9);
    }

    public static Hero findHero(String name)
    {
        for (Hero hero : heroes)
        {
            if (hero.getCardName().equals(name))
            {
                return hero;
            }
        }
        return null;
    }

    public static ArrayList<Hero> getHeroes()
    {
        return heroes;
    }

    public static void setHeroes(ArrayList<Hero> heroes)
    {
        Hero.heroes = heroes;
    }

    public int getTurnsRemainingToEndCoolDown()
    {
        return turnsRemainingToEndCoolDown;
    }

    public void setTurnsRemainingToEndCoolDown(int turnsRemainingToEndCoolDown)
    {
        this.turnsRemainingToEndCoolDown = turnsRemainingToEndCoolDown;
    }

    public int getCoolDown()
    {
        return coolDown;
    }

    public void setCoolDown(int coolDown)
    {
        this.coolDown = coolDown;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Hero)
        {
            if (((Hero) obj).getCardID().equals(this.getCardID()))
            {
                return true;
            }
        }
        return false;
    }
}
