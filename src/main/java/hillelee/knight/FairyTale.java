package hillelee.knight;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by JavaEE on 11.11.2017.
 */
public class FairyTale {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("hillelee");

        System.out.println(ctx.getBean("knight"));
    }
}


@Data
@Component
class Knight {
    private final Quest quest;
}

@Component
class Quest {
    private String task = "Kill the Dragon";

    public Quest() {

    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "task='" + task + '\'' +
                '}';
    }
}
