package com.example.prest.musictycoon;//package attemptreset.java;
import java.util.*;

public class Artists extends Songs {
    static Vector <String> artists_names = MainActivity.init_vector("artists_names.txt");
    static HashMap <String, Integer> bb_dom = new HashMap<String, Integer>();

    Vector <Songs> discog = new Vector<Songs>();
    int w_earned;
    int event_count;
    int energy;
    int weeks;
    int earned;
    int cut;
    int creativity;
    int social_med;
    String genre;

        Artists()
        {
            w_earned = 0;
            event_count = 0;
            energy = 30;
            weeks = 0;
            earned = 0;
            Genres g = new Genres();
            genre = g.ret_genre();
//            genre.erase(genre.end()-1, genre.end());
            creativity = Randoms.gen_num(13);
            social_med = Randoms.gen_num(20);
            cut = Randoms.gen_num(8)/3;
            if ( artists_names.size() > 0)
            {
                int num = Randoms.gen_uniform(artists_names.size());
                name = artists_names.elementAt(num);
                artists_names.remove( num );

            }
        }

        Songs add_song(int uppop, int uplast)
        {
            Integer count = Artists.bb_dom.containsKey(genre)
                    ? Artists.bb_dom.get(genre) : 0;
            Songs s = new Songs(genre, name, creativity, popularity *
                    (int)(1 + (count/120.0)), uppop, uplast, false);
            discog.add(s);
            popularity += s.get_pop()/12;
            energy -= 16;
            return s;
        }
        Vector <Songs> get_discog()
        {
//            sort( discog.begin(), discog.end(), [] (  Songs & left,
//                Songs & right)
//            {
//                return left.get_total_streams() > right.get_total_streams();
//            });
            Collections.sort(discog, new SortbyStreams());
            return discog;
        }
        void age_discog(int num)
        {
            //ages the artist
            weeks += num;
        energy += 7;
        if ( event_count > 0 )
        {
            event_count -= 1;
        }
        if ( energy >= 32 )
        {
            update_pop( 0.975, num);
        }

        //ages the discog
        w_earned = 0;
        for( int i = 0; i < discog.size(); ++i )
        {
            Songs s = discog.elementAt(i);
            s.age_song(num);
            earned += s.get_streams() * cut * 0.0005;
            w_earned += s.get_streams() * cut * 0.0005;
        }
	}
        int get_creativity()
        {
            return creativity;
        }
        int get_soc_med()
        {
            return social_med;
        }
        int get_energy()
        {
            return energy;
        }
        int get_weekly_earned()
        {
            return w_earned;
        }
        int get_earned()
        {
            return earned;
        }
        int get_cut()
        {
            return cut;
        }
        String get_genre()
        {
            return genre;
        }
        int get_weeks()
        {
            return weeks;
        }
        int get_event_count()
        {
            return event_count;
        }
        String start_event( int num )
        {
            String builder = "\n";
            energy -= num * 7;
            event_count = num;
            switch( num )
            {
                case 3:
                    energy -= num * 7;
                    popularity += 8;
                    social_med += 10;
                    return "";
                case 6:
                    Collections.sort( discog, new SortbyPop());
                    discog.elementAt(0).update_pop( 1.2, 1.5 );
                    social_med += 10;
                    lasting += 10;
                    return discog.elementAt(0).get_name();
                case 10:
                    Collections.sort( discog,new SortbyPop());
                    for( int i = 0; i < 5 && i < discog.size(); ++i )
                    {
                        discog.elementAt(i).update_pop( 1.15, 1.5);
                        builder += discog.elementAt(i).get_name() + "\n";
                    }
                    popularity += 10;
                return builder;
            }
            return "";
        }

//        bool operator < ( const Artists & rhs ) const
//        {
//            int own_total = 0;
//            int rhs_total = 0;
//            for ( auto s: discog)
//            {
//                own_total += s.get_pop();
//            }
//            for ( auto s: rhs.discog )
//            {
//                rhs_total += s.get_pop();
//            }
//            return own_total > rhs_total;
//        }


}
