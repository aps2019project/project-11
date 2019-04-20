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
    SHOW_DECK;

    public String username;
    public String cardOrItemName;
    public int cardOrItemID;
    public String deckName;
}