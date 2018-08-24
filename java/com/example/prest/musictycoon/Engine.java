package com.example.prest.musictycoon;//package attemptreset.java;
import java.util.*;


class Engine
{
	int score;
	int weeks;
	int myBal;
	int current_artist;
	Vector <OArtists> artists_v = new Vector<OArtists>();
	Vector <BBArtists> bb_v = new Vector<BBArtists>();
	Vector <OArtists> choices_v = new Vector<OArtists>();
	Vector <Songs> bb = new Vector<Songs>();
	HashMap < String, String > goals = new HashMap<String,String>();
	String menu_c;
	String ceiling_floor;

	Engine(int num )
	{
	    score = 0;
	    weeks = 0;
	    myBal = num;
	    current_artist = 0;
	    String DEFVAL = "Locked";
		goals.put("Unstoppable", DEFVAL);
        goals.put("Loyal", DEFVAL);
        goals.put("Golden Goose", DEFVAL);
        goals.put("Straight", DEFVAL);
        goals.put("Scrooge", DEFVAL);
        goals.put("Shiny III", DEFVAL);
        goals.put("Shiny II", DEFVAL);
        goals.put("Shiny I", DEFVAL);
        goals.put("Champion", DEFVAL);
        goals.put("Kanye West", DEFVAL);
        goals.put("Mr. Worldwide", DEFVAL);
        goals.put("Moneybags", DEFVAL);
        goals.put("Late Night", DEFVAL);
        goals.put("Bound 2", DEFVAL);
        goals.put("Trash", DEFVAL);
        goals.put("Hermit", DEFVAL);
        goals.put("Loss", DEFVAL);
        goals.put("Bold and Brash", DEFVAL);
		menu_c = "main_menu";
		ceiling_floor =  "______________________________________________\n";
		init_songs();
	}
	String choose( int num )
	{
		if ( menu_c.equals("main_menu" ))
		{
			return mm_choice(num);
		}
		else if ( menu_c.equals("art_list" ))
		{
			return al_choice(num);
		}
		else if ( menu_c.equals("one_art"))
		{
			return oa_choice(num);
		}
		else if ( menu_c.equals("event"))
		{
			return ev_choice(num, artists_v.elementAt(current_artist-1));
		}
		else if ( menu_c.equals("c_artists"))
		{
			return c_choice(num);
		}
		else if (menu_c.equals("billboards"))
		{
			return b_choice(num);
		}
		else if ( menu_c.equals("upgrades") )
		{
			return up_choice(num);
		}
		return "";
	}
	String calc_score()
	{
		HashMap <String, Integer> cert_map = calc_cert();
		String art_size     = "Number of Artists:    " + Integer.toString( artists_v.size() ) + "\n";
		String gold 		= "Number of Golds:      " + Integer.toString( cert_map.get("Gold") ) + "\n";
		String plat 		= "Number of Platinums:  " + Integer.toString( cert_map.get("Plat") ) + "\n";
		String diamond 		= "Number of Diamonds:   " + Integer.toString( cert_map.get("Diamond") ) + "\n";
		String balance =  "Finishing Balance:\t" + Integer.toString(myBal) + "\n";
		String awards =   "";

		int cert = ( cert_map.get("Gold") * 100 ) + ( cert_map.get("Plat") * 500 )
				+ ( cert_map.get("Diamond") * 20000);
		score = artists_v.size() * 500 + myBal/100 + cert;
		String final_score = "Game Over\n\tYour final score is " + Integer.toString(score) + "\n";
		return ceiling_floor + art_size + gold + plat + diamond + balance + awards + final_score + ceiling_floor;
	}
	int get_score()
	{
		return score;
	}

	//Private
	void init_songs()		//starts with 18 artists each with 3 songs
	{
		for ( int i = 0; i < Genres.genre_v.size(); ++i )
        {
            Artists.bb_dom.put(Genres.genre_v.get(i), 0);
        }
		OArtists a = new OArtists();
		oa_sign(a);
		for ( int i = 0; i < 18 ; ++i )
		{
			b_sign();
		}
//		for( int i = 0; i < 4; ++i )
//		{
//			shared_ptr<OArtists> a( new OArtists );
//			oa_sign(a);
//		}
		choose_artist();
	}

