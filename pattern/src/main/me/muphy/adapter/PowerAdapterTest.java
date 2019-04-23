package me.muphy.adapter;

import me.muphy.adapter.poweradapter.AC220V;
import me.muphy.adapter.poweradapter.PowerAdapter;

/**
 * 2019/4/19
 * 莫非
 */
public class PowerAdapterTest {
    public static void main(String[] args) {
        PowerAdapter adapter = new PowerAdapter(new AC220V());
        adapter.outputDC5V();
    }
}
