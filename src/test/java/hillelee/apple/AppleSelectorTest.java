package hillelee.apple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class AppleSelectorTest {

    List<Apple> apples = ImmutableList.of(new Apple("RED", 100),
            new Apple("RED", 120),
            new Apple("RED", 110),
            new Apple("GREEN", 150),
            new Apple("GREEN", 130));

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

            assertThat(heaviest.getWeight(), is(150));
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
                return o1.getWeight().compareTo(o2.getWeight());
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

        List<Apple> filtered = AppleSelector.filter(apples, new ColorPredicate());

        assertThat(filtered, hasSize(2));

    }

    @Test
    public void filterByAnonymousPredicate() throws Exception {
        List<Apple> apples = ImmutableList.of(new Apple("RED", 100),
                new Apple("RED", 120),
                new Apple("RED", 110),
                new Apple("GREEN", 150),
                new Apple("GREEN", 130));

        // List<Apple> filtered = AppleSelector.filter(apples, (apple) -> apple.getWeight() > 120);

        Predicate<Apple> heavierThan120 = apple -> apple.getWeight() > 120;
        Predicate<Apple> isRed = apple -> apple.getColor().equals("RED");
        Predicate<Apple> heavyAndRed = isRed.and(heavierThan120);

        List<Apple> filtered = apples.stream()
                .filter(heavyAndRed)
                .collect(toList());

        assertThat(filtered, hasSize(2));
    }

    @Test
    public void mapToColor() throws Exception  {
        List<String> colors = apples.stream()
                .map(Apple::getColor)
                .collect(toList());
        assertThat(colors, hasSize(5));
        assertThat(colors.get(0), is("RED"));

    }

    @Test
    public void printApples() throws Exception {
        apples.stream()
                .forEach(System.out::println);
    }

    @Test
    public void adjustColor() throws Exception {
        ColorAdjuster colorAdjuster = new ColorAdjuster();

        apples.stream()
                .map(Apple::getColor)
                .map(colorAdjuster::adjust)
                .forEach(System.out::println);
    }

    @Test
    public void executionFlow() throws Exception {
        apples.stream()
                .filter(apple -> apple.getWeight() > 59)
                .map(Apple::getColor)
                .peek(System.out::println)
                .limit(3)
                .collect(toList());
    }

    @Test
    public void findFirst() throws Exception {
        apples.stream()
                .filter(apple -> apple.getWeight() > 59)
                .findFirst()
                .map(Apple::getColor)
                .ifPresent(System.out::println);
    }

    @Test
    public void intStream() throws Exception {
        IntSummaryStatistics longSummaryStatistics = apples.stream()
                .map(Apple::getWeight)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println(longSummaryStatistics);
    }

    @Test
    public void name() throws Exception {
        Map<Integer, Apple> weightToApple = apples.stream()
                .collect(toMap(Apple::getWeight, identity()));

        assertThat(weightToApple.get(100), is(apples.get(0)));
    }

    @Test
    public void groupingByColor() throws Exception {
        Map<String, List<Apple>> collect = apples.stream()
                .collect(groupingBy(Apple::getColor, toList()));
        System.out.println(collect.get("RED"));
    }
}