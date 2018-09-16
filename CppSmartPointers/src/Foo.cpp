#include "Foo.hpp"

Foo::Foo(int counter, std::string name)
    : counter(counter), name(name)
{
    std::cout <<"Constructing Foo"<<std::endl;
}

Foo::Foo()
    : counter(0), name("")
{
    std::cout <<"Constructing default Foo"<<std::endl;
}

Foo::~Foo()
{
    std::cout <<"Destructing Foo with counter : "<<
                this->get_counter()<<", and name : "<<
                this->get_name()<<std::endl;
}

std::string Foo::get_name()
{
    return name;
}

int Foo::get_counter()
{
    return counter;
}

void Foo::set_counter(int counter)
{
    this->counter = counter;
}

void Foo::set_name(const std::string &name)
{
    this->name = name;
}
