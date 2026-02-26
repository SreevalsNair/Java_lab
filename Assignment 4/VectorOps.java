import java.util.Scanner;

public class VectorOps {

    public static double[] readVector(Scanner sc, int dim) {
        double[] arr = new double[dim];
        for (int i = 0; i < dim; i++) {
            arr[i] = sc.nextDouble();
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter dimension (2 or 3): ");
            int dimA = sc.nextInt();

            System.out.print("Enter dimension (2 or 3): ");
            int dimB = sc.nextInt();

            System.out.println("Enter Vector A components:");
            Vector A = new Vector(readVector(sc, dimA));

            System.out.println("Enter Vector B components:");
            Vector B = new Vector(readVector(sc, dimB));

            System.out.print("A + B = ");
            A.add(B).display();

            System.out.print("A - B = ");
            A.subtract(B).display();

            System.out.println("A . B = " + A.dot(B));

        } catch (VectorException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
