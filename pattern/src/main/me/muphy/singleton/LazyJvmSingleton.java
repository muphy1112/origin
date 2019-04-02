package me.muphy.singleton;

/**
 * 2019/4/1
 * 莫非
 */
public class LazyJvmSingleton {

    private LazyJvmSingleton(){

    }

    public static LazyJvmSingleton getInstance(){
        return LazySingleton.lazyJvmSingleton;
    }

    private static class LazySingleton{
        public static final LazyJvmSingleton lazyJvmSingleton = new LazyJvmSingleton();
    }

    private Object readResolve(){
        return LazySingleton.lazyJvmSingleton;
    }
}