	HashMap <String, Integer> calc_cert()
	{
		HashMap <String, Integer> cert_map = new HashMap<String, Integer>();
		cert_map.put("Gold", 0);
		cert_map.put("Plat", 0);
		cert_map.put("Diamond", 0);
		for( int i = 0; i < artists_v.size(); ++i )
		{
			for( int j = 0; j < artists_v.get(i).get_discog().size(); ++j )
			{
				int num = artists_v.get(i).get_discog().get(j).update_cert();
				if ( num == 1 )
				{
				    int count = cert_map.get("Gold");
					cert_map.put("Gold", count + 1);
					if( goals.get("Shiny I") == "Locked")
					{
						goals.put("Shiny I", artists_v.get(i).get_discog().elementAt(j).name);
					}
				}
				else if ( num != 0 && num < 20 && num % 2 == 0 )
				{
                    int count = cert_map.get("Plat");
                    cert_map.put("Plat", count + num/2 );
					if ( goals.get("Shiny II") == "Locked" )
					{
						goals.put("Shiny II", artists_v.get(i).get_discog().elementAt(j).name);
					}
				}
				else if ( num > 20 )
				{
                    int count = cert_map.get("Diamond");
                    cert_map.put("Diamond", count + num /20);
					if ( goals.get("Shiny III") == "Locked" )
					{
						goals.put("Shiny III", artists_v.get(i).get_discog().elementAt(j).name);
					}
				}
			}
		}
		return cert_map;
	}

//	String Integer.toString_genre()
//	{
//		String builder = "";
//		int num = 0;
//		for( Vector<String>.iterator i = genre_v.begin(); i!= genre_v.end(); ++i)
//		{
//			builder += Integer.toString(++num) + ". " + *i + "\n";
//		}
//		return builder;
//	}
	String stud_str( int num )
	{
		String builder = "";
		for ( int i = 0; i < num; ++i )
		{
			builder += "*";
		}
		return builder;
	}
	String converter( long num )
	{
		if ( num > 100000000)
		{
			return Long.toString( num/100000000) + "B ";
		}
		else if ( num > 100000)
		{
			return Long.toString( num/100000 ) + "M ";
		}
		return Long.toString(num * 10);
	}
	String pass_artist_time(int num)
	{
//	    for ( int i = 0; i < bb.size(); ++i )
//        {
//            bb.get(i).age_song(num);
//        }
		return update_songs(num) + helper_time( artists_v, num, true );
	}

	String update_songs(int num)
	{
		String builder = "";
		bb_domination();
		helper_time( bb_v, num, false );
		for( int i = 0; i < bb_v.size(); ++i )
		{
			if ( bb_v.get(i).get_energy() >= 36 * (Randoms.gen_num(10)/55.0))
			{
			    bb.add( bb_v.get(i).add_song( BBArtists.get_up_pop(), BBArtists.get_up_last()) );
			}
		}
        for( int i = 0; i < artists_v.size(); ++i )
		{
			if ( ( 1 + OArtists.get_up_energy()/9.0) * artists_v.get(i).get_energy() >=
					42 * (Randoms.gen_num(5)/55.0) )
			{
				Songs s = artists_v.get(i).add_song( OArtists.get_up_pop(), OArtists.get_up_last());
				bb.add( s );
				if ( goals.get("Trash") == "Locked " && s.get_pop() < 30 )
				{
					goals.put("Trash", s.get_name());
					builder += "\n**Trash: Debut a song at < 30 popularity**\n";
				}
			}
		}

		if ( BBArtists.get_up_pop() < weeks/12 )
		{
			BBArtists.add_pop();
		}
		if ( BBArtists.get_up_last() < weeks/10 )
		{
			BBArtists.add_last();
		}
		return builder;
	}

    <T extends Artists> String helper_time(Vector <T> s, int num, boolean add_bal )
	{
		String builder = "";
        for( int i = 0; i < s.size(); ++i )
        {
            s.get(i).age_discog(num);
            if ( add_bal )
            {
                myBal += s.get(i).get_weekly_earned();
                if ( goals.get("Golden Goose") == "Locked" && s.get(i).get_earned() >= 500000 )
                {
                    goals.put("Golden Goose", s.get(i).name);
                    builder += "\n**Golden Goose: Have an artist earn you 500,000**\n";
                }
                if ( goals.get("Loyal") == "Locked"  && s.get(i).get_weeks() >= 50 )
                {
                    goals.put("Loyal", s.get(i).name);
                    builder += "\n**Loyal: Have an artist signed for 50 weeks**\n";
                }
            }
        }
        return builder;
	}
	void b_sign()
	{
		BBArtists b = new BBArtists();
		for( int i = 0; i<3; ++i)
		{
			bb.add( b.add_song(BBArtists.get_up_pop(), BBArtists.get_up_last()) );
			b.energy += 20;
		}
		bb_v.add(b);
	}
	void oa_sign( OArtists oa )
	{
        Integer count = OArtists.genre_map.containsKey(oa.get_genre()) ? OArtists.genre_map.get(oa.get_genre())
                : 0;
        OArtists.genre_map.put(oa.get_genre(), count + 1);
        bb.add( oa.add_song( OArtists.get_up_pop(), OArtists.get_up_last()) );
		artists_v.add(oa);
	}

