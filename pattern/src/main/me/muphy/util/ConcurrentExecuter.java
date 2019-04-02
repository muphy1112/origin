package me.muphy.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 2019/4/2
 * 莫非
 */
public class ConcurrentExecuter {
    public static void execte(int threadCount) {
        List<Thread> list = new ArrayList();
        for (int i = 0; i < threadCount; i++) {
            list.add(new Thread(new me.muphy.util.ExectorThread()));
        }

        for (Thread thread : list) {
            thread.start();
        }

    }
}
