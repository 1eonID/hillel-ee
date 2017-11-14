package hillelee.dish;

import lombok.Data;

@Data
public class Dish {
    String name;
    Integer calories;
    Boolean isBio;
    DishType type;

}
