package hillelee.dish;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static hillelee.dish.DishType.BEEF;
import static hillelee.dish.DishType.CHICKEN;
import static hillelee.dish.DishType.VEGETABLES;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class DishTest {


    List<Dish> menu = ImmutableList.of(new Dish("Salad", 100, true, VEGETABLES),
                new Dish("Juice", 90, false, VEGETABLES),
                new Dish("Steak", 400, true, BEEF),
                new Dish("Grilled Chicken", 350, true, CHICKEN),
                new Dish("Burger", 300, false, BEEF),
                new Dish("Chips", 250, false, VEGETABLES));


    @Test
    public void printMostLowCalorie() throws Exception {

        //Classic Java
        for (Dish dish : menu) {
            if (dish.getCalories() < 150) {
                System.out.println(dish);
            }
        }

        System.out.println("-----------------");

        //Using Stream API
        menu.stream()
                .filter(dish -> dish.getCalories() < 150)
                .forEach(System.out::println);
    }

    @Test
    public void printTop3MostNutritious() throws Exception {

        //Classic Java
        menu = new ArrayList<>(menu);

        menu.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o2.getCalories().compareTo(o1.getCalories());
            }
        });

        for (int i = 0; i < 3; i++) {
            System.out.println(menu.get(i));
        }

        System.out.println("-----------------");

        //Using Stream API
        menu.stream()
                .sorted((o1, o2) -> o2.getCalories().compareTo(o1.getCalories()))
                .peek(System.out::println)
                .limit(3)
                .collect(toList());

    }

    @Test
    public void printSortedByBio() throws Exception {

        //Classic Java
        menu = new ArrayList<>(menu);

        menu.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getIsBio().compareTo(o2.getIsBio());
            }
        });

        for (Dish dish : menu) {
            System.out.println(dish);
        }

        System.out.println("-----------------");

        //Using Stream API
        menu.stream()
                .sorted(Comparator.comparing(Dish::getIsBio))
                .forEach(System.out::println);
    }

    @Test
    public void printSortedByAlphabet() throws Exception {

        //Classic Java
        menu = new ArrayList<>(menu);

        menu.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (Dish dish : menu) {
            System.out.println(dish);
        }

        System.out.println("-----------------");

        //Using Stream API
        menu.stream()
                .sorted(Comparator.comparing(Dish::getName))
                .forEach(System.out::println);
    }

    @Test
    public void averageCaloricValueByGroup() throws Exception {

        //Classic Java


        System.out.println("-----------------");

        //Using Stream API
        Map<DishType, Double> averCalByGroup = menu.stream()
                .collect(groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
    }

    @Test
    public void groupedByBIO() throws Exception {

        //Classic Java


        System.out.println("-----------------");

        //Using Stream API
        Map<DishType, List<Dish>> groupedByBioList = menu.stream()
                .filter(dish -> dish.getIsBio())
                .collect(groupingBy(Dish::getType, Collectors.toList()));
    }


}
