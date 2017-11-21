package hillelee.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class ProblemSolver {

    //Classic Java
    public String solve(Object problem){
        Class<?> aClass = problem.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CorrectAnswer.class)) {
                try {
                    return (String) method.invoke(problem);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        throw new RuntimeException("There is no CorrectAnswer annotation");
    }

    //Using Stream API
    public String solveWithStreams(Object problem) {
        return Stream.of(problem)
                .map(Object::getClass)
                .flatMap(clazz -> Arrays.stream(clazz.getMethods()))
                .filter(m -> m.isAnnotationPresent(CorrectAnswer.class))
                .map(invokeOn(problem))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no CorrectAnswer annotation"));
    }
       private Function<Method, String> invokeOn(Object problem) {
           return method -> {
               try {
                   return (String) method.invoke(problem);
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
           };
       }
}
