import java.util.ArrayList;

// -------------------- Base Class --------------------
class BankAccount {
    protected int accId;
    protected double currentAmount;

    public BankAccount(int accId, double currentAmount) {
        this.accId = accId;
        this.currentAmount = currentAmount;
    }

    public void deposit(double amt) {
        currentAmount += amt;
        System.out.println("Amount credited: " + amt);
    }

    public void withdraw(double amt) {
        if (amt <= currentAmount) {
            currentAmount -= amt;
            System.out.println("Amount debited: " + amt);
        } else {
            System.out.println("Not enough balance");
        }
    }

    public double getAmount() {
        return currentAmount;
    }

    public int getAccId() {
        return accId;
    }

    public String getType() {
        return "Regular Account";
    }
}

// -------------------- Savings --------------------
class SavingAcc extends BankAccount {
    private double minLimit = 1000;

    public SavingAcc(int accId, double currentAmount) {
        super(accId, currentAmount);
    }

    @Override
    public void withdraw(double amt) {
        if (currentAmount - amt >= minLimit) {
            currentAmount -= amt;
            System.out.println("Savings debited: " + amt);
        } else {
            System.out.println("Minimum balance must be maintained");
        }
    }

    @Override
    public String getType() {
        return "Saving Account";
    }
}

// -------------------- Loan --------------------
class CreditAccount extends BankAccount {

    public CreditAccount(int accId, double currentAmount) {
        super(accId, currentAmount);
    }

    @Override
    public void deposit(double amt) {
        currentAmount -= amt;
        System.out.println("Loan payment done: " + amt);
    }

    @Override
    public void withdraw(double amt) {
        currentAmount += amt;
        System.out.println("Loan amount taken: " + amt);
    }

    @Override
    public String getType() {
        return "Loan Account";
    }
}

// -------------------- Client --------------------
class Client {
    private int clientCode;
    private String clientName;
    private ArrayList<BankAccount> accList;

    public Client(int clientCode, String clientName) {
        this.clientCode = clientCode;
        this.clientName = clientName;
        this.accList = new ArrayList<>();
    }

    public void addNewAccount(BankAccount b) {
        accList.add(b);
    }

    public void showDetails() {
        System.out.println("\nClient Code: " + clientCode);
        System.out.println("Client Name: " + clientName);
        System.out.println("Account Summary:");

        for (BankAccount b : accList) {
            System.out.println("  ID: " + b.getAccId());
            System.out.println("  Category: " + b.getType());
            System.out.println("  Balance: " + b.getAmount());
            System.out.println("----------------------");
        }
    }
}

// -------------------- Main --------------------
public class BankingSystem {
    public static void main(String[] args) {

        ArrayList<Client> clientData = new ArrayList<>();

        // Create Clients
        Client u1 = new Client(11, "Arjun");
        Client u2 = new Client(22, "Neha");

        // Create Accounts
        BankAccount a1 = new SavingAcc(501, 8000);
        BankAccount a2 = new CreditAccount(601, 15000);

        BankAccount a3 = new SavingAcc(502, 12000);
        BankAccount a4 = new CreditAccount(602, 25000);

        // Assign accounts
        u1.addNewAccount(a1);
        u1.addNewAccount(a2);

        u2.addNewAccount(a3);
        u2.addNewAccount(a4);

        // Store clients
        clientData.add(u1);
        clientData.add(u2);

        // Transactions
        a1.deposit(2000);
        a1.withdraw(3000);

        a2.withdraw(5000); // taking loan
        a2.deposit(2000);  // repayment

        // Display all data
        for (Client c : clientData) {
            c.showDetails();
        }
    }
}
