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

    private int pointsPlayer;

    private boolean doAnotherTurn;

    private Dice dice;

    private int[] rolls;

    public Player (Dice dice) {
        this.dice = dice;
        this.pointsPlayer = 0;
    }

    /**
     * goes the the process of a plauers turn, rolling dice,
     * and adding points to the players score as needed;
     *
     * @return boolean of
     *
     */
    public int doTurn(int round) {
        doAnotherTurn = false;

        this.rolls = dice.roll();

        if (checkBigBunco(round))    {
            doAnotherTurn = true;
        }
        else if (checkLittleBunco()) {
            doAnotherTurn = false;
        }
        else if (gotPoints(round)) {
            doAnotherTurn = true;
        }
        else {
            //Didn't roll Little Bunco and no dice were equal to round
            doAnotherTurn = false;
        }
    }

    private int gotPoints (int round)   {
        int counter = 0;
        for(int i = 0; i < this.rolls.length; i++) {
            if (rolls[i] == round) {
                counter += 1;
            }
        }

        if (counter != 0)   {
            this.pointsPlayer += counter;
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
}
