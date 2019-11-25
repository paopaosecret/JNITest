package com.example.utils;

public class JniUtils {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
//        System.load("D:\\android\\gitproject\\thrid\\JNITest\\app\\build\\intermediates\\cmake\\debug\\obj\\x86_64\\libnative-lib.so");
    }

    public native String stringFromJNI();

    public native int add(int a, int b);

    public native String stringToC(String str);

    public native byte[] convertByte(byte[] bytes);
}
