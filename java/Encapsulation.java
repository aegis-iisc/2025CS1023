class BankAccount {
    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        if (initialBalance > 0) {
            this.balance = initialBalance;
        }
    }

    // Getter method
    public double getBalance() {
        return balance;
    }

    // Setter method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}




public class Encapsulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        account.deposit(500);
        account.balance = 0;
        System.out.println("Balance: " + account.getBalance());
    }
}
