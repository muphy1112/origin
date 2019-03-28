package me.muphy.demo.mvc.action;

import me.muphy.demo.service.IDemoService;
import me.muphy.mvcframework.anotation.MuphyAutoWired;
import me.muphy.mvcframework.anotation.MuphyController;
import me.muphy.mvcframework.anotation.MuphyRequestMapping;
import me.muphy.mvcframework.anotation.MuphyRequestParam;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 2019/3/29
 * 莫非
 */
@MuphyController
@MuphyRequestMapping("/demo")
public class DemoController {
    @MuphyAutoWired
    private IDemoService service;
    @MuphyRequestMapping("/query")
    //为什么要MuphyRequestParam
    public String query(HttpRequest request, HttpResponse response, @MuphyRequestParam("name") String name){
        return service.getMessage(name);
    }
}
