package View;

import Controller.*;
import Model.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ShowOutput
{
    private static ShowOutput showOutput = null;
    private Group rootSignUpMenu = Main.getRootSignUpMenu();
    private Scene sceneSignUpMenu = Main.getSceneSignUpMenu();
    private Group rootLoginMenu = Main.getRootLoginMenu();
    private Scene sceneLoginMenu = Main.getSceneLoginMenu();
    private Group rootMainMenu = Main.getRootMainMenu();
    private Scene sceneMainMenu = Main.getSceneMainMenu();
    private Group rootLeaderBoard = Main.getRootLeaderBoard();
    private Scene sceneLeaderBoard = Main.getSceneLeaderBoard();
    private Group rootShop = Main.getRootShop();
    private ScrollPane scrollPaneShop = Main.getScrollPaneShop();
    private Scene sceneShop = Main.getSceneShop();
    private Group rootCollection = Main.getRootCollection();
    private ScrollPane scrollPaneCollection = Main.getScrollPaneCollection();
    private Scene sceneCollection = Main.getSceneCollection();
    private Group rootBattleMenu = Main.getRootBattle();
    private Scene sceneBattleMenu = Main.getSceneBattle();
    private Group rootSinglePlayer = Main.getRootSinglePlayer();
    private Scene sceneSinglePlayer = Main.getSceneSinglePlayer();
    private Group rootMultiPlayer = Main.getRootMultiPlayer();
    private Scene sceneMultiPlayer = Main.getSceneMultiPlayer();

    public ShowOutput()
    {
        //just added to make ShowOutput singleton
    }

    public static ShowOutput getInstance()
    {
        if (showOutput == null)
        {
            showOutput = new ShowOutput();
        }
        return showOutput;
    }

    public void showAlert(String text)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deck operations");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.getButtonTypes().clear();
        alert.showAndWait();
    }

    public void printOutput(String command)
    {
        System.out.println(command);
    }

    public void printMainMenuCommands()
    {
        printOutput("Enter Collection");
        printOutput("Enter Shop");
        printOutput("Enter Battle");
        printOutput("Save");
        printOutput("Logout");
        printOutput("Help");
        printOutput("Exit");
    }

    void showRankingPlayers()
    {
        int counter = 1;
        for (Account account : AccountManager.getAccounts())
        {
            if (counter > 10)
            {
                return;
            }
            Label labelPlayerName = new Label(counter + "- " + account.getAccountName());
            labelPlayerName.setFont(Font.font(15));
            labelPlayerName.relocate(25, counter * 50);
            rootLeaderBoard.getChildren().add(labelPlayerName);

            Label labelPlayerHighScore = new Label(Integer.toString(account.getNumOfWins()));
            labelPlayerHighScore.setFont(Font.font(15));
            labelPlayerHighScore.relocate(250, counter * 50);
            rootLeaderBoard.getChildren().add(labelPlayerHighScore);

            if (account.equals(Account.loggedInAccount))
            {
                labelPlayerName.setTextFill(Color.RED);
                labelPlayerHighScore.setTextFill(Color.RED);
            }

            counter++;
        }
    }

    public void accountHelp()
    {
        printOutput("create account [user name]");
        printOutput("login [user name]");
        printOutput("show leaderBoard");
        printOutput("help");
        printOutput("exit");
    }

    public void shopHelp()
    {
        printOutput("show collection");
        printOutput("search [item name | card name]");
        printOutput("search collection [item name | card name]");
        printOutput("buy [card name | item name]");
        printOutput("sell [card ID | item ID]");
        printOutput("show");
        printOutput("help");
        printOutput("exit");
    }

    public void collectionHelp()
    {
        printOutput("search [cardName | itemName]");
        printOutput("create deck [deckName]");
        printOutput("delete deck [deckName]");
        printOutput("add [cardID | heroID | itemID] to deck [deckName]");
        printOutput("remove [cardID | heroID | itemID] from deck [deckName]");
        printOutput("validate deck [deckName]");
        printOutput("select deck [deckName]");
        printOutput("show all decks");
        printOutput("show deck [deckName]");
        printOutput("save");
        printOutput("show");
        printOutput("help");
        printOutput("exit");
    }

    public void printHeroStats(Hero hero, int counter)
    {
        printOutput(counter + "- Name : " + hero.getCardName() + " - AP : " + hero.getDefaultAP() + " – HP : " + hero.getDefaultHP() + " – Class : " + hero.getImpactType() + " – Special power : " + hero.getSpecialPower().getDescriptionTypeOfSpecialPower() + " - Sell Cost : " + hero.getPrice());
    }

    public void showCollectionInfo(Collection collection)
    {
        int counter = 1;
        printOutput("Heroes :");
        for (Card card : collection.getCards())
        {
            if (card instanceof Hero)
            {
                printHeroStats((Hero) card, counter);
                counter++;
            }
        }

        counter = 1;
        printOutput("Items :");
        for (Item item : collection.getItems())
        {
            printItemStats(counter, item);
            counter++;
        }

        counter = 1;
        printOutput("Cards :");
        for (Card card : collection.getCards())
        {
            if (card instanceof Hero)
            {
                continue;
            }
            printCardStats(counter, card);
            counter++;
        }
    }

    public void showAllDecksInfo()
    {
        int counter = 1;
        Deck mainDeck = Account.loggedInAccount.getMainDeck();
        if (mainDeck != null)
        {
            printOutput(counter + "- " + mainDeck.getDeckName() + " : ");
            printDeckStats(mainDeck);
            counter++;
        }
        for (Deck deck : Account.loggedInAccount.getPlayerDecks())
        {
            if (mainDeck != null && deck.getDeckName().equals(mainDeck.getDeckName()))
            {
                continue;
            }
            printOutput(counter + "- " + deck.getDeckName() + " : ");
            printDeckStats(deck);
            counter++;
        }
    }

    public void showDeckInfo(String deckName)
    {
        Deck deck = DeckManager.findDeck(deckName);
        if (deck != null)
        {
            printDeckStats(deck);
        }
        else
        {
            printOutput("There is no deck with this name");
        }
    }

    public void printDeckStats(Deck deck)
    {
        int counter = 1;
        printOutput("Heroes :");
        for (Hero hero : deck.getHero())
        {
            printHeroStats(hero, counter);
            counter++;
        }

        counter = 1;
        printOutput("Items :");
        for (Item item : deck.getItem())
        {
            printItemStats(counter, item);
            counter++;
        }

        counter = 1;
        printOutput("Cards :");
        for (Card card : deck.getNonHeroCards())
        {
            printCardStats(counter, card);
            counter++;
        }
    }

    public void showGameInfo()
    {
        printOutput("First Player : " + Battle.getCurrentBattle().getFirstPlayer().getAccount().getAccountName());
        printOutput("Second Player : " + Battle.getCurrentBattle().getSecondPlayer().getAccount().getAccountName());
        printOutput("First Player MP : " + Battle.getCurrentBattle().getFirstPlayer().getMP());
        printOutput("Second Player MP : " + Battle.getCurrentBattle().getSecondPlayer().getMP());
        if (Battle.getCurrentBattle().getBattleMode() == BattleMode.KILLING_ENEMY_HERO)
        {
            printOutput("First Player Hero HP : " + Battle.getCurrentBattle().getFirstPlayer().getMainDeck().getHero().get(0).getCurrentHP());
            printOutput("Second Player Hero HP : " + Battle.getCurrentBattle().getSecondPlayer().getMainDeck().getHero().get(0).getCurrentHP());
        }
        else if (Battle.getCurrentBattle().getBattleMode() == BattleMode.KEEP_FLAG_FOR_6_TURNS)
        {

        }
        else if (Battle.getCurrentBattle().getBattleMode() == BattleMode.GATHERING_FLAGS)
        {

        }

    }

    public void showMyMinions()
    {
        for (NonSpellCard minion : Battle.getCurrentBattle().getPlayerTurn().getInsertedCards())
        {
            showMinionInfoInTheBattle((Minion) minion);
        }
        showOwnHero(Battle.getCurrentBattle().getPlayerTurn());
    }

    private void showOwnHero(Player player)
    {
        Hero hero = player.getMainDeck().getHero().get(0);
        showInformationOfCards(hero.getCardID(), hero.getCardName(), hero.getCurrentHP(), hero.getRow(), hero.getColumn(), hero.getCurrentAP(), hero);
    }

    private void showInformationOfCards(String cardID, String cardName, int currentHP, int row, int column, int currentAP, Hero hero)
    {
        //todo behine kardan
        printOutput(cardID + " : " + cardName + ", " + "health : " + currentHP + ", " + "location : " + "(" + row + ", " + column + "), " + "power : " + currentAP);
    }

    public void showOpponentMinions()
    {
        Player opponent;
        if (Battle.getCurrentBattle().getPlayerTurn() == Battle.getCurrentBattle().getFirstPlayer())
        {
            opponent = Battle.getCurrentBattle().getSecondPlayer();
        }
        else
        {
            opponent = Battle.getCurrentBattle().getFirstPlayer();
        }
        for (NonSpellCard minion : opponent.getInsertedCards())
        {
            showMinionInfoInTheBattle((Minion) minion);
        }
        Hero hero = opponent.getMainDeck().getHero().get(0);
        showInformationOfCards(hero.getCardID(), hero.getCardName(), hero.getCurrentHP(), hero.getRow(), hero.getColumn(), hero.getCurrentAP(), hero);
    }

    private void showMinionInfoInTheBattle(Minion minion)
    {
        showInformationOfCards(minion.getCardID(), minion.getCardName(), minion.getCurrentHP(), minion.getRow(), minion.getColumn(), minion.getCurrentAP(), null);
    }

    public void showCardInfo(String cardID)
    {
        Card card = Battle.getCurrentBattle().getPlayerTurn().getAccount().getCollection().findCardinCollection(cardID);
        if (card instanceof Hero)
        {
            Hero hero = (Hero) card;
            printOutput("Hero:");
            printOutput("Name: " + hero.getCardName());
            printOutput("Cost: " + hero.getPrice());
            if (hero.getSpecialPower() != null)
            {
                printOutput("Desc: " + hero.getSpecialPower().getDescriptionTypeOfSpecialPower());
            }
            else
            {
                printOutput("This hero has no special power");
            }

        }
        if (card instanceof Minion)
        {
            Minion minion = (Minion) card;
            printOutput("Minion: ");
            printOutput("Name: " + minion.getCardName());
            printOutput("HP: " + minion.getCurrentHP());
            printOutput("AP: " + minion.getCurrentAP());
            printOutput("MP: " + minion.getRequiredMP());
            printOutput("Range: " + minion.getImpactType());
            printOutput("Combo Ability: " + minion.getImpactType());
            printOutput("Cost: " + minion.getPrice());
            if (minion.getSpecialPower() != null)
            {
                printOutput("Special Power: " + minion.getSpecialPower().getDescriptionTypeOfSpecialPower());
            }
            else
            {
                printOutput("This minion has no special power");
            }
        }
        if (card instanceof Spell)
        {
            Spell spell = (Spell) card;
            printOutput("Spell: ");
            printOutput("Name: " + spell.getCardName());
            printOutput("MP: " + spell.getRequiredMP());
            printOutput("Cost: " + spell.getPrice());
            printOutput("Desc: " + spell.getDescriptionTypeOfSpell());
        }
    }

    public void showShopInfo()
    {
        printOutput("Heroes :");
        int counter = 1;
        for (Card card : Shop.getInstance().getCards())
        {
            if (card instanceof Hero)
            {
                printHeroStats((Hero) card, counter);
                counter++;
            }

        }
        printOutput("Items :");
        counter = 1;
        for (Item item : Shop.getInstance().getItems())
        {
            printItemStats(counter, item);
            counter++;
        }
        printOutput("Spells :");
        counter = 1;
        for (Card card : Shop.getInstance().getCards())
        {
            if (card instanceof Spell)
            {
                printSpellCardStats(counter, (Spell) card);
                counter++;
            }
        }
        printOutput("Minions :");
        counter = 1;
        for (Card card : Shop.getInstance().getCards())
        {
            if (card instanceof Minion)
            {
                printMinionStats(counter, (Minion) card);
                counter++;
            }
        }
    }

    public void printCardStats(int counter, Card card)
    {
        if (card instanceof Spell)
        {
            printSpellCardStats(counter, (Spell) card);
        }
        else if (card  instanceof Minion)
        {
            printMinionStats(counter, (Minion) card);
        }
    }

    public void printCardStats(Card card)
    {
        if (card instanceof Spell)
        {
            printSpellCardStats((Spell) card);
        }
        else if (card  instanceof Minion)
        {
            printMinionStats((Minion) card);
        }
    }

    public void printItemStats(int counter, Item item)
    {
        printOutput(counter + "- Name : " + item.getItemName() + " – Desc : " + item.getDescriptionTypeOfItem() + " – Sell Cost : " + item.getPrice());
    }

    public void printItemStats(Item item)
    {
        printOutput("Name : " + item.getItemName() + " – Desc : " + item.getDescriptionTypeOfItem() + " – Sell Cost : " + item.getPrice());
    }

    public void printSpellCardStats(int counter, Spell spell)
    {
        printOutput(counter + "- Type : Spell - Name : " + spell.getCardName() + " - MP : " + spell.getRequiredMP() + " – Description : " + spell.getDescriptionTypeOfSpell() + " Sell Cost : " + spell.getPrice());
    }

    public void printSpellCardStats(Spell spell)
    {
        printOutput("Type : Spell - Name : " + spell.getCardName() + " - MP : " + spell.getRequiredMP() + " – Description : " + spell.getDescriptionTypeOfSpell() + " Sell Cost : " + spell.getPrice());
    }

    public void printMinionStats(int counter, Minion minion)
    {
        if (minion.getSpecialPower() != null)
        {
            printOutput(counter + "- Type : Minion - Name : " + minion.getCardName() + " – Class : " + minion.getImpactType() + " - AP : " + minion.getDefaultAP() + " - HP : " + minion.getDefaultHP() + " - MP : " + minion.getRequiredMP() + " - Special power : " + minion.getSpecialPower().getDescriptionTypeOfSpecialPower() + " – Sell Cost : " + minion.getPrice());
        }
        else
        {
            printOutput(counter + "- Type : Minion - Name : " + minion.getCardName() + " – Class : " + minion.getImpactType() + " - AP : " + minion.getDefaultAP() + " - HP : " + minion.getDefaultHP() + " - MP : " + minion.getRequiredMP() + " – Sell Cost : " + minion.getPrice());
        }
    }

    public void printMinionStats(Minion minion)
    {
        if (minion.getSpecialPower() != null)
        {
            printOutput("- Type : Minion - Name : " + minion.getCardName() + " – Class : " + minion.getImpactType() + " - AP : " + minion.getDefaultAP() + " - HP : " + minion.getDefaultHP() + " - MP : " + minion.getRequiredMP() + " - Special power : " + minion.getSpecialPower().getDescriptionTypeOfSpecialPower() + " – Sell Cost : " + minion.getPrice());
        }
        else
        {
            printOutput("- Type : Minion - Name : " + minion.getCardName() + " – Class : " + minion.getImpactType() + " - AP : " + minion.getDefaultAP() + " - HP : " + minion.getDefaultHP() + " - MP : " + minion.getRequiredMP() + " – Sell Cost : " + minion.getPrice());
        }
    }

    public void showGraveYardCardInfo(String cardID)
    {
        Card card = Battle.getCurrentBattle().getPlayerTurn().findCardInGraveYard(cardID);
        if (card != null)
        {
            printCardStats(card);
        }
    }

    public void showAllCardsInTheGraveYard()
    {
        int counter = 1;
        printOutput("first Player Grave Yard :");
        for (Card card : Battle.getCurrentBattle().getFirstPlayer().getGraveYard().getCards())
        {
            printCardStats(counter, card);
            counter++;
        }
        counter = 1;
        printOutput("second Player Grave Yard :");
        for (Card card : Battle.getCurrentBattle().getSecondPlayer().getGraveYard().getCards())
        {
            printCardStats(counter, card);
            counter++;
        }
    }

    public void showNextCardInfo()
    {
        Card card = Battle.getCurrentBattle().getPlayerTurn().getHand().getNextCard();
        printCardStats(card);
    }

    public void showHand(Hand hand)
    {
        for (Card card : hand.getCards())
        {
            showCardInfo(card.getCardID());
        }
        showCardInfo(hand.getNextCard().getCardID());
    }

    public void showBattleMenuCommands()
    {
        printOutput("Single Player");
        printOutput("Multi Player");
    }

    public void showBattleModes()
    {
        printOutput("Modes:");
        printOutput("1- Killing Enemy Hero");
        printOutput("2- Keep flag for 6 turns");
        printOutput("3- Gathering Flags");
    }

    public void showCollectibleItems()
    {
        int counter = 1;
        for (Item item : Battle.getCurrentBattle().getPlayerTurn().getCollectibleItems())
        {
            printItemStats(counter, item);
            counter++;
        }
    }

    public void showStoryBattleInfo()
    {
        printOutput("1- Hero : Dave White - Mode : Kill enemy hero");
        printOutput("2- Hero : Zahak - Mode : Gather and hold flag for 6 turn");
        printOutput("3- Hero : Arash - Mode : Gather half of the flags");
    }

    public void showCustomGameInfo()
    {
        showAllDecksInfo();
        showBattleModes();
    }

    public void showMenuGraveYard()
    {
        printOutput("Show Cards");
        printOutput("Show Info [card ID]");
    }

    public void showMenuBattle()
    {
        printOutput("Game info");
        printOutput("Show my minions");
        printOutput("Show opponent minions");
        printOutput("Show card info [card id]");
        printOutput("Select [card id]");
        printOutput("Show hand");
        printOutput("Insert [card name] in ( x , y )");
        printOutput("End turn");
        printOutput("Show collectibles");
        printOutput("Select [collectible id]");
        printOutput("Show Next Card");
        printOutput("Enter graveyard");
        printOutput("Help");
        printOutput("End Game");
        printOutput("Surrender");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void showMenuAfterSelectItem()
    {
        printOutput("Show Info");
        printOutput("Use [0-9]+ [0-9]+");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void showMenuAfterSelectCard()
    {
        printOutput("Move To [0-9]+ , [0-9]+");
        printOutput("Attack [cardID]");
        printOutput("Attack combo [opponentCardID] [myCardID] ...");
        printOutput("Use special power( [0-9]+ [0-9]+ )");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void showMenuMultiPlayerMatchMode()
    {
        printOutput("Start MultiPlayer Game KillingEnemyHero");
        printOutput("Start MultiPlayer Game KeepFlagFor6Turns");
        printOutput("Start MultiPlayer Game GatheringFlags");
        printOutput("ShowMenu");
        printOutput("Exit");
    }

    public void showMenuSelectUserForMultiPlayerMatch()
    {
        printOutput("Select User [userName]");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void showMenuSinglePlayerMatch()
    {
        printOutput("Story");
        printOutput("Custom Game");
        printOutput("Show Menu");
        printOutput("Exit");
    }

    public void printMatrixValues(int[][] matrix, String command)
    {
        printOutput(command);
        for (int i = 0; i < 5; i++)
        {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 9; j++)
            {
                stringBuilder.append(matrix[i][j]).append(" ");
            }
            printOutput(stringBuilder.toString());
        }
    }
}
