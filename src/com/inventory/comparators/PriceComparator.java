package com.inventory.comparators;

import com.inventory.model.Product;

import java.util.Comparator;

public class PriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}
