package com.example.prest.musictycoon;//package attemptreset.java;
import java.util.*;

public class Songs {
    static Vector <String> songs_v = MainActivity.init_vector("song_names.txt");
    int cert;
    String name;
    String genre;
    String singer;
    int popularity;
    int weeks;
    long streams;
    boolean mine;
    int lasting;
    Songs()
    {
        cert = 0;
        popularity = Randoms.gen_num(10);
        weeks = 0;
        streams = 0;
        lasting = Randoms.gen_num(3);
        mine = false;
    }
    Songs( String gen, String sing, int cre, int art_pop,
           int up_pop, int up_last, boolean own)
    {
        cert = 0;
        genre = gen;
        singer = sing;
        weeks = 0;
        streams = 0;
        mine = own;
        popularity = (int)((Randoms.gen_num((cre/4)) *
                (.855 + (art_pop/380.0) * (1.0 + (up_pop/69.0)))));
        lasting = 10 + Math.abs((int)(Randoms.gen_num(25) * (1.0 + up_last/30.0)));
        if (songs_v.size() > 0)
        {
            int num = Randoms.gen_uniform(songs_v.size());
            name = songs_v.elementAt(num);
            songs_v.remove( num );
        }
        streams = 0;

    }

    void update_pop( double base, double exp)
    {
        popularity = (int)(popularity * Math.pow( base, exp ));
    }
    String get_name()
    {
        return name;
    }
    int get_weeks()
    {
        return weeks;
    }
    int get_streams()
    {
        if ( weeks == 0 )
        {
            return 0;
        }
        return (int)(popularity * 1000 * Math.pow(1.03, popularity));
    }
    long get_total_streams()
    {
        return streams;
    }
    String get_song_info()
    {
        String n = "\nName:       " + name + "\n";
        String g = "Genre:      " + genre + "\n";
        String p = "Popularity: " + Integer.toString(popularity) + "\n";
        String d = "Weeks:\t    " +  Integer.toString(weeks);

        return n + g + p + d;
    }
    String get_singer()
    {
        return singer;
    }
    int get_pop()
    {
        if ( popularity < 1)
        {
            popularity = 0;
        }
        return popularity;
    }
    String get_genre()
    {
        return genre;
    }

    int age_song(int num)
    {
        lasting = (int)(lasting/( 1 + weeks * 0.035));
        streams += 7800 * Math.pow(1.086, popularity/1.3);
        update_pop( 0.996, num * 2 * (60.0/lasting) );
        ++weeks;
        update_cert();
        return weeks;
    }
    int update_cert()
    {
        if ( streams > 10000000)
        {
            cert = 2 * (int)( streams/10000000);
        }
        else
        {
            cert = (int)(streams/ 5000000);
        }
        return cert;
    }
    String get_owner()
    {
        return mine ? "*" : "";
    }

//    bool operator < ( const Songs & rhs ) const
//    {
//        return popularity > rhs.popularity;
//    }

}
class SortbyStreams implements Comparator<Songs>
{
    public int compare(Songs s1, Songs s2)
    {
        if ( s1.streams == s2.streams )
        {
            return 0;
        }
        else if ( s1.streams > s2.streams )
        {
            return -1;
        }
        else if ( s1.streams < s2.streams)
        {
            return 1;
        }
        return 0;
    }
}
class SortbyPop implements Comparator<Songs>
{
    public int compare(Songs s1, Songs s2)
    {
        return s2.popularity - s1.popularity;
    }
}