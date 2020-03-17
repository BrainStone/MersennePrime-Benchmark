package team.aura_dev.mersenne_benchmark;

import java.math.BigInteger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MersenneNumberGenerator {
  private static final BigInteger ZERO = BigInteger.ZERO;
  private static final BigInteger ONE = BigInteger.ONE;

  public static BigInteger shiftLeft_sub(int basePrime) {
    return ONE.shiftLeft(basePrime).subtract(ONE);
  }

  public static BigInteger setBit_sub(int basePrime) {
    return ZERO.setBit(basePrime).subtract(ONE);
  }
}
