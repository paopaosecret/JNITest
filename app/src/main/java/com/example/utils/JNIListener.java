package com.example.utils;

public class JNIListener {

    void testVoid(String str){
        System.out.println("JAVA void:" + str);
    }

    int testInt(int a, int b){
        System.out.println("JAVA int a + b = " + (a+b));
        return a+b;
    }

    static void testStaticVoid(String str) {
        System.out.println("JAVA static void:" + str);
    }

    static int testStaticInt(int a, int b){
        System.out.println("JAVA static int a + b = " + (a+b));
        return a+b;
    }
}
