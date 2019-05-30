package Model;

public enum BattleMode
{
    KILLING_ENEMY_HERO,
    KEEP_FLAG_FOR_6_TURNS,
    GATHERING_FLAGS;

    public static BattleMode getBattleMode(int customGameMode)
    {
        switch (customGameMode)
        {
            case 1:
                return BattleMode.KILLING_ENEMY_HERO;
            case 2:
                return BattleMode.KEEP_FLAG_FOR_6_TURNS;
            case 3:
                return BattleMode.GATHERING_FLAGS;
        }
        return null;
    }
}
