package com.fzt.ktzq.util;

import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * 加密工具
 * @author 黄弋峰 2022/12/28
 */
public class SMUtil {

    //公钥key必须是16字节，即128位
    private static final String SM4_KEY = "sm4fztktzqv587aa";
    private static final String ALGORITHM_ECB_PKCS5PADDING = "SM4/ECB/PKCS5Padding";
    //指定长度128位
    private static final int DEFAULT_KEY_SIZE = 128;

    static SymmetricCrypto sm4 = new SymmetricCrypto("SM4/ECB/PKCS5Padding", SM4_KEY.getBytes());

    /**
     * 加密函数
     * 为16进制，也可以加密成base64/字节数组::password
     * @param password
     * @return
     */
    public static String encryptSm4(String password) {
        return sm4.encryptHex(password);
    }

    /**
     * 解密函数
     * @param ciphertext
     * @return
     */
    public static String decryptSm4(String ciphertext) {
        return sm4.decryptStr(ciphertext);
    }
}
