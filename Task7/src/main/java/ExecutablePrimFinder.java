import java.util.concurrent.Callable;

public class ExecutablePrimFinder implements Callable<Boolean> {

    private int from, to, prim;

    /**
     * Constructor which takes some arguments to check if a certain number is a prim
     * @param from check from from
     * @param to check to to
     * @param prim the number to check if it's a prim
     */
    public ExecutablePrimFinder(int from, int to, int prim) {
        this.from = from;
        this.prim = prim;
        this.to = to;
    }

    public Boolean call() throws Exception {
        System.out.println("Thread number: " + Thread.currentThread().getId() + " begins to work!");
        for(int i = from; i <= to; i++){
            if(prim % i == 0){
                return false;
            }
        }
        return true;
    }
}
