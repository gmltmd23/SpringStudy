package spring;

public class FindEmailService {
    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public boolean findEmail(String email) {
        Member temp = null;
        temp = this.memberDao.selectByEmail(email);
        if (temp != null)
            return true;
        else
            return false;
    }
}
