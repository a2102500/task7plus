package com.ljm.util;
import java.util.Random;

/**
 * Created by liujm on 2017/8/4.


 import java.util.Random;

 /**
 * Created by CK on 2017/7/24.
 */
public class RandNum {
    /**
     * 产生随机的六位数
     */
    public static String getSix(){
        Random rad=new Random();

        String result  = rad.nextInt(1000000) +"";

        if(result.length()!=6){
            return getSix();
        }
        return result;
    }

    //生成六位数验证码
    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            System.out.println(getSix());
        }
    }
}

