package com.pluralsight;
import java.util.Scanner;

public class Console {
        private static final Scanner sc = new Scanner(System.in);

        public static int displayHomeScreen() {
            /*What do you want to do?
1- List all products
2- Lookup a product by its id
3- Find all products within a price range
4- Add a new product
5- Quit the application
Enter command:*/

            System.out.println("\nWhat do you want to do?What do you want to do?");
            System.out.println("1- List all products");
            System.out.println("2- Lookup a product by its id");
            System.out.println("3- Find all products within a price range");
            System.out.println("4- Add a new product");
            System.out.println("5- Quit the application");

            return promptForInt("Select an option: ");
        }

        public static int promptForInt(String prompt) {
            System.out.print(prompt);
            return Integer.parseInt(sc.nextLine());
        }

        public static String promptForString(String prompt) {
            System.out.print(prompt);
            return sc.nextLine();
        }

        public static double promptForDouble(String prompt){
            System.out.print(prompt);
            return Double.parseDouble(sc.nextLine());
        }

        public static long promptForLong(String prompt){
            System.out.println(prompt);
            return Long.parseLong(sc.nextLine());

        }
}
