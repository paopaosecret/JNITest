#include <jni.h>
#include <string.h>
#include <string>
#include <android/log.h>
#include "stdio.h"

#define MAX(a,b) (a>b) ? a : b
#define TAG "jnitest" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型
/**
 * JNIEXPORT:jni方法的函数声明关键字
 * jstring:  函数返回值
 * JNICALL:  jni访问的关键字，固定格式
 * Java_comexample_utils_JniUtils_stringFromJNI:jni函数名称（格式：Java_包名_类名_方法名（参数列表）；*注意  (包名中‘.’全用‘_’替换)*）
 * JNIEnv* env:JNI的环境指针，一个非常有用的变量，可以通过它调用所有的JNI函数
 * jobject jobj:函数调用者对象,相当于java层的this
 *
 */

struct node{
    char* name;
    int   value;
}stu1,stu2;

enum week{
    Mon,Tues,Wed,Thurs,Fri,Sat,Sun
};
extern "C" {
    JNIEXPORT jstring JNICALL Java_com_example_utils_JniUtils_stringFromJNI(
            JNIEnv *env,
            jobject jobj) {
        week week1 = Mon;
        switch(week1){
            case Mon:
                LOGE("this is monday");
                break;
        }
        LOGE("c语言方法stringFromJNI 开始执行");
        std::string hello = "Hello from C++";
        stu1.name="Tom";
        stu1.value = 10;
        LOGE("结构体：name：%s,value:%d",stu1.name,stu1.value);
        LOGE("c语言方法stringFromJNI 返回字符串：%s", hello.c_str());
        //string.c_str():将C语言string对象转换为出汗字符指针
        //NewStringUTF(const char* bytes):将C语言的字符指针转换为jni的就string对象

        struct node node1;
        node1.name = "小明";
        node1.value = 20;
        LOGE("结构体：name：%s,value:%d",node1.name,node1.value);
        return env->NewStringUTF(hello.c_str());
    }

    JNIEXPORT jint JNICALL Java_com_example_utils_JniUtils_add(
            JNIEnv *env, jobject /* this */obj, jint a, jint b) {
        LOGE("c语言方法add 开始执行,%d和%d的中较大的数是：%d", a, b, MAX(a, b));
        std::string hello = "Hello from C++";
        LOGE("c语言方法add 方法返回：%d + %d = %d", a, b, a + b);
        return a + b;
    }

    JNIEXPORT jstring JNICALL Java_com_example_utils_JniUtils_stringToC(
            JNIEnv *env, jobject /* this */obj, jstring jstr) {
        LOGE("c语言方法stringToC 开始执行");
        //GetStringUTFChars(jstring, jboolean):  将JNi jstring对象转换为c层的字符指针
        const char *chars = env->GetStringUTFChars(jstr, 0);
        LOGE("c语言方法stringToC 接收变量字符指针的内存地址：%#X", chars);
        int i;
        for (i = 0; i < strlen(chars); i++) {
            LOGE("c语言方法stringToC 第%d个字符：%c", i, chars[i]);
        }

        std::string hello = "Hello from C++";

        jstring str = env->NewStringUTF(chars);
        LOGE("c语言方法stringToC 方法返回：%s", chars);
        return str;
    }

    JNIEXPORT jbyteArray JNICALL Java_com_example_utils_JniUtils_convertByte(
            JNIEnv *env, jobject /* this */obj, jbyteArray bytes) {
        LOGE("c语言方法convertByte 开始执行");
        //java字节数组（jbyteArray） 转为  jni 字节指针（jbyte*）
        jbyte *cbytes = env->GetByteArrayElements(bytes, 0);
        int chars_len = env->GetArrayLength(bytes);

        for (int i = 0; i < chars_len / 2; i++) {
            jbyte a = cbytes[i];
            cbytes[i] = cbytes[chars_len - 1 - i];
            cbytes[chars_len - 1 - i] = a;
        }

        for (int j = 0; j < chars_len; j++) {
            LOGE("c语言方法反转之后 第%d个字符：%c", j, cbytes[j]);
        }

        //jni 字节指针（jbyte*）转为  java字节数组（jbyteArray）
        jbyteArray array = env->NewByteArray(chars_len);
        env->SetByteArrayRegion(array, 0, chars_len, cbytes);
        LOGE("c语言方法convertByte 方法返回");
        return array;
    }

}