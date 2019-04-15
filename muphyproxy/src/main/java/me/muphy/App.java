package me.muphy;

import me.muphy.proxy.JavaTextProgrammer;
import me.muphy.proxy.MuphyHeadhunting;
import me.muphy.proxy.Programmer;

/**
 * Hello world!
 *
 */
public class App 
{
    public App(){}

    public static void main( String[] args )
    {
        Programmer programmer = null;
        try {
            programmer = (Programmer) new MuphyHeadhunting().getInstance(new JavaTextProgrammer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        programmer.findJob();

//        $MuphyProxy0 proxy0 = new $MuphyProxy0(new MuphyHeadhunting(new JavaTextProgrammer()));
//        proxy0.findJob();
    }
}
