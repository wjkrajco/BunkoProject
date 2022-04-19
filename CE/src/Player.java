import java.util.*;

/**
 * Class that will represent a player
 *
 * @author Lliam Rankins
 * @author William Krajcovic
 * @author Shiv Patel
 * @author Micah Tucker
 */

public class Player {

    /** Creates a final int the points from a big bunco */
    public static final int POINTS_FOR_BIG_BUNCO = 5;

    /** Creates a final int the points from a big bunco */
    public static final int POINTS_FOR_LITLE_BUNCO = 3;

    /** Total score for Player in Bunco */
    private int totalScore;

    /** Current score for Player in Bunco */
    private int currentScore;

    /** Points for Big Bunco in the game */
    private int numBigBunco;

    /** Points for Little Bunco in the game */
    private int numLittleBunco;

    /** Total number of Rounds won by player */
    private int numRoundsWon;

    /** Name of Player */
    private String name;

    /** Another turn for player */
    private boolean doAnotherTurn;

    /** Dice object created for game */
    private Dice dice;

    /** Amount of times the dice rolls */
    private int[] rolls;

    private int lastRoundScore;

    /**
    * Player method including dice and name
    *
    * @param dice object of dice being used
    * @param name name of players
    *
    */
    public Player (Dice dice, String name) {
        this.dice = dice;
        this.name = name;
        this.totalScore = 0;
        this.currentScore = 0;
        this.lastRoundScore = 0;
    }

    /**
     * Goes the the process of a plauers turn, rolling dice,
     * and adding points to the players score as needed;
     */
    public void doTurn(int round) {
        doAnotherTurn = false;

        this.rolls = dice.roll();

        if (checkBigBunco(round))    {
            currentScore += POINTS_FOR_BIG_BUNCO;
            doAnotherTurn = true;
            numBigBunco ++;

        }
        else if (checkLittleBunco()) {
            currentScore += POINTS_FOR_LITLE_BUNCO;
            numLittleBunco++;

            doAnotherTurn = false;
            totalScore += currentScore;
            lastRoundScore = currentScore;
            currentScore = 0;
        }
        else {
            int otherPoints = checkOtherPoints(round);

            if (otherPoints >= 1) {
                currentScore += otherPoints;
                doAnotherTurn = true;
            }
            else {
                doAnotherTurn = false;
                totalScore += currentScore;
                lastRoundScore = currentScore;
                currentScore = 0;
            }
        }


    }



    /**
    * Checks if the dice and see if it returns a single number
    *
    * @param round the palyers individual round
    * @return counter checks if dice returns a single number
    */
    private int checkOtherPoints(int round) {
        int counter = 0;
        for(int i = 0; i < this.rolls.length; i++) {
            if (rolls[i] == round) {
                counter += 1;
            }
        }

        return counter;
    }

    private boolean checkLittleBunco() {
        for(int i = 0; i < this.rolls.length; i++) {
            if (this.rolls[i] != this.rolls[0]) {
                return false;
            }
        }

        return true;
    }

    private boolean checkBigBunco(int round) {

        for(int i = 0; i < this.rolls.length; i++) {
            if (this.rolls[i] != round) {
                return false;
            }
        }

        return true;
    }

    /**
    *  Total score for Player in Bunco
    *
    * @return totalScore for player
    */
    public int getTotalScore() {
        return totalScore;
    }

    /**
    * Last Round Score for each Player in Bunco
    *
    * @return lastRoundScore for player
    */
    public int getLastRoundScore() {
        return lastRoundScore;
    }

    /**
    * Name for each Player
    *
    * @return name for Player
    */
    public String getName() {
        return name;
    }

    /**
    * The number of Rounds won byplayer
    *
    * @return numRoundsWon number of rounds
    * for each player
    */
    public int getRoundsWon() {
        return numRoundsWon;
    }

    /**
    * Getting little bunco within the game
    *
    * @return numLittleBunco
    */
    public int getLitteBunco() {
        return numLittleBunco;
    }

    /**
    * Getting big bunco within the game
    *
    * @return numBigBunco
    */
    public int getBigBunco() {
        return numBigBunco;
    }

    /**
    * The winner of each round
    */
    public void wonRound() {
        numRoundsWon++;
    }

    /**
    * The next turn within the game
    */
    public boolean doAnotherTurn() {
        return doAnotherTurn;
    }

    public int[] getDiceRolls() {
        return this.rolls;
    }
}
