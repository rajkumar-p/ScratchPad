#include <iostream>
#include "utils.hpp"
#include "rand_int_dist.hpp"

int main() {
    std::vector<std::string> words = get_words_vector(25);

    std::sort(words.begin(), words.end(), [] (const std::string &s1, const std::string &s2) {
        return s1.length() < s2.length();
    });

    for (std::string word : words) {
        std::cout <<word<<std::endl;
    }

    return 0;
}
