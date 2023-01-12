package com.fzt.ktzq.test;

import com.fzt.ktzq.constant.QRCodeUtil;

/**
 * 二维码生成测试类
 * @author 黄弋峰 2023/1/12
 */
public class QrCodeTest {

    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "https://kscgc.sctv-tf.com/sctv/h5/shared/v5/proback_shared.html?newsId=1122551&newsJson=https://kscgc.sctv-tf.com/sctv/lookback/8385/2023/01/10/20230110.json";
        // 嵌入二维码的图片路径
        String imgPath = "D:/图片/111.jpg";
        // 生成的二维码的路径及名称
        String destPath = "D:/图片/QR/Test2.jpg";
        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);

    }

}