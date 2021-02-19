package ru.stqa.pft.sandbox;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeTests {
  @Test
  public void testPrime(){
    Assertions.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }
  @Test()
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assertions.assertTrue(Primes.isPrime(n));
  }


  @Test
  public void testNoPrime(){
    Assertions.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }


}

