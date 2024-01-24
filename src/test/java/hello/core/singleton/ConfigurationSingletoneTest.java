package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletoneTest {

    @Test
    void configurationTest() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

        // AppConfig를 통해서 스프링 컨테이너에 등록된 빈을 확인해보자.
        // AppConfig에 @Configuration을 붙이고, @Bean에 @Configuration을 붙이면
        // 스프링 컨테이너는 싱글톤을 보장해준다.
        // 이것은 스프링이 자바 코드까지 어떻게 하기는 어렵다.
        // 그래서 스프링은 CGLIB라는 바이트코드 조작 라이브러리를 사용한다.
        // CGLIB는 AppConfig를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한다.
        // 그리고 그 다른 클래스를 사용해서 스프링 빈을 조회하면, 항상 같은 인스턴스를 반환한다.
        // 이렇게 해서 싱글톤이 보장되는 것이다.
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // AppConfig도 스프링 빈으로 등록된다.
        // 그래서 AppConfig도 스프링 빈으로 등록되어 있고, 싱글톤을 보장한다.
        // 그래서 AppConfig를 조회해서 출력하면 모두 같은 AppConfig가 출력된다.
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
    }
}
