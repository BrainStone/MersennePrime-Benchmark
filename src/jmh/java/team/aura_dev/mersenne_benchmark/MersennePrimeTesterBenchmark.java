package team.aura_dev.mersenne_benchmark;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class MersennePrimeTesterBenchmark {
  // M_11   -> not prime
  // M_13   -> prime
  // M_1277 -> not prime
  // M_1279 -> prime
  @Param({"11", "13", "1277", "1279"})
  public int basePrime;

  private BigInteger candidate;

  @Setup(Level.Trial)
  public void setupPrime() {
    candidate = MersenneNumberGenerator.shiftLeft_sub(basePrime);
  }

  @Benchmark
  public void modPow_sub(Blackhole blackhole) {
    blackhole.consume(MersennePrimeTester.modPow_sub(candidate, basePrime));
  }

  @Benchmark
  public void modPow_sub_mod(Blackhole blackhole) {
    blackhole.consume(MersennePrimeTester.modPow_sub_mod(candidate, basePrime));
  }

  @Benchmark
  public void mul_mod_sub(Blackhole blackhole) {
    blackhole.consume(MersennePrimeTester.mul_mod_sub(candidate, basePrime));
  }

  @Benchmark
  public void mul_mod_sub_mod(Blackhole blackhole) {
    blackhole.consume(MersennePrimeTester.mul_mod_sub_mod(candidate, basePrime));
  }

  @Benchmark
  public void mul_sub_mod(Blackhole blackhole) {
    blackhole.consume(MersennePrimeTester.mul_sub_mod(candidate, basePrime));
  }
}
