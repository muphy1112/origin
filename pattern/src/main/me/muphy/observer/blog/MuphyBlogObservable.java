package me.muphy.observer.blog;

import java.util.Observable;

/**
 * 2019/4/23
 * 莫非
 * 被观察
 */
public class MuphyBlogObservable extends Observable {

    private String name = "Muphy技术交流社区";

    private static MuphyBlogObservable blog = new MuphyBlogObservable();

    private MuphyBlogObservable(){}

    public static MuphyBlogObservable getInstance() {
        return blog;
    }

    public String getName() {
        return name;
    }

    public void publicQuestion(Question question){
        System.out.println(question.getUserName() + "在" + this.name + "上提交了一个问题。");
        setChanged();
        notifyObservers(question);
    }
}
