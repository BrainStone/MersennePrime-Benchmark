package team.aura_dev.mersenne_benchmark;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.function.Function;
import org.junit.Test;

public class MersenneNumberGeneratorTest {
  private static final int ITERATIONS = 100_000;

  private static final BigInteger ONE = BigInteger.ONE;

  @Test
  public void shiftLeft_subTest() {
    assertCalculatesCorrectly(ITERATIONS, MersenneNumberGenerator::shiftLeft_sub);
  }

  @Test
  public void setBit_subTest() {
    assertCalculatesCorrectly(ITERATIONS, MersenneNumberGenerator::setBit_sub);
  }

  private static void assertCalculatesCorrectly(
      int iterations, Function<Integer, BigInteger> generator) {
    BigInteger num = ONE;

    for (int i = 1; i < iterations; ++i) {
      assertEquals(num, generator.apply(i));

      num = num.shiftLeft(1).or(ONE);
    }
  }
}
