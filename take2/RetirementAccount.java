package take2;

/**
 * Created by fenji on 8/24/2016.
 */
public class RetirementAccount extends Account implements Runnable {
    private double interrestRate = 1.1;
    private long interrestPeriod = 120000;
    private Thread newThread = null;
    public RetirementAccount(String accountName, Customer accountHolder, Double balance) {
        super(accountName, accountHolder, balance);
        newThread = new Thread(this);
        newThread.start();
        setAccountType("Retirement");
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
