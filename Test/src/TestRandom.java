import java.util.Random;
import java.util.*;

public class TestRandom {

    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a seed for the dice roll");
        int seed = -1;
        seed = in.nextInt();
        Random rand = new Random(seed);
        for (int i = 0; i < 79; ++i)
          System.out.print(rand.nextInt(6) + 1);
        }

}
