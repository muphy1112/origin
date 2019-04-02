package me.muphy.singleton;

/**
 * 2019/4/1
 * 莫非
 */
public class LazySingleton {

    private volatile static LazySingleton lazySingleton = null;

    private LazySingleton() {
        if (lazySingleton != null) {
            throw new RuntimeException("此类以单例存在！");
        }
    }

    public static LazySingleton getInstance() throws Exception {
        if (lazySingleton == null) {
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }

    private Object readResolve() {
        return lazySingleton;
    }

}
