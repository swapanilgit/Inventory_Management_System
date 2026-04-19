package com.inventory.collections;

import com.inventory.comparators.PriceComparator;
import com.inventory.comparators.ValueComparator;
import com.inventory.model.Product;

import java.util.*;

public class InventoryManagementSystem {
    // Different collections for different purposes
    private HashSet<Product> productSet; // For unique SKU management
    private TreeSet<Product> sortedProducts; // For sorted display
    private LinkedList<String> transactionHistory; // For transaction log
    private Stack<Product> undoStack; // For undo operations
    private Queue<Product> lowStockQueue; // For low stock alerts

    // Statistics
    private int totalProducts;
    private double totalInventoryValue;

    public InventoryManagementSystem() {
        productSet = new HashSet<>();
        sortedProducts = new TreeSet<>();
        transactionHistory = new LinkedList<>();
        undoStack = new Stack<>();
        lowStockQueue = new LinkedList<>();
        totalProducts = 0;
        totalInventoryValue = 0;
    }

    public void addProduct(Product product) {
        if(productSet.add(product)) {
            sortedProducts.add(product);
            totalProducts++;
            totalInventoryValue += product.getInventoryValue();

            // Add to transaction history
            String transaction = String.format("ADD: %s - %s (Qty: %d) at %s",
                    product.getSku(), product.getName(),
                    product.getQuantity(), new Date());
            transactionHistory.addFirst(transaction); // Add to beginning

            // Check for low stock
            if(product.getQuantity() < 10) {
                lowStockQueue.add(product);
            }

            System.out.println("✅ Product added successfully!");
        } else {
            System.out.println("❌ Product with SKU " + product.getSku() + " already exists!");
        }
    }

    public void updateProductQuantity(String sku, int newQuantity) {
        for(Product product : productSet) {
            if(product.getSku().equals(sku)) {
                // Save current state for undo
                try {
                    Product copy = new Product(product.getSku(), product.getName(),
                            product.getCategory(), product.getPrice(),
                            product.getQuantity());
                    undoStack.push(copy);
                } catch(Exception e) {
                    System.out.println("Could not save undo state: " + e.getMessage());
                }

                int oldQuantity = product.getQuantity();
                product.setQuantity(newQuantity);

                // Update inventory value
                totalInventoryValue -= product.getPrice() * oldQuantity;
                totalInventoryValue += product.getPrice() * newQuantity;

                // Update transaction
                String transaction = String.format("UPDATE: %s - Quantity changed from %d to %d at %s",
                        sku, oldQuantity, newQuantity, new Date());
                transactionHistory.addFirst(transaction);

                System.out.println("✅ Quantity updated successfully!");
                return;
            }
        }
        System.out.println("❌ Product with SKU " + sku + " not found!");
    }

    public void undoLastUpdate() {
        if(undoStack.isEmpty()) {
            System.out.println("No operations to undo!");
            return;
        }

        Product previousState = undoStack.pop();
        updateProductQuantity(previousState.getSku(), previousState.getQuantity());
        System.out.println("✅ Last update undone!");
    }

