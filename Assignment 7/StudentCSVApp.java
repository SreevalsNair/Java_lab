import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class StudentRecord {
    int id;
    String name, branch;
    int m1, m2, m3, m4, m5;
    double percentage;

    public StudentRecord(int id, String name, String branch,
                         int m1, int m2, int m3, int m4, int m5) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.m4 = m4;
        this.m5 = m5;
        calculatePercentage();
    }

    public void calculatePercentage() {
        int total = m1 + m2 + m3 + m4 + m5;
        this.percentage = total / 5.0;
    }

    public String toCSV() {
        return id + "," + name + "," + branch + "," +
               m1 + "," + m2 + "," + m3 + "," +
               m4 + "," + m5 + "," + percentage;
    }
}

public class StudentCSVApp {

    static String fileName = "Students.csv";

    // CREATE FILE + INITIAL DATA
    public static void createFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage");

            pw.println(new StudentRecord(1,"Aman","CSE",70,80,75,85,90).toCSV());
            pw.println(new StudentRecord(2,"Riya","ECE",65,70,60,75,80).toCSV());

            System.out.println("File created with initial records.");
        } catch (IOException e) {
            System.out.println("Error creating file: " + e);
        }
    }

    // ADD 3 NEW ROWS
    public static void addRecords() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.println(new StudentRecord(3,"Kunal","IT",60,65,70,0,0).toCSV());
            pw.println(new StudentRecord(4,"Sneha","MECH",55,60,65,0,0).toCSV());
            pw.println(new StudentRecord(5,"Arjun","CIVIL",50,55,60,0,0).toCSV());

            System.out.println("3 new records added.");
        } catch (IOException e) {
            System.out.println("Error adding records: " + e);
        }
    }

    // UPDATE MARKS + PERCENTAGE
    public static void updateRecords() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            List<String> lines = new ArrayList<>();

            String header = br.readLine();
            lines.add(header);

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Update marks4 & marks5 if zero
                if (Integer.parseInt(data[6]) == 0) data[6] = "70";
                if (Integer.parseInt(data[7]) == 0) data[7] = "75";

                int total = 0;
                for (int i = 3; i <= 7; i++) {
                    total += Integer.parseInt(data[i]);
                }
                double perc = total / 5.0;

                data[8] = String.valueOf(perc);

                lines.add(String.join(",", data));
            }
            br.close();

            PrintWriter pw = new PrintWriter(new FileWriter(fileName));
            for (String l : lines) pw.println(l);
            pw.close();

            System.out.println("Records updated with marks and percentage.");
        } catch (IOException e) {
            System.out.println("Error updating file: " + e);
        }
    }

    // DELETE ROW (by studentId)
    public static void deleteRecord(int deleteId) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            List<String> lines = new ArrayList<>();

            String header = br.readLine();
            lines.add(header);

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(deleteId + ",")) {
                    lines.add(line);
                }
            }
            br.close();

            PrintWriter pw = new PrintWriter(new FileWriter(fileName));
            for (String l : lines) pw.println(l);
            pw.close();

            System.out.println("Record with ID " + deleteId + " deleted.");
        } catch (IOException e) {
            System.out.println("Error deleting record: " + e);
        }
    }

    // DISPLAY FILE
    public static void displayFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            System.out.println("\n--- FILE CONTENT ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
    }

    public static void main(String[] args) {

        createFile();       // Create
        addRecords();       // Insert
        displayFile();

        updateRecords();    // Update
        displayFile();

        deleteRecord(3);    // Delete
        displayFile();

        // EXCEPTION TEST (file doesn't exist)
        try {
            BufferedReader br = new BufferedReader(new FileReader("WrongFile.csv"));
        } catch (IOException e) {
            System.out.println("\nException caught: " + e);
        }
    }
}
