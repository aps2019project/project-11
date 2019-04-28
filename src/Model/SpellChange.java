package Model;

public class SpellChange
{
    private int turnsToApplyChange = 1;
    private boolean positiveChange = true;
    private boolean applyChangeUntilEndOfTheGame = false;
    private TimeToActivateSpecialPower timeToActivateSpecialPower;
    private int changeAP;
    private int changeHP;
    private int changeMP;
    private boolean stunOpponent = false;
    private boolean disarmOpponent = false;
    private boolean activateHolyBuff = false;
    private boolean madeCellToxic = false;
    private boolean madeCellHoly = false;
    private boolean madeCellFiery = false;

    public SpellChange(int turnsToApplyChange, boolean positiveChange, boolean applyChangeUntilEndOfTheGame, TimeToActivateSpecialPower timeToActivateSpecialPower, int changeAP, int changeHP, int changeMP, boolean stunOpponent, boolean disarmOpponent, boolean activateHolyBuff, boolean madeCellToxic, boolean madeCellHoly, boolean madeCellFiery) {
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
}
