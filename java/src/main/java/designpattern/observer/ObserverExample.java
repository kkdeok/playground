package designpattern.observer;

import java.util.ArrayList;
import java.util.List;

// 1. 옵저버 인터페이스
interface Subscriber {
    void update(String news);
}

// 2. Concrete Observer 들
class EmailSubscriber implements Subscriber {
    private final String email;
    
    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String news) {
        System.out.println("[EMAIL] to " + email + ": " + news);
    }
}

class SmsSubscriber implements Subscriber {
    private final String phone;

    public SmsSubscriber(String phone) {
        this.phone = phone;
    }

    @Override
    public void update(String news) {
        System.out.println("[SMS] to " + phone + ": " + news);   
    }
}

class NewsPublisher {
    private final List<Subscriber> subscribers = new ArrayList<>();

    // 구독 신청
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    // 구독 해지
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    // 뉴스 발행
    public void publishNews(String news) {
        System.out.println("=== 뉴스 발행 : " + news  + " ===");
        notifySubscribers(news);
    }

    private void notifySubscribers(String news) {
        for (Subscriber s : subscribers) {
            s.update(news);
        }
    }
}


public class ObserverExample {
    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();

        Subscriber emailUser = new EmailSubscriber("user@example.com");
        Subscriber smsUser = new SmsSubscriber("010-1234-5678");

        // 구독 등록
        publisher.subscribe(emailUser);
        publisher.subscribe(smsUser);

        // 뉴스 발행 1
        publisher.publishNews("옵저버 패턴 강의가 업로드되었습니다!");

        // SMS 구독자 해지
        publisher.unsubscribe(smsUser);

        // 뉴스 발행 2
        publisher.publishNews("두 번째 뉴스: 프로듀서-컨슈머 패턴도 곧 다룹니다.");
    }
}
