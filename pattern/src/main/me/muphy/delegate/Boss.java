package me.muphy.delegate;

/**
 * 2019/4/17
 * 莫非
 */
public class Boss {

    public void command(Leader leader, String command){
        leader.doing(command);
    }
}

