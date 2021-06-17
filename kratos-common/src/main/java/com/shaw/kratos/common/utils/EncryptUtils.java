package com.shaw.kratos.common.utils;

import org.springframework.util.DigestUtils;

public class EncryptUtils {

    public static String encryptByMd5(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }
}
