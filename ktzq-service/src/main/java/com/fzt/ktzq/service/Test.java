package com.fzt.ktzq.service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class Test {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException {
//        String str = "11000000";
//        String S = "000000";
//        if ((str == "999999" || str == "11000000") && (S.startsWith("22") || S.startsWith("00"))){
//            System.out.println("可以");
//        } else {
//            System.out.println("不可以");
//        }
        String str = "12345430000";
        System.out.println("原字符串为：" + str);
        System.out.println("后四位是：" + str.substring(0, str.length()-4));
//        String str = "A";
//        if (str != null && ("A".equals(str) || "B".equals(str))){
//            System.out.println("满足");
//        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");//"DES/CBC/PKCS5Padding"是一个算法名称

    }
}
