package me.muphy.observer;

import me.muphy.observer.event.mouse.Mouse;
import me.muphy.observer.event.mouse.MouseEventCallback;
import me.muphy.observer.event.mouse.MouseEventType;

/**
 * 2019/4/24
 * 莫非
 */
public class MouseEventTest {
    public static void main(String[] args) {
        Mouse mouse = new Mouse();
        MouseEventCallback callback = new MouseEventCallback();

        mouse.addLisenter(MouseEventType.ON_CLICK, callback);
        mouse.click();
    }
}
