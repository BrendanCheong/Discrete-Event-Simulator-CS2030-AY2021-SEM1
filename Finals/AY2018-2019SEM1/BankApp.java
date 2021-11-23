import java.util.Random;
import java.util.Scanner;

public class BankApp {

    static boolean transfer(Account source, Account target, double amount) {
        boolean result = source.withdraw(amount);
        if (result) {
            target.deposit(amount);
            return true;
        } else {
            System.out.println("Transfer failed!");
            return false;
        }
    }

    static double getAmount(Scanner sc) {
        System.out.print("Enter amount to transfer: ");
        return sc.nextDouble();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account s = getSavingsAccount();
        Account t = getNormalAccount();
        double amt = getAmount(sc);
        boolean result = transfer(s, t, amt);
        sc.close();
        if (result) System.out.println("Transfer successful") ;
    }

    // getAccount and other methods omitted
    public static Account getSavingsAccount() {
        double randomAmount = 500 + (new Random().nextDouble() * (1500 - 500));
        return new SavingsAccount(randomAmount);
    }

    public static Account getNormalAccount() {
        double randomAmount = 500 + (new Random().nextDouble() * (1500 - 500));
        return new NormalAccount(randomAmount);
    }
}
