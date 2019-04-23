package me.muphy.observer.event.mouse;

import me.muphy.observer.event.core.EventLisenter;

/**
 * 2019/4/24
 * 莫非
 */
public class Mouse extends EventLisenter {

    public void focus(){
        System.out.println("调用焦点事件！");
        this.trigger(MouseEventType.ON_FOCUS);
    }

    public void click(){
        System.out.println("调用点击事件！");
        this.trigger(MouseEventType.ON_CLICK);
    }

    public void doubleClick(){
        System.out.println("调用双击点事件！");
        this.trigger(MouseEventType.ON_DOUBLE_CLICK);
    }

    public void up(){
        System.out.println("调用弹起事件！");
        this.trigger(MouseEventType.ON_UP);
    }

    public void down(){
        System.out.println("调用按下事件！");
        this.trigger(MouseEventType.ON_DOWN);
    }

    public void wheel(){
        System.out.println("调用滚动事件！");
        this.trigger(MouseEventType.ON_WHEEL);
    }

    public void over(){
        System.out.println("调用悬停事件！");
        this.trigger(MouseEventType.ON_OVER);
    }

    public void blur(){
        System.out.println("调用失焦事件！");
        this.trigger(MouseEventType.ON_BLUR);
    }

}
