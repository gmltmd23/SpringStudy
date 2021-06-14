package executor;

import assembler.Assembler;
import exceptions.DuplicateMemberException;
import exceptions.MemberNotFoundException;
import exceptions.WrongIdPasswordException;
import spring.ChangePasswordService;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Executor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.print("명령어를 입력하세요 : ");
            String command = br.readLine();
            if(command.equalsIgnoreCase("exit")) {
                System.out.println("종료 합니다...");
                break;
            }
            if(command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            }
            printHelp();
        }
    }

    private static Assembler assembler = new Assembler();

    private static void processNewCommand(String[] arg) {
        if (arg.length != 5) {
            printHelp();
            return ;
        }
        MemberRegisterService regSvc = assembler.getMemberRegisterService();
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setEmail(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.print("등록완료\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일 입니다.");
        }
    }

    private static void processChangeCommand(String[] arg) {
        if (arg.length != 4) {
            printHelp();
            return;
        }
        ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
        try {
            changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
            System.out.print("비밀번호 변경 완료\n");
        } catch (MemberNotFoundException e) {
            System.out.print("존재하지 않는 이메일 입니다.\n");
        } catch (WrongIdPasswordException e) {
            System.out.print("이메일과 암호가 일치하지 않습니다.\n");
        }
    }

    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래의 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println();
    }
}
