//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
package com.inventory;

import java.util.Scanner;
import com.inventory.collections.InventoryManagementSystem;
import com.inventory.model.Product;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryManagementSystem ims = new InventoryManagementSystem();

        while (true) {
            System.out.println("\n=== INVENTORY SYSTEM ===");
            System.out.println("1. Add Product");
            System.out.println("2. Update Quantity");
            System.out.println("3. View Products");
            System.out.println("4. Search Product");
            System.out.println("5. Low Stock");
            System.out.println("6. Transactions");
            System.out.println("7. Undo");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next(); // clear invalid input
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); // ✅ clear buffer

            switch (choice) {

                case 1:
                    System.out.print("SKU: ");
                    String sku = sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Category: ");
                    String cat = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine(); // ✅ clear buffer

                    ims.addProduct(new Product(sku, name, cat, price, qty));
                    break;

                case 2:
                    System.out.print("Enter SKU: ");
                    String uSku = sc.nextLine();

                    System.out.print("New Quantity: ");
                    int newQty = sc.nextInt();
                    sc.nextLine(); // ✅ clear buffer

                    ims.updateProductQuantity(uSku, newQty);
                    break;

                case 3:
                    System.out.print("Sort by (sku/price/value/name): ");
                    String sort = sc.nextLine();

                    ims.displayProductsSortedBy(sort);
                    break;

                case 4:
                    System.out.print("Enter keyword: ");
                    String key = sc.nextLine();

                    ims.searchProduct(key);
                    break;

                case 5:
                    ims.showLowStock();
                    break;

                case 6:
                    System.out.print("How many transactions: ");
                    int n = sc.nextInt();
                    sc.nextLine(); // ✅ clear buffer

                    ims.showTransactions(n);
                    break;

                case 7:
                    ims.undo();
                    break;

                case 8:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}