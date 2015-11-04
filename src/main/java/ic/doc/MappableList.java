package ic.doc;

import java.util.ArrayList;
import java.util.List;

class MappableList<T> extends HigherOrderList<T> {
  
  MappableList(List<T> delegate) {
    super(delegate);
  }

  @Override
  public List<T> doFunction(UnaryFunction<T> mapper) {
    List<T> result = new ArrayList<T>();
    for (T elem : delegate) {
      result.add(mapper.applyTo(elem));
    }
    return result;
  }
  
  @Override
  public T doFunction(BinaryFunction<T> mapper, T initialValue) {
    return null;
  }
  
}