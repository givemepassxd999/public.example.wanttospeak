package com.wanttospeak.util;

import java.util.UUID;

public class IdGenerator {

    public static String createId(){
        String id = UUID.randomUUID().toString();
        return id.substring(0,8) + id.substring(9,13) + id.substring(14,18) + id.substring(19,23) + id.substring(24);
    }
}
