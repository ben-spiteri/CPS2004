import java.util.*;




public class Main{

    static private ArrayList<Integer> userID = new ArrayList<Integer>();
    static private ArrayList<String> password = new ArrayList<String>();
    static private ArrayList<Double> balance = new ArrayList<Double>();

    
    static private ArrayList<Integer> requesteduserID = new ArrayList<Integer>();
    static private ArrayList<String> requestedpassword = new ArrayList<String>();
    static private ArrayList<Double> requestedbalance = new ArrayList<Double>();


    static private void initUsers(){

        //id and password for 1st user
        userID.add(101);
        password.add("a");
        balance.add(150000.0);

        //id and password for 2nd user
        userID.add(102);
        password.add("b");
        balance.add(2000000.0);
    }

    static private void initUserAccount(int id, Trader myUser){
        if(id == 101){

            myUser.initBalance(balance.get(0));

            myUser.addCoin("Bitcoin", 2);
            myUser.addCoin("Dogecoin", 21);
            myUser.addCoin("Ethereum", 16);

        }
        if(id == 102){
            myUser.initBalance(balance.get(1));

            myUser.addCoin("Bitcoin", 5);
            myUser.addCoin("Dogecoin", 11);
            myUser.addCoin("Ethereum", 38);
        }
        else{
            int otherID = userID.indexOf(id);

            myUser.initBalance(balance.get(otherID-1));
        }
    }

    static private int userLogin(){
        Scanner human = new Scanner(System.in);

        int tries = 3;

        while(tries > 0){
            System.out.println("Enter User id");
            int ID = human.nextInt();

            int loc = userID.indexOf(ID);
            if(loc > -1){

                System.out.println("Enter Password");
                human.nextLine();
                String pass = human.nextLine();

                if(pass .equals (password.get(loc))){
                    
                    return ID;
                }
            }
            System.out.println("Incorrect username and password");
            tries--;
            System.out.println("Tries remaining: " + tries);

        }

        return -1;
    }


    static private ArrayList<Integer> adminID = new ArrayList<Integer>();
    static private ArrayList<String> adminPassword = new ArrayList<String>();

    static private void initAdmins(){
        adminID.add(123);
        adminPassword.add("abc");
    }

    static private int adminLogin(){
        Scanner human = new Scanner(System.in);

        int tries = 3;

        while(tries > 0){
            System.out.println("Enter Admin id");
            int ID = human.nextInt();

            int loc = adminID.indexOf(ID);
            if(loc > -1){

                System.out.println("Enter Password");
                human.nextLine();
                String pass = human.nextLine();

                if(pass .equals (adminPassword.get(loc))){
                    return ID;
                }
            }
            System.out.println("Incorrect username and password");
            tries--;
            System.out.println("Tries remaining: " + tries);

        }

        return -1;
    }

