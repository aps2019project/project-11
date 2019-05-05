package Model;

import java.util.ArrayList;

public enum CommandType
{
    CREATE_ACCOUNT,
    LOGIN,
    SHOW_LEADER_BOARD,
    HELP,
    EXIT,
    ENTER_SHOP,
    ENTER_COLLECTION,
    ENTER_BATTLE,
    LOGOUT,
    SAVE,
    SHOW_COLLECTION,
    SEARCH,
    SEARCH_COLLECTION,
    BUY,
    SELL,
    SHOW,
    CREATE_DECK,
    DELETE_DECK,
    ADD_TO_DECK,
    REMOVE_FROM_DECK,
    VALIDATE_DECK,
    SET_MAIN_DECK,
    SHOW_ALL_DECKS,
    SHOW_DECK,
    SHOW_NEXT_CARD,
    ENTER_GRAVEYARD,
    SHOW_INFO,
    SHOW_CARDS,
    HELP_BATTLE,
    SHOW_MENU,
    GAME_INFO,
    SHOW_MY_MINIONS,
    SHOW_OPPONENT_MINIONS,
    SHOW_CARD_INFO,
    SELECT,
    MOVE_TO,
    SHOW_HAND,
    INSERT_CARD,
    SINGLE_PLAYER,
    MULTI_PLAYER,
    STORY,
    CUSTOM_GAME,
    SELECT_USER,
    START_MULTI_PLAYER_GAME,
    COMBO_ATTACK,
    SHOW_COLLECTIBLES,
    SELECT_ITEM,
    SHOW_ITEM_INFO,
    USE_ITEM,
    NORMAL_ATTACK,
    USE_SPECIAL_POWER
    ;

    public String username;
    public String cardOrItemName;
    public String cardOrItemID;
    public String deckName;
    public String cardOrItemIDInGraveYard;
    public int rowOfTheCell;
    public int columnOfTheCell;
    public String multiPlayerMatchMode;
    public int numOfFlags;
    public String enemyCardIDForCombo;
    public ArrayList<String> cardIDsForComboAttack = new ArrayList<>();
    public String enemyCardIDForNormalAttack;
    public String deckNameForCustomGame;
    public int customGameMode;
    public int customGameFlagNumber;
    public  int storyGameMode;

}