	boolean enough( int num )
	{
		if ( myBal >= num )
		{
			myBal -= num;
			return true;
		}
		return false;
	}

//Menu makers
	String main_menu()
	{
		String title = "|Main Menu\t";
		String balance = "Balance: " + Integer.toString(myBal) + "\t\t\n";
		String time = "|1.\tPlay Week\n";
		String week = "|\tCurrent Week: " + Integer.toString(weeks) + "\n";
		String artists = "|2.\tArtists\n";
		String billboards = "|3.\tBillboards\n";
		String upgrades = "|4.\tStudio Upgrades\n";
		String achievements = "|5.\tAchievements\n";
		//		String high_scores =	"|7.\tHigh Scores\n";
		String quit_choice = "|6.\tQuit\n";

		return ceiling_floor + title + balance + time + week + artists + billboards
			+ upgrades + achievements + quit_choice + ceiling_floor;
	}
	String artist_menu(int num)
	{
		Artists a = artists_v.elementAt(num-1);
		String art_name =		"|Name: " +  a.get_name() + "\t";
		String genre = 			"Genre: " + a.get_genre() + "\n"; // add the genre it is
		String pop = 			"|Popularity:   " + Integer.toString(a.get_pop()) + "  ";
		String creativity = 	"\tCreativity: " + Integer.toString(a.get_creativity()) + "\n";
		String soc = 			"|Social Media: "+ Integer.toString(a.get_soc_med()) + "  ";
		String energy = 		"\tEnergy:     "+ Integer.toString(a.get_energy()) + "\n";
		String cut = 			"|Cut:\t       "+ Integer.toString(a.get_cut()) + "% ";
		String earned = 		"\tEarned:     "  + Integer.toString(a.get_earned()) + "\n";
		String age = 			"|Weeks Signed:  " + Integer.toString(a.get_weeks()) + "\n";

		String back =  			"|0.\tBack\n";
		String disc =  			"|1.\tDiscography\n";
		//String add =  		"|2.\tAdd Song/Album\n";
		String events =  		"|2.\tEvents\n";
		String release =  		artists_v.size() > 1 ? "|3.\tRelease\n" : "";

		return ceiling_floor + art_name + genre + pop + creativity + soc + energy + cut + earned + age +
				back + disc + events + release + ceiling_floor;
	}

