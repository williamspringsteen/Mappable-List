package ic.doc;

import java.util.ArrayList;
import java.util.List;

public abstract class HigherOrderList<T> extends ArrayList<T> {

  protected final List<T> delegate;
  
  HigherOrderList(List<T> delegate) {
    this.delegate = delegate;
  }
  
  public abstract List<T> doFunction(UnaryFunction<T> function) throws Exception ;

  public abstract T doFunction(BinaryFunction<T> mapper, T initialValue);
  
}
