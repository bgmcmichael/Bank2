package ben.money.bank;

import java.util.Scanner;

/**
 * Created by fenji on 8/18/2016.
 */
public class Account {
    private String accountName = "";
    private String accountType = "Checking account";
    private double initBal = 0;
    private double currentBal = initBal;

    public Account(String accountName, double initBal) {
        this.initBal = initBal;
        this.accountName = accountName;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }

    public double getInitBal() {
        return initBal;
    }

    public double getCurrentBal() {
        return currentBal;
    }

    public String getAccountName() {
        return accountName;
    }

    public double deposit(double ammount) {
        currentBal += ammount;
        return currentBal;
    }

    public double withdraw(double ammount) throws Exception {
        if ((currentBal - ammount) < 0) {
            throw new Exception("Balance too low to withdraw that ammount");
        } else {
            currentBal -= ammount;
            return currentBal;
        }
    }

    public double transfer (Account otherAccount, double ammount) throws Exception {
       try {
           withdraw(ammount);
           otherAccount.deposit(ammount);
           return currentBal;
       } catch (Exception ex){
           throw new Exception("withdrawal failed");
        }
    }

    public String toString() {
        String accountInfo = "account.name=" + getAccountName() + "\n" +
                "account.initBalance=" + getInitBal() + "\n" +
                "account.currentBalance=" + getCurrentBal();

        return accountInfo;
    }

    public void interact(Customer thisCustomer){
        boolean exitFlag = true;
        while (exitFlag) {
            double ammount = 0.0;
            System.out.println("what would you like to do?");
            System.out.println("1- Deposit\n2- Withdraw\n3- Transfer\n4- Account info\n0- Exit account");
            Scanner scan = new Scanner(System.in);
            int menuChoice = Integer.valueOf(scan.nextLine());
            switch (menuChoice) {
                case 0:
                    exitFlag = !exitFlag;
                    break;
                case 1:
                    System.out.println("How much would you like to deposit?");
                    ammount = Double.valueOf(scan.nextLine());
                    System.out.println("$" + deposit(ammount) + " deposited.");
                    break;
                case 2:
                    System.out.println("How much would you like to deposit?");
                    ammount = Double.valueOf(scan.nextLine());
                    try {
                        withdraw(ammount);
                        System.out.println("$" + ammount + " withdrawn.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case 3:
                    System.out.println("How much would you like to transfer?");
                    ammount = Double.valueOf(scan.nextLine());
                    System.out.println("Which account would you like to transfer to?");
                    try {
                        transfer(thisCustomer.getAccount(), ammount);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Account owner: " + thisCustomer.getName());
                    System.out.println("Account name: " + accountName);
                    System.out.println("Account type: " + accountType);
                    System.out.println("Account balance: " + currentBal);
                    break;

            }
        }
    }
}
