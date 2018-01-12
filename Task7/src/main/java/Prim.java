public class Prim {
    public static void main(String[] args) {
        isPrim(11, 2);
    }

    public static boolean isPrim(int prim, int tasksToSplitIn){
        int analyzeUpTo = prim/2;
        int part_size = analyzeUpTo / tasksToSplitIn;
        for(int i = 0; (i+1) * part_size <= analyzeUpTo; i++){
            int start;
            int end = part_size*(i+1);
            if(i == 0){
                start = part_size*i+2;
            } else{
                start = part_size*i+1;
            }
            System.out.println(analyzeFromTo(start, end, prim));
        }
        return true;
    }

    public static boolean analyzeFromTo(int start, int end, int prim){
        for(int i = start; i <= end; i++){
            if(prim % i == 0){
                return false;
            }
        }
        return true;
    }
}
