package com.example.prest.musictycoon;//package attemptreset.java;

import java.util.*;



public class Genres {
    static Vector<String> genre_v;

    String ret_genre() {
        return genre_v.elementAt(Randoms.gen_uniform (genre_v.size()));
    }


}
