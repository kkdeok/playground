package com.doubleknd26.exercise.java.observer;


public class Main {

    public static void main (String[] args) {
        YouTubeChannel channel = new YouTubeChannel();

        Subscriber subscriber = new Subscriber();
        channel.addObserver(subscriber);

        channel.uploadContent();
        channel.notifyObservers();
    }
}
