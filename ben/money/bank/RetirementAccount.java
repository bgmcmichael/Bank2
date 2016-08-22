package ben.money.bank;

/**
 * Created by fenji on 8/19/2016.
 */
public class RetirementAccount extends Account implements Accrueable, Runnable {
    double interestRate = .1;

    public long getInterestPeriod() {
        return InterestPeriod;
    }

    public void setInterestPeriod(long interestPeriod) {
        InterestPeriod = interestPeriod;
    }

    long InterestPeriod = 120000;

    public RetirementAccount(String accountName, double initBal) {
        super(accountName, initBal);
        setAccountType("Retirement");
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
