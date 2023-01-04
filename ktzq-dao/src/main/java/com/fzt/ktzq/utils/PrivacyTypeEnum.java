package com.fzt.ktzq.utils;

import lombok.Getter;

/**
 * 隐私数据枚举类型
 * @author 黄弋峰 2023/1/4
 */
@Getter
public enum PrivacyTypeEnum {

    /**自定义（此项需设置脱敏的范围）**/
    CUSTOMER,

    /**姓名**/
    NAME,

    /**身份证号**/
    ID_CARD,

    /**手机号**/
    TEL,

    /**邮箱**/
    EMAIL,
}
