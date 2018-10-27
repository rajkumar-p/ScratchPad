#include "rand_int_dist.hpp"

RandIntDist::RandIntDist(const u_int32_t seed, int32_t start, int32_t end)
    : rng(), seed(seed), start(start), end(end), dist(start, end)
{
    rng.seed(seed);
}

int32_t RandIntDist::next()
{
    return dist(rng);
}

u_int32_t RandIntDist::get_seed()
{
    return seed;
}

int32_t RandIntDist::get_start()
{
    return start;
}

int32_t RandIntDist::get_end()
{
    return end;
}

