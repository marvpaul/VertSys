import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    static Scanner sc;
    static FetchAPI api;
    public static void main(String[] args) {
        //Entry point
        sc = new Scanner(System.in);
        api = new FetchAPI();
        printMainMenu();
    }

    /**
     * Print out the main menu and handles user input
     */
    public static void printMainMenu(){
        System.out.println("Main menu");
        System.out.println("1 - search for a coin");
        System.out.println("2 - list 100 most common coins");
        System.out.println("4 - exit");

        switch (getInput()){
            case 1:
                searchCoin();
                break;
            case 2:
                listMostCommonCoins();
                break;
            case 4:
                System.exit(0);
            default:
                printMainMenu();
        }
    }

    /**
     * Try to request the most common coins on coinmarketcap and save to coin list
     */
    public static void listMostCommonCoins(){
        JSONArray response = api.getRequest("?limit=100");
        List<Coin> coins = new ArrayList<Coin>();
        if (response != null) {
            for(int i = 0; i < response.length(); i++){
                coins.add(new Coin(response.getJSONObject(i)));
            }
        } else{
            System.err.println("Ooops, failed to complete request!");
        }
        System.out.println("All coins parsed.");
        printCoins(coins, 0, 4);
    }

    /**
     * Method to handle printing out some coins
     * @param coins a list of coins
     * @param from start coin index
     * @param to end coin index
     */
    private static void printCoins(List<Coin> coins, int from, int to){
        for(int i = from; i <= to; i++){
            System.out.println(coins.get(i));
        }

        //Show dialogue
        System.out.println("1 - view next 5 coins");
        System.out.println("2 - view previous 5 coins");
        System.out.println("3 - main menu");

        //Process user input
        switch (getInput()){
            case 1:
                if(to + 5 < coins.size()){
                    printCoins(coins, from+5, to+5);
                } else{
                    System.err.println("End of data reached!");
                    printCoins(coins, from, to);
                }
                break;
            case 2:
                if(from -5 >= 0){
                    printCoins(coins, from-5, to-5);
                } else {
                    System.err.println("Beginning of data reached!");
                    printCoins(coins, from, to);
                }
                break;
            case 3:
                printMainMenu();
                break;
            default:
                System.err.println("Not a valid input");
                printCoins(coins, from, to);
        }
    }

    /**
     * Creates dialogue which asks the user to enter a coin name
     * Then perform a request for the certain coin and print out the coin data to console
     * in case the coin exists at coinmarketcap
     */
    private static void searchCoin(){
        System.out.println("Search for coin");
        System.out.println("Please enter a coin to search for on coinmarketcap.com");
        String query = sc.nextLine();
        JSONArray response = api.getRequest(query);
        if(response != null){
            JSONObject resultJSON = response.getJSONObject(0);
            Coin result = new Coin(resultJSON);
            System.out.println(result);
        } else{
            System.err.println("Ooops, coin not found!");
        }

        printMainMenu();
    }

    /**
     * Helper method which tries to parse an input string to integer
     * @return -1 in case parsing wasn't successful. Otherwise the parsed number as an int
     */
    private static int getInput(){
        String input = sc.nextLine();
        try{
            return Integer.parseInt(input);
        } catch (Exception e){
            return -1;
        }
    }
}
