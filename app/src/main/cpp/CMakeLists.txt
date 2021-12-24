# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("libsocket")

# .so输出路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../../../libs/${CMAKE_ANDROID_ARCH_ABI}})

include_directories(include/libwebsocket
        include/libuv
#        include/openssl
        include/zlib
        )

add_library(libwebsockets
        STATIC
        IMPORTED)

set_target_properties(libwebsockets
        PROPERTIES
        IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI}/libwebsockets.a)


add_library(libuv
        STATIC
        IMPORTED)

set_target_properties(libuv
        PROPERTIES
        IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI}/libuv.a)


#add_library(libssl
#        STATIC
#        IMPORTED)
#
#set_target_properties(libssl
#        PROPERTIES
#        IMPORTED_LOCATION
#        ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI}/libssl.a)
#
#
#add_library(libcrypto
#        STATIC
#        IMPORTED)
#
#set_target_properties(libcrypto
#        PROPERTIES
#        IMPORTED_LOCATION
#        ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI}/libcrypto.a)


add_library(libz
        STATIC
        IMPORTED)

set_target_properties(libz
        PROPERTIES
        IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI}/libz.a)


# Creates and names a library, sets itinclude_directories() as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        libsocket

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        LwsService.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        libsocket

        # Links the target library to the log library
        # included in the NDK.
        libwebsockets
        libuv
#        libssl
#        libcrypto
        libz
        ${log-lib})