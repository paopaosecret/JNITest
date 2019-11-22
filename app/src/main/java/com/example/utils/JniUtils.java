package com.example.utils;

public class JniUtils {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();

    public native int add(int a, int b);
}
