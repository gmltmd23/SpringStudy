package spring;

import java.util.*;

public class MemberDao {
    private static long nextId = 0;
    private Map<String, Member> map = new HashMap<String, Member>();

    public Member selectByEmail(String email) {
        return map.get(email);
    }

    public Collection<Member> selectAll() {
        ArrayList<Member> temp = new ArrayList<>();
        for (String key : map.keySet()) {
            temp.add(map.get(key));
        }
        return temp;
    }

    public void insert(Member member) {
        member.setID(++nextId);
        map.put(member.getEmail(), member);
    }

    public void update(Member member) {
        map.put(member.getEmail(), member);
    }
}
