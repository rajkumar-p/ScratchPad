#include <iostream>
#include "Foo.hpp"

int main() {
    std::unique_ptr<Foo> up1(new Foo(2, "Two"));
    std::cout <<"counter : "<<up1->get_counter()<<
                ", name : "<<up1->get_name()<<std::endl;

    std::unique_ptr<Foo[]> up2(new Foo[3]);

    up2[0].set_counter(4);
    up2[0].set_name("Four");

    up2[1].set_counter(5);
    up2[1].set_name("Five");

    up2[2].set_counter(6);
    up2[2].set_name("Six");

    return 0;
}