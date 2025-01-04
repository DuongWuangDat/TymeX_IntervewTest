package org.example;

import java.util.List;

public class Solution {
    public int MissingNumber(List<Integer> arrNum){
        int totalSum =0;
        int sumOfArr= 0;
        int n = arrNum.size();

        for(int i=0; i<n;i++){
            sumOfArr+= arrNum.get(i);
        }

        for(int i=1; i<=n+1;i++){
            totalSum +=i;
        }

        int result = totalSum - sumOfArr;
        return result==0 ? -1 : result;
    };
}
