/**
 * Created by Priyanka.N on 29/07/2020.
 */

// use Synchronized block
// wait until the boolean variable changes in while loop
// print and notify 

public class OddEvenTwoThreads {
    int MAX = 20;
    boolean oddFlag = true;
    int count = 0;
    static public void main(String[] args) {
        OddEvenTwoThreads obj = new OddEvenTwoThreads();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                obj.printOdd();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                obj.printEven();
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void printOdd()
    {
        synchronized (this){        while(count< MAX) {
            while (oddFlag) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }
            System.out.println(count++);
            oddFlag = false;
            notify();
        }
    }}
    private void printEven() {
        synchronized (this) {
            while (count < MAX) {
                while (!oddFlag) {
                    try {
                        wait();
                    } catch (InterruptedException e) {

                    }
                }
                System.out.println(count++);
                oddFlag = true;
                notify();
            }
        }
    }
}

