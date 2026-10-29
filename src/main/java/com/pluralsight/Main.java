package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args){


        int selection =Console.displayHomeScreen();
        ArrayList<Product> inventory = getInventory();
        inventory.sort(Comparator.comparing(Product::getName));
        switch (selection){
            case 1 -> {for(int i  = 0; i < inventory.size();i++ ) {
                Product product = inventory.get(i);
                System.out.printf("%d | %s | %.2f%n",
                        product.getId(),
                        product.getName(),
                        product.getPrice());
            }}
            case 2 ->
        }


    }


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

    }





}
