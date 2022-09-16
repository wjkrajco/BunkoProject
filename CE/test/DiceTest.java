import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Dice class
 * @author Lliam Rankins
 * @author William Krajcovic
 * @author Shiv Patel
 * @author Micah Tucker
 */
public class DiceTest {

    /** dice instance for testing */
    private Dice dice;

    /** Expected roll values after rolling seed 1 for the first time */
    private String expDiceRolls;

    /** Number of sides on a dice for creating dice */
    public static final int SIDES_ON_DICE = 6;

    /**
    * Sets up a set of dice to be used for testing, seed 1, SIDES_ON_DICE sides
    */
    @BeforeEach
    public void setUp()    {
        dice = new Dice(1, SIDES_ON_DICE);
        expDiceRolls = "[4, 5, 2]";
    }

     /**
      * Tests the get rolls method
      */
    @Test
    public void testGetRolls() {
        dice.roll();
        int[] diceRolls = dice.getDiceRolls();

        assertEquals(expDiceRolls, Arrays.toString(diceRolls),
                      "Dice Rolled with seed 1, giving [4, 5, 2]");
    }

    /**
     * tests the rolling method given the seed 1
     */
    @Test
    public void testRoll() {
        int[] diceRolls = dice.roll();

        assertEquals(expDiceRolls, Arrays.toString(diceRolls),
                      "Dice Rolled with seed 1, giving [4, 5, 2]");
    }
}
