package hillelee.apple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class AppleSelectorTest {

    @Test
    public void selectHeaviest() throws Exception {
        List<Apple> apples = ImmutableList.of(new Apple("RED", 100),
                new Apple("RED", 120),
                new Apple("RED", 110),
                new Apple("GREEN", 150),
                new Apple("GREEN", 130));

        Optional<Apple> mayBeHeavieast = AppleSelector.getHeaviest(apples);
        if (mayBeHeavieast.isPresent()) {
            Apple heaviest = mayBeHeavieast.get();

            assertThat(heaviest.getWeigth(), is(150));
        } else {
            fail();
        }

    }

    @Test
    public void selectHeavieastFromEmptyList() throws Exception {
        List<Apple> apples = ImmutableList.of();

        Optional<Apple> mayBeApple = AppleSelector.getHeaviest(apples);
        if (mayBeApple.isPresent()) {
            fail();
        }
    }

    @Test
    public void sort() throws Exception {
        List<Apple> apples = ImmutableList.of(new Apple("RED", 100),
                new Apple("RED", 120),
                new Apple("RED", 110),
                new Apple("GREEN", 150),
                new Apple("GREEN", 130));

        apples = new ArrayList<>(apples);

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeigth().compareTo(o2.getWeigth());
            }
        });

        System.out.println(apples);


    }

    @Test
    public void mapDefault() throws Exception {
        Map<String, Integer> nameToSalary = ImmutableMap.of("Ivan", 100);

        Integer salary = nameToSalary.getOrDefault("Stepan", 0);
    }

    @Test
    public void filterByPerdicate() throws Exception {
        List<Apple> apples = ImmutableList.of(new Apple("RED", 100),
                new Apple("RED", 120),
                new Apple("RED", 110),
                new Apple("GREEN", 150),
                new Apple("GREEN", 130));

        List<Apple> filtered = AppleSelector.filter(apples, new ColorPredicate());

        assertThat(filtered, hasSize(2));

    }

    @Test
    public void filterByAnonymousPerdicate() throws Exception {
        List<Apple> apples = ImmutableList.of(new Apple("RED", 100),
                new Apple("RED", 120),
                new Apple("RED", 110),
                new Apple("GREEN", 150),
                new Apple("GREEN", 130));

        List<Apple> filtered = AppleSelector.filter(apples, (apple) -> apple.getWeigth() > 120);

        assertThat(filtered, hasSize(2));

    }
}