package designpattern.factorymethod;

interface Notification {
    void send(String to, String message);
}

class EmailNotification implements Notification {
    @Override
    public void send(String to, String message) {
        System.out.println("[EMAIL] to=" + to + ", message=" + message);   
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String to, String message) {
        System.out.println("[SMS] to=" + to + ", message=" + message);
    }
}

class NotificationFactory {
    public static Notification create(String type) {
        switch (type.toLowerCase()) {
            case "email":
                return new EmailNotification();
            case "sms":
                return new SmsNotification();
            default:
                throw new IllegalArgumentException("Unknown Type: " + type);
        }
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        Notification email = NotificationFactory.create("email");
        email.send("user@example.com", "Factory Pattern Hello!");

        Notification sms = NotificationFactory.create("sms");
        sms.send("010-1234-5678", "인증번호: 1234");
    }
}
