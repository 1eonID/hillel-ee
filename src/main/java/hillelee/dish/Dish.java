package hillelee.dish;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
    String name;
    Integer calories;
    Boolean isBio;
    DishType type;

}
