package me.muphy.strategy.pay;

/**
 * 2019/4/18
 * 莫非
 */
public class MsgResult {

    private String code;
    private String msg;
    private String data;

    public MsgResult(String code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "支付状态：[" + code + "]," + msg + ",交易详情：" + data;
    }
}
