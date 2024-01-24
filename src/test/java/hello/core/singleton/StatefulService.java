package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//         this.price = price; // 여기가 문제!
        // 이렇게 되면 여러 스레드가 동시에 접근하면서 값을 변경할 수 있다.
        // 그래서 이런 경우에는 지역 변수로 만들어서 사용해야 한다.
        // int price = 20000;
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
