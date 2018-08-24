package com.example.prest.musictycoon;//package attemptreset.java;
import java.util.*;


public class Randoms {
    static int gen_num(int desiredStandardDeviation){
        Random r = new Random();
        return (int) (r.nextGaussian()*desiredStandardDeviation+57);
    }
    static int gen_uniform( int num )
    {
        Random r = new Random();
        return r.nextInt(num - 1);
    }
}
