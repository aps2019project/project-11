package Model;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Minion extends NonSpellCard
{
    private static ArrayList<Minion> minions = new ArrayList<>();
    private boolean ableToCombo;
    public Minion(String name, int price, int MP, int HP, int AP, SpecialPower specialPower, boolean ableToCombo, ImpactType impactType, int rangeOfAttack , int imageNumber)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setDefaultHP(HP);
        this.setDefaultAP(AP);
        this.setSpecialPower(specialPower);
        this.ableToCombo = ableToCombo;
        this.setImpactType(impactType);
        this.setRangeOfAttack(rangeOfAttack);
        this.setImageNumber(imageNumber);
        minions.add(this);
        Shop.getInstance().addCardToShop(this);
    }

    public Minion()
    {

    }
    public static ArrayList<Minion> getMinions()
    {
        return minions;
    }

    public static void setMinions()
    {
        SpecialPower minionSpell2 = new SpecialPower("stun in current turn");
        minionSpell2.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onAttack, 0, 0, 0, true, false, false, false, false, false, false, false, false, true));
        minionSpell2.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, false, false, 1, 1, null, false, false, false));

        SpecialPower minionSpell10 = new SpecialPower("disarm and poison");
        minionSpell10.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onAttack, 0, 0, 0, false, true, false, false, false, false, false, false, false, false));
        minionSpell10.getSpellEffect().addSpellChange(new SpellChange(4, false, false, TimeToActivateSpecialPower.onAttack, 0, -1, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell10.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, false, false, 1, 1, null, false, false, false));

        SpecialPower minionSpell15 = new SpecialPower("10 power buff");
        minionSpell15.getSpellEffect().addSpellChange(new SpellChange(1, true, false, TimeToActivateSpecialPower.passive, 0, 10, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell15.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0,true, false, 1, 1, null, false, false, false));

        SpecialPower minionSpell17 = new SpecialPower("make 2 damage on around minions");
        minionSpell17.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onDeath, 0, -2, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell17.getSpellEffect().addTarget(new Target(0, 8, false, false, 0, 0, false, false, 3, 3, null, false, false, false));

        SpecialPower minionSpell18 = new SpecialPower("made toxic enemy force 3 turn");
        minionSpell18.getSpellEffect().addSpellChange(new SpellChange(3, false, false, TimeToActivateSpecialPower.onAttack, 0, -1, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell18.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, false, false, 1, 1, null, false, false, false));

        SpecialPower minionSpell20 = new SpecialPower("holy buff");
        minionSpell20.getSpellEffect().addSpellChange(new SpellChange(1, true, false, TimeToActivateSpecialPower.onAttack, 0, 0, 0, false, false, true, false, false, false, false, false, false, false));
        minionSpell20.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0,true, false, 1, 1, null, false, false, false));

        SpecialPower minionSpell21 = new SpecialPower("minions around the card receive one more damage when receive damage");
        minionSpell21.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onSpawn, 0, 0, 0, false, false, false, true, false, false, false, false, false, false));
        minionSpell21.getSpellEffect().addTarget(new Target(0, 8, false, false, 0, 0, false, false, 3, 3, null, false, false, false));

        SpecialPower minionSpell23 = new SpecialPower("if attack to opponent minion , next minion receive 8 damage");
        minionSpell23.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onAttack, 0, -8, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell23.getSpellEffect().addTarget(new Target(0, 1, false, false, 0, 0, false, false, 1, 1, null, false, false, false));

        SpecialPower minionSpell24 = new SpecialPower("if attack to opponent minion , next minion receive 6 damage");
        minionSpell24.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onAttack, 0, -6, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell24.getSpellEffect().addTarget(new Target(0, 1, false, false, 0, 0, false, false, 1, 1, null, false, false, false));

        SpecialPower minionSpell25 = new SpecialPower("give 2 power buff and 1 poison buff to around minion cards in one turn");
        minionSpell25.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.passive, 2, -1, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell25.getSpellEffect().addTarget(new Target(0, 9, false, false, 0, 0,true, false, 3, 3, null, false, false, false));

        SpecialPower minionSpell26 = new SpecialPower("give 2 power buff and 1 poison buff to around minion cards in continuously");
        minionSpell26.getSpellEffect().addSpellChange(new SpellChange(0, true, true, TimeToActivateSpecialPower.passive, 2, 0, 0, false, false, true, false, false, false, false, false, false, false));
        minionSpell26.getSpellEffect().addTarget(new Target(0, 9, false, false, 0, 0, false, true, 3, 3, null, false, false, false));

        SpecialPower minionSpell27 = new SpecialPower("increase 1 AP to all own minions");
        minionSpell27.getSpellEffect().addSpellChange(new SpellChange(0, true, true, TimeToActivateSpecialPower.onTurn, 1, 0, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell27.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, false, false, 9, 5, null, true, false, false));

        SpecialPower minionSpell31 = new SpecialPower("random damage one opponent 16 damage");
        minionSpell31.getSpellEffect().addSpellChange(new SpellChange(0, false, false, TimeToActivateSpecialPower.onSpawn, 0, -16, 0, false, false, false, false, false, false, false, false, false, false));
        minionSpell31.getSpellEffect().addTarget(new Target(0, 1, false, false, 0, 0, false, false, 9, 5, null, false, false, false));

        SpecialPower minionSpell35 = new SpecialPower("delete all positive buff");
        minionSpell35.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onAttack, 0, 0, 0, false, false, false, false, false, false, false, true, false, false));
        minionSpell35.getSpellEffect().addTarget(new Target(0, 0, false, false, 1, 0, false, false, 9, 5, null, false, false, false));

        SpecialPower minionSpell36 = new SpecialPower("around minions stun");
        minionSpell36.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onSpawn, 0, 0, 0, true, false, false, false, false, false, false, false, false, false));
        minionSpell36.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, false, false, 3, 3, null, false, true, false));

        SpecialPower minionSpell37 = new SpecialPower("12 holy buff");
        minionSpell37.getSpellEffect().addSpellChange(new SpellChange(0, true, false, TimeToActivateSpecialPower.passive, 0, 0, 0, true, false, true, false, false, false, false, false, false, false));
        minionSpell37.getSpellEffect().addTarget(new Target(0, 0, false, false, 0, 0, true, false, 1, 1, null, false, false, false));

        SpecialPower minionSpell38 = new SpecialPower("on death make 6 damage on opponent hero");
        minionSpell38.getSpellEffect().addSpellChange(new SpellChange(1, false, false, TimeToActivateSpecialPower.onDeath, 0, -6, 0, false, false, true, false, false, false, false, false, false, false));
        minionSpell38.getSpellEffect().addTarget(new Target(0, 0, false, true, 0, 0, false, false, 1, 1, null, false, false, false));

        new Minion("kamandarFars" ,300, 2, 6, 4, null, false,ImpactType.ranged, 7 , 10);
        new Minion("shamsirzanFars", 400, 2, 6, 4 ,minionSpell2,false,ImpactType.melee, 0 , 10);
        new Minion("neizedarFars", 500, 1, 5, 3, null,false, ImpactType.hybrid, 3, 10);
        new Minion("asbsavarFars", 200, 4, 10, 6, null,false,ImpactType.melee, 0, 10);
        new Minion("pahlavanFars", 600, 9, 24, 6, null,false,ImpactType.melee, 0, 10);
        new Minion("sepahsalarFars", 800, 7, 12, 4, null,true,ImpactType.melee ,0, 10);
        new Minion("kamandarTorani", 500, 1, 3, 4,null,false,ImpactType.ranged, 5 , 10);
        new Minion("gholabsangdarTorani", 600, 1, 4, 2, null,false,ImpactType.ranged, 7, 10);
        new Minion("neizedarTorani", 600, 1, 4, 4, null,false,ImpactType.hybrid, 3, 10);
        new Minion("jasosTorani", 700, 4, 6, 6, minionSpell10,false, ImpactType.melee, 0, 10);
        new Minion("GorzdarTorani", 450, 2, 3, 10, null, false, ImpactType.melee, 0, 11);
        new Minion("ShahzadeTorani", 800, 6, 6, 10, null, true, ImpactType.melee, 0 , 11);
        new Minion("Divesiah", 300, 9, 14, 10, null, false, ImpactType.hybrid, 7,  11);
        new Minion("GhoulSangAndaz", 300, 9, 12, 12, null, false, ImpactType.ranged, 7 , 11);
        new Minion("Oghab", 200, 2, 0, 2, minionSpell15, false, ImpactType.ranged, 3, 11);
        new Minion("DivGorazSavar", 300, 6, 16, 8, null, false, ImpactType.melee, 0,  11);
        new Minion("GhoulTakCheshm", 500, 7, 12, 11, minionSpell17, false, ImpactType.hybrid, 3, 11);
        new Minion("MarSammi", 300, 4, 5, 6, minionSpell18, false, ImpactType.ranged, 4,  11);
        new Minion("EzhdehayeAtashAndaz", 250, 5, 9, 5, null, false, ImpactType.ranged, 4, 11);
        new Minion("ShirDarande", 600, 2, 1, 8, minionSpell20, false, ImpactType.melee, 0,  11);
        new Minion("MarGhoulPeykar", 500, 8, 14, 7, minionSpell21, false, ImpactType.ranged, 5, 12);
        new Minion("GorgSefid" ,400 , 5 ,8 , 2 , null , false , ImpactType.melee , 0, 12);
        new Minion("Palang", 400, 4, 6, 2, minionSpell23, false, ImpactType.melee, 0 , 12);
        new Minion("Gorg", 400, 3, 6, 1, minionSpell24, false, ImpactType.melee, 0,  12);
        new Minion("JadoGar", 550, 4, 5, 4, minionSpell25, false, ImpactType.ranged, 3,  12);
        new Minion("JadoGarAzam", 550, 6, 6, 6, minionSpell26, false, ImpactType.ranged, 5 , 12);
        new Minion("Genn", 500, 6, 10, 4, minionSpell27, false, ImpactType.ranged, 4,  12);
        new Minion("GorazVahshi", 500, 6, 10, 14, null, false, ImpactType.melee, 0, 12);
        new Minion("Piran", 400, 8, 20, 12, null, false, ImpactType.melee, 0, 12);
        new Minion("Giv", 450, 4, 5, 7, null, false, ImpactType.ranged, 5, 12);
        new Minion("Bahman", 450, 8, 16, 9, minionSpell31, false, ImpactType.melee, 0, 13);
        new Minion("AshkBos", 400, 7, 14, 8, null, false, ImpactType.melee, 0 , 13);
        new Minion("Iraj", 500, 4, 6, 20, null, false, ImpactType.ranged, 3,  13);
        new Minion("GhoulBozorg", 600, 9, 30, 8, null, false, ImpactType.hybrid, 2 , 13);
        new Minion("GhoulDosar", 550, 4, 10, 4, minionSpell35, false, ImpactType.melee, 0 , 13);
        new Minion("NaneSarma", 500, 3, 3, 4, minionSpell36, false, ImpactType.ranged, 5,  13);
        new Minion("FoladZereh", 650, 3, 1, 1, minionSpell37, false, ImpactType.melee, 0 , 13);
        new Minion("Siavash", 350, 4, 8, 5, minionSpell38, false, ImpactType.melee, 0,  13);
        new Minion("ShahGhoul", 600, 5, 10, 4, null, true, ImpactType.melee, 0 , 13);
        new Minion("ArzhangDiv", 600, 3, 6, 6, null, true, ImpactType.melee, 0 , 13);
    }

    public boolean isAbleToCombo()
    {
        return ableToCombo;
    }

    public void setAbleToCombo(boolean ableToCombo)
    {
        this.ableToCombo = ableToCombo;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Minion)
        {
            if (((Minion) obj).getCardID().equals(this.getCardID()))
            {
                return true;
            }
        }
        return false;
    }

    public static void workingOnMinionText(ArrayList<TextField> textFields)
    {
        String name = textFields.get(0).getText();
        String Ap = textFields.get(1).getText();
        String Hp = textFields.get(2).getText();
        String AttackType = textFields.get(3).getText();
        String Range = textFields.get(4).getText();
        String specialPowerActivation = textFields.get(5).getText();
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
        String combo = textFields.get(19).getText();
        String numOfOwnMinion = textFields.get(20).getText();
        String numOfOpponentMinion = textFields.get(21).getText();
        String ownHero = textFields.get(22).getText();
        String opponentHero = textFields.get(23).getText();
        String numOfOpponentBothNonSpell = textFields.get(24).getText();
        String numOfOwnBothNonSpell = textFields.get(25).getText();
        String allOwnMinion = textFields.get(26).getText();
        String allOpponentBothNonSpell = textFields.get(27).getText();
        String allOwnBothNonSpell = textFields.get(28).getText();
        makingMinionCard(numOfOwnMinion, numOfOpponentMinion, ownHero, opponentHero, numOfOpponentBothNonSpell, numOfOwnBothNonSpell, allOwnMinion, allOwnBothNonSpell, allOpponentBothNonSpell, name, Ap, Hp, AttackType, Range, specialPowerActivation, cost, turnsToApply, isPositive, untilEnd, changeAp, changeHp, changeMp, stun, disarm, numOfHolyBuff, toxic, holyCell, fiery, combo);
    }

    private static void makingMinionCard(String numOfOwnMinion, String numOfOpponentMinion, String ownHero, String opponentHero, String numOfOpponentBothNonSpell, String numOfOwnBothNonSpell, String allOwnMinion, String allOwnBothNonSpell, String allOpponentBothNonSpell, String name, String ap, String hp, String attackType, String range, String specialPowerActivation, String cost, String turn, String isPositive, String UntilEnd, String changeAP, String changeHP, String ChangeMP, String stun, String disarm, String numOfHolyBuff, String toxic, String holycell, String fiery, String combo)
    {
        int AP = Integer.parseInt(ap);
        int HP = Integer.parseInt(hp);
        int price = Integer.parseInt(cost);
        int rangeOfAttack = Integer.parseInt(range);
        int apChange = Integer.parseInt(changeAP);
        int hpChange = Integer.parseInt(changeHP);
        int MpChange = Integer.parseInt(ChangeMP);
        int turnToApply = Integer.parseInt(turn);
        int numberOfOwnMinion = Integer.parseInt(numOfOwnMinion);
        int numberOfOpponentMinion = Integer.parseInt(numOfOpponentMinion);
        int numberOfOpponentBothNonSpell = Integer.parseInt(numOfOpponentBothNonSpell);
        int numberOfOwnBothNonSpell = Integer.parseInt(numOfOwnBothNonSpell);
        int numberOfHolyBuff = Integer.parseInt(numOfHolyBuff);
        Minion minion = new Minion();
        minion.setCardName(name);
        minion.setDefaultAP(AP);
        minion.setDefaultHP(HP);
        minion.setRangeOfAttack(rangeOfAttack);
        SpecialPower specialPower = new SpecialPower("Custom card");
        specialPower.getSpellEffect().addSpellChange(new SpellChange());
        specialPower.getSpellEffect().addTarget(new Target());
        minion.setSpecialPower(specialPower);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setNumOfHolyBuffs(numberOfHolyBuff);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeAP(apChange);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeHP(hpChange);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setChangeMP(MpChange);
        minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTurnsToApplyChange(turnToApply);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentBothNonSpellCards(numberOfOpponentBothNonSpell);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnMinions(numberOfOwnMinion);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOpponentMinions(numberOfOpponentMinion);
        minion.getSpecialPower().getSpellEffect().getTargets().get(0).setNumOfOwnBothNonSpellCards(numberOfOwnBothNonSpell);
        if (ownHero.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setOwnHero(true);
        }
        if (opponentHero.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setOpponentHero(true);
        }
        if (allOpponentBothNonSpell.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOpponentNonSpellCards(true);
        }
        if (allOwnBothNonSpell.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnBothNonSpellCards(true);
        }
        if (allOwnMinion.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getTargets().get(0).setAllOwnMinions(true);
        }
        if (attackType.equalsIgnoreCase("melee"))
        {
            minion.setImpactType(ImpactType.melee);
        }
        if (attackType.equalsIgnoreCase("ranged"))
        {
            minion.setImpactType(ImpactType.ranged);
        }
        if (attackType.equalsIgnoreCase("hybrid"))
        {
            minion.setImpactType(ImpactType.hybrid);
        }
        if (isPositive.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setPositiveChange(true);
        }
        if (stun.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setStunOpponent(true);
        }
        if (fiery.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellFiery(true);
        }
        if (combo.equalsIgnoreCase("true"))
        {
            minion.setAbleToCombo(true);
        }
        if (UntilEnd.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setApplyChangeUntilEndOfTheGame(true);
        }
        if (disarm.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setDisarmOpponent(true);
        }
        if (toxic.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellToxic(true);
        }
        if (holycell.equalsIgnoreCase("true"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setMadeCellHoly(true);
        }
        if (specialPowerActivation.equalsIgnoreCase("combo"))
        {
            minion.setAbleToCombo(true);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onTurn"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onTurn);
        }
        else if (specialPowerActivation.equalsIgnoreCase("passive"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.passive);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onAttack"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onAttack);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onSpawn"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onSpawn);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onDeath"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onDeath);
        }
        else if (specialPowerActivation.equalsIgnoreCase("onDefend"))
        {
            minion.getSpecialPower().getSpellEffect().getSpellChanges().get(0).setTimeToActivateSpecialPower(TimeToActivateSpecialPower.onDefend);
        }
        minion.setPrice(price);
        Account.loggedInAccount.getCollection().addCard(Account.loggedInAccount, minion, false);
        Shop.getInstance().addCardToShop(minion);
        Minion.getMinions().add(minion);
        //showOutput.printOutput("Custom card " + minion.getCardID() + " added to your collection");//todo
    }
}