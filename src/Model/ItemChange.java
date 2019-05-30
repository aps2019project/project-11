package Model;

public class ItemChange
{
    private int turnsToApplyChange;
    private boolean applyChangeUntilEndOfTheGame;
    private int changeMP;
    private int changeHP;
    private int changeAP;
    private boolean disarmOpponent;
    private boolean activateHolyBuff;
    private int numOfHolyBuffs;
    private boolean activatePowerBuff;
    private int numOfPowerBuff;
    private boolean activateWeaknessBuff;
    private int numOfWeaknessBuff;
    private boolean activatePoisonBuff;
    private int numOfPoisonBuff;

    public ItemChange(int turnsToApplyChange, boolean applyChangeUntilEndOfTheGame, int changeMP, int changeHP, int changeAP, boolean disarmOpponent, boolean activateHolyBuff, int numOfHolyBuffs, boolean activatePowerBuff, int numOfPowerBuff, boolean activateWeaknessBuff, int numOfWeaknessBuff, boolean activatePoisonBuff, int numOfPoisonBuff)
    {
        this.turnsToApplyChange = turnsToApplyChange;
        this.applyChangeUntilEndOfTheGame = applyChangeUntilEndOfTheGame;
        this.changeMP = changeMP;
        this.changeHP = changeHP;
        this.changeAP = changeAP;
        this.disarmOpponent = disarmOpponent;
        this.activateHolyBuff = activateHolyBuff;
        this.numOfHolyBuffs = numOfHolyBuffs;
        this.activatePowerBuff = activatePowerBuff;
        this.numOfPowerBuff = numOfPowerBuff;
        this.activateWeaknessBuff = activateWeaknessBuff;
        this.numOfWeaknessBuff = numOfWeaknessBuff;
        this.activatePoisonBuff = activatePoisonBuff;
        this.numOfPoisonBuff = numOfPoisonBuff;
    }

    public void applyItemChange(NonSpellCard nonSpellCard)
    {
        if (this.getTurnsToApplyChange() == 0 && !this.isApplyChangeUntilEndOfTheGame())
        {
            return;
        }
        if (this.isDisarmOpponent())
        {
            nonSpellCard.setCounterAttackAble(false);
        }
        if (this.isActivateHolyBuff())
        {
            nonSpellCard.setNumOfHolyBuffs(this.getNumOfHolyBuffs());
        }
        if (this.isActivatePoisonBuff())
        {
            nonSpellCard.setCurrentHP(nonSpellCard.getCurrentHP() - this.getNumOfPoisonBuff());
        }
        if (this.isActivatePowerBuff())
        {
            nonSpellCard.setCurrentAP(nonSpellCard.getCurrentAP() + this.getNumOfPowerBuff());
        }
        if (this.isActivateWeaknessBuff())
        {
            nonSpellCard.setCurrentAP(nonSpellCard.getCurrentAP() - this.getNumOfWeaknessBuff());
        }
        nonSpellCard.setCurrentHP(nonSpellCard.getCurrentHP() + this.getChangeHP());
        nonSpellCard.setCurrentAP(nonSpellCard.getCurrentAP() + this.getChangeAP());
        nonSpellCard.setRequiredMP(nonSpellCard.getRequiredMP() + this.getChangeMP());
        this.setTurnsToApplyChange(this.getTurnsToApplyChange() - 1);
    }

    public void applyItemChange(Player player)
    {
        player.increaseMPViaItem(this.getChangeMP());
    }

    public int getTurnsToApplyChange()
    {
        return turnsToApplyChange;
    }

    public void setTurnsToApplyChange(int turnsToApplyChange)
    {
        this.turnsToApplyChange = turnsToApplyChange;
    }

    public boolean isApplyChangeUntilEndOfTheGame()
    {
        return applyChangeUntilEndOfTheGame;
    }

    public int getChangeMP()
    {
        return changeMP;
    }

    public int getChangeHP()
    {
        return changeHP;
    }

    public int getChangeAP()
    {
        return changeAP;
    }

    public boolean isDisarmOpponent()
    {
        return disarmOpponent;
    }

    public boolean isActivateHolyBuff()
    {
        return activateHolyBuff;
    }

    public int getNumOfHolyBuffs()
    {
        return numOfHolyBuffs;
    }

    public boolean isActivatePowerBuff()
    {
        return activatePowerBuff;
    }

    public int getNumOfPowerBuff()
    {
        return numOfPowerBuff;
    }

    public boolean isActivateWeaknessBuff()
    {
        return activateWeaknessBuff;
    }

    public int getNumOfWeaknessBuff()
    {
        return numOfWeaknessBuff;
    }

    public boolean isActivatePoisonBuff()
    {
        return activatePoisonBuff;
    }

    public int getNumOfPoisonBuff()
    {
        return numOfPoisonBuff;
    }
}
