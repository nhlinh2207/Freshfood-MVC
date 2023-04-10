package com.linh.utils;

public class MoneyFormatUtil {

    public static String format(int money){
        String stringMoney = money+"";
        int moneySize = stringMoney.length();
        int division = moneySize / 3;
        if (division == 3)
            return  stringMoney+" đ";
        else{
            StringBuilder s = new StringBuilder(stringMoney);
            for(int i = stringMoney.length() -3; i>=0; i-=3) {
                if(i == 0) break;
                s.insert(i, ',');
            }
            s.append(" đ");
            return s.toString();

//            int j = stringMoney.length() - 1;
//            int indexCount = 0;
//            for (int i = j; i>=0; i--){
//                indexCount++;
//                if ( indexCount % 3 == 0 && i > 0){
//                    stringMoney = new StringBuilder(stringMoney).insert(i, ",").toString();
//                    j = i - 1;
//                    indexCount = 0;
//                }
//            }
//            return stringMoney+" đ";
        }
    }
}
