package me.muphy.observer;

import me.muphy.observer.blog.MuphyBlogObservable;
import me.muphy.observer.blog.Question;
import me.muphy.observer.blog.TeacherObserver;

/**
 * 2019/4/23
 * 莫非
 */
public class ObserverTest {

    public static void main(String[] args) {
        MuphyBlogObservable observable = MuphyBlogObservable.getInstance();
        TeacherObserver muphy = new TeacherObserver("莫非");

        Question question = new Question();
        question.setUserName("五一");
        question.setContent("观察者模式适用于哪些场景？");

        observable.addObserver(muphy);
        observable.addObserver(new TeacherObserver("潇湘"));
        observable.publicQuestion(question);

    }
}
