package com.doubleknd26.exercise.java.observer;


import java.util.Observable;

public class YouTubeChannel extends Observable {

    public void uploadContent() {
        setChanged();
    }
}
