public class Person {
    private final String name;
    private double balance;

    // constructor
    public Person(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    // returns the name as a string
    public String getName() {
        return this.name;
    }

    // returns the balance as a double
    public double getBalance() {
        return this.balance;
    }

    // determines if a purchase can be made and makes it if necessary
    public boolean purchase(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    // only deposits a positive double to balance
    public void deposit(double money) {
        if (money >= 0) {
            this.balance += money;
        }
    }
}
