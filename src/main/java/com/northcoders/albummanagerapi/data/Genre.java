package com.northcoders.albummanagerapi.data;

public enum Genre {
    ROCK(1, "Rock"),
    PROGROCK(2, "Progressive Rock"),
    METAL(3, "Metal"),
    SKA(4, "Ska"),
    JAZZ(5, "Jazz"),
    POP(6, "Pop"),
    HIPHOP(7, "Hip-Hop"),
    RNB(8, "R&B"),
    CLASSICAL(9, "Classical"),
    ELECTRONIC(10, "Electronic"),
    BLUES(11, "Blues");

    final int index;
    final String genreDescriptor;

    Genre(int index, String genreDescriptor) {
        this.index = index;
        this.genreDescriptor = genreDescriptor;
    }

}