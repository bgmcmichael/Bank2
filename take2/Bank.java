package take2;

import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.IdentityHashMap;
import java.util.Scanner;

/**
 * Created by fenji on 8/22/2016.
 */
public class Bank {
    private ArrayList<Customer> Customers = new ArrayList<Customer>();
    private Customer currentCustomer = null;

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public void stopThreads (){
        for (Customer thisCustomer : Customers){
            thisCustomer.stopThreads();
        }
    }

    public void useAccount (Account myAccount, Scanner scan){
        boolean exitFlag = true;
        while (exitFlag) {
            System.out.println("What would you like to do?");
            System.out.println("1- Deposit\n2- Withdraw\n3- Transfer\n4- Print account info\n0- Exit account");
            int userInput = Integer.valueOf(scan.nextLine());
            switch (userInput) {
                case 0:
                    exitFlag = !exitFlag;
                    break;
                case 1:
                    deposit(myAccount, scan);
                    break;
                case 2:
                    withdraw(myAccount, scan);
                    break;
                case 3:
                    transfer(myAccount, scan);
                    break;
                case 4:
                    printAccountInfo(myAccount);
                    break;
            }

        }
    }

    public void withdraw (Account myAccount, Scanner scan){
        System.out.println("How much do you need to withdraw?");
        double ammount = Double.valueOf(scan.nextLine());
        myAccount.setBalance(myAccount.getBalance() - ammount);
    }

    public void deposit (Account myAccount, Scanner scan) {
        System.out.println("How much do you wish to deposit?");
        double ammount = Double.valueOf(scan.nextLine());
        myAccount.setBalance(myAccount.getBalance() + ammount);
    }

    public void transfer (Account myAccount, Scanner scan){
        Account otherAccount = null;
        while(true) {
            int userInput = 0;
            System.out.println("Which account would you like to transfer to?");
            System.out.println(myAccount.getAccountHolder().getAccountMenu());
            userInput = Integer.valueOf(scan.nextLine());
            otherAccount = myAccount.getAccountHolder().getAccountAt(userInput - 1);
            if (otherAccount == myAccount) {
                System.out.println("You cannot transfer to and from the same account");
            }
            if (otherAccount != myAccount) {
                break;
            }
        }
            System.out.println("How much do you wish to transfer?");
            double ammount = Double.valueOf(scan.nextLine());
            myAccount.setBalance(myAccount.getBalance() - ammount);
            otherAccount.setBalance(otherAccount.getBalance() + ammount);
            System.out.println("Transfer completed");
    }

    public void printAccountInfo (Account myAccount){
        System.out.println(myAccount.getInfo());
    }

    public Customer createCustomer (String name){
        currentCustomer = new Customer(name, this);
        return currentCustomer;
    }

    public void addCustomer (Customer newCustomer){
        Customers.add(newCustomer);
    }

    public Customer getCurrentCustomer(){
        return currentCustomer;
    }

    public Customer getCustomerAt(int index) {
        currentCustomer = Customers.get(index);
        return currentCustomer;
    }

    public boolean customerExists(String name){
        boolean isInExistance = false;
        for (Customer thiscustomer : Customers) {
            if (thiscustomer.getName().equalsIgnoreCase(name)){
                currentCustomer = thiscustomer;
                isInExistance = true;
            }
        }
        return isInExistance;

//        String name = "";
//        System.out.println("What is your name?");
//        name = scan.nextLine();
//        int index = 0;
//        for (Customer currentCustomer : Customers) {
//            if(currentCustomer.getName().equalsIgnoreCase(name)){
//                this.currentCustomer = currentCustomer;
//                return index;
//            }
//            index++;
//        }
//        currentCustomer = createCustomer(name);
//        addCustomer(currentCustomer);
//        return (Customers.size() - 1);
    }

    public void accessAccounts(int indexHolder, Bank myBank, String name) {
        Customer thisCustomer = null;
        String textInput = "";
        if (indexHolder != -1) {
            //Access existing customer's accounts
            thisCustomer = myBank.getCustomerAt(indexHolder);
            System.out.println("welcome " + thisCustomer.getName() + " choose an account");
            System.out.println(thisCustomer.getAccountMenu());
        }
        currentCustomer = thisCustomer;
    }

    public String getCustomerMenu(){
        String menuString = "";
        int index = 0;
        for (Customer thisCustomer : Customers){
            if (index ==0) {
                menuString = (index + 1) + "- " + thisCustomer.getName();
            } else {
                menuString += "\n" + (index + 1) + "- " + thisCustomer.getName();
            }
        }
        return menuString;
    }

    public String toString(){
        String custList = "";
        for (int counter =0; counter < Customers.size(); counter++){
            if (counter < (Customers.size() - 1)){
                custList += Customers.get(counter).getName() + ",";
            } else {
                custList += Customers.get(counter).getName();
            }
        }
        return custList;
    }

    public void readFiles(){
        File bankFile = new File("bank.txt");
        try {
            Scanner fileScanner = new Scanner(bankFile);
            String[] custNames = null;
            String fileContents = "";

            while (fileScanner.hasNext()){
                fileContents += fileScanner.nextLine();
            }
            custNames = fileContents.split(",");
            for (int counter = 0; counter < custNames.length; counter++){
                if (custNames.length == 0){
                    break;
                }
                File accountFile = new File(custNames[counter] + ".txt");
                Scanner accountScanner = new Scanner(accountFile);
                String accountHolder = "", accountName = "", accountType = "", balanceString = "";
                double balance = 0;
                while (accountScanner.hasNext()) {
                    for (int count = 0; count < 5; count++) {
                        String[] splitter;
                        switch (count) {
                            case 0:
                                accountHolder = accountScanner.nextLine();
                                splitter = accountHolder.split("=");
                                accountHolder = splitter[1];
                                break;
                            case 1:
                                accountName = accountScanner.nextLine();
                                splitter = accountName.split("=");
                                accountName = splitter[1];
                                break;
                            case 2:
                                accountType = accountScanner.nextLine();
                                splitter = accountType.split("=");
                                accountType = splitter[1];
                                break;
                            case 3:
                                balanceString = accountScanner.nextLine();
                                splitter = balanceString.split("=");
                                balance = Double.valueOf(splitter[1]);
                                break;
                            case 4:
                                if (!customerExists(accountHolder)){
                                addCustomer(createCustomer(accountHolder));
                                }
                                Customers.get(counter).addAccount(Customers.get(counter).createAccount(accountName, balance, accountType));
                                break;

                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void saveToFile(){
        File bank = new File("bank.txt");
        try {
            FileWriter bankWriter = new FileWriter(bank);
            BufferedWriter bankBuffer = new BufferedWriter(bankWriter);
            bankBuffer.write(this.toString());
            bankBuffer.close();

           // bankWriter.write(this.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int counter = 0; counter < Customers.size(); counter++){
            Customers.get(counter).saveToFile();
        }
    }
}
