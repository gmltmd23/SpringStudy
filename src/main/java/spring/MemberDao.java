package spring;


import mapper.MemberRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class MemberDao {
    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?", new MemberRowMapper(), email);
        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(Member member) {

    }

    public void update(Member member) {

    }

    public Collection<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("select * from MEMBER", new MemberRowMapper());
        return results.isEmpty() ? null : results;
    }
}
