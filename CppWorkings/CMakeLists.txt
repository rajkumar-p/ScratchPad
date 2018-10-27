cmake_minimum_required(VERSION 3.12)

# Setup project name
project(CppWorkings)

# Use C++14
set(CMAKE_CXX_STANDARD 14)

# Make debug config the default build type
if (NOT CMAKE_BUILD_TYPE)
    message(STATUS "No build type passed, default to Debug")
    set(CMAKE_BUILD_TYPE "Debug" CACHE STRING "Build type (default Debug)" FORCE)
endif()

# Compiler options for specific configurations
set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -Wall -Wextra -g")
set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -Wall -Wextra")

# Setup include directories
include_directories(include)

add_executable(workings src/main.cpp include/utils.hpp src/utils.cpp include/rand_int_dist.hpp src/rand_int_dist.cpp)

# Package executables to specific directory
set_target_properties(workings PROPERTIES RUNTIME_OUTPUT_DIRECTORY_DEBUG ${CMAKE_SOURCE_DIR}/bin/debug)
set_target_properties(workings PROPERTIES RUNTIME_OUTPUT_DIRECTORY_RELEASE ${CMAKE_SOURCE_DIR}/bin/release)
