#include "catch.hpp"
#include "parallels.hpp"

TEST_CASE("Tests: [compute_sum_in_parallel(numbers)]", "[compute_sum_in_parallel(numbers)]")
{
    SECTION("Test1") {
        std::vector<int> numbers(1000, 1);
        REQUIRE(compute_sum_in_parallel(numbers) == 1000);
    }

    SECTION("Test2") {
        std::vector<int> numbers(10000, 1);
        REQUIRE(compute_sum_in_parallel(numbers) == 10000);
    }

    SECTION("Test3") {
        std::vector<int> numbers(7000, 70);
        REQUIRE(compute_sum_in_parallel(numbers) == 490000);
    }

    SECTION("Test4") {
        std::vector<int> numbers(340, 99);
        REQUIRE(compute_sum_in_parallel(numbers) == 33660);
    }
}