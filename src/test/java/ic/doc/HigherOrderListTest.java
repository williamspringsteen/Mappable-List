package ic.doc;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class HigherOrderListTest {
  
  @Test
  public void doesMappableListMapOverEmptyListAndReturnEmptyList() {
    ArrayList<String> nonMappableList = new ArrayList<String>();
    MappableList<String> list = new MappableList<String>(nonMappableList);
    
    Iterable<String> questions = list.doFunction(new UnaryFunction<String>() {
      @Override
      public String applyTo(String elem) {
        return elem+"?";
      }
    });
    ArrayList<String> questionList = new ArrayList<String>();
    assertThat(((ArrayList<String>) questions).toArray(), is(questionList.toArray()));
  }
  
  @Test
  public void doesMappableListMapSquareFunctionOverListOfIntegers() {
    ArrayList<Integer> nonMappableList = new ArrayList<Integer>(
        Arrays.asList(1, 2, 3, 4));
    MappableList<Integer> list = new MappableList<Integer>(nonMappableList);
    
    Iterable<Integer> squares = list.doFunction(new UnaryFunction<Integer>() {
      @Override
      public Integer applyTo(Integer elem) {
        return elem*elem;
      }
    });
    ArrayList<Integer> squaredList = new ArrayList<Integer>(
        Arrays.asList(1, 4, 9, 16));
    assertThat(((ArrayList<Integer>) squares).toArray(), is(squaredList.toArray()));
  }
  
  @Test
  public void doesFoldableListMultiplyAllElementsOfListWithMultiplyFunctionOverListOfIntegers() {
    ArrayList<Integer> nonFoldableList = new ArrayList<Integer>(
        Arrays.asList(1, 2, 3, 4));
    FoldableList<Integer> list = new FoldableList<Integer>(nonFoldableList);
    
    Integer multiplyAll = list.doFunction(new BinaryFunction<Integer>() {
      @Override
      public Integer applyTo(Integer elem1, Integer elem2) {
        return elem1*elem2;
      }
    }, 1);
    assertThat(multiplyAll, is(24));
  }
  
  @Test
  public void doesFoldableListAddAllElementsOfListWithMultiplyFunctionOverListOfIntegers() {
    ArrayList<Integer> nonFoldableList = new ArrayList<Integer>(
        Arrays.asList(1, 2, 3, 4));
    FoldableList<Integer> list = new FoldableList<Integer>(nonFoldableList);
    
    Integer addAll = list.doFunction(new BinaryFunction<Integer>() {
      @Override
      public Integer applyTo(Integer elem1, Integer elem2) {
        return elem1+elem2;
      }
    }, 0);
    assertThat(addAll, is(10));
  }
  
}