	String art_capacity()
	{
		return 	Integer.toString(artists_v.size()) + "/" + Integer.toString(2 + OArtists.get_up_size());
	}
	String artists_list()
	{
		int amount = artists_v.size() == 0 ? 0 : (int)(Math.pow( 3, artists_v.size()) * 10000);
		String art_name =		"|Manage Artists\t\tBalance: "  + Integer.toString(myBal) + "\n";
		int num = 2;
		String builder  = "";
		String back =  			"|0.\tBack\n";
		String sign =  			"|1.\tSign\t\tContract Price: "
				+ Integer.toString( amount ) + "\n";
		String siz = 			"|\t" + art_capacity() + "\n";
		String bonus = 			"|2.\tGenre Bonus\n";
		for( int i = 0; i < artists_v.size(); ++i )
		{
			builder += "|" + Integer.toString(++num) + ".\t" + artists_v.get(i).get_name() +
					"\n|\t\tGenre: " + artists_v.get(i).get_genre() + "\n";
		}

		return ceiling_floor + art_name + back + sign + siz + bonus + builder + ceiling_floor;
	}
	Vector<String> ret_choices()
	{
		Vector <String> s = new Vector<String>();
		for ( int i = 0; i < choices_v.size(); ++i )
		{
			s.add( choices_v.get(i).ret_info() );
		}
		return s;
	}
	String choose_artist()
	{
		String builder = "";
		int init_size = choices_v.size();
		for ( int i = 0; i < 3 - init_size; ++i )
		{
			OArtists a = new OArtists();
			choices_v.add( a );
		}
		for ( int i = 0; i < choices_v.size(); ++i )
		{
			builder += Integer.toString(i+1) + ceiling_floor +  ret_choices().get(i);
		}
		builder += artists_v.size() > 0 ? "4" + ceiling_floor + "Reset choices\nCost: " +
				Integer.toString( (int)(Math.pow( 3, artists_v.size()) * 3000)) + "\n" : "";
		return builder;
	}
	String up_price(int type_up)
	{
		return type_up < 7 ? "Price: " + Integer.toString((int)(Math.pow( 2, type_up + 1 )) * 10000) : "Max";
	}
	Vector <String> get_up_price()
	{
		Vector <String> retVec = new Vector<String>();
		retVec.add(" Studio\n(Songs last longer)\n" + up_price(OArtists.get_up_last())
		 		+ "\n" + stud_str(OArtists.get_up_last()));
		retVec.add(" Production\n(Song quality)\n" + up_price(OArtists.get_up_pop())
				+ "\n" + stud_str(OArtists.get_up_pop()));
		retVec.add(" Amenities\n(More frequent songs)\n" + up_price(OArtists.get_up_energy())
				+ "\n" + stud_str(OArtists.get_up_energy()));
		retVec.add(" Studio Size\n(Can sign more artists)\n" + up_price(OArtists.get_up_size())
				+ "\n" + stud_str(OArtists.get_up_size()));
		return retVec;
	}
	String studio_upgrades()
	{
		String stud_up = 	"|Studio Upgrades\t\tBalance: "  + Integer.toString(myBal) + "\n";
		String back =  		"|0.\tBack\n";
		String stud = 		"|1.\tUpgrade Studio       (SongLast )\t" + stud_str(OArtists.get_up_last()) + "\n|\tCost: ";
		String studc = 		 Integer.toString( (int)(Math.pow( 2, OArtists.get_up_last() + 1 )) * 10000);

		String prod =  		"\n|2.\tUpgrade Production   (SongPromo)\t" + stud_str(OArtists.get_up_pop()) + "\n|\tCost: ";
		String prodc = 		 Integer.toString( (int)(Math.pow( 2, OArtists.get_up_pop() + 1 )) * 10000);

		String gift =  		"\n|3.\tUpgrade Amenities    (Energy   )\t" + stud_str(OArtists.get_up_energy()) + "\n|\tCost: ";
		String giftc = 		 Integer.toString( (int)(Math.pow( 2, OArtists.get_up_energy() + 1 )) * 10000);

		String size =  		"\n|4.\tUpgrade Studio Size  (#Artists )\t" + stud_str(OArtists.get_up_size()) + "\n|\tCost: ";
		String sizec = 		 Integer.toString( (int)(Math.pow( 2, OArtists.get_up_size() + 1 )) * 10000) + "\n";

		return ceiling_floor + stud_up + back + stud + studc +
				prod + prodc + gift + giftc + size + sizec + ceiling_floor;
	}
//Everything billboards
	String bb_menu()
	{
		String back = 			"|0.\tBack\n";
		String billboards = 	"|1.\tBillboards\n";
//		String all_songs = 		"|2.\tAll Songs\n";
		String smart_board =	"|3.\tOwn Songs\n";
		String billboard_dom = 	"|4.\tBillboard Domination\n";
		return ceiling_floor + back + billboards + smart_board + billboard_dom + ceiling_floor;
	}
	Vector<String> bb_dom_v()
	{
		Artists.bb_dom.clear();
		for ( int i = 0; i < Genres.genre_v.size(); ++i )
		{
			Artists.bb_dom.put(Genres.genre_v.get(i), 0);
		}
		Vector <Songs> top_f = top_fourty(bb);
		for ( int i =0; i < top_f.size(); ++i )
		{
			Integer count = Artists.bb_dom.containsKey(top_f.get(i).get_genre())
					? Artists.bb_dom.get(top_f.get(i).get_genre()) : 0;
			Artists.bb_dom.put(top_f.get(i).get_genre(), count + 1);
		}
		Vector<String> ret_vec = new Vector<String>();
		for ( Map.Entry<String, Integer> entry: Artists.bb_dom.entrySet() )
		{
			Artists.bb_dom.replace( entry.getKey(), (100 * entry.getValue()/top_f.size()) );
			ret_vec.add( entry.getKey());
			ret_vec.add( Integer.toString(entry.getValue()) + "%");
		}
		ret_vec.add("                                        ");
		ret_vec.add(" ");
		return ret_vec;
	}
	String bb_domination()
	{
		Artists.bb_dom.clear();
		for ( int i = 0; i < Genres.genre_v.size(); ++i )
		{
			Artists.bb_dom.put(Genres.genre_v.get(i), 0);
		}
		Vector <Songs> top_f = top_fourty(bb);
		for ( int i =0; i < top_f.size(); ++i )
		{
			Integer count = Artists.bb_dom.containsKey(top_f.get(i).get_genre())
					? Artists.bb_dom.get(top_f.get(i).get_genre()) : 0;
			Artists.bb_dom.put(top_f.get(i).get_genre(), count + 1);
		}
		String builder = "";
		for ( Map.Entry<String, Integer> entry: Artists.bb_dom.entrySet() )
		{
		    Artists.bb_dom.replace( entry.getKey(), (100 * entry.getValue()/top_f.size()) );
			builder += entry.getKey() + "\t" + Integer.toString(entry.getValue()) + "%\n";
		}
		return builder;
	}
	Vector <Songs> top_fourty(Vector <Songs> s)
	{
	    Collections.sort( s, new SortbyPop());
		Vector <Songs> retVec = new Vector<Songs>();
		for ( int i = 0; i < s.size() && i < 40; ++i )
        {
            retVec.add( s.get(i));
        }
		return retVec;
	}
	Vector<String> billboards_v()
	{
		Vector <Songs> top_f = top_fourty(bb);
		Vector<String> ret_vec = new Vector<String>();
		int count = 0;
		for( int i = 0; i < top_f.size(); ++i )
		{
			if ( top_f.get(i).get_pop() > 20)
			{
				ret_vec.add("  " + Integer.toString(++count));
				ret_vec.add(top_f.get(i).get_name());
				ret_vec.add(top_f.get(i).get_owner());
				ret_vec.add(Integer.toString(top_f.get(i).get_pop()));
				ret_vec.add(Integer.toString(top_f.get(i).get_weeks()));
//				ret_vec.add("\n        " + Integer.toString(top_f.get(i).get_streams()));
			}
		}
		ret_vec.add("            ");
		ret_vec.add("         ");
		ret_vec.add("            ");
		ret_vec.add("            ");
		ret_vec.add("            ");

		return ret_vec;

	}
	String billboards()
	{
		Vector <Songs> top_f = top_fourty(bb);
		String builder = "Rank\tPop\tWeeks\tWeekly Streams\n";
		int count = 0;
		for( int i = 0; i < top_f.size(); ++i )
		{
			if ( top_f.get(i).get_pop() > 20)
			{
				builder += Integer.toString(++count) + "\t" + top_f.get(i).get_name() + "\t" + //(*b).get_singer() +
						 "\n" + top_f.get(i).get_owner() + "\t" + Integer.toString(top_f.get(i).get_pop()) +
						"\t" + Integer.toString(top_f.get(i).get_weeks()) + "\t" + Integer.toString(top_f.get(i).get_streams())  + "\n";

			}
		}
		return builder;
	}

