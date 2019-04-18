package me.muphy.adapter.poweradapter;

/**
 * 2019/4/19
 * 莫非
 */
public class PowerAdapter implements DC5V {
    private AC220V ac220v;

    public PowerAdapter(AC220V ac220v) {
        this.ac220v = ac220v;
    }

    @Override
    public int outputDC5V() {
        int input = ac220v.outputAC220V();
        int output = input / 44;
        System.out.println("适配器将" + input + "输入转为" + output + "输出");
        return output;
    }
}
