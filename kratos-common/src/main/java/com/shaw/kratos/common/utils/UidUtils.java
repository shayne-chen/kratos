package com.shaw.kratos.common.utils;

import java.util.UUID;

public class UidUtils {

    public static String generateUid() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        return uuidStr.toLowerCase().replace("-", "").substring(0, 15);
    }

    public static String generateSid(String uid) {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        return uid + uuidStr.toLowerCase().replace("-", "").substring(0, 15);
    }
}
