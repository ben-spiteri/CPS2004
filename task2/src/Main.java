import java.util.*;




public class Main{

    static private ArrayList<Integer> userID = new ArrayList<Integer>();
    static private ArrayList<String> password = new ArrayList<String>();

    static private void initUsers(){

        //id and password for 1st user
        userID.add(101);
        password.add("a");

        //id and password for 2nd user
        userID.add(102);
        password.add("b");
    }

    static private void initUserAccount(int id, Trader myUser){
        if(id == 101){

            myUser.setBalance(50000);

            myUser.addCoin("Bitcoin", 2);
            myUser.addCoin("Dogecoin", 21);
            myUser.addCoin("Ethereum", 16);

        }
        if(id == 102){
            myUser.setBalance(2000000);

            myUser.addCoin("Bitcoin", 5);
            myUser.addCoin("Dogecoin", 11);
            myUser.addCoin("Ethereum", 38);
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

                System.out.println("EnterPassword");
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
            int accID = userLogin();
            
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
                        //buying
                    }
                    else if(i == 4){
                        //selling
                    }
                    if(i == 5){
                        myTrader.printProfile();
                    }
                }while((i = myTrader.traderMenu()) != 6);


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
                    //print out pending registrations
                }
                else if(i == 2){
                    myExchange.printMarket();
                }
            }while((i = myAdmin.adminMenu()) != 3);
        }
           
        else if(input == 3){
            System.out.println("Registration Currently Not Available");
        }

        else{
            System.out.println("Thank you and have a nice day");
            System.exit(0);
        }
    }
}
