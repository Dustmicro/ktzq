package com.fzt.ktzq.service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

/**
 * 各种方法测试验证
 * @author 黄弋峰
 */
public class Test {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException {
//        String str = "11000000";
//        String S = "000000";
//        if ((str == "999999" || str == "11000000") && (S.startsWith("22") || S.startsWith("00"))){
//            System.out.println("可以");
//        } else {
//            System.out.println("不可以");
//        }
        String str = "精品大额存单死锅唉";
        System.out.println("原字符串为：" + str);
        System.out.println("后四位是：" + str.substring(0, str.length()-4));
        if (str.contains("大额存单")){
            System.out.println("匹配成功");
            return;
        } else {
            System.out.println("匹配不通过");
        }
//        String str = "A";
//        if (str != null && ("A".equals(str) || "B".equals(str))){
//            System.out.println("满足");
//        }
//        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");//"DES/CBC/PKCS5Padding"是一个算法名称

    }
}
