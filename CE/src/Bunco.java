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

    public static void main(String[] args) {
        int numPlayers;
        int diceSides = -1;
        int seed;
        String seeDiceStr;
        boolean seeDice;

        String seePointsStr;
        boolean seePoints;

        Players[] players;

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
        System.out.println("What sided die do you want to play with? (Range 6-26)");

        while(diceSides < 6 || diceSides > 26)    {
            while(!in.hasNextInt()) {
                System.out.print("Please enter an integer (Range 6-26): ");
            }
            diceSides = in.nextInt();
        }

        Dice dice = new Dice(seed, diceSides);

        //Asks for number of players, and creates them in following steps.
        System.out.println("How many players are playing? (Range 2-10) ");

        while (numPlayers < 2 || numPlayers > 10)    {
            while(!in.hasNextInt()) {
                System.out.print("Please enter an integer (Range 2-10): ");
            }
            numPlayers = in.nextInt();
        }

        players = makePlayers(in, numPlayers, dice);

        //Asks if they want to see each roll of dice
        System.out.print("Do you wish to see the dice rolls(y/n): ");
        seeDiceStr = in.next();

        while (!seeDiceStr.toUpperCase().equals("Y") && !seeDiceStr.toUpperCase().equals("N") {
            System.out.print("Please enter y or n: ");
            seeDiceStr = in.next();
        }
        if (seeDiceStr.equals("Y"))    {
            seeDice = true;
        }
        else {
            seeDice = false;
        }

        //Asks if they want to see points for players after each round
        System.out.print("Do you wish to see each players scores/counts at the end of the round(y/n): ");
        seePointsStr = in.next();

        while (!seePointsStr.toUpperCase().equals("Y") && !seePointsStr.toUpperCase().equals("N") {
            System.out.print("Please enter y or n: ");
            seePointsStr = in.next();
        }
        if (seePointsStr.equals("Y"))    {
            seePoints = true;
        }
        else {
            seePoints = false;
        }

        for (int i = 0; i < diceSides; i++) {
            doRound(players, i + 1);
        }
    }




    public Players[] makePlayers(Scanner in, int numPlayers, Dice dice) {
        String name;

        Players[] players = new Players[numPlayers];

        for (int i = 0; i < numPlayers; i++) {

            System.out.print("Player " + (i + 1) + " name: ");
            name = in.next();

            players[i] = new Player(dice, name);
        }

        return players;
    }

    public void doRound(Players[] players, int round) {
        for (int i = 0; i < players.length; i++) {
            players[i].doTurn(round);
        }

        System.out.println("Break down of round:");
        if (seePoints)  {
            for (int i = 0; i < players.length; i++) {
                System.out.println(players[i].getName() + ": ");
                System.out.println("Total Score: " + player[i].getTotalScore());
                System.out.println("Round Score: " + player[i].getCurrentScore());
                System.out.println("Number of Rounds Won: ")
            }
        }


    }

    public void gameOver() {

    }
}
