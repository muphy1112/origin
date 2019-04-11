package me.muphy.proxy.staticproxy.db;


/**
 * 2019/4/11
 * 莫非
 */
public class DynamicDataSourceEntity {

    private final static String DEFAULT_SOURCE = "DEFAULT";
    private final static ThreadLocal<String> local = new ThreadLocal<>();

    private DynamicDataSourceEntity(){

    }

    public static String get(){
        return local.get();
    }

    public static void set(String source){
        local.set(source);
    }

    public static void set(int year){
        local.set("DB_" + year);
    }

    public static void restory(){
        local.set(DEFAULT_SOURCE);
    }

}
