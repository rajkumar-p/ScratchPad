#ifndef CPPSMARTPOINTERS_FOO_HPP
#define CPPSMARTPOINTERS_FOO_HPP

#include <string>
#include <iostream>

class Foo
{
private:
    int counter;
    std::string name;

public:
    Foo(int counter, std::string name);
    Foo();
    ~Foo();

    int get_counter();
    std::string get_name();

    void set_counter(int counter);
    void set_name(const std::string &name);
};

#endif //CPPSMARTPOINTERS_FOO_HPP
