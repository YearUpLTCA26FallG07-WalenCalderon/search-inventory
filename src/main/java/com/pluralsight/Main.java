package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args){
        int selection;
        ArrayList<Product> inventory = getInventory();
        inventory.sort(Comparator.comparing(Product::getName));
        do {
            selection = Console.displayHomeScreen();
            Product product;
            switch (selection) {
                case 1 -> {
                    for (int i = 0; i < inventory.size(); i++) {
                        product = inventory.get(i);
                        System.out.printf("%d | %s | %.2f%n",
                                product.getId(),
                                product.getName(),
                                product.getPrice());
                    }
                }
                case 2 -> {
                    product = searchById(inventory);
                    if (product == null) {
                        System.out.println("Product not found");
                    } else {
                        System.out.println("Product found!");
                        System.out.printf("%d | %s | %.2f%n",
                                product.getId(),
                                product.getName(),
                                product.getPrice());
                    }
                }
                case 3 -> {
                    double maxPrice = Console.promptForDouble("Enter your maxPrice: ");
                    double minPrice = Console.promptForDouble("Enter your minPrice");
                    ArrayList <Product> productInRange = searchByPriceRange(maxPrice,minPrice, inventory);
                    if (productInRange.isEmpty()) {
                        System.out.println("Product not found");
                    } else {
                        System.out.println("Product/s found");
                        for(int i = 0; i < productInRange.size(); i++) {
                            product = productInRange.get(i);
                            System.out.printf("%d | %s | %.2f%n",
                                    product.getId(),
                                    product.getName(),
                                    product.getPrice());
                        }
                    }
                }
                case 4 -> {
                    int id = Console.promptForInt("Enter the Id: ");
                    String name = Console.promptForString("Enter name of the product: ");
                    double price = Console.promptForDouble("Enter the price of the product: ");
                    addProduct(id, name, price, inventory);
                    inventory.sort(Comparator.comparing(Product::getName));
                }

            }
        }while(selection != 5);
    }//end of main

    /**
     * this is a method that return the inventory from the inventory.csv
     * @return inventory
     */
    public static ArrayList<Product> getInventory(){
        ArrayList<Product> inventory = new ArrayList<Product>();
        try{
            FileReader  file = new FileReader("src/main/resources/inventory.csv") ;
            BufferedReader  reader = new BufferedReader(file);

            String line;
            while((line = reader.readLine()) != null){
                String[] tokens = line.split("\\|");
                int id = Integer.parseInt(tokens[0]);  //
                String productName = String.valueOf(tokens[1]);
                double price = Double.parseDouble(tokens[2]);
                Product product = new Product(id, productName, price);
                inventory.add(product);
            }

            reader.close();

        }catch(Exception e){
            System.out.println("Error reading file: " + e.getMessage());

        }
        return inventory;

    }//end of get inventory


    public static Product searchById(ArrayList<Product> inventory){
        int id = Console.promptForInt("Enter product ID: ");
        Product product;
        for(int i = 0; i < inventory.size(); i++){
            product = inventory.get(i);

            if(id == product.getId()){
                return product;
            }


        }
        return null;
    }//end of search by id

    public static ArrayList <Product> searchByPriceRange(double maxPrice, double minPrice, ArrayList <Product> inventory){
        ArrayList<Product> productInRange = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++){
            Product product = inventory.get(i);


            if(product.getPrice() > minPrice &&
                    product.getPrice() < maxPrice){
                productInRange.add(product);
            }


        }
        return productInRange;

    }//end of search by price range

    public static void addProduct(int id, String name, double price, ArrayList <Product> inventory){
        Product productToAdd = new Product(id, name, price);
        inventory.add(productToAdd);
    }


}
