package me.muphy.demo.mvc.action;

import me.muphy.demo.service.IDemoService;
import me.muphy.mvcframework.anotation.MuphyAutowired;
import me.muphy.mvcframework.anotation.MuphyController;
import me.muphy.mvcframework.anotation.MuphyRequestMapping;
import me.muphy.mvcframework.anotation.MuphyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2019/3/29
 * 莫非
 */
@MuphyController
@MuphyRequestMapping("/demo")
public class DemoController {
    @MuphyAutowired
    private IDemoService service;

    @MuphyRequestMapping("/query")
    //为什么要MuphyRequestParam
    public void query(HttpServletRequest request, HttpServletResponse response, @MuphyRequestParam("name") String name, @MuphyRequestParam("num") String num) throws IOException {
        response.getWriter().write(service.getMessage(name) + ", num=" + num);
    }

    @MuphyRequestMapping("/add")
    //为什么要MuphyRequestParam
    public void add(HttpServletResponse response, @MuphyRequestParam("a") int a, @MuphyRequestParam("b") int b) throws IOException {
        response.getWriter().write(a + " + " + b + " = " + (a + b));
    }

    @MuphyRequestMapping("/sub")
    //为什么要MuphyRequestParam
    public void sub(HttpServletResponse response, @MuphyRequestParam("a") int a, @MuphyRequestParam("b") int b) throws IOException {
        response.getWriter().write(a + " - " + b + " = " + (a - b));
    }
}
