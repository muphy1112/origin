package me.muphy.adapter.loginadapter;

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
        return "状态：[" + code + "]," + msg + ",详情：" + data;
    }
}
