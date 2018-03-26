package com.developer.schoolms.utils;

import java.io.File;
import java.util.UUID;

public class DemoConstants {
    public static final String UUID() {
        return UUID.randomUUID().toString();
    }

    public static final String[] USER_TYPE = {
            "ADMIN", "MANAGER", "OPERATOR"
    };

    public static final String[] SECTION_TYPE ={
      "A","B","C","D","E"
    };

    public static final String[] ATTENTANCE_TYPE ={
            "undefined","Present","Absent"
    };

    public static final String[] YEAR_SESSION ={"2074-2075","2075-2076","2076-2077","2077-2078"};

    public static String FILE_REPO = "E:\\schoolMS\\Image";
    public static String STUDENT_REPO = FILE_REPO + File.separator + "student";
}
