# Workings - C++

## Overview
This directory is a slate to various C++ programs for the following MOOC course - [Parallel Programming](https://www.coursera.org/learn/parallel-programming-in-java)

## Build Instructions
* Create a new directory, **build**
* Cd into **build**, run **cmake -DCMAKE_BUILD_TYPE=Debug ..** for debug configuration and **cmake -DCMAKE_BUILD_TYPE=Release ..** for release configuration
* Run **make**, after the above command completes
* Targets are copied over to **{Project.Dir}/bin/{Target}/ws**
* Test target is copied to **{Project.Dir}/bin/test/ws_tests**

## Pre-requisites
* C++11 compiler. gcc/clang/vc++ later versions support C++11 features. To install one, either build from source or use a package manager for your platform
* CMake. Version 3.7.0 or greater. The project uses CMake to generate the build files  

## Tests
Tests are implemented with the help of the [Catch](https://github.com/philsquared/Catch) test framework.

## License
Free as in free speech.

## Contributions & Questions
Send me a mail on <raj@diskodev.com> or tweet me at <https://twitter.com/rajkumar_p>
