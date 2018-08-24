package com.example.prest.musictycoon;//package attemptreset.java;

import java.util.HashMap;
import java.util.*;

public class OArtists extends Artists {
    static HashMap <String, Integer> genre_map = new HashMap<String, Integer>();
    static int up_last = 0;
    static int up_pop = 0;
    static int up_energy =0;
    static int up_size = 0;

    static int get_up_pop()
    {
        return up_pop;
    }
    static int get_up_last()
    {
        return up_last;
    }
    static int get_up_energy()
    {
        return up_energy;
    }
    static int get_up_size()
    {
        return up_size;
    }

    static int add_pop()
    {
        ++up_pop;
        return up_pop * 100;
    }
    static int add_last()
    {
        ++up_last;
        return up_last * 100;
    }
    static int add_energy()
    {
        ++up_energy;
        return up_energy * 100;
    }
    static int add_size()
    {
        ++up_size;
        return up_size * 100;
    }
    Songs add_song(int uppop, int uplast)
    {
        Songs s = new Songs(genre, name, creativity, popularity *
          (int)((1 + bb_dom.get(genre)/150.0) * (1 + genre_map.get(genre)/150.0)), uppop, uplast, true);

        discog.add(s);
        popularity += s.get_pop()/14;
        energy -= 21;
        return s;
    }
    String ret_info()
    {
        String n = "Name: " + name + "\n";
        String g = "Genre: " + genre + "\nCurrent Bonus: " +
                Integer.toString( !genre_map.containsKey(genre) ? 0:
                        genre_map.get(genre) * 10) + "%\n";
        String p = "Popularity: " + Integer.toString(popularity) + "\n";
        String c = "Creativity: " + Integer.toString(creativity) + "\n";
        String sc = "Social Media: " + Integer.toString(social_med) + "\n";
        String cu = "Cut: " + Integer.toString(cut);
//		String e = "Energy:    \t" + to_String(energy) + "\n";
//		String a = "Age:\t" + to_String(days) + "\n\n";
        return n + g + p + c + sc + cu;
    }
    Vector<String> art_butt()
    {
        Vector<String> ret_vec = new Vector<String>();
        ret_vec.add("Name: " +  get_name());
        ret_vec.add("Genre: " + get_genre());
        ret_vec.add("Weeks Signed: " + Integer.toString(get_weeks()));
        ret_vec.add("Popularity: " + Integer.toString(get_pop()));
        ret_vec.add("Creativity: " + Integer.toString(get_creativity()));
        ret_vec.add("Social Media: " + Integer.toString(get_soc_med()));
        ret_vec.add("Cut: "+ Integer.toString(get_cut()));
        ret_vec.add("Earned: "  + Integer.toString(get_earned()));
        ret_vec.add("____________________________");
        ret_vec.add(" ");
        return ret_vec;
    }


}
