import java.util.ArrayList;

// -------------------- Abstract Base --------------------
abstract class WorkforceMember {
    protected String fullName;
    protected String taxId;
    protected String startDate;
    protected String roleTag;
    protected int staffCode;

    public WorkforceMember(String fullName, String taxId, String startDate, String roleTag, int staffCode) {
        this.fullName = fullName;
        this.taxId = taxId;
        this.startDate = startDate;
        this.roleTag = roleTag;
        this.staffCode = staffCode;
    }

    public abstract double computeEarnings();

    public void displayProfile() {
        System.out.println("\nName: " + fullName);
        System.out.println("Staff Code: " + staffCode);
        System.out.println("Role: " + roleTag);
        System.out.println("Joining Date: " + startDate);
        System.out.println("Annual CTC: " + computeEarnings());
    }
}

// -------------------- Full-Time --------------------
class CoreStaff extends WorkforceMember {
    protected double fixedPay;
    protected double bonus;

    public CoreStaff(String fullName, String taxId, String startDate, String roleTag,
                     int staffCode, double fixedPay, double bonus) {
        super(fullName, taxId, startDate, roleTag, staffCode);
        this.fixedPay = fixedPay;
        this.bonus = bonus;
    }

    @Override
    public double computeEarnings() {
        if (roleTag.equalsIgnoreCase("DEV")) {
            return fixedPay + bonus;
        } else if (roleTag.equalsIgnoreCase("PEOPLE")) {
            double hiringReward = 15000;
            return fixedPay + hiringReward;
        }
        return fixedPay;
    }
}

// -------------------- Manager --------------------
class TeamLead extends CoreStaff {
    private double travelAllowance;
    private double learningAllowance;

    public TeamLead(String fullName, String taxId, String startDate, String roleTag,
                    int staffCode, double fixedPay, double bonus,
                    double travelAllowance, double learningAllowance) {
        super(fullName, taxId, startDate, roleTag, staffCode, fixedPay, bonus);
        this.travelAllowance = travelAllowance;
        this.learningAllowance = learningAllowance;
    }

    @Override
    public double computeEarnings() {
        return fixedPay + bonus + travelAllowance + learningAllowance;
    }
}

// -------------------- Contract --------------------
class FlexiStaff extends WorkforceMember {
    private int totalHours;
    private double ratePerHour;

    public FlexiStaff(String fullName, String taxId, String startDate, String roleTag,
                      int staffCode, int totalHours, double ratePerHour) {
        super(fullName, taxId, startDate, roleTag, staffCode);
        this.totalHours = totalHours;
        this.ratePerHour = ratePerHour;
    }

    @Override
    public double computeEarnings() {
        return totalHours * ratePerHour;
    }
}

// -------------------- Main Class --------------------
public class WorkforceApp {
    public static void main(String[] args) {

        ArrayList<WorkforceMember> team = new ArrayList<>();

        // Full-time employees
        WorkforceMember e1 = new CoreStaff("Ishaan Verma", "PAN7845X", "12-03-2022",
                "DEV", 301, 800000, 120000);

        WorkforceMember e2 = new CoreStaff("Meera Joshi", "PAN9921K", "05-07-2021",
                "PEOPLE", 302, 600000, 0);

        // Manager
        WorkforceMember m1 = new TeamLead("Kabir Anand", "PAN6611Z", "20-01-2020",
                "DEV", 303, 1200000, 200000, 50000, 40000);

        // Contract employee
        WorkforceMember c1 = new FlexiStaff("Riya Sen", "PAN2233L", "01-02-2024",
                "CONSULT", 401, 160, 800);

        team.add(e1);
        team.add(e2);
        team.add(m1);
        team.add(c1);

        // Display all
        for (WorkforceMember w : team) {
            w.displayProfile();
            System.out.println("-----------------------------");
        }
    }
}