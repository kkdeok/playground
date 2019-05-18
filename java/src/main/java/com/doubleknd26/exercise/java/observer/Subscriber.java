package com.doubleknd26.exercise.java.observer;

import java.util.Observable;
import java.util.Observer;


public class Subscriber implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof YouTubeChannel) {
            System.out.println("YouTube Channel has a new event.");
        }
    }
}
