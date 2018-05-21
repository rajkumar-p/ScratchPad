#include "parallels.hpp"
#include <future>
#include <numeric>

int compute_sum_in_parallel(std::vector<int> &numbers)
{
    int compute_sum(std::vector<int> numbers, size_t low, size_t high, int init);

    size_t mid = numbers.size() / 2;
    size_t low1 = 0, high1 = mid;
    size_t low2 = mid + 1, high2 = numbers.size() - 1;

    std::future<int> sum_left = std::async(std::launch::async, compute_sum, numbers, low1, high1, 0);
    std::future<int> sum_right = std::async(std::launch::async, compute_sum, numbers, low2, high2, 0);

    int sum = sum_left.get() + sum_right.get();

    return sum;
}

int compute_sum(std::vector<int> numbers, size_t low, size_t high, int init)
{
    return std::accumulate(numbers.begin() + low, numbers.begin() + high + 1, init);
}