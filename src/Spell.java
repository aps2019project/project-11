import java.util.ArrayList;

public class Spell extends Card
{
    enum targetCommunities
    {
        opponentForce, twoInTwoSquare, ownForce, ownHero, opponentHero, threeInThreeSquare, allOpponentForces, anyForce, allOwnForces, allOpponentForesInColumn, opponentMinion, ownMinion, opponentMinionAroundOwnHero
    }
    private targetCommunities targetCommunity;

    enum effects
    {
        toxicCell, fieryCell, holyCell, holyBuff, powerBuff, poisonBuff, WeaknessBuff, stunBuff, disarmBuff
    }
    private effects effect;

    private static ArrayList<Spell> spells = new ArrayList<>();

    private int effectMultiplicity;

    public Spell(String name, int price, int mana, targetCommunities targetCommunity, effects effect, int effectMultiplicity)
    {
        //todo
        this.effect = effect;
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


}
