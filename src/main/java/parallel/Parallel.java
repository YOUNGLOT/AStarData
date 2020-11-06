package parallel;

import lombok.SneakyThrows;

import java.util.ArrayList;

public class Parallel {
    private final int THREAD_COUNT = 2;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("오버라이딩 안하면 찍힌다. parallel runnable default sout");
        }
    };

    public Parallel(Runnable runnable) {
        this.runnable = runnable;
    }

    @SneakyThrows
    public void parallel() {
        ArrayList<Thread> threadList = new ArrayList<>(); // 쓰레드들을 담을 객체

        for (int i = 0; i < THREAD_COUNT; i++) {
            // Runnable 인터페이스를 사용해 새로운 쓰레드를 만듭니다.
            Thread test = new Thread(runnable);

            test.start(); // 이 메소드를 실행하면 Thread 내의 run()을 수행한다.
            threadList.add(test); // 생성한 쓰레드를 리스트에 삽입
        }

        for (Thread t : threadList) {
            t.join(); // 쓰레드의 처리가 끝날때까지 기다립니다.
        }

    }
}
