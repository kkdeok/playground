package designpattern.strategy;

interface PaymentStrategy {
    void pay(int amount);
}

class CardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("신용 카드 결제 : " + amount);
    }
}

class KakaoPayPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {   
        System.out.println("카카오페이 결제 : " + amount);
    }
}

class OrderService {
    private PaymentStrategy paymentStrategy;

    public OrderService(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void order(int amount) {
        System.out.println("주문 생성 완료 -> 결제 시작");
        paymentStrategy.pay(amount);
    }
}

public class StrategyExample {
    public static void main(String[] args) {
        // 카드 결제 전략
        OrderService osvc = new OrderService(new CardPayment());
        osvc.order(10000);

        // 런타임에 전략 교체 (카카오페이)
        osvc.setPaymentStrategy(new KakaoPayPayment());
        osvc.order(20000);
    }
}
