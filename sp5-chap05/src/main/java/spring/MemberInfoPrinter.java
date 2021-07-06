package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("infoPrinter")
public class MemberInfoPrinter {
    @Autowired
    private MemberDao memDao;
    @Autowired
    @Qualifier("printer")
    private MemberPrinter printer;

    public void printMemberInfo(String email) {
        Member member = memDao.selectByEmail(email);
        if (member == null) {
            System.out.println("멤버정보가 존재하지 않습니다.\n");
            return;
        }
        printer.print(member);
        System.out.println();
    }
}
