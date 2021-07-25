package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
    private MemberDao memDao;
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

    public void setMemberDao(MemberDao memberDao) {
        this.memDao = memberDao;
    }

    public void setMemberPrinter(MemberPrinter memberPrinter) {
        this.printer = memberPrinter;
    }
}
