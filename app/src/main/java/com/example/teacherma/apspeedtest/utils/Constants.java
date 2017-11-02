package com.example.teacherma.apspeedtest.utils;

/**
 * Class to place the static value .
 * <p>
 */

public class Constants {

    public static String BASE_URL = "";
    public static final String DATE_TO_STRING_FORMAT = "yyyy-mm-dd:hh-mm-ss";


    public interface ContentType {
        String
                JSON = "application/json",
                PNG = "image/png",
                JPEG = "image/jpeg",
                FROM_DATA = "multipart/form-data";
    }

    public interface AppSign {
        String
                K_CONTENT_TYPE = "Content-Type",
                V_CONTENT_TYPE = "application/json";
    }

    public interface ResponseCode {
        int
                SUCCESS = 1;
    }

    public interface ResponseError {
        String
                UNKNOWN = "Unknown Error";
    }

    public interface ResultCode {
        int
                LOCAL = 1024,
                REMOTE = 1025;
    }
}
