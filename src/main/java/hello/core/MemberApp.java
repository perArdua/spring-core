package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by kok8454@gmail.com on 2024-01-14
 * Github : http://github.com/perArdua
 */
public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();

        // applicationContext는 스프링 컨테이너라고 한다.
        // AnnotationConfigApplicationContext는 @Configuration이 붙은 클래스를 설정 정보로 사용한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // getBean()은 이름 그대로 스프링 컨테이너에서 빈을 찾아오는 메서드이다.
        // 첫 번째 파라미터는 @Bean의 이름, 두 번째 파라미터는 타입이다.
        // 타입으로 조회하면 해당 타입의 스프링 빈이 모두 조회된다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
