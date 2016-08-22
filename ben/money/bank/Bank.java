package ben.money.bank;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by fenji on 8/18/2016.
 */
public class Bank {
    private static double totalAssets;
    static FileHandler myFileHandler = new FileHandler();
    static ArrayList<Customer> customerList = new ArrayList<Customer>();

    public void addCustomer (String newCustomer) {
        customerList.add(new Customer(newCustomer));
    }

    public void printCustomers(){
        String custMenu = "";
        for (int count = 0; count < customerList.size(); count++){
            custMenu = ((count + 1) + "- " + customerList.get(0).getName() + "\n");
        }
        System.out.println(custMenu);
    }

    public void addAccount(Customer newCustomer) {
        int accountType = -1;
        String accountName = "";
        Double initBal = 0.0;
        Scanner scan = new Scanner (System.in);

        System.out.println("what kind of account would you like to start?");
        System.out.println("1- Checking\n2- Savings\n3-Retirement");
        accountType = Integer.valueOf(scan.nextLine());
        System.out.println("What would you like to name this account?");
        accountName = scan.nextLine();
        System.out.println("what is your account's initial balance going to be " + newCustomer.getName() + "?");
        initBal = Double.valueOf(scan.nextLine());

        switch (accountType) {
            case 1:
                Account newAccount1 = new Account(accountName, initBal);
                newCustomer.addAccount(newAccount1);
                break;
            case 2:
                SavingsAccount newAccount2 = new SavingsAccount(accountName, initBal);
                newCustomer.addAccount(newAccount2);
                break;
            case  3:
                RetirementAccount newAccount3 = new RetirementAccount(accountName, initBal);
                newCustomer.addAccount(newAccount3);
                break;
        }
        System.out.println(newCustomer.getName() + ", your new account was created");
    }


    public void saveCustList() {

    }

    public void saveAcctList(Customer cust) {

    }

    public void saveToFile (String fileContents){
        myFileHandler.writeFile("nameList", fileContents);
    }

    public void readFile (File myFile) {
        myFileHandler.readFile("src/ben/money/bank/nameList.txt");
    }

    public String toString() {
        String listOFCustomers = "";
        int counter = 0;
        for (Customer currentCustomer : customerList ) {
            if (counter == (customerList.size() - 1)) {
                listOFCustomers += currentCustomer.getName();
            } else {
                listOFCustomers += currentCustomer.getName() + ",";
            }
        }
        return listOFCustomers;
    }
}

