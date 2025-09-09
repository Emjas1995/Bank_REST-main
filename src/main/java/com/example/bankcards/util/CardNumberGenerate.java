package com.example.bankcards.util;

import java.util.Random;

public class CardNumberGenerate {
    public static String generateNumber(){
        String number="";
        String number_out = "";
        while (number.length()<17)
            number += Double.valueOf((Math.floor(Math.random() * 10))).intValue();
        System.out.println(number);
        System.out.println(number.length());
        for(int i=0; i<number.length()-1;i+=4){
            String partofNumber = number.substring(i, i+4);
            number_out+=partofNumber;
            if(i!=16)
                number_out+=" ";
        }
        return number_out;
    }
}
