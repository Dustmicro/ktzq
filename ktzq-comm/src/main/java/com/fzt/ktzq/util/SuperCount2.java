package com.fzt.ktzq.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * java swing 方法的可视化计算器
 * @author HuangYiFeng
 */
public class SuperCount2 {

    public static void main(String[] args) {
        JS count = new JS();
    }

    static class JS extends JFrame implements ActionListener {
        private StringBuilder sBuilder = new StringBuilder();  //利用StringBuilder类来显示，以及区分两个操作数
        JTextArea text = new JTextArea();
        double a, b;
        Double sum;
        int i;

        public JS() {
            setBounds(100, 100, 400, 400);
            setTitle("计算器");
            JMenuBar menubar = new JMenuBar();//创建菜单条
            JMenu menu1 = new JMenu("查看(V)");//创建和设置菜单名
            JMenu menu2 = new JMenu("编辑(E)");//创建和设置菜单名
            JMenu menu3 = new JMenu("帮助(H)");//创建和设置菜单名
            menubar.add(menu1);//将菜单加入到菜单条中
            menubar.add(menu2);
            menubar.add(menu3);
            this.setJMenuBar(menubar);//将设置好的菜单条放在窗口中
            this.setLayout(new BorderLayout());//添加文本框
            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();
            text.setPreferredSize(new Dimension(370, 60));//设置组件大小
            p2.setLayout(new FlowLayout());
            p1.add(text);
            this.add(p1, BorderLayout.NORTH);


            p2.setLayout(new GridLayout(5, 4));    //添加按钮
            JButton button[] = new JButton[20];
            button[0] = new JButton("C");        //清空
            button[1] = new JButton("CE");    //清除之前输入的一个数据
            button[2] = new JButton("%");        //取余
            button[3] = new JButton("÷");   //除法
            button[4] = new JButton("7");
            button[5] = new JButton("8");
            button[6] = new JButton("9");
            button[7] = new JButton("x");  //乘号
            button[8] = new JButton("4");
            button[9] = new JButton("5");
            button[10] = new JButton("6");
            button[11] = new JButton("—");  //减号
            button[12] = new JButton("1");
            button[13] = new JButton("2");
            button[14] = new JButton("3");
            button[15] = new JButton("+");  //加号
            button[16] = new JButton("e");    //乘方
            button[17] = new JButton("0");
            button[18] = new JButton(".");  //小数点
            button[19] = new JButton("="); //等于

            for (int i = 0; i < button.length; i++)    //将组件加入容器
                p2.add(button[i]);
            button[19].setBackground(Color.ORANGE);//设置按钮的背景颜色为橙色
            p2.add(button[19]);
            add(p2, BorderLayout.CENTER);

            for (int i = 0; i < button.length; i++)    //为每一个事件(按钮)添加监视器
                button[i].addActionListener(this);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //点击X号可以关闭程序
        }

