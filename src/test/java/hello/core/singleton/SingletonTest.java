package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
//        System.out.println("memberService1 = " + memberService1);
//        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
//        new SingletonService();
        // 이렇게 하면 컴파일 오류가 발생한다.
        // 왜냐하면 생성자가 private이기 때문이다.
        // 그래서 생성자를 호출할 수 없다.
        // 이렇게 생성자를 private으로 막아두면 외부에서 new 키워드를 사용해서 객체 인스턴스가 생성되는 것을 막을 수 있다.
        // 이렇게 생성을 막아두었는데 어떻게 사용하나?
        // 이 싱글톤 패턴을 적용한 클래스의 getInstance() 메서드를 사용해서 객체 인스턴스를 얻어온다.
        // 이렇게 하면 항상 같은 인스턴스를 반환한다.
        // 그래서 싱글톤 패턴을 적용하면 고객의 요청이 올 때마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2);

        // isSameAs()는 == 비교이고, isEqualTo()는 equals() 비교이다.
    }
}
