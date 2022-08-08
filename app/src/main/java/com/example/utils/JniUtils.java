package com.example.utils;

public class JniUtils {

    static {
        System.loadLibrary("native-lib");
    }

    // JAVA 调用C++
    public native String stringFromJNI();

    public native int add(int a, int b);

    public native String stringToC(String str);

    public native byte[] convertByte(byte[] bytes);

    // C++ 调用JAVA
    public native void testJNIListenerStaticVoid();

    public native void testJNIListenerVoid();

    public native void testJNIListenerStaticInt();

    public native void testJNIListenerInt();
}
