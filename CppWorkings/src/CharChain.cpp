#include <cstring>
#include "CharChain.hpp"

CharChain::CharChain(int len)
    : _len(len), _chain(new char[len])
{

}

CharChain::CharChain(int len, char *chain)
    : _len(len), _chain(new char[len])
{
    strncpy(_chain.get(), chain, len);
}

CharChain::CharChain(const CharChain& other)
    : _len(other.len()), _chain(std::move(other.copy()))
{

}

CharChain* CharChain::operator=(const CharChain &other)
{
    if (this != &other) {
        this->_len = other.len();
        this->_chain = std::move(other.copy());
    }

    return this;
}

int CharChain::len() const
{
    return _len;
}

std::unique_ptr<char> CharChain::copy() const
{
    std::unique_ptr<char> chain_copy(new char[_len]);
    strncpy(chain_copy.get(), _chain.get(), _len);

    return chain_copy;
}

