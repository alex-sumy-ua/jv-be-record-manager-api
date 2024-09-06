package com.northcoders.albummanagerapi.data;

public enum Genre {
    ROCK(1, "ROCK"),
    PROGROCK(2, "PROGROCK"),
    METAL(3, "METAL"),
    SKA(4, "SKA"),
    JAZZ(5, "JAZZ"),
    POP(6, "POP"),
    HIPHOP(7, "HIPHOP"),
    RNB(8, "RNB"),
    CLASSICAL(9, "CLASSICAL"),
    ELECTRONIC(10, "ELECTRONIC"),
    BLUES(11, "BLUES");

    public final int index;
    public final String descriptor;

    Genre(int index, String genreDescriptor) {
        this.index = index;
        this.descriptor = genreDescriptor;
    }

}