#include <iostream>
#include "CharChain.hpp"
#include "utils.hpp"
#include "rand_int_dist.hpp"

int main() {
    char name[] = "Alice";
    CharChain cc1 {sizeof(name), name};
    std::cout << "Created CharChain "
                 "with name : " << cc1.copy() <<
                 ", and len : " << cc1.len() <<
                 std::endl;

    char another_name[] = "Charlie";
    CharChain cc2 {sizeof(another_name), another_name};
    std::cout << "Created CharChain copy"
                 "with name : " << cc2.copy() <<
                 ", and len : " << cc2.len() <<
                 std::endl;

    CharChain cc3 = cc1;
    std::cout << "Created CharChain copy"
                 "with name : " << cc3.copy() <<
                 ", and len : " << cc3.len() <<
                 std::endl;

    cc3 = cc2;
    std::cout << "Created CharChain copy"
                 "with name : " << cc3.copy() <<
                 ", and len : " << cc3.len() <<
                 std::endl;

    std::cout <<"Size of a pointer : "<<sizeof(float *)<<std::endl;

    return 0;
}
