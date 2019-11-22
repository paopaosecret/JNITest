#include <jni.h>
#include <string>
#include <android/log.h>

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
extern "C" JNIEXPORT jstring JNICALL Java_com_example_utils_JniUtils_stringFromJNI(
        JNIEnv* env,
        jobject  jobj) {
    LOGE("c语言方法stringFromJNI 开始执行");
    std::string hello = "Hello from C++";
    LOGE("c语言方法stringFromJNI 返回字符串：%s", hello.c_str());
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL Java_com_example_utils_JniUtils_add(
        JNIEnv* env,  jobject /* this */obj, jint a, jint b) {
    LOGE("c语言方法add 开始执行");
    std::string hello = "Hello from C++";
    LOGE("c语言方法add 方法返回：%d + %d = %d", a,b,a+b);
    return a+b;
}