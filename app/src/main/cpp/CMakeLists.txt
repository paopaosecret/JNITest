# documentation: https://d.android.com/studio/projects/add-native-code.html
#cmake的最低版本
cmake_minimum_required(VERSION 3.4.1)

set(distribution_DIR ../../../../libs)

add_library( fmod

        SHARED

        IMPORTED)

set_target_properties( fmod

        PROPERTIES IMPORTED_LOCATION

        ${distribution_DIR}/${ANDROID_ABI}/libfmod.so)

add_library( fmodL

        SHARED

        IMPORTED)

set_target_properties( fmodL

        PROPERTIES IMPORTED_LOCATION

        ${distribution_DIR}/${ANDROID_ABI}/libfmodL.so)

# 源文件 最终生成的 so
#add_library(变量名A SHARED B.c)
add_library( native-lib SHARED native-lib.cpp )

include_directories(src/main/cpp/inc)

#设置动态依赖库2   会从 ndk中的platforms/android-21/arch-arm/usr/lib/  中查找log库 并赋值给log_lib
find_library( log-lib log )

# 设置依赖的库
# 如果是静态库，需要根据 add_library中设置的变量名B去查找
# 如果是动态库,需要根据  CMAKE_CXX_FLAGS 改变后的路径下去按照库名查找
#target_link_libraries( 要生成的库A  依赖的库B  ${log-lib} )
target_link_libraries( native-lib

        fmod

        fmodL

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
#find_library( log-lib log )

# 为了确保 CMake 可以在编译时定位头文件
# 这样就可以使用 #include <xx.h> 引入
# 否则需要使用 #include "path/xx"
#include_directories( imported-lib/include/ )

# 设置依赖的库
# 如果是静态库，需要根据 add_library中设置的变量名B去查找
# 如果是动态库,需要根据  CMAKE_CXX_FLAGS 改变后的路径下去按照库名查找
#target_link_libraries( 要生成的库A  依赖的库B  ${log-lib} )