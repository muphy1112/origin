package me.muphy.delegate;

/**
 * 2019/4/17
 * 莫非
 */
public class EmployeeA implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("员工A：做" + command);
    }
}
