package take2;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

/**
 * Created by fenji on 8/22/2016.
 */
public class BankRunner {
    public static void main(String[] args) {
        String textInput = "";
        boolean customerExists = false;
        int intInput = 0;
        double doubleInput = 0.0;
        Bank myBank = new Bank();
        Customer currentCustomer = null;
        Scanner scan = new Scanner(System.in);

        myBank.readFiles();

        while (true) {
                System.out.println("What is your name?");
                String name = scan.nextLine();
                customerExists = myBank.customerExists(name);
            if (!customerExists){
                currentCustomer = myBank.createCustomer(name);
                myBank.addCustomer(currentCustomer);
            } else {
                currentCustomer = myBank.getCurrentCustomer();
            }


            while (true) {
                System.out.println("would you like to make another account?\n1- yes\n2- no");
                intInput = Integer.valueOf(scan.nextLine());
                if (intInput == 2) {
                    break;
                }
                currentCustomer.addAccount(currentCustomer.createAccount(scan));
            }

            while (true) {
                System.out.println("Which account would you like to use?");
                System.out.println(myBank.getCurrentCustomer().getAccountMenu());
                System.out.println("0- Exit account list");
                intInput = Integer.valueOf(scan.nextLine());
                if (intInput == 0) {
                    break;
                }
                myBank.useAccount(currentCustomer.getAccountAt(intInput - 1), scan);
            }
            System.out.println("would you like to leave the bank?\n1- yes\n2- no");
            intInput = Integer.valueOf(scan.nextLine());
            if(intInput == 1){
                myBank.saveToFile();
                myBank.stopThreads();
                break;
            }
        }


    }
}