import java.time.Year;

public class vehicle {

    // Public data members
    public String brandName;
    public String modelName;
    public double price;
    public double mileage;
    public String fuelType;
    public Year mfgYear;

    // Private data members
    private String mfgCode;
    private String regNo;
    private int speed;

    // Getter & Setter methods
    public void setMfgCode(String mfgCode) {
        this.mfgCode = mfgCode;
    }

    public String getMfgCode() {
        return mfgCode;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    // Default Constructor
    public vehicle() {
        brandName = "Hyundai";
        modelName = "Creta";
        price = 1500000;
        mileage = 15.5;
        fuelType = "Petrol";
        mfgYear = Year.of(2019);
        mfgCode = "HYD123";
        regNo = "KA01AB1234";
        speed = 0;
    }

    // Parameterized Constructor
    public vehicle(String brandName, String modelName, double price, double mileage) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.mileage = mileage;
        this.fuelType = "Petrol";
        this.speed = 0;
    }

    // Copy Constructor
    public vehicle(vehicle v) {
        this.brandName = v.brandName;
        this.modelName = v.modelName;
        this.price = v.price;
        this.mileage = v.mileage;
        this.fuelType = v.fuelType;
        this.mfgYear = v.mfgYear;
        this.mfgCode = v.mfgCode;
        this.regNo = v.regNo;
        this.speed = v.speed;
    }

    // Required Methods
    public void start() {
        System.out.println(brandName + " started.");
    }

    public void stop() {
        System.out.println(brandName + " stopped.");
        speed = 0;
    }

    public void drive() {
        System.out.println(brandName + " is driving.");
    }

    public double calcMileage() {
        return mileage;
    }

    public void changeSpeed(int newSpeed) {
        speed = newSpeed;
        System.out.println("Speed changed to " + speed + " km/h");
    }
}
