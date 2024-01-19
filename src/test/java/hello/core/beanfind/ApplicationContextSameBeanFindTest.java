package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByName() {
        MemberRepository bean = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        // getBeansOfType()은 해당 타입의 모든 빈을 조회한다.
        // 이 메서드는 Map<String, Bean>으로 조회한다.
        // Map의 키는 빈의 이름, 값은 빈의 인스턴스이다.
        // 이렇게 조회한 빈이 2개 이상이면 테스트에 실패한다.
        // 그래서 Map의 사이즈를 확인하는 것이다.
        // 또한, 출력해보면 memberRepository1, memberRepository2가 출력된다.
        // 이렇게 출력되는 이유는 스프링 내부에서 사용하는 빈도 모두 출력하기 때문이다.
        // 그래서 memberRepository1, memberRepository2, springConfig, org.springframework.context.annotation.internalConfigurationAnnotationProcessor, org.springframework.context.annotation.internalAutowiredAnnotationProcessor, org.springframework.context.annotation.internalCommonAnnotationProcessor, org.springframework.context.event.internalEventListenerProcessor, org.springframework.context.event.internalEventListenerFactory가 출력된다.
        // 이런 스프링 내부에서 사용하는 빈까지 출력되는 것을 방지하기 위해서는 getBeansOfType()의 두 번째 파라미터에 false를 넣어주면 된다.
        // 이렇게 하면 스프링 내부에서 사용하는 빈은 제외하고 출력된다.
        // 그래서 memberRepository1, memberRepository2만 출력된다.
        // 이렇게 스프링 내부에서 사용하는 빈까지 출력되는 것을 방지하는 것이 좋다.
        // 왜냐하면 스프링 내부에서 사용하는 빈은 외부에서 가져다 쓸 일이 없기 때문이다.
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }


    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
