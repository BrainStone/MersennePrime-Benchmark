package team.aura_dev.mersenne_benchmark;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.function.BiFunction;
import org.junit.Test;

public class MersennePrimerTesterTest {
  @Test
  public void modPow_sub_test() {
    assertCalculatesCorrectly(MersennePrimeTester::modPow_sub);
  }

  @Test
  public void modPow_sub_mod_test() {
    assertCalculatesCorrectly(MersennePrimeTester::modPow_sub_mod);
  }

  @Test
  public void mul_mod_sub_test() {
    assertCalculatesCorrectly(MersennePrimeTester::mul_mod_sub);
  }

  @Test
  public void mul_mod_sub_mod_test() {
    assertCalculatesCorrectly(MersennePrimeTester::mul_mod_sub_mod);
  }

  @Test
  public void mul_sub_mod_test() {
    assertCalculatesCorrectly(MersennePrimeTester::mul_sub_mod);
  }

  private static void assertCalculatesCorrectly(BiFunction<BigInteger, Integer, Boolean> tester) {
    assertIsMersennePrime(2, true, tester);
    assertIsMersennePrime(3, true, tester);
    assertIsMersennePrime(5, true, tester);
    assertIsMersennePrime(7, true, tester);
    assertIsMersennePrime(11, false, tester);
    assertIsMersennePrime(13, true, tester);

    assertIsMersennePrime(1277, false, tester);
    assertIsMersennePrime(1279, true, tester);
    assertIsMersennePrime(1283, false, tester);

    assertIsMersennePrime(1234, false, tester);

    // TODO: Edge cases where there's a negative number in the calculation
    // Might not exist: https://www.mersenneforum.org/showthread.php?t=25373
  }

  private static void assertIsMersennePrime(
      int basePrime, boolean mersennePrime, BiFunction<BigInteger, Integer, Boolean> tester) {
    BigInteger candidate = MersenneNumberGenerator.shiftLeft_sub(basePrime);

    assertEquals(
        "Test case 2^" + basePrime + " - 1", mersennePrime, tester.apply(candidate, basePrime));
  }
}
