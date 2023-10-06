package com.driver;

public class NumberMaker {
    public static String createNumber(int digits, int sum){
        String res = helper(digits , sum , "");
        return res;
    }

    private static String helper(int digits, int sum , String comb){
        if(digits==0 && sum==0){
            return comb;
        }

        if(sum < 0 || digits < 0) return null;

        for(int i=1;i<=9;i++){
            String result = helper(digits - 1, sum - i, comb + i);
            if (result != null) {
                return result;
            }
        }

        return null;
    }
}
