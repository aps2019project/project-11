package Model;

public class SpellChange
{
    private int turnsToApplyChange;
    private boolean positiveChange;
    private boolean applyChangeUntilEndOfTheGame;
    private boolean removeOpponentPositiveBuffs;
    private boolean removeOwnNegativeBuffs;
    private TimeToActivateSpecialPower timeToActivateSpecialPower;
    private int changeAP;
    private int changeHP;
    private int changeMP;
    private boolean stunOpponent;
    private boolean disarmOpponent;
    private boolean activateHolyBuff;
    private boolean madeCellToxic;
    private boolean madeCellHoly;
    private boolean madeCellFiery;

    public SpellChange(int turnsToApplyChange, boolean positiveChange, boolean applyChangeUntilEndOfTheGame, TimeToActivateSpecialPower timeToActivateSpecialPower, int changeAP, int changeHP, int changeMP, boolean stunOpponent, boolean disarmOpponent, boolean activateHolyBuff, boolean madeCellToxic, boolean madeCellHoly, boolean madeCellFiery)
    {
        this.turnsToApplyChange = turnsToApplyChange;
        this.positiveChange = positiveChange;
        this.applyChangeUntilEndOfTheGame = applyChangeUntilEndOfTheGame;
        this.timeToActivateSpecialPower = timeToActivateSpecialPower;
        this.changeAP = changeAP;
        this.changeHP = changeHP;
        this.changeMP = changeMP;
        this.stunOpponent = stunOpponent;
        this.disarmOpponent = disarmOpponent;
        this.activateHolyBuff = activateHolyBuff;
        this.madeCellToxic = madeCellToxic;
        this.madeCellHoly = madeCellHoly;
        this.madeCellFiery = madeCellFiery;
    }

    public int getTurnsToApplyChange() {
        return turnsToApplyChange;
    }

    public void setTurnsToApplyChange(int turnsToApplyChange) {
        this.turnsToApplyChange = turnsToApplyChange;
    }

    public boolean isPositiveChange() {
        return positiveChange;
    }

    public void setPositiveChange(boolean positiveChange) {
        this.positiveChange = positiveChange;
    }

    public boolean isRemoveOpponentPositiveBuffs() {
        return removeOpponentPositiveBuffs;
    }

    public void setRemoveOpponentPositiveBuffs(boolean removeOpponentPositiveBuffs) {
        this.removeOpponentPositiveBuffs = removeOpponentPositiveBuffs;
    }

    public boolean isRemoveOwnNegativeBuffs() {
        return removeOwnNegativeBuffs;
    }

    public void setRemoveOwnNegativeBuffs(boolean removeOwnNegativeBuffs) {
        this.removeOwnNegativeBuffs = removeOwnNegativeBuffs;
    }

    public boolean isApplyChangeUntilEndOfTheGame() {
        return applyChangeUntilEndOfTheGame;
    }

    public void setApplyChangeUntilEndOfTheGame(boolean applyChangeUntilEndOfTheGame) {
        this.applyChangeUntilEndOfTheGame = applyChangeUntilEndOfTheGame;
    }

    public TimeToActivateSpecialPower getTimeToActivateSpecialPower() {
        return timeToActivateSpecialPower;
    }

    public void setTimeToActivateSpecialPower(TimeToActivateSpecialPower timeToActivateSpecialPower) {
        this.timeToActivateSpecialPower = timeToActivateSpecialPower;
    }

    public int getChangeAP() {
        return changeAP;
    }

    public void setChangeAP(int changeAP) {
        this.changeAP = changeAP;
    }

    public int getChangeHP() {
        return changeHP;
    }

    public void setChangeHP(int changeHP) {
        this.changeHP = changeHP;
    }

    public int getChangeMP() {
        return changeMP;
    }

    public void setChangeMP(int changeMP) {
        this.changeMP = changeMP;
    }

    public boolean isStunOpponent() {
        return stunOpponent;
    }

    public void setStunOpponent(boolean stunOpponent) {
        this.stunOpponent = stunOpponent;
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

    public boolean isMadeCellToxic() {
        return madeCellToxic;
    }

    public void setMadeCellToxic(boolean madeCellToxic) {
        this.madeCellToxic = madeCellToxic;
    }

    public boolean isMadeCellHoly() {
        return madeCellHoly;
    }

    public void setMadeCellHoly(boolean madeCellHoly) {
        this.madeCellHoly = madeCellHoly;
    }

    public boolean isMadeCellFiery() {
        return madeCellFiery;
    }

    public void setMadeCellFiery(boolean madeCellFiery) {
        this.madeCellFiery = madeCellFiery;
    }
}
