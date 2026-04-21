import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class MainApp extends Application {

    static Connection con;

    // ================= MAIN =================
    @Override
    public void start(Stage stage) {
        try {
            con = RestaurantJDBC.getConnection();
            RestaurantJDBC.createTables(con);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            return;
        }
        showMainMenu(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    // ================= MENU =================
    public static void showMainMenu(Stage stage) {
        Button rBtn = new Button("Manage Restaurants");
        Button mBtn = new Button("Manage Menu Items");

        rBtn.setOnAction(e -> new RestaurantUI(stage).show());
        mBtn.setOnAction(e -> new MenuItemUI(stage).show());

        VBox root = new VBox(20, rBtn, mBtn);
        root.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("Restaurant System");
        stage.show();
    }

    // ================= JDBC =================
    static class RestaurantJDBC {
        static final String URL = "jdbc:mysql://localhost:3306/restaurant_db";
        static final String USER = "root";
        static final String PASS = "your_password";

        static Connection getConnection() throws Exception {
            return DriverManager.getConnection(URL, USER, PASS);
        }

        static void createTables(Connection con) throws Exception {
            Statement st = con.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS Restaurant(Id INT PRIMARY KEY, Name VARCHAR(100), Address VARCHAR(100))");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS MenuItem(Id INT PRIMARY KEY, Name VARCHAR(100), Price DOUBLE, ResId INT, FOREIGN KEY(ResId) REFERENCES Restaurant(Id))");
        }

        static void insertRestaurant(Connection con, int id, String name, String addr) throws Exception {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Restaurant VALUES(?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, addr);
            ps.executeUpdate();
        }

        static ArrayList<String[]> getAllRestaurants(Connection con) throws Exception {
            ArrayList<String[]> list = new ArrayList<>();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Restaurant");

            while (rs.next()) {
                list.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                });
            }
            return list;
        }

        static void insertMenuItem(Connection con, int id, String name, double price, int resId) throws Exception {
            PreparedStatement ps = con.prepareStatement("INSERT INTO MenuItem VALUES(?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, resId);
            ps.executeUpdate();
        }

        static ArrayList<String[]> getAllMenuItems(Connection con) throws Exception {
            ArrayList<String[]> list = new ArrayList<>();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM MenuItem");

            while (rs.next()) {
                list.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                });
            }
            return list;
        }
    }

    // ================= RESTAURANT UI =================
    static class RestaurantUI {
        Stage stage;

        RestaurantUI(Stage s) { stage = s; }

        void show() {
            TableView<String[]> table = new TableView<>();

            TableColumn<String[], String> c1 = new TableColumn<>("ID");
            c1.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));

            TableColumn<String[], String> c2 = new TableColumn<>("Name");
            c2.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));

            TableColumn<String[], String> c3 = new TableColumn<>("Address");
            c3.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));

            table.getColumns().addAll(c1, c2, c3);

            Button load = new Button("Load");

            load.setOnAction(e -> {
                try {
                    table.getItems().clear();
                    table.getItems().addAll(RestaurantJDBC.getAllRestaurants(con));
                } catch (Exception ex) { ex.printStackTrace(); }
            });

            VBox root = new VBox(10, load, table);
            stage.setScene(new Scene(root, 500, 400));
            stage.setTitle("Restaurants");
            stage.show();
        }
    }

    // ================= MENU ITEM UI =================
    static class MenuItemUI {
        Stage stage;

        MenuItemUI(Stage s) { stage = s; }

        void show() {
            TableView<String[]> table = new TableView<>();

            TableColumn<String[], String> c1 = new TableColumn<>("ID");
            c1.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));

            TableColumn<String[], String> c2 = new TableColumn<>("Name");
            c2.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));

            TableColumn<String[], String> c3 = new TableColumn<>("Price");
            c3.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));

            table.getColumns().addAll(c1, c2, c3);

            Button load = new Button("Load");

            load.setOnAction(e -> {
                try {
                    table.getItems().clear();
                    table.getItems().addAll(RestaurantJDBC.getAllMenuItems(con));
                } catch (Exception ex) { ex.printStackTrace(); }
            });

            VBox root = new VBox(10, load, table);
            stage.setScene(new Scene(root, 500, 400));
            stage.setTitle("Menu Items");
            stage.show();
        }
    }
}
