package ic.doc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MappableList<T> extends HigherOrderList<T> {
  
  ExecutorService executor = Executors.newFixedThreadPool(4);
  
  MappableList(List<T> delegate) {
    super(delegate);
  }

  @Override
  public List<T> doFunction(UnaryFunction<T> mapper) throws Exception {
    List<T> result = new ArrayList<T>();
    Future<T> future = null;
    List<Future<T>> futureList = new ArrayList<>(delegate.size());
    for (T elem : delegate) {
      future = executor.submit(new DoFunctionTask<>(mapper, elem));
      futureList.add(future);
    }
    executor.shutdown();
    
    while (!areAllFuturesDone(futureList)) {
      for (int i = 0; i < futureList.size(); i++) {
        Future<T> futureElem = futureList.get(i);
        if (futureElem.isDone() && result.size() == i) {
          result.add(futureElem.get());
        }
      }
    }
    
    try {
      executor.awaitTermination(120, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.out.print("Error - Interrupted Exception");
    }
    
    System.out.println("LIST IS AT END " + result);
    
    return result;
  }
  
  @Override
  public T doFunction(BinaryFunction<T> mapper, T initialValue) {
    return null;
  }
  
  private boolean areAllFuturesDone(List<Future<T>> futureList) {
    
    for (Future<T> future : futureList) {
      if (!future.isDone()) {
        return false;
      }
    }
    
    return true;
    
  }
  
}

class DoFunctionTask<T> implements Callable {
  
  private UnaryFunction<T> mapper;
  private T elem;
  
  public DoFunctionTask(UnaryFunction<T> mapper, T elem) {
    this.mapper = mapper;
    this.elem = elem;
  }

  @Override
  public Object call() throws Exception {
    T newElem = mapper.applyTo(elem);
    return newElem;
  }
  
}