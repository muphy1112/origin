package me.muphy.observer.blog;

import java.util.Observable;
import java.util.Observer;

/**
 * 2019/4/23
 * 莫非
 * 观察者
 */
public class TeacherObserver implements Observer {

    private String name;

    public TeacherObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        MuphyBlogObservable observable = (MuphyBlogObservable) o;
        Question question = (Question) arg;
        System.out.println("==============================================================");
        System.out.println(name + "老师，你好！\n" +
                "你收到了来自“" + observable.getName() + "”的提问，希望你解答，问题内容如下：\n" +
                question.getContent() + "\n" +
                "提问者是：" + question.getUserName());
    }
}
