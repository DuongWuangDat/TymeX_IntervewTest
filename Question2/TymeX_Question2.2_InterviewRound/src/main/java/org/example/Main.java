package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Input data
        Scanner scanner = new Scanner(System.in);
        List<Integer> arrNum = new ArrayList<>();

        System.out.print("- Input n: ");
        var n = scanner.nextInt();

        for(int i=0; i<n;i++){
            var num = scanner.nextInt();
            arrNum.add(num);
        }

        //Solution
        var solution = new Solution();
        int rs= solution.MissingNumber(arrNum);
        System.out.println(rs);


    }


}