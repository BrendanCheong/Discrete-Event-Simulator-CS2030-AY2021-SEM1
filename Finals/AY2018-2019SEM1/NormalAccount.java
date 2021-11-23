public class NormalAccount extends Account {
    
    public NormalAccount(double balance) {
        super(balance);
    }

    @Override
    public boolean withdraw(double amt) {
        if (amt == 0 || !checkBalance(amt)) {
            System.out.println("You can't transfer nothing!");
            return false;
        } else {
            deductAccount(amt);
            return true;
        }
    }
}
