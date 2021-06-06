package com.shaw.kratos.common.utils;

import java.util.UUID;

public class UidUtils {

    public static String generateUid() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        return uuidStr.toLowerCase().replace("-", "").substring(0, 20);
    }

    public static String generateSid() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        return uuidStr.toLowerCase().replace("-", "").substring(0, 30);
    }
}
