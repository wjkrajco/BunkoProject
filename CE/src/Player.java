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

    private int totalScore;

    private int currentScore;

    private int numBigBunco;

    private int numLittleBunco;

    private int numRoundsWon;

    private String name;

    private boolean doAnotherTurn;

    private Dice dice;

    private int[] rolls;

    public Player (Dice dice, String name) {
        this.dice = dice;
        this.name = name;
        this.totalScore = 0;
        this.currentScore = 0;
    }

    /**
     * goes the the process of a plauers turn, rolling dice,
     * and adding points to the players score as needed;
     *
     * @return boolean of
     *
     */
    public boolean doTurn(int round) {
        doAnotherTurn = false;
        currentScore = 0;

        this.rolls = dice.roll();

        if (checkBigBunco(round))    {
            currentScore = POINTS_FOR_BIG_BUNCO;
            doAnotherTurn = true;
            numBigBunco += 1;

        }
        else if (checkLittleBunco()) {
            currentScore = POINTS_FOR_LITLE_BUNCO;
            doAnotherTurn = false;
            numLittleBunco++;
        }
        else if (gotPoints(round)) {
            //Current points added in gotPoints
            doAnotherTurn = true;
        }
        else {
            //Didn't roll Little Bunco and no dice were equal to round
            currentScore = 0;
            doAnotherTurn = false;
        }

        totalScore += currentScore;

        return doAnotherTurn;
    }

    private boolean gotPoints (int round)   {
        int counter = 0;
        for(int i = 0; i < this.rolls.length; i++) {
            if (rolls[i] == round) {
                counter += 1;
            }
        }
        if (counter != 0)   {
            this.pointsPlayer += counter;
            currentScore = counter;
            return true;
        }

        return false;

    }

    private boolean checkLittleBunco() {
        for(int i = 0; i < this.rolls.length; i++) {
            if (this.rolls[i] != this.rolls[0]) {
                this.playerPoints += POINTS_FOR_LITLE_BUNCO;
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

    public int getTotalScore() {
        return totalScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public String getName() {
        return name;
    }

    public int getRoundsWon() {
        return numRoundsWon;
    }

    public void wonRound() {
        numRoundsWon++;
    }
}
