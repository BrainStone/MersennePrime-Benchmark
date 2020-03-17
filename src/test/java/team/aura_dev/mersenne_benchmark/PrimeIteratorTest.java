package team.aura_dev.mersenne_benchmark;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PrimeIteratorTest {
  private static final PrimeIterator it = new PrimeIterator();

  @Test
  public void getArbitraryPrimeTest() {
    // Check getting the 12345th prime (note 0-based index)
    assertEquals(132241, (int) it.get(12344));
    // Check getting the 1234th prime (note 0-based index)
    assertEquals(10061, (int) it.get(1233));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void getNegativeIndexTest() {
    it.get(-123);
  }

  @Test
  public void iteratorTest() {
    int i = 0;

    for (int prime : it) {
      if (prime >= 10061) break;

      ++i;
    }

    // Should be the 1234th prime (but counting from 0)
    assertEquals(1233, i);
  }
}
