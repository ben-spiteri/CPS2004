import java.util.*;

public class Admin extends User{
    Scanner human = new Scanner(System.in);
    
    
    public Admin(int accountID){
        super("Admin", accountID);
    }

    public int adminMenu(){
        
        int input = 0;
        do{
            System.out.println("1. ViewApplications");
            System.out.println("2. View Market");
            System.out.println("3. logout/quit app");

            try {
                input = human.nextInt();
            } 
            catch (Exception e) {
                human.nextLine();
                System.out.println("please only enter a number");
            }
        }while(input < 1 || input > 3);

        return input;
    }    
}
