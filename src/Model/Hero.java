package Model;

import java.util.ArrayList;

public class Hero extends NonSpellCards
{
    private static ArrayList<Hero> heroes = new ArrayList<>();
    private int heroID;
    private int turnsRemainingToEndCoolDown;
    private int coolDown;

    public Hero(String name, int price, int HP, int AP, int Mp, int coolDown,SpecialPower specialPower)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setDefaultHP(HP);
        this.setDefaultAP(AP);
        this.setRequiredMP(Mp);
        this.setSpecialPower(specialPower);
        this.coolDown = coolDown;
        heroes.add(this);
    }

    public static void setHeroes()
    {
        SpecialPower specialPowerHero1 = new SpecialPower("Activate a power buff with 4 units Increase impact strength permanently on itself");
        specialPowerHero1.getSpellEffect().addSpellChange(new SpellChange(0, true, true, 4, 0, 0, false, false, false, false, false, false, false, false));
        specialPowerHero1.getSpellEffect().addTarget(new Target(0, 0, true, false, 0, 0, 0, 0, 0, 0, ImpactType.melee, 0, null, false, false, false));
        new Hero("Dave White", 8000, 50, 4, 1, 2, specialPowerHero1);

        SpecialPower specialPowerHero2 = new SpecialPower("Stun all opponent forces for 1 turn");
        specialPowerHero2.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, true, false, false, false, false, false, false, false));
        specialPowerHero2.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, 0, 0, 0, 0, ImpactType.melee, 0, null, false, true, false));
        new Hero("Simurgh", 9000, 50, 4, 5, 8, specialPowerHero2);

        SpecialPower specialPowerHero3 = new SpecialPower("Disarm one opponent force");
        specialPowerHero3.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, false, true, false, false, false, false, false, false));
        specialPowerHero3.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, ImpactType.melee, 0, null, false, false, false));
        new Hero("SevenHeadedDragon", 8000, 50, 4, 0, 1, specialPowerHero3);

        SpecialPower specialPowerHero4 = new SpecialPower("Stun 1 opponent force for 1 turn");
        specialPowerHero4.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, true, false, false, false, false, false, false, false));
        specialPowerHero4.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, ImpactType.melee, 0, null, false, false, false));
        new Hero("Rakhsh", 8000, 50, 4, 1, 2, specialPowerHero4);

        SpecialPower specialPowerHero5 = new SpecialPower("Poison opponent for 3 turns when it hurts");
        specialPowerHero5.getSpellEffect().addSpellChange(new SpellChange(3, false, false, 0, -1, 0, false, false, false, false, false, false, false, false));
        specialPowerHero5.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, 0, 0, 0, 0, ImpactType.melee, 0, null, false, false, false));
        new Hero("Zahak", 10000, 50, 2, 0, 0, specialPowerHero5);


        new Hero("Kaveh", 8000, 50, 4, ImpactType.melee, new Spell(), 3);
        new Hero("Arash", 10000, 30, 2, ImpactType.ranged, 6, new Spell(), 2);
        new Hero("Afsaneh", 11000, 40, 3, ImpactType.ranged, 3, new Spell(), 2);
        new Hero("Esfandiar", 12000, 35, 3, ImpactType.hybrid, 3, new Spell(), 0);
        new Hero("Rostam", 8000, 55, 7, ImpactType.hybrid, 4, 0);
    }

    public Hero findHero(int heroID)
    {
        for (Hero hero : heroes)
        {
            if (hero.getHeroID() == heroID)
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

    public int getHeroID()
    {
        return heroID;
    }

    public static void setHeroes(ArrayList<Hero> heroes) {
        Hero.heroes = heroes;
    }

    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }

    public int getTurnsRemainingToEndCoolDown() {
        return turnsRemainingToEndCoolDown;
    }

    public void setTurnsRemainingToEndCoolDown(int turnsRemainingToEndCoolDown) {
        this.turnsRemainingToEndCoolDown = turnsRemainingToEndCoolDown;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }
}
