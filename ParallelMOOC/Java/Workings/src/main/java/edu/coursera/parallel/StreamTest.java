package edu.coursera.parallel;


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {
    public void runSquareTest() {
        final int ARRAY_SIZE = 10000000;
        Integer[] numbers = new Integer[ARRAY_SIZE];

        for (int i = 0; i < ARRAY_SIZE; ++i) {
            numbers[i] = i;
        }

        System.out.println(String.format("Time taken for serial version : %s", seqSquareRoot(numbers)));
        System.out.println(String.format("Time taken for streams(serial) version : %s", seqStreamSquareRoot(numbers)));
        System.out.println(String.format("Time taken for streams(parallel) version : %s", parStreamSquareRoot(numbers)));
    }

    private long seqSquareRoot(Integer[] numbers) {
        Double[] squareRoot = new Double[numbers.length];

        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < numbers.length; ++i) {
            squareRoot[i] = Math.sqrt(numbers[i]);
        }
        final long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private long seqStreamSquareRoot(Integer[] number) {
        final long startTime = System.currentTimeMillis();
        List<Double> squareRoot = Arrays.stream(number)
                                        .map(n -> Math.sqrt(n))
                                        .collect(Collectors.toList());
        final long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private long parStreamSquareRoot(Integer[] numbers) {
        final long startTime = System.currentTimeMillis();
        List<Double> squareRoot = Arrays.stream(numbers)
                                        .parallel()
                                        .map(n -> Math.sqrt(n))
                                        .collect(Collectors.toList());
        final long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }
}
