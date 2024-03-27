package com.vijay.commentservice.model;

import java.time.Instant;

public class IdGenerator {

    public static Long generateUniqueId() {
        // Generate a unique timestamp-based ID
        return Instant.now().toEpochMilli();
    }
}
