import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int NR_THREADS = 4;

    /**
     * The entry point for application
     * @param args nothing
     */
    public static void main(String[] args) {
        System.out.println("Let's check some number to be prime numbers ^.^");

        //Create 10.000 random numbers and check if the numbers are primes
        for(int i = 0; i < 10000; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(1000, Integer.MAX_VALUE- Integer.MAX_VALUE/2);
            System.out.println(randomNum + " prim? --> " + Prim.isPrim(randomNum, NR_THREADS));
        }
    }
}
