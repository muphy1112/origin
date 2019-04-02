package me.muphy.prototype;

import java.io.*;
import java.util.Date;

/**
 * 2019/4/3
 * 莫非
 */
public class MonkeySun extends Monkey implements Cloneable, Serializable {

    private JinGuBang jinGuBang;

    public JinGuBang getJinGuBang() {
        return jinGuBang;
    }

    public void setJinGuBang(JinGuBang jinGuBang) {
        this.jinGuBang = jinGuBang;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return deepClone();
    }

    private Object deepClone() throws CloneNotSupportedException {
        MonkeySun monkeySun = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            monkeySun = (MonkeySun) ois.readObject();
            monkeySun.setBirth(new Date());
            //关闭流逻辑略写
            bis.close();
            bos.close();
            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CloneNotSupportedException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new CloneNotSupportedException();
        }
        return monkeySun;
    }

}
