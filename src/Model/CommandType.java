package Model;

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
    MOVE_TO
    ;


    public String username;
    public String cardOrItemName;
    public int cardOrItemID;
    public String deckName;
    public int cardOrItemIDInGraveYard;
    public int rowOfTheHouse;
    public int columnOfTheHouse;
}
