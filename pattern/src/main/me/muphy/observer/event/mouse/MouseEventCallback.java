package me.muphy.observer.event.mouse;

import me.muphy.observer.event.core.Event;

/**
 * 2019/4/24
 * 莫非
 */
public class MouseEventCallback {
    public void onFocus(Event event){
        System.out.println("触发焦点事件！\n" + event);
    }

    public void onClick(Event event){
        System.out.println("触发点击事件！\n" + event);
    }

    public void onDoubleClick(Event event){
        System.out.println("触发双击点事件！\n" + event);
    }

    public void onUp(Event event){
        System.out.println("触发弹起事件！\n" + event);
    }

    public void onDown(Event event){
        System.out.println("触发按下事件！\n" + event);
    }

    public void onWheel(Event event){
        System.out.println("触发滚动事件！\n" + event);
    }

    public void onOver(Event event){
        System.out.println("触发悬停事件！\n" + event);
    }

    public void onBlur(Event event){
        System.out.println("触发失焦事件！\n" + event);
    }
}
