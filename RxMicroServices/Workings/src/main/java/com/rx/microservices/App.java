package com.rx.microservices;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Flowable.range(0, 10)
                .map(i -> i * i)
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);
    }
}
