#ifndef CPPWORKINGS_CHARBLOCK_HPP
#define CPPWORKINGS_CHARBLOCK_HPP

#include <memory>

class CharChain {
private:
    int _len;
    std::unique_ptr<char> _chain;

public:
    explicit CharChain(int len);
    CharChain(int len, char *chain);

    CharChain(const CharChain& other);
    CharChain* operator=(const CharChain &other);

    int len() const;
    std::unique_ptr<char> copy() const;
};

#endif

