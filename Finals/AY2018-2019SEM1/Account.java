public abstract class Account {

    private double balance;

    Account(double balance) {
        this.balance = balance;
    }

    public abstract boolean withdraw(double amount);

    protected boolean checkBalance(double transfer) {
        return this.balance > transfer;
    }

    protected void deposit(double amount) {
        this.balance = this.balance + amount;
    }

    protected void deductAccount(double amt) {
        this.balance -= amt;
    }

    // other methods omitted
}
