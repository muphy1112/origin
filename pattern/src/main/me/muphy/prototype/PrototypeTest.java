package me.muphy.prototype;

/**
 * 2019/4/3
 * 莫非
 */
public class PrototypeTest {
    public static void main(String[] args){
        MonkeySun monkeySun = new MonkeySun();
        monkeySun.setWeight(56);
        monkeySun.setHeight(5);
        monkeySun.setJinGuBang(new JinGuBang());
        MonkeySun cloneMonkeySun = null;
        try {
            cloneMonkeySun = (MonkeySun) monkeySun.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(monkeySun.getJinGuBang() == cloneMonkeySun.getJinGuBang());
    }
}
