package me.muphy.demo.service.impl;

import me.muphy.demo.service.IDemoService;
import me.muphy.mvcframework.annotation.MuphyService;

/**
 * 2019/3/25
 * 莫非
 */
@MuphyService
public class DemoService implements IDemoService {
    @Override
    public String get(String name) {
        return "My name is " + name;
    }
}
