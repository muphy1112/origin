package me.muphy.singleton;

import me.muphy.singleton.impl.EnumSingleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 2019/4/1
 * 莫非
 */
public class EnumSingletonTest {

    public static void main(String[] args) {
        EnumSingleton singleton1 = null;
        EnumSingleton singleton2 = EnumSingleton.getInstance();
        singleton2.setData("muphy");
        try {
            FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(singleton2);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("EnumSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            singleton1 = (EnumSingleton) ois.readObject();

            System.out.println(singleton1 == singleton2);
            System.out.println(singleton1.getData() + ":" + singleton1.getData() == singleton2.getData());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