    public void displayProductsSortedBy(String criteria) {
        System.out.println("\n=== PRODUCTS SORTED BY " + criteria.toUpperCase() + " ===");

        List<Product> productList = new ArrayList<>(productSet);

        switch(criteria.toLowerCase()) {
            case "sku":
                Collections.sort(productList); // Natural ordering
                break;
            case "price":
                Collections.sort(productList, new PriceComparator());
                break;
            case "value":
                Collections.sort(productList, new ValueComparator());
                break;
            case "name":
                Collections.sort(productList, new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p1.getName().compareTo(p2.getName());
                    }
                });
                break;
            default:
                System.out.println("Invalid sort criteria!");
                return;
        }

        displayProductList(productList);
    }

    private void displayProductList(List<Product> products) {
        if(products.isEmpty()) {
            System.out.println("No products found!");
            return;
        }

        System.out.printf("%-10s %-20s %-15s %-10s %-8s %-12s\n",
                "SKU", "Name", "Category", "Price", "Qty", "Value");
        System.out.println("-".repeat(85));

        for(Product product : products) {
            System.out.printf("%-10s %-20s %-15s ₹%-9.2f %-8d ₹%-11.2f\n",
                    product.getSku(), product.getName(), product.getCategory(),
                    product.getPrice(), product.getQuantity(), product.getInventoryValue());
        }
    }

    public void displayLowStockAlerts() {
        System.out.println("\n=== LOW STOCK ALERTS ===");
        if(lowStockQueue.isEmpty()) {
            System.out.println("No low stock items!");
            return;
        }

        System.out.println("Items with stock less than 10:");
        int count = 1;
        for(Product product : lowStockQueue) {
            System.out.printf("%d. %s - %s (Current Stock: %d)\n",
                    count++, product.getSku(), product.getName(), product.getQuantity());
        }
    }

    public void displayTransactionHistory(int count) {
        System.out.println("\n=== LAST " + count + " TRANSACTIONS ===");

        Iterator<String> iterator = transactionHistory.iterator();
        int displayed = 0;

        while(iterator.hasNext() && displayed < count) {
            System.out.println(iterator.next());
            displayed++;
        }
    }

    public void displayInventoryStatistics() {
        System.out.println("\n=== INVENTORY STATISTICS ===");
        System.out.println("Total Products: " + totalProducts);
        System.out.println("Total Inventory Value: ₹" + String.format("%.2f", totalInventoryValue));

        // Category-wise breakdown
        Map<String, Double> categoryValues = new HashMap<>();
        Map<String, Integer> categoryCounts = new HashMap<>();

        for(Product product : productSet) {
            String category = product.getCategory();
            categoryValues.put(category,
                    categoryValues.getOrDefault(category, 0.0) + product.getInventoryValue());
            categoryCounts.put(category,
                    categoryCounts.getOrDefault(category, 0) + 1);
        }

        System.out.println("\nCategory-wise Breakdown:");
        for(Map.Entry<String, Double> entry : categoryValues.entrySet()) {
            String category = entry.getKey();
            double value = entry.getValue();
            int count = categoryCounts.get(category);
            double percentage = (value / totalInventoryValue) * 100;

            System.out.printf("• %s: %d products, Value: ₹%.2f (%.1f%%)\n",
                    category, count, value, percentage);
        }
    }


    public void searchProduct(String keyword) {
        List<Product> productList = new ArrayList<>(productSet);

        boolean found = false;

        for (Product p : productList) {
            if (p.getSku().toLowerCase().contains(keyword.toLowerCase()) ||
                    p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    p.getCategory().toLowerCase().contains(keyword.toLowerCase())) {

                System.out.println(p);
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ No matching product found!");
        }
    }

    public void showLowStock() {

        System.out.println("\n=== LOW STOCK PRODUCTS ===");

        if (lowStockQueue.isEmpty()) {
            System.out.println("No low stock items!");
            return;
        }

        int count = 1;
        for (Product p : lowStockQueue) {
            System.out.println(count++ + ". " + p);
        }
    }

    public void showTransactions(int n) {

        System.out.println("\n=== LAST " + n + " TRANSACTIONS ===");

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available!");
            return;
        }

        int count = 0;

        for (String t : transactionHistory) {
            if (count++ >= n) break;
            System.out.println(t);
        }
    }

    public void undo() {

        if (undoStack.isEmpty()) {
            System.out.println("❌ No operations to undo!");
            return;
        }

        Product previous = undoStack.pop();

        // Restore old quantity
        for (Product p : productSet) {
            if (p.getSku().equals(previous.getSku())) {

                int currentQty = p.getQuantity();
                p.setQuantity(previous.getQuantity());

                transactionHistory.addFirst(
                        "UNDO: " + p.getSku() + " reverted from "
                                + currentQty + " to " + previous.getQuantity()
                );

                System.out.println("✅ Undo successful!");
                return;
            }
        }

        System.out.println("❌ Product not found for undo!");
    }
}
