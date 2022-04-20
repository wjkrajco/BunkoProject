import static org.junit.jupiter.api.Assertions.*;

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

    /** Name for the current player */
    private String name = "Billy";

    /** player object used for big/little bunco and other point checks */
    private Player player;

    /** Player object for testing that they only get one turn, seed 1*/
    private Player playerOneTurn;

    /** Player object that rolls a set of dice that allows them to play again, seed 2 */
    private Player playerTwoTurns;

    /** Dice that dont allow for a next turn on first roll, seed 1 */
    private Dice diceDontNextTurn;

    /** Dice that do allow for a next turn on first roll, seed 2 */
    private Dice diceDoNextTurn;

    /**
     * Sets up the dice for players, and initilizes the players
     */
    @BeforeEach
    public void setUp()    {
        diceDontNextTurn = new Dice(1, 6);
        diceDoNextTurn = new Dice(2, 6);

        player = new Player(diceDontNextTurn, name);
        playerOneTurn = new Player(diceDontNextTurn, name);
        playerTwoTurns = new Player(diceDoNextTurn, name);

    }

    /**
     * Tests the get name method
     */
    @Test
    public void testGetName()  {
        assertEquals(name, player.getName(), "Checking name getter method as Billy");
    }

    /**
     * Tests the doTurn function, based on if they get to go again or not
     * Not exaustive, but effective way to test
     */
    @Test
    public void testDoTurn()   {
        //Player who should not go again after first roll with given round number
        playerOneTurn.doTurn(1);
        assertFalse(playerOneTurn.doAnotherTurn());

        //Player who should go again after their first roll with given round number
        playerTwoTurns.doTurn(1);
        assertTrue(playerTwoTurns.doAnotherTurn());

    }

    /**
     * Tests the check big bunco method, making use of player.setRolls
     */
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

    /**
     * Tests the check little bunco method, making use of player.setRolls
     */
    @Test
    public void testCheckLittleBunco() {
        int[] nonLittleBuncoRolls = new int[] { 1,2,3 };
        int[] littleBuncoRolls = new int[] { 2,2,2 };

        player.setRolls(nonLittleBuncoRolls);
        assertFalse(player.checkLittleBunco());

        player.setRolls(littleBuncoRolls);
        assertTrue(player.checkLittleBunco());
    }

    /**
     * Tests the check other points method, making use of player.setRolls
     */
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