    //main function
    public static void main(String[] args){
        Scanner human = new Scanner(System.in);
        
        Exchange myExchange = new Exchange();
        
        initUsers();
        initAdmins();

        int input = 0;

        do{
            do{

                System.out.println("1. Trader Login");
                System.out.println("2. Admin Login");
                System.out.println("3. Register");
                System.out.println("4. quit");
    
                try {
                    input = human.nextInt();
                } 
                catch (Exception e) {
                    human.nextLine();
                    System.out.println("please only enter a number");
                }
            }while (input > 4 || input < 1);
    
    
            if(input == 1){
                
                int accID = (int)userLogin();

                if(accID > -1){
    
                    Trader myTrader = new Trader(accID);
                    
                    initUserAccount(accID, myTrader);


                    int i = 0;
                    do{
                        if(i == 1){
                            myTrader.printWallet();
                        }
                        else if(i == 2){
                            myExchange.printMarket();
                        }
                        else if(i == 3){
                            int input3 = 0;
                            do{
                                System.out.println("1. Buy");
                                System.out.println("2. Sell");
                                System.out.println("3. Quit");
    
                                try {
                                    input3 = human.nextInt();
                                } 
                                catch (Exception e) {
                                    human.nextLine();
                                    System.out.println("please only enter a number");
                                }
                            }while(input3 < 1 || input3 > 3);
    
    
                            if(input3 != 3){
                                System.out.println("Enter Coin name");
                                human.nextLine();
                                String name = human.nextLine();
                                System.out.println("Enter ammount of coins");
                                int amount = human.nextInt();
    
                                double price;
                                double spend;
                                if(input3 == 1){
                                    if((price = myExchange.getCoinPrice(name)) > 0){
                                    
                                        if(amount <= myExchange.getCoinQuantity(name)){
                                            spend = price * amount;
        
                                            if(myTrader.getBalance() >= spend){
                                                
                                                myExchange.marketOrder(input3, name, amount);
                                                myTrader.updateWallet(name, amount);
                                                myTrader.setBalance(-(spend));
                                            }
                                            else
                                                System.out.println("invalid balance");
                                        
                                        }
                                        else{
                                            amount = myExchange.getCoinQuantity(name);
                                            
                                            spend = price * amount;
        
                                            if(myTrader.getBalance() >= spend){
                                                
                                                myExchange.marketOrder(input3, name, amount);
                                                myTrader.updateWallet(name, amount);
                                                myTrader.setBalance(-spend);
                                            }
                                            else
                                                System.out.println("invalid balance");
                                        }
                                    }
                                }
    
                                else {
                                    price = myExchange.getCoinPrice(name);
                                    
                                    if(amount <= myTrader.getCoinQ(name)){
                                        spend = price * amount;
                                        
                                        myExchange.marketOrder(input3, name, amount);
                                        myTrader.updateWallet(name, -amount);
                                        myTrader.setBalance(spend);
                                    }
                                    else{
                                        System.out.println("insuffiecent wallet quantities");
                                    }
                                }
                            }
                        }
                        else if(i == 4){
                           
                            int input4 = 0;
                            do{
                                System.out.println("1. Bid");
                                System.out.println("2. Ask");
                                System.out.println("3. Quit");
    
                                try {
                                    input4 = human.nextInt();
                                } 
                                catch (Exception e) {
                                    human.nextLine();
                                    System.out.println("please only enter a number");
                                }
                            }while(input4 < 1 || input4 > 3);
    
                            if(input4 != 3){
                                System.out.println("Enter Coin name");
                                human.nextLine();
                                String name = human.nextLine();
                                System.out.println("Enter ammount of coins");
                                int amount = human.nextInt();
                                System.out.println("Enter price");
                                double price = human.nextDouble();
    
                                double spend;
                                if(input4 == 1){
                                    if((myExchange.matchingEngine(name, price)) > -1){
                                        
                                        spend = price * amount;
        
                                        if(myTrader.getBalance() >= spend){
    
                                            int purchased = myExchange.bidOrder(name, price, amount);
    
                                            if(purchased < 1){
    
                                                myTrader.updateWallet(name, amount);
                                                myTrader.setBalance(-spend);
                                            }
                                            else{
                                                
                                                spend = price * purchased;
                                                myTrader.updateWallet(name, purchased);
                                                myTrader.setBalance(-spend);
                                            }
                                        }
                                        else
                                            System.out.println("invalid balance");
                                    }
                                    else
                                        System.out.println("no offers available for your bid");
                                }
                                
                                else if (input4 == 2){
    
                                    if(myTrader.getCoinQ(name) > -1){
                                        if(myTrader.getCoinQ(name)<= amount){
                                            myExchange.askOrder(name, amount, price);
                                        }
                                        else
                                            System.out.println("wallet balance invalid");
                                    }
                                    else
                                        System.out.println("invalid coin name");
                                }
                            }
                        }
                        if(i == 5){
                            myTrader.printProfile();
                        }
                        if(i == 6){
                            myExchange.printOrderBooks();
                        }
                    }while((i = myTrader.traderMenu()) != 7);
    
    
                }
                else{
                    System.out.println("App has been locked due to too many false sign in!");
                }
            }
    
            else if(input == 2){
                int accID = adminLogin();
    
                Admin myAdmin = new Admin(accID);
    
                int i = 0;
                do{
                    if(i == 1){
                        System.out.println("Pending Registrations===================================");
                        for(int index = 0; index < requesteduserID.size(); index++){
                            System.out.println("Request number: " + index + 1);
                            System.out.println("User ID ; " + requesteduserID.get(index));
                            System.out.println("balance ; " + requestedbalance.get(index));
                        }
    
                        System.out.println("enter Index of registration to accept or 0 to decline all");
                        int accept = human.nextInt();
    
                        if(accept == 0){
                            requesteduserID.clear();
                            requestedbalance.clear();
                            requestedpassword.clear();
                        }
                        else if (accept > 0){
                            if(requesteduserID.get(accept-1) > -1){
                                userID.add(requesteduserID.get(accept-1));
                                password.add(requestedpassword.get(accept-1));
    
                                requesteduserID.remove(accept-1);
                                requestedpassword.remove(accept-1);
                                requestedbalance.remove(accept-1);
                            }
                        }
    
                    }
                    else if(i == 2){
                        myExchange.printMarket();
                    }
                    else if(i == 3){
                        myExchange.printOrderBooks();
                    }
                }while((i = myAdmin.adminMenu()) != 4);
            }
               
            else if(input == 3){
                int newUserID = (userID.get(userID.size()-1)) + 1 + (requesteduserID.size() % 100);
                System.out.println("Please Enter a Password");
                human.nextLine();
                String newPassword = human.nextLine();
                System.out.println("Please Enter a Starting balance");
                double balance = human.nextDouble();
                System.out.println("Please wait for an Admin to accept your registration");
                System.out.println("Your userID if accepted will be " + newUserID);
    
    
                requesteduserID.add(newUserID);
                requestedpassword.add(newPassword);
                requestedbalance.add(balance);
            }
    
            else{
                System.out.println("Thank you and have a nice day");
                System.exit(0);
            }

        }while(input != 4);
    }
}
