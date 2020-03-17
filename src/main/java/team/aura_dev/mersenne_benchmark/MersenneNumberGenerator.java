package team.aura_dev.mersenne_benchmark;

import java.math.BigInteger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MersenneNumberGenerator {
  private static final BigInteger ZERO = BigInteger.ZERO;
  private static final BigInteger ONE = BigInteger.ONE;

  private static final byte ALL_BITS_SET = (byte) 0xff;

  public static BigInteger rawByteArray(int exponent) {
    // Technically it would be $(exponent + 7) / 8$ to get the number of bytes needed to represent
    // `exponent` amount of bits. However we need to include the parity/sign bit. So that changes
    // the calculation to $((exponent + 1) + 7) / 8$, which simplifies to just $exponent / 8 + 1$.
    final int byteLen = exponent / 8 + 1;
    byte[] byteArray = new byte[byteLen];

    // Fill all entries with ones only
    for (int i = 1; i < byteLen; ++i) {
      byteArray[i] = ALL_BITS_SET;
    }

    // The first element (big-endian encoded!) does not have all bits set. The amount of bits to set
    // is the amount of bits "left over" from the previous bit setting. ($exponent % 8$).
    // $(x & 7) <==> (x % 8)$
    byteArray[0] = (byte) ((1 << (exponent & 7)) - 1);

    return new BigInteger(byteArray);
  }

  public static BigInteger setBit_sub(int exponent) {
    return ZERO.setBit(exponent).subtract(ONE);
  }

  public static BigInteger shiftLeft_sub(int exponent) {
    return ONE.shiftLeft(exponent).subtract(ONE);
  }
}
