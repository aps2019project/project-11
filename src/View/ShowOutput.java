package View;

import Model.*;

public class ShowOutput
{
    private static ShowOutput showOutput = null;

    private ShowOutput()
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

    public String getGameInfo(Battle battle)
    {
        StringBuilder str = new StringBuilder("");
        str.append("First Player : ").append(battle.getFirstPlayer().getAccount().getAccountName()).append("\n");
        str.append("Second Player : ").append(battle.getSecondPlayer().getAccount().getAccountName()).append("\n");
        str.append("First Player MP : ").append(battle.getFirstPlayer().getMP()).append("\n");
        str.append("Second Player MP : ").append(battle.getSecondPlayer().getMP()).append("\n");
        if (battle.getBattleMode() == BattleMode.KILLING_ENEMY_HERO)
        {
            str.append("First Player Hero HP : ").append(battle.getFirstPlayer().getMainDeck().getHero().get(0).getCurrentHP()).append("\n");
            str.append("Second Player Hero HP : ").append(battle.getSecondPlayer().getMainDeck().getHero().get(0).getCurrentHP()).append("\n");
        }
        else if (battle.getBattleMode() == BattleMode.KEEP_FLAG_FOR_6_TURNS)
        {

        }
        else if (battle.getBattleMode() == BattleMode.GATHERING_FLAGS)
        {

        }

        return str.toString();
    }

    private void showInformationOfCards(String cardID, String cardName, int currentHP, int row, int column, int currentAP, Hero hero)
    {
        printOutput(cardID + " : " + cardName + ", " + "health : " + currentHP + ", " + "location : " + "(" + row + ", " + column + "), " + "power : " + currentAP);
    }

    public String showCardInfoString(Card card)
    {
        StringBuilder str = new StringBuilder("");
        if (card instanceof Hero)
        {
            Hero hero = (Hero) card;
            str.append("Hero:\n" + "Name: ").append(hero.getCardName()).append("\n").append("Cost: ").append(hero.getPrice());
            if (hero.getSpecialPower() != null)
            {
                str.append("Desc: ").append(hero.getSpecialPower().getDescriptionTypeOfSpecialPower());
            }
            else
            {
                str.append("This hero has no special power");
            }
        }
        if (card instanceof Minion)
        {
            Minion minion = (Minion) card;
            str.append("Minion: ");
            str.append("Name: ").append(minion.getCardName()).append("\n");
            str.append("HP: ").append(minion.getDefaultHP()).append("\n");
            str.append("AP: ").append(minion.getDefaultAP()).append("\n");
            str.append("MP: ").append(minion.getRequiredMP()).append("\n");
            str.append("Range: ").append(minion.getImpactType()).append("\n");
            str.append("Combo Ability: ").append(minion.getImpactType()).append("\n");
            str.append("Cost: ").append(minion.getPrice()).append("\n");
            if (minion.getSpecialPower() != null)
            {
                str.append("Special Power: ").append(minion.getSpecialPower().getDescriptionTypeOfSpecialPower()).append("\n");
            }
            else
            {
                str.append("This minion has no special power").append("\n");
            }
        }
        if (card instanceof Spell)
        {
            Spell spell = (Spell) card;
            str.append("Spell: ").append("\n");
            str.append("Name: ").append(spell.getCardName()).append("\n");
            str.append("MP: ").append(spell.getRequiredMP()).append("\n");
            str.append("Cost: ").append(spell.getPrice()).append("\n");
            str.append("Desc: ").append(spell.getDescriptionTypeOfSpell()).append("\n");
        }
        return str.toString();
    }

    public String showCardInfoInBattleString(Card card)
    {
        System.out.println(card.getCardName());
        StringBuilder str = new StringBuilder("");
        if (card instanceof Hero)
        {
            Hero hero = (Hero) card;
            str.append("Hero:\n" + "Name: ").append(hero.getCardName()).append("\n");
            str.append("HP: ").append(hero.getCurrentHP()).append("\n");
            str.append("AP: ").append(hero.getCurrentAP()).append("\n");
            if (hero.getSpecialPower() != null)
            {
                str.append("Desc: ").append(hero.getSpecialPower().getDescriptionTypeOfSpecialPower());
            }
            else
            {
                str.append("This hero has no special power");
            }
        }
        if (card instanceof Minion)
        {
            Minion minion = (Minion) card;
            str.append("Minion: ");
            str.append("Name: ").append(minion.getCardName()).append("\n");
            str.append("HP: ").append(minion.getCurrentHP()).append("\n");
            str.append("AP: ").append(minion.getCurrentAP()).append("\n");
            str.append("Range: ").append(minion.getImpactType()).append("\n");
            str.append("Combo Ability: ").append(minion.getImpactType()).append("\n");
            if (minion.getSpecialPower() != null)
            {
                str.append("Special Power: ").append(minion.getSpecialPower().getDescriptionTypeOfSpecialPower()).append("\n");
            }
            else
            {
                str.append("This minion has no special power").append("\n");
            }
        }
        return str.toString();
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

    public void showStoryBattleInfo()
    {
        printOutput("1- Hero : Dave White - Mode : Kill enemy hero");
        printOutput("2- Hero : Zahak - Mode : Gather and hold flag for 6 turn");
        printOutput("3- Hero : Arash - Mode : Gather half of the flags");
    }

    public void showCustomGameInfo()
    {
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
