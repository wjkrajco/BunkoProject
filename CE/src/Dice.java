import java.util.Random;
import java.util.*;

/**
 * Class that will generate dice rolls
 *
 * @author Lliam Rankins
 * @author William Krajcovic
 * @author Shiv Patel
 * @author Micah Tucker
 */

public class Dice {

    /** Creates a final int for the number dice rolled */
    public static final int NUMBER_OF_DICE = 3;

    /** Creates a final int for the numbers on a dice */
    public static final int NUMBERS_ON_DICE = 6;

    /** Creates an int array of the numbers on the three dice rolled*/
    private int[] diceRolls;

    /** Creates the int variable for the seed */
    private int seed;

    /**
     * Dice constructor that takes in a seed for the
     * given game of Bunco
     */
    public Dice (int seed) {
        this.seed = seed;
    }

    /**
     * Rolls three dice valued 1-6, and returns them in a
     * int array of length 3.
     *
     * @return int array of the 3 dice rolls for the current player.
     */
    public int[] roll() {

        Random rand = new Random();
        if (seed != -1) {
            rand = new Random(this.seed);
        }

        diceRolls = new int[NUMBER_OF_DICE];

        for (int i = 0; i < diceRolls.length; i++) {
            diceRolls[i] = rand.nextInt(NUMBERS_ON_DICE) + 1;
        }

        return diceRolls;
    }
}
