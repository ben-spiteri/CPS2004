import java.util.*;

public class Trader extends User {    
    Scanner human = new Scanner(System.in);
    
    private double balance;
    private ArrayList<Integer> coinC = new ArrayList<Integer>();
    private ArrayList<String> coinT = new ArrayList<String>();
    
    public Trader(int accountID){
        super("Trader", accountID);
    }

    public void initBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double change){
        balance = balance + change;
    }

    public int getCoinQ(String coin){
        return coinC.get(coinT.indexOf(coin));
    }

    public int addCoin(String coin, int ammount){
        int position;

        coinC.add(ammount);
        coinT.add(coin);

        position = coinT.size();
        return position;
    }

    public void updateWallet(String coin, int amount){
        int index = coinT.indexOf(coin);

        if(index > -1){
            coinC.set(index, (coinC.get(index) + amount));
        }
        else{
            coinT.add(coin);
            coinC.add(amount);
        }
    }

    public int traderMenu(){
        
        int input = 0;
        do{
            System.out.println("1. View My Wallet");
            System.out.println("2. View Market");
            System.out.println("3. Market Order");
            System.out.println("4. Limit Order");
            System.out.println("5. Print Profile");
            System.out.println("6. View Order Book");
            System.out.println("7. Logout/Quit App");
    
            try {
                input = human.nextInt();
            } 
            catch (Exception e) {
                human.nextLine();
                System.out.println("please only enter a number");
            }
        }while(input < 1 || input > 7);

        return input;
    }


    public void printProfile(){
        System.out.println("==================================================");
        System.out.println("User ID: " + getID());
        System.out.println("Balance: " + getBalance());
        
        for(int i = 0; i < coinC.size(); i++){
            System.out.println(coinT.get(i) + " - " + coinC.get(i) + " coins");
        }
        System.out.println("==================================================");
    }

    public void printWallet(){
        System.out.println("User Wallet=======================================");
        for(int i = 0; i < coinC.size(); i++){
            System.out.println(coinT.get(i) + " - " + coinC.get(i) + " coins");
        }
        System.out.println("==================================================");

    }

}
