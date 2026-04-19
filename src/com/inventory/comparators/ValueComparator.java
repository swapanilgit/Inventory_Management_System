package com.inventory.comparators;

import com.inventory.model.Product;

import java.util.Comparator;

public class ValueComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p2.getInventoryValue(), p1.getInventoryValue()); // Descending
    }
}
