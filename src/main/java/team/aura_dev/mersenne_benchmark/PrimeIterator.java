package team.aura_dev.mersenne_benchmark;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class PrimeIterator implements Iterable<Integer>, Iterator<Integer>, Supplier<Integer> {
  private static final List<Integer> primes = new LinkedList<Integer>(Arrays.asList(2, 3));

  private int currentCandidate;
  private int currentIndex;

  public PrimeIterator() {
    currentCandidate = 5;
    currentIndex = 0;
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Integer next() {
    return get();
  }

  @Override
  public Iterator<Integer> iterator() {
    return this;
  }

  @Override
  public Integer get() {
    return get(currentIndex++);
  }

  /**
   * Gets the nth prime.<br>
   * <b>The first prime has the index 0!</b>
   *
   * @param index the index of the wanted prime, starting at index 0
   * @return the prime at the specified 0-based index
   */
  public Integer get(int index) {
    if (index < 0) throw new IndexOutOfBoundsException("index must not be negative. Was " + index);

    if (index >= primes.size()) {
      synchronized (primes) {
        for (; index >= primes.size(); currentCandidate += 6) {
          checkPrime(currentCandidate);
          checkPrime(currentCandidate + 2);
        }
      }
    }

    return primes.get(index);
  }

  private static void checkPrime(int candidate) {
    if (isPrime(candidate)) {
      primes.add(candidate);
    }
  }

  /**
   * Checks whether the passed number is prime or not.<br>
   * This method performs divisibility tests only up to the floor of the square root of the number.
   * It also skips numbers that a multiples of 2 and 3.
   *
   * @param number The number to be checked for primality.
   * @return Whether number is prime or not
   */
  private static boolean isPrime(int candidate) {
    final int upperBound = (int) Math.sqrt(candidate);
    final Iterator<Integer> it = primes.iterator();
    int divisor = it.next();

    while (divisor <= upperBound) {
      if (isDivisibleBy(candidate, divisor)) return false;

      divisor = it.next();
    }

    return true;
  }

  /**
   * Simple check for divisibility.
   *
   * @param number Number to be checked for divisibility.
   * @param divisor Divisor for divisibility check.
   * @return If number is divisible by divisor false otherwise true.
   */
  private static boolean isDivisibleBy(int number, int divisor) {
    return (number % divisor) == 0;
  }
}
