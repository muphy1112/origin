package me.muphy.demo.service.impl;

import me.muphy.demo.service.IDemoService;
import me.muphy.mvcframework.anotation.MuphyService;

/**
 * 2019/3/27
 * 莫非
 */
@MuphyService
public class DemoService implements IDemoService {
    @Override
    public String getMessage(String name) {
        return "My name is " + name;
    }
}
