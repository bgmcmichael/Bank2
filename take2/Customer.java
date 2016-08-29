package take2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by fenji on 8/22/2016.
 */
public class Customer {

    private String name = "";
    private Bank myBank;
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public Customer(String name, Bank myBank) {
        this.name = name;
        this.myBank = myBank;
    }

    public void stopThreads (){
        for (Account thisaccount : accounts){
            thisaccount.stopThread();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bank getMyBank() {
        return myBank;
    }

    public void setMyBank(Bank myBank) {
        this.myBank = myBank;
    }

    public Account getAccountAt(int index){
        return accounts.get(index);
    }

    public String getAccountMenu(){
        String menuString = "";
        int index = 0;
        for (Account currentAccount : accounts){
            if (index ==0) {
                menuString = (index + 1) + "- " + currentAccount.getAccountName();
            } else {
                menuString += "\n" + (index + 1) + "- " + currentAccount.getAccountName();
            }
            index++;
        }
        return menuString;
    }

    public Account createAccount(String accountName, double balance, String accountType){
        if (accountType.equalsIgnoreCase("Checking")) {
            Account newAccount = new Account(accountName, this, balance);
            return newAccount;
        }
        if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount newAccount = new SavingsAccount(accountName, this, balance);
            return newAccount;
        }
        if (accountType.equalsIgnoreCase("Retirement")) {
            RetirementAccount newAccount = new RetirementAccount(accountName, this, balance);
            return newAccount;
        }
        return null;
    }

    public Account createAccount(Scanner scan){
        String accountName = "";
        double balance = 0.0;
        int accountType = -1;
        System.out.println("What kind of account do you wish to make?");
        System.out.println("1- Checking\n2- Savings\n3- Retirement");
        accountType = Integer.valueOf(scan.nextLine());
        if (accountType == 1) {
            System.out.println("What would you like to call your account?");
            accountName = scan.nextLine();
            System.out.println("Enter an initial deposit");
            balance = Double.valueOf(scan.nextLine());
            Account newAccount = new Account(accountName, this, balance);
            return newAccount;
        }
        if (accountType == 2) {
            System.out.println("What would you like to call your account?");
            accountName = scan.nextLine();
            System.out.println("Enter an initial deposit");
            balance = Double.valueOf(scan.nextLine());
            SavingsAccount newAccount = new SavingsAccount(accountName, this, balance);
            return newAccount;
        }
        if (accountType == 3) {
            System.out.println("What would you like to call your account?");
            accountName = scan.nextLine();
            System.out.println("Enter an initial deposit");
            balance = Double.valueOf(scan.nextLine());
            RetirementAccount newAccount = new RetirementAccount(accountName, this, balance);
            return newAccount;
        }
        return null;
    }

    public void addAccount (Account account){
        accounts.add(account);

    }

    public String toString(){
        String accountInfo = "";
        for (int counter = 0; counter < accounts.size(); counter++){
            if (counter == 0){
                accountInfo = accounts.get(counter).getInfo();
            } else{
                accountInfo += "\n" + accounts.get(counter).getInfo();
            }
        }
        return accountInfo;
    }

    public void saveToFile(){
        File customer = new File(  name + ".txt");
        try {
            FileWriter custWriter = new FileWriter(customer);
            BufferedWriter custBuffer = new BufferedWriter(custWriter);
            custBuffer.write(this.toString());
            custBuffer.close();

            //custWriter.write(this.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
