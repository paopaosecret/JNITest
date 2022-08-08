# documentation: https://d.android.com/studio/projects/add-native-code.html
#TODO 1cmake的最低版本
cmake_minimum_required(VERSION 3.4.1)

#TODO 设置变量
set(distribution_DIR ../../../../libs)

#TODO 2设置编译类型
#add_executable(demo demo.cpp)          # 生成可执行文件
#add_library(demo STATIC demo.cpp)      # 生成静态库
#add_library(demo SHARED demo.cpp)      # 生成动态库或共享库

#TODO 3指定编译包含的源文件 add_library(demo demo.cpp test.cpp util.cpp)

#该指令的主要作用就是将指定的源文件生成链接文件，然后添加到工程中去。该指令常用的语法如下：
#add_library(<name> [STATIC | SHARED | MODULE]
#[EXCLUDE_FROM_ALL]
#[source1] [source2] [...])
#其中<name>表示库文件的名字，该库文件会根据命令里列出的源文件来创建。
#而STATIC、SHARED和MODULE的作用是指定生成的库文件的类型。
#STATIC库是目标文件的归档文件，在链接其它目标的时候使用。
#SHARED库会被动态链接（动态链接库），在运行时会被加载。
#MODULE库是一种不会被链接到其它目标中的插件，但是可能会在运行时使用dlopen-系列的函数。
#默认状态下，库文件将会在于源文件目录树的构建目录树的位置被创建，该命令也会在这里被调用。
# 源文件 最终生成的 so
#add_library(变量名A SHARED B.c)
add_library( native-lib SHARED native-lib.cpp )

#TODO 设置动态依赖库2   会从 ndk中的platforms/android-21/arch-arm/usr/lib/  中查找log库 并赋值给log_lib
#TODO 类似的命令还有 find_file()、find_path()、find_program()、find_package()
#可以得到库文件的绝对路径
find_library( log-lib log )

# 设置依赖的库
# 如果是静态库，需要根据 add_library中设置的变量名B去查找
# 如果是动态库,需要根据  CMAKE_CXX_FLAGS 改变后的路径下去按照库名查找
#target_link_libraries( 要生成的库A  依赖的库B  ${log-lib} )
# 该指令的作用为将目标文件与库文件进行链接。该指令的语法如下：
#上述指令中的<target>是指通过add_executable()和add_library()指令生成已经创建的目标文件。而[item]表示库文件没有后缀的名字。默认情况下，库依赖项是传递的。当这个目标链接到另一个目标时，链接到这个目标的库也会出现在另一个目标的连接线上。这个传递的接口存储在interface_link_libraries的目标属性中，可以通过设置该属性直接重写传递接口。
target_link_libraries( native-lib

        ${log-lib} )

#说明文档
#cmake的最低版本
#cmake_minimum_required(VERSION 3.6)

# ${ANDROID_ABI} 构建的COU
# ${CMAKE_SOURCE_DIR} cmakeList.txt所在的文件夹路径
# message("","") 相当于log
# add_subdirectory(路径）  引入路径下的 cmakeList
# 源文件 最终生成的 so
#add_library(变量名A SHARED B.c)
#add_library(变量名A STATIC B.c)

# 源文件 最终生成的 so   多文件设置
#file(GLOB 变量名SRCC  src/main/cpp/*.c  src/main/cpp/*.cpp)
#add_library(变量名A SHARED ${SRCC})

# =========================添加依赖库1=================================
# 设置静态库以导入的形式加入到项目中来
#add_library(变量名B STATIC IMPORTED)
#set_target_properties(变量名B PROPERTIES IMPORTED_LOCATION 路径1/xx.a  路径2/xxx.so)

# ========================= 添加依赖库2 =================================

#设置动态依赖库1
# 添加动态库,为了android版本之间的兼容性，需要设置一个变量
# CMAKE_CXX_FLAGS c++的参数 会传给编译器
# CMAKE_C_FLAGS c的参数 会传给编译器
# 重新定义 CMAKE_CXX_FLAGS 变量的内容   （如果是c文件，需要用 CMAKE_C_FLAGS ）
# CMAKE_SOURCE_DIR 当前文件的地址
# ANDROID_ABI 当前手机的ABI
#set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}  -L${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}")

#设置动态依赖库2   会从 ndk中的platforms/android-21/arch-arm/usr/lib/  中查找log库 并赋值给log_lib
#可以得到库文件的绝对路径
#find_library( log-lib log )

# 为了确保 CMake 可以在编译时定位头文件
# 这样就可以使用 #include <xx.h> 引入
# 否则需要使用 #include "path/xx"
#include_directories( imported-lib/include/ )

# 设置依赖的库
# 如果是静态库，需要根据 add_library中设置的变量名B去查找
# 如果是动态库,需要根据  CMAKE_CXX_FLAGS 改变后的路径下去按照库名查找
#target_link_libraries( 要生成的库A  依赖的库B  ${log-lib} )