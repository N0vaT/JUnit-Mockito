package edu.school21.numbers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {

    private NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest(name = "number - {0} is prime")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 109})
    void isPrimeForPrimes(int argument){
        assertTrue(numberWorker.isPrime(argument));
    }
    @ParameterizedTest(name = "number - {0} is not prime")
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 110})
    void isPrimeForNotPrimes(int argument){
        assertFalse(numberWorker.isPrime(argument));
    }
    @ParameterizedTest(name = "Error! number - {0} is not correct")
    @ValueSource(ints = {-100, -1, 0, 1})
    void isPrimeForIncorrectNumbers(int argument){
        assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(argument));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void digitsSum(int num, int sum){
        assertEquals(numberWorker.digitsSum(num),sum);
    }
}