package me.muphy.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 2019/4/24
 * 莫非
 */
public class EventLisenter {

    protected Map<String, Event> events = new HashMap<>();

    public void addLisenter(String eventType, Object target){
        try {
            if(target != null){
                Method callback = target.getClass().getDeclaredMethod("on" + toFirstUpperCase(eventType), Event.class);
                this.addLisenter(eventType, target, callback);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private String toFirstUpperCase(String eventType) {
        if(eventType == null || eventType.isEmpty()) return eventType;
        return eventType.substring(0,1).toUpperCase() + eventType.substring(1);
    }

    public void addLisenter(String eventType, Object target, Method callback){
        this.events.put(eventType, new Event(target, callback));
    }

    private void trigger(Event event){
        event.setSource(this);
        event.setTime(System.currentTimeMillis());
        try {
            if(event.getCallback() != null){
                event.getCallback().invoke(event.getTarget(),event);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void trigger(String trigger){
        if(this.events.containsKey(trigger)){
            this.trigger(this.events.get(trigger).setTrigger(trigger));
        }
    }
}
