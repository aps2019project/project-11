package Network;

public enum ClientCommandEnum
{
    SIGN_UP,
    LOGIN,
    LOGOUT,
    MAKE_CUSTOM_SPELL,
    MAKE_CUSTOM_MINION,
    MAKE_CUSTOM_HERO,
    GET_ALL_ACCOUNTS,
    SAVE_ACCOUNT_INFO,
    BUY,
    SELL,
    IMPORT_DECK,
    EXPORT_DECK,
    CREATE_DECK,
    REMOVE_CARD_FROM_DECK,
    ADD_CARD_TO_DECK,

    MAKE_STORY_BATTLE
    ,MAKE_CUSTOM_BATTLE
    ,MAKE_MULTI_PLAYER_BATTLE
    ,SET_BATTLEFIELD_PANES_AND_GRIDPANE
    ,SET_NEXT_CARD_PANE
    ,GET_INSTANCE_OF_SHOW_OUTPUT
    ,SET_PLAYERS_NAME
    ,TASKS_WHEN_SURRENDER
    ,GET_PLAYER_TURN_GRAVE_YARD_CARDS
    , CLEAR_HAND_PANES_IMAGE_VIEW_AND_END_TURN_AND_SET_HAND_ICON
    ,SET_HEROES_FIRST_PLACE
    ,SET_HERO_ICONS
    ,SET_HAND_ICONS
    ,SET_GRID_PANE
    , GET_PLAYER_DECKS
    ,SET_MP_ICONS
    ,GET_ONLINE_ACCOUNTS
    ,SEND_MESSAGE
    ,GET_ALL_MESSAGES_IN_CHAT
    ;
}