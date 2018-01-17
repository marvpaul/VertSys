import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Prim prim = new Prim();

        System.out.println("Let's check some number to be prime numbers ^.^");
        for(int i = 0; i < 10000; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(1000, Integer.MAX_VALUE- Integer.MAX_VALUE/2);
            System.out.println(randomNum + " prim? --> " + prim.isPrim(randomNum, 4));
        }
    }
}
