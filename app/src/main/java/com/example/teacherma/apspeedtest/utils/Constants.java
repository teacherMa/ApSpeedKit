package com.example.teacherma.apspeedtest.utils;

/**
 * Class to place the static value .
 * <p>
 */

public class Constants {

    public static String BASE_URL = "";
    public static final String DATE_TO_STRING_FORMAT = "yyyy-MM-dd  hh:mm:ss";
    public static final String DOT = ".";

    public interface PortFilter {
        int
                MAX_PORT = 65535,
                MIN_PORT = 10000;
    }

    public interface IpAddressFilter {
        int
                MAX_IP = 255,
                MIN_IP = 0;
    }

    public interface NetWorkError {
        String
                WIFI_CONNECTION_ERROR = "Wifi doesn't work",
                UNKNOWN_ERROR = "Unknown error",
                ACCESS_NETWORK_FAILED = "Access network state failed";
    }

    public interface BuildNewTestResult {
        int
                SUCCESS = 0;
    }

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
