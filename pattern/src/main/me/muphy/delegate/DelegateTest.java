package me.muphy.delegate;

/**
 * 2019/4/17
 * 莫非
 */
public class DelegateTest {

    public static void main(String[] args) {
        new Boss().command(new Leader(), "架构");
    }
}
