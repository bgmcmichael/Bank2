package ben.money.bank;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by fenji on 8/18/2016.
 */
public class Customer {

    private String name = "";

    public Account getAccount() {
        Scanner scan = new Scanner (System.in);
        System.out.println("which account do you wish to access?");
        printAccountList();
        int menuChoice = Integer.valueOf(scan.nextLine());
        return accountList.get(menuChoice -1);
    }

    ArrayList<Account> accountList = new ArrayList<Account>();
    Account currentAccount = null;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account newAccount){
        accountList.add(newAccount);
    }

    public void useAccount(){
        while (true) {
            Scanner scan = new Scanner (System.in);
            int menuChoice = 0;
            getAccount().interact(this);
            System.out.println("Do you wish to access another account?\n1- Yes\n2- No");
            menuChoice = Integer.valueOf(scan.nextLine());
            if (menuChoice == 2) break;
        }

    }

    public String toString() {
        String fileContents = "";
        int count = 0;
        for (Account currentAccount : accountList ) {
            count++;
            if (count == accountList.size()) {
                fileContents += currentAccount.toString();
            } else
                fileContents += currentAccount.toString() + "\n";
        }
        return fileContents;
    }

    public void printAccountList (){
        for (int count = 0; count < accountList.size(); count++){
            System.out.println((count + 1) + "- " + accountList.get(count));
        }
    }
}
