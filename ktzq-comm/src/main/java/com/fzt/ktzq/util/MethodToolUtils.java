package com.fzt.ktzq.util;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * 方法类工具合集
 * @author HuangYiFeng
 */
public class MethodToolUtils {

    /**
     * 用户输入方法
     */
    public static void userInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入第一个数字：");
        int num1 = input.nextInt();
        System.out.print("请输入运算符(+、-、*、/)：");
        String operator = input.next();
        System.out.print("请输入第二个数字：");
        int num2 = input.nextInt();
        int sum = MethodToolUtils.superCount(num1, operator, num2);
        //转中文输出
        String sumCn = ConvertUpMoney.toChinese(String.valueOf(sum));
        System.out.println("计算结果是：" + num1 + " " + operator + " " + num2 + " " + "= " + sum + " ========> " + sumCn);
    }

    /**
     * 计算方法
     * @param num1
     * @param symbol
     * @param num2
     * @return
     */
    public static int superCount(int num1, String symbol, int num2) {
        int sum = 0;
        switch (symbol) {
            case "+":
                sum = num1 + num2;
                break;
            case "-":
                sum = num1 - num2;
                break;
            case "*":
                sum = num1 * num2;
                break;
            case "/":
                sum = num1 / num2;
                break;
            default:
                System.out.println("要不你猜猜为什么计算失败？");
                break;
        }
        return sum;
    }


    /**
     * 加载界面进度条展示
     */
    public static String progressBar(String flag) {
        char incomplete = ' '; // U+2591 Unicode Character 表示还没有完成的部分
        char complete = '>'; // U+2588 Unicode Character 表示已经完成的部分
        int total = 100;
        StringBuilder builder = new StringBuilder();
        Stream.generate(() -> incomplete).limit(total).forEach(builder::append);
        int a = 0;
        for (int i = 0; i < total; i++) {
            builder.replace(i, i + 1, String.valueOf(complete));
            String progressBar = "\r" + builder;
            String percent = " " + (i + 1) + "%";
            System.out.print(progressBar + percent);
            a = a + 1;
            try {
                // 这里为了表示越到后面越慢的场景，所以这里的sleep不是一个固定的数值。
                Thread.sleep(i * 5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (a == 100) {
            return flag = "OK";
        }
        return flag;
    }
}
