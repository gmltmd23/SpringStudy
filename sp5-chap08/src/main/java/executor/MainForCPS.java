package executor;

import config.AppCtx;
import exceptions.MemberNotFoundException;
import exceptions.WrongIdPasswordException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ChangePasswordService;

public class MainForCPS {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        ChangePasswordService cps = ctx.getBean("changePwdSvc", ChangePasswordService.class);

        try {
            String oldPw = "1111";
            String newPw = "1234";
            cps.changePassword("madvirus@madvirus.net", oldPw, newPw);
            System.out.println("암호를 변경했습니다. [ " + oldPw + " > " + newPw + " ]");
        } catch (MemberNotFoundException e) {
            System.out.println("회원 데이터가 존재하지 않습니다.");
        } catch (WrongIdPasswordException e) {
            System.out.println("암호가 올바르지 않습니다.");
        } finally {
            ctx.close();
        }
    }
}