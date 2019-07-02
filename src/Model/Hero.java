package Model;

import javafx.scene.control.TextField;

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
        specialPowerHero1.getSpellEffect().addTarget(new Target(0, 0, true, false, 0, 0, true, false, 1, 1, null, false, false, false));
        new Hero("Dave White", 8000, 50, 4, 1, 2, specialPowerHero1, ImpactType.melee, 0 ,0);

        SpecialPower specialPowerHero2 = new SpecialPower("Stun all opponents forces for 1 turn");
        specialPowerHero2.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, true, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero2.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, false, false, 9, 5, null, false, true, false));
        new Hero("Simurgh", 9000, 50, 4, 5, 8, specialPowerHero2, ImpactType.melee, 0, 1);

        SpecialPower specialPowerHero3 = new SpecialPower("Disarm one opponent force");
        specialPowerHero3.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, false, true, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero3.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, false, false, 9, 5, null, false, false, false));
        new Hero("SevenHeadedDragon", 8000, 50, 4, 0, 1, specialPowerHero3, ImpactType.melee, 0,  2);

        SpecialPower specialPowerHero4 = new SpecialPower("Stun 1 opponent force for 1 turn");
        specialPowerHero4.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, true, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero4.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, false, false, 9, 5, null, false, false, false));
        new Hero("Rakhsh", 8000, 50, 4, 1, 2, specialPowerHero4, ImpactType.melee, 0,  3);

        SpecialPower specialPowerHero5 = new SpecialPower("Poison opponent for 3 turns when it hurts");
        specialPowerHero5.getSpellEffect().addSpellChange(new SpellChange(3, false, false, 0, -1, 0, false, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero5.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, false, false, 1, 1, null, false, false, false));
        new Hero("Zahak", 10000, 50, 2, 0, 0, specialPowerHero5, ImpactType.melee, 0,  4);

        SpecialPower specialPowerHero6 = new SpecialPower("Hallow a cell for 3 turns");
        specialPowerHero6.getSpellEffect().addSpellChange(new SpellChange(3, true, false, 0, 0, 0, false, false, false, 0, false, false, true, false, false, false, false, false));
        specialPowerHero6.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, false, true, 1, 1, null, false, false, false));
        new Hero("Kaveh", 8000, 50, 4, 1, 3, specialPowerHero6, ImpactType.melee, 0,  5);

        SpecialPower specialPowerHero7 = new SpecialPower("Impact 4 units to all forces in heroes row");
        specialPowerHero7.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, -4, 0, false, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero7.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, false, false, 9, 1, null, false, true, false));
        new Hero("Arash", 10000, 30, 2, 2, 2, specialPowerHero7, ImpactType.ranged, 6, 6);

        SpecialPower specialPowerHero8 = new SpecialPower("Dispel 1 opponent force");
        specialPowerHero8.getSpellEffect().addSpellChange(new SpellChange(1, false, false, 0, 0, 0, false, false, false, 0, false, false, false, false, false, false, true, false));
        specialPowerHero8.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, false, false, 9, 5, null, false, false, false));
        new Hero("Afsaneh", 11000, 40, 3, 1, 2, specialPowerHero8, ImpactType.ranged, 3, 7);

        SpecialPower specialPowerHero9 = new SpecialPower("3 continuous holly buffs");
        specialPowerHero9.getSpellEffect().addSpellChange(new SpellChange(0, true, false, 0, 0, 0, false, false, true, 3, false, false, false, false, false, false, false, true));
        specialPowerHero9.getSpellEffect().addTarget(new Target(0, 0, true, false, 0, 0, true, false, 1, 1, null, false, false, false));
        new Hero("Esfandiar", 12000, 35, 3, 0, 0, specialPowerHero9, ImpactType.hybrid, 3, 8);

        SpecialPower specialPowerHero10 = new SpecialPower("Rostam has no specialPower");
        specialPowerHero10.getSpellEffect().addSpellChange(new SpellChange(0, false, false, 0, 0, 0, false, false, false, 0, false, false, false, false, false, false, false, false));
        specialPowerHero10.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, false, false, 0, 0, null, false, false, false));
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

    public static void workingOnHeroText(ArrayList<TextField> textFields)
    {
        String name = textFields.get(0).getText();
        String Ap = textFields.get(1).getText();
        String Hp = textFields.get(2).getText();
        String AttackType = textFields.get(3).getText();
        String Range = textFields.get(4).getText();
        String coolDown = textFields.get(5).getText();
        String cost = textFields.get(6).getText();
        String turnsToApply = textFields.get(7).getText();
        String isPositive = textFields.get(8).getText();
        String untilEnd = textFields.get(9).getText();
        String changeAp = textFields.get(10).getText();
        String changeHp = textFields.get(11).getText();
        String changeMp = textFields.get(12).getText();
        String stun = textFields.get(13).getText();
        String disarm = textFields.get(14).getText();
        String numOfHolyBuff = textFields.get(15).getText();
        String toxic = textFields.get(16).getText();
        String holyCell = textFields.get(17).getText();
        String fiery = textFields.get(18).getText();
        String kill = textFields.get(19).getText();
        String numOfOwnMinion = textFields.get(20).getText();
        String numOfOpponentMinion = textFields.get(21).getText();
        String ownHero = textFields.get(22).getText();
        String opponentHero = textFields.get(23).getText();
        String numOfOpponentBothNonSpell = textFields.get(24).getText();
        String numOfOwnBothNonSpell = textFields.get(25).getText();
        String allOwnMinion = textFields.get(26).getText();
        String allOpponentBothNonSpell = textFields.get(27).getText();
        String allOwnBothNonSpell = textFields.get(28).getText();

        makingHeroCard(numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOpponentBothNonSpell, allOwnBothNonSpell, name, Ap, Hp, AttackType, Range, coolDown, cost, turnsToApply, isPositive, untilEnd, changeAp, changeHp, changeMp, stun, disarm, numOfHolyBuff, toxic, holyCell, fiery, kill);
    }

    private static void makingHeroCard(String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOpponentBothNonSpell, String allOwnBothNonSpell, String name, String ap, String hp, String attackType, String range, String coolDown, String cost, String turnsToApply, String isPositive, String untilEnd, String changeAp, String changeHp, String changeMP, String stun, String disarm, String numOfHolyBuff, String toxic, String holyCell, String fiery, String kill)
    {
        int AP = Integer.parseInt(ap);
        int HP = Integer.parseInt(hp);
        int price = Integer.parseInt(cost, 10);
        int rangeOfAttack = Integer.parseInt(range);
        int cooldown = Integer.parseInt(coolDown, 10);
        int turn = Integer.parseInt(turnsToApply);
        int apChange = Integer.parseInt(changeAp);
        int hpChange = Integer.parseInt(changeHp);
        int mpChange = Integer.parseInt(changeMP);
        int holyNumber = Integer.parseInt(numOfHolyBuff);
        int numberOfOwnMinion = Integer.parseInt(numOfOwnMinion);
        int numberOfOpponentMinion = Integer.parseInt(numOfOpponentMinion);
        int numberOfOpponentBothNonSpell = Integer.parseInt(numOfOpponentBothNonSpell);
        int numberOfOwnBothNonSpell = Integer.parseInt(numOfOwnBothNonSpell);
        int numberOfHolyBuff = Integer.parseInt(numOfHolyBuff);
        Hero hero = new Hero();
        hero.setCardName(name);
        hero.setDefaultAP(AP);
        hero.setDefaultHP(HP);
        SpecialPower specialPower = new SpecialPower("Custom card");
        specialPower.getSpellEffect().addTarget(new Target());
        specialPower.getSpellEffect().addSpellChange(new SpellChange());
        hero.setSpecialPower(specialPower);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTurnsToApplyChange(turn);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnBothNonSpellCards(numberOfOwnBothNonSpell);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentMinions(numberOfOpponentMinion);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnMinions(numberOfOwnMinion);
        hero.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentBothNonSpellCards(numberOfOpponentBothNonSpell);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(numberOfHolyBuff);
        if (allOpponentBothNonSpell.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOpponentNonSpellCards(true);
        }
        if (allOwnBothNonSpell.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnBothNonSpellCards(true);
        }
        if (allOwnMinion.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnMinions(true);
        }
        if (ownHero.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setOwnHero(true);
        }
        if (opponentHero.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getTargets().get(0).setOpponentHero(true);
        }
        if (attackType.equalsIgnoreCase("melee"))
        {
            hero.setImpactType(ImpactType.melee);
        }
        if (attackType.equalsIgnoreCase("ranged"))
        {
            hero.setImpactType(ImpactType.ranged);
        }
        if (attackType.equalsIgnoreCase("hybrid"))
        {
            hero.setImpactType(ImpactType.hybrid);
        }

        if (isPositive.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setPositiveChange(true);
        }
        if (untilEnd.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setApplyChangeUntilEndOfTheGame(true);
        }
        if (stun.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setStunOpponent(true);
        }
        if (disarm.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setDisarmOpponent(true);
        }
        if (toxic.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellToxic(true);
        }
        if (kill.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setKilling(true);
        }
        if (fiery.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellFiery(true);
        }
        if (holyCell.equalsIgnoreCase("true"))
        {
            hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellHoly(true);
        }
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(holyNumber);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeHP(hpChange);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeAP(apChange);
        hero.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeMP(mpChange);
        hero.setPrice(price);
        hero.setCoolDown(cooldown);
        hero.setRangeOfAttack(rangeOfAttack);
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, hero, false);
        Shop.getInstance().addCardToShop(hero);
        Hero.getHeroes().add(hero);
        //showOutput.printOutput("Custom card " + hero.getCardID() + " added to your collection"); //todo
    }
}
