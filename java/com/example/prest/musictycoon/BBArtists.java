package com.example.prest.musictycoon;//package attemptreset.java;

public class BBArtists extends Artists {
    static int up_last = 0;
    static int up_pop = 0;

    static int get_up_pop()
    {
        return up_pop;
    }
    static int get_up_last()
    {
        return up_last;
    }
    static void add_pop()
    {
        if ( up_pop < 5 )
        {
            ++up_pop;
        }
    }
    static void add_last()
    {
        if ( up_last < 5)
        {
            ++up_last;
        }
    }

}
