package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        // AutoAppConfig에 @ComponentScan이 있기 때문에 @Component가 붙은 클래스를 스프링 빈으로 자동 등록한다.
        // @ComponentScan은 @Component가 붙은 클래스를 찾아서 스프링 빈으로 등록한다.
        // @ComponentScan은 @Component를 포함하는 다음 애노테이션도 인식한다.
        // @Configuration, @Controller, @Service, @Repository, @Configuration
        // 또한 @Bean이 붙은 메서드도 스프링 빈으로 등록한다.
        // @ComponentScan은 default로 @Component가 붙은 클래스를 스프링 빈으로 등록한다.
        // @ComponentScan은 default로 @Component가 붙은 클래스를 탐색 시작 위치의 하위 패키지를 탐색한다.
        // @ComponentScan은 default로 @Component가 붙은 클래스를 스프링 빈의 이름은 클래스명을 사용한다.
    }
}
