import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests player class
 * @author Lliam Rankins
 * @author William Krajcovic
 * @author Shiv Patel
 * @author Micah Tucker
 */
 public class PlayerTest    {

     private String name = "Billy";

     private Player player;

     private Player playerOneTurn;

     private Player playerTwoTurns;

     private Dice diceDontNextTurn;

     private Dice diceDoNextTurn;

     @BeforeEach
     public void setUp()    {
         diceDontNextTurn = new Dice(1, 6);
         diceDoNextTurn = new Dice(2, 6);

         player = new Player(diceDontNextTurn, name);
         playerOneTurn = new Player(diceDontNextTurn, name);
         playerTwoTurns = new Player(diceDoNextTurn, name);

     }

     @Test
     public void testGetName()  {
         assertEquals(name, player.getName(), "Checking name getter method as Billy");
     }

     @Test
     public void testDoTurn()   {
         //Player who should not go again after first roll with given round number
         playerOneTurn.doTurn(1);
         assertFalse(playerOneTurn.doAnotherTurn());

         //Player who should go again after their first roll with given round number
         playerTwoTurns.doTurn(1);
         assertTrue(playerTwoTurns.doAnotherTurn());

     }


     @Test
     public void testCheckBigBunco() {
          int[] nonBigBuncoRolls = new int[] { 1,2,3 };
          int[] bigBuncoRollsRoundOne = new int[] { 1,1,1 };

          player.setRolls(nonBigBuncoRolls);
          assertFalse(player.checkBigBunco(1));

          player.setRolls(bigBuncoRollsRoundOne);
          assertFalse(player.checkBigBunco(2));

          player.setRolls(bigBuncoRollsRoundOne);
          assertTrue(player.checkBigBunco(1));
     }

     @Test
     public void testCheckLittleBunco() {
          int[] nonLittleBuncoRolls = new int[] { 1,2,3 };
          int[] littleBuncoRolls = new int[] { 2,2,2 };

          player.setRolls(nonLittleBuncoRolls);
          assertFalse(player.checkLittleBunco());

          player.setRolls(littleBuncoRolls);
          assertTrue(player.checkLittleBunco());
     }

     @Test
     public void testCheckOtherPoints() {
          int[] onePointOtherPointsRoll = new int[] { 1,2,3 };
          int[] twoPointOtherPointsRoll = new int[] { 1,1,2 };
          //No three point check, because Little bunco is checked for earlier
          int[] noPointOtherPointsRoll = new int[] { 2,2,3 };

          player.setRolls(onePointOtherPointsRoll);
          assertEquals(1, player.checkOtherPoints(1),
                      "Returns 1 for round one with the given rolls");

          player.setRolls(twoPointOtherPointsRoll);
          assertEquals(2, player.checkOtherPoints(1),
                      "Returns 2 for round one with the given rolls");

          player.setRolls(noPointOtherPointsRoll);
          assertEquals(0, player.checkOtherPoints(1),
                      "Returns 0 for round one with the given rolls");
     }
 }
