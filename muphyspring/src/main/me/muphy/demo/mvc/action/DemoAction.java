package me.muphy.demo.mvc.action;

import me.muphy.demo.service.IDemoService;
import me.muphy.mvcframework.annotation.MuphyAutoWired;
import me.muphy.mvcframework.annotation.MuphyController;
import me.muphy.mvcframework.annotation.MuphyRequestMapping;
import me.muphy.mvcframework.annotation.MuphyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2019/3/25
 * 莫非
 */
@MuphyController(value = "sadfdsf")
@MuphyRequestMapping("/demo")
public class DemoAction {

    @MuphyAutoWired
    private IDemoService demoService;

    @MuphyRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response, @MuphyRequestParam("name") String name) {
        String result = demoService.get(name);
        try {
            response.getWriter().write("<!DOCTYPE HTML> <html> <body> <video width=\"320\" height=\"240\" controls=\"controls\"> <source src=\"movie.ogg\" type=\"video/ogg\"> <source src=\"movie.mp4\" type=\"video/mp4\"> Your browser does not support the video tag. <br>" + result + "</video> </body> </html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MuphyRequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, @MuphyRequestParam("name") String name) {
        int num = Integer.parseInt(name);
        try {
            response.getWriter().write("2 * " + num + " = " + 2 * num);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
