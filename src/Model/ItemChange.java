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

    public ItemChange(int turnsToApplyChange, boolean applyChangeUntilEndOfTheGame, int changeMP, int changeHP, int changeAP, boolean disarmOpponent, boolean activateHolyBuff, int numOfHolyBuffs, boolean activatePowerBuff, int numOfPowerBuff, boolean activateWeaknessBuff, int numOfWeaknessBuff, boolean activatePoisonBuff, int numOfPoisonBuff) {
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

    public int getTurnsToApplyChange() {
        return turnsToApplyChange;
    }

    public void setTurnsToApplyChange(int turnsToApplyChange) {
        this.turnsToApplyChange = turnsToApplyChange;
    }

    public boolean isApplyChangeUntilEndOfTheGame() {
        return applyChangeUntilEndOfTheGame;
    }

    public void setApplyChangeUntilEndOfTheGame(boolean applyChangeUntilEndOfTheGame) {
        this.applyChangeUntilEndOfTheGame = applyChangeUntilEndOfTheGame;
    }

    public int getChangeMP() {
        return changeMP;
    }

    public void setChangeMP(int changeMP) {
        this.changeMP = changeMP;
    }

    public int getChangeHP() {
        return changeHP;
    }

    public void setChangeHP(int changeHP) {
        this.changeHP = changeHP;
    }

    public int getChangeAP() {
        return changeAP;
    }

    public void setChangeAP(int changeAP) {
        this.changeAP = changeAP;
    }

    public boolean isDisarmOpponent() {
        return disarmOpponent;
    }

    public void setDisarmOpponent(boolean disarmOpponent) {
        this.disarmOpponent = disarmOpponent;
    }

    public boolean isActivateHolyBuff() {
        return activateHolyBuff;
    }

    public void setActivateHolyBuff(boolean activateHolyBuff) {
        this.activateHolyBuff = activateHolyBuff;
    }

    public int getNumOfHolyBuffs() {
        return numOfHolyBuffs;
    }

    public void setNumOfHolyBuffs(int numOfHolyBuffs) {
        this.numOfHolyBuffs = numOfHolyBuffs;
    }

    public boolean isActivatePowerBuff() {
        return activatePowerBuff;
    }

    public void setActivatePowerBuff(boolean activatePowerBuff) {
        this.activatePowerBuff = activatePowerBuff;
    }

    public int getNumOfPowerBuff() {
        return numOfPowerBuff;
    }

    public void setNumOfPowerBuff(int numOfPowerBuff) {
        this.numOfPowerBuff = numOfPowerBuff;
    }

    public boolean isActivateWeaknessBuff() {
        return activateWeaknessBuff;
    }

    public void setActivateWeaknessBuff(boolean activateWeaknessBuff) {
        this.activateWeaknessBuff = activateWeaknessBuff;
    }

    public int getNumOfWeaknessBuff() {
        return numOfWeaknessBuff;
    }

    public void setNumOfWeaknessBuff(int numOfWeaknessBuff) {
        this.numOfWeaknessBuff = numOfWeaknessBuff;
    }

    public boolean isActivatePoisonBuff() {
        return activatePoisonBuff;
    }

    public void setActivatePoisonBuff(boolean activatePoisonBuff) {
        this.activatePoisonBuff = activatePoisonBuff;
    }

    public int getNumOfPoisonBuff() {
        return numOfPoisonBuff;
    }

    public void setNumOfPoisonBuff(int numOfPoisonBuff) {
        this.numOfPoisonBuff = numOfPoisonBuff;
    }
}
