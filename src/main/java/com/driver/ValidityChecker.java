package com.driver;

import java.util.*;
public class ValidityChecker {
    public boolean checkValidity(String licenseId){
        for(int i=0;i<licenseId.length()-1;i++){
            if(licenseId.charAt(i) == licenseId.charAt(i+1)) return false;
        }
        return true;
    }

    class CharFreq{
        char c;
        int freq;
        CharFreq(char c, int freq){
            this.c = c;
            this.freq = freq;
        }
    }
    public String createNewId(String licenseId){
        //Store count of all characters
        HashMap<Character , Integer> map = new HashMap<>();
        for(char c : licenseId.toCharArray()){
            map.put(c , map.getOrDefault(c,0)+1);
        }

        //Arrange characters in descending order in pq
        PriorityQueue<CharFreq> pq = new PriorityQueue<>((a,b) -> {
            return b.freq - a.freq;
        });

        //Add all character to pq
        for(char c : map.keySet()){
            pq.add(new CharFreq(c , map.get(c)));
        }

        //Create valid string using pq
        StringBuilder res = new StringBuilder();

        while(pq.size() >= 2){
            //extract characters
            char c1 = pq.remove().c;
            char c2 = pq.remove().c;

            //add to res
            res.append(c1);
            res.append(c2);

            //update count in map
            map.put(c1 , map.get(c1) - 1);
            map.put(c2 , map.get(c2) - 1);

            //Add to pq if count>0
            if(map.get(c1) > 0) pq.add(new CharFreq(c1 , map.get(c1)));
            if(map.get(c2) > 0) pq.add(new CharFreq(c2 , map.get(c2)));
        }

        if(!pq.isEmpty()){
            char c = pq.remove().c;
            if(map.get(c) > 1) return null;
            res.append(c);
        }

        return res.toString();
    }
}
