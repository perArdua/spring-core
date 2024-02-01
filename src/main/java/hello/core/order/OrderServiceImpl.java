package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kok8454@gmail.com on 2024-01-14
 * Github : http://github.com/perArdua
 */
@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    //     인터페이스 뿐만 아니라 구현체도 함께 의존하고 있다. DIP 위반
//     변경하지 않고 확장할 수 없다. OCP 위반
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//     인터페이스만 의존하도록 변경
//     DIP를 지키면서 OCP도 지킬 수 있다.
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 정보 조회
        // SRP를 지키기 위해 할인 정책에 대한 부분을 분리했다.
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에 따른 할인 금액 계산

        return new Order(memberId, itemName, itemPrice, discountPrice); // 최종 생성된 주문 정보 반환
    }

    // Test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