	Vector<String> smart_board_v()
	{
		Vector <String> ret_vec = new Vector<String>();
		for( int i = 0; i < artists_v.size(); ++i)
		{
			ret_vec.add("");
			ret_vec.add(artists_v.get(i).get_name());
			ret_vec.add(" ");
			ret_vec.add(" ");
			Vector <Songs> s = artists_v.get(i).get_discog();
			for ( int j = 0; j < s.size(); ++j )
			{
				ret_vec.add("    " + converter(s.get(j).get_total_streams()));
				ret_vec.add(s.get(j).get_name());
				ret_vec.add((s.get(j).get_pop() < 10 ? "  " :Integer.toString(s.get(j).get_pop())));
				ret_vec.add(Integer.toString(s.get(j).get_weeks()));
			}
			ret_vec.add("                   ");
			ret_vec.add("                                              ");
			ret_vec.add("          ");
			ret_vec.add("          ");
		}
		return ret_vec;

	}
	String smart_board()
	{
//		sort( av.begin(), av.end());
		String builder = "\tPop\tWeeks\t#Streams\tSong Name\n";
		for( int i = 0; i < artists_v.size(); ++i)
		{
			builder += discog_info(artists_v.get(i));
		}
		if ( builder == "Pop\tWeeks\t#Streams\tSong Name\n")
		{
			builder = "Sign some artists first!!\n";
		}
		return builder;
	}
	Vector<String> discog_v(OArtists a)
	{
		Vector<String> ret_vec = new Vector<String>();
		Vector <Songs> s = a.get_discog();
		for ( int i = 0; i < s.size(); ++i )
		{
			ret_vec.add("    " + converter(s.get(i).get_total_streams()));
			ret_vec.add(s.get(i).get_name());
			ret_vec.add((s.get(i).get_pop() < 10 ? "  " :Integer.toString(s.get(i).get_pop())));
			ret_vec.add(Integer.toString(s.get(i).get_weeks()));
		}
		ret_vec.add("                   ");
		ret_vec.add("                                              ");
		ret_vec.add("          ");
		ret_vec.add("          ");
		return ret_vec;
	}
	String discog_info( OArtists some_art )
	{
//		String builder = some_art.get_name() + "\n\t" + (some_art).get_genre() + "\n";
		String builder = "";
		Vector <Songs> s = some_art.get_discog();
		for ( int i = 0; i < s.size(); ++i )
		{
			builder +=  (s.get(i).get_pop() < 10 ? "     " :Integer.toString(s.get(i).get_pop())) + "        " +
					Integer.toString(s.get(i).get_weeks()) + "              " + converter(s.get(i).get_total_streams())
					+ "                " +  s.get(i).get_name() + "\n";
		}
		return builder;
	}

