package ic.doc;

public interface BinaryFunction<T> {
  T applyTo(T accumulator, T elem);
}
