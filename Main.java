import bank.Bank;

import java.util.Scanner;

public class Main extends Application {

    private static Scanner s = new Scanner(System.in);

    // Nettoie l'écran des prints précédents
    private static void flushScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        // Init
        Bank b = new Bank();

        /// Declaration before loop
        boolean endOfSession = false;
        String userInput;
        String name;
        Integer balance;
        Integer threshold;
        Integer balance_modifier;

        // Loop
        while (!endOfSession) {

            // Menu display
            System.out.println("\n\nWhat operation do you want to do ?");
            System.out.println("0. See all accounts");
            System.out.println("1. Create a new account");
            System.out.println("2. Change balance on a given account");
            System.out.println("3. Block an account");
            System.out.println("q. Quit\n");

            // Getting primary input
            userInput = s.nextLine();

            // Processing user input
            switch (userInput) {
                case "q":
                    endOfSession = true;
                    b.closeDb();
                    break;
                case "0":
                    b.printAllAccounts();
                    break;
                case "1":
                    System.out.println("Please give us the account name");
                    name = s.nextLine();
                    System.out.println("Please give us the balance account");
                    balance = s.nextInt();
                    System.out.println("Please give us the account threshold");
                    threshold = s.nextInt();
                    b.createNewAccount(name, balance, threshold);
                    break;
                case "2":
                    System.out.println("Please give us the account name");
                    name = s.nextLine();
                    System.out.println("Please tell us by how much the balance shall be changed");
                    balance_modifier = s.nextInt();
                    b.changeBalanceByName(name, balance_modifier);
                    break;
                case "3":
                    System.out.println("Please give us the account name");
                    name = s.nextLine();
                    b.blockAccount(name);
                    break;
            }
        }

    }
}

