package singleton;

import me.muphy.singleton.ContainerSingleton;
import me.muphy.singleton.SingletonBean;
import me.muphy.util.ConcurrentExecuter;

/**
 * 2019/4/1
 * 莫非
 */
public class ContainerSingletonTest {

    public static void main(String[] args) {
//        SingletonBean singleton = (SingletonBean) ContainerSingleton.getBean("me.muphy.singleton.SingletonBean");
//        System.out.println(singleton);

        ConcurrentExecuter.execte(10);

    }
}
