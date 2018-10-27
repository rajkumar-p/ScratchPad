#include "utils.hpp"
#include "rand_int_dist.hpp"

std::vector<std::string> get_words_vector(size_t no_of_words)
{
    RandIntDist word_len_dist(std::random_device()(), 4, 15);
    RandIntDist char_dist(std::random_device()(), 97, 122);

    std::vector<std::string> words;
    for (size_t i = 0; i < no_of_words; ++i) {
        size_t word_len = word_len_dist.next();
        std::string word = "";
        for (size_t j = 0; j < word_len; ++j) {
            word.push_back(char_dist.next());
        }

        words.push_back(word);
    }

    return words;
}

