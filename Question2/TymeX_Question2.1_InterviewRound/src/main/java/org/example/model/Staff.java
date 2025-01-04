package org.example.model;

import org.example.type.FeatureEnum;
import org.example.type.OrderEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Staff {

    //Init
    private List<Product> Inventory = new ArrayList<Product>(){
        {
            add(new Product("Laptop", 999.99, 5));
            add(new Product("SmartPhone", 499.99, 10));
            add(new Product("Tablet", 299.99, 0));
            add(new Product("SmartWatch",199.99, 3));
        }
    };

    public List<Product> getInventory() {
        return Inventory;
    }

    public void Print(){
        for(var prod: Inventory){
            System.out.println("- " + prod.toString());
        }
    }

    //Get total inventory value
    public double GetTotalInventoryValue(){
        double sum =0;
        for(var item : Inventory){
            sum += (item.getPrice() * item.getQuantity());
        }
        return sum;
    };

    //Find most expensive product
    public Product FindMostExpensiveProd() {
        Product prod = new Product("max", 0,0);
        for (var item: Inventory){
            if(prod.getPrice()<= item.getPrice()){
                prod = item;
            }
        }
        return prod;
    };

    //Check product is existed in stock
    public boolean CheckProductIsExisted(String name){
      for(var item: Inventory){
          if(item.getName().equals(name)){
              return true;
          }
      }
      return false;
    };

    //Sort product with option (ASC/DESC, PRICE/QUANTITY)
    public void SortInventory(OrderEnum order, FeatureEnum featureOpt){
        switch (order){
            case ASC:
                sortASC(featureOpt);
                break;
            case DESC:
                sortDesc(featureOpt);
                break;
        }
    }

    private void sortASC(FeatureEnum featureOpt){
        switch (featureOpt){
            case PRICE -> {
                Inventory.sort(Comparator.comparingDouble(Product::getPrice));
            }
            case QUANTITY -> {
                Inventory.sort(Comparator.comparingInt(Product::getQuantity));
            }
        }
    }

    private void sortDesc(FeatureEnum featureOpt){
        switch (featureOpt){
            case PRICE -> {
                Inventory.sort((prod1,prod2)-> Double.compare(prod2.getPrice(), prod1.getPrice()));
            }
            case QUANTITY -> {
                Inventory.sort((prod1,prod2)-> Integer.compare(prod2.getQuantity(), prod1.getQuantity()));
            }
        }
    }

}
