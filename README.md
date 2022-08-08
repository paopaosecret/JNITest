## JNI开发知识整理

### 开发流程
1、native功能的定义与实现
2、native源码编译为库文件
3、使用编译的库文件

#### 第一部分 - native功能的定义与实现
Step 1：在Java 类中定义 native方法，例如
- [Java文件](./app/src/main/java/com/example/utils/JniUtils.java)
public native String stringFromJNI();

Step 2：通过native语言实现该方法，例如
- [C++文件](./app/src/main/cpp/native-lib.cpp)
public native String stringFromJNI();

#### 第二部分 编译库文件
Android studio中的两种编译方式（cmake方式 和 ndk-build方式）
由于最新Android studio默认支持cmake方式，所以使用cmake方式操作
##### Step1 配置CMakeLists.txt文件
-[Cmake文件配置](./app/src/main/cpp/CMakeLists.txt)
其中，主要配置有
[配置CMake版本信息]
cmake_minimum_required(VERSION 3.4.1)

[配置库的信息](库的名称 库的类型 编译库使用的源码)
该指令的主要作用就是将指定的源文件生成链接文件，然后添加到工程中去
add_library( 
        native-lib               
        SHARED 
        native-lib.cpp )

[配置使用的第三方库信息]
find_library(
    log-lib 
    log )
    
    
        
        



