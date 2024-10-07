package com.github.busiedpandora.mymusic.model;

public enum NavigationBarField {
    MUSIC("music"), ABOUT("about");

    private final String name;

    NavigationBarField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
