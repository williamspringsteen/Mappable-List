package ic.doc;

import java.util.List;

public class FoldableList<T> extends HigherOrderList<T> {

  FoldableList(List<T> delegate) {
    super(delegate);
  }

  @Override
  public T doFunction(BinaryFunction<T> folder, T initialValue) {
    T accumulator = initialValue;
    for (T elem : delegate) {
      accumulator = (folder.applyTo(accumulator, elem));
    }
    return accumulator;
  }

  @Override
  public List<T> doFunction(UnaryFunction<T> function) {
    return null;
  }
  
}
