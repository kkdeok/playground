package designpattern.singleton;

public class Singleton {
    // Java 에서 double-checked locking 쓸 때는 필수로 volatile
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        // 1차 체크: 락 없이 빠르게 null 여부 확인
        if (instance == null) {
            synchronized(Singleton.class) {
                // 2차 체크: 정말로 null 인지 다시 확인
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
