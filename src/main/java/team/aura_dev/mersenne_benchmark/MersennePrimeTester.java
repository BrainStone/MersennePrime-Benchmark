package team.aura_dev.mersenne_benchmark;

import java.math.BigInteger;
import lombok.experimental.UtilityClass;

@UtilityClass
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

  public static boolean modPow_sub_remain(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.modPow(TWO, candidate).subtract(TWO).remainder(candidate);
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

  public static boolean mul_remain_sub(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.multiply(LLval).remainder(candidate).subtract(TWO);
    }

    return LLval.signum() == 0;
  }

  public static boolean mul_remain_sub_remain(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.multiply(LLval).remainder(candidate).subtract(TWO).remainder(candidate);
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

  public static boolean mul_sub_remain(BigInteger candidate, int prime) {
    if (prime == 2) return true;

    BigInteger LLval = FOUR;

    for (int i = 2; i < prime; i++) {
      LLval = LLval.multiply(LLval).subtract(TWO).remainder(candidate);
    }

    return LLval.signum() == 0;
  }
}
