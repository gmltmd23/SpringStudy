package executor;

import config.AppContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.Client;

public class Main {
    private static AnnotationConfigApplicationContext ctx = null;

    public static void main(String[] args) {
        ctx = new AnnotationConfigApplicationContext(AppContext.class);
        Client temp = ctx.getBean(Client.class);
        temp.send();
        ctx.close();
    }
}
