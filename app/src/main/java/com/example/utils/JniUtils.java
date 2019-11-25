package com.example.utils;

public class JniUtils {

    //定义音效类型常量
    public static final  int zhengchang = 0;//正常
    public static final int luoli = 1;//萝莉
    public static final int dashu = 2;//大叔
    public static final int jingsong = 3;//惊悚
    public static final int gaoguai = 4;//搞怪
    public static final int kongling = 5;//空灵

    static {
        System.loadLibrary("fmodL");
        System.loadLibrary("fmod");
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();

    public native int add(int a, int b);

    public native String stringToC(String str);

    public native byte[] convertByte(byte[] bytes);

    public native void playSound(String path,int type);
}
