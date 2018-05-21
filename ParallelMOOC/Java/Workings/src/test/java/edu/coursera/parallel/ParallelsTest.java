package edu.coursera.parallel;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ParallelsTest {
    @Test
    public void testSeqArraySum()
    {
        long[] numbers;
        long sum;

        Parallels p = new Parallels();

        numbers = new long[15];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = 15;
        }

        sum = p.seqArraySum(numbers);
        assertEquals(225, sum);

        numbers = new long[10000];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = 5;
        }

        sum = p.seqArraySum(numbers);
        assertEquals(50000, sum);

        numbers = new long[10000];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = 100;
        }

        sum = p.seqArraySum(numbers);
        assertEquals(1000000, sum);
    }

    @Test
    public void testParArraySum()
    {
        long[] numbers;
        long sum;

        Parallels p = new Parallels();

        numbers = new long[15];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = 15;
        }

        sum = p.parArraySum(numbers);
        assertEquals(225, sum);

        numbers = new long[10000];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = 5;
        }

        sum = p.parArraySum(numbers);
        assertEquals(50000, sum);

        numbers = new long[10000];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = 100;
        }

        sum = p.parArraySum(numbers);
        assertEquals(1000000, sum);
    }
}
