package com.fzt.ktzq.service;

public class Test {
    public static void main(String[] args) {
        String str = "11000000";
        String S = "000000";
        if ((str == "999999" || str == "11000000") && (S.startsWith("22") || S.startsWith("00"))){
            System.out.println("可以");
        } else {
            System.out.println("不可以");
        }
    }
}
