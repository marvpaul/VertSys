import org.json.JSONObject;

/**
 * Represents a coin with data offers from coinmarketcap
 */
public class Coin {
    private float price_usd, price_btc, percent_change_24h, percent_change_7d;
    private int rank;
    private String name, symbol;

    /**
     * Constructor which creates an coin object from an JSONObj
     * @param obj
     */
    public Coin(JSONObject obj){
        try{
            this.price_usd = Float.parseFloat(obj.get("price_usd").toString());
        } catch (NumberFormatException e){
            this.price_usd = 0;
        }
        try{
            this.price_btc = Float.parseFloat(obj.get("price_btc").toString());
        } catch (NumberFormatException e){
            this.price_btc = 0;
        }
        try{
            this.percent_change_24h = Float.parseFloat(obj.get("percent_change_24h").toString());
        } catch (NumberFormatException e){
            this.percent_change_24h = 0;
        }
        try{
            this.percent_change_7d = Float.parseFloat(obj.get("percent_change_7d").toString());
        } catch (NumberFormatException e){
            this.percent_change_7d = 0;
        }
        try{
            this.price_usd = Float.parseFloat(obj.get("price_usd").toString());
        } catch (NumberFormatException e){
            this.price_usd = 0;
        }
        try{
            this.rank = Integer.parseInt(obj.get("rank").toString());
        } catch (NumberFormatException e){
            this.rank = 0;
        }
        this.name = obj.get("name").toString();
        this.symbol = obj.get("symbol").toString();
    }

    public float getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(float price_usd) {
        this.price_usd = price_usd;
    }

    public float getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(float price_btc) {
        this.price_btc = price_btc;
    }

    public float getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(float percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public float getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(float percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return  Colors.ANSI_GREEN + "rank : " + rank +
                "\nname: " + name +
                "\nsymbol: " + symbol +
                "\n price in usd: " + price_usd +
                "\nprice in btc: " + price_btc +
                "\npercent changed in 24 hours: " + percent_change_24h +
                "\npercent changed in 7 days: " + percent_change_7d + Colors.ANSI_RESET + "\n";
    }
}
