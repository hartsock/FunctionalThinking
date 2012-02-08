package com.blogspot.hartsock.functionalthinking;

import com.blogspot.hartsock.functionalthinking.functions.BinaryFunction;
import com.blogspot.hartsock.functionalthinking.functions.Function;
import com.blogspot.hartsock.functionalthinking.functions.UnaryFunction;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * @author Muhammad Ashraf
 * @since 2/7/12
 */
public class FPCollectionTest {

    @Test
    public void testForEach() throws Exception {
     FPCollection<String> collection=new FPCollection<String>("To","love,","or","not","to","love:","that","is","the","question");

        collection.forEach(new Function<String>() {
          public void apply(String input) {
              System.out.println(input);
          }
      });



    }

    @Test
    public void testMap() throws Exception {
        FPCollection<Integer> collection=new FPCollection<Integer>(1,2,3,4,5,6,7,8,9,10);

        final Collection<String> result = collection.map(new UnaryFunction<Integer, String>() {
            public String apply(Integer input) {
                return input%2==0?"even":"odd";
            }
        });

        System.out.println("result = " + result);
    }

    @Test
    public void testFoldLeftSum() throws Exception {
        FPCollection<Integer> collection=new FPCollection<Integer>(1,2,3,4,5,6,7,8,9,10);

        final Integer sum = collection.foldLeft(0, new BinaryFunction<Integer, Integer>() {
            public Integer apply(Integer seed, Integer input) {
                return seed + input;
            }
        });

       final Integer expected=55;
      Assert.assertEquals("incorrect sum ", expected, sum);
    }

    @Test
    public void testFoldLeftString() throws Exception {
        FPCollection<String> collection=new FPCollection<String>("To","love,","or","not","to","love:","that","is","the","question");

        final String result = collection.foldLeft("", new BinaryFunction<String, String>() {
            public String apply(String seed, String input) {
                return seed + " " + input;
            }
        });

        final String expected = " To love, or not to love: that is the question";
        Assert.assertEquals("incorrect result", expected, result);
    }

}
