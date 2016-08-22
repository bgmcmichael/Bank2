package ben.money.bank;

/**
 * Created by fenji on 8/19/2016.
 */
public class SavingsAccount extends Account implements Accrueable, Runnable {
    double interestRate = .05;
    long InterestPeriod = 10000;

    public SavingsAccount(String accountName, double initBal) {
        super(accountName, initBal);
        setAccountType("Savings");
    }

    @Override
    public void AccrueInterest() {
        deposit(getCurrentBal() * interestRate);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(InterestPeriod);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AccrueInterest();
        }
    }
}
