import java.util.*;

/**
 * Class that control the game, and keep track of the player and dice classes
 *
 * @author Lliam Rankins
 * @author William Krajcovic
 * @author Shiv Patel
 * @author Micah Tucker
 */

public class Bunco {

    /**
    * Prompts the user with a series of questions
    * First question is the number of sides on
    * the dice are needed
    * Second question how many players in the game
    * Next if you want to see the dice being rolled
    * Also the next question is asking if the
    * players want to see there score for each round.
    *
    * @param args command-line parameters (not used)
    */
    public static void main(String[] args) {
        int numPlayers = -1;
        int diceSides = -1;
        int seed = -1;
        String continueRoundStr;
        String seeDiceStr;
        boolean seeDice;

        String seePointsStr;
        boolean seePoints;

        Player[] players;

        if (args.length >= 1) {
            try {
                seed = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Seed is not an Integer.");
            }
        } else {
            seed = -1;
        }

        // Set up scanner for console to read filename
        Scanner in = new Scanner(System.in);

        //Grabs the number of dice sides and assigns it to diceSides
        System.out.println("What sided die do you want to play with?");
        System.out.print("Please enter an integer (Range 6-26): ");

        while (diceSides < 6 || diceSides > 26) {
            while (!in.hasNextInt()) {
                in.next();
                System.out.print("Please enter an integer (Range 6-26): ");
            }

            diceSides = in.nextInt();

            if (diceSides < 6 || diceSides > 26) {
                System.out.print("Please enter an integer (Range 6-26): ");
            }
        }


        Dice dice = new Dice(seed, diceSides);
        System.out.println("");

        //Asks for number of players, and creates them in following steps.
        System.out.println("How many players are playing?");
        System.out.print("Please enter an integer (Range 2-10): ");

        while (numPlayers < 2 || numPlayers > 10) {
            while (!in.hasNextInt()) {
                in.next();
                System.out.print("Please enter an integer (Range 2-10): ");
            }

            numPlayers = in.nextInt();

            if (numPlayers < 2 || numPlayers > 10) {
                System.out.print("Please enter an integer (Range 2-10): ");
            }
        }

        System.out.println("");


        //Makes all the players
        players = makePlayers(in, numPlayers, dice);
        System.out.println("");

        //Asks if they want to see each roll of dice
        System.out.print("Do you wish to see the dice rolls(y/n): ");
        seeDiceStr = in.next();

        while (!seeDiceStr.toUpperCase().equals("Y") && !seeDiceStr.toUpperCase().equals("N")) {
            System.out.print("Please enter y or n: ");
            seeDiceStr = in.next();
        }

        if (seeDiceStr.toUpperCase().equals("Y"))    {
            seeDice = true;
        }
        else {
            seeDice = false;
        }
        System.out.println("");

        //Asks if they want to see points for players after each round
        System.out.print("Do you wish to see each players scores at the end of the round(y/n): ");
        seePointsStr = in.next();

        while (!seePointsStr.toUpperCase().equals("Y") && !seePointsStr.toUpperCase().equals("N")) {
            System.out.print("Please enter y or n: ");
            seePointsStr = in.next();
        }

        if (seePointsStr.toUpperCase().equals("Y"))    {
            seePoints = true;
        }
        else {
            seePoints = false;
        }
        System.out.println("");

        //Plays through the seprate rounds
        for (int i = 0; i < diceSides; i++) {
            doRound(players, i + 1, seePoints, seeDice);


            if (i != (diceSides - 1))  {

                System.out.print("Do you wish to play another round? (y/n)");
                continueRoundStr = in.next();

                while (!continueRoundStr.toUpperCase().equals("Y") && !continueRoundStr.toUpperCase().equals("N")) {
                    System.out.print("Please enter y or n: ");
                    continueRoundStr = in.next();
                }

                if (continueRoundStr.toUpperCase().equals("N"))    {
                    System.exit(0);
                }
            }
        }


        int indexOfWinner = 0;
        for (int i = 0; i < players.length; i++)    {
            if (players[i].getRoundsWon() > players[indexOfWinner].getRoundsWon()) {
                indexOfWinner = i;

            }
        }

        System.out.println("You have finished the game and "
                          + players[indexOfWinner].getName() + " has won the most rounds with "
                          + players[indexOfWinner].getRoundsWon() + " wins " );

        System.out.println("");
        System.out.println("");
    }

    /**
    * Assigns the dice withe the appropriate players
    *
    * @param in input from the user
    * @param numPlayers the number of players
    * @param dice object created to roll for the game
    * @return players objects
    */
    public static Player[] makePlayers(Scanner in, int numPlayers, Dice dice) {
        String name = "";

        Player[] players = new Player[numPlayers];
        in.nextLine();

        for (int i = 0; i < numPlayers; i++) {

            System.out.print("Player " + (i + 1) + " name: ");

            //Get to work for names with spaces First Last name situations

            name = in.nextLine();

            players[i] = new Player(dice, name);
        }

        return players;
    }

    /**
    * Rounds for each player
    *
    * @param players the names of players
    * @param round the current round of the game
    * @param seePoints shows the points after each round
    */
    public static void doRound(Player[] players, int round, boolean seePoints, boolean seeDice) {
        boolean doTurn = true;
        boolean someoneHasWon = false;

        //Runs until someone has run the round
        while (!someoneHasWon) {
            //Runs for each player having them roll until they can't
            for (int i = 0; i < players.length; i++) {
                //Runs until player doesnt get a dice combination that lets them roll again
                while (doTurn) {
                    players[i].doTurn(round);

                    if (seeDice) {
                        System.out.println(players[i].getName() + " rolled: " + Arrays.toString(players[i].getDiceRolls()));
                    }

                    //Checks if a current player won, and does accordingly
                    if (players[i].getTotalScore() >= 21)  {
                        players[i].wonRound();
                        doTurn = false;
                        someoneHasWon = true;
                        System.out.println("Congradulations " + players[i].getName() + " has won round " + round +  "!!!");
                        //Prints out points for each player if they said yes to it earlier.
                        if (seePoints)  {
                            System.out.println("");
                            System.out.println("Break down of game after round " + round + ":");

                            printScores(players);
                        }

                        break;
                    }

                    doTurn = players[i].doAnotherTurn();
                }

                if (someoneHasWon)  {
                    for (int j = 0; j < players.length; j++)  {
                        players[j].resetTotalScore();
                    }
                    break;
                }

                doTurn = true;
            }
        }
    }

    public static void printScores(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + ": ");
            System.out.println("Total Score: " + players[i].getTotalScore());
            System.out.println("Round Score: " + players[i].getLastRoundScore());
            System.out.println("Rounds Won: " + players[i].getRoundsWon());
            System.out.println("Little Buncos: " + players[i].getLitteBunco());
            System.out.println("Big Buncos: " + players[i].getBigBunco());

            System.out.println("");
        }
    }

}
