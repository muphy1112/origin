package me.muphy.prototype;

import java.io.Serializable;

/**
 * 2019/4/3
 * 莫非
 */
public class JinGuBang implements Serializable {
    private int height;
    private int width;

    public void bigger(int multiple){
        this.height *= multiple;
        this.width *= multiple;
    }

    public void smaller(int multiple){
        this.width /= multiple;
        this.height /= multiple;
    }
}
