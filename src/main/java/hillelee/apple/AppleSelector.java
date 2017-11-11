package hillelee.apple;

import hillelee.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class AppleSelector {
    public static Optional<Apple> getHeaviest(List<Apple> apples) {
        Apple heaviest = null;
        for (Apple apple: apples) {
            if (heaviest == null || apple.getWeight() > heaviest.getWeight()) {
                heaviest = apple;
            }
        }

        return Optional.ofNullable(heaviest);
    }

    public static List<Apple> filterHeaty(List<Apple> apples, Integer weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterByColor(List<Apple> apples, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filter(List<Apple> apples, Predicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static <T> List<T> filterByAnonimousPredicate(List<T> items, Predicate predicate) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}


class ColorPredicate implements Predicate<Apple> {

    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equals("GREEN");
    }
}

class WeightPredicate implements Predicate<Apple> {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 100;
    }
}
