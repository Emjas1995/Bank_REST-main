package com.example.bankcards.util;

import com.example.bankcards.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class CardNumberCrp {

    static public List<Card> crypt(List<Card> cards) throws CloneNotSupportedException {
        List<Card> crypt_cards = new ArrayList<>();
        for(Card card : cards){
            String crp_number = "";
            String number = card.getNumber();
            char[] number_chars = number.toCharArray();
            for(int i=0; i<number.length(); i++){
                if(i> number.length()-6){
                    crp_number+=number_chars[i];
                }
                else if(!number_chars.equals(' ')){
                    crp_number+="*";
                }
                else crp_number+=" ";
            }
            Card crypt_card = (Card) card.clone();
            crypt_card.setNumber(crp_number);
            crypt_cards.add(crypt_card);
        }

        return crypt_cards;
    }
}
