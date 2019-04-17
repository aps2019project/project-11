import java.util.ArrayList;

public class Hero extends NonSpellCards
{
    private static ArrayList<Hero> heroes = new ArrayList<>();
    private int heroID;
    private int turnsRemainingToEndCoolDown;
    private int coolDown;

    public Hero(String name, int price, int HP, int AP, ImpactType impactType, int maxAttackRange, Spell specialPower, int coolDown)
    {
        this(name, price, HP, AP, impactType, coolDown);
        this.setMaxAttackRange(maxAttackRange);
        this.setSpecialPower(specialPower);
    }

    public Hero(String name, int price, int HP, int AP, ImpactType impactType, Spell specialPower, int coolDown)
    {
        this(name, price, HP, AP, impactType, coolDown);
        this.setSpecialPower(specialPower);
    }

    public Hero(String name, int price, int HP, int AP, ImpactType impactType, int maxAttackRange, int coolDown)
    {
        this(name, price, HP, AP, impactType, coolDown);
        this.setMaxAttackRange(maxAttackRange);
    }

    public Hero(String name, int price, int HP, int AP, ImpactType impactType, int coolDown)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setDefaultHP(HP);
        this.setDefaultAP(AP);
        this.setImpactType(impactType);
        this.coolDown = coolDown;
        heroes.add(this);
    }

    public static void setHeroes()
    {
        new Hero("Dave White", 8000, 50, 4, ImpactType.melee, new Spell(), 2);
        new Hero("Simurgh", 9000, 50, 4, ImpactType.melee, new Spell(), 8);
        new Hero("SevenHeadedDragon", 8000, 50, 4, ImpactType.melee, new Spell(), 1);
        new Hero("Rakhsh", 8000, 50, 4, ImpactType.melee, new Spell(), 1);
        new Hero("Zahak", 10000, 50, 2, ImpactType.melee, new Spell(), 0);
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

    public void printHeroStats(int counter)
    {
        System.out.println(counter + " : Name :" + getCardName() + " - AP : " + getDefaultAP() + " – HP : " + getDefaultHP() + " – Class : " + getTypeOfImpact() + " – Special power: " /*todo*/ + " - Sell Cost : " + getPrice());
    }
}
