package take2;

/**
 * Created by fenji on 8/24/2016.
 */
public class SavingsAccount extends Account implements Runnable {
    private double interrestRate = 1.05;
    private long interrestPeriod = 10000;
    private Thread newThread = null;
    public SavingsAccount(String accountName, Customer accountHolder, Double balance) {
        super(accountName, accountHolder, balance);
        newThread = new Thread(this);
        newThread.start();
        setAccountType("Savings");
    }

    public void accrueInterrest(){
        try{
            Thread.sleep(interrestPeriod);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        setBalance(getBalance() * interrestRate);
    }

    public void stopThread(){
        newThread.stop();
    }

    @Override
    public void run() {
        while(true){
            accrueInterrest();
        }
    }
}