        public void actionPerformed(ActionEvent e) {      //事件处理
            // TODO Auto-generated method stub
            String lab = e.getActionCommand();            //得到当前的按钮字符，后面进行匹配

            if (lab.equals("0")) {                        //处理按钮 0~9
                sBuilder.append("0");                     //利用StringBuilder类的对象调用方法，将数据存起来
                text.setText(sBuilder.toString());        //显示之前所有输入的数据
            } else if (lab.equals("1")) {
                sBuilder.append("1");                     //利用StringBuilder类的对象调用方法，将数据存起来
                text.setText(sBuilder.toString());        //显示之前所有输入的数据
            } else if (lab.equals("2")) {
                sBuilder.append("2");                     //利用StringBuilder类的对象调用方法，将数据存起来
                text.setText(sBuilder.toString());        //显示之前所有输入的数据
            } else if (lab.equals("3")) {
                sBuilder.append("3");                     //利用StringBuilder类的对象调用方法，将数据存起来
                text.setText(sBuilder.toString());        //显示之前所有输入的数据
            } else if (lab.equals("4")) {
                sBuilder.append("4");
                text.setText(sBuilder.toString());
            } else if (lab.equals("5")) {
                sBuilder.append("5");
                text.setText(sBuilder.toString());
            } else if (lab.equals("6")) {
                sBuilder.append("6");
                text.setText(sBuilder.toString());
            } else if (lab.equals("7")) {
                sBuilder.append("7");
                text.setText(sBuilder.toString());
            } else if (lab.equals("8")) {
                sBuilder.append("8");
                text.setText(sBuilder.toString());
            } else if (lab.equals("9")) {
                sBuilder.append("9");                                  //利用StringBuilder类的对象调用方法，将数据存起来
                text.setText(sBuilder.toString());                     //显示之前所有输入的数据
            } else if (lab.equals("CE")) {                             //处理"CE"按钮事件   即清除当前的输入的数据
                sBuilder.deleteCharAt(sBuilder.length() - 1);    //StringBuilder的实例化对象调用方法下标减1即可
                text.setText(sBuilder.toString());
            } else if (lab.equals("C")) {                              //处理"CE"按钮事件   即清除之前的输入所有的数据
                sBuilder = new StringBuilder();                        //重新创建即可清除之前的输入所有的数据
                text.setText(sBuilder.toString());
            } else if (lab.equals(".")) {                              // 处理按钮 "." 事件
                sBuilder.append(".");                                  //利用StringBuilder类的对象调用方法，将数据存起来
                text.setText(sBuilder.toString());                     //显示之前输入的数据
            } else if (lab.equals("+")) {                              //处理+"按钮乘法
                a = Double.parseDouble(sBuilder.toString());           //将 运算符 之前的数据 作为第一个操作数 a
                i = 0;
                sBuilder = new StringBuilder();                        //将数组清空来存储第二个操作数 b
                text.setText("+");
            } else if (lab.equals("—")) {                              //处理"—"按钮乘法
                a = Double.parseDouble(sBuilder.toString());           //将前面的输入的数 作为第一个操作数a
                i = 1;                                                 //标记运算类型  即"÷"之前的数据即将作为因数
                sBuilder = new StringBuilder();                        //将数组清空来存储第二个操作数 b
                text.setText("—");
            } else if (lab.equals("x")) {                              //处理"x"按钮乘法
                a = Double.parseDouble(sBuilder.toString());           //将前面的输入的数 作为第一个操作数a
                i = 2;                                                 //标记运算类型  即"÷"之前的数据即将作为因数
                sBuilder = new StringBuilder();                        //将数组清空来存储第二个操作数 b
                text.setText("x");
            } else if (lab.equals("÷")) {                              //处理"÷"按钮事件
                i = 3;                                                 //标记运算类型  即"÷"之前的数据即将作为被除数
                a = Double.parseDouble(sBuilder.toString());           //将除法的输入的数 作为第一个操作数a
                sBuilder = new StringBuilder();                        //将数组清空来存储第二个操作数 b
                text.setText("÷");
            } else if (lab.equals("%")) {                              //处理取余运算
                a = Double.parseDouble(sBuilder.toString());           //将前面的输入的数 作为第一个操作数a
                i = 4;                                                 //标记取余运算
                sBuilder = new StringBuilder();                        //将数组清空来存储第二个操作数 b
                text.setText("%");
            } else if (lab.equals("e")) {                              //处理取余运算
                a = Double.parseDouble(sBuilder.toString());           //将前面的输入的数 作为第一个操作数a
                i = 5;                                                 //标记取余运算
                sBuilder = new StringBuilder();                        //将数组清空来存储第二个操作数 b
                text.setText("e");
            } else if (lab.equals("=")) {
                b = Double.parseDouble(sBuilder.toString());           //一遇到"=",我们就能得到第二个操作数b
                if (i == 0) {                                          //加法 a+b
                    sum = a + b;
                    text.setText(sum.toString());                      //输出第二个操作数已经输入的数据
                    sBuilder = new StringBuilder();                    //清空数据，为后面的操作数留空间
                    sBuilder.append(sum);
                } else if (i == 1) {                                   //加法  a-b
                    sum = a - b;
                    text.setText(sum.toString());
                    sBuilder = new StringBuilder();                    //清空数据，为后面的操作数留空间
                    sBuilder.append(sum);
                } else if (i == 2) {                                   //乘法  a*b
                    sum = a * b;
                    text.setText(sum.toString());
                    sBuilder = new StringBuilder();
                    sBuilder.append(sum);
                } else if (i == 3) {                                   //除法 a÷b
                    sum = a / b;
                    text.setText(sum.toString());
                    sBuilder = new StringBuilder();
                    sBuilder.append(sum);
                } else if (i == 4)                                     //	取余      a%b
                {
                    sum = a % b;
                    int m = (int) a;
                    int n = (int) b;
                    sum = (double) m % n;
                    text.setText(sum.toString());
                    sBuilder = new StringBuilder();
                    sBuilder.append(sum);
                } else if (i == 5)                                     //	次幂      a是底数	 b是指数
                {
                    sum = 1.0;
                    if (b == 0)
                        sum = 1.0;
                    else
                        for (int i = 1; i <= b; i++)
                            sum = sum * a;
                    text.setText(sum.toString());
                    sBuilder = new StringBuilder();
                    sBuilder.append(sum);
                } else
                    text.setText(sBuilder.toString());
            }
        }
    }
}
