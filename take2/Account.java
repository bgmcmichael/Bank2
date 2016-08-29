package take2;

/**
 * Created by fenji on 8/22/2016.
 */
public class Account {

    private Customer accountHolder = null;
    private String accountHolderName = "";
    private String accountName = "";
    private String accountType = "Checking";
    private Double balance = 0.0;

    public Account(String accountName, Customer accountHolder, Double balance) {
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        accountHolderName = accountHolder.getName();
        this.balance = balance;
    }

    public void stopThread(){};

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getInfo(){
        String accountInfo = "Account holder=" + accountHolder.getName() + "\nAccount name=" + accountName + "\nAccount type=" +
                accountType + "\nAccount balance=" + balance;
        return accountInfo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Customer accountHolder) {
        this.accountHolder = accountHolder;
    }
}
