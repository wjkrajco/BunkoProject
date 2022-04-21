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

    /** Maximum sides allowed on dice */
    public static final int MAX_SIDES_ON_DICE = 26;

    /** Minimum sides allowed on dice */
    public static final int MIN_SIDES_ON_DICE = 6;

    /** Maximum allowed players */
    public static final int MAX_PLAYERS = 10;

    /** Minimum allowed players */
    public static final int MIN_PLAYERS = 2;

    /** Minimum score required to win Bunco */
    public static final int SCORE_REQUIRED_TO_WIN = 21;

    /**
    * Prompts the user with a series of questions
    * First question is the number of sides on
    * the dice being used
    * Second question how many players in the game
    * Next if you want to see the dice being rolled
    * Also the next question is asking if the
    * players want to see there score for each round.
    *
    * @param args args[0] used for dice seed
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

        while (diceSides < MIN_SIDES_ON_DICE || diceSides > MAX_SIDES_ON_DICE) {
            while (!in.hasNextInt()) {
                in.next();
                System.out.print("Please enter an integer (Range 6-26): ");
            }

            diceSides = in.nextInt();

            if (diceSides < MIN_SIDES_ON_DICE || diceSides > MAX_SIDES_ON_DICE) {
                System.out.print("Please enter an integer (Range 6-26): ");
            }
        }


        Dice dice = new Dice(seed, diceSides);
        System.out.println("");

        //Asks for number of players, and creates them in following steps.
        System.out.println("How many players are playing?");
        System.out.print("Please enter an integer (Range 2-10): ");

        while (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS) {
            while (!in.hasNextInt()) {
                in.next();
                System.out.print("Please enter an integer (Range 2-10): ");
            }

            numPlayers = in.nextInt();

            if (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS) {
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
            doRound(players, i + 1, seePoints, seeDice, in);


            if (i != (diceSides - 1))  {

                System.out.print("Do you wish to play another round? (y/n)");
                continueRoundStr = in.next();

                while (!continueRoundStr.toUpperCase().equals("Y") &&
                        !continueRoundStr.toUpperCase().equals("N")) {

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
            if (players[i].getTotalScore() > players[indexOfWinner].getTotalScore()) {
                indexOfWinner = i;

            }
        }

        System.out.println("You have finished the game and "
                          + players[indexOfWinner].getName() + " has won having earned "
                          + players[indexOfWinner].getTotalScore() + " points " );

        System.out.println("");
        System.out.println("");
    }

    /**
    * Creates an array of Players assigned with the correct dice object, as well
    * as asking for the names of each player
    *
    * @param in console input from the user
    * @param numPlayers the number of players
    * @param dice object for each player to roll for the game
    * @return Array of player objects
    */
    public static Player[] makePlayers(Scanner in, int numPlayers, Dice dice) {
        String name = "";

        Player[] players = new Player[numPlayers];
        in.nextLine();

        for (int i = 0; i < numPlayers; i++) {

            System.out.print("Player " + (i + 1) + " name: ");

            name = in.nextLine();

            players[i] = new Player(dice, name);
        }

        return players;
    }

    /**
    * Rounds for each player
    *
    * @param players array of player objects
    * @param round the current round of the game
    * @param seePoints shows the points after each round
    * @param seeDice if dice rolls should be shown
    * @param in A scanner that takes the input of whether
    * a user would like to roll again
    */
    public static void doRound(Player[] players, int round,
        boolean seePoints, boolean seeDice, Scanner in) {

        boolean doTurn = true;
        boolean someoneHasWon = false;
        String rollAgainStr = "";

        //Runs until someone has run the round
        while (!someoneHasWon) {
            //Runs for each player having them roll until they can't
            for (int i = 0; i < players.length; i++) {
                //Runs until player doesnt get a dice combination that lets them roll again
                while (doTurn) {
                    if (seeDice) {
                        System.out.print(players[i].getName() +
                            " It is your turn to roll, would you like to roll? (y/n): ");
                        rollAgainStr = in.next();

                        while (!rollAgainStr.toUpperCase().equals("Y") &&
                                !rollAgainStr.toUpperCase().equals("N")) {

                            System.out.print("Please enter y or n: ");
                            rollAgainStr = in.next();
                        }

                        if (rollAgainStr.toUpperCase().equals("N"))    {
                            System.exit(0);
                        }
                        players[i].doTurn(round);
                        System.out.println(players[i].getName() + " rolled: " +
                                            Arrays.toString(players[i].getDiceRolls()));
                        if (seePoints)      {
                            printScores(players);
                        }
                    }
                    else    {
                        players[i].doTurn(round);
                    }

                    //Checks if a current player won, and does accordingly
                    if (players[i].getTotalRoundScore() >= SCORE_REQUIRED_TO_WIN)  {
                        players[i].wonRound();
                        doTurn = false;
                        someoneHasWon = true;
                        System.out.println("Congratulations " + players[i].getName() +
                                            " has won round " + round +  "!!!");

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
                        players[j].resetTotalRoundScore();
                    }
                    break;
                }

                doTurn = true;
            }
        }
    }

    /**
     * Prints the scores, buncos, etc for each player in the players list
     *
     * @param players array of players for the current game
     */
    public static void printScores(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + ": ");
            System.out.println("Total Score: " + players[i].getTotalScore());
            System.out.println("Current Round Score: " + players[i].getTotalRoundScore());
            System.out.println("Rounds Won: " + players[i].getRoundsWon());
            System.out.println("Total Little Buncos: " + players[i].getLitteBunco());
            System.out.println("Total Big Buncos: " + players[i].getBigBunco());

            System.out.println("");
        }
    }

}
