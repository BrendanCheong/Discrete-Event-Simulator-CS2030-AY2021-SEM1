public class SavingsAccount extends Account {
    
    public SavingsAccount(double amt) {
        super(amt);
    }

    @Override
    public boolean withdraw(double amt) {
        if (amt > 1000) {
            System.out.println("Over withdrawal detected");
            return false;
        } else if (amt == 0 || !checkBalance(amt)) {
            System.out.println("You can't transfer nothing!");
            return false;
        } else {
            deductAccount(amt);
            return true;
        }
    }
}