	String gen_button()
	{
		String builder = "Genre Bonus\n";
		if ( OArtists.genre_map.size() > 0)
		{
			for ( Map.Entry<String, Integer> entry: OArtists.genre_map.entrySet() )
			{
				builder += "    " + entry.getKey() + ": " + Integer.toString(entry.getValue()*10) + "                                       \n";
			}
			return builder;
		}
		return builder;
	}
	String genre_bonus()
	{
		String builder = "|Genre Bonus\n";
		if ( OArtists.genre_map.size() > 0)
		{
            for ( Map.Entry<String, Integer> entry: OArtists.genre_map.entrySet() )
			{
				builder += "|\t" + entry.getKey() + ": " + Integer.toString(entry.getValue()*10) + "\n";
			}
			return ceiling_floor + builder + ceiling_floor;
		}
		return "Sign some artists first!!\n";
	}
	Vector<String> show_goals_v()
	{
		Vector<String> ret_vec = new Vector<String> ();
		for ( Map.Entry<String, String> entry: goals.entrySet() )
		{
			ret_vec.add( entry.getKey());
			ret_vec.add( entry.getValue());
		}
		ret_vec.add("____________________");
		ret_vec.add("               ");
		return ret_vec;

	}
	String display_goals()
	{
		String builder = "Status  Name\n";
        for ( Map.Entry<String, String> entry: goals.entrySet() )
		{
			builder += entry.getValue() + entry.getKey() + "\n";
		}
		return builder;
	}
//Event Requirements
	String inter_req(OArtists a)
	{
		String builder = "";
		if ( a.get_weeks() < 10 )
		{
			builder += "Artist is " + Integer.toString((a).get_weeks()) + "/10 weeks old\n";
		}
		if ( a.get_event_count() > 0 )
		{
			builder += "Artist has to wait " + Integer.toString(a.get_event_count()) +
					" weeks until the next event\n";
		}
		if ( goals.get("Trash") == "Locked")
		{
			builder += "Trash needs to be unlocked\n";
		}
		if ( goals.get("Hermit") =="Locked" )
		{
			builder += "Hermit needs to be unlocked\n";
		}
		return builder.length() == 0 ? "All requirements fulfilled!" : builder.substring(0, builder.length() - 1);
	}
	String mv_req(OArtists a)
	{
		String builder = "";
		if ( a.get_weeks() < 20 )
		{
			builder += "Artist is " + Integer.toString(a.get_weeks()) +
					"/20 weeks old\n";
		}
		if ( a.get_pop() < 60 )
		{
			builder += "Artist has " + Integer.toString(a.get_pop()) +
					"/60 popularity\n";
		}
		if ( a.get_event_count() > 0 )
		{
			builder += "Artist has to wait " + Integer.toString(a.get_event_count()) +
					" weeks until the next event\n";
		}
		if ( goals.get("Shiny I") == "Locked")
		{
			builder += "Shiny I needs to be unlocked\n";
		}
		if ( goals.get("Scrooge") == "Locked")
		{
			builder += "Scrooge needs to be unlocked\n";
		}
		if ( goals.get("Bold and Brash") == "Locked")
		{
			builder += "Bold and Brash needs to be unlocked\n";
		}
		return builder.length() == 0 ? "All requirements fulfilled!" : builder.substring(0, builder.length() - 1);
	}
	String tour_req(OArtists a)
	{
		String builder = "";
		if ( a.get_weeks() < 30 )
		{
			builder += "Artist is " + Integer.toString(a.get_weeks()) +
					"/30 weeks old\n";
		}
		if ( a.get_soc_med() < 85 )
		{
			builder += "Artist has " + Integer.toString(a.get_soc_med()) +
					"/85 social media\n";
		}
		if ( a.get_event_count() > 0 )
		{
			builder += "Artist has to wait " + Integer.toString(a.get_event_count()) +
					" weeks until the next event\n";
		}
		if ( a.get_earned() < 400000 )
		{
			builder += "Artist has earned " + Integer.toString(a.get_earned()) +
					"/400,000\n";
		}
		if ( goals.get("Straight") == "Locked")
		{
			builder += "Straight needs to be unlocked\n";
		}
		if ( goals.get("Shiny II") == "Locked")
		{
			builder += "Shiny II needs to be unlocked\n";
		}
		if ( goals.get("Bound 2") == "Locked")
		{
			builder += "Bound 2 needs to be unlocked\n";
		}
		return builder.length() == 0 ? "All requirements fulfilled!" : builder.substring(0, builder.length() - 1);
	}
	String a_events()
	{
		String title = "|Events\n";
		String back  = "|0.\tBack\n";
		String show  = "|1.\tInterview / Show\n";
		String mv	 = "|2.\tMusic Video\n";
		String tour  = "|3.\tTour\n";
		return ceiling_floor + title + back + show + inter_req(artists_v.elementAt(current_artist - 1)) +
				mv + mv_req(artists_v.elementAt(current_artist - 1)) + tour + tour_req(artists_v.elementAt(current_artist - 1)) + ceiling_floor;
	}


//Choices
	String mm_choice(int num)
	{
		//Main menu
		switch (num)
		{
			case 1:
				return pass_time();
			case 2:
				menu_c = "art_list";
				return artists_list();
			case 3:
				menu_c = "billboards";
				return bb_menu();
			case 4:
				menu_c= "upgrades";
				return studio_upgrades();
			case 5:
				return display_goals();
//			case 6:
//				//Throws an exception to end the game
//				throw ( EndGameException() );
			default:
				return main_menu();
		}
	}
	String al_choice( int num )
	{
		//Artist list menu
		int siz = artists_v.size();
		int amount = artists_v.size() == 0 ? 0 : (int)(Math.pow( 3, artists_v.size()) * 10000);
		if ( num == 1)
		{
			if ( artists_v.size()  + 1 <= 2 + OArtists.get_up_size()  && myBal >= amount )
			{
				menu_c = "c_artists";
				return choose_artist();
			}
		}
		else if ( num == 2)
		{
			return genre_bonus();
		}
		else if ( num == 0)
		{
			menu_c = "main_menu";
			return main_menu();
		}
		else if (num > 2 && num < siz + 3 )
		{
			menu_c = "one_art";
			current_artist = num - 2;
			return artist_menu( current_artist );
		}

		return artists_list();
	}
	int reset_int()
	{
		return (int)( Math.pow( 3, artists_v.size()) * 4000);
	}
	String c_choice( int num )
	{
		menu_c = "art_list";
		if ( num > 0 && num < choices_v.size() + 1 )
		{
			int amount = artists_v.size() == 0 ? 0 : (int)(Math.pow( 3, artists_v.size()) * 10000);
			if ( artists_v.size()  + 1 <= 2 + OArtists.get_up_size() && enough( amount ))
			{
				oa_sign( choices_v.elementAt( num - 1));
				choices_v.remove( num - 1);
				choices_v.add(new OArtists());
			}
		}else if( num == 4 && artists_v.size() > 0 && enough((int)( Math.pow( 3, artists_v.size()) * 4000)) )
		{
			choices_v.clear();
			choose_artist();
		}
		return artists_list() + check_agoals();
	}
	String ev_choice ( int num, OArtists a )
	{
		String builder = "";
		switch( num )
		{
			case 1:
				if ( inter_req(a) != "All requirements fulfilled!")
				{
					return "";
				}
				menu_c = "one_art";
			    if ( goals.get("Late Night") == "Locked")
			    {
			    	goals.put("Late Night", a.name);
			    	builder += "\n**Late Night: Be on a talk show**\n";
			    }
                a.start_event( 3 );
				return a.get_name() + " is a guest on a talk show" + builder;
			case 2:
				if ( mv_req(a) != "All requirements fulfilled!")
				{
					return "";
				}
				menu_c = "one_art";
			    if ( goals.get("Bound 2") == "Locked")
			    {
			    	goals.put("Bound 2", a.name);
			    	builder += "\n**Bound 2: Produce a music video**\n";
			    }
				return a.get_name() + " is making a music video for " +
                        a.start_event( 6 ) + "\n" + builder;
			case 3:
				if ( tour_req(a) != "All requirements fulfilled!")
				{
					return "";
				}
				menu_c = "one_art";
			    if ( goals.get("Mr. Worldwide") == "Locked")
			    {
			    	goals.put("Mr. Worldwide", a.name);
			    	builder += "\n**Mr. Worldwide: Complete a tour**\n";
			    }
				return a.get_name() + " is going on a tour singing " +
                        a.start_event( 10 ) + "\n" + builder;
			default:
				return "";
		}
	}
	boolean in_bounds()
	{
		return artists_v.size() > 0;
	}
	String check_agoals()
	{

		String builder = "";
		if ( goals.get("Straight") == "Locked" && OArtists.genre_map.size() >= 5 )
		{
			goals.put("Straight", "5 Different Genres");
			builder += "\n**Straight: Sign 5 artists of different genres**\n";
		}
		if ( goals.get("Scrooge") == "Locked" && in_bounds() &&
                artists_v.elementAt(artists_v.size() - 1).get_cut() >= 21)
		{
			goals.put("Scrooge", artists_v.elementAt(artists_v.size() -1).name);
			builder += "\n**Scrooge: Sign an artist taking a 21% cut**\n";
		}
		if ( goals.get("Hermit") == "Locked" && in_bounds() &&
                artists_v.elementAt( artists_v.size() - 1).get_soc_med() < 35 )
		{
			goals.put("Hermit", artists_v.elementAt( artists_v.size() - 1).name);
			builder += "\n**Hermit: Sign an artist with less than 35 social media**\n";
		}
		if ( goals.get("Bold and Brash") == "Locked" && in_bounds() &&
                artists_v.elementAt( artists_v.size() - 1).get_creativity() > 64)
		{
			goals.put("Bold and Brash", artists_v.elementAt( artists_v.size() - 1).name);
			builder += "\n**Bold and Brash: Sign an artist with 65 creativity**\n";
		}
		return builder;
	}
	String oa_choice(int num)
	{
		//Single artist menu
		String curgenre = artists_v.elementAt(current_artist-1).get_genre();
		switch(num)
		{
			case 0:
				menu_c = "art_list";
				return artists_list();
			case 1:
				return "\tPop\tWeeks\t#Streams\tSong Name\n" + discog_info( artists_v.elementAt( current_artist - 1) );
			case 2:
				menu_c = "event";
				return a_events();
			case 3:
				if ( artists_v.size() > 1 )
				{
					Integer count = OArtists.genre_map.containsKey(curgenre)
							? OArtists.genre_map.get(curgenre) : 0;
                    OArtists.genre_map.put(curgenre, count - 1);
                    if ( count == 0 )
                    {
                        OArtists.genre_map.remove( curgenre );
                    }
                    artists_v.remove( current_artist-1);
                    myBal += artists_v.size() <= 1 ? 0 : (int)(Math.pow( 3, artists_v.size() - 1) * 10000);
                    menu_c = "art_list";
				}
				return artists_list();
			default:
				return artist_menu(current_artist);
		}
	}
	String b_choice( int num )
	{
		//Display Billboard
		switch(num)
		{
			case 0:
				menu_c = "main_menu";
				return main_menu();
			case 1:
				return billboards();
//			case 2:
//				return smart_board(bb_v);
			case 2:
				return smart_board();
			case 4:
				return bb_domination();
			default:
				return "";
		}
	}
	String up_choice( int num )
	{
		//Upgrade menu
		switch(num)
		{
			case 0:
				menu_c = "main_menu";
				return main_menu();
			case 1:
				if ( OArtists.get_up_last() <= 6 &&
						enough( (int)Math.pow( 2, OArtists.get_up_last() + 1 ) * 10000) )
				{
					OArtists.add_last();
				}
				return studio_upgrades();
			case 2:
				if ( OArtists.get_up_pop() <= 6  &&
						enough( (int)Math.pow( 2, OArtists.get_up_pop() + 1 ) * 10000) )
				{
					OArtists.add_pop();
				}
				return studio_upgrades();
			case 3:
				if ( OArtists.get_up_energy() <= 6  &&
						enough( (int)Math.pow( 2, OArtists.get_up_energy() + 1 ) * 10000 ) )
				{
					OArtists.add_energy();
				}
				return studio_upgrades();
			case 4:
				if ( OArtists.get_up_size() <= 6  &&
						enough( (int)Math.pow( 2, OArtists.get_up_size() + 1 ) * 10000 ) )
				{
					OArtists.add_size();
				}
				return studio_upgrades();
			case 5:
				menu_c = "main_menu";
				return main_menu();
			default:
				return "";
		}
	}

	String pass_time()
	{
		String builder = "";
		++weeks;

		builder += pass_artist_time(1);
//		try{
//			builder += pass_artist_time(1);
//		} catch( ... )
//		{
//			cout << "Pass_artist_time crash";
//		}
//		if ( weeks >= 10000 )
//		{
//			throw( EndGameException() );
//		}
	    if ( goals.get("Moneybags") == "Locked" && myBal >= 2500000 )
	    {
			goals.put("Moneybags", "Multi Millionaire");
	    	builder += "\n**Moneybags: Accumulate 2,500,0000**\n";
	    }
		calc_cert();
	    return main_menu() + builder;
	}

}
