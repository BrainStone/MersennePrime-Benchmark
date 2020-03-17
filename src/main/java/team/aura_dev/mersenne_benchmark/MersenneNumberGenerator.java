package team.aura_dev.mersenne_benchmark;

import java.math.BigInteger;
import java.util.Arrays;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MersenneNumberGenerator {
  private static final BigInteger ZERO = BigInteger.ZERO;
  private static final BigInteger ONE = BigInteger.ONE;

  public static BigInteger rawByteArray(int exponent) {
    final int byteLen = exponent / 8 + 1;
    byte[] byteArray = new byte[byteLen];

    Arrays.fill(byteArray, (byte) 0xff);

    byteArray[0] = (byte) ((1 << (exponent % 8)) - 1);

    return new BigInteger(byteArray);
  }

  public static BigInteger setBit_sub(int exponent) {
    return ZERO.setBit(exponent).subtract(ONE);
  }

  public static BigInteger shiftLeft_sub(int exponent) {
    return ONE.shiftLeft(exponent).subtract(ONE);
  }
}
