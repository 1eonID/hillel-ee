package hillelee.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class ProblemSolver {
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

//    public Optional<String> solveWithStreams(Object problem) {
//        Class<?> aClass = problem.getClass();
//        Method[] methods = aClass.getMethods();
//        Optional<String> method = Arrays.stream(methods)
//                .filter(m -> m.isAnnotationPresent(CorrectAnswer.class))
//                .map(m -> m.toString());
//    }
}
