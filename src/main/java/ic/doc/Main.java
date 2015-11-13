package ic.doc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    
    ArrayList<Integer> nonMappableList = new ArrayList<Integer>(
        Arrays.asList(1, 2, 3, 4));
    MappableList<Integer> list = new MappableList<Integer>(nonMappableList);
    
    UnaryFunction<Integer> slowSquare = new UnaryFunction<Integer>() {
      
      @Override
      public Integer applyTo(Integer elem) {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          System.out.println("Error - Interrupt Exception");
        }
        return elem*elem;
      }
      
    };
    
    try {
      List<Integer> squares = list.doFunction(slowSquare);
    } catch (Exception e) {
      System.out.println("Exception occured.");
    }
    
  }

}
