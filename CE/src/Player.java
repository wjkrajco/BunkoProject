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

    /** Creates a final int the points from a little bunco */
    public static final int POINTS_FOR_LITLE_BUNCO = 3;

    /** Total score for Player in Bunco */
    private int totalScore;

    /** Current score for Player in Bunco */
    private int currentScore;

    /** number of Big Bunco in the game */
    private int numBigBunco;

    /** number of Little Bunco in the game */
    private int numLittleBunco;

    /** Total number of Rounds won by player */
    private int numRoundsWon;

    /** Name of Player */
    private String name;

    /** if the player should have another turn of not */
    private boolean doAnotherTurn;

    /** Dice object created for game */
    private Dice dice;

    /** The dice values for the last roll */
    private int[] rolls;

    /** the score for the last round */
    private int lastRoundScore;

    /**
    * Player constructor including dice and name
    *
    * @param dice object for dice being used
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
    *
    * @param round the current round for the game
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
    * Checks the dice roll and sees if any of them rolled the same number as the round number.
    *
    * @param round the current round of the game
    * @return counter number of dice that matched the round number
    */
    public int checkOtherPoints(int round) {
      int counter = 0;
      for(int i = 0; i < this.rolls.length; i++) {
        if (rolls[i] == round) {
          counter += 1;
        }
      }

      return counter;
    }

    /**
    * Checks the current dice to see if theyre all the same number thus a little buncos
    *
    * @return true if all three dice have the same value
    */
    public boolean checkLittleBunco() {
      for(int i = 0; i < this.rolls.length; i++) {
        if (this.rolls[i] != this.rolls[0]) {
          return false;
        }
      }

      return true;
    }

    /**
    * Checks the current dice to see if all three match the current round
    *
    * @param round the current round the game is on
    * @return true if all three dice match the round number
    */
    public boolean checkBigBunco(int round) {

      for(int i = 0; i < this.rolls.length; i++) {
        if (this.rolls[i] != round) {
          return false;
        }
      }

      return true;
    }

    /**
    * Total score for Player in Bunco
    *
    * @return totalScore for player
    */
    public int getTotalScore() {
      return totalScore;
    }

    /**
    *  Sets player's total score to zero
    */
    public void resetTotalScore() {
      totalScore = 0;
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
    * Numbers of little buncos for current player
    *
    * @return numLittleBunco
    */
    public int getLitteBunco() {
      return numLittleBunco;
    }

    /**
    * Numbers of Big buncos for current player
    *
    * @return numBigBunco
    */
    public int getBigBunco() {
      return numBigBunco;
    }

    /**
    * Increments the number numRoundsWon for current player
    */
    public void wonRound() {
      numRoundsWon++;
    }

    /**
    * returns the current players statis if they should do another turn or not
    *
    * @return doAnotherTurn
    */
    public boolean doAnotherTurn() {
      return doAnotherTurn;
    }

    /**
    * returns the array of dice rolls for the last turn
    *
    * @return this.rolls
    */
    public int[] getDiceRolls() {
      return this.rolls;
    }

    /**
    * Sets the dice rolls of the current player,
    * USED EXCLUSIVLY FOR TESTING, NO OTHER PURPOSE
    *
    * @param newRolls the integer set of dice rolls to have the players dice rolls set to
    */
    public void setRolls(int[] newRolls) {
      this.rolls = newRolls;
    }
}
