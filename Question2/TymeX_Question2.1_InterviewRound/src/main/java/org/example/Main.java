package org.example;

import org.example.model.Staff;
import org.example.type.FeatureEnum;
import org.example.type.OrderEnum;

public class Main {
    public static void main(String[] args) {
        Staff staff = new Staff();
        var sum = staff.GetTotalInventoryValue();
        System.out.println("- Total Inventory Value: " + sum);

        var prod = staff.FindMostExpensiveProd();
        System.out.println("- Most expensive product" + prod.toString());

        var isHeadPhoneExist = staff.CheckProductIsExisted("Headphones");
        System.out.println("- Headphone is existed: " + isHeadPhoneExist);

        staff.SortInventory(OrderEnum.ASC, FeatureEnum.PRICE);
        System.out.println("** Sort ascending with option Price");
        staff.Print();

        staff.SortInventory(OrderEnum.ASC, FeatureEnum.QUANTITY);
        System.out.println("** Sort ascending with option Quantity");
        staff.Print();

        staff.SortInventory(OrderEnum.DESC, FeatureEnum.PRICE);
        System.out.println("** Sort descending with option Price");
        staff.Print();

        staff.SortInventory(OrderEnum.DESC, FeatureEnum.QUANTITY);
        System.out.println("** Sort descending with option Price");
        staff.Print();
    }
}