package me.muphy.prototype;

import java.util.Date;

/**
 * 2019/4/3
 * 莫非
 */
public class Monkey {
    private int height;
    private int weight;
    private Date birth = new Date();

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
