package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы 2
*/

public class Solution {

    public static class Product {
        private int id;
        private String productName, price, quantity;

        public Product(int id, String productName, String price, String quantity) {
            this.id = id;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("%-8d%-30s%-8s%-4s", id, productName, price, quantity);
        }

        public int getId() {
            return id;
        }
    }

    public static void main(String[] args) throws IOException {
        int l = args.length;
        if (l == 0) return;

        List<Product> productList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();

        // read file
        try (BufferedReader bufferedFileReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedFileReader.ready()) {
                productList.add(getProduct(bufferedFileReader.readLine()));
            }
        }

        switch (args[0]) {
            case "-u": {
                // read params
                int id = Integer.parseInt(args[1]);

                String name = "";
                for (int i = 2; i < l - 2; i++) name += args[i] + " ";
                if (name.length() > 30) name = name.substring(0, 30);

                String price = args[l - 2];
                if (price.length() > 8) price = price.substring(0, 8);

                String quantity = args[l - 1];
                if (quantity.length() > 4) quantity = quantity.substring(0, 4);

                Product updProduct = new Product(id, name.trim(), price, quantity);

                for (int i = 0; i < productList.size(); i++) {
                    Product curProduct = productList.get(i);
                    if (curProduct.getId() == id) {
                        productList.set(i, updProduct);
                        break;
                    }
                }
                break;
            }

            case "-d": {
                // read params
                int id = Integer.parseInt(args[1]);

                for (int i = 0; i < productList.size(); i++) {
                    Product curProduct = productList.get(i);
                    if (curProduct.getId() == id) {
                        productList.remove(i);
                        break;
                    }
                }
                break;
            }
        }

        // write file
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Product prod : productList) {
                fileWriter.write(prod.toString() + "\n");
            }
        }
    }

    public static Product getProduct(String productLine) {
        int id = Integer.parseInt(productLine.substring(0, 8).trim());
        String productName = productLine.substring(8, 38);
        String price = productLine.substring(38, 46);
        String quantity = productLine.substring(46, 50);
        return new Product(id, productName, price, quantity);
    }
}
