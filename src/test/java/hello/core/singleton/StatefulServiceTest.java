package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA: A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A 주문 금액 조회
        System.out.println("price = " + userAPrice);

        assertThat(userBPrice).isEqualTo(20000);
        // ThreadA: 사용자 A는 10000원을 기대했지만, 기대와 다르게 20000원이 출력된다.
        // 그 이유는 statefulService1과 statefulService2는 같은 객체이기 때문이다.
        // 그래서 statefulService1의 price 필드가 20000원으로 변경되었기 때문에
        // statefulService2의 price 필드도 20000원으로 변경된 것이다.
        // 이렇게 되면 여러 스레드가 동시에 접근하면서 값을 변경할 수 있다.
        // 그래서 이런 경우에는 지역 변수로 만들어서 사용해야 한다.
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}