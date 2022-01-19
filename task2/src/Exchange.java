import java.util.*;

public class Exchange {
    private ArrayList<String> coins = new ArrayList<String>();
    private ArrayList<Double> cPrice = new ArrayList<Double>();
    private ArrayList<Integer> cAmmount = new ArrayList<Integer>();

    public Exchange(){
        coins.add("Bitcoin");
        cPrice.add(40000.0);
        cAmmount.add(121);

        coins.add("Ethereum");
        cPrice.add(3000.0);
        cAmmount.add(500);

        coins.add("Dogecoin");
        cPrice.add(10.0);
        cAmmount.add(10000);

        coins.add("Cardano");
        cPrice.add(20.0);
        cAmmount.add(900);

        coins.add("Solana");
        cPrice.add(150.0);
        cAmmount.add(350);
    }

    public void printMarket(){

        System.out.println("==================================================");
        for(int i = 0; i < coins.size(); i++){
            System.out.println(coins.get(i) + " --- $" + cPrice.get(i) + " --- coins: " + cAmmount.get(i));
        }
        System.out.println("==================================================");
    }

    public double getCoinPrice(String coin){
        return cPrice.get(coins.indexOf(coin));
    }

    public int getCoinQuantity(String coin){
        return cAmmount.get(coins.indexOf(coin));
    }

    
    public int updateCoin(){
        //function to update a coin i.e. if it is sold or bought etc....
        return 0;
    }
}
