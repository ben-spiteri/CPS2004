import java.util.*;

public class Exchange {
    private ArrayList<String> coins = new ArrayList<String>();
    private ArrayList<Double> cPrice = new ArrayList<Double>();
    private ArrayList<Integer> cAmmount = new ArrayList<Integer>();

    private ArrayList<ArrayList<Object>> sellBook = new ArrayList<ArrayList<Object>>();
    

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
        
        initOrderBooks();

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

    public void initOrderBooks(){
        sellBook.add(new ArrayList<Object>(Arrays.asList("Solana", (int)10, (double)165)));
        sellBook.add(new ArrayList<Object>(Arrays.asList("Bitcoin", (int)40, (double)41000)));
        sellBook.add(new ArrayList<Object>(Arrays.asList("Ethereum", (int)10, (double)3100)));

        Collections.sort(sellBook, new Comparator<ArrayList<Object>>() {
            @Override
            public int compare(ArrayList<Object> o1, ArrayList<Object> o2) {
                
                if((double)o1.get(2) > (double)o2.get(2)){
                    return 1;
                }
                else if((double)o1.get(2) < (double)o2.get(2)){
                    return -1;
                }
                else{
                    return 0;
                }
            }               
        });    
    }

    public void printOrderBooks(){
        System.out.println("==================================================");
        System.out.println("Ask Book: " + sellBook);
        System.out.println("==================================================");
    }

    public void marketOrder(int choice, String c, int cQty){

        int coinI = coins.indexOf(c);

        if(coinI > -1){
            if(choice == 1){
                cAmmount.set(coinI, (cAmmount.get(coinI) - cQty)); 
            }
            else if(choice == 2){
                cAmmount.set(coinI, (cAmmount.get(coinI) + cQty));    
            }
        }
    }

    public int matchingEngine(String coin, double price){
        
        //looping through book to check if match exists
        for(int i = 0; i < sellBook.size(); i++){

            if(sellBook.get(i).get(0) .equals (coin) && (double)sellBook.get(i).get(2) == price){
                return i;
            }
        }
        
        return -1;
    }

    public int bidOrder(String coin, double price, int qty){


        int index = matchingEngine(coin, price);
        if(index > -1){
            
            if((int)sellBook.get(index).get(1) > qty){
                sellBook.get(index).set(1, ((int)sellBook.get(index).get(1) - qty));
                
                //returning -1 if buyers order was fulfilled
                return -1;
            }
            else{
                
                int purchased = (int)sellBook.get(index).get(1);

                sellBook.remove(index);

                return purchased;
            }
        }
        return -1;
    }

    public void askOrder(String coin, int qty, double price){
        ArrayList<Object> newListing = new ArrayList<Object>();
        newListing.add(coin);
        newListing.add(qty);
        newListing.add(price);

        sellBook.add(newListing);
    }
}
