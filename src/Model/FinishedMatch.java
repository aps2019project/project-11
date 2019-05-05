package Model;

public class FinishedMatch
{
    private String opponentName;
    private MatchResult matchResult;
    private int time;

    public FinishedMatch(String opponentName, MatchResult matchResult, int time) {
        this.opponentName = opponentName;
        this.matchResult = matchResult;
        this.time = time;
    }

    public String getOpponentName()
    {
        return opponentName;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }
}

