package team.aura_dev.mersenne_benchmark;

import java.math.BigInteger;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

@ExtensionMethod(MersennePrimeTester.FastMod.class)
public class MersennePrimeTester {
  private static final BigInteger TWO = BigInteger.valueOf(2);
  private static final BigInteger FOUR = BigInteger.valueOf(4);

  public static boolean modPow_sub(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.modPow(TWO, candidate).subtract(TWO);
    }

    return LLval.signum() == 0;
  }

  public static boolean modPow_sub_mod(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.modPow(TWO, candidate).subtract(TWO).mod(candidate);
    }

    return LLval.signum() == 0;
  }

  public static boolean mul_fastMod_sub(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.multiply(LLval).fastMod(candidate, prime).subtract(TWO);
    }

    return LLval.signum() == 0;
  }

  public static boolean mul_fastMod_sub_fastMod(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval =
          LLval.multiply(LLval).fastMod(candidate, prime).subtract(TWO).fastMod(candidate, prime);
    }

    return LLval.signum() == 0;
  }

  public static boolean mul_mod_sub(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.multiply(LLval).mod(candidate).subtract(TWO);
    }

    return LLval.signum() == 0;
  }

  public static boolean mul_mod_sub_mod(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.multiply(LLval).mod(candidate).subtract(TWO).mod(candidate);
    }

    return LLval.signum() == 0;
  }

  public static boolean mul_sub_fastMod(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.multiply(LLval).subtract(TWO).fastMod(candidate, prime);
    }

    return LLval.signum() == 0;
  }

  public static boolean mul_sub_mod(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.multiply(LLval).subtract(TWO).mod(candidate);
    }

    return LLval.signum() == 0;
  }

  @UtilityClass
  protected static final class FastMod {
    public static final BigInteger fastMod(BigInteger number, BigInteger candidate, int prime) {
      BigInteger tmp = number.and(candidate).add(number.shiftRight(prime));

      return (tmp.compareTo(candidate) >= 0) ? tmp.subtract(candidate) : tmp;
    }
  }
}
