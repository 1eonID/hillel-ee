package hillelee.defaultMethods;

/**
 * Created by JavaEE on 28.10.2017.
 */
public interface Fruit {
    String getColor();
    Integer getWeigth();

    default Double approximateVitaminC() {
        if (getColor().equals("GREEN")) {
            return getWeigth() * 0.0001;
        } else {
            return getWeigth() * 0.00005;
        }
    }
}
