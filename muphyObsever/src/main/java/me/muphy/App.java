package me.muphy;

import me.muphy.mouse.Mouse;
import me.muphy.mouse.MouseEventCallback;
import me.muphy.mouse.MouseEventType;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Mouse mouse = new Mouse();
        MouseEventCallback callback = new MouseEventCallback();

        mouse.addLisenter(MouseEventType.ON_CLICK, callback);
        mouse.addLisenter(MouseEventType.ON_BLUR, callback);
        mouse.click();
        mouse.blur();
    }
}
