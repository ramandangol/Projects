package com.developer.schoolms.utils;

import java.util.UUID;

public class UUIDGenerator {

    private static UUID uuid;

    public static final String UUID() {
        return uuid.randomUUID().toString();
    }
}