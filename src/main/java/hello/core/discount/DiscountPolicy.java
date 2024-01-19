package hello.core.discount;

import hello.core.member.Member;

/**
 * Created by kok8454@gmail.com on 2024-01-14
 * Github : http://github.com/perArdua
 */
public interface DiscountPolicy {

    /*
    * @return 할인 대상 금액
    */
    int discount(Member member, int price);
}
