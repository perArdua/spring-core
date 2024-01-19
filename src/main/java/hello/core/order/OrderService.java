package hello.core.order;

/**
 * Created by kok8454@gmail.com on 2024-01-14
 * Github : http://github.com/perArdua
 */
public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
