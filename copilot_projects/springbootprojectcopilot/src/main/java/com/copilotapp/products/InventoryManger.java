package com.copilotapp.products;

import java.util.HashMap;
import java.util.Map;

//inventory manager class that uses a map and  add ,removes ,list and update products
public class InventoryManger {
    private Map<Integer, Product> inventory;

    public InventoryManger() {
        this.inventory = new HashMap<>();
    }

    // Method to add a product
    public void addProduct(int id, String name, int quantity) {
        if (inventory.containsKey(id)) {
            System.out.println("Product with ID " + id + " already exists.");
        } else {
            Product product = new Product(id, name, quantity);
            inventory.put(id, product);
            System.out.println("Product added: " + product);
        }
    }

    // Method to remove a product
    public void removeProduct(int id) {
        if (inventory.containsKey(id)) {
            Product removedProduct = inventory.remove(id);
            System.out.println("Product removed: " + removedProduct);
        } else {
            System.out.println("Product with ID " + id + " does not exist.");
        }
    }

    // Method to list all products
    public void listProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Products:");
            for (Product product : inventory.values()) {
                System.out.println(product);
            }
        }
    }

    // Method to update a product's quantity
    public void updateProductQuantity(int id, int newQuantity) {
        if (inventory.containsKey(id)) {
            Product product = inventory.get(id);
            product.setQuantity(newQuantity);
            System.out.println("Updated quantity for product: " + product);
        } else {
            System.out.println("Product with ID " + id + " does not exist.");
        }
    }
 // main method to test the InventoryManager class
    public static void main(String[] args) {
        InventoryManger inventoryManager = new InventoryManger();
        inventoryManager.addProduct(1, "Laptop", 10);
        inventoryManager.addProduct(2, "Smartphone", 20);
        inventoryManager.listProducts();
        inventoryManager.updateProductQuantity(1, 15);
        inventoryManager.removeProduct(2);
        inventoryManager.listProducts();
    }

    
}
