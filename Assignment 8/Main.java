import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("   Inventory Management System");
        System.out.println("   (Singleton + Adapter + Iterator)");
        System.out.println("===========================================\n");

        // Singleton – one and only InventoryManager instance
        InventoryManager manager = InventoryManager.getInstance();

        // Verify Singleton: both references point to the same object
        InventoryManager anotherRef = InventoryManager.getInstance();
        System.out.println("Singleton check (same instance): "
                + (manager == anotherRef) + "\n");

        // Add NewProduct objects directly
        manager.addProduct(new NewProduct("Laptop"));
        manager.addProduct(new NewProduct("Wireless Mouse"));
        manager.addProduct(new NewProduct("Mechanical Keyboard"));

        // Add LegacyItem objects via Adapter
        manager.addProduct(new ProductAdapter(new LegacyItem(1001, "Old CRT Monitor")));
        manager.addProduct(new ProductAdapter(new LegacyItem(1002, "Vintage Dot-Matrix Printer")));
        manager.addProduct(new ProductAdapter(new LegacyItem(1003, "Floppy Disk Drive")));

        // Iterator – traverse inventory and display each product
        System.out.println("--- Inventory Details ---");
        Iterator<Product> iterator = manager.returnInventory();

        int count = 1;
        while (iterator.hasNext()) {
            Product product = iterator.next();
            System.out.print("Item " + count++ + ": ");
            product.displayDetails();
        }

        System.out.println("\nTotal products in inventory: " + (count - 1));
        System.out.println("===========================================");
    }
}
