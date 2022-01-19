public class User {

    private String type;
    private int accountID;
    
    public User(String type, int accountID) {
        this.type = type;
        this.accountID = accountID;
    }

    public String getType(){
        return type;
    }

    public int getID(){
        return accountID;
    }
}
