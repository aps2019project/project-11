package Model;

import java.util.ArrayList;

public class Spell extends Card
{
    private static ArrayList<Spell> spells = new ArrayList<>();
    private String descriptionTypeOfSpell;
    private SpellEffect spellEffect = new SpellEffect();

    public Spell(String name, int price, int MP, String descriptionTypeOfSpell)
    {
        this.setCardName(name);
        this.setPrice(price);
        this.setRequiredMP(MP);
        this.setDescriptionTypeOfSpell(descriptionTypeOfSpell);
        spells.add(this);
        Shop.getInstance().addCardToShop(this);
    }

    public static void setSpells()
    {
        Spell spellOne = new Spell("totalDisarm",1000,0, "disarm");
        spellOne.getSpellEffect().addSpellChange( new SpellChange(0 , false , true  , 0 , 0 , 0 , false , true , false , 0,false , false,false,false,false,false,false,false));
        spellOne.getSpellEffect().addTarget(new Target(0 , 0 ,false , false , 1 , 0 , 0 , 0 , 0 , 0 ,  null,false,false,false));

        Spell spellTwo = new Spell("areaDispel",1500,2, "removingAllBadAndGoodBuffs" );
        spellTwo.getSpellEffect().addSpellChange(new SpellChange(1,true,false, 0,0,0,false,false,true,0,false,false,false,false,false,false,false,false));
        spellTwo.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,0, 0  ,  0 ,  0 ,null,false,false,false));

        Spell spellThree = new Spell("empower", 250, 1,"addingAP");
        spellThree.getSpellEffect().addSpellChange(new SpellChange(1,true,false,2,0,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellThree.getSpellEffect().addTarget(new Target(0,0,false,false,0,1,0,0,0,0,null,false,false,false));

        Spell spellFour = new Spell("fireball",400,1,"impactToOpponent");
        spellFour.getSpellEffect().addSpellChange(new SpellChange(1,false,false,0,-4,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellFour.getSpellEffect().addTarget(new Target(0,0,false,false,1,0,0,0,0,0,null,false,false,false));

        Spell spellFive = new Spell("godStrength",450,2,"addingAP");
        spellFive.getSpellEffect().addSpellChange(new SpellChange(1,true,false,4,0,0,false, false,false,0,false,false,false,false,false,false,false,false));
        spellFive.getSpellEffect().addTarget(new Target(0,0,true,false,0,0,0,0,0,0,null,false,false,false));

        Spell spellSix = new Spell("hellFire",600,3,"fieryHouse");
        spellSix.getSpellEffect().addSpellChange(new SpellChange(2,false,false,0,0,0,false,false,false,0,false,false,false,true,false,false,false,false));
        spellSix.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,1 ,  1 , 2  ,  2,null,false,false,false));

        Spell spellSeven = new Spell("lightingBolt",1250,2,"impactToOpponent");
        spellSeven.getSpellEffect().addSpellChange(new SpellChange(1,false,false,0,-8,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellSeven.getSpellEffect().addTarget(new Target(0,0,false,true,0,0,0,0,0,0,null,false,false,false));

        Spell spellEight = new Spell("poisonLake",900,5,"toxicHouse");
        spellEight.getSpellEffect().addSpellChange(new SpellChange(1,false,false,0,0,0,false,false,false,0,false,true,false,false,false,false,false,false));
        spellEight.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,  1 , 1 , 3  , 3  ,null,false,false,false));

        Spell spellNine = new Spell("madness",650,0,"addingAPButDisarm");
        spellNine.getSpellEffect().addSpellChange(new SpellChange(3,true,false,4,0,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellNine.getSpellEffect().addTarget(new Target(0,0,false,false,0,1,0,0,0,0,null,false,false,false));

        Spell spellTen = new Spell("allDisarm",2000,9,"disarm");
        spellTen.getSpellEffect().addSpellChange(new SpellChange(1,false,false,0,0,0,false,true,false,0,false,false,false,false,false,false,false,false));
        spellTen.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,0,0,0,0,null,false,true,false));

        Spell spellEleven = new Spell("allPoison",1500,8,"toxic");
        spellEleven.getSpellEffect().addSpellChange(new SpellChange(4,false,false,0,0,0,false,false,false,0,false,true,false,false,false,false,false,false));
        spellEleven.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,0,0,0,0,null,false,true,false));

        Spell spellTwelve = new Spell("dispel",1500,0,"...");
        spellTwelve.getSpellEffect().addSpellChange(new SpellChange(0,false,false,0,0,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellTwelve.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,0,0,0,0,null,false,false,false));

        Spell spellThirteen = new Spell("healthWithProfit",2250,0,"2hollyBuffFor3turn");
        spellThirteen.getSpellEffect().addSpellChange(new SpellChange(1,false,false,0,-6,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellThirteen.getSpellEffect().addSpellChange(new SpellChange(3,true,false,0,0,0,false,false,true,2,false,false,false,false,false,false,false,false));
        spellThirteen.getSpellEffect().addTarget(new Target(0,0,false,false,0,1,0,0,0,0,null,false,false,false));

        Spell spellFourteen = new Spell("powerUp",2500,2,"powerUp");
        spellFourteen.getSpellEffect().addSpellChange(new SpellChange(1,true,false,6,0,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellFourteen.getSpellEffect().addTarget(new Target(0,0,false,false,0,1,0,0,0,0,null,false,false,false));

        Spell spellFifteen = new Spell("allPower",2000,4,"addingAP");
        spellFifteen.getSpellEffect().addSpellChange(new SpellChange(0,true,true,2,0,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellFifteen.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,0,0,0,0,null,false,false,true));

        Spell spellSixteen = new Spell("allAttack",1500,4,"impactingToAllOpponent");
        spellSixteen.getSpellEffect().addSpellChange(new SpellChange(1,false,false,0,-6,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellSixteen.getSpellEffect().addTarget(new Target(0,0,false,false,0,0,0,0,0,0,null,false,true,false));

        Spell spellSeventeen = new Spell("weakening",1000,1,"weaknessBuff");
        spellSeventeen.getSpellEffect().addSpellChange(new SpellChange(1,false,false,-4,0,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellSeventeen.getSpellEffect().addTarget(new Target(0,1,false,false,0,0,0,0,0,0,null,false,false,false));

        Spell spellEighteen = new Spell("sacrifice",1600,2,"weaknessBuffAndPowerBuff");
        spellEighteen.getSpellEffect().addSpellChange(new SpellChange(1,true,false,8,0,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellEighteen.getSpellEffect().addSpellChange(new SpellChange(1,false,false,0,-6,0,false,false,false,0,false,false,false,false,false,false,false,false));
        spellEighteen.getSpellEffect().addTarget(new Target(1,0,false,false,0,0,0,0,0,0,null,false,false,false));

        Spell spellNineteen = new Spell("kingGuard",1750,9,"killingOpponentMinion");
        spellNineteen.getSpellEffect().addSpellChange(new SpellChange(1,false,false,0,0,0,false,false,false,0,false,false,false,false,false,true,false,false));
        spellNineteen.getSpellEffect().addTarget(new Target(0,1,false,false,0,0,0,0,0,0,null,false,false,false));

        Spell spellTwenty = new Spell("shock",1200,1,"stun");
        spellTwenty.getSpellEffect().addSpellChange(new SpellChange(2,false,false,0,0,0,true,false,false,0,false,false,false,false,false,false,false,false));
        spellTwenty.getSpellEffect().addTarget(new Target(0,1,false,false,1,0,0,0,0,0,null,false,false,false));
    }

    public String getDescriptionTypeOfSpell()
    {
        return descriptionTypeOfSpell;
    }

    public void setDescriptionTypeOfSpell(String descriptionTypeOfSpell)
    {
        this.descriptionTypeOfSpell = descriptionTypeOfSpell;
    }

    public SpellEffect getSpellEffect()
    {
        return spellEffect;
    }

    public static ArrayList<Spell> getSpells()

    {
        return spells;
    }

    public void printSpellCardStats(int counter)
    {
        System.out.println(counter + "- Type : Spell - Name : " + getCardName() + " - MP : " + getRequiredMP() + " – Description : " + getDescriptionTypeOfSpell() + " Sell Cost : " + getPrice());
    }

    public void printSpellCardStats()
    {
        System.out.println("Type : Spell - Name : " + getCardName() + " - MP : " + getRequiredMP() + " – Description : " + getDescriptionTypeOfSpell() + " Sell Cost : " + getPrice());
    }
}
