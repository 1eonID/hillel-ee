package hillelee.apple;

import hillelee.defaultMethods.Fruit;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by JavaEE on 28.10.2017.
 */
@Data
@AllArgsConstructor
public class Apple implements Fruit {
    private String color;
    private Integer weigth;

}