package me.muphy.delegate;

/**
 * 2019/4/17
 * 莫非
 */
public class EmployeeB implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("员工B：做" + command);
    }
}
