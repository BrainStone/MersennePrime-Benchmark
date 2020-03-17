package team.aura_dev.mersenne_benchmark;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class MersenneNumberGeneratorBenchmark {
  @Param({"1", "10", "100", "1000"})
  public int nthPrime;

  private int prime;

  @Setup(Level.Trial)
  public void setupPrime() {
    prime = new PrimeIterator().get(nthPrime);
  }

  @Benchmark
  public void shiftLeft_sub(Blackhole blackhole) {
    blackhole.consume(MersenneNumberGenerator.shiftLeft_sub(prime));
  }

  @Benchmark
  public void setBit_sub(Blackhole blackhole) {
    blackhole.consume(MersenneNumberGenerator.setBit_sub(prime));
  }
}
