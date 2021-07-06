package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

public class MemberPrinter {
    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member member) {
        if (dateTimeFormatter == null) {
            System.out.printf(
                    "회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF\n",
                    member.getID(), member.getEmail(), member.getName(), member.getRegisterDateTime());
        } else {
            System.out.println(dateTimeFormatter);
            System.out.printf(
                    "회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %sF\n",
                    member.getID(), member.getEmail(), member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }

    @Autowired(required = false) // 다만 이건 Nullable과는 다른점이 있는데 일치하는 Bean이 없으면 아예 값을 할당하지 않는다. // Nullable은 말그대로 Bean이 없으면 null을 넣는다.
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    /*
    아래 코드는 위에 @Autowired(required = false) 를 쓴것과같은 코드이다
    
    public void setDateTimeFormatter(Optional<DateTimeFormatter> formatterOpt) {
        if (formatterOpt.isPresent()) {
            this.dateTimeFormatter = formatterOpt.get();
        } else {
            this.dateTimeFormatter = null;
        }
    }
    
    또는
    
    public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
    
    이 @Nullable 코드는 require와 다른점은 이놈은 세터함수를 실행시키고,
    위에 required 에노테이션을 넣은놈은 DateTimeFormatter가 Bean에 등록안되있으면 세터함수 실행안시킴
     */
}
