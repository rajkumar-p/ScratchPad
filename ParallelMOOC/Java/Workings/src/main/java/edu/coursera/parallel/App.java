package edu.coursera.parallel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        long[] numbers = new long[10000];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = 5;
        }

        Parallels p = new Parallels();
        long sum = p.seqArraySum(numbers);

        System.out.println(sum);

        numbers = new long[10000];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = 5;
        }

        sum = p.parArraySum(numbers);
        System.out.println(sum);
    }
}
