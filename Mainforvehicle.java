public class Mainforvehicle {

    public static void main(String[] args) {

        vehicle v1 = new vehicle();
        vehicle v2 = new vehicle("Toyota", "Camry", 3000000, 19.0);
        vehicle v3 = new vehicle("Honda", "Civic", 2000000, 17.5);
        vehicle v4 = new vehicle(v2);

        // Edge case testing examples
        vehicle v5 = new vehicle("Test", "XYZ", 1000000, -5);
        vehicle v6 = new vehicle("Test", "ABC", -200000, 10);

        vehicle[] myFleet = { v1, v2, v3, v4, v5, v6 };

        if (myFleet.length == 0) {
            System.out.println("No vehicles in fleet.");
            return;
        }

        System.out.printf("%-10s %-15s %-12s %-10s%n",
                "Brand", "Model", "Price", "Mileage");
        System.out.println("------------------------------------------------------");

        double totalValue = 0;
        double totalMileage = 0;
        int validMileageCount = 0;

        vehicle bestMileageVehicle = null;

        for (vehicle v : myFleet) {

            // Null check (VERY important edge case)
            if (v == null) {
                System.out.println("Null vehicle entry skipped.");
                continue;
            }

            double mileage = v.calcMileage();

            // Validate mileage
            if (mileage <= 0) {
                System.out.println("Invalid mileage for vehicle: " + v.modelName);
                continue;
            }

            // Validate price
            if (v.price <= 0) {
                System.out.println("Invalid price for vehicle: " + v.modelName);
                continue;
            }

            System.out.printf("%-10s %-15s %-12.2f %-10.2f%n",
                    v.brandName,
                    v.modelName,
                    v.price,
                    mileage);

            totalValue += v.price;
            totalMileage += mileage;
            validMileageCount++;

            if (bestMileageVehicle == null ||
                mileage > bestMileageVehicle.calcMileage()) {
                bestMileageVehicle = v;
            }
        }

        if (validMileageCount == 0) {
            System.out.println("\nNo valid vehicles for summary.");
            return;
        }

        double avgMileage = totalMileage / validMileageCount;

        System.out.println("\nSummary Report");
        System.out.println("--------------");
        System.out.println("Total Valid Vehicles : " + validMileageCount);
        System.out.printf("Total Fleet Value    : %.2f%n", totalValue);
        System.out.printf("Average Mileage      : %.2f%n", avgMileage);
        System.out.println("Best Mileage         : "
                + bestMileageVehicle.modelName + " ("
                + bestMileageVehicle.calcMileage() + ")");
    }
}
