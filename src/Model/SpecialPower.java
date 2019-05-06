package Model;

import java.util.ArrayList;

public class SpecialPower
{
    private String descriptionTypeOfSpecialPower;
    private SpellEffect spellEffect = new SpellEffect();
    private ArrayList<Target> targets = new ArrayList<>();
    private ArrayList<SpellChange> spellChanges = new ArrayList<>();

    public SpecialPower(String descriptionTypeOfSpecialPower)
    {
        this.descriptionTypeOfSpecialPower = descriptionTypeOfSpecialPower;
    }

    public void applySpecialPower(int x, int y)
    {
        for (int i = 0 ;i<this.getSpellEffect().getTargets().size(); i++)
        {
            Target target = this.getSpellEffect().getTargets().get(i);
            SpellChange spellChange = this.getSpellEffect().getSpellChanges().get(i);

            if (target.isOwnHero())
            {
                Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0).addActiveSpellOnThisCard(spellChange);
            }
            else if (target.isOpponentHero())
            {
                Hero opponentHero = Battle.getCurrentBattle().getOpponentHero();
                opponentHero.addActiveSpellOnThisCard(spellChange);
            }
            else if (target.isAllOpponentNonSpellCards())
            {
                Hero opponentHero = Battle.getCurrentBattle().getOpponentHero();
                opponentHero.addActiveSpellOnThisCard(spellChange);
                ArrayList<Minion> opponentMinions= Battle.getCurrentBattle().getOpponentMinions();
                for (Minion opponentMinion : opponentMinions)
                {
                    opponentMinion.addActiveSpellOnThisCard(spellChange);
                }
            }
            else if (target.isAllOwnBothNonSpellCards())
            {
                Hero ownHero = Battle.getCurrentBattle().getPlayerTurn().getMainDeck().getHero().get(0);
                ownHero.addActiveSpellOnThisCard(spellChange);
                ArrayList<Minion> ownMinions = Battle.getCurrentBattle().getPlayerTurn().getInsertedCards();
                for (Minion ownMinion: ownMinions)
                {
                    ownMinion.addActiveSpellOnThisCard(spellChange);
                }
            }
            else if (target.isAllOwnMinion())
            {
                ArrayList<Minion> ownMinions = Battle.getCurrentBattle().getPlayerTurn().getInsertedCards();
                for (Minion ownMinion : ownMinions)
                {
                    ownMinion.addActiveSpellOnThisCard(spellChange);
                }
            }
            else if (target.getNumOfOwnMinions() > 0)
            {
                ArrayList<Minion> ownMinions = Battle.getCurrentBattle().getPlayerTurn().getInsertedCards();
                for (int j=0; j< target.getNumOfOwnMinions(); j++)
                {
                    ownMinions.get(j).addActiveSpellOnThisCard(spellChange);
                }

            }
            else if (target.getNumOfOpponentBothNonSpellCards() > 0)
            {
                ArrayList<NonSpellCards> oppnentBothNonSpellCards = Battle.getCurrentBattle().findingOpponentNonSpellCards();
                for (int counter = 0; counter < target.getNumOfOpponentBothNonSpellCards(); counter++)
                {
                    oppnentBothNonSpellCards.get(counter).addActiveSpellOnThisCard(spellChange);
                }
            }
            else if (target.getNumOfOpponentMinions() > 0)
            {
                ArrayList<Minion> opponentMinions = Battle.getCurrentBattle().getOpponentMinions();
                for (int counter = 0; counter<target.getNumOfOpponentMinions(); counter++)
                {
                    opponentMinions.get(counter).addActiveSpellOnThisCard(spellChange);
                }
            }
            else if (target.getNumOfOwnBothNonSpellCards() > 0)
            {
                ArrayList<NonSpellCards> ownBothNonSpellCards = Battle.getCurrentBattle().findingOwnNonSpellCards();
                for (int counter = 0 ;counter <target.getNumOfOwnBothNonSpellCards() ; counter++)
                {
                    ownBothNonSpellCards.get(counter).addActiveSpellOnThisCard(spellChange);
                }
            }
            else if (target.getStartRow() >= 0 || target.getStartColumn() >= 0 || target.getEndRow() >=0 || target.getEndColumn() >=0)
            {
                //todo
            }
            else if (target.getMaxAttackRange() > 0)
            {
                /// TODO
            }
            else if (target.getNextNoneSpellCard()!= null)
            {

            }

        }
    }
    public String getDescriptionTypeOfSpecialPower()
    {
        return descriptionTypeOfSpecialPower;
    }

    public void setDescriptionTypeOfSpecialPower(String descriptionTypeOfSpecialPower)
    {
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
