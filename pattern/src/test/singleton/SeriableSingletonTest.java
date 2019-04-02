package singleton;

import me.muphy.singleton.HungrySingleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 2019/4/1
 * 莫非
 */
public class SeriableSingletonTest {
    public static void main(String[] args) {
        HungrySingleton singleton1 = null;
        HungrySingleton singleton2 = HungrySingleton.getInstance();

        try {
            FileOutputStream fos = new FileOutputStream("seriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(singleton2);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("seriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            singleton1 = (HungrySingleton) ois.readObject();

            System.out.println(singleton1 == singleton2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
