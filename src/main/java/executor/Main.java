package executor;

import config.AppContext;
import config.AppCtxWithPrototype;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.Client;
import spring.Client2;

public class Main {
    private static AnnotationConfigApplicationContext ctx = null;

    public static void main(String[] args) {
        ctx = new AnnotationConfigApplicationContext(AppContext.class);
        Client temp = ctx.getBean(Client.class);
        temp.send();

        Client2 c2 = ctx.getBean(Client2.class);
        c2.send();

        AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext(AppCtxWithPrototype.class);
        Client temp2 = ctx2.getBean(Client.class);
        System.out.println(temp == temp2);

        ctx.close();
        ctx2.close();
    }
}
