import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Dice class
 * @author Lliam Rankins
 * @author William Krajcovic
 * @author Shiv Patel
 * @author Micah Tucker
 */
 public class DiceTest    {

     /** dice instance for testing */
     private Dice dice;

     private String expDiceRolls;

     public static final int SIDES_OF_DICE = 6;



     @BeforeEach
     public void setUp()    {
         dice = new Dice(1, 6);
         expDiceRolls = "[4, 5, 2]";
     }




     @Test
     public void testGetRolls() {
          dice.roll();
          int[] diceRolls = dice.getDiceRolls();

          assertEquals(expDiceRolls, Arrays.toString(diceRolls), "Dice Rolled with seed 1, giving [4, 5, 2]");
     }

     @Test
     public void testRoll() {
         int[] diceRolls = dice.roll();

         assertEquals(expDiceRolls, Arrays.toString(diceRolls), "Dice Rolled with seed 1, giving [4, 5, 2]");
     }
}
