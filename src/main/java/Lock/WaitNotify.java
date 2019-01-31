package Lock;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2019/1/22 10:56
 * @Version 1.0
 */
public class WaitNotify {


    static class Wait implements Runnable {

        public static volatile boolean flag = false;

        @Override
        public void run() {
            synchronized (WaitNotify.class) {

                while (!flag) {
                    try {
                        WaitNotify.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("wait finish");

            }
        }
    }



}
