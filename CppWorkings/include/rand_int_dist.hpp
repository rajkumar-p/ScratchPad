#ifndef CPPWORKINGS_RAND_INT_DIST_HPP
#define CPPWORKINGS_RAND_INT_DIST_HPP

#include <random>

class RandIntDist {
private:
    std::mt19937 rng;
    const u_int32_t seed;
    const int32_t start;
    const int32_t end;

    std::uniform_int_distribution
            <std::mt19937::result_type> dist;

public:
    RandIntDist(const u_int32_t seed,
            int32_t start,
            int32_t end);

    int32_t next();

    u_int32_t get_seed();
    int32_t get_start();
    int32_t get_end();
};

#endif //CPPWORKINGS_RAND_INT_DIST_HPP
